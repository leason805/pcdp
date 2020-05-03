package com.boxun.pcdp.admin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.boxun.estms.util.StringUtil;
import com.boxun.pcdp.admin.entity.TModule;
import com.boxun.pcdp.admin.service.IModuleService;

@Controller
@RequestMapping("/system/admin/module")
public class ModuleController {

	private static final Logger LOGGER = Logger.getLogger(ModuleController.class);
	
	@Autowired
	private IModuleService moduleService;
	
	@RequestMapping("/list")
	public String list(ModelMap modelMap){
		return "/admin/module/list";
	}
	
	@RequestMapping("/show/{id}")
	public String show(ModelMap modelMap, @PathVariable Long id){
		LOGGER.info("Get module id: " + id);
		if(id != null){
			TModule module = moduleService.load(id);
			modelMap.addAttribute("module", module);
		}
		return "/admin/module/show";
	}
	
	@RequestMapping("/listdata")
	public @ResponseBody Map<String, Object> listData(){
		LOGGER.info("Get user list data.");
		List<TModule> listdata = moduleService.list();
		Map<String, Object> map  = new HashMap<String, Object>();
		map.put("sEcho", 1);  
		map.put("iTotalRecords", listdata.size());  
        map.put("iTotalDisplayRecords", listdata.size());  
		map.put("aaData", listdata);
		
		return map;
	}
	
	@RequestMapping("/create")
	public String create(ModelMap modelMap, TModule module){
		if(StringUtil.isNotBlank(module.getName()) && StringUtil.isNotBlank(module.getUrl())){
			moduleService.create(module);
		}
		return "/admin/success";
	}
	
	@RequestMapping("/update/{id}")
	public String update(ModelMap modelMap, @PathVariable Long id, TModule module){
		LOGGER.info("Get menu id: " + id);
		if(id != null){
			TModule dbRecord = moduleService.load(id);
			
			dbRecord.setCode(module.getCode());
			dbRecord.setName(module.getName());
			dbRecord.setUrl(module.getUrl());
			dbRecord.setOrder(module.getOrder());
			dbRecord.setIcon(module.getIcon());
			moduleService.update(dbRecord);
		}
		return "/admin/success";
	}
}
