<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>This is MVC</title>
<link rel="stylesheet" href="css/style.css" type="text/css" />
<script src="js/jquery-3.4.1.min.js"></script>
</head>
<body>
	<!-- header -->
	<header>
		<h1 style="color: #fdfdfd">Hello MVC</h1>
		<div class="login-container">
			<table id="logged-in">
				<tr>
					<td>님, 환영합니다.</td>
				</tr>
				<tr>
					<td><button id="button" onclick="location.href='views/login.jsp'">로그인하러 가기</button></td>
				</tr>
			</table>
		</div>
		
		<!-- 메인메뉴 -->
		<nav>
			<ul class="main-nav">
				<li class="home"><a href="#">Home</a></li>
				<li id="notice"><a href="#">공지사항</a></li>
				<li id="board"><a href="#">게시판</a></li>
				<li id="gallary"><a href="#">사진게시판</a></li>
			</ul>
		</nav>
	</header>



	<!-- section -->
	<section id="content">
		<h2 align='center' style="margin-top: 200px">내가 MVC가 뭔지 알려주지!!!</h2>
	</section>




	<!-- footer -->
	<footer>
		<p>&lt;Copyright 2019.</p>
		<Strong>KH정보교육원</Strong>. All rights reserved.&gt;
	</footer>
</body>
</html>