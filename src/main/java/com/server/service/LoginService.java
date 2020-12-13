package com.server.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.server.dao.MemberJoinDAO;

@Service
public class LoginService{
	@Autowired
	private MemberJoinDAO memberJoinDao;
	
	public HashMap<String, Object>insertMember(Map<String, Object> dto) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		int successCode = 0;
		for (String key : dto.keySet()) {
			String value = String.valueOf(dto.get(key));
			System.out.println(key + " : " + value);
		}
		successCode = memberJoinDao.insertMember(dto);
//		map2 = list.get(0);
//		
		System.out.println("successCode >>>>"+successCode);
		map.put("code", successCode);
		return map;
	}
}