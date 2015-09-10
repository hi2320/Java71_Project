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
	
	@Autowired ServletContext sc;
	@RequestMapping
	public void test(HttpServletRequest request, @RequestParam MultipartFile photo) throws Exception {
		System.out.println("file testing...");
		if (photo.getSize() != 0) {
			String originFilename = photo.getOriginalFilename();
			System.out.println("originName ==>> " + originFilename);
		
			// 원래 파일 이름에서 확장명을 추출: 예) .pdf, .jpg
  	  int lastDotPosition = originFilename.lastIndexOf(".");
  	  String extname = originFilename.substring(lastDotPosition);
  	  
  	  // 새 파일명 준비
  	  // 다른 사람이 올린 파일 이름과 중돌을 방지하지 위해 밀리초를 기반으로 새 파일명을 만든다.
  	  String newFilename = System.currentTimeMillis() + extname;
  	  System.out.println("새파일명 :: " + newFilename);
  	  
  	  // 파일을 저장할 위치 알아내기
  	  String realUploadPath = sc.getRealPath("/img");
  	  System.out.println("파일저장위치:: " + realUploadPath);
  	  
  	  // 파일을 저장할 위치 + 새 파일명
      File newPath = new File(realUploadPath + "/" + newFilename);
      System.out.println("파일저장위치와 새파일명 :: " + newPath);
      // 임시 폴더에 있는 파일을 새 위치와 새 파일명으로 옮긴다.
  	  photo.transferTo(newPath);
  	  
		
		}
//		new FileUpload().userProfilePicture(request);
		
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
