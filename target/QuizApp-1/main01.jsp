<%
    if (session.getAttribute("usuario") != null) {
        response.sendRedirect("main02.jsp");
    }
%>

<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css"
              integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link rel="stylesheet" href="styles/div.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:400,700">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <title>QuizApp</title>
    </head>

    <body>

        <input type="hidden" id="status" value="<%= request.getAttribute("status")%>"><!-- comment -->
        
       


        <!-- Content -->
        <div class="container" style="margin-top:30px">
            <div class="row">
                <div class="col-sm-4">
                    <h2>Entra</h2>
                    <h5>Realiza tus propios quizz</h5>
                    <p>Registrate y descubre mas posibilidades</p>



                    <!-- Button trigger modal -->
                    <button type="button" class="btn btn-outline-success btn-block" data-toggle="modal" data-target="#myModal">
                        Comienza
                    </button>

                    <!-- Modal -->
                    <div id="myModal" class="modal fade">
                        <div class="modal-dialog modal-login">
                            <div class="modal-content">
                                <div class="modal-header">				
                                    <h4 class="modal-title">Sign In</h4>
                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                </div>
                                <div class="modal-body">
                                    <form action="${pageContext.request.contextPath}/user?accion=login" method="post">
                                        <div class="form-group">
                                            <div class="input-group">
                                                <span class="input-group-addon"><i class="fa fa-user"></i></span>
                                                <input type="text" class="form-control" name="username"  placeholder="Username" required="required">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="input-group">
                                                <span class="input-group-addon"><i class="fa fa-lock"></i></span>
                                                <input type="text" class="form-control" name="password" placeholder="Password" required="required">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <button type="submit" class="btn btn-primary btn-block btn-lg">Sign In</button>
                                        </div>
                                        <p class="hint-text"><a href="#">Forgot Password?</a></p>
                                    </form>
                                </div>
                                <div class="modal-footer">Don't have an account? <a href="register.jsp">Create one</a></div>
                            </div>
                        </div>
                    </div>
                </div>

                
                                    <jsp:include page="/WEB-INF/JSPs/listarQuizes.jsp"/>                                
        <!-- Footer -->



        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
                integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js"
                integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js"
                integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>
        
        
        <!-- JS -->
        <script src="vendor/jquery/jquery.min.js"></script>
        <script src="js/main.js"></script>s
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
        <link rel="stylesheet" href="alert/dist/sweetalert.css"><!-- comment -->
        <script type="text/javascript">
            var status = document.getElementById("status").value;
            if (status == "failure") {
                swal("Oh no", "Usuario o contraseña incorrectas", "error");
            }
        </script>
    </body>
</html>
