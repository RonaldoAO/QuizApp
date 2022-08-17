package domain;
import java.time.LocalDateTime;
import java.util.Date;

public class Calificacion {
    private int idQuiz; 
    private int idUser;
    private int calificacion;
    private String fecha;

    public Calificacion(int idQuiz, int idUser, int calificacion, String fecha) {
        this.idQuiz = idQuiz;
        this.idUser = idUser;
        this.calificacion = calificacion;
        this.fecha = fecha;
    }

    public Calificacion(int idQuiz) {
        this.idQuiz = idQuiz;
    }

    
    public int getIdQuiz() {
        return idQuiz;
    }

    public void setIdQuiz(int idQuiz) {
        this.idQuiz = idQuiz;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    
}
