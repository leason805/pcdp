package com.boxun.estms.util;

import java.util.List;
import java.util.Properties;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;


public class MailUtil {

	public static boolean send(){
		boolean result = true;
		
		try{
			JavaMailSenderImpl senderImpl = new JavaMailSenderImpl();  
			  
	        // 设定mail server  
	        senderImpl.setHost("smtp.163.com");  
	  
	        // 建立邮件消息,发送简单邮件和html邮件的区别  
	        MimeMessage mailMessage = senderImpl.createMimeMessage();  
	        MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage);  
	  
	        // 设置收件人，寄件人  
	        messageHelper.setTo("leason805@21cn.com");  
	        messageHelper.setFrom("leason805@163.com");  
	        messageHelper.setSubject("测试HTML邮件！");  
	        // true 表示启动HTML格式的邮件  
	        messageHelper  
	                .setText( "<html><head></head><body><h1>hello!!spring html Mail</h1></body></html>",  true);  
	  
	        senderImpl.setUsername("leason805"); // 根据自己的情况,设置username  
	        senderImpl.setPassword("leason@805"); // 根据自己的情况, 设置password  
	        Properties prop = new Properties();  
	        prop.put("mail.smtp.auth", "true"); // 将这个参数设为true，让服务器进行认证,认证用户名和密码是否正确  
	        prop.put("mail.smtp.timeout", "25000");  
	        senderImpl.setJavaMailProperties(prop);  
	        // 发送邮件  
	        senderImpl.send(mailMessage);  
	  
	        System.out.println("邮件发送成功..");
		}
		catch(Exception e){
			result = false;
			e.printStackTrace();
		}
		return result;
	}
	
	
	public static boolean send(String host, String user, String pwd, String from, List<String> tolist, List<String> cclist, String title, String text){
		boolean result = true;
		
		try{
			JavaMailSenderImpl senderImpl = new JavaMailSenderImpl();  
			  
	        // 设定mail server  
	        senderImpl.setHost(host);  
	  
	        // 建立邮件消息,发送简单邮件和html邮件的区别  
	        MimeMessage mailMessage = senderImpl.createMimeMessage();  
	        MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage, true, "UTF-8");     
	       // MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage);  
	  
	        // 设置收件人，寄件人  
	        //messageHelper.setTo(to);  
	        for(String to : tolist){
	        	messageHelper.addTo(to);
	        }
	       
	        if(cclist != null && !cclist.isEmpty()){
	        	for(String cc : cclist){
		        	messageHelper.addCc(cc);
		        }
	        }
	        messageHelper.setFrom(from);  
	        messageHelper.setSubject(title);  
	        // true 表示启动HTML格式的邮件  
	        messageHelper.setText( text,  true);  
	  
	        senderImpl.setUsername(user); // 根据自己的情况,设置username  
	        senderImpl.setPassword(pwd); // 根据自己的情况, 设置password  
	        Properties prop = new Properties();  
	        prop.put("mail.smtp.auth", "true"); // 将这个参数设为true，让服务器进行认证,认证用户名和密码是否正确  
	        prop.put("mail.smtp.timeout", "25000");  
	        senderImpl.setJavaMailProperties(prop);  
	        // 发送邮件  
	        senderImpl.send(mailMessage);  
	  
	        System.out.println("邮件发送成功..");
		}
		catch(Exception e){
			result = false;
			e.printStackTrace();
		}
		return result;
	}
}
