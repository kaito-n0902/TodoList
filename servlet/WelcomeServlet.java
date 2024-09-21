package servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.InsertLogic;
import model.ToDoList;

public class WelcomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//作成済みのリストをセッションスコープに保存
		HttpSession session = request.getSession();
		InsertLogic bo = new InsertLogic();
		List<ToDoList> todolists = bo.findTodolist(); 
        
		session.setAttribute("todolist", todolists);
		
		//welcome.jspへフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/welcome.jsp");
		dispatcher.forward(request, response);
	}
}
