package com.boxun.pcdp.archive.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.boxun.pcdp.archive.entity.ACertCategory;
import com.boxun.pcdp.archive.service.ICertCategoryService;
import com.boxun.pcdp.basic.controller.BaseController;

@Controller
@RequestMapping("/system/archive/certcategory")
public class CertCategoryController extends BaseController{

private static final Logger LOGGER = Logger.getLogger(CertCategoryController.class);
	
	@Autowired
	private ICertCategoryService certcategoryService;
	
	@RequestMapping("/list")
	public String list(ModelMap modelMap){
		List<ACertCategory> list = certcategoryService.listTops();
		modelMap.addAttribute("list", list);
		return "/archive/certcategory/list";
	}
	
	
	@RequestMapping("/show/{id}")
	public String show(ModelMap modelMap, @PathVariable Long id){
		LOGGER.info("Get show id: " + id);
		if(id != null){
			ACertCategory model = certcategoryService.load(id);
			modelMap.addAttribute("model", model);
		}
		
		return "/archive/certcategory/show";
	}
	
	@RequestMapping("/add/{id}")
	public String add(ModelMap modelMap, @PathVariable Long id){
		LOGGER.info("Get id: " + id);
		if(id != null){
			ACertCategory model = certcategoryService.load(id);
			modelMap.addAttribute("model", model);
		}
		return "/archive/certcategory/add";
	}
	
	@RequestMapping("/create")
	public String create(ModelMap modelMap, @RequestParam Long parentId, ACertCategory model){

		if(parentId != null && parentId > 0){
			ACertCategory parent = certcategoryService.load(parentId);
			model.setParent(parent);
		}
		certcategoryService.create(model);
		modelMap.addAttribute("refresh", "page");
		modelMap.addAttribute("url", "/system/archive/certcategory/list");
		
		return "/success";
	}
	
	@RequestMapping("/update/{id}")
	public String update(ModelMap modelMap, @PathVariable Long id, ACertCategory model){
		LOGGER.info("Get update id: " + id);
		LOGGER.info("Update id: " + id);
		if(id != null){
			ACertCategory dbRecord = certcategoryService.load(id);
			dbRecord.setName(model.getName());
			dbRecord.setDescription(model.getDescription());
			certcategoryService.update(dbRecord);
			
			modelMap.addAttribute("refresh", "page");
			modelMap.addAttribute("url", "/system/archive/certcategory/list");
		}
		return "/success";
	}
}
