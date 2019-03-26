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


<script src="../../resources/js/upload.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>
<style type="text/css">
	.fileDrop{
	width:80%;
	height:100px;
	border:1px dotted gray;
	background-color:lightslategray;
	margin:auto;
	}
	.uploadedList li{
		list-style-type:none;
	}
</style>


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
		<form action="/board/update" method="post">
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
			
<script id="sourceAttach" type="text/x-handlebars-template">
	<li class="col-xs-3 pull-left">
		<span>
			<img src="{{imgsrc}}">
		</span>
		<div>
			<a href="{{getLink}}">{{fileName}}</a>
		</div>
	</li>
</script>
			
			<div class="form-group" >
				<label for="content">내용</label>
				<input id ="content" class="form-control" name="content" value="${vo.content }">
				
			</div>
			
			<!-- 수정하기 눌렀을 때 수정한 글이 있더 페이지로 되돌아갈 수 있도록 하기 위해, read.jsp에서 보내는 값 받아오는 부분-->
			<input value="${cri.page }" name ="page" type=hidden>
			<input value="${cri.perPage }" name ="perPage" type=hidden>
						
		</form>
		
		20190325
		<div class="form-group">
			<label>업로드할 파일을 드랍시키세요.</label>
			<div class="fileDrop"></div>
		</div>
		
		<ul class="uploadedList clearfix">
			
		</ul>
		
		
		<div class="form-group">
			<button type="submit" class="btn btn-warning">수정</button>
		</div>
	</div>
</div> <!-- class container end tag -->


20190325
<script id="source" type="text/x-handlebars-template">
<li class="col-xs-3">
<span>
	<img src="{{imgsrc}}">
	<!-- 핸들바스 마크업 작업 -->
</span>
<div>
	<a target="{{target}}" href="{{getLink}}">{{fileName}}</a>
	<a class="btn btn-danger btn-xs pull-right delbtn" href="{{fullName}}"><span class="glyphicon glyphicon-remove"></span></a>
</div>
</li>
</script>

<script type="text/javascript">
	/* 스크립트 태그 바로 아래 있는 변수는 var가 있는지 없는지 관계 없이 전역변수 */
	var bno="${vo.bno}";
	var source = $("#source").html();
	var template = Handlebars.compile(source);
	
	$(document).ready(function(){
		
		/*  */
		
		
		
		$(".uploadedList").on("click", ".delbtn", function(event){
			event.preventDefault();
			var $delBtn=$(this);
			var $delLi=$(this).parent("div").parent("li");
			/* 제이쿼리에서 변수에 $ 있으면 엘리먼트를 뜻함 */
			
			var delok = confirm("수정 버튼과 상관 없이 파일이 삭제됩니다.\n정말 삭제하시겠습니까?");
			
			if(delok){
			$.ajax({
				url:"/board/deletefile",
				type:"post",
				/* 일반컨트롤러 어노테이션을 받았기 때문에 type에 post와 get밖에 넘겨받을 수 없다. */
				data:{
					fileName:$delBtn.attr("href"),
					bno:bno
				},
				dataType:"text",
				success:function(result){
					$delLi.remove();
				}
			});
			}
			
			/*더 공부하기 [JavaScript] 이벤트 핸들러 종류 */
		});
		
		
		$(".fileDrop").on("dragenter dragover", function(event){
			event.preventDefault();
		});
		
		$(".fileDrop").on("drop",function(event){
			event.preventDefault();
			
			var files = event.originalEvent.dataTransfer.files;
			var file=files[0];
			
			var formData=new FormData();
			formData.append("file",file);
			
			$.ajax({
				url:"/uploadAjax",
				type:"post",
				data:formData,
				dataType: "text",
				processData: false,
				contentType: false,
				success:function(data){
					alert(data);
					alert(data.target);
					/* upload.js에서 선언한 함수 호출하여 사용  */
					var result = getFileInfo(data);
					
					$(".uploadedList").append(template(result));
				}
			});
		
		});
		
		
		
		
		
		
		$(".btn-warning").click(function(){
			/* $("form").attr("action", "updateui");
			$("form").attr("method", "get"); */
			/* $("button[type='submit']").click(function(){
				$("form").submit();
			}) */
			
			
			/* $("form").submit(); */
			
			var $form=$("form");
			var str="";
			$(".delbtn").each(function(index){
				str+="<input value='"+$(this).attr("href")+"' name='files["+index+"]' type='hidden'/>"
				
			});
			$form.append(str).attr("action","update")
			.attr("method", "post");
			
			$form.get(0).submit();
		});
		getAllAttach(bno);
	});

	

	function getAllAttach(bno){
  	  $.getJSON("/board/getAttach/"+bno, function(result){
  		/* var source = $("#source").html();
  		var template = Handlebars.compile(source); */
  		
  		$(result).each(function(){
  			var data = getFileInfo(this);
  			$(".uploadedList").append(template(data));
  		});
  		  
  		  
  	  });
    }
	
	
	
</script>



</body>
</html>
