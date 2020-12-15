'use strict'

// 전역변수 선언
let timeId;		// 이메일 인증

// 회원가입 유효성 검사
function sendit() {
	// 객체 저장 
	const userid = document.getElementById('user_id');
	const userpw = document.getElementById('user_pw');
	const userpw_re = document.getElementById('userpw_re');
	const username = document.getElementById('user_name');
	const isSsn = document.getElementById('isSsn');
	const isIdSsn = document.getElementById('isIdSsn');
	const zipcode = document.getElementById('sample6_postcode');
	const address1 = document.getElementById('sample6_address');
	const address2 = document.getElementById('sample6_detailAddress');
	
	// 정규식
	const expPwText = /^.*(?=^.{4,20}$)(?=.*\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&*()+=]).*$/;
	const expNameText = /[가-힣|a-z|A-Z]+$/; 
	
	// 아이디 유효성
	if(userid.value == '') {
		alert('아이디를 입력하세요.');
		userid.focus();
		return false;
	}
	if(isIdSsn.value == 'false') {
		alert('아이디 인증은 필수입니다.');
		isSsn.focus();
		return false;
	}
	
	// 비밀번호 유효성
	if(userpw.value == '') {
		alert('비밀번호를 입력하세요.');
		userpw.focus();
		return false;
	}
	if(expPwText.test(userpw.value) == false) {
		alert('비밀번호 형식을 확인하세요.\n 4자이상 대소문자 특수문자 포함된 비밀번호만 가능합니다.');
		userpw.focus();
		return false;
	}
	if(userpw_re.value == '') {
		alert('비밀번호 확인을 입력하세요.');
		userpw_re.focus();
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
	if(username.value.length < 2) {
		alert('이름을 확인해주세요.');
		username.focus();
		return false;
	}
	if(expNameText.test(username.value) == false) {
		alert('이름 형식을 확인하세요.\n 문자만 가능합니다.');
		username.focus();
		return false;
	}

	// 이메일 유효성
	if(isSsn.value == 'false') {
		alert('이메일 인증은 필수입니다.');
		isSsn.focus();
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

//아이디 중복 검사
function doIdAuth() {
	// 아이디 검증을 위한 객체 생성
	const userid = document.getElementById('user_id');
	
	// 유효성 검사
	if(userid.value.length < 4 || userid.value.length > 20) {
		alert('아이디를 4자이상 20자 이하로 입력하세요.');
		userid.focus();
		return false;
	}
	
	// XMLHttpRequest 객체 생성
	let xhttp = new XMLHttpRequest();
	
	xhttp.onreadystatechange = function() {
		if (xhttp.readyState === XMLHttpRequest.DONE) {
			if (xhttp.status === 200) {
				
			} else {
				alert('select 에러코드 null\n아이디 중복 검사가 실패되었습니다.\n관리자에게 문의주세요.');
			}
		}
	};
	
	// request 보내기
	xhttp.open('GET', '/tetris/login/idAuth?userId=' + userid.value, false); 
	xhttp.send();
	
	// 검사한 값 response
	const resultText = xhttp.responseText;
	const resultObj = JSON.parse(resultText);
	
	// 결과 값에 따라 화면 노출
	// -1 컨트롤러 에러, 0 중복 없음, 중복 개수만큼  +정수
	if(resultObj.result == -1) {
		alert("select 에러코드 -1\n관리자에게 문의주세요.");
	} else if(resultObj.result == 0) {
		alert('중복된 아이디가 없습니다.\n가입을 지속해주세요.');
		document.getElementById('user_id').setAttribute('type', 'hidden');
		document.getElementById('idResult').innerHTML = userid.value;
		document.getElementById('user_id').setAttribute('display', 'block');
		document.getElementById('idNumber').setAttribute('disabled', 'disabled');
		document.getElementById('idNumber').setAttribute('value', '중복 검사 완료');
		document.getElementById('isIdSsn').setAttribute('value', 'true');
	} else {
		alert('중복된 아이디가 있습니다.\n다른 아이디를 입력해주세요.');
	}
} // end doIdAuth()

// 이메일 유효성 검사, Ajax 이메일 발송
function doEmailAuth() {
	// 이메일 검증을 위한 객체 생성
	const email = document.getElementById('user_email');

	// 이메일 검증을 위한 정규식, 유효성 검사후 이메일 정보를 담을 변수
	const expEmailText = /^[A-Za-z0-9\.\-]+@[A-Za-z0-9\.\-]+\.[A-Za-z0-9\.\-]+$/;
	let paraEmail;
	
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
	
	// 유효성 검사 통과 후 이메일 정보 변수에 저장
	paraEmail = email.value;
	
	// XMLHttpRequest 객체 생성
	let xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (xhttp.readyState === XMLHttpRequest.DONE) {
			if (xhttp.status === 200) {
				let setTime = 180;	// 3분 -> 180초
				timeId = null;		// 객체 위치 저장하는 함수 초기화
				
				// 이메일 발송 버튼 숨기기
				document.getElementById('emailAuth').setAttribute('type', 'hidden');
				document.getElementById('emailAuthArea').style.display ='block';
				
				// 3분 타이머
				timeId = setInterval(function() {
					const m = Math.floor(setTime / 60) + "분 " + (setTime % 60) + "초";	// 남은 시간 계산
				    document.getElementById('result').innerHTML = m;
					setTime--;
					if(setTime < 0) {
						clearInterval(timeId);
						// 인증 초과 팝업과 함께 버튼 활성과, 숫자 표시 없애기
						alert('인증시간이 초과됬습니다.');
						document.getElementById('emailAuth').setAttribute('type', 'button');
						document.getElementById('emailAuthArea').style.display ='none';
						document.getElementById('result').innerHTML = "";
					}
				}, 1000);
				
			} else {
				alert('이메일 발송이 실패했습니다.');
			}
		}
	};
	
	xhttp.open('GET', '/tetris/login/email?mail=' + paraEmail, false);	// open(request method, URL, 비동기식 true 동기식 false) 
	xhttp.send();														// send() : POST 방식으로 요구한 경우 서버로 보내고 싶은 데이터
} // end doEmailAuth()

// 이메일 인증 번호 검사, Ajax response
function doEmailNumberAuth() {
	// 객체 저장
	const authNum = document.getElementById('emailAuthText');
	
	// 유효성 검사
	if(authNum.value.length < 10) {
		alert('이메일 인증번호는 10자리입니다.\n다시 확인해주세요');
		authNum.focus(); return false;
	}
	
	// 유효성 검사 후 사용자가 작성한 내용 변수에 저장
	let paraAuthNum = authNum.value;
	
	// XMLHttpRequest 객체 생성
	let xhttp = new XMLHttpRequest();

	// 입력한 값이 인증번호와 일치하는지 검사
	xhttp.open('GET', '/tetris/login/compare?myAuthNum=' + paraAuthNum, false);
	xhttp.send();
	
	// 검사한 값 response
	const resultText = xhttp.responseText;
	const resultObj = JSON.parse(resultText);
	
	// 결과 값에 따라 화면 노출
	if(resultObj.result == 1) {
		alert('정상적으로 인증되었습니다.');
		clearInterval(timeId);
		document.getElementById('result').innerHTML = "";
		document.getElementById('user_email').setAttribute('type', 'hidden');
		document.getElementById('emailResult').innerHTML = document.getElementById('user_email').value;
		document.getElementById('emailAuthText').setAttribute('disabled', 'disabled');
		document.getElementById('emailNumber').setAttribute('value', '인증 완료');
		document.getElementById('emailNumber').setAttribute('disabled', 'disabled');
		document.getElementById('isSsn').setAttribute('value', 'true');
	} else {
		alert('인증번호가 일치하지 않습니다.\n다시 확인 후 입력해주세요.');
	}
} // end doEmailNumberAuth()

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
			if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
			    addr = data.roadAddress;
			} else { // 사용자가 지번 주소를 선택했을 경우(J)
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

// 회원정보 수정 유효성 검사
function senditUpdate() {
	const zipcode = document.getElementById('sample6_postcode');
	const address1 = document.getElementById('sample6_address');
	const address2 = document.getElementById('sample6_detailAddress');
	
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
	
}

// 이메일 변경 유효성 검사
function senditEmail() {
	// 이메일 유효성
	const isSsn = document.getElementById('isSsn');
	if(isSsn.value == 'false') {
		alert('이메일 인증은 필수입니다.');
		isSsn.focus();
		return false;
	}
} // end senditEmail()

// 비밀번호 유효성 검사
function senditPw() {
	// 객체 저장
	const userpw_now = document.getElementById('userpw_now');
	const user_pw = document.getElementById('user_pw');
	const userpw_re = document.getElementById('userpw_re');
	
	// 정규식
	const expPwText = /^.*(?=^.{4,20}$)(?=.*\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&*()+=]).*$/;
	
	// 비밀번호 유효성
	if(userpw_now.value == '') {
		alert('현재 비밀번호를 입력하세요.');
		userpw_now.focus();
		return false;
	}
	if(user_pw.value == '') {
		alert('변경할 비밀번호를 입력하세요.');
		user_pw.focus();
		return false;
	}
	if(expPwText.test(user_pw.value) == false) {
		alert('변경할 비밀번호 형식을 확인하세요.\n 4자이상 대소문자 특수문자 포함된 비밀번호만 가능합니다.');
		user_pw.focus();
		return false;
	}
	if(userpw_re.value == '') {
		alert('변경할 비밀번호 확인을 입력하세요.');
		userpw_re.focus();
		return false;
	}
	if(user_pw.value != userpw_re.value) {
		alert('비밀번호와 비밀번호 확인의 값이 서로 다릅니다.');
		user_pw.focus();
		return false;
	}
} // end senditPw()

// 회원 삭제
function doDelete() {
	const userInput = prompt('정말로 회원 탈퇴를 원하십니까?\n탈퇴하시면 복구가 어렵습니다.\n탈퇴를 원하신다면 \"탈퇴\"를 공백없이 기입해주세요.');
	const userid = document.getElementById('user_id');
	console.log()
	if(userInput == '탈퇴') {
		location.href = "delete?userId=" + userid.value;
	} else {
		if(typeof userInput === null){
			alert('탈퇴 취소 버튼을 누르셨습니다.\n탈퇴가 진행되지 않았습니다.');
		}else{
			alert('정확하게 입력하지 않으셨습니다. \n정확하게 입력되지 않으면 탈퇴가 진행되지 않습니다.');
		}
	}
} // end doDelete()