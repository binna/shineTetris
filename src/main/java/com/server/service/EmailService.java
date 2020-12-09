package com.server.service;

import java.util.Random;

import javax.inject.Inject;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	
	String joinSecurityKey = "";
	
	@Inject
    JavaMailSender mailSender;
	
	//인증코드 생성 메서드
	private void getAuthCode(int size) {
	    Random random = new Random();
	    
	    String key = "0123456789ABCDEFGHIJKLMNOPQRSTUVWSYZabcdefghijklmnopqrstuvwsyz";
	    int num;
	    
	    for (int i = 0; i < size; i++) {
	    	num = random.nextInt(62);
	        joinSecurityKey += key.charAt(num) + "";
		}
	}
    
	// 이메일 발송
    public void sendMail() {
    	
    	// 인증코드 만들기
    	getAuthCode(10);
    	
        try {

        	MimeMessage mimeMessage = mailSender.createMimeMessage();
        	
        	// 받는 사람
        	mimeMessage.addRecipient(RecipientType.TO, new InternetAddress("bgogi14@naver.com"));
        	
        	// 보내는 사람
        	mimeMessage.addFrom(new InternetAddress[] { new InternetAddress("shinetetris@gmail.com", "샤인테트리스") });
        	
        	// 메일 제목
        	mimeMessage.setSubject("샤인 테트리스 : 로그인 인증번호 안내");
        	
        	// 본문 내용
        	mimeMessage.setContent("<html><body><h1 style=\"text-align:center\">샤인 테트리스 가입에 감사드립니다.</h1>"
                    + "<hr><p>안녕하세요. 샤인테트리스입니다.<p>"
                    + "<p>아래의 인증번호를 입력하시고 지원서를 계속 작성해주세요.</p><br><br>"
                    + "<div style=\"text-align:center\"><button style=\"background-color: #97FFFF; padding: 30px 60px; font-size: 15px\">"
                    + joinSecurityKey
                    + "</button></div></body></html>", "text/html; charset=UTF-8");

        	// 메일 발송
        	mailSender.send(mimeMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    } // end sendMail()

} // end Service