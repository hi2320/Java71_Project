package comq.service;

import java.util.List;

import comq.domain.Answer;
import comq.domain.Curator;
import comq.domain.Question;

public interface CuratorDao {
	
		public List<Answer> getAnswerList(int queId) throws Exception;
		
		public List<Question> getQuestionList(int curId) throws Exception;

		public Curator getCurator(int curId) throws Exception;
}
