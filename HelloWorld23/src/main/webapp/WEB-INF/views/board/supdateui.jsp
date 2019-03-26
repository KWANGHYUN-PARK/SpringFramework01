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

<!-- 
부트스트랩의 기본
<div class="container">
	<div class="row">
	
	</div>
</div> 
-->


<div class="container">
	<div class="row">
		<form action="/board/supdate" method="post">
			<div class="form-group" >
				<label for="bno">글번호</label>
				<input id ="bno" class="form-control" readonly name="bno" value="${vo.bno }">	
			</div>
			<div class="form-group" >
				<label for="writer">작성자</label>
				<input id ="writer" class="form-control" name="writer" value="${vo.writer }">
				
			</div>
			<div class="form-group" >
				<label for="title">제목</label>
				<input id ="title" class="form-control" name="title" value="${vo.title }">
				
			</div>
			
			<div class="form-group" >
				<label for="content">내용</label>
				<input id ="content" class="form-control" name="content" value="${vo.content }">
				
			</div>
			
			<!-- 수정하기 눌렀을 때 수정한 글이 있더 페이지로 되돌아갈 수 있도록 하기 위해, read.jsp에서 보내는 값 받아오는 부분-->
			<input value="${cri.page }" name ="page" type=hidden>
			<input value="${cri.perPage }" name ="perPage" type=hidden>
			<input value="${cri.searchType}" name="searchType" type="hidden" >
			<input value="${cri.keyword}" name="keyword" type="hidden" >
			
		</form>
		<div class="form-group">
			<button type="submit" class="btn btn-warning">수정</button>
		</div>
	</div>
</div> 

<script type="text/javascript">
	$(document).ready(function(){
		$(".btn-warning").click(function(){
			/* $("form").attr("action", "updateui");
			$("form").attr("method", "get"); */
			/* $("button[type='submit']").click(function(){
				$("form").submit();
			}) */
			$("form").submit();
		})
	});
</script>
</body>
</html>
