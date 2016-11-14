<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="frm" %>
<html>
<head>
    <title>Registrate</title>
    <link href="<c:url value="/resources/theme1/css/bootstrap.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/theme1/navbarstyle.css"/>" rel="stylesheet">
    <style type="text/css">
        .col-centered{
            float: none;
            margin: 0 auto;
        }
        body{
            margin-top: 20px;
        }
    </style>
</head>
<body>

    <div class="pos-f-t">
        <nav class="navbar navbar-dark bg-inverse navbar-static-top bg-faded">
            <div class="container">
                <button class="navbar-toggler hidden-sm-up" type="button" data-toggle="collapse" data-target="#exCollapsingNavbar2" aria-expanded="false" aria-controls="exCollapsingNavbar2" aria-label="Toggle navigation"></button>
                <div class="collapse navbar-toggleable-xs" id="exCollapsingNavbar2">
                    <a class="navbar-brand" href="${pageContext.request.contextPath}/">AgendaT2</a>
                </div>
            </div>
        </nav>
    </div>

    <div class="container">
            <c:if test="${not empty error}">
                <div class="row">
                    <div class="col-md-6 col-sm-6 col-xs-12 col-centered">
                        <div class="alert alert-danger" role="alert">
                            <strong>Ops!</strong> ${error}
                        </div>
                    </div>
                </div>
            </c:if>
            <div class="row">
                <div class="col-md-6 col-sm-6 col-xs-12 col-centered">
                    <frm:form method="post" action="registeruser">
                        <div class="form-group ">
                            <frm:input class="form-control" id="username" path="username" placeholder="Usuario" type="text" required="required"/>
                        </div>
                        <div class="form-group ">
                            <frm:input class="form-control" id="password" path="password" placeholder="Contraseña" type="password" required="required"/>
                        </div>
                        <div class="form-group ">
                            <frm:input class="form-control" id="name" path="person.name" placeholder="Nombres" type="text" required="required"/>
                        </div>
                        <div class="form-group ">
                            <frm:input class="form-control" id="lastName" path="person.lastName" placeholder="Apellidos" type="text" required="required"/>
                        </div>
                        <div class="form-group ">
                            <frm:input class="form-control" id="cellphoneNumber" path="person.cellphoneNumber" placeholder="Celular" type="text" required="required"/>
                        </div>
                        <div class="form-group ">
                            <frm:input class="form-control" id="email" path="person.email" placeholder="Correo-e" type="text" required="required"/>
                        </div>
                        <div class="form-group ">
                            <frm:input class="form-control" id="dni" path="person.dni" placeholder="DNI" type="text" required="required"/>
                        </div>
                        <div class="form-group ">
                            <label class="control-label ">
                                Sexo
                            </label>
                            <div class="">
                                <div class="radio">
                                    <label class="radio">
                                        <frm:radiobutton path="person.sex" value="M"/>
                                        M
                                    </label>
                                </div>
                                <div class="radio">
                                    <label class="radio">
                                        <frm:radiobutton path="person.sex"  value="F"/>
                                        F
                                    </label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div>
                                <button class="btn btn-primary " name="submit" type="submit">
                                    Registrar
                                </button>
                            </div>
                        </div>
                    </frm:form>
                </div>
            </div>
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
