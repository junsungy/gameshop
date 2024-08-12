<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Login</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<script src="/webgame/static/js/jquery-3.7.1.min.js"></script>
<script>
$(function(){
	$("form").on("submit", f);
	$("#username").val(localStorage.getItem("username"));
	$("#password").val(localStorage.getItem("password"));
	var checkStatus = localStorage.getItem("checkStatus");
	if (checkStatus == 1){
		$("#remember").prop("checked", true);
	}
});
function f(){
	var check = $("#remember").prop("checked");
	if(check){
		localStorage.setItem("username", $("#username").val());
		localStorage.setItem("password", $("#password").val());
		localStorage.setItem("checkStatus", 1);
	}else {
		localStorage.removeItem("username");
		localStorage.removeItem("password");
		localStorage.removeItem("checkStatus");
	}
}
</script>
<style>
	body {
		background-image: url('/webgame/static/images/loginBackground.png');
		background-size: cover;
	}

	h2, div.container.mt-3 {
		background-color: #333!important;
		color: white!important;
	}
	input {
		background-color: #555!important;
		color: white!important;
		border-color: #777!important;
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
	
	input::placeholder {
		color: #ddd;
	}
	
	input::-webkit-input-placeholder {
		color:#ddd;
	}
	
	input:-ms-input-placeholder {
		color:#ddd;
	}
</style>
</head>
<body>
<jsp:include page="../common/loginHeader.jsp"></jsp:include>
<div class="container mt-3">
  <form action="login.do" method="post" class="loginBox">
  	<header>
		<h2>로그인</h2>
	</header>
    <div class="mb-3 mt-3">
      <label for="username">아이디:</label>
      <input type="text" class="form-control" id="username" placeholder="아이디를 입력하세요" name="username">
    </div>
    <div class="mb-3">
      <label for="password">비밀번호:</label>
      <input type="password" class="form-control" id="password" placeholder="비밀번호를 입력하세요" name="password">
    </div>
    <div class="form-check mb-3">
      <label class="form-check-label">
        <input class="form-check-input" type="checkbox" name="remember" id="remember"> 아이디 비밀번호 기억
      </label>
    </div>
    <button type="submit" class="btn btn-secondary">Login</button>
  </form>
</div>
</body>
</html>
