<%@page import="domain.Quiz"%>
<%@page import="java.util.List"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    List<Quiz> quizes = (List<Quiz>)session.getAttribute("quizesPorUser"); 
    if (quizes.size()>0) {
%>
<h5>Mis quizzes</h5>
<%
    }
%>
<ul class="list-group list-group-flush">
    <c:forEach var="quiz" items="${quizesPorUser}" varStatus="status" >
        <li class="list-group-item">${quiz.name}<a href="${pageContext.request.contextPath}/question?idquiz=${quiz.idQuiz}"> [Click para editar]</a>
            <a href="${pageContext.request.contextPath}/quiz?accion=resultados&idquiz=${quiz.idQuiz}" style="color:rgb(2, 165, 2);"> [Click para ver info]</a></li>
        </c:forEach>
</ul>
