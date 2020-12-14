<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 변경</title>

<!-- JS -->
<script src="${pageContext.request.contextPath}/JS/join.js"></script>

</head>
<body>

<form name="regform" id="regform" action="${path}/tetris/login/pwUpdateOk" method="post" onclick="senditEmail()">
	<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }" />
	
	<p><label>현재 비밀번호 : <input type="password" name="userpw_now" id="userpw_now" maxlength="20"></label></p>
	<p><label>변경할 비밀번호 : <input type="password" name="user_pw" id="user_pw" maxlength="20"></label></p>
	<p><label>변경할 비밀번호 확인 : <input type="password" name="userpw_re" id="userpw_re" maxlength="20"></label></p>

	<input type="hidden" name="user_id" id="user_id" value="${user_id}">
								
	<p><input type="submit" value="수정완료"></p>
</form>

</body>
</html>