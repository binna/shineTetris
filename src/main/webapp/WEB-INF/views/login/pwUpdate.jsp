<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 변경</title>
<!-- JS -->
<script src="${pageContext.request.contextPath}/JS/join.js"></script>
<link href="http://fonts.googleapis.com/css?family=Source+Sans+Pro:200,300,400,500,600,700,900" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/css/default.css" rel="stylesheet" type="text/css" media="all" />
<link href="${pageContext.request.contextPath}/css/fonts.css" rel="stylesheet" type="text/css" media="all" />

</head>
<body>
	<div class="main-content" style="width: 400px; height: 399px">
		<div class="main-form">
			<div class="main-sub-content">
				<form name="regform" id="regform" action="${path}/tetris/login/pwUpdateOk" method="post" onsubmit="return senditPw()">
					<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }" />
					
					<p><label>현재 비밀번호 : <input type="password" name="userpw_now" id="userpw_now" maxlength="20"></label></p>
					<p><label>변경할 비밀번호 : <input type="password" name="user_pw" id="user_pw" maxlength="20"></label></p>
					<p><label>변경할 비밀번호 확인 : <input type="password" name="userpw_re" id="userpw_re" maxlength="20"></label></p>
				
					<input type="hidden" name="user_id" id="user_id" value="${user_id}">
												
					<p><input type="submit" value="수정완료"></p>
				</form>
			</div>
		</div>
	</div>
</body>
</html>