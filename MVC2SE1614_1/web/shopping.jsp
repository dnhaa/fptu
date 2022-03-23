<%-- 
    Document   : shopping
    Created on : Feb 22, 2022, 3:53:24 PM
    Author     : ACER
--%>

<%--<%@page import="hadn.product.ProductDTO"%>
<%@page import="java.util.List"%>--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book Store</title>
    </head>
    <body>
        <h1>Software Book Store</h1>
        <form action="searchBookController">
            <input type="text" name="txtBookName" value="${param.txtBookName}" />
            <input type="submit" value="Search Book" name="btAction" />
        </form>
            <c:set var="errors" value="${requestScope.ERROR_ADD_ITEM}" />
            <c:if test="${not empty errors.statusError}">
                <font color="red">
                    ${errors.statusError}
                </font>
            </c:if>
            <c:set var="txtBookName" value="${param.txtBookName}" />
            
            <c:if test="${not empty txtBookName}">
                
                <c:set var="result" value="${requestScope.SEARCHBOOKRESULT}" />    
                <c:if test="${not empty result}">
                    <table border="1">
                        <thead>
                            <tr>
                                <th>No.</th>
                                <th>Name</th>
                                <th>Quantity</th>
                                <th>Price</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="dto" items="${result}" varStatus="counter">
                                
                                <form action="addItemToCartController">
                            
                                    <tr>
                                        <td>
                                            ${counter.count}
                                        </td>
                                        <td>
                                            ${dto.name}
                                            <input type="hidden" name="txtBook" value="${dto.name}" />
                                        </td>
                                        <td>
                                            ${dto.quantity}
                                        </td>
                                        <td>
                                            ${dto.price}
                                        </td>
                                        <td>
                                            <input type="hidden" name="chkStatus" value="${dto.status}" />
                                            <input type="hidden" name="txtBookName" value="${param.txtBookName}" />
                                            <input type="submit" value="Add More Books to Your Cart" name="btAction" />
                                        </td>
                                    </tr>
                                    
                                </form>    
                            </c:forEach>
                        </tbody>
                    </table>

                </c:if>
                <c:if test="${empty result}">
                    <h1>
                        No book found.
                    </h1>
                </c:if>

                
            
            </c:if>
            <form action="showCartPage">
                <input type="submit" value="View Your Cart" name="btAction" />
            </form>
        <%--<form action="DispatchServlet">
            <input type="text" name="txtBookName" value="<%= request.getParameter("txtBookName")%>" />
            <input type="submit" value="Search Book" name="btAction" />
        </form>
        <%
            String searchBookName = request.getParameter("txtBookName");
            if (searchBookName != null) {
                List<ProductDTO> result = (List<ProductDTO>)request.getAttribute("SEARCHBOOKRESULT");
                if (result != null){
                    %>
                    <table border="1">
                        <thead>
                            <tr>
                                <th>No.</th>
                                <th>Name</th>
                                <th>Quantity</th>
                                <th>Price</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% 
                                int count = 0;
                                for(ProductDTO dto : result) {
                            %>
                                
                                <form action="DispatchServlet">    
                                    <tr>
                                        <td><%= ++count %></td>
                                        <td>
                                            <%= dto.getName() %>
                                            <input type="hidden" name="txtBook" value="<%= dto.getName() %>" />
                                        </td>
                                        <td><%= dto.getQuantity()%></td>
                                        <td><%= dto.getPrice()%></td>
                                        <td>
                                            <input type="submit" value="Add Book to Your Cart" name="btAction" />
                                        </td>
                                    </tr>
                                </form>
                            <%
                                }
                            %>
                        </tbody>
                        
                    </table>

                    <%
                }
            }
        %>--%>
        
    </body>
</html>
