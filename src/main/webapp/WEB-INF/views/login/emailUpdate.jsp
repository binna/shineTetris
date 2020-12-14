<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이메일 변경</title>

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
				<form name="regform" id="regform" action="${path}/tetris/login/emailUpdateOk" method="post" onsubmit="return senditEmail()">
					<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }" />
							
					<!-- 이메일 인증 -->
					<p><label>이메일 : <input type="text" name="user_email" id="user_email" value="${user_email}"></label><span id="emailResult"></span>
					&nbsp;<input type="button" id="emailAuth" value="이메일 발송" onclick="doEmailAuth()"><span id="result"></span></p>
					<p id="emailAuthArea" style="display: none;"><label>인증번호 : <input type="text" name="emailAuthText" id="emailAuthText" maxlength="10"></label>
					&nbsp;<input type="button" id="emailNumber" value="인증번호 확인" onclick="doEmailNumberAuth()"></p>
							
					<!-- 아이디 정보, 인정 검사 받았는지 여부는 hidden -->
					<input type="hidden" name="user_id" id="user_id" value="${user_id}">
					<input type="hidden" name="isSsn" id="isSsn" value="false">
						
					<p><input type="submit" value="수정완료"></p>
				</form>
			</div>
		</div>
	</div>
</body>
</html>