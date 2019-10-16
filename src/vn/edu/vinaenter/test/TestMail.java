package vn.edu.vinaenter.test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestMail {

//	@Autowired
//	private JavaMailSender sender;
	
	//@Autowired
	//private SendMailService sendMailService;
//	@Autowired
//	private ServletContext servletContext;
	
	@GetMapping("/url")
	@ResponseBody
	public String testxxx(HttpServletRequest request) {
		System.out.println(request.getRequestURL());
		String url = request.getRequestURL().toString();
		url = url.substring(0, url.lastIndexOf("/"));
		System.out.println(url);
		//System.out.println(request.getRequestURI());
		
		/*String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
		System.out.println(url);*/
		return "";
	}
	
	@GetMapping("/testBean")
	@ResponseBody
	public String testBean(HttpServletRequest request, HttpServletResponse response) {
		//sendMailService.sendMailRegister();
		// Create a Simple MailMessage.
       /* SimpleMailMessage message = new SimpleMailMessage();
         
        message.setTo("tranhuuhongson@gmail.com");
        message.setSubject("Test Simple Email");
        message.setText("Hello, Im testing Simple Email");
 
        // Send Message!
        this.sender.send(message);*/
		
		
		/*String mywebsite = request.getRequestURL().toString();
		System.out.println(mywebsite);
		
		MimeMessage message = sender.createMimeMessage();
		boolean multipart = true;
		MimeMessageHelper helper = null;
		try {
			helper = new MimeMessageHelper(message, multipart, "utf-8");
			
			// Attachment 1
			String path = request.getContextPath() + "files" + File.separator + "file.txt";
			System.out.println(path);
	        FileSystemResource file = new FileSystemResource(new File(path));
	        System.out.println(file.getFilename());
	        helper.addAttachment(file.getFilename(), file);
	        
			String htmlMsg = "<h3>Register account cland.pro</h3>"
					+ "<img src='https://jooinn.com/images/home-13.jpg' style='width: 150px;' />";
			message.setContent(htmlMsg, "text/html");
			helper.setTo("sonthh.vinaenter@gmail.com");
			helper.setSubject("Test send HTML email");

		} catch (MessagingException e) {
			e.printStackTrace();
		}
        sender.send(message);*/
        return "Email Sent!";
	}
}
