package comq.service.impl;

import java.util.List;

import org.junit.internal.runners.model.EachTestNotifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import comq.domain.Answer;
import comq.domain.Curator;
import comq.domain.Question;
import comq.service.CuratorDao;
import comq.service.CuratorService;

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
	
}
