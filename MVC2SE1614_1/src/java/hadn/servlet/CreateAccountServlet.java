/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hadn.servlet;

import hadn.registration.RegistrationCreateError;
import hadn.registration.RegistrationDAO;
import hadn.registration.RegistrationDTO;
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

/**
 *
 * @author ACER
 */
@WebServlet(name = "CreateAccountServlet", urlPatterns = {"/CreateAccountServlet"})
public class CreateAccountServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
//    private final String ERROR_PAGE = "createAccountError.jsp";
//    private final String LOGIN_PAGE = "login.html";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ServletContext context = this.getServletContext();
        Properties siteMaps = (Properties)context.getAttribute("SITEMAPS");
        response.setContentType("text/html;charset=UTF-8");
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String confirm = request.getParameter("txtConfirm");
        String fullname = request.getParameter("txtFullname");
        
        RegistrationCreateError errors = new RegistrationCreateError();
        boolean foundErr = false;
//        String url = ERROR_PAGE;
        String url = siteMaps.getProperty(MyApplicationConstants.CreateAccountFeature.ERROR_PAGE);
        try {
            //1. Check constrains users' errors
            if (username.trim().length() < 6 || username.trim().length() > 20){
                foundErr = true;
                errors.setUsernameLengthError("Username is required from 6 to 20 chars");
            }
            if (password.trim().length() < 6 || password.trim().length() > 30){
                foundErr = true;
                errors.setPasswordLengthError("Password is required from 6 to 30 chars");
            }
            else if (!confirm.trim().equals(password.trim())){
                foundErr = true;
                errors.setConfirmNotMatched("Confirm must match password");
            }
            if (fullname.trim().length() < 6 || fullname.trim().length() > 50){
                foundErr = true;
                errors.setFullnameLengthError("Full name is required from 6 to 50 chars");
            }
            
            //2. Process
            if (foundErr) {
                request.setAttribute("CREATEERRORS", errors);
            } else {
                //2.2. Call DAO
                RegistrationDAO dao = new RegistrationDAO();
                RegistrationDTO dto = new RegistrationDTO(username, password, fullname, false);
                boolean result = dao.createNewAccount(dto);
                
                if (result) {
//                    url = LOGIN_PAGE;
                    url = siteMaps.getProperty(MyApplicationConstants.DispatchFeatures.LOGIN_PAGE);
                }
            }
        }catch (SQLException ex) {
            String msg = ex.getMessage();
            log("CreateAccountServlet _ SQL " + msg);
            errors.setUsernameIsExisted(username + " is existed");
            request.setAttribute("CREATEERRORS", errors);
        } catch (NamingException ex) {
            log("CreateAccountServlet _ Naming " + ex.getMessage());
        }
        finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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
