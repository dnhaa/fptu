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
import java.util.List;
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
@WebServlet(name = "SearchLastnameServlet", urlPatterns = {"/SearchLastnameServlet"})
public class SearchLastnameServlet extends HttpServlet {
//    private final String SEARCH_PAGE = "search.html";
//    private final String SEARCH_RESULT_PAGE = "search.jsp";
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
        String errors = request.getParameter("createError");
        String username = request.getParameter("usernameError");
        String searchValue = request.getParameter("txtSearchValue");
//        String url = SEARCH_PAGE;
        String url = siteMaps.getProperty(MyApplicationConstants.SearchLastnameFeature.SEARCH_PAGE_HTML);
        try {
            if (searchValue.trim().length() > 0) {
                //call DAO
                RegistrationDAO dao = new RegistrationDAO();
                
                dao.searchLastName(searchValue);
                List<RegistrationDTO> result = dao.getAccounts();
                
                request.setAttribute("CREATEERRORS", errors);
                request.setAttribute("USERNAME_ERROR", username);
                request.setAttribute("SEARCHRESULT", result);
                url = siteMaps.getProperty(MyApplicationConstants.SearchLastnameFeature.SEARCH_PAGE);
                
            } //end search Value had existed value
        } catch (SQLException ex){
            log("SearchLastnameServlet _ SQL" + ex.getMessage());
        } catch (NamingException ex) {
            log("SearchLastnameServlet _ Naming" + ex.getMessage());
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
