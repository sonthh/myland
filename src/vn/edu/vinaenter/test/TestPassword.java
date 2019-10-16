package vn.edu.vinaenter.test;

import java.security.Principal;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import vn.edu.vinaenter.security.MyUserDetails;
import vn.edu.vinaenter.security.UserDetailsSecurityUtils;

@Controller
public class TestPassword {
	
	@Autowired
	private BCryptPasswordEncoder bCrypt; 
	
	@Autowired
	private JavaMailSender mailSender;
	
	@ResponseBody
	@GetMapping("/sendMail")
	public String testSendMail() {
		System.out.println(mailSender);
		System.out.println("Sending text...");
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("sonthh.vinaenter@gmail.com");
		message.setTo("tranhuuhongson@gmail.com");
		message.setSubject("Subject");
		message.setText("test send gmail using spring");
		// sending message
		mailSender.send(message);
		System.out.println("Sending text done!");
		return "";
	}
	
//	@Autowired
//	private UserDAO userDAO;
	
	@ResponseBody
	@GetMapping("/cookies")
	public String testLogin(HttpServletRequest request, HttpServletResponse response) {
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			/*
			 * xóa tất cả cookies
			 * cookie.setValue("");
		    cookie.setMaxAge(0);
		    cookie.setPath("/");
		    response.addCookie(cookie);*/

			System.out.println(cookie.getName());
			System.out.println(cookie.getMaxAge());
			System.out.println(cookie.getValue());
			System.out.println("--------------");
		}
		return "";
	}

	@ResponseBody
	@GetMapping("/password-bcrypt")
	public String getPasswordBcrypt() {
		//kiểm tra mật khẩu đã được mã hóa có giống với mật khẩu mới này không
		//không đc mã hóa rồi so sánh vì mỗi lần mã hóa cùng một password nhưng chuỗi mã hóa khác nhau
		//ta chỉ dùng phương thức matches
		if (bCrypt.matches("123456", "$2a$10$plhg8MyH3QPRGFfDnOFv3.mK3YNAQDA4vSHDSaFF.Rk7h2Ye1KMvO")) {
			System.out.println("true");
		} else {
			System.out.println("false");
		}
		
		return bCrypt.encode("123456");
	}
	
	@GetMapping("testLogin")
	@ResponseBody
	public String testLogin() {
		//System.out.println(authentication.isAuthenticated());
		//User user = userDAO.getItemByUsernameEnable("fsadfsd");
		//System.out.println(user);
		MyUserDetails user = UserDetailsSecurityUtils.getMyUserDetails();
		if (user != null) {
			System.out.println(user.getUsername());
			System.out.println(user.getPassword());
			System.out.println(user.getAuthorities());
			System.out.println(user.getRole());
		} else {
			System.out.println("user details bị null rồi");
		}
		return "";
	}
	
	/*test thôi nhá: các cách lấy current user*/
	@GetMapping("getUser")
	@ResponseBody
	public String getCurrentUser(Principal principal, HttpServletRequest request, Authentication authentication) {
		System.out.println(authentication.isAuthenticated());
		System.out.println(authentication.getAuthorities());
		System.out.println("-----------------------");
		
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		System.out.println(userDetails.getUsername());
		System.out.println(userDetails.getPassword());
		System.out.println(userDetails.isEnabled());
		System.out.println(userDetails.getAuthorities());
		System.out.println("-----------------------");
		
		System.out.println("username: " + principal.getName());
		System.out.println("-----------------------");
		
		Principal p = request.getUserPrincipal();
		System.out.println(p.getName());
		System.out.println("------------------------");
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println(auth.getName());
		System.out.println("--------------");
		
		return "";
	}
}
