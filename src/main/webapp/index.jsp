<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게임 구매 시스템</title>
<script src="/webgame/static/js/jquery-3.7.1.min.js"></script>
<script>
$(function(){
	$.ajax({
		type:"get",
		url:"game/popular.go",
		success:function(responseData){
			$("#popular").html(responseData);
		}
	});
});
</script>
<style>
	#search {
		background-image: url('static/images/background.jpg');
		background-attachment: fixed;
		height: 300px;
		text-align: center;
	}
	
	form {
		line-height: 300px;
		text-align: center!important;
	}
</style>
</head>
<body>
<jsp:include page="common/loginHeader.jsp"></jsp:include>
<div id="search">
	<form action="game/search.go">
		<input id="searchItem" name="searchItem" placeholder="검색할 게임의 이름, 제작사, 장르를 입력하세요" style="width:30%;height:40px;min-width:400px;">
		<input type="submit" class="btn btn-secondary" value="검색">
	</form>
</div>
<div class="listHeader">
	<h2 class="listTitle">인기 게임</h2>
	<div id="popular"></div>
</div>
</body>
</html>