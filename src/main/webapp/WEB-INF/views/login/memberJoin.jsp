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
</head>
<body>
	<h2>회원가입</h2> 
	<form name="regform" id="regform" action="${path}/tetris/login/memberJoin" method="post" onsubmit="return sendit()">
		<p><label>아이디 : <input type="text" name="userid" id="userid" maxlength="20"></label></p>
		<p><label>비밀번호 : <input type="password" name="userpw" id="userpw" maxlength="20"></label></p>
		<p><label>비밀번호 확인 : <input type="password" name="userpw_re" id="userpw_re" maxlength="20"></label></p>
		<p><label>이름 : <input type="text" name="username" id="username"></label></p>
		<p><label>이메일 : <input type="text" name="email" id="email"></label></p>
		
		<!-- 우편 API -->
		<p>우편번호 : <input type="text" name="zipcode" id="sample6_postcode" disabled="disabled"> <input type="button" id="adrBtn" value="우편번호 검색" onclick="sample6_execDaumPostcode()"></p>
		<p><label>주소 : <input type="text" name="address1" id="sample6_address"></label></p>
		<p><label>상세주소 : <input type="text" name="address2" id="sample6_detailAddress"></label>
		
		<!-- 버튼 -->
		<p><input type="submit" value="가입완료"> <input type="reset" value="다시작성"></p>
	</form>

	<button id="emailAuth">이메일 발송</button>
	<input type="button" value="이메일 발송" onClick="${path}/tetris/login/email">
</body>
</html>