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
		<!-- mid
		sender
		targetId
		message
		
		<form action="aop/create" method="post">
			<div class="form-group">
			메세지 번호<input name="mid" type="text"><br>
			보내는 사람<input name="sender" type="text"><br>
			받는 사람<input name="targetId" type="text"><br>
			메시지<textarea name="message" rows="5" ></textarea>
			</div>
			<button></button>
		</form> -->
		
		<form action="create" method="post">
			<div class="form-group">
			<label for="mid">MID</label>
			<input class="form-control" name="mid" id="mid">
			</div>
			<div class="form-group">
			<label for="sender">발신자</label>
			<input class="form-control" name="sender" id="sender">
			</div>
			<div class="form-group">
			<label for="targetId">수신자</label>
			<input class="form-control" name="targetId" id="targetId">
			</div>
			<div class="form-group">
			<label for="message">메시지</label>
			<input class="form-control" name="message" id="message">
			</div>
			<div class="form-group">
			<button class="btn-info" type="submit">메시지 전송</button>
			</div>
		</form>
	</div>
</div> 


<script type="text/javascript">
	$(document).ready(function(){
		$("button").click(function(){
			$("form").submit();
		});
	});
</script>
</body>
</html>