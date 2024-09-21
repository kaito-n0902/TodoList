<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>リスト編集</title>
</head>
<body>
<form action="ListServlet" method="post">
	<table border="1">
	<tr>
	    <th>内容</th>
	    <th>メモ</th>
		<th>期日</th>
	</tr>
	<tr>
		<td>
			<input type="hidden" name="key" value="${delist.key }" />
		</td>
		<td>
			<input type="text" name="todoMain" value="${delist.todoMain}" />
		</td>
		<td>
			<input type="text" name="memo" value="${delist.memo}" />
		</td>
		<td>
			<input type="text" name="dueDate" value="${delist.dueDate}" />
		</td>
		<td>
			<input type="hidden" name="action" value="edit" />
			<input type="submit" value="編集完了">
		</td>
	</tr>
	</table>
</form>
</body>
</html>
