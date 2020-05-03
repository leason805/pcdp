package com.boxun.pcdp.knowledge.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.boxun.estms.util.DateUtil;
import com.boxun.estms.util.StringUtil;
import com.boxun.pcdp.admin.entity.TCompany;
import com.boxun.pcdp.admin.service.ICompanyService;
import com.boxun.pcdp.basic.controller.BaseController;
import com.boxun.pcdp.knowledge.entity.KPaper;
import com.boxun.pcdp.knowledge.entity.KPaperItem;
import com.boxun.pcdp.knowledge.entity.KQuestion;
import com.boxun.pcdp.knowledge.entity.KSection;
import com.boxun.pcdp.knowledge.pojo.ExamSetPojo;
import com.boxun.pcdp.knowledge.pojo.SectionPojo;
import com.boxun.pcdp.knowledge.service.IPaperItemService;
import com.boxun.pcdp.knowledge.service.IPaperService;
import com.boxun.pcdp.knowledge.service.IQuestionService;
import com.boxun.pcdp.knowledge.service.ISectionService;

@Controller
@RequestMapping("/system/knowledge/paper")
public class PaperController extends BaseController{

private static final Logger LOGGER = Logger.getLogger(PaperController.class);
	
	@Autowired
	private IPaperService paperService;
	
	@Autowired
	private ISectionService sectionService;
	
	@Autowired
	private ICompanyService companyService;
	
	@Autowired
	private IPaperItemService paperitemService;
	
	@Autowired
	private IQuestionService questionService;
	
	@RequestMapping("/list")
	public String list(ModelMap modelMap){
		return "/knowledge/paper/list";
	}
	
	@RequestMapping("/show/{id}")
	public String show(ModelMap modelMap, @PathVariable Long id){
		LOGGER.info("Get show id: " + id);
		if(id != null){
			KPaper model = paperService.load(id);
			modelMap.addAttribute("model", model);
			
			List<SectionPojo> sections = sectionService.listSectionPojosById(model.getSection().getId());
			//List<KSection> sections = sectionService.listTops();
			modelMap.addAttribute("sections", sections);
		}
		
		List<TCompany> companys = this.companyService.list();
		modelMap.addAttribute("companys", companys);
		
		
		return "/knowledge/paper/show";
	}
	
	@RequestMapping("/listdata")
	public @ResponseBody Map<String, Object> listData(){
		LOGGER.info("Get list data.");
		List<KPaper> listdata = paperService.list();
		Map<String, Object> map  = new HashMap<String, Object>();
		map.put("sEcho", 1);  
		map.put("iTotalRecords", listdata.size());  
        map.put("iTotalDisplayRecords", listdata.size());  
		map.put("aaData", listdata);
		
		return map;
	}
	
	@RequestMapping("/create")
	public String create(ModelMap modelMap, KPaper model, @RequestParam Long companyId, @RequestParam Long sectionId){

		if(companyId != null){
			TCompany  company = this.companyService.load(companyId);
			model.setCompany(company);
		}
		
		if(sectionId != null){
			KSection section = this.sectionService.load(companyId);
			model.setSection(section);
		}
		
		List<KSection> sections = sectionService.list();
		Map<String, KSection> sectionMap = new HashMap<String, KSection>();
		for(KSection sec : sections){
			sectionMap.put(sec.getId().toString(), sec);
		}
		
		String paramName = null;
		String paramValue = null;
		String secId = null;
		KPaperItem item = null;
		KSection section = null;
		List<KPaperItem> itemList = new ArrayList<KPaperItem>();
		
		Enumeration<String> enu = this.request.getParameterNames();  
		while(enu.hasMoreElements()){  
			paramName = enu.nextElement();  
			paramValue = this.request.getParameter(paramName);
			if(StringUtil.isNotBlank(paramValue) && paramName.startsWith("item_")){
				secId = paramName.split("_")[1];
				
				item = new KPaperItem();
				if(sectionMap.get(secId) != null){
					section = sectionMap.get(secId);
					item.setSection(section);
					item.setPaper(model);
					item.setSize(Integer.parseInt(paramValue));
					itemList.add(item);
				}
			}
		}
		model.setItems(itemList);
		
		paperService.create(model);
		return "/success";
	}
	
	@RequestMapping("/update/{id}")
	public String update(ModelMap modelMap, @PathVariable Long id, KPaper model){
		LOGGER.info("Get update id: " + id);
		LOGGER.info("Update id: " + id);
		if(id != null){
			KPaper dbRecord = paperService.load(id);
			dbRecord.setName(model.getName());
			dbRecord.setLimits(model.getLimits());
			dbRecord.setMinutes(model.getMinutes());
			dbRecord.setScore(model.getScore());
			dbRecord.setSize(model.getSize());
			dbRecord.setPassscore(model.getPassscore());
			dbRecord.setDescription(model.getDescription());
			
			
			
			List<KSection> sections = sectionService.list();
			Map<String, KSection> sectionMap = new HashMap<String, KSection>();
			for(KSection sec : sections){
				sectionMap.put(sec.getId().toString(), sec);
			}
			
			String paramName = null;
			String paramValue = null;
			String sectionId = null;
			KPaperItem item = null;
			KSection section = null;
			List<KPaperItem> itemList = new ArrayList<KPaperItem>();
			
			Enumeration<String> enu = this.request.getParameterNames();  
			while(enu.hasMoreElements()){  
				paramName = enu.nextElement();  
				paramValue = this.request.getParameter(paramName);
				if(StringUtil.isNotBlank(paramValue) && paramName.startsWith("item_")){
					sectionId = paramName.split("_")[1];
					
					item = new KPaperItem();
					if(sectionMap.get(sectionId) != null){
						section = sectionMap.get(sectionId);
						item.setSection(section);
						item.setPaper(dbRecord);
						item.setSize(Integer.parseInt(paramValue));
						itemList.add(item);
					}
				}
			}
			
			List<KPaperItem> dbList = dbRecord.getItems();
			
			List<KPaperItem> newList = new ArrayList<KPaperItem>();
			List<KPaperItem> removeList = new ArrayList<KPaperItem>();
			
			if(dbList != null && !dbList.isEmpty()){
				boolean exist = false;
				//for new add
				for(KPaperItem it : itemList){
					exist = false;
					for(KPaperItem dbit : dbList){
						if(dbit.getSection().getId().equals(it.getSection().getId())){
							exist = true;
							dbit.setSize(it.getSize());
							this.paperitemService.update(dbit);
							break;
						}
					}
					if(!exist){
						this.paperitemService.create(it);
					}
				}
				
				//for remove
				for(KPaperItem dbit : dbList){
					exist = false;
					for(KPaperItem it : itemList){
						if(dbit.getSection().getId().equals(it.getSection().getId())){
							exist = true;
							break;
						}
					}
					if(!exist){
						dbit.setPaper(null);
						this.paperitemService.update(dbit);
					}
				}
			}
			else{
				dbRecord.setItems(itemList);
			}
			
			paperService.update(dbRecord);
			
		}
		return "/success";
	}
	
	@RequestMapping("/sections/{id}")
	public String sections(ModelMap modelMap, @PathVariable Long id){
		List<KSection> tops = sectionService.listTops(id);
		modelMap.addAttribute("tops", tops);
		
		if(tops != null && (tops.size() == 1)){
			List<SectionPojo> sections = sectionService.listSectionPojosById(tops.get(0).getId());
			modelMap.addAttribute("sections", sections);
		}

		return "/knowledge/paper/sections";
	}
	
	@RequestMapping("/questions/{id}")
	public String questions(ModelMap modelMap, @PathVariable Long id){
		LOGGER.info("Get questions id: " + id);
		
		List<SectionPojo> sections = sectionService.listSectionPojosById(id);
		//List<KSection> sections = sectionService.listTops();
		modelMap.addAttribute("sections", sections);
		
		
		return "/knowledge/paper/questions";
	}
	
	@RequestMapping("/prints/{id}")
	public String prints(ModelMap modelMap, @PathVariable Long id, Long size){
		KPaper paper = paperService.load(id);
		
		KSection section = null;
		
		List<ExamSetPojo> list = new ArrayList<ExamSetPojo>();
		for(int i=0; i<size; i++){
			ExamSetPojo pojo = new ExamSetPojo();
			list.add(pojo);
		}
		
		for(KPaperItem item : paper.getItems()){
			section = item.getSection();
			if(section != null){
				List<KQuestion> all = this.questionService.listBySectionId(section.getId());
				if(all.size() < item.getSize()){
					break;
				}
				for(ExamSetPojo pojo : list){
					pojo.addQuestions(random(item.getSize(), all));
				}
			}
		}
		
		String date = DateUtil.formatDateTime(new Date(), "yyyyMMdd");
		modelMap.addAttribute("date", date);
		modelMap.addAttribute("paper", paper);
		modelMap.addAttribute("list", list);

		return "/knowledge/paper/prints";
	}
	
	private List<KQuestion> random(Integer weight, List<KQuestion> list){
		List<KQuestion> result =  new ArrayList<KQuestion>();
		Random r = new Random();
		Set<KQuestion> set =  new HashSet<KQuestion>();
		while(set.size() < weight){
			set.add(list.get(r.nextInt(list.size())));
		}
		result.addAll(set);
		return result;
	}
}
