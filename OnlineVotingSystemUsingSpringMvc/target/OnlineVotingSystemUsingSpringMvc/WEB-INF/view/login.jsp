<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
<title>Login Page</title>
</head>
<body>
    <h2>Login</h2>
    <form:form action="login" method="post">
        <table>
            <tr>
                <td>UserEmail:</td>
                <td><form:input path="email" /></td>
            </tr>
            <tr>
                <td>Password:</td>
                <td><form:password path="password" /></td>
            </tr>
            <tr>
                <td colspan="2"><input type="submit" value="Login" /></td>
            </tr>
        </table>
    </form:form>
    <c:if test="${not empty message}">
        <font color="red">${message}</font>
    </c:if>
</body>
</html>
