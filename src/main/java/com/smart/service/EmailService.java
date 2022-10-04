package com.smart.service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

import org.springframework.stereotype.Service;

@Service
public class EmailService {
	
	public boolean sendEmail(String subject, String message, String to)
	{
		boolean f = false;
		
		String from = "abc@gmail.com";
		
		// variable for gmail
		String host = "smtp.gmail.com";
		
		// get the system properties
		Properties properties = System.getProperties();
		System.out.println("Properties "+properties);
		
		// setting important information to properties object
		
		// host set
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");
		
		// Step 1: to get the session object
		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("abc@gmail.com", "");
			}
		});
		
		session.setDebug(true);
		
		// Step 2: compose the message [text, multi media]
		MimeMessage m = new MimeMessage(session);
		
		try {
			
			// from email
			m.setFrom(from);
			
			// adding recipient to message
			m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			
			// adding subject to message
			m.setSubject(subject);
			
			// adding text to message
			m.setContent(message, "text/html");
			
			// Step 3: send the message using Transport class
			Transport.send(m);
			
			System.out.println("Sent success..........");
			f = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;
	}

}
