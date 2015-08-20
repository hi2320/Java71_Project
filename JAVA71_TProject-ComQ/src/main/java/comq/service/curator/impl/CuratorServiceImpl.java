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

		// 인덱스 넘버링 초기값 설정
		int cIndex = curator.getCurId();
		int qIndex = Integer.parseInt(String.valueOf(cIndex)+"00");
		int aIndex = Integer.parseInt(String.valueOf(cIndex)+"000");
		
		
		// 넘버링된 인덱스 DB delete
		curatorDao.deleteAnswer(aIndex);
		curatorDao.deleteQuestion(qIndex);
			
		System.out.println("딜리트 완료?");
		Curator curatorTest = this.getCurator(cIndex);
		System.out.println(curatorTest);
		
		System.out.println(cIndex);
		System.out.println(qIndex);
		System.out.println(aIndex);
		
		// 인덱스 넘버링 세팅
		for (int i = 0; i < curator.getQuestionList().size(); i++) {
			curator.getQuestionList().get(i).setCurId(cIndex);
			curator.getQuestionList().get(i).setQueId(qIndex);
			System.out.println(qIndex);
			System.out.println(curator.getQuestionList());
			for (int j = 0; j < curator.getQuestionList().get(i).getAnswerList().size(); j++) {
				curator.getQuestionList().get(i).getAnswerList().get(j).setQueId(qIndex);
				curator.getQuestionList().get(i).getAnswerList().get(j).setAnsId(aIndex++);
				System.out.println(curator.getQuestionList().get(i).getAnswerList().get(j));
				System.out.println(aIndex);
			}
			qIndex++;
			System.out.println(curator);
		}
		
	  // 넘버링된 인덱스 DB insert
		for (int i = 0; i < curator.getQuestionList().size(); i++) {
			curatorDao.addQuestion(curator.getQuestionList().get(i));
			for (int j = 0; j < curator.getQuestionList().get(i).getAnswerList().size(); j++) {
				curatorDao.addAnswer(curator.getQuestionList().get(i).getAnswerList().get(j));
			}
		}
		
  }
	
}
