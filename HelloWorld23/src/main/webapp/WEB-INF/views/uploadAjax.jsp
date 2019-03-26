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
<script
   src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>

<style type="text/css">
	.fileDrop{
		width: 100%;
		height: 200px;
		border: 1px dotted blue;
	}
	small{
	margin=left:3px;
	color:red;
	cursor:pointer;
	}

</style>

<title>Insert title here</title>
</head>
<body>


<div class="container">
	<div class="row">
		
			<div class="fileDrop">
				
			</div>
			
			<div class="uploadedList">
						
			</div>
	</div>
</div> 



<script type="text/javascript">
	$(document).ready(function(){
		/* 왜 어떤 경우에는 .click을 사용하고 또 어떤 경우에는 .on(click)으로 사용하는가? */ 
		$(".uploadedList").on("click", "small", function(event){
			var $that=$(this);
			
			
			/* '$'의 의미. 태그를 가르킴 ?!*/
			$.ajax({
				url:"deletefile",
				type:"post",
				/* 일반컨트롤러 어노테이션을 받았기 때문에 type에 post와 get밖에 넘겨받을 수 없다. */
				data:{
					fileName:$that.attr("data-src")
				},
				dataType:"text",
				success:function(result){
					alert("삭제되었습니다.");
					$that.parent("div").remove();
				}
			
				
			})
			
			/*더 공부하기 [JavaScript] 이벤트 핸들러 종류 */
		});
		
		
		$(".fileDrop").on("dragenter dragover", function(event){
			event.preventDefault();
			
		});
		
		$(".fileDrop").on("drop", function(event){
			event.preventDefault();
			
			var files = event.originalEvent.dataTransfer.files;
			
			
			
			for(var i=0;i<files.length;i++){
			
			var file = files[i];
			
			var formData=new FormData();
			formData.append("file", file);
			
			$.ajax({
				url:"/uploadAjax",
				type:"post", 
				data: formData, 
				dataType: "text",
				/* 쿼리로 안되게 막아놓음 */
				processData: false,
				/* 컨텐트 타입 변환되는걸 막음 */
				contentType: false,
				success:function(data){
					alert(data);
					var str="";
					/* 이미지 파일인지 일반 파일인지 구분 */
					if(checkImageType(data)){
							alert("이미지파일일 때 실행되는 부분");
						str="<div><a target='_blank' href='displayfile?fileName="+getImageLink(data)+"'><img src='displayfile?fileName="+data+"'/>"+getOriginalName(data)+"</a><small data-src='"+data+"'>x</small></div>";
					}else{
						  alert("일반파일일 때 실행되는 부분");
						str="<div><a href='displayfile?fileName="+data+"'><img style='width=100px;height=100px;' src='../resources/img/general.jpg'/>"+getOriginalName(data)+"</a><small data-src='"+data+"'>x</small></div>"
					}
					$(".uploadedList").append(str);
						
				}
			
			});
			
			}
		});
	});
	
	
	function getImageLink(fileName){
		if(checkImageType(fileName)){
			var prefix=fileName.substring(0,12);
			var suffix=fileName.substring(14);
			return prefix+suffix;
		}
		return null;
	}
	
	function checkImageType(fileName){/* 이미지파일인지 아닌지 구분하는 방법 1. 확장자, 2. 자바스크립트의 정규화식 */
		var pattern=/jpg|gif|png|jpeg/i; /* /i 대소문자 무시한다는 의미   Q:확장자명이 아닌 파일 이름 중에 저런거 있으면..?*/
		return fileName.match(pattern);
	}
	
	//업로드한 파일명칭 출력되게 하려고 구현했는데 data 그대로 가져다 쓰면 폴더명까지 다 나옴
	/* 이미지 파일은 두번째 언더바 이후가 파일명
	이미지가 아닌 일반파일은 첫번째 언더바 이후가 파일명 */
	function getOriginalName(fileName){
	
		var orgiginalName="";
		
		
		if(checkImageType(fileName)){
			/* var pre_idx = fileName.indexOf("_")+1;
			var name2 fileName.substring(pre_idx);
			var idx = name2.indexOf("_")+1; */
			var idx = fileName.indexOf("_",14)+1;
			originalName = fileName.substring(idx);
			
		}else{//원본 파일명에 _가 포함되어 있으면 정상 작동 안되는 문제가 있음
			var idx = fileName.indexOf("_")+1;
			originalName = fileName.substring(idx);
		}
		return originalName;
	}
</script>
</body>
</html>