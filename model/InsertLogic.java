package model;

import java.util.List;

import dao.ListsDAO;

public class InsertLogic {	
	ListsDAO dao = new ListsDAO();
	
	public List<ToDoList> findTodolist() {
		//SELECTのDAO実行
		return dao.findTodolist();
	}
	
	public int findMainkey() {
		//主キー取得用DAO実行
		return dao.findMainkey();
	}
	
	public void execute(ToDoList todoList) {
		//INSERTのDAO実行
		dao.insertList(todoList);
	}
}