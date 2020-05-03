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
import com.boxun.pcdp.estimate.entity.EAssessDetail;
import com.boxun.pcdp.estimate.service.IAssessDetailService;

@Controller
@RequestMapping("/system/estimate/assessdetail")
public class AssessDetailController extends BaseController{

private static final Logger LOGGER = Logger.getLogger(AssessDetailController.class);
	
	@Autowired
	private IAssessDetailService assessdetailService;
	
	@RequestMapping("/list")
	public String list(ModelMap modelMap){
		return "/estimate/assessdetail/list";
	}
	
	@RequestMapping("/show/{id}")
	public String show(ModelMap modelMap, @PathVariable Long id){
		LOGGER.info("Get show id: " + id);
		if(id != null){
			EAssessDetail model = assessdetailService.load(id);
			modelMap.addAttribute("model", model);
		}
		
		return "/estimate/assessdetail/show";
	}
	
	@RequestMapping("/listdata")
	public @ResponseBody Map<String, Object> listData(){
		LOGGER.info("Get list data.");
		List<EAssessDetail> listdata = assessdetailService.list();
		Map<String, Object> map  = new HashMap<String, Object>();
		map.put("sEcho", 1);  
		map.put("iTotalRecords", listdata.size());  
        map.put("iTotalDisplayRecords", listdata.size());  
		map.put("aaData", listdata);
		
		return map;
	}
	
	@RequestMapping("/create")
	public String create(ModelMap modelMap, EAssessDetail model){

		assessdetailService.create(model);
		return "/success";
	}
	
	@RequestMapping("/update/{id}")
	public String update(ModelMap modelMap, @PathVariable Long id, EAssessDetail model){
		LOGGER.info("Get update id: " + id);
		LOGGER.info("Update id: " + id);
		if(id != null){
			EAssessDetail dbRecord = assessdetailService.load(id);
//			dbRecord.setDescription(model.getDescription());
			assessdetailService.update(dbRecord);
		}
		return "/success";
	}
}
