package model;

import java.io.Serializable;
import java.sql.Date;

public class ToDoList implements Serializable{
	//todoテーブルのレコードを表すEntity
	private int key;
	private String todoMain;
	private String memo;
	private Date dueDate;
	
	public ToDoList() {}
	public ToDoList(int key, String todoMain, String memo, Date dueDate) {
		this.key = key;
		this.todoMain = todoMain;
		this.memo = memo;
		this.dueDate = dueDate;
	}
	
	public int getKey() {
		return this.key;
	}
	public String getTodoMain() {
		return this.todoMain;
	}
	public String getMemo() {
		return this.memo;
	}
	public Date getDueDate() {
		return this.dueDate;
	}
}
