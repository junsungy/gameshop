<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<title>회원가입</title>
<script src="/webgame/static/js/jquery-3.7.1.min.js"></script>
<script>
$(function(){
	$("form").on("submit", call);
	$("#btnDupChk").on("click",f_chk);
});

function f_chk(){
	var username = $("#username").val();
	if(username==""){
		alert("아이디를 입력하세요");
		document.querySelector("#username").focus();
		return;
	}
	$.ajax({
		url:"usernameChk.go",
		type:"get",
		data:{"username":username},
		success:function(responseData){
			var msg = "";
			if(responseData=="0"){
				msg = "사용 가능한 아이디입니다."
			}else {
				msg="이미 존재하는 아이디입니다.";
				$("#username").val("");
				document.querySelector("#username").focus();
			}
			$("#validUsername").text(msg);
		}
	})
}

function call(){
	if ($("#pw").val() != $("#pwChk").val()){
		event.preventDefault();
		$("#invalidPw").text("비밀번호가 일치하지 않습니다.");
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
	<h2>회원가입</h2>
	<form action="signup.go" method="post">
		<div class="mb-3 mt-3">
			<label for="username">아이디:</label>
			<input class="form-control" id="username" name="username" placeholder="아이디를 입력하세요" required>
			<button id="btnDupChk" class="btn btn-secondary">중복 확인</button><p id="validUsername">중복 확인이 필요합니다.</p>
		</div>
		<div class="mb-3">
			<label>비밀번호: </label>
			<input class="form-control" type="password" id="pw" name="pw" placeholder="비밀번호를 입력하세요" required><br>
			<label>비밀번호 확인: </label>
			<input class="form-control" type="password" id="pwChk" name="pwChk" placeholder="비밀번호를 다시 입력하세요" required>
			<p id="invalidPw">비밀번호를 확인합니다.</p>
		</div>
		<input type="submit" value="계정 생성" class="btn btn-secondary">
	</form>
</div>
</body>
</html>