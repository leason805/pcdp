package com.boxun.pcdp.performance.controller;

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
import com.boxun.pcdp.performance.entity.PItemScore;
import com.boxun.pcdp.performance.service.IItemScoreService;

@Controller
@RequestMapping("/system/performance/itemscore")
public class ItemScoreController extends BaseController{

private static final Logger LOGGER = Logger.getLogger(ItemScoreController.class);
	
	@Autowired
	private IItemScoreService itemscoreService;
	
	@RequestMapping("/list")
	public String list(ModelMap modelMap){
		return "/performance/itemscore/list";
	}
	
	@RequestMapping("/show/{id}")
	public String show(ModelMap modelMap, @PathVariable Long id){
		LOGGER.info("Get show id: " + id);
		if(id != null){
			PItemScore model = itemscoreService.load(id);
			modelMap.addAttribute("model", model);
		}
		
		return "/performance/itemscore/show";
	}
	
	@RequestMapping("/listdata")
	public @ResponseBody Map<String, Object> listData(){
		LOGGER.info("Get list data.");
		List<PItemScore> listdata = itemscoreService.list();
		Map<String, Object> map  = new HashMap<String, Object>();
		map.put("sEcho", 1);  
		map.put("iTotalRecords", listdata.size());  
        map.put("iTotalDisplayRecords", listdata.size());  
		map.put("aaData", listdata);
		
		return map;
	}
	
	@RequestMapping("/create")
	public String create(ModelMap modelMap, PItemScore model){

		itemscoreService.create(model);
		return "/success";
	}
	
	@RequestMapping("/update/{id}")
	public String update(ModelMap modelMap, @PathVariable Long id, PItemScore model){
		LOGGER.info("Get update id: " + id);
		LOGGER.info("Update id: " + id);
		if(id != null){
			PItemScore dbRecord = itemscoreService.load(id);
//			dbRecord.setDescription(model.getDescription());
			itemscoreService.update(dbRecord);
		}
		return "/success";
	}
}
