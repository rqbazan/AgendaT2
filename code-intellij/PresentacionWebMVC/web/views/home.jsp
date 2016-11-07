<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="frm" %>
<html>
<head>
    <title>Home</title>
</head>
<body>
    <h3>Lista de contactos</h3>
    <p>
        <a href="${pageContext.request.contextPath}/registercontact">
            Agregar un contacto
        </a>
    </p>
    <p>
        <a href="${pageContext.request.contextPath}/logout">
            Salir
        </a>
    </p>
    <table border="1" style="width: 100%">
        <thead>
        <tr>
            <th>Nombres</th>
            <th>Apellidos</th>
            <th>Celular</th>
            <th>Correo-e</th>
            <th>Sexo</th>
            <th>DNI</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${contacts}" var="contact">
            <tr>
                <td>${contact.name}</td>
                <td>${contact.lastName}</td>
                <td>${contact.cellphoneNumber}</td>
                <td>${contact.email}</td>
                <td>${contact.sex}</td>
                <td>${contact.dni}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</body>
</html>
