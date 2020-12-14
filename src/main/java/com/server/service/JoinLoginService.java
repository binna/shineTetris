package com.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.server.controller.CustomNoOpPasswordEncoder;
import com.server.dao.MemberJoinDAO;
import com.server.dto.PwDTO;
import com.server.dto.UserDTO;

@Service
public class JoinLoginService {
	@Autowired
	private MemberJoinDAO memberJoinDao;
	
	JoinLoginService loginService;
	
	// 인코딩을 위한 자동 주입
	private PasswordEncoder pwencoder;
	public final PasswordEncoder getPwencoder() {return pwencoder;}
	@Autowired
	public final void setPwencoder(PasswordEncoder pwencoder) {this.pwencoder = pwencoder;}
	
	// 복호화
	CustomNoOpPasswordEncoder customNoOpPasswordEncoder;
	
	// 회원 가입
	public int insertMember(UserDTO userdto) throws Exception {
		int idChkcode = 0;		// 아이디 중복 검사 -> 중복 없으면 0, 중복 개수만큼 개수 출력
		int code = 0;			// insert 성공 여부 검사 -> 실패하면 0, 성공하면 1, 중복된 아이디 있으면 2
		
		// DB 아이디 select 작업, 아이디 중복값 검사
		idChkcode = memberJoinDao.idChk(userdto.getUser_id());
		
		if(idChkcode == 0) {
			userdto.setUser_pw(pwencoder.encode(userdto.getUser_pw()));		// 비밀번호 인코딩
			code = memberJoinDao.insertMember(userdto);						// DB insert 작업
		} else {
			// 아이디 중복값 있을 때
			code = 2;
		}

		return code;
	}
	// 아이디 중복 검사
	public int idChk(String user_id) throws Exception {
		int idChkcode = 0;		// 아이디 중복 검사 -> 중복 없으면 0, 중복 개수만큼 개수 출력
		
		// DB 아이디 select 작업, 아이디 중복값 검사
		idChkcode = memberJoinDao.idChk(user_id);
		
		return idChkcode;
	}  
	
	// 기본 회원 정보 수정 위해 검색
	public UserDTO selectMember(String user_id) throws Exception {
		UserDTO userdto = new UserDTO();
		userdto = memberJoinDao.selectMember(user_id);
		return userdto;
	}
	// 기본 회원 정보 수정 Update
	public int updateMember(UserDTO userdto) throws Exception {
		int code = 0;
		code = memberJoinDao.updateMember(userdto);
		return code;
	}
	
	// 이메일 수정을 위해 검색
	public String selectEmail(String user_id) throws Exception {
		String user_email = memberJoinDao.selectEmail(user_id);
		return user_email;
	}
	// 이메일 정보 수정 Update
	public int updateEmail(UserDTO userdto) throws Exception {
		int code = 0;
		code = memberJoinDao.updateEmail(userdto);
		return code;
	}
	
	// 비밀번호 검색
	public String selectPw(String user_id) throws Exception {
		String user_pw = memberJoinDao.selectPw(user_id);
		return user_pw;
	}
	// 비밀번호 Update
	public int updatePw(PwDTO pwdto) throws Exception {
		int code = 0;	// update 성공 여부
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		System.out.println(customNoOpPasswordEncoder.matches(pwdto.getUserpw_now(), memberJoinDao.selectPw(pwdto.getUser_id())));
		// 인코딩
		//pwdto.setUserpw_now(pwencoder.encode(pwdto.getUserpw_now()));
		//pwdto.setUser_pw(pwencoder.encode(pwdto.getUser_pw()));
		
		
		//System.out.println(pwdto.getUser_pw());
		//System.out.println(pwdto.getUserpw_now());
		//System.out.println(memberJoinDao.selectPw(pwdto.getUser_id()));

		// 사용자가 입력한 현재 비밀번호와 디비에 저장된 비밀번호가 일치하는지 확인하기
		if(memberJoinDao.selectPw(pwdto.getUser_id()).equals(pwdto.getUser_pw())) {
			code = memberJoinDao.updatePw(pwdto);
		} else {
			code = -2;
		}
		
		return code;
	}
	
	// 회원정보 삭제
	public int deleteMember(String user_id) throws Exception {
		int code = 0;
		code = memberJoinDao.deleteMember(user_id);
		return code;
	}
	
} // end class