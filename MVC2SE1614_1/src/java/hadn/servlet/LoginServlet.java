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
//import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ACER
 */
public class LoginServlet extends HttpServlet {

//    private final String INVALID_PAGE = "invalid.html";
//    private final String SEARCH_PAGE = "search.jsp";

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
        //PrintWriter out = response.getWriter();
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
//        String url = INVALID_PAGE;
        String url = siteMaps.getProperty(MyApplicationConstants.LoginFeature.INVALID_PAGE);
//        HttpSession session = request.getSession();
        try {

            //check number
//                try{
//                    int user = Integer.parseInt(username);
//                    int pass = Integer.parseInt(password);
//                }catch(NumberFormatException nfe){
//                    url = INVALID_PAGE;
//                    return;
//                }
            
            //practice
            
//            1. Call Model
            RegistrationDAO dao = new RegistrationDAO();
            //boolean result = dao.checkLogin(username, password);
            dao.checkLogin(username, password);
            List<RegistrationDTO> result = dao.getAccounts();
            
            //2. Process result
            if (result != null) {
//                url = SEARCH_PAGE;
                url = siteMaps.getProperty(MyApplicationConstants.LoginFeature.SEARCH_PAGE);
                String lastname = result.get(0).getFullName();
                Cookie cookie = new Cookie(username, password);
                cookie.setMaxAge(60); //60s
                response.addCookie(cookie);
                HttpSession session = request.getSession();
                session.setAttribute("USERNAME", username);
                session.setAttribute("LASTNAME", lastname);
                
                
            }
            
            //practice
            
            //start
            
//            RegistrationDAO dao = new RegistrationDAO();
//            dao.checkLogin(username, password);
//            List<RegistrationDTO> result = dao.getAccounts();
//            
//            if (result != null) {
//                url = siteMaps.getProperty(MyApplicationConstants.LoginFeature.SEARCH_PAGE);
//                HttpSession session = request.getSession();
//                session.setAttribute("LASTNAME", result.get(0).getFullName());
//                Cookie cookies = new Cookie(username, password);
//                cookies.setMaxAge(60);
//                response.addCookie(cookies);
//            }
            
            //end

        } //end oif username and password are matched!!!
        //end if user clicked login button
        catch (NamingException ex) {
            log("LoginServlet _ Naming" + ex.getMessage());
        } catch (SQLException ex) {
            log("LoginServlet _ SQL" + ex.getMessage());
        } finally {
//            response.sendRedirect(url);
//            out.close();
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
