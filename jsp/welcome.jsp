<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ToDoList</title>
</head>
<body>
<h1>リスト一覧</h1>
<table border="1">
	<tr>
    	<th>内容</th>
        <th>メモ</th>
        <th>期日</th>
    </tr>
	<c:forEach var="todolist" items="${todolist}">
  	<tr>
		<td><c:out value="${todolist.todoMain}" /></td>
        <td><c:out value="${todolist.memo}" /></td>
        <td><c:out value="${todolist.dueDate}" /></td>
        <td>
        <form action="ListServlet" method="get">
        	<input type="hidden" name="key" value="${todolist.key}" />
            <input type="hidden" name="todoMain" value="${todolist.todoMain}" />
            <input type="hidden" name="memo" value="${todolist.memo}" />
            <input type="hidden" name="dueDate" value="${todolist.dueDate}" />
            <input type="submit" name="action" value="削除" />
            <input type="submit" name="action" value="編集" />
        </form>
        </td>
	</tr>
    </c:forEach>
</table>
<p>
	<a href="CreateListServlet" id="create">新規作成</a>
</p>
</body>
</html>
