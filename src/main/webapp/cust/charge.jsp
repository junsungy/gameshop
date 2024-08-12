<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>지갑 충전</title>
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
	<h2>잔액 충전</h2>
	<form action="charge.do" method="post">
		<div class="mb-3 mt-3">
			<label>충전할 금액:</label>
			<input type="number" name="amount" value="0" required>
		</div>
		<input type="submit" value="충전" class="btn btn-secondary">
	</form>
</div>
</body>
</html>