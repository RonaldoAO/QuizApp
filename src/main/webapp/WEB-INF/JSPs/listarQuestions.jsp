<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="accordion">
    <c:forEach var="question" items="${questions}" varStatus="status" >
        <div class="card">
            <div class="card-header">

                <a class="collapsed card-link" data-toggle="collapse" href="#a${status.count}"
                   style="color:rgb(0, 175, 0);">
                    ${question.pregunta}
                </a>
            </div>
            <div id="a${status.count}" class="collapse" data-parent="#accordion">
                <div class="card-body">
                    Respuesta: ${question.respuesta}<br/>
                    Reactivo 2: ${question.reactivo1}<br/>
                    Reactivo 3: ${question.reactivo2}
                </div>
                <a href="${pageContext.request.contextPath}/question?accion=editar&idCliente=${question.idQuestion}" class="btn btn-outline-success btn-sm" role="button" style="margin:0px 0px 15px 15px; ">Editar</a>
                <a href="${pageContext.request.contextPath}/question?accion=eliminar&idCliente=${question.idQuestion}" class="btn btn-outline-success btn-sm" role="button" style="margin:0px 0px 15px 15px; ">Eliminar</a>
            </div>
        </div>
    </c:forEach>

</div>

<!-- cliente MODAL -->
<jsp:include page="/WEB-INF/JSPs/agregarQuestion.jsp"/>