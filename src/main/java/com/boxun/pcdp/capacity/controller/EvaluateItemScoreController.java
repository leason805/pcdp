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
import com.boxun.pcdp.capacity.entity.CEvaluateItemScore;
import com.boxun.pcdp.capacity.service.IEvaluateItemScoreService;

@Controller
@RequestMapping("/system/capacity/evaluateitemscore")
public class EvaluateItemScoreController extends BaseController{

private static final Logger LOGGER = Logger.getLogger(EvaluateItemScoreController.class);
	
	@Autowired
	private IEvaluateItemScoreService evaluateitemscoreService;
	
	@RequestMapping("/list")
	public String list(ModelMap modelMap){
		return "/capacity/evaluateitemscore/list";
	}
	
	@RequestMapping("/show/{id}")
	public String show(ModelMap modelMap, @PathVariable Long id){
		LOGGER.info("Get show id: " + id);
		if(id != null){
			CEvaluateItemScore model = evaluateitemscoreService.load(id);
			modelMap.addAttribute("model", model);
		}
		
		return "/capacity/evaluateitemscore/show";
	}
	
	@RequestMapping("/listdata")
	public @ResponseBody Map<String, Object> listData(){
		LOGGER.info("Get list data.");
		List<CEvaluateItemScore> listdata = evaluateitemscoreService.list();
		Map<String, Object> map  = new HashMap<String, Object>();
		map.put("sEcho", 1);  
		map.put("iTotalRecords", listdata.size());  
        map.put("iTotalDisplayRecords", listdata.size());  
		map.put("aaData", listdata);
		
		return map;
	}
	
	@RequestMapping("/create")
	public String create(ModelMap modelMap, CEvaluateItemScore model){

		evaluateitemscoreService.create(model);
		return "/success";
	}
	
	@RequestMapping("/update/{id}")
	public String update(ModelMap modelMap, @PathVariable Long id, CEvaluateItemScore model){
		LOGGER.info("Get update id: " + id);
		LOGGER.info("Update id: " + id);
		if(id != null){
			CEvaluateItemScore dbRecord = evaluateitemscoreService.load(id);
//			dbRecord.setDescription(model.getDescription());
			evaluateitemscoreService.update(dbRecord);
		}
		return "/success";
	}
}
