package web;

import datos.QuestionDaoJDBC;
import datos.QuizDaoJDBC;
import datos.UsuarioDaoJDBC;
import domain.Quiz;
import domain.Usuario;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import  javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/user")
public class User extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        QuizDaoJDBC quizDao =  new QuizDaoJDBC();
        List<Quiz> quizes = quizDao.listar(); 
        this.reacomodo(quizes); //Se añade el nombre de Usuario y el numero de preguntas
        
        
        HttpSession sesion = request.getSession();
        sesion.setAttribute("quizes", quizes);
        
        sesion.setAttribute("questions", null);
        
        Usuario usuario = (Usuario)sesion.getAttribute("usuario");
        //Si existe el usuario
        if(usuario != null && usuario.getIdUsuario() != 0){
            //Listar por idUsuario
            List<Quiz> quizesPorUser = new QuizDaoJDBC().listarPorIdUser(usuario); 
            sesion.setAttribute("quizesPorUser", quizesPorUser);
        }
        response.sendRedirect("main01.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");

        if ("register".equals(accion)) {
            this.register(request, response);
        } else if ("login".equals(accion)) {
            this.login(request, response);
        } else if ("logout".equals(accion)) {
            HttpSession sesion = request.getSession();
            sesion.setAttribute("usuario", null);
            request.getRequestDispatcher("main01.jsp").forward(request, response);
        }

    }

    private void register(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UsuarioDaoJDBC usuario = new UsuarioDaoJDBC();
        String nombre = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirm_password = request.getParameter("confirm_password");

        boolean comprobacion = usuario.confirmarExistencia(email);

        if (!confirm_password.equals(password)) {
            request.setAttribute("status", "failurePass");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        } else if (comprobacion) {
            request.setAttribute("status", "failureMail");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        } else {
            Usuario user = new Usuario(nombre, email, password);
            int countRows = usuario.insertar(user);
            if (countRows > 0) {
                request.setAttribute("email", email);
                this.accionDefault(request, response);
            } else {
                request.setAttribute("status", "failure");
                request.getRequestDispatcher("register.jsp").forward(request, response);
            }
        }
    }

    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Usuario usuario = new UsuarioDaoJDBC().login(new Usuario(username, password));
        if (usuario != null) {
            HttpSession sesion = request.getSession();
            sesion.setAttribute("usuario", usuario);
            response.sendRedirect("main02.jsp");
            //Si existe el usuario
            //Listar por idUsuario
            List<Quiz> quizesPorUser = new QuizDaoJDBC().listarPorIdUser(usuario); 
            sesion.setAttribute("quizesPorUser", quizesPorUser);
        } else {
            request.setAttribute("status", "failure");
            request.getRequestDispatcher("main01.jsp").forward(request, response);
        }
    }

    private void accionDefault(HttpServletRequest request, HttpServletResponse response) {
        String correo = request.getParameter("email");
        Usuario usuario = new UsuarioDaoJDBC().encontrarPorCorreo(correo);
        HttpSession sesion = request.getSession();
        sesion.setAttribute("usuario", usuario);
        //Si existe el usuario
        if(usuario != null){
            //Listar por idUsuario
            List<Quiz> quizesPorUser = new QuizDaoJDBC().listarPorIdUser(usuario); 
            sesion.setAttribute("quizesPorUser", quizesPorUser);
        }
        try {
            response.sendRedirect("main02.jsp");
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
    }
    private void reacomodo(List<Quiz> quizes){
        Usuario usuario;
        for(Quiz quiz: quizes){
            usuario = new UsuarioDaoJDBC().encontrar(new Usuario(quiz.getIdUsuario())); 
            quiz.setNombreUsuario(usuario.getNombre());
            quiz.setNoDePreguntas(new QuestionDaoJDBC().listar(quiz).size());
        }
    }
}
