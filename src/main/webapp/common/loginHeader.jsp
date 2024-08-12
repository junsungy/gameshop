<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Header</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<style>
	button {
		margin: 5px 10px 5px 10px;
	}
	
	h2.listTitle {
		padding: 1rem;
	}
	#search {
		margin-right: 20px;
	}
</style>
<body>
<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
  <div class="container-fluid">
    <a class="navbar-brand" href="/webgame">GAME SHOP</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#collapsibleNavbar">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="collapsibleNavbar">
      <ul class="navbar-nav">
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown">게임</a>
          <ul class="dropdown-menu">
            <li><a class="dropdown-item" href="/webgame/game/gameAll.go">모든 게임</a></li>
            <li><a class="dropdown-item" href="/webgame/game/gameDiscount.go">할인 중인 게임</a></li>
          </ul>
        </li>
        <c:if test="${loginCust.is_admin == 1 }">
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown">관리자 메뉴</a>
          <ul class="dropdown-menu">
            <li><a class="dropdown-item" href="/webgame/game/insertGame.do">게임 추가</a></li>
            <li><a class="dropdown-item" href="/webgame/game/updateDiscount.do">할인율 조정</a></li>
          </ul>
        </li>
        </c:if>
      </ul>
    </div>
    <form class="d-flex">
        <c:if test="${empty loginCust}">
			<button class="btn btn-secondary" type="button" onclick="location.href='/webgame/auth/login.do'">Login</button>
        	<button class="btn btn-secondary" type="button" onclick="location.href='/webgame/auth/signup.go'">SignUp</button>
		</c:if>
		<c:if test="${not empty loginCust}">
			<a class="myName" href="/webgame/cust/mypage.do?username=${loginCust.username}" style="color:white;line-height:50px">${loginCust.username}님 환영합니다</a>
			<button class="btn btn-secondary" type="button" onclick="location.href='/webgame/auth/logout.go'">Logout</button>
		</c:if>
      </form>
  </div>
</nav>
</body>
</html>
