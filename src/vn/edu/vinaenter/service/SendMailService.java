package vn.edu.vinaenter.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

public class SendMailService {
	private JavaMailSender mailSender;

	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	private void sendMail(String to, String subject, String content) {
		MimeMessage message = mailSender.createMimeMessage();
		boolean multipart = true;
		MimeMessageHelper helper = null;
		try {
			helper = new MimeMessageHelper(message, multipart, "utf-8");
			message.setContent(content, "text/html");
			helper.setTo(to);
			helper.setSubject(subject);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
        mailSender.send(message);
	}
	
	public void sendMailRegister(String to, String link) {
		String content = "<h3>Register account clandpro</h3>"
				+ "<div><a href='" + link + "'>Click here</a></div>"
				+ "<div><img src='https://jooinn.com/images/home-13.jpg' style='width: 150px;' /></div>";
		String subject = "Register Account";
		sendMail(to, subject, content);
	}
	
	public void sendMailForgotPassword(String to, String link) {
		String content = "<h3>Forgot password clandpro</h3>"
				+ "<div><a href='" + link + "'>Click here</a></div>"
				+ "<div><img src='https://jooinn.com/images/home-13.jpg' style='width: 150px;' /></div>";
		String subject = "Forgot password";
		sendMail(to, subject, content);
	}
}
