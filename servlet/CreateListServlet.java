package servlet;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.InsertLogic;
import model.ToDoList;

public class CreateListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//createList.jspへフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/createList.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		String todoMain = request.getParameter("todoMain");
		String memo = request.getParameter("memo");
		String dueDate = request.getParameter("dueDate");
		Date sqlDate= Date.valueOf(dueDate);
		
		//取得したリクエストパラメータを元にDBにカラムを追加
		InsertLogic insertbo = new InsertLogic();
		int findMK = insertbo.findMainkey();
		ToDoList todoList = new ToDoList(findMK,todoMain, memo, sqlDate);
		
		insertbo.execute(todoList);
		
		//作成済みのリストをセッションスコープに保存
		HttpSession session = request.getSession();
		List<ToDoList> todolists = insertbo.findTodolist(); 
        
		session.setAttribute("todolist", todolists);
		
		//welcome.jspへフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/welcome.jsp");
		dispatcher.forward(request, response);
	}

}
