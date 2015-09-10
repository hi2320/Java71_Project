package comq.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import comq.common.FileUpload;
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
	
	@RequestMapping
	public ModelAndView joinUser() throws Exception {
		
		User user = new User();
		
		user.setUserId(2223);
		user.setEmail("testEmail@google.com");
		user.setnName("ensis");
		user.setPwd("1111");
		user.setUserKind("COMQ");
		user.setPhone("010-9999-9999");
		user.setProPic(null);
		
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
	public void test(HttpServletRequest request) throws Exception {
		new FileUpload().userProfilePicture(request);
		
	}
	
	@RequestMapping(value="/checkId", method=RequestMethod.POST)
	public @ResponseBody String emailCheck(@RequestParam("id") String id) throws Exception {
	
		System.out.println(id);
		
		String result = "";
		if(userService.getUserCheck(id)) {
			result = "ture";				
		} else {
			result = "flase";
		}
		
		return result;
	}
	
}
