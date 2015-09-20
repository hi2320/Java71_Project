package comq.web;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

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
	
	// login User Check
	@RequestMapping(value="/sessionCheck", method=RequestMethod.POST)
	public @ResponseBody String sessionCheck(HttpServletRequest request, HttpSession session) throws Exception {
		
		session = request.getSession(true);
		User user = (User)session.getAttribute("loginUser");
		
		JSONObject json = new JSONObject();
		String email = "";
				
		if (user != null) {
			json.put("email", user.getEmail());
			email = json.toString();
			System.out.println("logIN User: " +email);
		}
		
		return email;
	}

	// Get User
	@RequestMapping
	public @ResponseBody String getUser(HttpServletRequest request, HttpSession session) throws Exception {
		
		session = request.getSession(true);
		User user = (User)session.getAttribute("loginUser");
		System.out.println("getUser : "+user);
		
		JSONObject json = new JSONObject();
		String logUser = "";
		
		if (user != null) {
			json.put("email", user.getEmail());
			json.put("propic", user.getProPic());
			
			System.out.println(json);
			logUser = json.toJSONString();
			System.out.println(logUser);
		}
		
		return logUser;
	}
	

	// Update User
	@Autowired ServletContext sc1;
	@RequestMapping
	public @ResponseBody String updateUser(HttpServletRequest request, HttpSession session, @RequestParam MultipartFile propic) throws Exception {
		
		User user = (User)session.getAttribute("loginUser");
		
		System.out.println("updateUser Before: "+user);
		String updateResult = "fail";
		
		if ( !(request.getParameter("email").equals(user.getEmail()) || request.getParameter("email").equals("")) ) {
			user.setEmail(request.getParameter("email"));
		}
		
		if ( !(request.getParameter("pwd").equals(user.getPwd()) || request.getParameter("pwd").equals("")) ) {
			user.setPwd(request.getParameter("pwd"));
		}
		
		if (propic.getSize() != 0) {
			// newPath Create
			String originFilename = propic.getOriginalFilename();
			System.out.println("originName ==>> " + originFilename);
		
  	  int lastDotPosition = originFilename.lastIndexOf(".");
  	  String extname = originFilename.substring(lastDotPosition);
  	  
  	  String newFilename = System.currentTimeMillis() + extname;
  	  System.out.println("newFileName :: " + newFilename);
  	  
  	  String realUploadPath = sc1.getRealPath("/propic");
  	  System.out.println("realUploadPath:: " + realUploadPath);
  	  
      File newPath = new File(realUploadPath + "/" + newFilename);
      System.out.println("newPath:: " + newPath);
  	  propic.transferTo(newPath);

  	  // oldPath Delete
  	  File oldPath = new File(realUploadPath + "/" + user.getProPic());
  	  System.out.println("old propic : " +oldPath);
  	  if(oldPath.exists()) {
  	  	oldPath.delete();
  	  	System.out.println("old Propic Delete...");
  	  }
  	  
  	  // newPath Create
  	  user.setProPic(newFilename);
		}
		
		System.out.println("updateUser Complete: "+user);
				
		if (user != null) {
			if ( userService.updateUser(user) ) {
				updateResult = "success";
			}
		}
		
		return updateResult;
	}
	
	// Delete User
	@Autowired ServletContext sc2;
	@RequestMapping
	public @ResponseBody String deleteUser(HttpSession session) throws Exception {
		
		User user = (User)session.getAttribute("loginUser");
		String result = "fail";
		
		
		if(user != null) {
			// 삭제 파일 저장
			File deleteFile = new File(sc2.getRealPath("/propic")+"/"+user.getProPic());
			System.out.println("삭제할 파일 :"+deleteFile);
			
			if( userService.deleteUser(user.getUserId()) ) {
				result = "success";
				if( deleteFile.exists()) {
					deleteFile.delete();
				}
				session.invalidate();
			}
		}
		
		System.out.println("Delete User:"+result);
		return result;
	}
	
	// User login
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public @ResponseBody String login(@RequestParam("email") String email, @RequestParam("pwd") String pwd, HttpSession session) throws Exception {
		
		User user = userService.getUser(email);
		String result = "false";
		
		System.out.println(user);
		if( user != null) {
			if( user.getPwd().equals(pwd) ) {
  			user.setActive(true);
  		}
  		if( user.isActive() ) {
  			session.setAttribute("loginUser", user);
  			result = "true";
  		}
		}
		System.out.println(user);
		System.out.println(result);
		System.out.println(session.getAttribute("loginUser"));
		
		return result;
	}
	
	// User logout
	@RequestMapping(value="/logout", method=RequestMethod.POST)
	public @ResponseBody String logout(HttpSession session) throws Exception {
		
		String result = "false";
		
		if(session.getAttribute("loginUser") != null) {
			session.removeAttribute("loginUser");
			result ="true";
		} 
		
		return result;
	}
	
	// user join
	@Autowired ServletContext sc;
	@RequestMapping(value="joinUser", method=RequestMethod.POST)
	public ModelAndView joinUser(HttpServletRequest request, @RequestParam MultipartFile propic) throws Exception {
		
		User user = new User();
		
		user.setEmail(request.getParameter("email"));
		user.setPwd(request.getParameter("pwd"));
		user.setUserAccess("user");
		
		if (propic.getSize() != 0) {
			String originFilename = propic.getOriginalFilename();
			System.out.println("originName ==>> " + originFilename);
		
  	  int lastDotPosition = originFilename.lastIndexOf(".");
  	  String extname = originFilename.substring(lastDotPosition);
  	  
  	  String newFilename = System.currentTimeMillis() + extname;
  	  System.out.println("newFileName :: " + newFilename);
  	  
  	  String realUploadPath = sc.getRealPath("/propic");
  	  System.out.println("realUploadPath:: " + realUploadPath);
  	  
      File newPath = new File(realUploadPath + "/" + newFilename);
      System.out.println("newPath:: " + newPath);
  	  propic.transferTo(newPath);
  	  
  	  user.setProPic(newFilename);
		} else {
			user.setProPic("default_propic.png");
		}
				
		System.out.println(user);
		if (0 != userService.insertUser(user)) {
			System.out.println("insert Success .. ");
		} else {
			System.out.println("insert Failed ..");
		}
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/");
		
		return mv;
		
	}
	
	// e-mail duplication checking
	@RequestMapping(value="/emailDuplicationcheck", method=RequestMethod.POST)
	public @ResponseBody String emailCheck(@RequestParam("email") String email) throws Exception {
		System.out.println(email);
		
		String result = "";
		if(userService.getUserCheck(email)) {
			result = "false";				
		} else {
			result = "true";
		}
		return result;
	}
	
	// password checking
	@RequestMapping
	public @ResponseBody String pwdCheck(@RequestParam String pwd, HttpSession session) throws Exception {
		
		User user = (User)session.getAttribute("loginUser");
		String result = "fail";

		if ( user != null) {
			if (user.getPwd().equals(pwd)) {
				result = "success";
			}	
		}
		
		return result;
	}
	
	// e-mail authorization 
/*	public void sendAuthMail(HttpServletRequest request, HttpServletResponse response) throws Exception {

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
	}*/
}
