package test;

import datos.QuestionDaoJDBC;
import datos.QuizDaoJDBC;
import datos.UsuarioDaoJDBC;
import domain.Question;
import domain.Quiz;
import domain.Usuario;
import java.text.SimpleDateFormat;
import java.util.Date;

public class main {
    public static void main(String[] args) {
        Date dt  = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        
        String currentTime = sdf.format(dt); 
        
        System.out.println(currentTime);
    }
}
