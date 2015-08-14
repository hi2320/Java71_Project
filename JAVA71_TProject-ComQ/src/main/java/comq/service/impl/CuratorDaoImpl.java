package comq.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import comq.domain.Answer;
import comq.domain.Curator;
import comq.domain.Question;
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

	@Override
	public Curator getCurator(int curId) throws Exception {
		return sqlSession.selectOne("CuratingMapper.getCurator", curId);
	}
	
	@Override
	public List<Question> getQuestionList(int curId) throws Exception {
		return sqlSession.selectList("CuratingMapper.getQuestionList", curId);
	}

	@Override
  public List<Answer> getAnswerList(int queId) throws Exception {
		return sqlSession.selectList("CuratingMapper.getAnswerList", queId);
		
  }

	
}
