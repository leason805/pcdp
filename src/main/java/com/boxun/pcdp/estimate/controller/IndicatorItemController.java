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
import com.boxun.pcdp.estimate.entity.EIndicatorItem;
import com.boxun.pcdp.estimate.service.IIndicatorItemService;

@Controller
@RequestMapping("/system/estimate/indicatoritem")
public class IndicatorItemController extends BaseController{

private static final Logger LOGGER = Logger.getLogger(IndicatorItemController.class);
	
	@Autowired
	private IIndicatorItemService indicatoritemService;
	
	@RequestMapping("/list")
	public String list(ModelMap modelMap){
		return "/estimate/indicatoritem/list";
	}
	
	@RequestMapping("/show/{id}")
	public String show(ModelMap modelMap, @PathVariable Long id){
		LOGGER.info("Get show id: " + id);
		if(id != null){
			EIndicatorItem model = indicatoritemService.load(id);
			modelMap.addAttribute("model", model);
		}
		
		return "/estimate/indicatoritem/show";
	}
	
	@RequestMapping("/listdata")
	public @ResponseBody Map<String, Object> listData(){
		LOGGER.info("Get list data.");
		List<EIndicatorItem> listdata = indicatoritemService.list();
		Map<String, Object> map  = new HashMap<String, Object>();
		map.put("sEcho", 1);  
		map.put("iTotalRecords", listdata.size());  
        map.put("iTotalDisplayRecords", listdata.size());  
		map.put("aaData", listdata);
		
		return map;
	}
	
	@RequestMapping("/create")
	public String create(ModelMap modelMap, EIndicatorItem model){

		indicatoritemService.create(model);
		return "/success";
	}
	
	@RequestMapping("/update/{id}")
	public String update(ModelMap modelMap, @PathVariable Long id, EIndicatorItem model){
		LOGGER.info("Get update id: " + id);
		LOGGER.info("Update id: " + id);
		if(id != null){
			EIndicatorItem dbRecord = indicatoritemService.load(id);
			dbRecord.setDescription(model.getDescription());
			indicatoritemService.update(dbRecord);
		}
		return "/success";
	}
}
