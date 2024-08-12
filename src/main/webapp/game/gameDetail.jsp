<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게임 상세 정보</title>
<style>
	body {
		background-color: #222!important;
	}
	
	div.gameInfo > p {
		color: white;
		padding: 10px;
		display: inline;
	}
	
	h2 {
		color: white!important;
	}
	
	div.gameInfo > img {
		padding: 10px 10px 10px 50px ;
		height: 300px!important;
	}
	
	div.gameInfo{
		display: inline-block;
	}
</style>
<link rel="stylesheet" href="../static/css/gamelist.css">
</head>
<body>
<jsp:include page="../common/loginHeader.jsp"></jsp:include>
<h2 class="listTitle">${game.game_name}</h2>
<div class="gameInfo">
	<img src="../static/images/${game.thumbnail}" alt="${game.game_name}" height=200>
</div>
<div class="gameInfo">
	<fmt:parseNumber value="${game.price*(1-game.discount_rate)+((game.price*(1-game.discount_rate)%1>0.5)?(1-(game.price*(1-game.discount_rate)%1))%1:-(game.price*(1-game.discount_rate)%1))}" integerOnly="true" var="rPrice"/>
	<fmt:formatNumber value="${rPrice}" var="realPrice" type="currency" groupingUsed="true" currencySymbol="\\"/>
	<fmt:formatNumber value="${game.discount_rate }" type="percent" var="disc"/>
	<p>가격: </p>
	<c:if test="${game.discount_rate>0}">
		<p>${realPrice} (-${disc})</p>
	</c:if>
	<c:if test="${game.discount_rate==0}">
		<p>${realPrice}</p>
	</c:if>
	<br>
	<p>제작사: ${game.maker}</p><br>
	<p>장르: ${game.genre}</p><br>
	<p>발매일: ${game.release_date}</p><br>
	<c:if test="${purchased == 0}">
		<button class="btn btn-secondary" onclick="location.href='buy.do?gameid=${game.game_id}'">구매</button>
	</c:if>
	<c:if test="${purchased == 1}">
		<c:if test="${game.release_date <= today }">
	      	<button class="btn btn-secondary" onclick="location.href='/webgame/download.do?filename=${game.thumbnail}'">다운로드</button>
	    </c:if>
	    <c:if test="${game.release_date > today }">
	    	<p>아직 발매되지 않은 상품입니다.</p>
	    </c:if>
	</c:if>
</div>
<br>
<h2 class="listTitle">${game.maker}의 다른 게임</h2>
<div id="wrapper">
	<c:forEach items="${gamelist}" var="game2">
		<c:if test="${game.game_id != game2.game_id }">
	    <div class="game">
	      <figure>
	        <a href="gameDetail.go?gameid=${game2.game_id}">
	        	<img src="${pageContext.request.servletContext.contextPath}/static/images/${game2.thumbnail}" alt="${game2.thumbnail}" height=200>
	        </a>
	      </figure>
	      <p>이름: <a href="gameDetail.go?gameid=${game2.game_id}" style="color:white;text-decoration:none;">${game2.game_name}</a></p>
	      <p>제작사: ${game2.maker}</p>
	      <fmt:parseNumber value="${game2.price*(1-game2.discount_rate)+((game2.price*(1-game2.discount_rate)%1>0.5)?(1-(game2.price*(1-game2.discount_rate)%1))%1:-(game2.price*(1-game2.discount_rate)%1))}" integerOnly="true" var="rPrice"/>
	      <fmt:formatNumber value="${rPrice}" var="realPrice" type="currency" groupingUsed="true" currencySymbol="\\"/>
	      <fmt:formatNumber value="${game2.discount_rate }" type="percent" var="disc"/>
	      <c:if test="${game2.discount_rate>0}">
	      	<p>가격: ${realPrice} (-${disc})</p>
	      </c:if>
	      <c:if test="${game2.discount_rate==0}">
	      	<p>가격: ${realPrice}</p>
	      </c:if>
	    </div>
	    </c:if>
	 </c:forEach>
</div>
</body>
</html>