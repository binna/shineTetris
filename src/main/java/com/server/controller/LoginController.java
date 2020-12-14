package com.server.controller;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.server.dto.EmailDTO;
import com.server.service.EmailService;
import com.server.service.JoinLoginService;
import com.server.util.CommonUtil;

@Controller
@RequestMapping("/login/*")
public class LoginController {
	
	String joinSecurityKey = "";
	
	@Autowired
	JoinLoginService loginService;
	
	@GetMapping("/all")
	public void doAll() {  // 리턴타입 없으면 url 과 같은 경로의 jsp 파일을 찾는다.
		System.out.println("doAll() : access everybody");
	}
	
	@GetMapping("/member")
	public void doMember() {
		System.out.println("doMember() : access member only");
	}
	
	// 회원가입
	@GetMapping("/memberJoin")
	public void doMemberJoin(){
		System.out.println("doMemberJoin()");
	}
	 
	// 회원가입 insert
	@RequestMapping("/insert")
	@ResponseBody
	public HashMap<String, Object> doInsert(HttpServletRequest request) {
		HashMap<String, Object>map =  new HashMap<String, Object>();
		 
		//ajax로 들어온값 Map형식으로 변환 웹의 name값 기준
		Map<String, Object> dto = CommonUtil.request2Map(request);
		 
		try {
			map = loginService.insertMember(dto);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("code", "9999");
			map.put("message", e.getMessage());
		}
 
		return map;
	}
	 
	// 회원정보 update
	 @RequestMapping("/update")
	 @ResponseBody
	 public HashMap<String, Object> doUpdate(HttpServletRequest request) {
		 HashMap<String, Object>map =  new HashMap<String, Object>();
		 //ajax로 들어온값 Map형식으로 변환 웹의 name값 기준
		 Map<String, Object> dto = CommonUtil.request2Map(request);
		 try {
			 //어떤값이 들어왔는지 확인용
			 for (String key : dto.keySet()) {
					String value = String.valueOf(dto.get(key));
					System.out.println(key + " : " + value);
				}
			map = loginService.updateMember(dto);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("code", "9999");
			map.put("message", e.getMessage());
		}
		 
		 return map;
	 }
	 
	// 회원정보 삭제
	 @RequestMapping("/delete")
	 @ResponseBody
	 public HashMap<String, Object> doDelete(HttpServletRequest request) {
		 HashMap<String, Object>map =  new HashMap<String, Object>();
		 //ajax로 들어온값 Map형식으로 변환 웹의 name값 기준
		 Map<String, Object> dto = CommonUtil.request2Map(request);
		 try {
			 //어떤값이 들어왔는지 확인용
			 for (String key : dto.keySet()) {
					String value = String.valueOf(dto.get(key));
					System.out.println(key + " : " + value);
				}
			map = loginService.deleteMember(dto);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("code", "9999");
			map.put("message", e.getMessage());
		}
		 
		 return map;
	 }
	 
	 // 회원 가입 이메일 발송
	 @Inject
	 EmailService emailService;		// 서비스를 호출 하기 위한 의존성 주입
	 @RequestMapping("/email")
	 public String sendEmail(HttpServletRequest request) {
		 String mail = request.getParameter("mail");
		 
		 joinSecurityKey = emailService.sendMail(mail);
		 
		 return "/login/memberJoin";
	 }
	 
	 // 이메일 인증 번호와 내가 입력한 값 일치 여부 검증
	 @RequestMapping("/compare")
	 @ResponseBody
	 public EmailDTO compareNumber(HttpServletRequest request) {
		 String myAuthNum = request.getParameter("myAuthNum");
		 
		 EmailDTO emaildto = new EmailDTO();
		 
		 System.out.println("myAuthNum>>>>>>>>"+myAuthNum);
		 System.out.println("joinSecurityKey>>>>>>>>"+joinSecurityKey);
		 
		 if(myAuthNum.equals(joinSecurityKey)) {
			 emaildto.setResult(1);
		 } else {
			 emaildto.setResult(0);
		 }
		 
		 return emaildto;
	 }
	 
} // end Controller