<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- 
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>로그인페이지</title>
</head>
<body>
	<h1>Custom Login Page</h1>
	
	<h2><c:out value="${error}"/></h2>
	<h2><c:out value="${logout}"/></h2>
	
	<form method='post' action="${pageContext.request.contextPath}/login">
		<input type='text' name='username'><br>
		<input type='password' name='password'><br>
		<input type='checkbox' name='remember-me'> Remember Me <br>
		<input type='submit'><br>
		<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }" />
	</form>
	
	<input type="button" value="회원가입" onClick="location.href='${path}/tetris/login/memberJoin'">

</body>
</html>
 -->
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<meta name="keywords" content="" />
<meta name="description" content="" />
<link href="http://fonts.googleapis.com/css?family=Source+Sans+Pro:200,300,400,600,700,900" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/css/default.css" rel="stylesheet" type="text/css" media="all" />
<link href="${pageContext.request.contextPath}/css/fonts.css" rel="stylesheet" type="text/css" media="all" />

<!--[if IE 6]><link href="default_ie6.css" rel="stylesheet" type="text/css" /><![endif]-->

</head>
<body>
<div id="header-wrapper">
<div id="header" class="container">
	<!--
	<div id="logo">
		<h1><a href="#"><strong>shine-Tetris-Games</strong></a></h1>
	</div>
	 
	<div id="menu">
		<ul>
			<li class="current_page_item"><a href="#" accesskey="1" title="">Homepage</a></li>
			<li><a href="#" accesskey="2" title="">Our Clients</a></li>
			<li><a href="#" accesskey="3" title="">About Us</a></li>
			<li><a href="#" accesskey="4" title="">Careers</a></li>
			<li><a href="#" accesskey="5" title="">Contact Us</a></li>
		</ul>
	</div>
	 -->
</div></div>
<div id="page" class="container">
	<div id="content">
		<div class="titleText">
			<h2>Welcome to shineTetris</h2>
			<!-- 
			<span class="byline">Mauris vulputate dolor sit amet nibh</span> 
			 -->
		</div>
		<!-- 
		<a href="#" class="image image-full"><img src="images/pic01.jpg" alt="" /></a>
		<p>This is <strong>Mongoose </strong>, a free, fully standards-compliant CSS template designed by <a href="http://templated.co" rel="nofollow">TEMPLATED</a>. The photos in this template are from <a href="http://fotogrph.com/"> Fotogrph</a>. This free template is released under the <a href="http://templated.co/license">Creative Commons Attribution</a> license, so you're pretty much free to do whatever you want with it (even use it commercially) provided you give us credit for it. Have fun :) </p>
		 -->
		<div id="onecolumn">
			<div class="title">
				<h2>Login</h2>
			</div>
			<div>
				<form method='post' action="${pageContext.request.contextPath}/login">
					<input type='text' name='username' placeholder="ShineTetris ID를 입력해주세요" style="width: 65%"/><br>
					<input type='password' name='password' placeholder="ShineTetris 비밀번호를 입력해주세요" style="width: 65%; margin:5px 0"/><br>
					<input type='checkbox' name='remember-me' style="margin:5px 5px;"><labal style="font-size:12px; ">ID저장하기</labal> </input> 
					<input type="button" value="회원가입" onClick="location.href='${path}/tetris/login/memberJoin'" style="margin-left:26%">
					<br>
					<input type='submit' value="로그인" style="width: 67%; margin:8px 0"/>
					<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }" /> <br>
					
				</form>
			</div>
			
		</div>
	</div>
</div>
</body>
</html>
