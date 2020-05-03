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
import com.boxun.pcdp.performance.entity.PIndicatorScore;
import com.boxun.pcdp.performance.service.IIndicatorScoreService;

@Controller("pIndicatorScoreController")
@RequestMapping("/system/performance/indicatorscore")
public class IndicatorScoreController extends BaseController{

private static final Logger LOGGER = Logger.getLogger(IndicatorScoreController.class);
	
	@Autowired
	private IIndicatorScoreService indicatorscoreService;
	
	@RequestMapping("/list")
	public String list(ModelMap modelMap){
		return "/performance/indicatorscore/list";
	}
	
	@RequestMapping("/show/{id}")
	public String show(ModelMap modelMap, @PathVariable Long id){
		LOGGER.info("Get show id: " + id);
		if(id != null){
			PIndicatorScore model = indicatorscoreService.load(id);
			modelMap.addAttribute("model", model);
		}
		
		return "/performance/indicatorscore/show";
	}
	
	@RequestMapping("/listdata")
	public @ResponseBody Map<String, Object> listData(){
		LOGGER.info("Get list data.");
		List<PIndicatorScore> listdata = indicatorscoreService.list();
		Map<String, Object> map  = new HashMap<String, Object>();
		map.put("sEcho", 1);  
		map.put("iTotalRecords", listdata.size());  
        map.put("iTotalDisplayRecords", listdata.size());  
		map.put("aaData", listdata);
		
		return map;
	}
	
	@RequestMapping("/create")
	public String create(ModelMap modelMap, PIndicatorScore model){

		indicatorscoreService.create(model);
		return "/success";
	}
	
	@RequestMapping("/update/{id}")
	public String update(ModelMap modelMap, @PathVariable Long id, PIndicatorScore model){
		LOGGER.info("Get update id: " + id);
		LOGGER.info("Update id: " + id);
		if(id != null){
			PIndicatorScore dbRecord = indicatorscoreService.load(id);
//			dbRecord.setDescription(model.getDescription());
			indicatorscoreService.update(dbRecord);
		}
		return "/success";
	}
}
