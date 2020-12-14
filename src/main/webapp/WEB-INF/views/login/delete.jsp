<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:choose>
	<c:when test="${code == 1}">
		<script>
			// 문제없음, 삭제 완
			alert("회원 탈퇴 처리가 정상적으로 처리되었습니다.\n그동안 이용해주셔서 감사합니다.");
			location.href = "${pageContext.request.contextPath}/customLogin";
		</script>
	</c:when>
	<c:when test="${code == 0}">
		<script>
			// delete 에러코드 -1 : DB 에러
			alert("delete 에러코드 0\n관리자에게 문의주세요.");
			history.back();
		</script>
	</c:when>
	<c:when test="${code == -1}">
		<script>
			// delete 에러코드 -1 : 컨트롤러에서 에러
			alert("delete 에러코드 -1\n관리자에게 문의주세요.");
			history.back();
		</script>
	</c:when>
	<c:otherwise>
		<script>
			// delete 에러코드 null : 알 수 없는 에러
			alert("delete 에러코드 null\n관리자에게 문의주세요.");
			history.back();
		</script>
	</c:otherwise>
</c:choose>  