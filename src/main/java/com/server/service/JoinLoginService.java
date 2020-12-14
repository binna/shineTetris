package com.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.server.dao.MemberJoinDAO;
import com.server.dto.UserDTO;

@Service
public class JoinLoginService {
	@Autowired
	private MemberJoinDAO memberJoinDao;
	
	@Autowired
	JoinLoginService loginService;
	
	// 인코딩을 위한 자동 주입
	private PasswordEncoder pwencoder;
	public final PasswordEncoder getPwencoder() {return pwencoder;}
	@Autowired
	public final void setPwencoder(PasswordEncoder pwencoder) {this.pwencoder = pwencoder;}
	
	// 회원 가입
	public int insertMember(UserDTO userdto) throws Exception {
		int idChkcode = 0;		// 아이디 중복 검사 -> 중복 없으면 0, 중복 개수만큼 개수 출력
		int Code = 0;			// insert 성공 여부 검사 -> 실패하면 0, 성공하면 1, 중복된 아이디 있으면 2
		
		// DB 아이디 select 작업, 아이디 중복값 검사
		idChkcode = memberJoinDao.idChk(userdto.getUser_id());
		
		if(idChkcode == 0) {
			userdto.setUser_pw(pwencoder.encode(userdto.getUser_pw()));		// 비밀번호 인코딩
			Code = memberJoinDao.insertMember(userdto);						// DB insert 작업
		} else {
			// 아이디 중복값 있을 때
			Code = 2;
		}

		return Code;
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
	
	
	
	
	
	
	// 회원정보 삭제
	public int deleteMember(String user_id) throws Exception {
		int code = 0;
		code = memberJoinDao.deleteMember(user_id);
		return code;
	}
	
} // end class