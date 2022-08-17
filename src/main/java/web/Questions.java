package web;

import datos.QuestionDaoJDBC;
import datos.QuizDaoJDBC;
import domain.Question;
import domain.Quiz;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/question")
public class Questions extends HttpServlet {

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if(accion!= null){
            switch (accion) {
                case "editar":
                    this.editar(request, response);
                    break;
                case "eliminar":
                    this.eliminar(request, response);
                    break;    
                default:
                    this.accionPorDefault(request, response);
            }
        }else{
            this.accionPorDefault(request, response);
        }
    }

    private void editar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int idCliente = Integer.parseInt(request.getParameter("idCliente"));
        System.out.println("ID CLIENTE: " + idCliente);
        Question question = new QuestionDaoJDBC().encontrar(new Question("", idCliente));
        System.out.println("QUESTION: " + question);
        request.setAttribute("question", question);
        String jspEditar = "/WEB-INF/JSPs/updateQuestion.jsp";
        request.getRequestDispatcher(jspEditar).forward(request, response);
    }
    
    private void eliminar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        int idCliente = Integer.parseInt(request.getParameter("idCliente"));
        System.out.println("ID CLIENTE: " + idCliente);
        int rows = new QuestionDaoJDBC().delete(new Question("", idCliente));
        this.accionPorDefault(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if(accion!= null){
            switch (accion) {
                case "insertar":
                    this.insertar(request, response);
                    break; 
                case "modificar": 
                    this.modificar(request, response);
                    break; 
                default:
                    this.accionPorDefault(request, response);
            }
        }else{
            this.accionPorDefault(request, response);
        }
    }
    private void modificar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        int idQuestion = Integer.parseInt(request.getParameter("idQuestion"));
        String pregunta = request.getParameter("pregunta");
        String respuesta = request.getParameter("respuesta");
        String reactivo2 = request.getParameter("reactivo2");
        String reactivo3 = request.getParameter("reactivo3");
        
        int rows = new QuestionDaoJDBC().update(new Question(idQuestion, 1, pregunta, respuesta, reactivo3, reactivo2));
        
        this.accionPorDefault(request, response);
    }
    private void insertar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sesion = request.getSession();
        QuestionDaoJDBC questionJDBC = new QuestionDaoJDBC(); 
        String pregunta = request.getParameter("pregunta");
        String respuesta = request.getParameter("respuesta");
        String reactivo2 = request.getParameter("reactivo2");
        String reactivo3 = request.getParameter("reactivo3");

        
        Quiz quiz = (Quiz)sesion.getAttribute("quiz"); 
        System.out.println(quiz);
        questionJDBC.insert(new Question(quiz.getIdQuiz(), pregunta, respuesta, reactivo2, reactivo3));
        this.accionPorDefault(request, response);
    }
    
    /*
    * El accion por default lista las preguntas por ID del Quiz
    */
    private void accionPorDefault(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        HttpSession sesion = request.getSession();
        Quiz quiz = (Quiz)sesion.getAttribute("quiz"); 
        if(quiz == null){
            quiz = new Quiz(Integer.parseInt(request.getParameter("idquiz"))); 
            sesion.setAttribute("quiz", quiz);
        }
        QuestionDaoJDBC questionJDBC = new QuestionDaoJDBC();
        sesion.setAttribute("questions", questionJDBC.listar(quiz));
        
        response.sendRedirect("making.jsp");
        
    }
}
