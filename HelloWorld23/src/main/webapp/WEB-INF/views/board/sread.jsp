<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js" type="text/javascript"></script>
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
			<div class="form-group">
				<label for="title">글제목</label> <input class="form-control" readonly
					value="${vo.title }" id="title" name="title">
			</div>
		</div>
	</div>
	<div class="container">
		<div class="row">
			<div class="form-group">
				<label for="writer">작성자</label> <input class="form-control" readonly
					value="${vo.writer }" id="writer" name="writer">
			</div>
		</div>


		<div class="row">
			<div class="form-group">
				<label for="content">글내용</label>
				<textarea class="form-control" readonly id="content" name="content">
			${vo.content }
			</textarea>
			</div>
		</div>
		<div class="form-group">
			<button id="update" class="btn btn-warning">수정</button> 
			<button id="delete" class="btn btn-danger">삭제</button> 
			<button id="list" class="btn btn-info">목록</button>
		</div>
	</div>


	<form>
		<input value="${vo.bno}" name="bno" type="hidden">
		<input value="${cri.page }" name="page" type="hidden" >
		<input value="${cri.perPage}" name="perPage" type="hidden" >
		<input value="${cri.searchType}" name="searchType" type="hidden" >
		<input value="${cri.keyword}" name="keyword" type="hidden" >
	</form>
	a href로 데이터 보내는 것과
	폼테그로 데이터 보내는 것의 방법상 차이
	
	<script type="text/javascript">
		$(document).ready(function() {
			$("#update").click(function(){
				$("form").attr("action", "supdateui");
				$("form").attr("method", "get");
				$("form").submit();
			})
			$(".btn-danger").click(function(){
				$("form").attr("action", "sdelete");
				$("form").attr("method", "post");
				$("form").submit();
			})
			$("#list").click(function(){
				$("form")
				.attr("action", "search")
				.attr("method", "get")
				.submit();
			})
		});
	</script>
</body>
</html>