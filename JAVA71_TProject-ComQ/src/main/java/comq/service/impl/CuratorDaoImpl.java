package comq.service.impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import comq.domain.Curating;
import comq.service.CuratorDao;

@Repository("curatorDaoImpl")
public class CuratorDaoImpl implements CuratorDao {

	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	public CuratorDaoImpl() {
		System.out.println(this.getClass());
	}
	
	
	public Curating getCurating(int curid) {
		
		
		
		Curating curating = null;
		return curating;
	}
	
}
