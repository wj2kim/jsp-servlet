<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.kh.member.model.vo.Member" %>
<%
	Member loginMember=(Member)session.getAttribute("loginMember");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>HelloMVC</title>
<link rel="stylesheet" href="css/style.css" type="text/css"/>
<script src="js/jquery-3.4.1.min.js"></script>
</head>
<body>
	<!-- header -->
		<header>
			<h1 style="color:#fdfdfd">Hello MVC</h1>
			<% if(loginMember!=null){%>
							<div class="login-container">
					<table id="logged-in">
						<tr>
							<td><%=loginMember.getUserName()%>님, 환영합니다.</td>
						</tr>
						<tr>
							<td>
								<input type="button" value="내정보보기">
								<input type="button" value="logout" onclick="location.href='<%=request.getContextPath()%>/logout';"> 
								<!-- 항상 절대경로(absolute으로 써야한다) -->
							</td>
						</tr>
					</table>
				</div>
			<% }else{ %>
			<div class="login-container">
				<form action="<%= request.getContextPath()%>/login" method="post"
				onsubmit="return validate();">
				<table>
					<tr>
						<td ><input type="text" name="userId" id="userId" placeholder="아이디 입력"></td>
						<td></td>
					</tr>
					<tr>
						<td><input type="password" name="password" id="password" placeholder="비밀번호 입력"></td>
						<td><input type="submit" value="로그인"></td>
					</tr>
					<tr>
						<td colspan="2"><input type="checkbox" name="saveId" id="saveId"><label for="saveId">아이디 저장</label>
						&nbsp;&nbsp;<input type="button" value="회원가입" onclick=""></td>
					</tr>
				</table>
			</form>
			</div>	


			<%} %>
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
		<script>
			function validate(){
				if($("#userId").val().trim().length<4){
					alert("아이디는 4글자 이상 입력하세요.");
					$("#userId").focus();
					return false;
				}
				if($("#password").val().trim().length==0){
					alert("비밀번호를 입력하세요.");
					$("#password").focus();
					return false;
				}
				return true;
			}
		</script>