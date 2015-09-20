package comq.service.user;

import comq.domain.User;

public interface UserDao {
	
	public int insertUser(User user) throws Exception;
	
	public int getUserCheck(String email) throws Exception;
	public User getUser(String email) throws Exception;
	public int updateUser(User user) throws Exception;
	public int deleteUser(int userId) throws Exception;
	
}
