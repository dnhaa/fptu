/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hadn.product;

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
public class ProductDAO implements Serializable {
    private List<ProductDTO> books;

    public List<ProductDTO> getBooks() {
        return books;
    }
    
    public void seachBookName(String bookName) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "Select id_pd, name_pd, quantity_pd, price_pd, status_pd "
                        + "From Product "
                        + "Where name_pd Like ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + bookName + "%");
                rs = stm.executeQuery();
                while (rs.next()){
                    String id = rs.getString("id_pd");
                    String name = rs.getString("name_pd");
                    int quantity = rs.getInt("quantity_pd");
                    double price = rs.getDouble("price_pd");
                    boolean status = rs.getBoolean("status_pd");
                    
                    ProductDTO dto = new ProductDTO(id, name, quantity, price, status);
                    
                    if (this.books == null) {
                        this.books = new ArrayList<>();
                    }
                    this.books.add(dto);
                }
            }
            
        } finally{
            if (rs != null){
                rs.close();
            }
            if (stm != null){
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

}
