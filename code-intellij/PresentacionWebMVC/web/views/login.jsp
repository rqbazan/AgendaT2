<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="frm" %>
<html>
<head>
    <title>Login - AgendaT2</title>
</head>
<body>

<div align="center">
    <frm:form method="POST" action="home">
        <table border="1">
            <tr>
                <td><frm:label path="username" name="">Username</frm:label></td>
                <td><frm:input path="username"></frm:input></td>
            </tr>
            <tr>
                <td><frm:label path="password" name="">Password</frm:label></td>
                <td><frm:input path="password" type="password"></frm:input></td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="Ingresar" name="btnIngresar"  >
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <a href="${pageContext.request.contextPath}/registeruser">
                        Registrate
                    </a>
                </td>
            </tr>
        </table>
        ${error}
    </frm:form>
</div>
</body>
</html>
