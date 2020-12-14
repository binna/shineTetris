package com.server.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.server.controller.CustomNoOpPasswordEncoder;
import com.server.dao.MemberJoinDAO;

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
	
	public HashMap<String, Object>insertMember(Map<String, Object> dto) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		int idChkcode = 0;		// 아이디 중복 검사 -> 중복 없으면 0, 중복 개수만큼 개수 출력
		int successCode = 0;	// insert 성공 여부 검사 -> 실패하면 0, 성공하면 1, 중복된 아이디 있으면 2
		
		// 비밀번호 인코딩을 위해 사용자가 입력한 비밀번호 담기
		String user_pw = dto.get("user_pw") + "";		
		
		// insert 실패
		idChkcode = memberJoinDao.idChk(dto);
		if(idChkcode == 0) {
			dto.put("user_pw", pwencoder.encode(user_pw));	// 비밀번호 인코딩
			successCode = memberJoinDao.insertMember(dto);	// insert
		} else {
			successCode = 2;
		}

		// insert가 성공했는지 여부를 map에 담아서 리턴
		map.put("code", successCode);					
		return map;
	}
	
	public HashMap<String, Object>updateMember(Map<String, Object> dto) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		int successCode = 0;
//		for (String key : dto.keySet()) {
//			String value = String.valueOf(dto.get(key));
//			System.out.println(key + " : " + value);
//		}
		successCode = memberJoinDao.updateMember(dto);
//		map2 = list.get(0);
//		
		System.out.println("successCode >>>>"+successCode);
		map.put("code", successCode);
		return map;
	}
	
	public HashMap<String, Object>deleteMember(Map<String, Object> dto) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		int successCode = 0;
//		for (String key : dto.keySet()) {
//			String value = String.valueOf(dto.get(key));
//			System.out.println(key + " : " + value);
//		}
		successCode = memberJoinDao.deleteMember(dto);
//		map2 = list.get(0);
//		
		System.out.println("successCode >>>>"+successCode);
		map.put("code", successCode);
		return map;
	}
}