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
		
	</div>
</div> 



<script type="text/javascript">
	$(document).ready(function(){
		var result="${savedName}";//스크립트 안에서 el태그 사용할 때에 ""쌍따옴표로 묶어야 한다.
		parent.addFilePath(result);//더 알아보가 자바스크립트 자식창에서 parent function 호출
	});
</script>
</body>
</html>