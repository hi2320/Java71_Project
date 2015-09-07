package comq.service.user;

import comq.domain.User;

public interface UserService {
	
	public int insertUser(User user) throws Exception;
	
	public boolean getUserCheck(String email) throws Exception;
	public User getUser(int userId) throws Exception;
	
}
