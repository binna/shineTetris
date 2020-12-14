<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  

<c:choose>
	<c:when test="${code == 0}">
		<script>
			// 문제없음, 가입 완
			alert("가입해주셔서 감사합니다.\n정상적으로 가입되었습니다.\n로그인 페이지로 이동합니다.");
			location.href = "customLogin";
		</script>
	</c:when>
	<c:when test="${code == 1}">
		<script>
			// insert 에러코드 1 : 디비 insert 문제
			alert("insert 에러코드 1\n관리자에게 문의주세요.");
			history.back();   // 브라우저가 기억하는 직전 페이지(입력중 페이지로)
		</script>
	</c:when>
	<c:when test="${code == 2}">
		<script>
			// insert 에러코드 2 : 아이디 중복 문제
			alert("insert 에러코드 2\n중복된 아이디입니다.\n다른 아이디로 가입 부탁드립니다.");
			history.back();   // 브라우저가 기억하는 직전 페이지(입력중 페이지로)
		</script>
	</c:when>
	<c:when test="${code == 999}">
		<script>
			// insert 에러코드 999: db 문제 
			alert("insert 에러코드 999\n관리자에게 문의주세요.");
			history.back();   // 브라우저가 기억하는 직전 페이지(입력중 페이지로)
		</script>
	</c:when>
	<c:otherwise>
		<script>
			// insert 에러코드 null : 알 수 없는 에러
			alert("insert 에러코드 null\n관리자에게 문의주세요.");
			history.back();
		</script>
	</c:otherwise>
</c:choose>