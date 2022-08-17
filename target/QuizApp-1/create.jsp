<!DOCTYPE html>
<html lang="en">

<head>
    <title>Create</title>
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
        <h2>Crear quiz</h2>
        <h2>${idUsuario}</h2>
        <form action="${pageContext.request.contextPath}/quiz?accion=create" method="post">
            <div class="form-group">
                <label for="name">Inserte un nombre</label>
                <input type="text" class="form-control" id="name"  name="name">
            </div>
            <div class="form-group">
                <label for="email">Inserte una breve descripción:</label>
                <input type="text" class="form-control" id="description"  name="description">
            </div>
            <label for="dificulty">Seleccione la dificultad:</label>
            <select name="dificulty" class="custom-select mb-3">
                <option value="easy">Facil</option>
                <option value="medium">Medio</option>
                <option value="hard">Dificil</option>
            </select>
            <input type="hidden" name="idUsuario" id="status" value="<%= request.getAttribute("idUsuario")%>">
            
            <button type="submit" class="btn btn-success btn-block" style="height:50px;">Crear</button>
            
        </form>
    </div>
    <div class="container" style=" text-align:center; margin-top:20px;">
        <a href="main02.jsp">regresar</a>
    </div>
</body>

</html>