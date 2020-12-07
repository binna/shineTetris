<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>member</title>
</head>
<body>
<h1>/sample/member page</h1>

<h1> Logout Page</h1>
<form action="${pageContext.request.contextPath}/customLogout" method='post'>
<input type="hidden"name="${_csrf.parameterName}"value="${_csrf.token}"/>
<button>로그아웃</button>
</form>

</body>
</html>