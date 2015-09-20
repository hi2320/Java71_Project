package comq.service.user;

import comq.domain.User;

public interface UserService {
	
	public int insertUser(User user) throws Exception;
	
	public boolean getUserCheck(String email) throws Exception;
	public User getUser(String email) throws Exception;
	public boolean updateUser(User user) throws Exception;
	public boolean deleteUser(int userId) throws Exception;
	
}
