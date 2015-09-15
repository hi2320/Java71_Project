package comq.web;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@RequestMapping
	public ModelAndView questionPageView(@RequestParam("curId") int curId,
			HttpSession session) throws Exception {
		ModelAndView mv = new ModelAndView();

		//회원 검사 관리자인가 유저인가
		boolean isUser = true;
		if(curId == 0) {
			isUser = false;
		}
		Curator curator = null;
		if(isUser) {
			mv.setViewName("/user/question_page.jsp");
			curator = curatorService.getCurator(curId);
		} else {
			mv.setViewName("/manager/question_manage.jsp");
			curator = curatorService.getCurator(1);
		}
		mv.addObject("list", curator);
		
		return mv;
	}
	
	@RequestMapping
	public ModelAndView changeQuestionPage(@RequestParam("curId") int curId) throws Exception {
		ModelAndView mv = new ModelAndView();
		Curator curator = curatorService.getCurator(curId);
		System.out.println(curatorService.getCurator(curId));
		
		mv.setViewName("/manager/question_manage.jsp");
		mv.addObject("list", curator);
		return mv;
	}
	
	@RequestMapping
	public ModelAndView updateQuestionPage(@ModelAttribute Curator curators) throws Exception {
		ModelAndView mv = new ModelAndView();
		System.out.println("질문 수정 합니다");
//		질문페이지 수정(del >> ins)
		curatorService.updateCurator(curators);
		
//		mv.setViewName("redirect:/app/curator/questionPageView?curId="+curators.getCurId());
		mv.setViewName("redirect:/app/curator/changeQuestionPage?curId="+curators.getCurId());
		return mv;
	}
	
	@RequestMapping
	public ModelAndView curating(@RequestParam("curId") int curId,
	    @RequestParam String[] questionKey,
	    @RequestParam String[] answers) throws Exception {
		//search in danawa 
		ModelAndView mv = new ModelAndView();
		List<String> keywords = new ArrayList<String>();
		
		
		for(int i = 0; i < questionKey.length; i++) {
		  System.out.println(questionKey[i]);
		  if(!keywords.contains(questionKey[i])) {
		    keywords.add(questionKey[i]);
		  }
		}
		
//		String[] keywords = {"모니터", "1920", "마우스", "게이밍"};
		List<Map> objs = new ArrayList<Map>();
		List<String> urlKeywords = new ArrayList<String>();
		
		for(int i = 0; i < keywords.size(); i++) {
			StringBuilder sb = new StringBuilder();
			StringBuilder keywordPart = new StringBuilder(keywords.get(i));
			sb.append("http://api.danawa.com/api/search/product/info?key=9a8680fa6492d9f7c7ae1a509ce946e4&mediatype=json&charset=utf8&limit_no=1&keyword=")
			.append(URLEncoder.encode(keywords.get(i)+" ", "UTF-8"));
			for(int j = 0; j < answers.length; j++) {
			 if(keywords.get(i).equals(answers[j].substring(0, answers[j].indexOf(":")))) {
			    String answerSpec = answers[j].substring(answers[j].indexOf(":")+1);
			    sb.append(URLEncoder.encode(answerSpec+" ", "UTF-8"));
			    keywordPart.append(" ").append(answerSpec);
			  }
			}
			urlKeywords.add(keywordPart.toString());
			URL url = new URL(sb.toString());
			BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
			
			JSONObject jsonData = (JSONObject)JSONValue.parse(br); 
			Map<String,String> map = (Map)jsonData.get("productList");
			
			objs.add(map);
			
			System.out.println(i+":: =================\n");
		}
//		JSONObject obj = (JSONObject)JSONValue.parse(br);
		
		mv.addObject("data", objs);
		mv.addObject("keyword", urlKeywords);
		mv.setViewName("/user/curating_complete.jsp");
		
		return mv;
	}
	
	@RequestMapping
	public ModelAndView curatingChangeProd(@RequestParam String keyword) throws Exception {
	  ModelAndView mv = new ModelAndView();
	  System.out.println(keyword);
	  StringBuilder danawaUrl = new StringBuilder("http://api.danawa.com/api/search/product/info?key=9a8680fa6492d9f7c7ae1a509ce946e4&mediatype=json&charset=utf8&limit_no=10&keyword=");
	  danawaUrl.append(URLEncoder.encode(keyword, "UTF-8"));
	  System.out.println(danawaUrl.toString());
	  URL url = new URL(danawaUrl.toString());
    BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
    
    JSONObject jsonData = (JSONObject)JSONValue.parse(br); 
    System.out.println(jsonData);
    System.out.println("\n===================\n");
    mv.addObject("data", jsonData.get("productList"));
    mv.setViewName("/user/curating_products.jsp");
    return mv;
  }
	
	@RequestMapping
	public ModelAndView getListAppend(@RequestParam String keyword, @RequestParam int startNo) throws Exception {
	  ModelAndView mv = new ModelAndView();
	  System.out.println(keyword);
	  StringBuilder danawaUrl = new StringBuilder("http://api.danawa.com/api/search/product/info?key=9a8680fa6492d9f7c7ae1a509ce946e4&mediatype=json&charset=utf8");
	  danawaUrl.append("&start_no=").append(startNo).append("&keyword=").append(keyword);
	  
	  URL url = new URL(danawaUrl.toString());
    BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
    
    JSONObject jsonData = (JSONObject)JSONValue.parse(br); 
    System.out.println(jsonData);
    System.out.println("\n===================\n");
    mv.addObject("data", jsonData.get("productList"));
	  
	  mv.setViewName("/user/curating_products.jsp");
    return mv;
  }
	
	@RequestMapping
	public ModelAndView curatingComplete() throws Exception {
//		curating complete. move to my curating list
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("/index.html");
		return mv;
	}
	
}
