<!DOCTYPE html>
<html lang="en">

    <head>
        <title>Making</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
        <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
        <link rel="stylesheet" href="styles/making.css">
    </head>

    <body>
        <h2>Nombre del Quizz</h2>
        <div class="container" style="margin-top:20px;">

            <jsp:include page="/WEB-INF/JSPs/listarQuestions.jsp"/>  
            
            <div class="container">
                <a href="#" class="btn btn-success" data-toggle="modal" data-target="#agregarQuestionModal">Agregar pregunta</a>
                <a href="index.jsp" class="btn btn-success" role="button">Guardarlo</a>
                
                <a href="${pageContext.request.contextPath}/quiz?accion=eliminar" class="btn btn-outline-success" role="button">Eliminar Quiz</a>
            </div>
        </div>

    </body>

</html>