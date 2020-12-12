<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>회원가입</title>

<!-- JS -->
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="${pageContext.request.contextPath}/JS/join.js"></script>
<style>
input:focus {
  background-color: yellow;
}
button:focus {
  border: thick double #32a1ce;
}
</style>
</head>
<body>
	<h2>회원가입</h2> 
	<form name="regform" id="regform" action="${path}/tetris/login/memberJoin" method="post" onsubmit="return sendit()">
		<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }" />
		<p><label>아이디 : <input type="text" name="userid" id="userid" maxlength="20"></label></p>
		<p><label>비밀번호 : <input type="password" name="userpw" id="userpw" maxlength="20"></label></p>
		<p><label>비밀번호 확인 : <input type="password" name="userpw_re" id="userpw_re" maxlength="20"></label></p>
		<p><label>이름 : <input type="text" name="username" id="username"></label></p>
		<p><label>이메일 : <input type="text" name="email" id="email"></label>
		&nbsp;<input type="button" id="emailAuth" value="이메일 발송" onclick="doEmailAuth()"><span id="result"></span></p>
		<input id="mailSecuKey" value="${joinSecurityKey}">
		<input id="mailMessage" value="${message}">
		
		<!-- 우편 API -->
		<p>우편번호 : <input type="text" name="zipcode" id="sample6_postcode" disabled="disabled">
		&nbsp;<input type="button" id="adrBtn" value="우편번호 검색" onclick="sample6_execDaumPostcode()"></p>
		<p><label>주소 : <input type="text" name="address1" id="sample6_address"></label></p>
		<p><label>상세주소 : <input type="text" name="address2" id="sample6_detailAddress"></label></p>
		
		<!-- 버튼 -->
		<p><input type="submit" value="가입완료"> <input type="reset" value="다시작성"></p>
	</form>

</body>
</html>