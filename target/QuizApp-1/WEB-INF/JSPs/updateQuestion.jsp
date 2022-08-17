<!DOCTYPE html>
<html lang="en">

<head>
    <title>Bootstrap Example</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" href="styles/create.css">
</head>

<body>

    <div class="container">
        <h2>Update Question</h2>
        <form action="${pageContext.request.contextPath}/question?accion=modificar&idQuestion=${question.idQuestion}" method="POST">
            <div class="form-group">
                <label for="apellido">Pregunta</label>
                <textarea class="form-control" id="exampleFormControlTextarea1" rows="3"  name="pregunta">${question.pregunta}</textarea>
            </div>
            <div class="form-group">
                <label for="apellido">Respuesta</label>
                <input type="text" class="form-control" name="respuesta" value="${question.respuesta}" required>
            </div>
            <div class="form-group">
                <label for="apellido">Reactivo 2</label>
                <input type="text" class="form-control" name="reactivo2" value="${question.reactivo1}" required>
            </div>
            <div class=" form-group">
                <label for="apellido">Reactivo 3</label>
                <input type="text" class="form-control" name="reactivo3" value="${question.reactivo2}" required>
            </div>
            <div class="row">
                <div class="col-sm-6">
                    <button type="submit" class="btn btn-success btn-block">Guardar</button>
                </div>
            </div>
           
            <div class="row row-cols-1" style="text-align:center;">
                <div class="col" style="margin:50px 0;"><a href="${pageContext.request.contextPath}/question" style="color:rgb(0, 176, 0);">Atras</a></div>

              </div>
        </form>
    </div>

</body>

</html>