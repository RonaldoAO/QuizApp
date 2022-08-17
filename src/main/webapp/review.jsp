<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Test</title>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:400,700">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
        <style>
            body {
                color: #999;
                background: #f5f5f5;
                font-family: 'Roboto', sans-serif;

            }

            .form-control,
            .form-control:focus,
            .input-group-addon {
                border-color: #e1e1e1;
                border-radius: 0;
            }

            .signup-form {
                width: 700px;
                margin: 0 auto;
                padding: 30px 0;
            }

            @media (max-width:700px) {
                .signup-form{
                    width: 90%;
                }
            }
            .signup-form h2 {
                color: #636363;
                margin: 0 0 15px;
                text-align: center;
            }

            .signup-form .lead {
                font-size: 14px;
                margin-bottom: 30px;
                text-align: center;
            }

            .signup-form form {
                border-radius: 1px;
                margin-bottom: 15px;
                background: #fff;
                border: 1px solid #f3f3f3;
                box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
                padding: 30px;
            }

            .signup-form .form-group {
                margin-bottom: 20px;
            }

            .signup-form label {
                font-weight: normal;
                font-size: 13px;
            }

            .signup-form .form-control {
                min-height: 38px;
                box-shadow: none !important;
                border-width: 0 0 1px 0;
            }

            .signup-form .input-group-addon {
                max-width: 42px;
                text-align: center;
                background: none;
                border-bottom: 1px solid #e1e1e1;
                padding-left: 5px;
            }

            .signup-form .btn,
            .signup-form .btn:active {
                font-size: 16px;
                font-weight: bold;
                background: #19aa8d !important;
                border-radius: 3px;
                border: none;
                min-width: 140px;
            }

            .signup-form .btn:hover,
            .signup-form .btn:focus {
                background: #179b81 !important;
            }

            .signup-form a {
                color: #19aa8d;
                text-decoration: none;
            }

            .signup-form .a:hover {
                text-decoration: underline;
            }

            .signup-form .fa {
                font-size: 21px;
                position: relative;
                top: 8px;
            }

            .signup-form .fa-paper-plane {
                font-size: 17px;
            }

            .signup-form .fa-check {
                color: #fff;
                left: 9px;
                top: 18px;
                font-size: 7px;
                position: absolute;
            }
            .bar{
                background-color: #19aa8d !important;
            }
        </style>
    </head>

    <body>
        <div class="progress" style="height:10px;">
            <div class=" bar progress-bar bg-success" style="width:${porcentaje}%;"></div>
        </div>
        <div class="signup-form">
            <form action="${pageContext.request.contextPath}/quiz?accion=next" method="post">

                <br/> 
                <h2>Nombre del Quiz</h2>
                <h6 style="text-align: justify;">${question.pregunta}</h6>
                <br/>
                <div class="form-group">
                    <div class="form-check" >
                        <label class="form-check-label">
                            <input type="radio" class="form-check-input" name="optradio" value="${opcion1}" required>${opcion1}
                        </label>
                    </div>
                </div>
                <div class="form-group">
                    <div class="form-check">
                        <label class="form-check-label">
                            <input type="radio" class="form-check-input" name="optradio" value="${opcion2}">${opcion2}
                        </label>
                    </div>
                </div>
                <div class="form-group">
                    <div class="form-check">
                        <label class="form-check-label">
                            <input type="radio" class="form-check-input" name="optradio" value="${opcion3}">${opcion3}
                        </label>
                    </div>
                </div>
                <br/>
                <div class="form-group">
                    <button type="submit" class="btn btn-success btn-block btn-lg">Siguiente</button>
                </div>
                <div class="text-center"><a href="main02.jsp" class="a">Terminar</a></div>
            </form>

        </div>
    </body>

</html>