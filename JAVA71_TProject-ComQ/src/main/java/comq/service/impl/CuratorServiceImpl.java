package comq.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

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
	
}
