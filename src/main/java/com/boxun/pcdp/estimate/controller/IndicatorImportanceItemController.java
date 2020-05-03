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
import com.boxun.pcdp.estimate.entity.EIndicatorImportanceItem;
import com.boxun.pcdp.estimate.service.IIndicatorImportanceItemService;

@Controller
@RequestMapping("/system/estimate/indicatorimportanceitem")
public class IndicatorImportanceItemController extends BaseController{

private static final Logger LOGGER = Logger.getLogger(IndicatorImportanceItemController.class);
	
	@Autowired
	private IIndicatorImportanceItemService indicatorimportanceitemService;
	
	@RequestMapping("/list")
	public String list(ModelMap modelMap){
		return "/estimate/indicatorimportanceitem/list";
	}
	
	@RequestMapping("/show/{id}")
	public String show(ModelMap modelMap, @PathVariable Long id){
		LOGGER.info("Get show id: " + id);
		if(id != null){
			EIndicatorImportanceItem model = indicatorimportanceitemService.load(id);
			modelMap.addAttribute("model", model);
		}
		
		return "/estimate/indicatorimportanceitem/show";
	}
	
	@RequestMapping("/listdata")
	public @ResponseBody Map<String, Object> listData(){
		LOGGER.info("Get list data.");
		List<EIndicatorImportanceItem> listdata = indicatorimportanceitemService.list();
		Map<String, Object> map  = new HashMap<String, Object>();
		map.put("sEcho", 1);  
		map.put("iTotalRecords", listdata.size());  
        map.put("iTotalDisplayRecords", listdata.size());  
		map.put("aaData", listdata);
		
		return map;
	}
	
	@RequestMapping("/create")
	public String create(ModelMap modelMap, EIndicatorImportanceItem model){

		indicatorimportanceitemService.create(model);
		return "/success";
	}
	
	@RequestMapping("/update/{id}")
	public String update(ModelMap modelMap, @PathVariable Long id, EIndicatorImportanceItem model){
		LOGGER.info("Get update id: " + id);
		LOGGER.info("Update id: " + id);
		if(id != null){
			EIndicatorImportanceItem dbRecord = indicatorimportanceitemService.load(id);
//			dbRecord.setDescription(model.getDescription());
			indicatorimportanceitemService.update(dbRecord);
		}
		return "/success";
	}
}
