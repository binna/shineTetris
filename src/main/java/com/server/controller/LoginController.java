package com.server.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.server.service.LoginService;
import com.server.util.CommonUtil;

@Controller
@RequestMapping("/login/*")
public class LoginController {
	
	@Autowired
	LoginService loginService;
	
	@GetMapping("/loginMain")
	public void loginInput(String error, String logout, Model model) {
		System.out.println("error: " + error);
		System.out.println("logout: " + logout);
		
		if(error != null) {
			model.addAttribute("error", "Login Error Check Your Account");
		}
		
		if(logout != null) {
			model.addAttribute("logout", "Logout!!");
		}
	} 
	
	@GetMapping("loginAfter")
	public void loginAfter() {
		System.out.println("로그인 성공");
	}
	
	@GetMapping("/sampleTest")
	//@ResponseBody
	//public HashMap<String, Object> sampleTest(HttpServletRequest request) {
	public HashMap<String, Object> sampleTest() {
		HashMap<String, Object> map =  new HashMap<String, Object>();
		//Map<String, Object> dto = CommonUtil.request2Map(request);
		Map<String, Object> dto = null;
		
		try {
			map = loginService.sample(dto);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return map;
	}
}