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
import com.boxun.pcdp.archive.entity.AEducationInfo;
import com.boxun.pcdp.archive.service.IEducationInfoService;

@Controller
@RequestMapping("/system/archive/educationinfo")
public class EducationInfoController extends BaseController{

private static final Logger LOGGER = Logger.getLogger(EducationInfoController.class);
	
	@Autowired
	private IEducationInfoService educationinfoService;
	
	@RequestMapping("/list")
	public String list(ModelMap modelMap){
		return "/archive/educationinfo/list";
	}
	
	@RequestMapping("/show/{id}")
	public String show(ModelMap modelMap, @PathVariable Long id){
		LOGGER.info("Get show id: " + id);
		if(id != null){
			AEducationInfo model = educationinfoService.load(id);
			modelMap.addAttribute("model", model);
		}
		
		return "/archive/educationinfo/show";
	}
	
	@RequestMapping("/listdata")
	public @ResponseBody Map<String, Object> listData(){
		LOGGER.info("Get list data.");
		List<AEducationInfo> listdata = educationinfoService.list();
		Map<String, Object> map  = new HashMap<String, Object>();
		map.put("sEcho", 1);  
		map.put("iTotalRecords", listdata.size());  
        map.put("iTotalDisplayRecords", listdata.size());  
		map.put("aaData", listdata);
		
		return map;
	}
	
	@RequestMapping("/create")
	public String create(ModelMap modelMap, AEducationInfo model){

		educationinfoService.create(model);
		return "/success";
	}
	
	@RequestMapping("/update/{id}")
	public String update(ModelMap modelMap, @PathVariable Long id, AEducationInfo model){
		LOGGER.info("Get update id: " + id);
		LOGGER.info("Update id: " + id);
		if(id != null){
			AEducationInfo dbRecord = educationinfoService.load(id);
//			dbRecord.setDescription(model.getDescription());
			educationinfoService.update(dbRecord);
		}
		return "/success";
	}
}
