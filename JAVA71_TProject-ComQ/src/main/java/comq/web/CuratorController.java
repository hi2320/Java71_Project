package comq.web;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import comq.domain.Curator;
import comq.domain.Estimate;
import comq.domain.ProdList;
import comq.domain.User;
import comq.service.curator.CuratorService;
import comq.service.estimate.EstimateService;

@Controller
@RequestMapping("/curator/*")
public class CuratorController {

	@Autowired
	@Qualifier("curatorServiceImpl")
	private CuratorService curatorService;
	public void setCuratorService(CuratorService curatorService) {
		this.curatorService = curatorService;
	}
	
	@Autowired
	@Qualifier("estimateServiceImpl")
	private EstimateService estimateService;
	public void setEstimateService(EstimateService estimateService) {
		this.estimateService = estimateService;
	}
	
	public CuratorController() {
		System.out.println(this.getClass());
	}
	
	@RequestMapping
	public ModelAndView questionPageView(@RequestParam("curId") int curId,
			HttpSession session,
			HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView();

		session = request.getSession(true);
		User user = (User)session.getAttribute("loginUser");
		
		mv.setViewName("/user/question_page.jsp");
		
		Curator curator = null;
		curator = curatorService.getCurator(curId);

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
		
		mv.setViewName("redirect:/app/curator/changeQuestionPage?curId="+curators.getCurId());
		return mv;
	}
	
	@RequestMapping
	public ModelAndView curating(@RequestParam("curId") int curId,
	    @RequestParam String[] questionKey,
	    @RequestParam String[] answers) throws Exception {
		//search in danawa 
		String danawaProdSearchString = "http://api.danawa.com/api/search/product/info?"
				+ "key=9a8680fa6492d9f7c7ae1a509ce946e4&mediatype=json&charset=utf8&limit_no=1";
		
		ModelAndView mv = new ModelAndView();
		List<String> keywords = new ArrayList<String>();
		
		
		for(int i = 0; i < questionKey.length; i++) { // 질문 키워드
		  System.out.println(questionKey[i]);
		  if(!keywords.contains(questionKey[i])) {
		    keywords.add(questionKey[i]);
		  }
		}
		
		List<Map> objs = new ArrayList<Map>();
		List<String> urlKeywords = new ArrayList<String>();
		
		for(int i = 0; i < keywords.size(); i++) { // 답변 키워드에서 질문 키워드와 같은 ( 제품종류) 연결
			StringBuilder keywordPart = new StringBuilder(keywords.get(i));
			for(int j = 0; j < answers.length; j++) {
			 if(keywords.get(i).equals(answers[j].substring(0, answers[j].indexOf(":")))) {
			    String answerSpec = answers[j].substring(answers[j].indexOf(":")+1);
			    keywordPart.append(" ").append(answerSpec);
			  }
			}
			urlKeywords.add(keywordPart.toString());
			System.out.println("urlKeywords >> "+urlKeywords.get(i));
		}
//		JSONObject obj = (JSONObject)JSONValue.parse(br);
		List<String> resultKeywords = new ArrayList<String>();
		Map<String, String> map = null;
		JSONObject jsonData = null;
		for(int i = 0; i < urlKeywords.size(); i++) {
			if(urlKeywords.get(i).indexOf(' ') != -1) {
			
				String prodKind = urlKeywords.get(i).substring(0, urlKeywords.get(i).indexOf(' '));
				String spec = urlKeywords.get(i).substring(urlKeywords.get(i).indexOf(' '));
				System.out.println(prodKind + " :: " + spec);
				
				String vgaOnlyDanawa = null;// vga 관련 스트링
				if(prodKind.equals("VGA")) {
					vgaOnlyDanawa = "http://api.danawa.com/api/search/product/info?key=9a8680fa6492d9f7c7ae1a509ce946e4&mediatype=json&charset=utf8";
					vgaOnlyDanawa += "&limit_no=10";
					System.out.println("limit 해제 > " + vgaOnlyDanawa);
					resultKeywords.add(urlKeywords.get(i));
					
					StringBuilder danawaApi = new StringBuilder(vgaOnlyDanawa).append("&keyword=");
					StringBuilder newdanawaApi = new StringBuilder(danawaApi).append(URLEncoder.encode(urlKeywords.get(i), "UTF-8"));
					
					URL url = new URL(newdanawaApi.toString());
					BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
					
					jsonData = (JSONObject)JSONValue.parse(br);
					JSONArray jsonArray = (JSONArray)jsonData.get("productList");
					System.out.println("jsonArray >> " + jsonArray);
					for (int j = 0; j < jsonArray.size(); j++) {
						JSONObject prodList = (JSONObject) (jsonArray.get(j));
						System.out.println("prodList > " + prodList);
						String summary = (String) prodList.get("summary");
						System.out.println("summary > " + summary);

						if (summary.contains("정격파워")) { // 옵션 정보 찾을때까지 돌리기
														// 10개중 하나는 있겠지..
							String[] summarys = summary.split("\\|");
							objs.add(prodList);// VGA 중 정격파워 옵션 정보 있는 것 가져오기

							for (int k = 0; k < summarys.length; k++) {
								System.out.println("summarys >> " + summarys[k]);
								if (summarys[k].contains("정격파워")) {
									String ratedPowerNumericValue = (summarys[k].substring(4,summarys[k].indexOf("W") + 1)).trim();
									System.out.println("정격 파워 >> "+ ratedPowerNumericValue);
									resultKeywords.add(("파워 ATX " + ratedPowerNumericValue));
									String searchPower = URLEncoder.encode("파워 ATX " + ratedPowerNumericValue,"UTF-8");
									String powerSearch = danawaProdSearchString+ "&keyword=" + searchPower;

									URL powerUrl = new URL(powerSearch);
									BufferedReader powerBr = new BufferedReader(
											new InputStreamReader(powerUrl.openStream(),"UTF-8"));
									JSONObject powerJson = (JSONObject) JSONValue.parse(powerBr);
									map = (Map) powerJson.get("productList");
									objs.add(map);
									break;
								}
							}
							break;

						}
						
					}/* end VGA */
				} else {
					resultKeywords.add(urlKeywords.get(i));
					
					StringBuilder danawaApi = new StringBuilder(danawaProdSearchString).append("&keyword=");
					
					StringBuilder newdanawaApi = new StringBuilder(danawaApi).append(URLEncoder.encode(urlKeywords.get(i), "UTF-8"));
					URL url = new URL(newdanawaApi.toString());
					BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
					
					jsonData = (JSONObject)JSONValue.parse(br);
					map = (Map)jsonData.get("productList");
					
					objs.add(map);
				}
				
				
				if(prodKind.equals("CPU")) {
//					메인보드 가져오기  :: 메인보드 일반-ATX cpu의 키워드
					resultKeywords.add("메인보드 일반-ATX "+spec);
					String mainboard = URLEncoder.encode("메인보드 일반-ATX"+spec, "UTF-8");
					String mainboardSearch = danawaProdSearchString+"&keyword=" + mainboard;
					URL murl = new URL(mainboardSearch);
					BufferedReader mbr = new BufferedReader(new InputStreamReader(murl.openStream(), "UTF-8"));
					JSONObject mjsonData = (JSONObject)JSONValue.parse(mbr);
					map = (Map)mjsonData.get("productList");
					
					objs.add(map);
				}/* end CPU */
			} 
		}
		mv.addObject("data", objs);
		mv.addObject("keyword", resultKeywords);
		mv.addObject("curId", curId);
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
	public ModelAndView curatingMoreProd(@RequestParam String keyword, @RequestParam int startNum) throws Exception {
	  ModelAndView mv = new ModelAndView();
	  System.out.println(getClass());
	  System.out.println(keyword);
	  System.out.println(startNum + "\n");
	  
	  StringBuilder danawaUrl = new StringBuilder("http://api.danawa.com/api/search/product/info?key=9a8680fa6492d9f7c7ae1a509ce946e4&mediatype=json&charset=utf8&limit_no=10&keyword=");
	  danawaUrl.append(URLEncoder.encode(keyword, "UTF-8"))
	  .append("&start_no=").append(startNum);
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
	public @ResponseBody String createEstimate(@RequestParam int curId, @RequestParam String estName, 
			HttpSession session, HttpServletRequest request) throws Exception {
		System.out.println("createEstimate\n");
		session = request.getSession();
		User user = (User)session.getAttribute("loginUser");
		System.out.println(user);
		Estimate estimate = new Estimate(user.getUserId(), curId, estName);
		estimateService.addEstimate(estimate);
		estimate = estimateService.getLastEstimate();
		System.out.println(estimate);
		String returnEstId = estimate.getEstId()+"";
		
		return returnEstId;
	}
	
	@RequestMapping
	public @ResponseBody String curatingComplete(@ModelAttribute ProdList prod) throws Exception {
		System.out.println("큐레이팅 완료 데이터 받아오기");
//		ProdList prod = new ProdList(estId, prolKind, prodId, anlAns);
		System.out.println(prod);
		estimateService.addProdList(prod);
//		System.out.println(estId);
//		System.out.println(prodId);
//		System.out.println(anlAns);
		return "";
	}
	
	
//	My Estimate Data
	@RequestMapping
	public @ResponseBody String userEstimateList(
			@RequestParam int startNo,
			@RequestParam int endNo,
			HttpSession session,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
//		URLDecoder.decode(한글, "UTF-8"); // json 받을 때 한글 깨지면
		session = request.getSession();
		User user = (User)session.getAttribute("loginUser");
		System.out.println(startNo + " <::> " + endNo);
		List<Estimate> estimateList = estimateService.getEstimateList(user.getUserId(), startNo, endNo);

		List<String> jsonStr = new ArrayList<String>();
		
		for(int i = 0; i < estimateList.size(); i++) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("estId", estimateList.get(i).getEstId()+"");
			map.put("userId", estimateList.get(i).getUserId()+"");
			map.put("curId", estimateList.get(i).getCurId()+"");
			map.put("estName", URLEncoder.encode(estimateList.get(i).getEstName(), "UTF-8"));
			map.put("estDate", estimateList.get(i).getEstDate()+"");
			
			String estimateJsonMap = JSONObject.toJSONString(map);			
			
			System.out.println("estimateJsonMap >> " + estimateJsonMap);
			
			jsonStr.add(estimateJsonMap);
		}
		
		System.out.println(JSONArray.toJSONString(jsonStr));
		
		response.setCharacterEncoding("text/html;charset=UTF-8"); 
		
		return JSONArray.toJSONString(jsonStr);
	}
	
	@RequestMapping
	public @ResponseBody String getEstimate(@RequestParam int estId,
			HttpServletResponse response) throws Exception {
		List<ProdList> prodlist = estimateService.getProdList(estId);
		List<String> jsonProdlist = new ArrayList<String>();
		Map<String, String> estimateProd = new HashMap<String, String>();
		System.out.println(estId);
		
		for(int i = 0; i < prodlist.size(); i++) {
			System.out.println("product list :: \n"+prodlist.get(i));
			StringBuilder danawaUrl = new StringBuilder("http://api.danawa.com/api/main/product/info?key=78a56e90e3c30c7e7781b405fc44fb02&mediatype=json&charset=utf8&prodCode=");
			System.out.println(prodlist.get(i));
			int prodCode = prodlist.get(i).getProdId();
			danawaUrl.append(prodCode);
			
			URL url = new URL(danawaUrl.toString());
			BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
			JSONObject jsonData = (JSONObject)JSONValue.parse(br);

			JSONObject maker = (JSONObject)jsonData.get("maker");
			JSONObject summary = (JSONObject)jsonData.get("productDescription");
			JSONObject images= (JSONObject)jsonData.get("images");
			JSONArray imagelist = (JSONArray)images.get("image");
			
			String imageUrl = null;
			for(int j = 0; j < imagelist.size(); j++) {
				JSONObject searchImageSize = (JSONObject)imagelist.get(j);
				if(searchImageSize.get("name").equals("middle_1")) {
					imageUrl = (String)searchImageSize.get("url");
				}
			}
			if(imageUrl == null) {
				imageUrl = "";
			}
			
			estimateProd.put("prolId", prodlist.get(i).getProlId()+"");
			estimateProd.put("imageUrl", imageUrl);
			estimateProd.put("maker", URLEncoder.encode((String)maker.get("name"), "UTF-8"));
			estimateProd.put("prodName", URLEncoder.encode((String)jsonData.get("name"), "UTF-8"));
			estimateProd.put("price", (String)jsonData.get("minPrice"));
			estimateProd.put("summary", URLEncoder.encode((String)summary.get("importantOptionString"), "UTF-8"));
			estimateProd.put("prodKind", URLEncoder.encode(prodlist.get(i).getProlKind(), "UTF-8"));
			estimateProd.put("prodKeyword", URLEncoder.encode(prodlist.get(i).getAnlAns(),"UTF-8"));
			
			String jsonString = JSONObject.toJSONString(estimateProd);
			jsonProdlist.add(jsonString);
			
		}
		System.out.println(JSONArray.toJSONString(jsonProdlist));
		response.setCharacterEncoding("text/html;charset=UTF-8"); 
		
		return JSONArray.toJSONString(jsonProdlist);
	}
	
	@RequestMapping
	public @ResponseBody String updateEstimateProd(@ModelAttribute ProdList prod) throws Exception {
		System.out.println("updateEstimateProd >> " + prod);
		estimateService.updateProd(prod);
		return "";
	}
	@RequestMapping
	public @ResponseBody String deleteEstimate(@RequestParam int estId) throws Exception {
		System.out.println("deleteEstimate >> " + estId);
		estimateService.deleteEstimate(estId);
		return "";
	}
	@RequestMapping
	public @ResponseBody String deleteProdList(@RequestParam int prolId) throws Exception {
		System.out.println("deleteProdList >> " + prolId);
		estimateService.deleteProd(prolId);
		return "";
	}
}
