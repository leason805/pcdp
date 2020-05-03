package com.boxun.pcdp.training.controller;

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
import com.boxun.pcdp.training.entity.TArrangeUser;
import com.boxun.pcdp.training.service.IArrangeUserService;

@Controller("TArrangeUser")
@RequestMapping("/system/training/arrangeuser")
public class ArrangeUserController extends BaseController{

private static final Logger LOGGER = Logger.getLogger(ArrangeUserController.class);
	
	@Autowired
	private IArrangeUserService arrangeuserService;
	
	@RequestMapping("/list")
	public String list(ModelMap modelMap){
		return "/training/arrangeuser/list";
	}
	
	@RequestMapping("/show/{id}")
	public String show(ModelMap modelMap, @PathVariable Long id){
		LOGGER.info("Get show id: " + id);
		if(id != null){
			TArrangeUser model = arrangeuserService.load(id);
			modelMap.addAttribute("model", model);
		}
		
		return "/training/arrangeuser/show";
	}
	
	@RequestMapping("/listdata")
	public @ResponseBody Map<String, Object> listData(){
		LOGGER.info("Get list data.");
		List<TArrangeUser> listdata = arrangeuserService.list();
		Map<String, Object> map  = new HashMap<String, Object>();
		map.put("sEcho", 1);  
		map.put("iTotalRecords", listdata.size());  
        map.put("iTotalDisplayRecords", listdata.size());  
		map.put("aaData", listdata);
		
		return map;
	}
	
	@RequestMapping("/create")
	public String create(ModelMap modelMap, TArrangeUser model){

		arrangeuserService.create(model);
		return "/success";
	}
	
	@RequestMapping("/update/{id}")
	public String update(ModelMap modelMap, @PathVariable Long id, TArrangeUser model){
		LOGGER.info("Get update id: " + id);
		LOGGER.info("Update id: " + id);
		if(id != null){
			TArrangeUser dbRecord = arrangeuserService.load(id);
//			dbRecord.setDescription(model.getDescription());
			arrangeuserService.update(dbRecord);
		}
		return "/success";
	}
}
