package domain;

public class Question {
    private int idQuestion;// Primary key
    private int idQuiz;// Foreign key
    private String pregunta;
    private String respuesta; 
    private String reactivo1;
    private String reactivo2;
    private String reactivo3;

    public Question(String pregunta, int idQuestion){
        this.idQuestion = idQuestion;
    }
    /*
    * Constructor para el listado de las preguntas
    */
    public Question(int idQuiz) {
        this.idQuiz = idQuiz;
    }
  
    public Question(String pregunta, String respuesta, String reactivo1, String reactivo2) {
        this.pregunta = pregunta;
        this.respuesta = respuesta;
        this.reactivo1 = reactivo1;
        this.reactivo2 = reactivo2;
    }

    public Question(int idQuiz, String pregunta, String respuesta, String reactivo1, String reactivo2) {
        this.idQuiz = idQuiz;
        this.pregunta = pregunta;
        this.respuesta = respuesta;
        this.reactivo1 = reactivo1;
        this.reactivo2 = reactivo2;
    }

    public Question(int idQuestion, int idQuiz, String pregunta, String respuesta, String reactivo1, String reactivo2) {
        this.idQuestion = idQuestion;
        this.idQuiz = idQuiz;
        this.pregunta = pregunta;
        this.respuesta = respuesta;
        this.reactivo1 = reactivo1;
        this.reactivo2 = reactivo2;
    }
    
    

    public int getIdQuestion() {
        return idQuestion;
    }

    public void setIdQuestion(int idQuestion) {
        this.idQuestion = idQuestion;
    }

    public int getIdQuiz() {
        return idQuiz;
    }

    public void setIdQuiz(int idQuiz) {
        this.idQuiz = idQuiz;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public String getReactivo1() {
        return reactivo1;
    }

    public void setReactivo1(String reactivo1) {
        this.reactivo1 = reactivo1;
    }

    public String getReactivo2() {
        return reactivo2;
    }

    public void setReactivo2(String reactivo2) {
        this.reactivo2 = reactivo2;
    }

    public String getReactivo3() {
        return reactivo3;
    }

    public void setReactivo3(String reactivo3) {
        this.reactivo3 = reactivo3;
    }

    
    @Override
    public String toString() {
        return "idQuiz=" + idQuiz + "\n" + pregunta + "\n respuesta:" + respuesta + "\n reactivo1=" + reactivo1 + "\n reactivo2=" + reactivo2 ;
    }
    
    
    
    
}
