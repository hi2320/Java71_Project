package comq.service.curator.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import comq.domain.Curator;
import comq.service.curator.CuratorDao;
import comq.service.curator.CuratorService;

@Service("curatorServiceImpl")
public class CuratorServiceImpl implements CuratorService {

	@Autowired
	@Qualifier("curatorDaoImpl")
	private CuratorDao curatorDao;

	public void setCuratorDao(CuratorDao curatorDao) {
		this.curatorDao = curatorDao;
	}

	public CuratorServiceImpl() {
		System.out.println(this.getClass());
	}


	@Override
	public Curator getCurator(int curId) throws Exception {
		
		Curator curator = curatorDao.getCurator(curId);
		curator.setQuestionList(curatorDao.getQuestionList(curId));

		for (int i = 0; i < curator.getQuestionList().size(); i++) {
			curator.getQuestionList()
			    		.get(i)
			    		.setAnswerList(curatorDao.getAnswerList(curator.getQuestionList().get(i).getQueId()));
		}
		return curator;
	}

	@Override
  public void updateCurator(Curator curator) throws Exception {
	  int questionDBCount = curatorDao.getQuestionCount(curator.getCurId());
	  int questionCount = curator.getQuestionList().size();
	  
	  int cs = Math.abs(questionCount - questionDBCount); 
	  curatorDao.updateCurator(curator);
	  
	  for (int i = 0; i < questionCount; i--) {
	  	
	  	if(questionCount == questionDBCount) { 
	  		curatorDao.updateQuestion(curator.getQuestionList().get(i));
	  		
	  	} else if(questionCount > questionDBCount) {
	  		curatorDao.updateQuestion(curator.getQuestionList().get(i));
	  		for (int j = questionCount; j < (questionCount+cs); j++) {
	  			curatorDao.addQuestion(curator.getQuestionList().get(j));
	  		}
	  		
	  	} else if(questionCount < questionDBCount) {
	  		curatorDao.updateQuestion(curator.getQuestionList().get(i));
	  		for (int j = questionDBCount; j > questionCount; j-- ) {
	  			curatorDao.deleteQuestion(queId)
	  		}
	  		
	  	}
	  	
	  }
	  
	  int answerDBCount = curatorDao.getAnswerCount(curator.getQuestionList().get(1).getQueId());
	  int answerCount = 0;
	  
	  
	  	
	  
	  
  }
	
}
