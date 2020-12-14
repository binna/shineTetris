package com.server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CommonController {
	
	@GetMapping("/customLogin")
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
	
	@GetMapping("/customLogout")
	public void logoutGET() {
		System.out.println("custom logout");
	}
	
	@PostMapping("/customLogout")
	public void logoutPost() {
		System.out.println("post custom logout");
	}

	@RequestMapping("/download")
	public ModelAndView download(ModelAndView mv) {
		String fullPath = "C:/test/gamefile.jar";
		File file = new File(fullPath);
		
		mv.setViewName("downloadView");
		mv.addObject("downloadFile", file);
		return mv;
	}
	
}