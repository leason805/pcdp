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
import com.boxun.pcdp.knowledge.entity.KProject;
import com.boxun.pcdp.knowledge.service.IProjectService;

@Controller
@RequestMapping("/system/knowledge/project")
public class ProjectController extends BaseController{

private static final Logger LOGGER = Logger.getLogger(ProjectController.class);
	
	@Autowired
	private IProjectService projectService;
	
	@RequestMapping("/list")
	public String list(ModelMap modelMap){
		return "/knowledge/project/list";
	}
	
	@RequestMapping("/show/{id}")
	public String show(ModelMap modelMap, @PathVariable Long id){
		LOGGER.info("Get show id: " + id);
		if(id != null){
			KProject model = projectService.load(id);
			modelMap.addAttribute("model", model);
		}
		
		return "/knowledge/project/show";
	}
	
	@RequestMapping("/listdata")
	public @ResponseBody Map<String, Object> listData(){
		LOGGER.info("Get list data.");
		List<KProject> listdata = projectService.list();
		Map<String, Object> map  = new HashMap<String, Object>();
		map.put("sEcho", 1);  
		map.put("iTotalRecords", listdata.size());  
        map.put("iTotalDisplayRecords", listdata.size());  
		map.put("aaData", listdata);
		
		return map;
	}
	
	@RequestMapping("/create")
	public String create(ModelMap modelMap, KProject model){

		projectService.create(model);
		return "/admin/success";
	}
	
	@RequestMapping("/update/{id}")
	public String update(ModelMap modelMap, @PathVariable Long id, KProject model){
		LOGGER.info("Get update id: " + id);
		LOGGER.info("Update id: " + id);
		if(id != null){
			KProject dbRecord = projectService.load(id);
			dbRecord.setTitle(model.getTitle());
			dbRecord.setLimits(model.getLimits());
			dbRecord.setMinutes(model.getMinutes());
			dbRecord.setScore(model.getScore());
			dbRecord.setPassscore(model.getPassscore());
			dbRecord.setSize(model.getSize());
			dbRecord.setProjectType(model.getProjectType());
			
			dbRecord.setDescription(model.getDescription());
			projectService.update(dbRecord);
		}
		return "/admin/success";
	}
}
