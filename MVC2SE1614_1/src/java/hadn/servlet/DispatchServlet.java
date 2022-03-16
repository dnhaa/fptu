///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package hadn.servlet;
//
//import hadn.utils.MyApplicationConstants;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.Properties;
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletContext;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
///**
// *
// * @author ACER
// */
//@WebServlet(name = "DispatchServlet", urlPatterns = {"/DispatchServlet"})
//public class DispatchServlet extends HttpServlet {
//    //private final String LOGIN_PAGE = "login.html";
////    private final String LOGIN_CONTROLLER = "LoginServlet";
////    private final String SEARCH_LASTNAME_CONTROLLER = "SearchLastnameServlet";
////    private final String DELETE_ACCOUNT_CONTROLLER = "DeleteAccountServlet";
////    private final String COOKIES_CHECK_CONTROLLER = "CookiesCheckServlet";
////    private final String UPDATE_ACCOUNT_CONTROLLER = "UpdateAccountServlet";
////    private final String LOGOUT_CONTROLLER = "LogoutServlet";
////    private final String SEARCH_BOOK_CONTROLLER = "SearchBookServlet";
////    private final String ADD_ITEM_TO_CART_CONTROLLER = "AddItemToCartServlet";
////    private final String SHOW_CART_PAGE = "showCart.jsp";
////    private final String REMOVE_ITEM_FROM_CART_CONTROLLER = "RemoveItemFromCartServlet";
////    private final String CREATE_ACCOUNT_CONTROLLER = "CreateAccountServlet";
//    /**
//     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
//     * methods.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
//        ServletContext context = this.getServletContext();
//        Properties siteMaps = (Properties)context.getAttribute("SITEMAPS");
//        //which button did user click?
//        String button = request.getParameter("btAction");
//        String url = siteMaps.getProperty(MyApplicationConstants.DispatchFeatures.LOGIN_PAGE);  //đầy là nhúng file hả? nhúng file và gọi file...?
//        
//        try {
//            if (button == null){
//                url = siteMaps.getProperty(MyApplicationConstants.CookiesCheckFeature.COOKIES_CHECK_CONTROLLER);;
//            } else if (button.equals("Login")) {
////                url = LOGIN_CONTROLLER;
//                url = siteMaps.getProperty(MyApplicationConstants.LoginFeature.LOGIN_CONTROLLER);
//            } else if (button.equals("Search")) {
////                url = SEARCH_LASTNAME_CONTROLLER;
//                url = siteMaps.getProperty(MyApplicationConstants.SearchLastnameFeature.SEARCH_LASTNAME_CONTROLLER);
//            } else if (button.equals("delete")) {
////                url = DELETE_ACCOUNT_CONTROLLER;
//                url = siteMaps.getProperty(MyApplicationConstants.DeleteAccountFeature.DELETE_ACCOUNT_CONTROLLER);
//            } else if (button.equals("Update")){
////                url = UPDATE_ACCOUNT_CONTROLLER;
//                url = siteMaps.getProperty(MyApplicationConstants.UpdateAccountFeature.UPDATE_ACCOUNT_CONTROLLER);
//            } else if (button.equals("Logout")) {
////                url = LOGOUT_CONTROLLER;
//                url = siteMaps.getProperty(MyApplicationConstants.LogoutFeature.LOGOUT_CONTROLLER);
//            } else if (button.equals("Search Book")){
////                url = SEARCH_BOOK_CONTROLLER;
//                url = siteMaps.getProperty(MyApplicationConstants.SearchBookFeature.SEARCH_BOOK_CONTROLLER);
//            } else if (button.equals("Add Book to Your Cart")) {
////                url = ADD_ITEM_TO_CART_CONTROLLER;
//                url = siteMaps.getProperty(MyApplicationConstants.AddItemToCartFeature.ADD_ITEM_TO_CART_CONTROLLER);
//            } else if (button.equals("View Your Cart")) {
////                url = SHOW_CART_PAGE;
//                url = siteMaps.getProperty(MyApplicationConstants.ShowCartFeature.SHOW_CART_PAGE);
//            } else if (button.equals("Add More Books to Your Cart")) {
////                url = ADD_ITEM_TO_CART_CONTROLLER;
//                url = siteMaps.getProperty(MyApplicationConstants.AddItemToCartFeature.ADD_ITEM_TO_CART_CONTROLLER);
//            } else if (button.equals("Remove Your Choice Items")) {
////                url = REMOVE_ITEM_FROM_CART_CONTROLLER;
//                url = siteMaps.getProperty(MyApplicationConstants.RemoveItemFromCartFeature.REMOVE_ITEM_FROM_CART_CONTROLLER);
//            } else if (button.equals("Create New Account")) {
//                url = siteMaps.getProperty(MyApplicationConstants.CreateAccountFeature.CREATE_ACCOUNT_CONTROLLER);
//            }
//        } finally {
//            RequestDispatcher rd = request.getRequestDispatcher(url);
//            rd.forward(request, response);
//        }
//    }
//
//    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
//    /**
//     * Handles the HTTP <code>GET</code> method.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        processRequest(request, response);
//    }
//
//    /**
//     * Handles the HTTP <code>POST</code> method.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        processRequest(request, response);
//    }
//
//    /**
//     * Returns a short description of the servlet.
//     *
//     * @return a String containing servlet description
//     */
//    @Override
//    public String getServletInfo() {
//        return "Short description";
//    }// </editor-fold>
//
//}
