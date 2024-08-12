<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>구매 결과</title>
<style>
	body {
		background-image: url('/webgame/static/images/loginBackground.png');
		background-size: cover;
	}

	h2, div.container.mt-3 {
		background-color: #333!important;
		color: white!important;
	}
	
	div.container.mt-3 {
		display:grid;
		grid-template-columns: 400px;
		place-content: space-evenly;
		background-color: #333!important;
		min-width: 440px!important;
		max-width: 440px!important;
		border-radius: 3%;
		padding: 20px;
	}
	
	header {
		font-size:1.5rem;
  		padding:0.5rem;
	}
</style>
</head>
<body>
<jsp:include page="../common/loginHeader.jsp"></jsp:include>
<div class="container mt-3">
  	<header>
		<h2>구매 결과</h2>
	</header>
    <c:if test="${result > 0}">
		<p>구매하였습니다.</p>
		<button class="btn btn-secondary" onclick="location.href='/webgame/cust/mypage.do?username=${loginCust.username}'">마이 페이지로 이동</button>
	</c:if>
	<c:if test="${result == 0}">
		<p>이미 구매하신 상품이거나, 잔액이 부족합니다.</p>
	</c:if>
</div>
</body>
</html>