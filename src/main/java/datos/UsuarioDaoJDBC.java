package datos;

import domain.Usuario;
import java.sql.*;
import java.util.*;

public class UsuarioDaoJDBC {

    private static final String SQL_SELECT = "SELECT * FROM usuario";

    private static final String SQL_SELECT_BY_ID = "SELECT *"
            + " FROM usuario WHERE idUsuario = ?";

    private static final String SQL_SELECT_BY_EMAIL = "SELECT * FROM usuario WHERE email= ? ";

    private static final String SQL_INSERT = "INSERT INTO usuario(nombre, email, password) "
            + " VALUES(?, ?, ?)";

    private static final String SQL_UPDATE = "UPDATE usuario "
            + " SET nombre=?, email=?, password=? WHERE idUsuario=?";

    private static final String SQL_DELETE = "DELETE FROM usuario WHERE idUsuario = ?";
    
    private static final String LOGIN = "SELECT * FROM usuario WHERE nombre = ? AND password = ?";

    public List<Usuario> listar() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Usuario cliente = null;
        List<Usuario> clientes = new ArrayList<>();
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int idCliente = rs.getInt("idUsuario");
                String nombre = rs.getString("nombre");
                String email = rs.getString("email");
                String password = rs.getString("password");

                cliente = new Usuario(idCliente, nombre, email, password);
                clientes.add(cliente);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return clientes;
    }

    public static Usuario encontrar(Usuario usuario) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_ID);
            stmt.setInt(1, usuario.getIdUsuario());
            rs = stmt.executeQuery();
            rs.next();//nos posicionamos en el primer registro devuelto

            String nombre = rs.getString("nombre");
            String email = rs.getString("email");
            String password = rs.getString("password");

            usuario.setNombre(nombre);
            usuario.setEmail(email);
            usuario.setPassword(password);

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return usuario;
    }

    
    public Usuario login(Usuario usuario) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(LOGIN);
            stmt.setString(1, usuario.getNombre());
            stmt.setString(2, usuario.getPassword());
            rs = stmt.executeQuery();
            if(rs.next()){
                usuario.setEmail(rs.getString("email"));
                usuario.setIdUsuario(rs.getInt("idUsuario"));
            }else{
                return null;
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return usuario;
    }
    /*
    * Metodo hecho para confirmar la existencia del correo en el registro
    * true = yes
    * false = no
     */
    public Usuario encontrarPorCorreo(String correo) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Usuario usuario = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_EMAIL);
            stmt.setString(1, correo);
            rs = stmt.executeQuery();
            //nos posicionamos en el primer registro devuelto
            if (rs.next()) {
                String nombre = rs.getString("nombre");
                int id = rs.getInt("idUsuario");
                String email = rs.getString("email");
                String password = rs.getString("password");

                usuario = new Usuario(id, nombre, email, password);
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return usuario;
    }
    
    public boolean confirmarExistencia(String correo){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean resultado = false;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_EMAIL);
            stmt.setString(1, correo);
            rs = stmt.executeQuery();
            //nos posicionamos en el primer registro devuelto
            resultado =  rs.next();
            

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return resultado;
    }
    
    public int insertar(Usuario usuario) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, usuario.getNombre());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getPassword());

            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    public int actualizar(Usuario usuario) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, usuario.getNombre());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getPassword());
            stmt.setInt(4, usuario.getIdUsuario());

            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    public int eliminar(Usuario usuario) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, usuario.getIdUsuario());

            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    
    public static String funcionEjemplo(){
        return "Hola mundo"; 
    }
}
