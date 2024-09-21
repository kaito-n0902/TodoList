package model;

import dao.ListsDAO;

public class DeleteLogic {
	ListsDAO dao = new ListsDAO();
	
	public void execute(ToDoList delist) {
		//テーブルから指定行削除
		dao.deleteRow(delist);
	}
}
