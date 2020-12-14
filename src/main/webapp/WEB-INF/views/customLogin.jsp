<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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

</head>
<body>
	<div class="main-container">
		<div id="page" class="container">
			<div class="login-content">
				<div class="login-form">
					<div class="LoginTitle">
						<!-- <h2>Login</h2> -->
					</div>
					<div>
						<form method='post' action="${pageContext.request.contextPath}/login">
							<input type='text' name='username' placeholder="ShineTetris ID를 입력해주세요" style="width: 65%"/><br>
							<input type='password' name='password' placeholder="ShineTetris 비밀번호를 입력해주세요" style="width: 65%; margin:5px 0"/><br>
							<input type='checkbox' name='remember-me' style="margin:5px 5px;"><labal style="font-size:12px; ">ID저장하기</labal> </input> 
							<input type="button" class="button" value="회원가입" onClick="location.href='${path}/tetris/login/memberJoin'" style="margin-left:26%">
							<br>
							<input type='submit' value="로그인" class="button" style="width: 67%; margin:8px 0"/>
							<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }" /> <br>
							
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>