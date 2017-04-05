<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<form method="post">
	<button >새글쓰기</button>
	</form>

	<c:forEach items="${list}" var="list">
		<h2><a href="/detail?bno=${list.bno}"> <c:forEach begin="1" end="${list.gord}">re:</c:forEach>  글번호: ${list.bno}제목: ${list.title}  작성자:${list.writer} 작성일: ${list.regdate}</h2>
	</c:forEach>



	<div class="pagination">
	<ul>
	
		<c:if test="${pager.prev}">
			<li><a href='board?pageNum=${pager.start -1 }'> << </a></li>
		</c:if>

		<c:forEach begin="${pager.start}" end="${pager.end }" var="num">
			<li><a href="board?pageNum=${num}">${num}</a></li>
		</c:forEach>

		<c:if test="${pager.next}">
			<li><a href='board?pageNum=${pager.end +1 }'> >> </a></li>
		</c:if>
	</ul>
	</div>

<style>
.pagination ul li {
list-style: none;
float: left;
margin : 1px;
width: 18px;
text-align: center;

}
a{
text-decoration: none;
}
important { background-color: black;}

</style>



</body>
</html>