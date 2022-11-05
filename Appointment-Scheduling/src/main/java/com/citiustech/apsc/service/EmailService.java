package com.citiustech.apsc.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.citiustech.apsc.model.EmailDto;

@Service
public class EmailService {

	@Autowired
	private TemplateEngine engine;

	@Autowired
	private JavaMailSender javaMailSender;



	public String sendMail(EmailDto emailDto) throws MessagingException {
		System.out.println(emailDto);
		
		Context context = new Context();
		context.setVariable("email", emailDto);

		String process = engine.process("appointment", context);
		
		processMail(process, emailDto, emailDto.getPatientEmail());
		
		String process1 = engine.process("physician", context);
		
		processMail(process1, emailDto, emailDto.getPhysicianEmail());
		
		return "sent";

	}
	
	public void processMail(String process, EmailDto emailDto, String emailAddress) throws MessagingException {
		

		
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);

		messageHelper.setSubject(emailDto.getStatus());

		messageHelper.setText(process, true);

		messageHelper.setTo(emailAddress);

		javaMailSender.send(mimeMessage);
		
	}

}