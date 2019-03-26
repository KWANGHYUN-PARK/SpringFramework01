<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>

<title>Insert title here</title>
</head>
<body>


<div class="container">
	<div class="row">
		로그인 정상 여부 판별하여 화면 이동하는 방법 1 20190325
		<%-- <c:if test="${empty userVO}">
			<c:redirect url="/user/login"></c:redirect>			
		</c:if>
		<c:if test="${!empty userVO}">
			<c:redirect url="/"></c:redirect>			
		</c:if> --%>
		
	</div>
</div> 


/user/loginpost.jsp
로그인 실패했을 때
	-> 로그인 화면으로 가도록 함
로그인 성공했을 때
	-> / 로 이동하도록

<script type="text/javascript">
	$(document).ready(function(){
		/* if(${userVO==null}){
			self.location="/user/login";
		} */
		
		
		/* 로그인 정상 여부 판별하여 화면 이동하는 방법 2 20190325
		if(${userVO==null}){
			self.location="/user/login";
		}else{
			self.location="/";
		} */
	});
</script>
</body>
</html>