package comq.service.curator.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import comq.domain.Answer;
import comq.domain.Curator;
import comq.domain.Question;
import comq.service.curator.CuratorDao;

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

	// SELECT - domain
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
	
	// SELECT - DB_table count
	@Override
  public int getQuestionCount(int curId) throws Exception {
	  return sqlSession.selectOne("CuratingMapper.getQuestionCount", curId);
  }

	@Override
  public int getAnswerCount(int queId) throws Exception {
	  return sqlSession.selectOne("CuratingMapper.getAnswerCount", queId);
  }
	
	
	// INSERT
	@Override
  public int addCurator(Curator curator) throws Exception {
		return sqlSession.insert("CuratingMapper.insertCurator", curator);
  }

	@Override
  public int addQuestion(Question question) throws Exception {
		return sqlSession.insert("CuratingMapper.insertQuestion", question);
  }

	@Override
  public int addAnswer(Answer answer) throws Exception {
		return sqlSession.insert("CuratingMapper.insertAnswer", answer);
  }

	
	// UPDATE
	@Override
  public int updateCurator(Curator curator) throws Exception {
	  return sqlSession.update("CuratingMapper.updateCurator", curator);  
  }

	@Override
  public int updateQuestion(Question question) throws Exception {
	  return sqlSession.update("CuratingMapper.updateQuestion", question);
  }
	
	@Override
  public int updateAnswer(Answer answer) throws Exception {
	  return sqlSession.update("CuratingMapper.updateAnswer", answer);
  }

	// DELETE
	@Override
  public int deleteCurator(int curId) throws Exception {
	  return sqlSession.delete("CuratingMapper.deleteCurator", curId) ;
  }

	@Override
  public int deleteQuestion(int qIndex) throws Exception {
	  return sqlSession.delete("CuratingMapper.deleteQuestion", qIndex);
  }

	@Override
  public int deleteAnswer(int aIndex) throws Exception {
	  return sqlSession.delete("CuratingMapper.deleteAnswer", aIndex);
  }

	
}
