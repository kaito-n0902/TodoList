package model;

import dao.ListsDAO;

public class UpdateLogic {
	ListsDAO dao = new ListsDAO();
	
	public void execute(ToDoList editlist) {
		//テーブル内指定行を編集
		dao.updateRow(editlist);
	}
}
