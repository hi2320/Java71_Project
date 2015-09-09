package comq.service.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import comq.domain.User;
import comq.service.user.UserDao;
import comq.service.user.UserService;

@Service("userServiceImpl")
public class UserServiceImpl implements UserService {

	@Autowired
	@Qualifier("userDaoImpl")
	private UserDao userDao;
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	public UserServiceImpl() {
		System.out.println(this.getClass());
	}
	
	@Override
  public int insertUser(User user) throws Exception {
		return userDao.insertUser(user);
  }
	
	@Override
  public boolean getUserCheck(String email) throws Exception {
		boolean result = false; 
		int resultDao = userDao.getUserCheck(email);
		
		if(resultDao != 0) {
			result = true;
		}
		
	  return result;
  }

	@Override
  public User getUser(int userId) throws Exception {
	  return userDao.getUser(userId);
  }

}
