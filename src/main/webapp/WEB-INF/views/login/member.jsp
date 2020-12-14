<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>member</title>

<!-- JS -->
<script src="${pageContext.request.contextPath}/JS/join.js"></script>
<link href="http://fonts.googleapis.com/css?family=Source+Sans+Pro:200,300,400,600,700,900" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/css/default.css" rel="stylesheet" type="text/css" media="all" />
<link href="${pageContext.request.contextPath}/css/fonts.css" rel="stylesheet" type="text/css" media="all" />
</head>
<body>
<div class="main-container">
		<div id="page" class="container" style="top: 20%"> 
			<div class="main-content">
				<div class="main-form">
					<div class="main-sub-title">
						<form action="${pageContext.request.contextPath}/customLogout" method='post' style="float: right;">
							<input type="hidden"name="${_csrf.parameterName}" value="${_csrf.token}"/>
							<button class="button">로그아웃</button>
						</form>
						<button class="button" onclick="location.href='${pageContext.request.contextPath}/login/update?userId=${user_id}'" style="float: left: ;">회원 정보 수정</button>
					</div>
					<div class="main-sub-content">
						<div style="text-align: center;font-size: 2em;">${user_id} 님</div>
						<div style="text-align: center;font-size: 1.7em; margin-top: 2%">shineTetis에 오신것을 환영합니다.</div>
						<button type="button" class="btm_image" id="gameStart" style="border:0; margin-top: 9%; margin-left: 26%; margin-bottom: 14%;  ">
							<img src="/tetris/images/start.png">
						</button>
						<!-- 
						<button type="button" class="btm_image" id="download" style="border:0; margin-left: 14%" onclick="location.href='${pageContext.request.contextPath}/download'">
							<img src="/tetris/images/download.png">
						</button>
						 -->
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>