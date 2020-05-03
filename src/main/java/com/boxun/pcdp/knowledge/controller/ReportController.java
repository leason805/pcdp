package com.boxun.pcdp.knowledge.controller;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.boxun.estms.entity.Const;
import com.boxun.estms.pojo.UserInfo;
import com.boxun.pcdp.admin.entity.TCompany;
import com.boxun.pcdp.admin.service.ICompanyService;
import com.boxun.pcdp.basic.controller.BaseController;
import com.boxun.pcdp.knowledge.entity.KArrange;
import com.boxun.pcdp.knowledge.entity.KArrangeUser;
import com.boxun.pcdp.knowledge.entity.KExamAnswer;
import com.boxun.pcdp.knowledge.entity.KPaperItem;
import com.boxun.pcdp.knowledge.entity.KSection;
import com.boxun.pcdp.knowledge.pojo.ReportItemPojo;
import com.boxun.pcdp.knowledge.pojo.ReportPojo;
import com.boxun.pcdp.knowledge.service.IArrangeService;
import com.boxun.pcdp.knowledge.service.IArrangeUserService;
import com.boxun.pcdp.knowledge.service.IExamScoreService;
import com.boxun.pcdp.knowledge.service.IPaperService;
import com.boxun.pcdp.knowledge.service.ISectionService;

@Controller
@RequestMapping("/system/knowledge/report")
public class ReportController extends BaseController{

	@Autowired
	private IArrangeUserService karrangeuserService;
	
	@Autowired
	private ISectionService sectionService;
	
	@Autowired
	private ICompanyService companyService;
	
	@Autowired
	private IPaperService paperService;
	
	@Autowired
	private IArrangeService karrangeService;
	
	@Autowired
	private IExamScoreService examScoreService;
	
	@RequestMapping("/list")
	public String list(ModelMap modelMap){
		UserInfo userInfo = (UserInfo)this.session.getAttribute("userInfo");
		Long userId = 0L;
		if(userInfo != null){
			userId = userInfo.getUserId();
		}

		List<KArrangeUser> list = karrangeuserService.list4User(userId, true);
		
		modelMap.addAttribute("list", list);
		
		return "/knowledge/report/list";
	}
	
	@RequestMapping("/mine")
	public String mine(ModelMap modelMap, Long arrId){
		UserInfo userInfo = (UserInfo)this.session.getAttribute("userInfo");
		Long userId = 0L;
		if(userInfo != null){
			userId = userInfo.getUserId();
		}
		
		List<KArrangeUser> arrList = karrangeuserService.list4User(userId, true);
		
		//SELECT COUNT(*), sec.id secid, an.correct_type TYPE FROM kn_exam_answer an, kn_question que, kn_section sec WHERE an.question_id = que.id AND que.section_id = sec.id GROUP BY sec.id, an.correct_type
		KArrangeUser arrUser = null;
		
		if(arrId == null){
			if(arrList != null && !arrList.isEmpty()){
				arrUser = arrList.get(0);
			}
		}
		else{
			arrUser = this.karrangeuserService.load(arrId);
		}
		
		if(arrUser != null){
			List<Map<String, Object>> scoreMapList = this.examScoreService.listScoreByArrangeUser(arrUser.getId());
			
			List<KSection> list = sectionService.listTops();
			List<KSection> secondLevelList = new ArrayList<KSection>();
			Map<Long, List<String>> idMaps = new HashMap<Long, List<String>>();
			Map<Long, String> nameMaps = new HashMap<Long, String>();
			
			if(list != null && !list.isEmpty()){
				KSection parent = null;
				for(KSection section : list){
					if(arrUser.getArrange().getPaper().getSection().getId().equals(section.getId())){
						parent = section;
					}
				}
				for(KSection sec : parent.getChildren()){
					for(KSection child : sec.getChildren()){
						secondLevelList.add(child);
					}
				}
			}
			
			for(KSection sec : secondLevelList){
				idMaps.put(sec.getId(), convert2Str(this.sectionService.listChildIds(sec.getId(), true)));
				nameMaps.put(sec.getId(), sec.getName());
			}
			
			ReportItemPojo item = null;
			ReportPojo pojo = new ReportPojo();
			pojo.setName(arrUser.getArrange().getPaper().getName());
			
//			for(KPaperItem pi : arrUser.getArrange().getPaper().getItems()){
//				
//				item = new ReportItemPojo();
//				item.setName(pi.getSection().getName());
//				
//				for(Map<String, Object> map : scoreMapList){
//					if(map.get("SECTION").toString().equals(pi.getSection().getId().toString())){
//						item.addTotal(((BigInteger)map.get("SIZE")).intValue());
//						if(Const.CorrectType.YES.toString().equals(map.get("CORRECT").toString())){
//							item.addCorrectSize(((BigInteger)map.get("SIZE")).intValue());
//						}
//						else{
//							item.addIncorrectSize(((BigInteger)map.get("SIZE")).intValue());
//						}
//					}
//				}
//
//				item.calRate();
//				pojo.addItem(item);
//			}
			

			Map<Long, ReportItemPojo> itemMaps = new HashMap<Long, ReportItemPojo>(); 
			
			for(Map<String, Object> map : scoreMapList){
				String sectionId = map.get("SECTION").toString();
				for(Long key : idMaps.keySet()){
					List<String> ids = idMaps.get(key);
					if(ids.contains(sectionId)){
						item = itemMaps.get(key);
						if(item == null){
							item = new ReportItemPojo();
							item.setName(nameMaps.get(key));
							itemMaps.put(key, item);
						}
						item.addTotal(((BigInteger)map.get("SIZE")).intValue());
						if(Const.CorrectType.YES.toString().equals(map.get("CORRECT").toString())){
							item.addCorrectSize(((BigInteger)map.get("SIZE")).intValue());
						}
						else{
							item.addIncorrectSize(((BigInteger)map.get("SIZE")).intValue());
						}
					}
				}
			}
			
			Integer largest = 10 ;
			for(ReportItemPojo it : itemMaps.values()){
				it.calRate();
				pojo.addItem(it);
				
				if(item.getTotal() > largest){
					largest = item.getTotal();
				}
			}
			
			
			
			
//			for(KExamAnswer ans : arrUser.getScore().getAnswers()){
//				for(Long key : maps.keySet()){
//					List<Long> ids = maps.get(key);
//					if(ids.contains(ans.getQuestion().getSection().getId())){
//						
//						
//						
//						item = itemMaps.get(key);
//						if(item == null){
//							item = new ReportItemPojo();
//							item.setName(ans.getQuestion().getSection().getName());
//							itemMaps.put(key, item);
//						}
//						item.increaseTotal();
//						if(Const.CorrectType.YES.equals(ans.getCorrectType())){
//							item.increaseCorrectSize();
//						}
//						else{
//							item.increaseIncorrectSize();
//						}
//						
//						//item.calRate();
//					}
//				}
//			}
//			for(ReportItemPojo item : itemMaps.values()){
//				item.calRate();
//				pojo.addItem(item);
//			}

			
			modelMap.addAttribute("list", arrList);
			modelMap.addAttribute("arrId", arrUser.getId());
			modelMap.addAttribute("model", pojo);
			modelMap.addAttribute("largest", largest);
		}
		
		return "/knowledge/report/mine";
	}
	
	
	@RequestMapping("/allreport")
	public String report(ModelMap modelMap, Long arrId, Long companyId){
		List<TCompany> companys = this.companyService.list();
		modelMap.addAttribute("companys", companys);
		
		List<KArrange> arranges = karrangeService.list();
		modelMap.addAttribute("arranges", arranges);
		Integer largest = 100 ;
		if(arrId != null){
			KArrange arrange = this.karrangeService.load(arrId);
			if(arrange != null){
				//Long paperId = arrange.getPaper().getId();
				List<Map<String, Object>> scoreMapList = this.examScoreService.listScoreByArrange(arrange.getId());
				
				List<KSection> list = sectionService.listTops();
				List<KSection> secondLevelList = new ArrayList<KSection>();
				Map<Long, List<String>> idMaps = new HashMap<Long, List<String>>();
				Map<Long, String> nameMaps = new HashMap<Long, String>();
				
				if(list != null && !list.isEmpty()){
					KSection parent = arrange.getPaper().getSection();
					for(KSection sec : parent.getChildren()){
						for(KSection child : sec.getChildren()){
							secondLevelList.add(child);
						}
					}
				}
				
				for(KSection sec : secondLevelList){
					idMaps.put(sec.getId(), convert2Str(this.sectionService.listChildIds(sec.getId(), true)));
					nameMaps.put(sec.getId(), sec.getName());
				}
				
				ReportItemPojo item = null;
				ReportPojo pojo = new ReportPojo();
				pojo.setName(arrange.getPaper().getName());
				
				
				Map<Long, ReportItemPojo> itemMaps = new HashMap<Long, ReportItemPojo>(); 
				
				for(Map<String, Object> map : scoreMapList){
					String sectionId = map.get("SECTION").toString();
					for(Long key : idMaps.keySet()){
						List<String> ids = idMaps.get(key);
						if(ids.contains(sectionId)){
							item = itemMaps.get(key);
							if(item == null){
								item = new ReportItemPojo();
								item.setName(nameMaps.get(key));
								itemMaps.put(key, item);
							}
							item.addTotal(((BigInteger)map.get("SIZE")).intValue());
							if(Const.CorrectType.YES.toString().equals(map.get("CORRECT").toString())){
								item.addCorrectSize(((BigInteger)map.get("SIZE")).intValue());
							}
							else{
								item.addIncorrectSize(((BigInteger)map.get("SIZE")).intValue());
							}
						}
					}
				}
				
				
				for(ReportItemPojo it : itemMaps.values()){
					it.calRate();
					pojo.addItem(it);
					
					if(item.getTotal() > largest){
						largest = item.getTotal();
					}
				}

				modelMap.addAttribute("model", pojo);
				
			}
			modelMap.addAttribute("arrId", arrId);
		}
		if(companyId != null){
			modelMap.addAttribute("companyId", companyId);
		}
		modelMap.addAttribute("largest", largest);
		return "/knowledge/report/allreport";
	}
	
	private List<String> convert2Str(List<Long> list){
		List<String> result = new ArrayList<String>();
		if(list != null && !list.isEmpty()){
			for(Long val : list){
				result.add(val.toString());
			}
		}
		return result;
	}
	
	@RequestMapping("/datas")
	public String datas(ModelMap modelMap, Long arrId, Long companyId){
		List<TCompany> companys = this.companyService.list();
		modelMap.addAttribute("companys", companys);
		
		List<KArrange> arranges = karrangeService.list();
		modelMap.addAttribute("arranges", arranges);
		
		if(arrId != null){
			KArrange arrange = this.karrangeService.load(arrId);
			List<KArrangeUser> list = this.karrangeuserService.list(arrange.getId());
			modelMap.addAttribute("list", list);
		}
		
		if(arrId != null){
			modelMap.addAttribute("arrId", arrId);
		}
		
		if(companyId != null){
			modelMap.addAttribute("companyId", companyId);
		}
		return "/knowledge/report/datas";
	}
}
