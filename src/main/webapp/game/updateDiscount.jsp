<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>할인율 조정</title>
<style>
	body {
		background-image: url('/webgame/static/images/loginBackground.png');
		background-size: cover;
	}
	
	table {
		color: white;
		border-color: #111;
		margin-left: 30px;
		border-collapse: collapse;
	}
	
	th, td {
		padding: 10px;
	}
	
	thead {
		background-color: #222;
		text-align: center;
	}
	
	tbody {
		background-color: #333;
	}
	
	h2 {
		color: white!important;
		padding: 10px 30px;
	}
	
	form.info {
		padding: 10px 30px;
	}
	
	label {
		color: white;
	}
	
	input {
		background-color: #222;
		color: white;
	}
</style>
<script src="/webgame/static/js/jquery-3.7.1.min.js"></script>
<script>
function f(ths){
	var gameId = $(ths).text();
	$("#gameId").val(gameId);
}
</script>
</head>
<body>
<jsp:include page="../common/loginHeader.jsp"></jsp:include>
<h2>할인율 조정</h2>
<form class="info" action="updateDiscount.do" method="post">
	<label>id</label>
	<input type="number" id="gameId" name="gameId" required>
	<label>할인율</label>
	<input type="number" name="dRate" min="0" max="1" step="0.01" required>
	<input type="submit" value="수정">
</form>
<table border="1">
	<thead>
		<tr>
			<th>id</th>
			<th>이름</th>
			<th>제작사</th>
			<th>장르</th>
			<th>가격</th>
			<th>할인율</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${games }" var="game">
			<tr>
				<td>
					<a href="#" onclick="f(this)" style="color:white;text-decoration:none;">${game.game_id }</a>
				</td>
				<td>${game.game_name }</td>
				<td>${game.maker }</td>
				<td>${game.genre }</td>
				<td>${game.price }</td>
				<td>${game.discount_rate }</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
</body>
</html>