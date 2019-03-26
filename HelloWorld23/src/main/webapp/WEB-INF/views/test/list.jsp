<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>${list} ${tvplist}
<br>

<a href="/test/insertui">글쓰기</a>
<table>
	<thead>
		<tr>
			<th>
			num
			</th>
			<th>
			tname
			</th>
			
		</tr>
	</thead>
		
	<tbody>
	<c:forEach items = "${list }" var="dto">
		<tr>
			<td>
			${dto.num }
			
			</td>
			<td>
			<a href="/test/selectByNum?num=${dto.num }">
			${dto.tname }
			</a>
			</td>
		</tr>
	</c:forEach>
	</tbody>

</table>
</body>
</html>