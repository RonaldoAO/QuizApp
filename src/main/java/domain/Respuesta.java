package domain;

public class Respuesta {
    String pregunta;
    String respuesta;
    String icon;

    public Respuesta(String pregunta, String respuesta ){
        this.pregunta = pregunta;
        this.respuesta = respuesta;
    }
    public Respuesta(String pregunta, String respuesta, String icon) {
        this.respuesta = respuesta;
        this.icon = icon;
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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
    
}
