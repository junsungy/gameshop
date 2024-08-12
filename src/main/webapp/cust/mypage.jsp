<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="../static/css/gamelist.css">
<title>마이 페이지</title>
<style>
	h2, p {
		color: white!important;
		padding: 10px;
	}
	
	p.info {
		display: inline;
	}
	
	a.gameInfo {
		color: white!important;
		text-decoration: none!important;
	}
</style>
</head>
<body>
<jsp:include page="../common/loginHeader.jsp"></jsp:include>
<h2>마이페이지</h2>
<p class="info">잔액 : ${userInfo.credit}</p>
<button class="btn btn-secondary" onclick = "location.href='charge.do'">잔액 충전</button>
<h2>보유 게임 목록</h2>
<div id="wrapper">
	<c:forEach items="${games}" var="game">
	    <div class="game">
	      <figure>
	        <a href="../game/gameDetail.go?gameid=${game.game_id}">
	        	<img src="${pageContext.request.servletContext.contextPath}/static/images/${game.thumbnail}" alt="${game.thumbnail}" height=200>
	        </a>
	      </figure>
	      <p>이름 : <a class="gameInfo" href="../game/gameDetail.go?gameid=${game.game_id}">${game.game_name}</a></p>
	      <p>제작사 : ${game.maker}</p>
	      <p>장르 : ${game.genre}</p>
	      <button class="btn btn-secondary" onclick="location.href='refund.do?gameid=${game.game_id}'">환불</button>
	      <c:if test="${game.release_date <= today }">
	      	<button class="btn btn-secondary" onclick="location.href='../download.do?filename=${game.thumbnail}'">다운로드</button>
	      </c:if>
	    </div>
	 </c:forEach>
</div>
</body>
</html>