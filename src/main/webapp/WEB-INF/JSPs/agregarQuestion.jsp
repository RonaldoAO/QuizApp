<div class="modal fade" id="agregarQuestionModal">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header bg-success text-white">
                <h5 class="modal-title">Pregunta cliente</h5> 
                <button class="close" data-dismiss="modal">
                    <span>&times;</span>
                </button>
            </div>

            <form action="${pageContext.request.contextPath}/question?accion=insertar"
                  method="POST" class="was-validated">

                <div class="modal-body">
                    <div class="form-group">
                        <label for="validationTextarea">Pregunta</label>
                        <textarea name="pregunta" class="form-control is-invalid" id="validationTextarea" placeholder="Inserta tu pregunta" required></textarea>
                        <div class="invalid-feedback">
                            Porfavor ingresa una pregunta.
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="apellido">Respuesta</label>
                        <input type="text" class="form-control" name="respuesta" required>
                    </div>
                    <div class="form-group">
                        <label for="apellido">Reactivo 2</label>
                        <input type="text" class="form-control" name="reactivo2" required>
                    </div>
                    <div class="form-group">
                        <label for="apellido">Reactivo 3</label>
                        <input type="text" class="form-control" name="reactivo3" required>
                    </div>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-primary" type="submit">Guardar</button>
                </div>    
            </form>
        </div>
    </div>
</div>
