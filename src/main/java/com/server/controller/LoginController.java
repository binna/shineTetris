package com.server.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.server.service.EmailService;

@Controller
@RequestMapping("/login/*")
public class LoginController {
	
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
	 
	 // 회원 가입 이메일 발송
	 @Inject
	 EmailService emailService;		// 서비스를 호출 하기 위한 의존성 주입
	 @RequestMapping("/email")
	 public String send(HttpServletRequest request, Model model) {
		 String mail = request.getParameter("mail");
		 String joinSecurityKey = "";
		 
		 //HashMap<Stirng, Stirng> value = HashMap Map<Stirng, Stirng>();
		 
		 joinSecurityKey = emailService.sendMail(mail);

		 if(joinSecurityKey.length() != 0) {
			 model.addAttribute("joinSecurityKey", joinSecurityKey);
			 model.addAttribute("message", "메일 발송을 실패했습니다.");
		 } else {
			 model.addAttribute("message", "이메일이 발송되었습니다.");
		 }
		 
		 return "/login/memberJoin";
	 }
	 
} // end Controller