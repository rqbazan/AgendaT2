<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="frm" %>
<html>
<head>
    <title>Login - AgendaT2</title>
    <link href="<c:url value="/resources/theme1/css/bootstrap.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/theme1/loginstyle.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/theme1/navbarstyle.css"/>" rel="stylesheet">
    <style type="text/css">
        .col-centered{
            float: none;
            margin: 0 auto;
        }
        .nav-tab {
            text-align: center;
        }
    </style>
</head>
<body>

    <div class="pos-f-t">
        <nav class="navbar navbar-dark bg-inverse navbar-static-top bg-faded">
            <div class="container">
                <button class="navbar-toggler hidden-sm-up" type="button" data-toggle="collapse" data-target="#exCollapsingNavbar2" aria-expanded="false" aria-controls="exCollapsingNavbar2" aria-label="Toggle navigation"></button>
                <div class="collapse navbar-toggleable-xs" id="exCollapsingNavbar2">
                    <a class="navbar-brand" href="/">AgendaT2</a>
                </div>
            </div>
        </nav>
    </div>

    <div class="container">
        <div class="row">
            <div class="col-sm-6 col-md-4 col-md-offset-4 col-centered">
                <div class="account-wall">
                    <img class="profile-img" src="<c:url value="/resources/theme1/images/photo_not_user.png"/>" alt="">
                    <frm:form class="form-signin" action="home">
                        <frm:input path="username" type="text" class="form-control" placeholder="Usuario" required="required"/>
                        <frm:input path="password" type="password" class="form-control" placeholder="Contraseña" required="required"/>
                        <button class="btn btn-lg btn-primary btn-block" type="submit">Ingresar</button>
                    </frm:form>
                </div>
                <div class="nav-tab">
                    <a href="${pageContext.request.contextPath}/registeruser" class="text-center new-account">Registrate</a>
                </div>
            </div>
        </div>
        </br>
        <c:if test="${not empty error}">
            <div class="row">
                <div class="col-sm-6 col-md-4 col-md-offset-4 col-centered">
                    <div class="alert alert-danger" role="alert">
                        <strong>Ops!</strong> ${error}
                    </div>
                </div>
            </div>
        </c:if>
    </div>

    <footer class="footer">
        <div class="container">
            <span class="text-muted">Elaborado por: Ricardo Alexis Quiroz Bazan (A.K.A. Santiago)</span>
        </div>
    </footer>

    <script src="https://ajax.urugoogleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js" integrity="sha384-3ceskX3iaEnIogmQchP8opvBy3Mi7Ce34nWjpBIwVTHfGYWQS9jwHDVRnpKKHJg7" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.3.7/js/tether.min.js" integrity="sha384-XTs3FgkjiBgo8qjEjBk0tGmf3wPrWtA6coPfQDfFEY8AnYJwjalXCiosYRBIBZX8" crossorigin="anonymous"></script>
    <script src="<c:url value="/resources/theme1/js/bootstrap.min.js"/>"></script>
</body>
</html>