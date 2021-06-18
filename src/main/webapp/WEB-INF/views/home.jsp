<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Agenda de Usuario</title>
    </head>
    <body>
        <div align="center">
            <h1>Usuario List</h1>
            <h3><a href="/newUsuario">New Usuario</a></h3>
            <table border="1">
                <th>No</th>
                <th>Nome</th>
                <th>Email</th>
                <th>Senha</th>
                <th>Telefone</th>
                <th>Action</th>
                 
                <c:forEach var="usuario" items="${listUsuario}" varStatus="status">
                <tr>
                    <td>${status.index + 1}</td>
                    <td>${usuario.name}</td>
                    <td>${usuario.email}</td>
                    <td>${usuario.senha}</td>
                    <td>${usuario.telefone}</td>
                    <td>
                        <a href="/editUsuario?id=${usuario.id}">Edit</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="/deleteUsuario?id=${usuario.id}">Delete</a>
                    </td>
                             
                </tr>
                </c:forEach>             
            </table>
        </div>
    </body>
</html>