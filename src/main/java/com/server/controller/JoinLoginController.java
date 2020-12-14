package com.server.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.server.dto.AjaxResultDTO;
import com.server.dto.PwDTO;
import com.server.dto.UserDTO;
import com.server.service.EmailService;
import com.server.service.JoinLoginService;

@Controller
@RequestMapping("/login/*")
public class JoinLoginController {
	
	String joinSecurityKey = "";
	
	@Autowired
	JoinLoginService joinLoginService;
	
	// 메인 페이지
	@GetMapping("/main")
	public void doMain() {
		System.out.println("doAll() : access everybody");
	}
	
	// 로그인 후 페이지
	@GetMapping("/member")
	public void doMember(Model model) {
		// 현재 인증된(로그인한) 사용자의 정보 가져오기
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails userDetails = (UserDetails) principal;
		String username = userDetails.getUsername();
		model.addAttribute("user_id", username);
	}
	
	// 회원가입
	@GetMapping("/memberJoin")
	public void doMemberJoin(){
		System.out.println("doMemberJoin()");
	}

	// 회원가입 insert
	@RequestMapping("/insert")
	public void doInsert(HttpServletRequest request, Model model) {
		int code = 0;
		
		UserDTO userdto = new UserDTO();
		userdto.setUser_id(request.getParameter("user_id"));
		userdto.setUser_pw(request.getParameter("user_pw"));
		userdto.setUser_name(request.getParameter("user_name"));
		userdto.setUser_email(request.getParameter("user_email"));
		userdto.setUser_zipcode(request.getParameter("zipcode"));
		userdto.setUser_address1(request.getParameter("address1"));
		userdto.setUser_address2(request.getParameter("address2"));
		 
		try {
			code = joinLoginService.insertMember(userdto);
		} catch (Exception e) {
			e.printStackTrace();
			code = -1;
		}
		
		model.addAttribute("code", code);
	}
	 
	// 아이디 중복 검사
	@RequestMapping("/idAuth")
	@ResponseBody
	public AjaxResultDTO doIdAuth(HttpServletRequest request) {
		// result : -1 컨트롤러 문제, -2 DB 문제, 0 중복 없음, 중복 개수만큼  +정수
		int result = -1;
		
		AjaxResultDTO id_dto = new AjaxResultDTO();
		
		String user_id = request.getParameter("userId");
		
		try {
			result = joinLoginService.idChk(user_id);
		} catch (Exception e) {
			e.printStackTrace();
			result = -2;
		}
		
		id_dto.setResult(result);
		return id_dto;
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
	public AjaxResultDTO compareNumber(HttpServletRequest request) {
		AjaxResultDTO emaildto = new AjaxResultDTO();
		
		String myAuthNum = request.getParameter("myAuthNum");
		 
		if(myAuthNum.equals(joinSecurityKey)) {
			emaildto.setResult(1);
		} else {
			emaildto.setResult(0);
		}
		
		return emaildto;
	}
 
	// 회원정보 update를 위해 자료를 뿌려줌
	@RequestMapping("/update")
	public void doUpdate(HttpServletRequest request, Model model) {
		UserDTO userdto = new UserDTO();
		
		String user_id = request.getParameter("userId");
		
		try {
			userdto = joinLoginService.selectMember(user_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		model.addAttribute("user_id", userdto.getUser_id());
		model.addAttribute("user_name", userdto.getUser_name());
		model.addAttribute("user_zipcode", userdto.getUser_zipcode());
		model.addAttribute("user_address1", userdto.getUser_address1());
		model.addAttribute("user_address2", userdto.getUser_address2());
	}

	// 회원정보 update
	@RequestMapping("/updateOk")
	public void doUpdateOk(HttpServletRequest request, Model model) {
		int code = 0;
		
		UserDTO userdto = new UserDTO();
		userdto.setUser_id(request.getParameter("user_id"));
		userdto.setUser_zipcode(request.getParameter("zipcode"));
		userdto.setUser_address1(request.getParameter("address1"));
		userdto.setUser_address2(request.getParameter("address2"));
		
		try {
			code = joinLoginService.updateMember(userdto);
		} catch (Exception e) {
			e.printStackTrace();
			code = -1;
		}
		
		model.addAttribute("code", code);
	}

	// 이메일 update를 위해 자료를 뿌려줌
	@RequestMapping("/emailUpdate")
	public void doEmailUpdate(HttpServletRequest request, Model model) {
		String user_id = request.getParameter("userId");
		String user_email = "";
		
		try {
			user_email = joinLoginService.selectEmail(user_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		model.addAttribute("user_id", user_id);
		model.addAttribute("user_email", user_email);
	}
	
	// 이메일 update
	@RequestMapping("/emailUpdateOk")
	public void doEmailUpdateOk(HttpServletRequest request, Model model) {
		int code = 0;

		UserDTO userdto = new UserDTO();
		userdto.setUser_id(request.getParameter("user_id"));
		userdto.setUser_email(request.getParameter("user_email"));
		
		try {
			code = joinLoginService.updateEmail(userdto);
		} catch (Exception e) {
			e.printStackTrace();
			code = -1;
		}
		
		model.addAttribute("code", code);
	}
	
	// 비밀번호 변경을 위한 현재, 과거 비밀번호 입력창
	@RequestMapping("pwUpdate")
	public void doPwUpdate(HttpServletRequest request, Model model) {
		String user_id = request.getParameter("userId");
		
		model.addAttribute("user_id", user_id);
	}
	
	// 비밀번호 update
	@RequestMapping("pwUpdateOk")
	public void doPwUpdateOk(HttpServletRequest request, Model model) {
		int code = 0;
		
		PwDTO pwdto = new PwDTO();
		pwdto.setUser_id(request.getParameter("user_id"));
		pwdto.setUser_pw(request.getParameter("user_pw"));
		pwdto.setUserpw_now(request.getParameter("userpw_now"));
		
		try {
			joinLoginService.updatePw(pwdto);
		} catch (Exception e) {
			e.printStackTrace();
			code = -1;
		}
		model.addAttribute("code", code);
	}
	
	// 회원정보 삭제
	@RequestMapping("/delete")
	public void doDelete(HttpServletRequest request, Model model) {
		int code = 0;
		 
		String user_id = request.getParameter("userId");
		try {
			code = joinLoginService.deleteMember(user_id);
		} catch (Exception e) {
			e.printStackTrace();
			code = -1;
		}
		model.addAttribute("code", code);
	}
	 
} // end Controller