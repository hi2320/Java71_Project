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
	  int questionDBConut = curatorDao.getQuestionCount(curator.getCurId());
	  int questionCount = curator.getQuestionList().size();
	  
	  int answerDBCount = 0;
	  int answerCount = 0;
	  
	  
	  	
	  
	  
  }
	
}
