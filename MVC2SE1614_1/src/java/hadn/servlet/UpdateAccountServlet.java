/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hadn.servlet;

import hadn.registration.RegistrationCreateError;
import hadn.registration.RegistrationDAO;
import hadn.utils.MyApplicationConstants;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Properties;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ACER
 */
@WebServlet(name = "UpdateAccountServlet", urlPatterns = {"/UpdateAccountServlet"})
public class UpdateAccountServlet extends HttpServlet {

//    private final String ERROR_PAGE = "blinkError.html";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ServletContext context = this.getServletContext();
        Properties siteMaps = (Properties)context.getAttribute("SITEMAPS");
        response.setContentType("text/html;charset=UTF-8");
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String role = request.getParameter("chkAdmin");
        boolean admin = false;
        if (role != null) {
            admin = true;
        }
        String searchValue = request.getParameter("lastSearchValue");
//        String url = ERROR_PAGE;
        String url = siteMaps.getProperty(MyApplicationConstants.UpdateAccountFeature.BLINK_ERROR_PAGE);
        RegistrationCreateError errors = new RegistrationCreateError();
        boolean foundErr = false;
        try {
            
            //practice
            
            //1. Check Constrains
            if (password.trim().length() < 6
                    || password.trim().length() > 30) {
                foundErr = true;
                errors.setPasswordLengthError("Password is required from 6 to 30 characters");
            }
            if (foundErr) {
       //         System.out.println("sai");
                

                
                url = "searchLastnameController?"
//                            + "?btAction=Search"
                        + "&txtSearchValue=" + searchValue
                        + "&createError=" + errors.getPasswordLengthError()
                        + "&usernameError=" + username
                                ;

            } else {
                RegistrationDAO dao = new RegistrationDAO();
                boolean result = dao.updateAccount(username, password, admin);
                if (result) {
                    url = "searchLastnameController?"
//                            + "?btAction=Search"
                            + "&txtSearchValue=" + searchValue;
                }
            }
            
            //practice
//                if (password.trim().length() < 6 || password.trim().length() > 30) {
//                    foundErr = true;
//                    errors.setPasswordLengthError("Password must be from 6 to 30 characters.");
//                } if (foundErr) {
//                    url = "searchLastnameController?"
//                            + "createError=" + errors.getPasswordLengthError()
//                            + "&usernameError=" + username
//                            + "&txtSearchValue=" + searchValue;
//                } else {
//                    RegistrationDAO dao = new RegistrationDAO();
//                    boolean result = dao.updateAccount(username, password, admin);
//                    if (result)
//                    url = "searchLastnameController?"
//                            + "txtSearchValue=" + searchValue;
//                }
            //end
 
        } catch (NamingException ex) {
            log("UpdateAccountServlet _ Naming" + ex.getMessage());
        } catch (SQLException ex) {
            log("UpdateAccountServlet _ SQL" + ex.getMessage());
        } finally {
            response.sendRedirect(url);
//            RequestDispatcher rd = request.getRequestDispatcher(url);
//            rd.forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
