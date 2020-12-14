<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:choose>
	<c:when test="${code == 1}">
		<script>
			// 문제없음, 변경 완
			alert("회원 정보가 정상적으로 변경되었습니다.");
			self.opener = self;
			window.close();
		</script>
	</c:when>
	<c:when test="${code == 0}">
		<script>
			// pwUpdateOk 에러코드 0 : DB에서 에러
			alert("pwUpdateOk 에러코드 0\n관리자에게 문의주세요.");
			self.opener = self;
			window.close();
		</script>
	</c:when>
	<c:when test="${code == -1}">
		<script>
			// pwUpdateOk 에러코드 -1 : 컨트롤러에서 에러
			alert("pwUpdateOk 에러코드 -1\n관리자에게 문의주세요.");
			self.opener = self;
			window.close();
		</script>
	</c:when>
	<c:when test="${code == -2}">
		<script>
			// 현재 비밀번호가 일치한 비밀번호가 아님
			alert("현재 사용중인 비밀번호를 틀리셨습니다.\n다시 사용 중인 비밀번호를 확인해주세요.");
			self.opener = self;
			window.close();
		</script>
	</c:when>
	<c:otherwise>
		<script>
			// pwUpdateOk 에러코드 null : 알 수 없는 에러
			alert("pwUpdateOk 에러코드 null\n관리자에게 문의주세요.");
			self.opener = self;
			window.close();
		</script>
	</c:otherwise>
</c:choose>  