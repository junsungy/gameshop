<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="/webgame/static/css/gamelist.css">
<title>전체 게임 목록</title>
</head>
<body>
<jsp:include page="../common/loginHeader.jsp"></jsp:include>
<div class="listHeader">
	<h2 class="listTitle">전체 게임 목록</h2>
	<div id="search">
		<form action="search.go">
			<input id="searchItem" name="searchItem" placeholder="게임 검색" style="width:20%;height:40px;">
			<input type="submit" class="btn btn-secondary" value="검색">
		</form>
	</div>
</div>
<div id="wrapper">
	<c:forEach items="${games}" var="game">
	    <div class="game">
	      <figure>
	        <a href="gameDetail.go?gameid=${game.game_id}">
	        	<img src="${pageContext.request.servletContext.contextPath}/static/images/${game.thumbnail}" alt="${game.thumbnail}" height=200>
	        </a>
	      </figure>
	      <p>이름: <a class="gameInfo" href="gameDetail.go?gameid=${game.game_id}">${game.game_name}</a></p>
	      <p>제작사: <a class="gameInfo" href="search.go?searchItem=${game.maker}">${game.maker}</a></p>
	      <fmt:parseNumber value="${game.price*(1-game.discount_rate)+((game.price*(1-game.discount_rate)%1>0.5)?(1-(game.price*(1-game.discount_rate)%1))%1:-(game.price*(1-game.discount_rate)%1))}" integerOnly="true" var="rPrice"/>
	      <fmt:formatNumber value="${rPrice}" var="realPrice" type="currency" groupingUsed="true" currencySymbol="\\"/>
	      <fmt:formatNumber value="${game.discount_rate }" type="percent" var="disc"/>
	      <c:if test="${game.discount_rate>0}">
	      	<p>가격: ${realPrice} (-${disc})</p>
	      </c:if>
	      <c:if test="${game.discount_rate==0}">
	      	<p>가격: ${realPrice}</p>
	      </c:if>
	    </div>
	 </c:forEach>
</div>
</body>
</html>