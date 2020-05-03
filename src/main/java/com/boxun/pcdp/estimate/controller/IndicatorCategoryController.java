package com.boxun.pcdp.estimate.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.boxun.pcdp.basic.controller.BaseController;
import com.boxun.pcdp.estimate.entity.EIndicatorCategory;
import com.boxun.pcdp.estimate.service.IIndicatorCategoryService;

@Controller
@RequestMapping("/system/estimate/indicatorcategory")
public class IndicatorCategoryController extends BaseController{

private static final Logger LOGGER = Logger.getLogger(IndicatorCategoryController.class);
	
	@Autowired
	private IIndicatorCategoryService indicatorcategoryService;
	
	@RequestMapping("/list")
	public String list(ModelMap modelMap){
		return "/estimate/indicatorcategory/list";
	}
	
	@RequestMapping("/show/{id}")
	public String show(ModelMap modelMap, @PathVariable Long id){
		LOGGER.info("Get show id: " + id);
		if(id != null){
			EIndicatorCategory model = indicatorcategoryService.load(id);
			modelMap.addAttribute("model", model);
		}
		
		return "/estimate/indicatorcategory/show";
	}
//	
//	@RequestMapping("/listdata")
//	public @ResponseBody Map<String, Object> listData(){
//		LOGGER.info("Get list data.");
//		List<EIndicatorCategory> listdata = indicatorcategoryService.list();
//		Map<String, Object> map  = new HashMap<String, Object>();
//		map.put("sEcho", 1);  
//		map.put("iTotalRecords", listdata.size());  
//        map.put("iTotalDisplayRecords", listdata.size());  
//		map.put("aaData", listdata);
//		
//		return map;
//	}
//	
	@RequestMapping("/create")
	public String create(ModelMap modelMap, EIndicatorCategory model){

		indicatorcategoryService.create(model);
		modelMap.addAttribute("refresh", "page");
		modelMap.addAttribute("url", "/system/estimate/indicator/list");
		
		return "/success";
	}
	
	@RequestMapping("/update/{id}")
	public String update(ModelMap modelMap, @PathVariable Long id, EIndicatorCategory model){
		LOGGER.info("Get update id: " + id);
		LOGGER.info("Update id: " + id);
		if(id != null){
			EIndicatorCategory dbRecord = indicatorcategoryService.load(id);
			dbRecord.setName(model.getName());
			indicatorcategoryService.update(dbRecord);
		}
		modelMap.addAttribute("refresh", "page");
		modelMap.addAttribute("url", "/system/estimate/indicator/list");
		return "/success";
	}
}
