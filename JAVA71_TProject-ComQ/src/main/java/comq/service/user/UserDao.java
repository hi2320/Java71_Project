package comq.service.user;

import comq.domain.User;

public interface UserDao {
	
	public int insertUser(User user) throws Exception;
	
	public int getUserCheck(String email) throws Exception;
	public User getUser(int userId) throws Exception;
	
}