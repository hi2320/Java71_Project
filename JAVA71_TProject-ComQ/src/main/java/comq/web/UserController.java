package comq.web;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	
	@RequestMapping
	public ModelAndView joinUserView() throws Exception {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/user/joinUser.html");
		
		return mv;
	}
	
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
				
		if (0 != userService.insertUser(user)) {
			System.out.println("insert Success .. ");
		} else {
			System.out.println("insert Failed ..");
		}
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("user/joinUser.html");
		
		return mv;
		
	}
	
	@RequestMapping
	public void test(HttpServletRequest request, @RequestParam MultipartFile photo) throws Exception {
		System.out.println("file testing...");
		if (photo.getSize() != 0) {
			String originFilename = photo.getOriginalFilename();
			System.out.println("originName ==>> " + originFilename);
		
  	  int lastDotPosition = originFilename.lastIndexOf(".");
  	  String extname = originFilename.substring(lastDotPosition);
  	  
  	  String newFilename = System.currentTimeMillis() + extname;
  	  System.out.println("newFileName :: " + newFilename);
  	  
  	  String realUploadPath = sc.getRealPath("/img");
  	  System.out.println("realUploadPath:: " + realUploadPath);
  	  
      File newPath = new File(realUploadPath + "/" + newFilename);
      
      System.out.println("newPath:: " + newPath);
  	  photo.transferTo(newPath);
		}
		
	}
	
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
	
}
