package servlet;

import java.io.IOException;
import java.sql.Date;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.DeleteLogic;
import model.ToDoList;
import model.UpdateLogic;

public class ListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		String keyStr = request.getParameter("key");
		int key = 0;
		key = Integer.parseInt(keyStr);
		String todoMain = request.getParameter("todoMain");
		String memo = request.getParameter("memo");
		String dueDate = request.getParameter("dueDate");
		Date sqlDate= Date.valueOf(dueDate);
				
		//取得したリクエストパラメータからDB内の該当カラムをセッションスコープに保存
		HttpSession session = request.getSession();
		ToDoList delist = new ToDoList(key, todoMain, memo, sqlDate);
		session.setAttribute("delist", delist);
				
		//削除か編集かをactionのリクエストパラメータで判断
		if(action.equals("削除")) {
			//deleteList.jspへフォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/deleteList.jsp");
			dispatcher.forward(request, response);
		}else if(action.equals("編集")) {
			//editList.jspへフォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/editList.jsp");
			dispatcher.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		
		//削除か編集かをactionのリクエストパラメータで判断
		if(action.equals("delete")) {
			HttpSession session = request.getSession();
			ToDoList delist = (ToDoList)session.getAttribute("delist");
			DeleteLogic deletebo = new DeleteLogic();
			deletebo.execute(delist);
			
			//deleteComplete.jspにフォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/deleteComplete.jsp");
			dispatcher.forward(request, response);
		}else if(action.equals("edit")) {
			String keyStr = request.getParameter("key");
			int key = 0;
			key = Integer.parseInt(keyStr);
			String todoMain = request.getParameter("todoMain");
			String memo = request.getParameter("memo");
			String dueDate = request.getParameter("dueDate");
			Date sqlDate= Date.valueOf(dueDate);
			ToDoList editlist = new ToDoList(key, todoMain, memo, sqlDate);
			UpdateLogic updatebo = new UpdateLogic();
			updatebo.execute(editlist);
			
			//editComplete.jspにフォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/editComplete.jsp");
			dispatcher.forward(request, response);
		}
	}

}
