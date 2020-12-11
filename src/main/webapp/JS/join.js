'use strict'


window.onload = function(){
	
    

}


function sendit() {
	// 객체 저장 
	const userid = document.getElementById('userid');
	const userpw = document.getElementById('userpw');
	const userpw_re = document.getElementById('userpw_re');
	const username = document.getElementById('username');
	const email = document.getElementById('email');
	const zipcode = document.getElementById('sample6_postcode');
	const address1 = document.getElementById('sample6_address');
	const address2 = document.getElementById('sample6_detailAddress');
	
	// 정규식
	const expPwText = /^.*(?=^.{4,20}$)(?=.*\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&*()+=]).*$/;
	const expNameText = /[가-힣]+$/; 
	const expEmailText = /^[A-Za-z0-9\.\-]+@[A-Za-z0-9\.\-]+\.[A-Za-z0-9\.\-]+$/;
	
	// 아이디 유효성
	if(userid.value == '') {
		alert('아이디를 입력하세요.');
		userid.focus();
		return false;
	}
	if(userid.value.length < 4 || userid.value.length > 20) {
		alert('아이디를 4자이상 20자 이하로 입력하세요.');
		userid.focus(); return false;
	}
	
	// 비밀번호 유효성
	if(userpw.value == '') {
		alert('비밀번호를 입력하세요.');
		userpw.focus();
		return false;
	}
	if(userpw_re.value == '') {
		alert('비밀번호 확인을 입력하세요.');
		userpw_re.focus();
		return false;
	}
	if(expPwText.test(userpw.value) == false) {
		alert('비밀번호 형식을 확인하세요.');
		userpw.focus();
		return false;
	}
	if(userpw.value != userpw_re.value) {
		alert('비밀번호와 비밀번호 확인의 값이 서로 다릅니다.');
		userpw.focus();
		return false;
	}
	
	// 이름 유효성
	if(username.value == '') {
		alert('이름을 입력하세요.');
		username.focus();
		return false;
	}
	if(expNameText.test(username.value) == false) {
		alert('이름 형식을 확인하세요.');
		username.focus();
		return false;
	}

	// 이메일 유효성
	if(email.value == '') {
		alert('이메일을 입력하세요.');
		email.focus();
		return false;
	}
	if(expEmailText.test(email.value) == false) {
		alert('이메일 형식을 확인하세요.');
		email.focus();
		return false;
	}
	
	// 집 주소 유효성
	if(zipcode.value == '' || address1.value == '') {
		alert('주소를 입력하세요.');
		document.getElementById('adrBtn').focus();
		return false;
	}
	if(address2.value == '') {
		alert('주소를 입력하세요.');
		address2.focus();
		return false;
	}

} // end sendit()

// 이메일 인증
function doEmailAuth() {
	// 이메일 검증을 위한 객체 생성
	const email = document.getElementById('email');

	// 이메일 검증을 위한 정규식, 유효성 검사후 이메일 정보를 담을 변수
	const expEmailText = /^[A-Za-z0-9\.\-]+@[A-Za-z0-9\.\-]+\.[A-Za-z0-9\.\-]+$/;
	let paraEmail;
	
	// XMLHttpRequest 객체 생성
	let xhttp = new XMLHttpRequest();
	
	// 이메일 유효성 검사
	if(email.value == '') {
		alert('이메일을 입력하세요.');
		email.focus();
		return false;
	}
	if(expEmailText.test(email.value) == false) {
		alert('이메일 형식을 확인하세요.');
		email.focus();
		return false;
	}
	
	// 모든 유효성 검사 통과 후 이메일 정보 변수에 저장
	paraEmail = email.value;
	
	xhttp.open('GET', '/tetris/login/email?mail=' + paraEmail, true);	// open(request method, URL, 비동기식 true 동기식 false) 
	xhttp.send(null);													// send() : POST 방식으로 요구한 경우 서버로 보내고 싶은 데이터
	
	
	/*
	
	try {
		if (xhttp.readyState === XMLHttpRequest.DONE) {
			if (xhttp.status === 200) {
				alert(xhttp.responseText);
			} else {
				alert('There was a problem with the request.');
			}
		}
	} catch (e) {
		alert('Caught Exception: ' + e.description);
	}
	*/
} // end doEmailAuth()











// 우편 API
function sample6_execDaumPostcode() { 
	new daum.Postcode({
		oncomplete: function(data) {
			// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
			
			// 각 주소의 노출 규칙에 따라 주소를 조합한다.
			// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
			var addr = ''; // 주소 변수
			var extraAddr = ''; // 참고항목 변수
			
			//사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다. 
			if (data.userSelectedType === 'R') { 
				// 사용자가 도로명 주소를 선택했을 경우 
				addr = data.roadAddress; 
			} else { 
				// 사용자가 지번 주소를 선택했을 경우(J) 
				addr = data.jibunAddress; 
			}
			
			// 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
			if(data.userSelectedType === 'R'){
				// 법정동명이 있을 경우 추가한다. (법정리는 제외)
				// 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
				if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){ 
					extraAddr += data.bname; 
				} 
				// 건물명이 있고, 공동주택일 경우 추가한다.
				if(data.buildingName !== '' && data.apartment === 'Y'){
					extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
				} 
				// 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다. 
				if(extraAddr !== ''){
					extraAddr = ' (' + extraAddr + ')';
				} 
			}

			// 우편번호와 주소 정보를 해당 필드에 넣는다.
			document.getElementById('sample6_postcode').value = data.zonecode;
			document.getElementById("sample6_address").value = addr;
			// 커서를 상세주소 필드로 이동한다.
			document.getElementById("sample6_detailAddress").focus();
		}
    }).open();
} // end sample6_execDaumPostcode()