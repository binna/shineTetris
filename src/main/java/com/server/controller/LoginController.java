package com.server.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.server.dto.EmailDTO;
import com.server.service.EmailService;

@Controller
@RequestMapping("/login/*")
public class LoginController {
	
	String joinSecurityKey = "";
	
	@GetMapping("/all")
	public void doAll() {  // 리턴타입 없으면 url 과 같은 경로의 jsp 파일을 찾는다.
		System.out.println("doAll() : access everybody");
	}
	
	@GetMapping("/member")
	public void doMember() {
		System.out.println("doMember() : access member only");
	}
	
	 @GetMapping("/memberJoin")
	 public void doMemberJoin(){
		 System.out.println("doMemberJoin()");
	 }
	 
	 // 회원가입 insert
	 @RequestMapping("/insert")
	 public void doInsert() {
		 
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