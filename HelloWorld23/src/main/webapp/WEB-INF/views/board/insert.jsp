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
		<form action="/board/insert" method="post">
			<div class="form-group">
				<label for="title">제목</label>
				<input required class="form-control" id="title" name="title">
				
			</div>
			<div class="form-group">
				<label for="writer">작성자</label>
				<input required class="form-control" id="writer" name ="writer">
			</div>
			
			<div class="form-group">
				<label for="form-group">내용</label>
				<textarea required class="form-control" id="content" name="content" ></textarea>
				
			</div>
		</form>	
		
		<div class="form-group">
			<label>업로드할 파일을 드랍시키세요.</label>
			<div class="fileDrop"></div>
		</div>
		
		<ul class="uploadedList clearfix">
			
		</ul>
				
		
		<div class="form-group">
			<button class="btn z" id="a" type="submit">등록</button>	
			<button class="btn z" id="b" type="submit">등록</button>
			<button class="btn" id="c" type="submit">등록</button>
			<button class="btn" id="d" type="submit">등록</button>
			<button class="btn" id="e" type="submit">등록</button>
			<button class="btn" id="f" type="submit">등록</button>
			<button class="btn" id="g" type="submit">등록</button>
			<button id="h" type="submit">등록</button>
			
		</div>

		
	</div>
</div> 

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
	
	var source=$("#source").html();
	var template =Handlebars.compile(source);
	
	$(document).ready(function(){
		
		$(".uploadedList").on("click", ".delbtn", function(event){
			event.preventDefault();
			var $delBtn=$(this);
			var $delLi=$(this).parent("div").parent("li");
			/* 제이쿼리에서 변수에 $ 있으면 엘리먼트를 뜻함 */
			
			
			$.ajax({
				url:"/deletefile",
				type:"post",
				/* 일반컨트롤러 어노테이션을 받았기 때문에 type에 post와 get밖에 넘겨받을 수 없다. */
				data:{
					fileName:$delBtn.attr("href")
				},
				dataType:"text",
				success:function(result){
					$delLi.remove();
				}
			
				
			})
			
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
		
		$("button[type='submit']").click(function(){
			
			event.preventDefault();
			var $form=$("form");
			var str="";
			
			$(".delbtn").each(function(index){
				str+="<input name='files["+index+"]' value='"+$(this).attr("href")+"' type='hidden'/>";
			});
			$form.append(str);
			$form.get(0).submit();
			
			//$("form").submit();
			
			/* alert("zzzz"); */
			/* $("#a")
			$("#c")
			$(".btn z")
			$("button[type='submit']")
			$("input[type='text']) */
		})
	});
</script>
선택자 사용방법
1. 아이디 선택 #
2. 클래스 선택 .
3. [] 속성값 type이 "~"인 것을 지칭(반드시 type이라는 속성이 명기되어 있어야 한다.)
input 태그는 type값 입력하지 않으면 디폴트값 text로 지정되지만, 
선택자를 이용할 때에는 type="text"로 호출해도 해당 input태그 안에 type값이 text라고 명기되어있지 않으면 선택하지 못함.

$("button[type='submit'])
버튼 테그들 중에서 type이 'submit'인 것을 선택한다는 의미
</body>
</html>