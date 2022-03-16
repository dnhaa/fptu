/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hadn.servlet;

import hadn.cart.CartObject;
import hadn.product.ProductSelectError;
import hadn.utils.MyApplicationConstants;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;
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
@WebServlet(name = "AddItemToCartServlet", urlPatterns = {"/AddItemToCartServlet"})
public class AddItemToCartServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    //private final String SHOPPING_PAGE = "shopping.jsp";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ServletContext context = this.getServletContext();
        Properties siteMaps = (Properties) context.getAttribute("SITEMAPS");
        response.setContentType("text/html;charset=UTF-8");
        String url = siteMaps.getProperty(MyApplicationConstants.AddItemToCartFeature.SHOPPING_PAGE);
        ProductSelectError pse = new ProductSelectError();
        try {
//            HttpSession session = request.getSession();
//            CartObject cart = (CartObject) session.getAttribute("CART");
//            if (cart == null) {
//                cart = new CartObject();
//            }
//            String item = request.getParameter("txtBook");
//            String status = request.getParameter("chkStatus");
//            //System.out.println(status);
//            if (item != null) {
//                if (status.equals("true")) {
//                    cart.addItemToCart(item);
//                }
//                else { 
//                    pse.setStatusError("This book is not for sale");
//                    request.setAttribute("ERROR_ADD_ITEM", pse);
//                }
//            } 
//            session.setAttribute("CART", cart);

            HttpSession session = request.getSession();
            CartObject cart = (CartObject) session.getAttribute("CART");
            if (cart == null) {
                cart = new CartObject();
            }
            String item = request.getParameter("txtBook");
            if (item != null) {
                String status = request.getParameter("chkStatus");
                if (status.equals("true")) {
                    cart.addItemToCart(item);
                }
                else {
                    pse.setStatusError("The item is not for sale.");
                    request.setAttribute("ERROR_ADD_ITEM", pse);
                }
            }
            session.setAttribute("CART", cart);
        } finally {
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
