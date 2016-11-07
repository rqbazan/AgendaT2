<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Registrate</title>
</head>
<body>
<div align="center">
    <form method="POST" action="registeruser">
        <table border="1">
            <tr>
                <td><label>Usuario</label></td>
                <td><input type="text" name="username"/></td>
            </tr>
            <tr>
                <td><label>Contraseña</label></td>
                <td><input type="password" name="password"/></td>
            </tr>
            <tr>
                <td><label>Confirmar contraseña</label></td>
                <td><input type="password" name="confirmPassword"/></td>
            </tr>
            <tr>
                <td><label>Nombres</label></td>
                <td><input type="text" name="name"/></td>
            </tr>
            <tr>
                <td><label>Apellidos</label></td>
                <td><input type="text" name="lastName"/></td>
            </tr>
            <tr>
                <td><label>Celular</label></td>
                <td><input type="text" name="cellphoneNumber"/></td>
            </tr>
            <tr>
                <td><label>Correo-e</label></td>
                <td><input type="text" name="email"/></td>
            </tr>
            <tr>
                <td><label>Sexo</label></td>
                <td>
                    <p>
                        M <input type="radio" name="sex" value="male" checked/>
                    </p>
                    <p>
                        F <input type="radio" name="sex" value="female"/>
                    </p>
                </td>
            </tr>
            <tr>
                <td><label>DNI</label></td>
                <td><input type="text" name="dni"/></td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="Guardar" name="btnGuardar"  >
                </td>
            </tr>
        </table>
        ${error}
    </form>
</div>
</body>
</html>
