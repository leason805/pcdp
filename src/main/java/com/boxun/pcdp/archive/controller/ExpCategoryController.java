package com.boxun.pcdp.archive.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.boxun.pcdp.archive.entity.AExpCategory;
import com.boxun.pcdp.archive.service.IExpCategoryService;
import com.boxun.pcdp.basic.controller.BaseController;

@Controller
@RequestMapping("/system/archive/expcategory")
public class ExpCategoryController extends BaseController{

private static final Logger LOGGER = Logger.getLogger(ExpCategoryController.class);
	
	@Autowired
	private IExpCategoryService expcategoryService;
	
	@RequestMapping("/list")
	public String list(ModelMap modelMap){
		List<AExpCategory> list = expcategoryService.listTops();
		modelMap.addAttribute("list", list);
		return "/archive/expcategory/list";
	}
	
	
	@RequestMapping("/show/{id}")
	public String show(ModelMap modelMap, @PathVariable Long id){
		LOGGER.info("Get show id: " + id);
		if(id != null){
			AExpCategory model = expcategoryService.load(id);
			modelMap.addAttribute("model", model);
		}
		
		return "/archive/expcategory/show";
	}
	
	@RequestMapping("/add/{id}")
	public String add(ModelMap modelMap, @PathVariable Long id){
		LOGGER.info("Get id: " + id);
		if(id != null){
			AExpCategory model = expcategoryService.load(id);
			modelMap.addAttribute("model", model);
		}
		return "/archive/expcategory/add";
	}
	
	@RequestMapping("/create")
	public String create(ModelMap modelMap, @RequestParam Long parentId, AExpCategory model){

		if(parentId != null && parentId > 0){
			AExpCategory parent = expcategoryService.load(parentId);
			model.setParent(parent);
		}
		expcategoryService.create(model);
		modelMap.addAttribute("refresh", "page");
		modelMap.addAttribute("url", "/system/archive/expcategory/list");
		
		return "/success";
	}
	
	@RequestMapping("/update/{id}")
	public String update(ModelMap modelMap, @PathVariable Long id, AExpCategory model){
		LOGGER.info("Get update id: " + id);
		LOGGER.info("Update id: " + id);
		if(id != null){
			AExpCategory dbRecord = expcategoryService.load(id);
			dbRecord.setName(model.getName());
			dbRecord.setDescription(model.getDescription());
			expcategoryService.update(dbRecord);
			
			modelMap.addAttribute("refresh", "page");
			modelMap.addAttribute("url", "/system/archive/expcategory/list");
		}
		return "/success";
	}
}
