<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>リスト削除</title>
</head>
<body>
<table border="1">
<tr>
    <th>内容</th>
    <th>メモ</th>
	<th>期日</th>
</tr>
<tr>
	<td><c:out value="${delist.todoMain}" /></td>
	<td><c:out value="${delist.memo}" /></td>
	<td><c:out value="${delist.dueDate}" /></td>
</tr>
</table>
<p>このリストを削除しますか？</p>
<form action="ListServlet" method="post">
	<input type="hidden" name="action" value="delete" />
	<input type="submit" value="削除">
</form>
</body>
</html>