package comq.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import comq.domain.Curator;
import comq.service.curator.CuratorService;

@Controller
@RequestMapping("/curator/*")
public class CuratorController {

	@Autowired
	@Qualifier("curatorServiceImpl")
	private CuratorService curatorService;
	public void setCuratorService(CuratorService curatorService) {
		this.curatorService = curatorService;
	}
	
	public CuratorController() {
		System.out.println(this.getClass());
	}
	
	@RequestMapping
	public void curating() throws Exception {
		System.out.println(curatorService.getCurator(1));
		
	}
	
	@RequestMapping
	public void curatorAdmin(@ModelAttribute Curator curators) throws Exception {
		//admin 페이지에서 값 넘겨받기
		Curator curator = new Curator();
		for(int i = 0; i < curators.getQuestionList().size(); i++) {
			System.out.println("질문문장::"+curators.getQuestionList().get(i).getqSente());
			System.out.println("제품종류::"+curators.getQuestionList().get(i).getqProd());
			System.out.println("답변유형::"+curators.getQuestionList().get(i).getqType());
			
			for(int j = 0; j < curators.getQuestionList().get(i).getAnswerList().size(); j++) {
				System.out.println("답변문장::"+curators.getQuestionList().get(i).getAnswerList().get(j).getaSente());
				System.out.println("답변스펙::"+curators.getQuestionList().get(i).getAnswerList().get(j).getaSpec());
			}
			System.out.println("==============================================\n");
		}
		System.out.println(curators);
		
		curatorService.updateCurator(curators);
	}
	
	@RequestMapping
	public ModelAndView curatorAdminView() throws Exception {
		ModelAndView mv = new ModelAndView();
		Curator curator = curatorService.getCurator(1);
		System.out.println(curatorService.getCurator(1));
		
		mv.setViewName("/manager/question_manage.jsp");
		mv.addObject("list", curator);
		return mv;
	}
	
}
