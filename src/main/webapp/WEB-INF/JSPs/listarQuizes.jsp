<%@page import="domain.Usuario"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    Usuario usuario = (Usuario) session.getAttribute("usuario");
%>
<div class="col-sm-8">

    <h2>Quizzes</h2>

    <div class="dropdown">
        <button type="button" class="btn btn-success dropdown-toggle" data-toggle="dropdown">
            Ordenar por..
        </button>
        <div class="dropdown-menu">
            <a class="dropdown-item" href="#">Nombre</a>
            <a class="dropdown-item" href="#">Dificultad</a>
            <a class="dropdown-item " href="#">Duracion</a>

        </div>
    </div>

    <div class="card-deck">
        <c:forEach var="quiz" items="${quizes}" varStatus="status" >
            <div class="card" style="min-width:200px;  margin: 15px;">
                <div class="card-body">
                    <h5 class="card-title">${quiz.name}</h5>
                    <p class="card-text">${quiz.description}
                    </p>
                    <%
                        if (usuario != null) {
                    %>
                    <a href="${pageContext.request.contextPath}/quiz?accion=play&idQuiz=${quiz.idQuiz}" class="btn btn-success btn-sm" role="button">Empezar</a>
                    <%
                    } else {
                    %>
                    <button type="button" class="btn btn-success btn-sm" data-toggle="modal" data-target="#myModal">
                        Empezar
                    </button>
                    <%
                        }
                    %>
                    <!-- Mandamos el idQuiz  -->
                </div>
                <div class="card-footer">
                    <small class="text-muted">Dificultad: ${quiz.difficulty}</small><br />
                    <small class="text-muted">Preguntas: ${quiz.noDePreguntas}</small><br />
                    <small class="text-muted">Autor: ${quiz.nombreUsuario}</small>
                </div>
            </div> 
        </c:forEach>
    </div>
</div>

</div>
</div>
<!-- End Content -->
