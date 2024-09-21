<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create New List</title>
</head>
<body>
<form action="CreateListServlet" method="post">
TODO：<input type="text" name="todoMain"><br>
メモ：<input type="text" name="memo"><br>
期日：<input type="date" name="dueDate"><br>
<input type="submit" value="作成">
</form>
</body>
</html>