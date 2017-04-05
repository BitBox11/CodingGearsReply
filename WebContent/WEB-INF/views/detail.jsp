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



	<c:forEach items="${list2}" var="list">
		<h2>글번호 :${list.bno}</h2>
		<h2>제목: ${list.title}</h2>
		<h2>작성자: ${list.writer}</h2>
		<h2>작성일 :${list.regdate}</h2>

		<h2>---------------------------------------</h2>
		<h2>답글</h2>
		<form method="post">
			<input type="hidden" name="gno" value="${list.gno}"> <input
				type="hidden" name="gord" value="${list.gord+1}"> <input
				type="hidden" name="parent" value="${list.bno}">
			<div>
				<h2>
					제 목<input type="text" name="title">
				</h2>
			</div>

			<div>
				<h2>
					내 용<input type="text" name="content">
				</h2>
			</div>

			<div>
				<h2>
					작성자<input type="text" name="writer">
				</h2>
			</div>

			<button>등록</button>
		</form>

	</c:forEach>

	</br>










</body>
</html>