package comq.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import comq.service.CuratorService;

@Controller
@RequestMapping("/curator/*")
public class CuratorController {

	@Autowired
	@Qualifier("curatorServiceImpl")
	private CuratorService curatorService;
	public void setCuratorService(CuratorService curatorService) {
		this.curatorService = curatorService;
	}
	
	@RequestMapping
	public void curating() throws Exception {
		System.out.println(curatorService.getCurator(1));
		
	}
	
	public void curatorAdmin() throws Exception {
		
	}
}
