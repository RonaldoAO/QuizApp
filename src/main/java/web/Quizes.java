package web;
import datos.CalificacionDaoJDBC;
import datos.QuestionDaoJDBC;
import datos.QuizDaoJDBC;
import domain.Calificacion;
import domain.Question;
import domain.Quiz;
import domain.Respuesta;
import domain.Usuario;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Stack;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/quiz")
public class Quizes extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if ("eliminar".equals(accion)) {
            this.delete(request, response);
        } else if ("play".equals(accion)) {
            this.play(request, response);
        } else if ("next".equals(accion)) {
            this.next(request, response);
        } else if("resultados".equals(accion)){
            this.resultados(request, response);
        }
        //Crear indice como atributoo
        //Si la peticion es next le sumamos uno
    }

    private void resultados(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        HttpSession sesion = request.getSession();
        int idQuiz = Integer.parseInt(request.getParameter("idquiz"));
        System.out.println("IDQUIZ: " + idQuiz);
        List<Calificacion> calificaciones = new CalificacionDaoJDBC().listar(new Quiz(idQuiz));
        request.setAttribute("calificaciones", calificaciones);
        //sesion.setAttribute("calificaciones", calificaciones);
        request.getRequestDispatcher("estadisticas.jsp").forward(request, response);
    }
    
    private void play(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sesion = request.getSession();
        Usuario usuario = (Usuario) sesion.getAttribute("usuario");

        if (usuario != null) {
            int idQuiz = Integer.parseInt(request.getParameter("idQuiz"));
            List<Question> questions = new QuestionDaoJDBC().listar(new Quiz(idQuiz));
            List<Respuesta> respuestas = new ArrayList<>(); 
            float particion = 100 / questions.size();
            sesion.setAttribute("particion", particion);
            sesion.setAttribute("questionsPlay", questions);
            sesion.setAttribute("respuestas", respuestas);
            sesion.setAttribute("indice", 0);
            sesion.setAttribute("calificacion", 0);

            acciondefault(request, response);
        } else {
            response.sendRedirect("register.jsp");
        }

    }

    private void delete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sesion = request.getSession();
        Quiz quiz = (Quiz) sesion.getAttribute("quiz");
        QuizDaoJDBC quizJDBC = new QuizDaoJDBC();
        quizJDBC.eliminar(quiz);
        sesion.setAttribute("quiz", null);
        sesion.setAttribute("questions", null);
        //response.sendRedirect("/user");

        RequestDispatcher rd = request.getRequestDispatcher("user");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if ("create".equals(accion)) {
            this.create(request, response);
        } else if ("eliminar".equals(accion)) {
            this.delete(request, response);
        } else if ("next".equals(accion)) {
            this.next(request, response);
        }
    }

    private void create(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sesion = request.getSession();
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String dificulty = request.getParameter("dificulty");
        Usuario usuario = (Usuario) sesion.getAttribute("usuario");
        QuizDaoJDBC quizDAO = new QuizDaoJDBC();
        int rows = quizDAO.insertar(new Quiz(name, dificulty, description, usuario.getIdUsuario()));
        Quiz quiz = quizDAO.buscarPorNombre(name);

        if (rows != 0) {

            sesion.setAttribute("quiz", quiz);
            response.sendRedirect("making.jsp");
        }
    }

    private void next(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sesion = request.getSession();

        List<Respuesta> respuestas = (List<Respuesta>) sesion.getAttribute("respuestas");
        
        String eleccion = request.getParameter("optradio");
        System.out.println(eleccion);
        Question question = (Question) sesion.getAttribute("question");
        System.out.println(question.getRespuesta());
        
        Respuesta respuesta = new Respuesta(question.getPregunta(), eleccion); 
        if (eleccion.equals(question.getRespuesta())) {//Si es correcta la respuesta
            System.out.println("Respuesta Correcta");
            int calificacion = (int) sesion.getAttribute("calificacion") + 1;
            sesion.setAttribute("calificacion", calificacion);
            
            //Añadimos el icon para despues mostrarlo en los resultados
            respuesta.setIcon("fa-solid fa-circle-check");
        }else{
            respuesta.setIcon("fa-solid fa-circle-xmark");
        }
        respuestas.add(respuesta); 
        sesion.setAttribute("respuestas", respuestas);

        acciondefault(request, response);
    }

    private void acciondefault(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Pregunta con el parametro "indice"
        HttpSession sesion = request.getSession();
        int indice = (int) sesion.getAttribute("indice");
        System.out.println("INDICE: " + indice);
        List<Question> questions = (List<Question>) sesion.getAttribute("questionsPlay");

        float particion = (float) sesion.getAttribute("particion");
        sesion.setAttribute("porcentaje", particion * indice);

        if (indice < questions.size()) {
            sesion.setAttribute("question", questions.get(indice));//Pregunta

            Stack<Integer> rango = generadorDeNumerosAleatorio(3); //Reactivos
            request.setAttribute(generador(rango.pop()), questions.get(indice).getRespuesta());
            request.setAttribute(generador(rango.pop()), questions.get(indice).getReactivo1());
            request.setAttribute(generador(rango.pop()), questions.get(indice).getReactivo2());
            indice++;
            sesion.setAttribute("indice", indice);
            request.getRequestDispatcher("review.jsp").forward(request, response);
        } else {
            int calificacion = (int)sesion.getAttribute("calificacion");
            Usuario usuario = (Usuario) sesion.getAttribute("usuario");
            sesion.setAttribute("total", questions.size());
            int calificacionFinal = (100 * calificacion) / questions.size(); 
            sesion.setAttribute("calificacionFinal", calificacionFinal);
            
            //Se guarda la calificacion final  
            CalificacionDaoJDBC calificacionDAO  = new CalificacionDaoJDBC();
            Date dt  = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String currentTime = sdf.format(dt); 
            calificacionDAO.insert(new Calificacion(questions.get(0).getIdQuiz(), usuario.getIdUsuario(), calificacionFinal, currentTime)); 
            
            
            response.sendRedirect("result.jsp");
        }
        //response.sendRedirect("review.jsp");

    }

    private String generador(int i) {
        if (i == 0) {
            return "opcion1";
        } else if (i == 1) {
            return "opcion2";
        } else {
            return "opcion3";
        }
    }

    private Stack<Integer> generadorDeNumerosAleatorio(int limite) {
        int pos;
        Stack<Integer> pCartas = new Stack<Integer>();
        for (int i = 0; i < limite; i++) {
            pos = (int) Math.floor(Math.random() * limite);
            while (pCartas.contains(pos)) {
                pos = (int) Math.floor(Math.random() * limite);
            }
            pCartas.push(pos);
        }
        return pCartas;
    }
}
