<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>회원가입</title>
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="./JS/member.js"></script>
</head>
	<body>
	<h2>회원가입</h2> 
	<form name="regform" id="regform" method="post" action="regist_ok.php" onsubmit="return sendit()">
		<input type="hidden" name="isSsn" id="isSsn" value="false">
		<p><label>아이디 : <input type="text" name="userid" id="userid" maxlength="20"></label></p>
		<p><label>비밀번호 : <input type="password" name="userpw" id="userpw" maxlength="20"></label></p>
		<p><label>비밀번호 확인 : <input type="password" name="userpw_re" id="userpw_re" maxlength="20"></label></p>
		<p><label>이름 : <input type="text" name="username" id="username"></label></p>
		<p><label>휴대폰 번호 : <input type="text" name="hp" id="hp"></label> ('-' 을 포함)</p>
		<p><label>이메일 : <input type="text" name="email" id="email"></label></p>
		<p>취미 : <label>드라이브<input type="checkbox" name="hobby" value="드라이브"></label>
				<label>등산<input type="checkbox" name="hobby" value="등산"></label>
				<label>영화감상<input type="checkbox" name="hobby" value="영화감상"></label>
				<label>게임<input type="checkbox" name="hobby" value="게임"></label> </p>
		<p><label>주소 : <input type="text" name="address1" id="sample6_address"></label></p>
		<p><label>상세주소 : <input type="text" name="address2" id="sample6_detailAddress"></label></p> 
		<p><label>참고항목 : <input type="text" name="address3" id="sample6_extraAddress"></label></p>
		<p><input type="submit" value="가입완료"> <input type="reset" value="다시작성"></p>
	</form>
</body>
</html>