package comq.common;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONObject;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import comq.domain.User;

public class LogonCheckInterceptor extends HandlerInterceptorAdapter {
	
	//Constructor
	public LogonCheckInterceptor() {
		System.out.println(this.getClass());
	}
	
	//Method
	public boolean preHandle (HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		JSONObject json = new JSONObject();
    out.print(json.toString());
    
    
		HttpSession session = request.getSession(true);
		User user = (User)session.getAttribute("loginUser");
		
		
    if (user != null) {
  		ObjectMapper objMapper = new ObjectMapper(); 
  		String jsonVal = objMapper.writeValueAsString(user);
  		System.out.println(jsonVal);
		} else {
			return false;
		}
		
		
		return true;
	}
}
