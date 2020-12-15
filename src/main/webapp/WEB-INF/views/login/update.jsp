<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>회원정보 수정</title>

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
			<div class="profile-content">
				<div class="profile-form"> 
					<div>
						<form name="regform" id="regform" class="profile-mod-form" action="${path}/tetris/login/updateOk" method="post" onsubmit="senditUpdate()">
							<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }" />
							<p><label>아이디 : <span>${user_id}</span></label></p>
							<p><label>이름 : <span>${user_name}</span></label></p>

							<!-- id값 post로 넘기기 위해 hidden으로 input 만듦 -->							
							<input type="hidden" name="user_id" id="user_id" value="${user_id}">
					
							<!-- 우편 API -->
							<p>우편번호 : <input type="text" name="zipcode" id="sample6_postcode" value="${user_zipcode}">
							&nbsp;<input type="button" onclick="sample6_execDaumPostcode()" class="button" value="우편번호 찾기"><br></p>
							<p>주소 : <input type="text" name="address1" id="sample6_address" value="${user_address1}"><br></p>
							<p>상세주소 : <input type="text" name="address2" id="sample6_detailAddress" value="${user_address2}"></p>
							
							<!-- 버튼 -->
							<p><input type="submit" class="button" value="수정완료">
							&nbsp;<input type="button" class="button" value="메인 페이지로 이동" onClick="location.href='${path}/tetris/login/member'"></p>
							<p><button type="button" class="button" 
								onclick="window.open('${pageContext.request.contextPath}/login/pwUpdate?userId=${user_id}', 
												 	 '비밀번호 변경', 'width=500, height=500')">비밀번호 변경</button>
							<button type="button" class="button"
								onclick="window.open('${pageContext.request.contextPath}/login/emailUpdate?userId=${user_id}', 
												 	 '이메일 변경', 'width=500, height=500'),''">이메일 변경</button>
							<button type="button" class="button" onclick="doDelete()">회원 정보 삭제</button></p>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>