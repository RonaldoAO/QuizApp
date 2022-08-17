package datos;

import domain.Calificacion;
import domain.Quiz;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CalificacionDaoJDBC {
    
    private static final String INSERT = "INSERT INTO calificacion(calificacion, fecha, idquiz, idUser) VALUES(?,?,?,?)";
    private static final String SELECT_BY_IDQUIZ = "SELECT * FROM calificacion WHERE idquiz = ?"; 
    
    public int insert(Calificacion calificacion){
        int rows = 0;
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(INSERT);
            stmt.setInt(1, calificacion.getCalificacion());
            stmt.setString(2, calificacion.getFecha());
            stmt.setInt(3, calificacion.getIdQuiz());
            stmt.setInt(4, calificacion.getIdUser());
            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally{
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }
    
    public List<Calificacion> listar(Quiz quiz){
        List<Calificacion> calificaciones = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try { 
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SELECT_BY_IDQUIZ);
            stmt.setInt(1, quiz.getIdQuiz());
            rs = stmt.executeQuery();
            while(rs.next()){
                int calif = rs.getInt("calificacion");
                String fecha = rs.getString("fecha");
                int idQuiz = rs.getInt("idquiz");
                int idUser = rs.getInt("idUser");
                
                Calificacion califi = new Calificacion(idQuiz, idUser, calif, fecha); 
                calificaciones.add(califi); 
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally{
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return calificaciones;
    }
}
