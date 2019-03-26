function checkImageType(fileName){
	var pattern=/jpg|gif|png|jpeg/i;
	return fileName.match(pattern);
}

function getFileInfo(data){
	var imgsrc, getLink, fileName, fullName, target;
	
	
	fullName=data;
	
	if(checkImageType(data)){
		imgsrc="/displayfile?fileName="+data;
		/*data 는 우측과 같은 형태로 들어온다 /2019/03/22/s_uuidxxxxx_aa.png
		이를 이용하여 prefix와 suffix를 가공*/
		var prefix=data.substring(0,12);
		var suffix=data.substring(14);
		getLink="/displayfile?fileName="+(prefix+suffix);
		
		var idx=data.indexOf("_",14);
		/*14번째 문자 이후에 나온는 _의 인덱스 번호 반환*/
		
		fileName=data.substring(idx+1);
		target="_blank";
		
	}else{
		imgsrc="/resources/img/general.jpg";
		getLink="/displayfile?fileName="+data;
		var idx=data.indexOf("_");
		/*일반문서는 앞에 s_라는 문자열 추가하여 저장하지 않았기 때문에 */
		fileName=data.substring(idx+1);
		
		
	}
	return {imgsrc:imgsrc, getLink:getLink, fileName:fileName, fullName:fullName, target:target};
	/*{속성명 : 속성값}*/
}