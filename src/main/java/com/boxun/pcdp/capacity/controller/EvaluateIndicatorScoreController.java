package com.boxun.pcdp.capacity.controller;

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
import com.boxun.pcdp.capacity.entity.CEvaluateIndicatorScore;
import com.boxun.pcdp.capacity.service.IEvaluateIndicatorScoreService;

@Controller
@RequestMapping("/system/capacity/evaluateindicatorscore")
public class EvaluateIndicatorScoreController extends BaseController{

private static final Logger LOGGER = Logger.getLogger(EvaluateIndicatorScoreController.class);
	
	@Autowired
	private IEvaluateIndicatorScoreService evaluateindicatorscoreService;
	
	@RequestMapping("/list")
	public String list(ModelMap modelMap){
		return "/capacity/evaluateindicatorscore/list";
	}
	
	@RequestMapping("/show/{id}")
	public String show(ModelMap modelMap, @PathVariable Long id){
		LOGGER.info("Get show id: " + id);
		if(id != null){
			CEvaluateIndicatorScore model = evaluateindicatorscoreService.load(id);
			modelMap.addAttribute("model", model);
		}
		
		return "/capacity/evaluateindicatorscore/show";
	}
	
	@RequestMapping("/listdata")
	public @ResponseBody Map<String, Object> listData(){
		LOGGER.info("Get list data.");
		List<CEvaluateIndicatorScore> listdata = evaluateindicatorscoreService.list();
		Map<String, Object> map  = new HashMap<String, Object>();
		map.put("sEcho", 1);  
		map.put("iTotalRecords", listdata.size());  
        map.put("iTotalDisplayRecords", listdata.size());  
		map.put("aaData", listdata);
		
		return map;
	}
	
	@RequestMapping("/create")
	public String create(ModelMap modelMap, CEvaluateIndicatorScore model){

		evaluateindicatorscoreService.create(model);
		return "/success";
	}
	
	@RequestMapping("/update/{id}")
	public String update(ModelMap modelMap, @PathVariable Long id, CEvaluateIndicatorScore model){
		LOGGER.info("Get update id: " + id);
		LOGGER.info("Update id: " + id);
		if(id != null){
			CEvaluateIndicatorScore dbRecord = evaluateindicatorscoreService.load(id);
//			dbRecord.setDescription(model.getDescription());
			evaluateindicatorscoreService.update(dbRecord);
		}
		return "/success";
	}
}
