package vn.edu.vinaenter.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import vn.edu.vinaenter.model.bean.Role;
import vn.edu.vinaenter.model.bean.User;
import vn.edu.vinaenter.model.dao.UserDAO;
import vn.edu.vinaenter.service.SendMailService;


@Controller
@RequestMapping("auth")
public class AuthController {

	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private SendMailService sendMailService;
	
	@Autowired
	private BCryptPasswordEncoder bCript;
	
	@GetMapping("login")
	public String login() {
		return "auth/login";
	}
	
	@GetMapping("register")
	public String register() {
		return "auth/register";
	}
	
	@PostMapping("register")
	public String register(@Valid @ModelAttribute("objUser") User objUser, BindingResult errors,
			@RequestParam("repassword") String repassword, RedirectAttributes redirectAttributes,
			ModelMap modelMap, HttpServletRequest request) {
		//email ko được rỗng
		if (objUser.getEmail().trim().equals("")) {
			errors.rejectValue("email", "messageMail");
		} else if (userDAO.countEmail(objUser.getEmail()) > 0) {
			errors.rejectValue("email", "existedEmail");
		}
		
		//tồn tại username
		if (userDAO.hasUser(objUser.getUsername())) {
			errors.rejectValue("username", "existedUser");
		}
		
		//password ko rỗng
		if (objUser.getPassword().trim().equals("")) {
			errors.rejectValue("password", "messageInputPassword");
		} else if (objUser.getPassword().length() < 5 || objUser.getPassword().length() > 32) { //password từ 5-32
			errors.rejectValue("password", "messageLengthField");
		}
		//confirm password
		if (!objUser.getPassword().trim().equals(repassword)) {
			errors.rejectValue("password", "messageConfirmPassword");
		}
		
		
		objUser.setRole(new Role(3, null));
		objUser.setEnable(0);
		objUser.setPassword(bCript.encode(objUser.getPassword()));
		
		if (errors.hasErrors()) {
			return "auth/register";
		}
		
		try {
			//gởi url để người dùng vào email xác nhận
			String urlConfirm = request.getRequestURL() + "/confirm?u=" + objUser.getUsername() + "&p=" + objUser.getPassword();
			sendMailService.sendMailRegister(objUser.getEmail(), urlConfirm);
		} catch (Exception e) {
			//nếu gởi ko thành công tức là email không tồn tại
			//e.printStackTrace();
			System.out.println("Lỗi gởi mail");
			modelMap.addAttribute("objUser", objUser);
			modelMap.addAttribute("msg", "Email bạn không hợp lệ hoặc bạn chưa kết nối internet");
			return "auth/register";
		}
		
		userDAO.addItemUserRegister(objUser);
		redirectAttributes.addFlashAttribute("msg", "Đăng kí thành công, vui lòng check email để xác nhận");
		return "redirect:/auth/register";
	}
	
	//enable khi user confirm trong email
	@GetMapping("register/confirm")
	public String confirmAccount(@RequestParam("u") String username, @RequestParam("p") String password) {
		int count = userDAO.countUserByUsernamePassword(username, password);
		System.out.println(count);
		if (count > 0) {
			User objUser = userDAO.getItemByUsernamePassword(username, password);
			userDAO.enableUser(objUser.getId(), 1);
			return "redirect:/auth/register/confirm/notification?msg=success";
		}
		return "redirect:/auth/register/confirm/notification?msg=error";
	}
	
	@GetMapping("register/confirm/notification")
	public String notification() {
		return "auth/notification";
	}
	
	@GetMapping("forgot")
	public String forgot() {
		return "auth/forgot";
	}
	
	@PostMapping("forgot")
	public String forgot(@RequestParam("email") String email, @RequestParam("password") String password,
			@RequestParam("repassword") String repassword, RedirectAttributes redirectAttributes,
			HttpServletRequest request) {
		
		if (userDAO.countEmail(email) == 0) {
			redirectAttributes.addFlashAttribute("msg", "Email chưa nhập hoặc không hợp lệ");
			return "redirect:/auth/forgot";
		}
		password = password.trim();
		repassword = repassword.trim();
		if (password.length() < 5 || password.length() > 32) {
			redirectAttributes.addFlashAttribute("msg", "Vui lòng nhập password hợp lệ 5-32 kí tự");
			return "redirect:/auth/forgot";
		}
		if (!password.equals(repassword)) {
			redirectAttributes.addFlashAttribute("msg", "Vui lòng nhập khớp password");
			return "redirect:/auth/forgot";
		}
		
		password = bCript.encode(password);
		try {
			//gởi url để người dùng vào email xác nhận
			String urlConfirm = request.getRequestURL() + "/confirm?e=" + email + "&p=" + password;
			sendMailService.sendMailRegister(email, urlConfirm);
		} catch (Exception e) {
			//nếu gởi ko thành công tức là email không tồn tại
			//e.printStackTrace();
			System.out.println("Lỗi gởi mail");
			redirectAttributes.addFlashAttribute("msg", "Email bạn không hợp lệ hoặc bạn chưa kết nối internet");
			return "redirect:/auth/forgot";
		}
		
		redirectAttributes.addFlashAttribute("msg", "Đổi mật khẩu thành công vui lòng check mail để xác nhận");
		return "redirect:/auth/forgot";
	}
	
	//đổi mật khảu
	@GetMapping("forgot/confirm")
	public String confirmForgotPassword(@RequestParam("e") String email, @RequestParam("p") String password) {
		if (userDAO.countEmail(email) == 0) {
			return "redirect:/auth/forgot/confirm/notification?msg=errorPass";
		}
		
		User user = userDAO.getItemByEmail(email);
		user.setPassword(password);
		userDAO.editItem(user);
		return "redirect:/auth/forgot/confirm/notification?msg=successPass";
	}
	
	@GetMapping("forgot/confirm/notification")
	public String notificationPass() {
		return "auth/notification";
	}
	
}
