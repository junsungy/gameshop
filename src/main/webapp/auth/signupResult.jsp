<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입 완료</title>
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
		<h2>회원가입 완료되었습니다</h2>
	</header>
    <p>가입한 계정으로 로그인해주세요</p>
	<button class="btn btn-secondary" onclick="location.href='/webgame/auth/login.do'">Login</button>
</div>
</body>
</html>