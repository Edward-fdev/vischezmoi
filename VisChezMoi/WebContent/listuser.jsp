<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Show All Users</title>
</head>
<body>
    <table border=1>
        <thead>
            <tr>
                <th>>Pseudo</th>
                <th>Login</th>
                <th>Date d'inscrpition</th>
                <th colspan=2>Action</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${users}" var="user">
                <tr>
                    <td><c:out value="${user.pseudo}" /></td>
                    <td><c:out value="${user.login}" /></td>
                    <td><fmt:formatDate pattern="dd MMM,yyyy" value="${user.dateinscription}" /></td>
                    <td><a href="UserServlet?action=edit&userId=<c:out value="${user.pseudo}"/>">Update</a></td>
                    <td><a href="UserServlet?action=delete&userId=<c:out value="${user.pseudo}"/>">Delete</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <p><a href="UserServlet?action=insert">Add User</a></p>
</body>
</html>