package comq.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
	public void joinUser() throws Exception {
		User user = new User();
		
		user.setUserId(2222);
		user.setEmail("testEmail@google.com");
		user.setnName("테스트 유저");
		user.setPwd("1111");
		user.setUserKind("COMQ");
		user.setPhone(010-9999-9999);
		
		userService.insertUser(user);
		
	}
	
}
