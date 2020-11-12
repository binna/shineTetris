package com.server.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.server.dao.LoginDAO;

@Service
public class LoginService{
	@Autowired
	private LoginDAO loginDao;
	
	@SuppressWarnings("unchecked")
	public HashMap<String, Object>sample(Map<String, Object> dto) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		HashMap<String, Object> map2 = new HashMap<String, Object>();
		List<HashMap<String, Object>> list = null;
		
		list = (List<HashMap<String, Object>>) loginDao.sampleSelectList(dto);
		map2 = list.get(0);
		for (String key : map2.keySet()) {
			String value = String.valueOf(map2.get(key));
			System.out.println(key + " : " + value);
		}
		
		map.put("rows", list);
		return map;
	}
}