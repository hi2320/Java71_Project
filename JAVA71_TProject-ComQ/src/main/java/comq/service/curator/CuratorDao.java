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
		
	// INSERT
	public int addCurator(int curId) throws Exception;
	public int addQuestion(int curId) throws Exception;
	public int addAnswer(int queId) throws Exception;
	
	// UPDATE
	public int upDateCurator(int curId) throws Exception;
	public int upDateQuestion(int curId) throws Exception;
	public int upDateAnswer(int queId) throws Exception;
	
	
}
