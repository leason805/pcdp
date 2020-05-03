package com.boxun.pcdp.knowledge.controller;

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
import com.boxun.pcdp.knowledge.entity.KArrangeUser;
import com.boxun.pcdp.knowledge.service.IArrangeUserService;

@Controller("KArrangeUser")
@RequestMapping("/system/knowledge/arrangeuser")
public class ArrangeUserController extends BaseController{

private static final Logger LOGGER = Logger.getLogger(ArrangeUserController.class);
	
	@Autowired
	private IArrangeUserService karrangeuserService;
	
	@RequestMapping("/list")
	public String list(ModelMap modelMap){
		return "/knowledge/arrangeuser/list";
	}
	
	@RequestMapping("/show/{id}")
	public String show(ModelMap modelMap, @PathVariable Long id){
		LOGGER.info("Get show id: " + id);
		if(id != null){
			KArrangeUser model = karrangeuserService.load(id);
			modelMap.addAttribute("model", model);
		}
		
		return "/knowledge/arrangeuser/show";
	}
	
	@RequestMapping("/listdata")
	public @ResponseBody Map<String, Object> listData(){
		LOGGER.info("Get list data.");
		List<KArrangeUser> listdata = karrangeuserService.list();
		Map<String, Object> map  = new HashMap<String, Object>();
		map.put("sEcho", 1);  
		map.put("iTotalRecords", listdata.size());  
        map.put("iTotalDisplayRecords", listdata.size());  
		map.put("aaData", listdata);
		
		return map;
	}
	
	@RequestMapping("/create")
	public String create(ModelMap modelMap, KArrangeUser model){

		karrangeuserService.create(model);
		return "/success";
	}
	
	@RequestMapping("/update/{id}")
	public String update(ModelMap modelMap, @PathVariable Long id, KArrangeUser model){
		LOGGER.info("Get update id: " + id);
		LOGGER.info("Update id: " + id);
		if(id != null){
			KArrangeUser dbRecord = karrangeuserService.load(id);
			//dbRecord.setDescription(model.getDescription());
			karrangeuserService.update(dbRecord);
		}
		return "/success";
	}
}
