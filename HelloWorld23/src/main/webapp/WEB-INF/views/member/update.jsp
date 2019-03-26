<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>회원정보 수정</h1>
	<form action="/member/update" method="post">
		id : <input name="id" readonly value="${dto.id }"><br>
		이름 : <input name="name" value="${dto.name }"><br>
		나이 : <input type="number" name="age" value="${dto.age }"><br>
		<input type="submit" value="수정">
	</form>
</body>
</html>