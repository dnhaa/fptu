<%-- 
    Document   : showCart
    Created on : Feb 24, 2022, 8:10:23 AM
    Author     : ACER
--%>

<%--<%@page import="java.util.Map"%>
<%@page import="hadn.cart.CartObject"%>--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book Store</title>
    </head>
    <body>
        <h1>Your Cart Includes</h1>
<%--        
        <c:set var="cart" value="${sessionScope.CART}" />
        <c:if test="${not empty cart}">
            <c:set var="items" value="${cart.items}"/>
            <c:if test="${not empty items}">
                <form action="cartController">
                    <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Title</th>
                            <th>Quantity</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="item" items="${items.keySet()}" varStatus="counter">
                            
                        
                            <tr>
                                <td>
                                    ${counter.count}
                                </td>
                                <td>
                                    ${item}
                                </td>
                                <td>
                                    ${items.get(item)}
                                </td>
                                <td>
                                    <input type="checkbox" name="chkItem" value="${item}" />
                                </td>
                            </tr>
                            
                            
                            
                        </c:forEach>
                        <tr>
                            <td colspan="3">
                                <input type="submit" value="Add More Books to Your Cart" name="btAction" />
                            </td>
                            <td>
                                <input type="submit" value="Remove Your Choice Items" name="btAction" />
                            </td>
                        </tr>
                    </tbody>
                </table>
                </form>
            </c:if>
            <c:if test="${empty items}">
                <h2>No Cart!!</h2>
            </c:if>
        </c:if>
        <c:if test="${empty cart}">
            <h2>No Cart!!</h2>
        </c:if>
            --%>
            
        
            
        <c:set var="cart" value="${sessionScope.CART}" />
        <c:if test="${not empty cart}">
            <c:set var="items" value="${cart.items}"/>
            <c:if test="${not empty items}">
                <form action="cartController">
                
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Title</th>
                            <th>Quantity</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="item" items="${items.keySet()}" varStatus="counter">
                            <tr>
                                <td>
                                    ${counter.count}
                                </td>
                                <td>
                                    ${item}
                                </td>
                                <td>
                                    ${items.get(item)}
                                </td>
                                <td>
                                    <input type="checkbox" name="chkItem" value="${item}" />
                                </td>
                            </tr>
                            
                        </c:forEach>
                        <tr>
                            <td colspan="3">
                                <input type="submit" value="Add More Books to Your Cart" name="btAction"/>
                            </td>
                            <td>
                                <input type="submit" value="Remove Your Choice Items" name="btAction"/>
                            </td>
                        </tr>
                    </tbody>
                </table>
                </form>
            </c:if>
            <c:if test="${empty items}">
                <h2>
                No cart.
                </h2>
            </c:if>
        </c:if>    
        <c:if test="${empty cart}">
            <h2>
                No cart.
            </h2>
        </c:if>    
            
            
            
            
            
            
            
            
            
        <%--
        <%
            if (session != null) {
                CartObject cart = (CartObject)session.getAttribute("CART");
                if (cart != null) {
                    Map<String, Integer> items = cart.getItems();
                    if (items != null) {
                        %>
                        
                        <form action="DispatchServlet">
                        
                        <table border="1">
                            <thead>
                                <tr>
                                    <th>No.</th>
                                    <th>Title</th>
                                    <th>Quantity</th>
                                    <th>Action</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    int count = 0;
                                    for (String item : items.keySet()) {
                                        %>
                                        <tr>
                                            <td>
                                                <%= ++count %>
                                            </td>
                                            <td>
                                                <%= item %>
                                            </td>
                                            <td>
                                                <%= items.get(item) %>
                                            </td>
                                            <td>
                                                <input type="checkbox" name="chkItem" value="<%= item %>" />
                                            </td>
                                        </tr>
                                        <%
                                    }
                                %>
                                <tr>
                                    <td colspan="3">
                                        <input type="submit" value="Add More Books to Your Cart" name="btAction" />
                                    </td>
                                    <td>
                                        <input type="submit" value="Remove Your Choice Items" name="btAction" />
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                        </form>
                        <%
                    }  else {
                    %>
                    <h2>No Cart!!</h2>
                    <%
                    }
                } else {
                    %>
                    <h2>No Cart!!</h2>
                    <%
                }
            }
        %>--%>
    </body>
</html>
