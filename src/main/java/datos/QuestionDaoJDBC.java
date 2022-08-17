package datos;

import static datos.Conexion.close;
import domain.Question;
import domain.Quiz;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuestionDaoJDBC {

    private static final String SQL_UPDATE = "UPDATE question "
            + " SET pregunta=?, respuesta=?, reactivo1=?, reactivo2=? WHERE idQuestion=?";

    private static final String SQL_SELECT = "SELECT * FROM question WHERE idquiz = ?";

    private static final String SQL_INSERT = "INSERT INTO question(idquiz, pregunta, respuesta, reactivo1, reactivo2)"
            + " VALUES(?, ?, ?, ?, ?)";

    private static final String SQL_SELECT_BY_ID = "SELECT * FROM question WHERE idQuestion = ?";
    
    private static final String SQL_DELETE = "DELETE FROM question WHERE idQuestion = ?";

    public int insert(Question question) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setInt(1, question.getIdQuiz());
            stmt.setString(2, question.getPregunta());
            stmt.setString(3, question.getRespuesta());
            stmt.setString(4, question.getReactivo1());
            stmt.setString(5, question.getReactivo2());
            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return rows;
    }

    public List<Question> listar(Quiz quiz) {
        List<Question> questions = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            stmt.setInt(1, quiz.getIdQuiz());
            rs = stmt.executeQuery();
            while (rs.next()) {
                int idQuestion = rs.getInt("idQuestion"); 
                int idQuiz = rs.getInt("idquiz"); 
                String pregunta = rs.getString("pregunta");
                String respueste = rs.getString("respuesta");
                String reactivo1 = rs.getString("reactivo1");
                String reactivo2 = rs.getString("reactivo2");
                questions.add(new Question(idQuestion, idQuiz, pregunta, respueste, reactivo1, reactivo2));
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally{
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return questions;
    }

    public Question encontrar(Question question) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_ID);
            stmt.setInt(1, question.getIdQuestion());
            rs = stmt.executeQuery();
            rs.next();
            question.setPregunta(rs.getString("pregunta"));
            question.setRespuesta(rs.getString("respuesta"));
            question.setReactivo1(rs.getString("reactivo1"));
            question.setReactivo2(rs.getString("reactivo2"));
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally{
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return question;
    }
    
    public int delete(Question question){
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, question.getIdQuestion());
            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally{
            Conexion.close(stmt);
            Conexion.close(conn);
        }      
        return rows;
    }
    
    public int update(Question question){
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE); 
            stmt.setString(1, question.getPregunta());
            stmt.setString(2, question.getRespuesta());
            stmt.setString(3, question.getReactivo1());
            stmt.setString(4, question.getReactivo2());
            stmt.setInt(5, question.getIdQuestion());
            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally{
            close(stmt);
            close(conn);
        }
        
        return rows;
    }

    
}
