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

<!-- CSS -->
<link href="http://fonts.googleapis.com/css?family=Source+Sans+Pro:200,300,400,600,700,900" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/css/default.css" rel="stylesheet" type="text/css" media="all" />
<link href="${pageContext.request.contextPath}/css/fonts.css" rel="stylesheet" type="text/css" media="all" />

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
<div class="main-container">
		<div id="page" class="container" style="top: 20%"> 
			<div class="sign-in-content">
				<div class="sign-in-form">
					<div class="singInTitle">
						<!-- <h2>Login</h2> -->
					</div>
					<div>
						<!-- 
						<form name="regform" id="regform" class="sign-in-reg-form" action="${path}/tetris/login/insert" method="post" onsubmit="return sendit()">
						 -->
						 <form name="regform" id="regform" class="sign-in-reg-form" action="${path}/tetris/login/insert" method="post">
							<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }" />
							<p><label>아이디 : <input type="text" name="user_id" id="user_id" maxlength="20"></label><span id="idResult"></span>
							&nbsp;<input type="button" id="idNumber" value="아이디 중복 검사" onclick="doIdAuth()"></p>
							
							<p><label>비밀번호 : <input type="password" name="user_pw" id="user_pw" maxlength="20"></label></p>
							<p><label>비밀번호 확인 : <input type="password" name="userpw_re" id="userpw_re" maxlength="20"></label></p>
							<p><label>이름 : <input type="text" name="user_name" id="user_name"></label></p>
					
							<!-- 이메일 인증 -->
							<p><label>이메일 : <input type="text" name="user_email" id="user_email" value="${mail}"></label><span id="emailResult"></span>
							&nbsp;<input type="button" id="emailAuth" value="이메일 발송" onclick="doEmailAuth()"><span id="result"></span></p>
							<p id="emailAuthArea" style="display: none;"><label>인증번호 : <input type="text" name="emailAuthText" id="emailAuthText" maxlength="10"></label>
							&nbsp;<input type="button" id="emailNumber" value="인증번호 확인" onclick="doEmailNumberAuth()"></p>
							
							<!-- 이메일 인증 관련 hidden -->
							<input type="hidden" name="isSsn" id="isSsn" value="false">
							<!-- 아이디 중복 검사 hidden -->
							<input type="hidden" name="isIdSsn" id="isIdSsn" value="false">
							
							<!-- 우편 API -->
							<p>우편번호 : <input type="text" name="zipcode" id="sample6_postcode">
							&nbsp;<input type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기"><br></p>
							<p>주소 : <input type="text" name="address1" id="sample6_address"><br></p>
							<p>상세주소 : <input type="text" name="address2" id="sample6_detailAddress"></p>
							
							<!-- 버튼 -->
							<p><input type="submit" value="가입완료"> <input type="reset" value="다시작성"></p>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>