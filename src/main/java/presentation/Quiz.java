package presentation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/Quiz")
public class Quiz extends HttpServlet {
    private String startHtml;
    private String endHtml;
    public Quiz(){
        this.startHtml="<!DOCTYPE html><html lang=\"en\"><head><meta charset=\"UTF-8\"><title>Quiz</title></head><body><h1>The Number Quiz</h1>";
        this.endHtml="</body></html>";
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        logic.Quiz q =(logic.Quiz) session.getAttribute("quiz");
        String num=request.getParameter("num");
        int answer=(num.isEmpty())?0:Integer.parseInt(num);
        q.setAnswer(answer);
        session.setAttribute("quiz",q);

        if(q.hasQuestion())
            doGet(request,response);
        else {
            PrintWriter print=response.getWriter();
            response.setContentType("text/html");
            print.println(startHtml);
            print.println("Your Current Score is "+q.getScore()+" .<br/>");
            print.println("You have completed the Number Quiz, with a score of "+ q.getScore()+" out of "+q.getTotalScore()+".");
            print.println(endHtml);

        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session=request.getSession();
        logic.Quiz q =(session.isNew())?new logic.Quiz():(logic.Quiz) session.getAttribute("quiz");

        PrintWriter print=response.getWriter();
        response.setContentType("text/html");
        print.println(startHtml);
        print.println("Your Current Score is "+q.getScore()+" .<br/>");
        print.println("Guess the next number in the sequence.<br/>");
        print.println(q.getQuestion()+"<br/>");
        print.println("<form action=\"Quiz\" method=\"post\">");
        print.println("<label>Your Answer : <input type=\"number\" name=\"num\"></label><br/>");
        print.println("<input type=\"submit\" value=\"Submit\"></form>");

        print.println(endHtml);

        session.setAttribute("quiz",q);




    }
}
