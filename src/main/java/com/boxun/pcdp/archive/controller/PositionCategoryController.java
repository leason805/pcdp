package com.boxun.pcdp.archive.controller;

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
import com.boxun.pcdp.archive.entity.APositionCategory;
import com.boxun.pcdp.archive.service.IPositionCategoryService;

@Controller
@RequestMapping("/system/archive/positioncategory")
public class PositionCategoryController extends BaseController{

private static final Logger LOGGER = Logger.getLogger(PositionCategoryController.class);
	
	@Autowired
	private IPositionCategoryService positioncategoryService;
	
	@RequestMapping("/list")
	public String list(ModelMap modelMap){
		return "/archive/positioncategory/list";
	}
	
	@RequestMapping("/show/{id}")
	public String show(ModelMap modelMap, @PathVariable Long id){
		LOGGER.info("Get show id: " + id);
		if(id != null){
			APositionCategory model = positioncategoryService.load(id);
			modelMap.addAttribute("model", model);
		}
		
		return "/archive/positioncategory/show";
	}
	
	@RequestMapping("/listdata")
	public @ResponseBody Map<String, Object> listData(){
		LOGGER.info("Get list data.");
		List<APositionCategory> listdata = positioncategoryService.list();
		Map<String, Object> map  = new HashMap<String, Object>();
		map.put("sEcho", 1);  
		map.put("iTotalRecords", listdata.size());  
        map.put("iTotalDisplayRecords", listdata.size());  
		map.put("aaData", listdata);
		
		return map;
	}
	
	@RequestMapping("/create")
	public String create(ModelMap modelMap, APositionCategory model){

		positioncategoryService.create(model);
		return "/success";
	}
	
	@RequestMapping("/update/{id}")
	public String update(ModelMap modelMap, @PathVariable Long id, APositionCategory model){
		LOGGER.info("Get update id: " + id);
		LOGGER.info("Update id: " + id);
		if(id != null){
			APositionCategory dbRecord = positioncategoryService.load(id);
			dbRecord.setName(model.getName());
			dbRecord.setOrder(model.getOrder());
			dbRecord.setCategoryType(model.getCategoryType());
			dbRecord.setDescription(model.getDescription());
			positioncategoryService.update(dbRecord);
		}
		return "/success";
	}
}
