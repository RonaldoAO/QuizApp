package domain;

public class Quiz {

    private int idQuiz;
    private String name;
    private String difficulty;
    private String description;
    private int idUsuario;
    private String nombreUsuario;
    private int noDePreguntas;

    public Quiz(int idQuiz) {
        this.idQuiz = idQuiz;
    }

    public Quiz(int idQuiz, String name, String difficulty, String description, int idUsuario, String nombreUsuario, int noDePreguntas) {
        this.idQuiz = idQuiz;
        this.name = name;
        this.difficulty = difficulty;
        this.description = description;
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.noDePreguntas = noDePreguntas;
    }
    
    public Quiz(int idQuiz, String name, String difficulty, String description, int idUsuario) {
        this.idQuiz = idQuiz;
        this.name = name;
        this.difficulty = difficulty;
        this.description = description;
        this.idUsuario = idUsuario;
    }

    
    public Quiz(String name, String difficulty, String description, int idUsuario) {
        this.name = name;
        this.difficulty = difficulty;
        this.description = description;
        this.idUsuario = idUsuario;
    }

    public Quiz(int idQuiz, String name) {
        this.idQuiz = idQuiz;
        this.name = name;
    }
    
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
 
    public int getIdQuiz() {
        return idQuiz;
    }

    public void setIdQuiz(int idQuiz) {
        this.idQuiz = idQuiz;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public int getNoDePreguntas() {
        return noDePreguntas;
    }

    public void setNoDePreguntas(int noDePreguntas) {
        this.noDePreguntas = noDePreguntas;
    }

    
    @Override
    public String toString() {
        return "Quiz{" + "idQuiz=" + idQuiz + ", difficulty=" + difficulty + ", description=" + description + ", idUsuario=" + idUsuario + '}';
    }

}
