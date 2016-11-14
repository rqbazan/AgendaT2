<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="frm" %>
<html>
<head>
    <title>Home - AgendaT2</title>
    <link href="<c:url value="/resources/theme1/css/bootstrap.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/theme1/navbarstyle.css"/>" rel="stylesheet">
</head>
<body>

    <div class="pos-f-t">
        <nav class="navbar navbar-dark bg-inverse navbar-static-top bg-faded">
            <div class="container">
                <button class="navbar-toggler hidden-sm-up" type="button" data-toggle="collapse" data-target="#exCollapsingNavbar2" aria-expanded="false" aria-controls="exCollapsingNavbar2" aria-label="Toggle navigation"></button>
                <div class="collapse navbar-toggleable-xs" id="exCollapsingNavbar2">
                    <a class="navbar-brand" href="${pageContext.request.contextPath}/">AgendaT2</a>
                    <ul class="nav navbar-nav">
                        <li class="nav-item dropdown float-xs-right">
                            <a class="nav-link dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                Usuario
                            </a>
                            <div class="dropdown-menu" aria-labelledby="supportedContentDropdown">
                                <a class="dropdown-item" href="${pageContext.request.contextPath}/logout">Log out</a>
                            </div>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
    </div>

    <div class="container">
        <p style="margin-top: 10px">
            <a href="${pageContext.request.contextPath}/registercontact"  class="btn btn-secondary" role="button" aria-pressed="true">
                Agregar un contacto
            </a>
        </p>
        <c:if test="${not empty contacts}">
            <table class="table table-striped">
                <thead class="thead-inverse">
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
        </c:if>
        <c:if test="${empty contacts}">
            <div class="alert alert-info" role="alert">
                <strong>Ops!</strong> Aún no tienes contactos en tu agenda
            </div>
        </c:if>
    </div>

    <footer class="footer">
        <div class="container">
            <span class="text-muted">Elaborado por: Ricardo Alexis Quiroz Bazan (A.K.A. Santiago)</span>
        </div>
    </footer>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js" integrity="sha384-3ceskX3iaEnIogmQchP8opvBy3Mi7Ce34nWjpBIwVTHfGYWQS9jwHDVRnpKKHJg7" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.3.7/js/tether.min.js" integrity="sha384-XTs3FgkjiBgo8qjEjBk0tGmf3wPrWtA6coPfQDfFEY8AnYJwjalXCiosYRBIBZX8" crossorigin="anonymous"></script>
    <script src="<c:url value="/resources/theme1/js/bootstrap.min.js"/>"></script>
</body>
</html>
