<%@page import="java.lang.Object"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="domain.Calificacion" %>
<%@page import="domain.Usuario" %>
<%@page import="datos.UsuarioDaoJDBC" %>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<%
    List<Calificacion> calificaciones = (List<Calificacion>)request.getAttribute("calificaciones");
    int contador = 0;
    %>
<html lang="en">
    <head>
        <title>Bootstrap Example</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
        <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
    </head>
    <body>

        <div class="container">
            <h2>Nombre del Question</h2>
            <p>Resultados del question:</p>            
            <table class="table">
                <thead>
                    <tr>
                        <th>Name</th>
                        <th>Email</th>
                        <th>Fecha</th>
                        <th>Resultado</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="calificacion" items="${calificaciones}" varStatus="status" >
                        <tr>
                            <td><%
                                Usuario usuario = new UsuarioDaoJDBC().encontrar(new Usuario(calificaciones.get(contador).getIdUser()));
                                out.print(usuario.getNombre());
                                contador++;
                                %></td>
                            <td><%
                                out.print(usuario.getEmail());
                                %></td>
                            <td>${calificacion.fecha}</td>
                            <td>${calificacion.calificacion}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <a class="btn btn btn-success btn-block" href="main02.jsp" role="button">Regresar</a>
        </div>

    </body>
</html>
