package datos;

import domain.Quiz;
import domain.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class QuizDaoJDBC {
    
    private static final String SQL_SELECT = "SELECT * FROM quiz";
    
    private static final String SQL_SELECT_FOR_NAME = "SELECT * FROM quiz WHERE name=?";
    
    private static final String SQL_SELECT_FOR_IDUSER = "SELECT * FROM quiz WHERE idUsuario = ?";
    
    private static final String SQL_INSERT = "INSERT INTO quiz(difficulty, idUsuario, description, name) "
            + " VALUES(?, ?, ?, ?)"; 
    
    
    private static final String SQL_DELETE = "DELETE FROM quiz WHERE idquiz = ?";
    
    public Quiz buscarPorNombre(String nombre){
        Quiz quiz = null;
        Connection conn = null; 
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_FOR_NAME);
            stmt.setString(1, nombre);
            rs = stmt.executeQuery();
            if(rs.next()){
                int idQuiz = rs.getInt("idquiz");
                String name = rs.getString("name");
                String difficulty  = rs.getString("difficulty");
                String description = rs.getString("description");
                int idUsuario = rs.getInt("idUsuario");
                quiz = new Quiz(idQuiz, name, difficulty, description, idUsuario);
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally{
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        
        return quiz; 
    }
    
    public List<Quiz> listarPorIdUser(Usuario usuario){
        List<Quiz> quizes = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null; 
        ResultSet rs = null;
        try { 
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_FOR_IDUSER);
            stmt.setInt(1, usuario.getIdUsuario());
            rs = stmt.executeQuery();
            while(rs.next()){
                String nombre = rs.getString("name");
                int idquiz = rs.getInt("idquiz");
                quizes.add(new Quiz(idquiz, nombre)); 
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally{
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return quizes;
    }
    
    public int insertar(Quiz quiz) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, quiz.getDifficulty());
            stmt.setInt(2, quiz.getIdUsuario());
            stmt.setString(3, quiz.getDescription());
            stmt.setString(4, quiz.getName());
            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }
    
    public List<Quiz> listar() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Quiz quiz = null;
        List<Quiz> quizes = new ArrayList<>();
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int idQuiz = rs.getInt("idquiz");
                String difficulty = rs.getString("difficulty");
                int idUsuario = rs.getInt("idUsuario"); 
                String description = rs.getString("description");
                String name = rs.getString("name");
                quiz = new Quiz(idQuiz, name, difficulty, description, idUsuario); 
                
                quizes.add(quiz);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return quizes;
    }
    
    public int eliminar(Quiz quiz) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, quiz.getIdQuiz());

            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }
}
