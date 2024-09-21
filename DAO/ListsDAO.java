package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.ToDoList;

public class ListsDAO {
	private final String JDBC_URL = "jdbc:postgresql://192.168.182.128:5432/todo";
	
	public void insertList(ToDoList todoList) {
		//TODOLIST新規作成時に使用
		
		//JDBCドライバを読み込む
		try{
			Class.forName("org.postgresql.Driver");
		}catch(ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}
		
		//postgreSQLへ接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL);
				PreparedStatement preStatement = conn.prepareStatement("INSERT INTO todo (t_num, t_todo, t_memo, t_day) VALUES(?, ?, ?, ?);"))
		{
			
			int key = todoList.getKey();
			String todo = todoList.getTodoMain();
			String memo = todoList.getMemo();
			Date date = todoList.getDueDate();
			
			//INSERT文を準備
			preStatement.setInt(1, key);
			preStatement.setString(2, todo);
            preStatement.setString(3, memo);
            preStatement.setDate(4, date);
            
          //INSERT文を実行
			preStatement.executeUpdate(); 
		
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int findMainkey() {
		//主キー取得用
		//JDBCドライバを読み込む
		int findMK = 1;
		try{
			Class.forName("org.postgresql.Driver");
		}catch(ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}
			
		//postgreSQLへ接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL);
				PreparedStatement preStatement = conn.prepareStatement("SELECT * FROM todo ORDER BY t_num DESC LIMIT 1;"))
		{	
			//SELECT文を実行し、結果を取得
			ResultSet rs = preStatement.executeQuery();
			
			if(rs.next()) {
				//取得した主キーに+1する
				findMK = rs.getInt("t_num") + 1;
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return findMK;
	}
	
	public List<ToDoList> findTodolist() {
		//welcome.jspに作成済みのtodolist表示用
		
		List<ToDoList> todoLists = new ArrayList<>();
		
		//JDBCドライバを読み込む
		try{
			Class.forName("org.postgresql.Driver");
		}catch(ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}
		
		//postgreSQLへ接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL);
			PreparedStatement preStatement = conn.prepareStatement("SELECT * FROM todo;"))
		{
			
		    //SELECT文を実行
			ResultSet rs = preStatement.executeQuery(); 
			
			//SELECT結果の受け取り
			while(rs.next()){
                int mainKey = rs.getInt(1);
                String todo = rs.getString(2);
                String memo = rs.getString(3);
                Date date = rs.getDate(4);
                ToDoList todoList = new ToDoList(mainKey, todo, memo, date);
                todoLists.add(todoList);
            }
				
		}catch(SQLException e) {
				e.printStackTrace();
		}
		return todoLists;
	}
	
	public void deleteRow(ToDoList delist) {
		//todoリスト削除時用
		
		//JDBCドライバを読み込む
			try{
				Class.forName("org.postgresql.Driver");
			}catch(ClassNotFoundException e) {
				throw new IllegalStateException("JDBCドライバを読み込めませんでした");
			}
				
			//postgreSQLへ接続
			try (Connection conn = DriverManager.getConnection(JDBC_URL);
				PreparedStatement preStatement = conn.prepareStatement("DELETE FROM todo WHERE t_num = ? AND t_todo = ? AND t_memo = ? AND t_day = ?;"))
			{
				int key = delist.getKey();
				String todo = delist.getTodoMain();
				String memo = delist.getMemo();
				Date date = delist.getDueDate();
				
				//DELETE文を準備
				preStatement.setInt(1, key);
				preStatement.setString(2, todo);
	            preStatement.setString(3, memo);
	            preStatement.setDate(4, date);
				
				//DELETE文の実行
				preStatement.executeUpdate();
				
			}catch(SQLException e) {
				e.printStackTrace();
		}
	}
	
	public void updateRow(ToDoList editlist) {
		//todoリスト編集時用
		
		//JDBCドライバを読み込む
		try{
			Class.forName("org.postgresql.Driver");
		}catch(ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}
			
		//postgreSQLへ接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL);
			PreparedStatement preStatement = conn.prepareStatement("UPDATE todo SET t_todo = ?, t_memo = ?, t_day = ? WHERE t_num = ?;"))
		{
			int key = editlist.getKey();
			String todo = editlist.getTodoMain();
			String memo = editlist.getMemo();
			Date date = editlist.getDueDate();
			
			//UPDATE文を準備
			preStatement.setString(1, todo);
            preStatement.setString(2, memo);
            preStatement.setDate(3, date);
            preStatement.setInt(4, key);
			
			//UPDATE文の実行
			preStatement.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
	}
	}
}
