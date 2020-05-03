package com.boxun.pcdp.knowledge.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.boxun.pcdp.admin.entity.TCompany;
import com.boxun.pcdp.admin.service.ICompanyService;
import com.boxun.pcdp.basic.controller.BaseController;
import com.boxun.pcdp.knowledge.entity.KSection;
import com.boxun.pcdp.knowledge.service.IProjectService;
import com.boxun.pcdp.knowledge.service.ISectionService;

@Controller
@RequestMapping("/system/knowledge/section")
public class SectionController extends BaseController{

private static final Logger LOGGER = Logger.getLogger(SectionController.class);
	
	@Autowired
	private ISectionService sectionService;
	
	@Autowired
	private IProjectService projectService;
	
	@Autowired
	private ICompanyService companyService;
	
	@RequestMapping("/list")
	public String list(ModelMap modelMap, Long companyId){
		
		List<TCompany> companys = this.companyService.list();
		modelMap.addAttribute("companys", companys);
		
		if(companyId == null){
			companyId = companys.get(0).getId();
		}
		
		List<KSection> list = new ArrayList<KSection>();
		List<KSection> tops = sectionService.listTops();
		if(tops != null && !tops.isEmpty()){	
			for(KSection section : tops){
				if(section.getCompany().getId().equals(companyId)){
					list.add(section);
				}
			}
		}
		
		modelMap.addAttribute("companyId", companyId);
		modelMap.addAttribute("list", list);
		
		return "/knowledge/section/list";
	}
	
	@RequestMapping("/show/{id}")
	public String show(ModelMap modelMap, @PathVariable Long id){
		LOGGER.info("Get show id: " + id);
		
		List<TCompany> companys = this.companyService.list();
		modelMap.addAttribute("companys", companys);
		
		if(id != null){
			KSection model = sectionService.load(id);
			modelMap.addAttribute("model", model);
		}
		
		return "/knowledge/section/show";
	}
	
	@RequestMapping("/add/{id}")
	public String add(ModelMap modelMap, @PathVariable Long id){
		LOGGER.info("Get id: " + id);
		if(id != null){
			KSection model = sectionService.load(id);
			modelMap.addAttribute("model", model);
		}
		return "/knowledge/section/add";
	}
	
	@RequestMapping("/create")
	public String create(ModelMap modelMap, @RequestParam Long companyId, @RequestParam Long parentId, KSection model){
	//public String create(ModelMap modelMap, @RequestParam Long parentId, KSection model){
		if(companyId != null && companyId > 0){
			TCompany  company = this.companyService.load(companyId);
			model.setCompany(company);
		}
		if(parentId != null && parentId > 0){
			KSection parent = sectionService.load(parentId);
			model.setParent(parent);
			model.setPsequence(parent.getSequence());
			model.setSequence(parent.getSequence() + "." + model.getNumber());
			model.setLevel(parent.getLevel() + 1);
		}
		else{
			model.setSequence(model.getNumber()+"");
		}
		sectionService.create(model);
		modelMap.addAttribute("refresh", "page");
		modelMap.addAttribute("url", "/system/knowledge/section/list");
		return "/success";
	}
	
	@RequestMapping("/update/{id}")
	//public String update(ModelMap modelMap, @PathVariable Long id, @RequestParam Long companyId, KSection model){
	public String update(ModelMap modelMap, @PathVariable Long id, KSection model){
		LOGGER.info("Get update id: " + id);
		LOGGER.info("Update id: " + id);
		if(id != null){
			KSection dbRecord = sectionService.load(id);
			
			if(dbRecord.getParent() != null){
				dbRecord.setSequence(dbRecord.getParent().getSequence() + "." + model.getNumber());
			}
			else{
				dbRecord.setSequence(model.getNumber()+"");
			}
			
			dbRecord.setNumber(model.getNumber());
			dbRecord.setName(model.getName());
			dbRecord.setDescription(model.getDescription());
			
//		if(companyId != null && companyId > 0){
//			TCompany  company = this.companyService.load(companyId);
//			dbRecord.setCompany(company);
//		}
			
			sectionService.update(dbRecord);
			
			modelMap.addAttribute("refresh", "page");
			modelMap.addAttribute("url", "/system/knowledge/section/list");
		}
		return "/success";
	}
	
	@RequestMapping("/delete/{id}")
	public String delete(ModelMap modelMap, @PathVariable Long id){
		LOGGER.info("Delete id: " + id);
		if(id != null){
			KSection dbRecord = sectionService.load(id);
			sectionService.delete(dbRecord);
			
			modelMap.addAttribute("refresh", "page");
			modelMap.addAttribute("url", "/system/knowledge/section/list");
		}
		return "/admin/success";
	}
}
