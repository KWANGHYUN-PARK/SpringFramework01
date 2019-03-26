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
<h1></h1>
ID: ${dto.num}<br>
TNAME:  ${dto.tname}<br>
TDAY:  ${dto.tday}<br>

<a href="/test/updateui?num=${dto.num }">수정</a><br>
<a href="/test/delete?num=${dto.num }">삭제</a><br>
<a href="/test/list">목록</a><br>
</body>
</html>