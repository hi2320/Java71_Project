package comq.service.user.impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import comq.domain.User;
import comq.service.user.UserDao;

@Repository("userDaoImpl")
public class UserDaoImpl implements UserDao {

	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;
	public void setSqlsession(SqlSession sqlsession) {
		this.sqlSession = sqlsession;
	}
	
	public UserDaoImpl() {
		System.out.println(this.getClass());
	}
	
	@Override
  public int insertUser(User user) throws Exception {
		return sqlSession.insert("UserMapper.insertUser", user);
  }

	@Override
  public int getUserCheck(String email) throws Exception {
		return sqlSession.selectOne("UserMapper.getUserJoinCheck", email);
  }

	@Override
  public User getUser(String email) throws Exception {
	  return sqlSession.selectOne("UserMapper.getUser", email);
  }

	@Override
  public int updateUser(User user) throws Exception {
	  return sqlSession.update("UserMapper.updateUser", user);
  }

	@Override
  public int deleteUser(int userId) throws Exception {
	  return sqlSession.delete("UserMapper.deleteUser", userId);
  }
	
}
