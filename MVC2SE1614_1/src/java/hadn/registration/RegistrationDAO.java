/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hadn.registration;

import hadn.utils.DBHelper;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author ACER
 */
public class RegistrationDAO implements Serializable {

    private List<RegistrationDTO> accounts;

    public List<RegistrationDTO> getAccounts() {
        return accounts;
    }

    //javax.sql.DataSource

    public boolean checkLoginBoolean(String username, String password)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1. Connect to DB
            con = DBHelper.makeConnection();
            if (con != null) {

                //2. Create SQL String
                String sql = "Select username "
                        + "FROM Registration "
                        + "WHERE username = ? "
                        + "AND password = ?";
                //3. Create Statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                //4. Execte Statement to get Result
                rs = stm.executeQuery();
                //5. Process Result
                if (rs.next()) {
                    return true;
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public void checkLogin(String usernameValue, String passwordValue)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1. Connect to DB
            con = DBHelper.makeConnection();
            if (con != null) {

                //2. Create SQL String
                String sql = "Select username, password, lastname, isAdmin "
                        + "FROM Registration "
                        + "WHERE username = ? "
                        + "AND password = ?";
                //3. Create Statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, usernameValue);
                stm.setString(2, passwordValue);
                //4. Execte Statement to get Result
                rs = stm.executeQuery();
                //5. Process Result
                if (rs.next()) {
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String fullname = rs.getString("lastname");
                    boolean role = rs.getBoolean("isAdmin");

                    RegistrationDTO dto = new RegistrationDTO(username, password, fullname, role);

                    this.accounts = new ArrayList<>();
                    this.accounts.add(dto);

                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public void searchLastName(String searchValue)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1. Connect to DB
            con = DBHelper.makeConnection();
            if (con != null) {

                //2. Create SQL String
                String sql = "Select username, password, lastname, isAdmin "
                        + "From Registration "
                        + "Where lastname Like ?";
                //3. Create Statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%");
                //4. Execte Statement to get Result
                rs = stm.executeQuery();
                //5. Process Result
                while (rs.next()) {

                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String fullname = rs.getString("lastname");
                    boolean role = rs.getBoolean("isAdmin");

                    RegistrationDTO dto = new RegistrationDTO(username, password, fullname, role);

                    if (this.accounts == null) {
                        this.accounts = new ArrayList<>();

                    } //end create new List
                    this.accounts.add(dto);
                }//end traverse Result set
            }//end if connection has opened
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }
    
    //practice
    public boolean deleteAccount(String username) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            //1. Connect to DB
            con = DBHelper.makeConnection();
            if (con != null) {

                //2. Create SQL String
                String sql = "Delete "
                        + "FROM Registration "
                        + "WHERE username = ?";
                //3. Create Statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, username);

                //4. Execte Statement to get Result
                int effectRow = stm.executeUpdate();

                //5. Process Result
                if (effectRow > 0) {
                    return true;

                }

            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }
    //practice
    //start
//    public boolean deleteAccount(String username) throws NamingException, SQLException {
//        Connection con = null;
//        PreparedStatement stm = null;
//        
//        con = DBHelper.makeConnection();
//        if (con != null) {
//            String sql = "Delete from Registration "
//                    + "where username = ?";
//            stm = con.prepareStatement(sql);
//            stm.setString(1, username);
//            int effectRow = stm.executeUpdate();
//            
//            if (effectRow > 0) {
//                return true;
//            }
//        }
//        return false;
//    }


    //end

    public boolean updateAccount(String username, String password, boolean role) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            //1. Connect to DB
            con = DBHelper.makeConnection();
            if (con != null) {

                //2. Create SQL String
                String sql = "Update Registration "
                        + "SET password = ? "
                        + ", isAdmin = ? "
                        + "WHERE username = ?";
                //3. Create Statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, password);
                stm.setBoolean(2, role);
                stm.setString(3, username);

                //4. Execte Statement to get Result
                int effectRow = stm.executeUpdate();

                //5. Process Result
                if (effectRow > 0) {
                    return true;

                }

            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public boolean createNewAccount(RegistrationDTO dto) throws SQLException, NamingException {
        if (dto == null) {
            return false;
        } //end dto existed
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "Insert Into Registration("
                        + "username, password, lastname, isAdmin"
                        + ") Values (?, ?, ?, ?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, dto.getUsername());
                stm.setString(2, dto.getPassword());
                stm.setString(3, dto.getFullName());
                stm.setBoolean(4, dto.isRole());

                int effectRow = stm.executeUpdate();

                if (effectRow > 0) {
                    return true;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }
}
