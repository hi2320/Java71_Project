package comq.service.curator;

import java.util.List;

import comq.domain.Answer;
import comq.domain.Curator;
import comq.domain.Question;

public interface CuratorDao {
	
	// SELECT
	public Curator getCurator(int curId) throws Exception;
	public List<Question> getQuestionList(int curId) throws Exception;
	public List<Answer> getAnswerList(int queId) throws Exception;
	
	public int getQuestionCount(int curId) throws Exception;
	public int getAnswerCount(int queId) throws Exception;
	
	// INSERT
	public int addCurator(Curator curator) throws Exception;
	public int addQuestion(Question question) throws Exception;
	public int addAnswer(Answer answer) throws Exception;
	
	// UPDATE
	public int updateCurator(Curator curator) throws Exception;
	public int updateQuestion(Question question) throws Exception;
	public int updateAnswer(Answer answer) throws Exception;
	
	// DELETE
	public int deleteCurator(int curId) throws Exception;
	public int deleteQuestion(int queId) throws Exception;
	public int deleteAnswer(int ansId) throws Exception;
	
}
