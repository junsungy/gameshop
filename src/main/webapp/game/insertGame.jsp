<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게임 추가</title>
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
  <form action="insertGame.do" method="post" enctype="multipart/form-data">
  	<header>
		<h2>게임 추가</h2>
	</header>
    <div class="mb-3 mt-3">
      	<label>게임 이름</label>
		<input type="text" class="form-control" name="gameName" required>
    </div>
    <div class="mb-3 mt-3">
      	<label>대체 이름</label>
		<input type="text" class="form-control" name="altName" placeholder="여러 개인 경우 ,로 구분">
    </div>
    <div class="mb-3 mt-3">
      	<label>가격</label>
		<input type="number" class="form-control" name="price" required>
    </div>
    <div class="mb-3 mt-3">
      	<label>장르</label>
		<input type="text" class="form-control" name="genre" placeholder="여러 개인 경우 ,로 구분">
    </div>
    <div class="mb-3 mt-3">
      	<label>제작사</label>
		<input type="text" class="form-control" name="maker" placeholder="영어 대문자로 작성">
    </div>
    <div class="mb-3 mt-3">
      	<label>발매일</label>
		<input type="date" class="form-control" name="rdate" required>
    </div>
    <div class="mb-3 mt-3">
      	<label>썸네일</label>
		<input type="file" class="btn btn-secondary" name="thumbnail"><br>
    </div>
    <input type="submit" class="btn btn-secondary" value="추가">
  </form>
</div>
</body>
</html>