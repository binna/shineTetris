<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:choose>
	<c:when test="${code == 1}">
		<script>
			// 문제없음, 변경 완
			alert("회원 정보가 정상적으로 변경되었습니다.");
			location.href = "member";
		</script>
	</c:when>
	<c:when test="${code == 0}">
		<script>
			// updateOk 에러코드 0 : DB에서 에러
			alert("updateOk 에러코드 0\n관리자에게 문의주세요.");
			history.back();
		</script>
	</c:when>
	<c:when test="${code == -1}">
		<script>
			// updateOk 에러코드 -1 : 컨트롤러에서 에러
			alert("updateOk 에러코드 -1\n관리자에게 문의주세요.");
			history.back();
		</script>
	</c:when>
	<c:otherwise>
		<script>
			// updateOk 에러코드 null : 알 수 없는 에러
			alert("updateOk 에러코드 null\n관리자에게 문의주세요.");
			history.back();
		</script>
	</c:otherwise>
</c:choose>  