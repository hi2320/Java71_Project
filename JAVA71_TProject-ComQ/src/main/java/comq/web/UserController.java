package comq.web;

import java.io.File;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import comq.common.SMTPAuthenticator;
import comq.domain.User;
import comq.service.user.UserService;

@Controller
@RequestMapping("/user/*")
public class UserController {

	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;
	public void setUserService (UserService userService) {
		this.userService = userService;
	}
	
	public UserController() {
		System.out.println(this.getClass());
	}
	
	@RequestMapping
	public ModelAndView joinUserView() throws Exception {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/user/login.html");
		
		return mv;
	}
	
	@RequestMapping
	public ModelAndView login(@RequestParam("email") String email, @RequestParam("pwd") String pwd, HttpSession session) throws Exception {
		
		User user = userService.getUser(email);
		ModelAndView mv = new ModelAndView();
		
		if( user.getEmail() != null) {
			if( !user.isActive() ) {
				session.setAttribute("user", user);
				mv.addObject("loginStatus", true);
			}
		} else {
			mv.addObject("loginStatus", false);
		}
		
		
		System.out.println(user);
		
		return new ModelAndView();
	}
	
	// user join
	@Autowired ServletContext sc;
	@RequestMapping
	public ModelAndView joinUser(HttpServletRequest request, @RequestParam MultipartFile propic) throws Exception {
		
		User user = new User();
		
		user.setEmail(request.getParameter("email"));
		user.setPwd(request.getParameter("pwd"));
		user.setUserKind("comq");
		
		if (propic.getSize() != 0) {
			String originFilename = propic.getOriginalFilename();
			System.out.println("originName ==>> " + originFilename);
		
  	  int lastDotPosition = originFilename.lastIndexOf(".");
  	  String extname = originFilename.substring(lastDotPosition);
  	  
  	  String newFilename = System.currentTimeMillis() + extname;
  	  System.out.println("newFileName :: " + newFilename);
  	  
  	  String realUploadPath = sc.getRealPath("/img");
  	  System.out.println("realUploadPath:: " + realUploadPath);
  	  
      File newPath = new File(realUploadPath + "/" + newFilename);
      System.out.println("newPath:: " + newPath);
  	  propic.transferTo(newPath);
  	  
  	  user.setProPic(realUploadPath + "/" + newFilename);
		} else {
			user.setProPic(null);
		}
				
		System.out.println(user);
		if (0 != userService.insertUser(user)) {
			System.out.println("insert Success .. ");
		} else {
			System.out.println("insert Failed ..");
		}
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("user/joinUser.html");
		
		return mv;
		
	}
	
	// e-mail duplication checking
	@RequestMapping(value="/checkId", method=RequestMethod.POST)
	public @ResponseBody String emailCheck(@RequestParam("id") String id) throws Exception {
		System.out.println(id);
		
		String result = "";
		if(userService.getUserCheck(id)) {
			result = "false";				
		} else {
			result = "true";
		}
		return result;
	}
	
	// e-mail authorization 
	@RequestMapping
	public void sendAuthMail(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println("이메일 인증 테스트");
    request.setCharacterEncoding("UTF-8");
    response.setContentType("text/html;charset=UTF-8");
    response.setCharacterEncoding("UTF-8");

    String m_name = "COMQ-관리자";
    String m_email = "narujb@gmail.com";
    String m_title = "제목이요";
    String m_text = "내용이요";
    
    try {
        String mail_from =  m_name + "<" + m_email + ">";
        String mail_to =    "crunky89@naver.com";
        String title =      "hosting.83rpm.com 요청사항 :: " + m_title;
        String contents =   "보낸 사람 :: " + m_name + "&lt;" + m_email + "&gt;<br><br>" + m_title + "<br><br>" + m_text;
        mail_from = new String(mail_from.getBytes("UTF-8"), "8859_1");
        mail_to = new String(mail_to.getBytes("UTF-8"), "8859_1");

        Properties props = new Properties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");
        props.put("mail.smtp.auth", "true");

        Authenticator auth = new SMTPAuthenticator();
        Session sess = Session.getDefaultInstance(props, auth);
        MimeMessage msg = new MimeMessage(sess);
        msg.setFrom(new InternetAddress(mail_from));
        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(mail_to));
        msg.setSubject(title, "UTF-8");
        msg.setContent(contents, "text/html; charset=UTF-8");
        msg.setHeader("Content-type", "text/html; charset=UTF-8");
        Transport.send(msg);
        response.sendRedirect("index.html");
    } catch (Exception e) {
      e.printStackTrace();
      response.sendRedirect("request_failed.jsp");
    } 
    System.out.println("이메일 인증 테스트 end");
	}
}
