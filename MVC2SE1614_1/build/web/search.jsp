<%-- 
    Document   : search
    Created on : Feb 11, 2022, 11:24:15 AM
    Author     : ACER
--%>

<%--<%@page import="hadn.registration.RegistrationDTO"%>
<%@page import="java.util.List"%>--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Search</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>

        <font color="red">
        Welcome, ${sessionScope.LASTNAME}
        </font>
        <h1>Search Page</h1>

        <%--        practice--%>
        <%--
                <form action="searchLastnameController">
                    Search Value <input type="text" name="txtSearchValue" value="${param.txtSearchValue}" /><br/>
                    <input type="submit" value="Search" name="btAction" />
                </form><br/>
                <c:set var="searchValue" value="${param.txtSearchValue}"/>
                <c:if test="${not empty searchValue}">
                    <c:set var="result" value="${requestScope.SEARCHRESULT}"/>
                    <c:if test="${not empty result}">
                        <table border="1">
                            <thead>
                                <tr>
                                    <th>No.</th>
                                    <th>Username</th>
                                    <th>Password</th>
                                    <th>Full Name</th>
                                    <th>Role</th>
                                    <th>Delete</th>
                                    <th>Update</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="dto" items="${result}" varStatus="counter">
                                <form action="updateAccountController">
                                    <c:set var="errors" value="${requestScope.CREATEERRORS}" />
                                    <c:set var="usernamesPasswordError" value="${requestScope.USERNAME_ERROR}" />
                                    <tr>
                                        <td>
                                            ${counter.count}
                                        </td>
                                        <td>
                                            ${dto.username}
                                            <input type="hidden" name="txtUsername" value="${dto.username}" />
                                        </td>
                                        <td>
                                            
                                            <input type="text" name="txtPassword" value="${dto.password}" />
                                            <c:if test="${not empty errors and dto.username == usernamesPasswordError}">
                                                <br/><font color="red">
                                                    ${errors}
                                                </font><br/>
                                            </c:if>
                                        </td>
                                        <td>
                                            ${dto.fullName}
                                        </td>
                                        <td>
                                            <input type="checkbox" name="chkAdmin" value="ON"
                                                <c:if test="${dto.role}">
                                                    checked="checked"
                                                </c:if>
                                            />
                                            
                                        </td>
                                        <td>
                                            <c:url var="deleteAccount" value="deleteAccountController">
                                                <c:param name="pk" value="${dto.username}" />
                                                <c:param name="lastSearchValue" value="${searchValue}" />
                                            </c:url>
                                            <a href="${deleteAccount}">Delete</a>
                                        </td>
                                        <td>
                                            <input type="submit" value="Update" name="btAction" />
                                            <input type="hidden" name="lastSearchValue" value="${searchValue}" />
                                        </td>
                                    </tr>
                                </form>
                                </c:forEach>
                            </tbody>
                        </table>

            </c:if>
            <c:if test="${empty result}">
                <h2>
                    No record match.
                </h2>
            </c:if>
        </c:if>
        --%>

        <%--        practice--%>

        <form action="searchLastnameController">
            <input type="text" name="txtSearchValue" value="${param.txtSearchValue}" />
            <input type="submit" value="Search" />
        </form>
        <c:set var="searchValue" value="${param.txtSearchValue}" />
        <c:if test="${not empty searchValue}">
            <c:set var="results" value="${requestScope.SEARCHRESULT}" />
            <c:if test="${not empty results}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Username</th>
                            <th>Password</th>
                            <th>Full name</th>
                            <th>Admin</th>
                            <th>Delete</th>
                            <th>Update</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="dto" items="${results}" varStatus="counter">
                        <form action="updateAccountController" method="POST">

                            <tr>
                                <td>
                                    ${counter.count}
                                </td>
                                <td>
                                    ${dto.username}
                                    <input type="hidden" name="txtUsername" value="${dto.username}" />
                                </td>
                                <td>
                                    <input type="text" name="txtPassword" value="${dto.password}" />
                                    <c:if test="${not empty requestScope.CREATEERRORS and dto.username eq requestScope.USERNAME_ERROR}">
                                        <br/><font color="red">
                                            ${requestScope.CREATEERRORS}
                                        </font> 
                                    </c:if>
                                </td>
                                <td>
                                    ${dto.fullName}
                                </td>
                                <td>
                                    <input type="checkbox" name="chkAdmin" value="ON"
                                           <c:if test="${dto.role}">
                                               checked="checked"
                                           </c:if>
                                           />
                                </td>
                                <td>
                                    <c:url var="deleteAccount" value="deleteAccountController">
                                        <c:param name="pk" value="${dto.username}" />
                                        <c:param name="lastSearchValue" value="${searchValue}" />
                                    </c:url>
                                    <a href="${deleteAccount}">Delete</a>
                                </td>
                                <td>
                                    <input type="submit" value="Update" />
                                    <input type="hidden" name="lastSearchValue" value="${searchValue}" />
                                </td>
                            </tr>
                        </form> 
                    </c:forEach>
                </tbody>
            </table>

        </c:if>
        <c:if test="${empty results}">
            <h2>
                No Record Found.
            </h2>
        </c:if>
    </c:if>




    <%--
    <%
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            Cookie lastCookie = cookies[cookies.length - 1];
            String username = lastCookie.getName();
    %>
    <font color="red">
    Welcome, <%= username%>
    </font>
    <%
        } //end cookies
    %>

        <h1>Search Page</h1>
        <form action="DispatchServlet">
            Search Value <input type="text" name="txtSearchValue" value="<%= request.getParameter("txtSearchValue")%>" /><br/>
            <input type="submit" value="Search" name="btAction" />
        </form><br/>

        <%
            String searchValue = request.getParameter("txtSearchValue");
            if (searchValue != null) {
                List<RegistrationDTO> result
                        = (List<RegistrationDTO>) request.getAttribute("SEARCHRESULT");

                if (result != null) {
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>No.</th>
                    <th>Username</th>
                    <th>Password</th>
                    <th>Full name</th>
                    <th>Role</th>
                    <th>Delete</th>
                    <th>Update</th>
                </tr>
            </thead>
            <tbody>
                <%
                    int count = 0;
                    for (RegistrationDTO dto : result) {
                        String urlRewriting = "DispatchServlet"
                                + "?btAction=delete"
                                + "&pk=" + dto.getUsername()
                                + "&lastSearchValue=" + searchValue;


                %>
            <form action="DispatchServlet">

                <tr>

                    <td>
                        <%= ++count%>
                    </td>
                    <td>
                        <%= dto.getUsername()%>
                        <input type="hidden" name="txtUsername" value="<%= dto.getUsername()%>" />
                    </td>
                    <td>
                        <input type="text" name="txtPassword" value="<%= dto.getPassword()%>" />
                    </td>

                    <td>
                        <%= dto.getFullName()%>
                    </td>
                    <td>
                        <input type="checkbox" name="chkAdmin" value="ON"
                               <%
                                   if (dto.isRole()) {
                               %>
                               checked="checked"
                               <%
                                   }
                               %>

                               />
                    </td>
                    <td>
                        <a href="<%= urlRewriting%>">Delete</a>
                    </td>
                    <td>
                        <input type="hidden" name="lastSearchValue" value="<%= searchValue%>" />
                        <input type="submit" value="Update" name="btAction" />
                    </td>
                </tr>
            </form> 
            <%
                } // end for transvering result
            %>
        </tbody>
    </table>


    <%
    } else {
    %>
    <h2>
        No record is matched.
    </h2>
    <%
            }
        } //search Value has value
    %>--%>
    <form action="logoutController" method="POST">
        <input type="submit" value="Logout" name="btAction" />
    </form>

</body>
</html>
