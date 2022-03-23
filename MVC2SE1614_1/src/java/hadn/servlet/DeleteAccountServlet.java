/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hadn.servlet;

import hadn.registration.RegistrationDAO;
import hadn.registration.RegistrationDTO;
import hadn.utils.MyApplicationConstants;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
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
@WebServlet(name = "DeleteAccountServlet", urlPatterns = {"/DeleteAccountServlet"})
public class DeleteAccountServlet extends HttpServlet {

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
        Properties siteMaps = (Properties) context.getAttribute("SITEMAPS");
        response.setContentType("text/html;charset=UTF-8");
        String username = request.getParameter("pk");
        String role = request.getParameter("role");
        boolean isAdmin = false;
        if (role.equals("true")) {
            isAdmin = true;
        }
        String searchValue = request.getParameter("lastSearchValue");
//        String url = ERROR_PAGE;
        String url = siteMaps.getProperty(MyApplicationConstants.DeleteAccountFeature.BLINK_ERROR_PAGE);
        try {
            //1. Call Dao
            HttpSession session = request.getSession(false);
            if (session != null) {
                RegistrationDTO user = (RegistrationDTO) session.getAttribute("USER");
                if (!user.getUsername().equals(username)) {
                    if (user.isRole() == false) {
                        if (isAdmin == false) {
                            RegistrationDAO dao = new RegistrationDAO();
                            boolean result = dao.deleteAccount(username);
                            if (result) { //gọi search lại 1 lần nữa
                                url = "searchLastnameController?"
                                        //                        + "?btAction=Search"
                                        + "&txtSearchValue=" + searchValue;
                            }
                        } else {
                            url = "searchLastnameController?"
                                    + "&adminDeleteError=" + "Normal user cannot delete admin"
                                    + "&txtSearchValue=" + searchValue;
                        }
                    } else {
                        RegistrationDAO dao = new RegistrationDAO();
                        boolean result = dao.deleteAccount(username);
                        if (result) { //gọi search lại 1 lần nữa
                            url = "searchLastnameController?"
                                    //                        + "?btAction=Search"
                                    + "&txtSearchValue=" + searchValue;
                        }
                    }
                } else {
                    url = "searchLastnameController?"
                            + "&selfDeleteError=" + "User cannot delete themselves"
                            + "&txtSearchValue=" + searchValue;
                }
            }
        } catch (NamingException ex) {
            log("DeleteAccountServlet _ Naming" + ex.getMessage());
        } catch (SQLException ex) {
            log("DeleteAccountServlet _ SQL" + ex.getMessage());
        } finally {
            response.sendRedirect(url);
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
