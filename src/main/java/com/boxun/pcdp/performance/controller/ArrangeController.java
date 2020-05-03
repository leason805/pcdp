package com.boxun.pcdp.performance.controller;

import java.util.ArrayList;
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

import com.boxun.estms.entity.Const;
import com.boxun.pcdp.admin.entity.TUser;
import com.boxun.pcdp.admin.service.IUserService;
import com.boxun.pcdp.basic.controller.BaseController;
import com.boxun.pcdp.estimate.entity.EAssess;
import com.boxun.pcdp.estimate.entity.EAssessProject;
import com.boxun.pcdp.estimate.service.IAssessProjectService;
import com.boxun.pcdp.estimate.service.IAssessService;
import com.boxun.pcdp.performance.entity.PArrange;
import com.boxun.pcdp.performance.pojo.ArrangePojo;
import com.boxun.pcdp.performance.service.IArrangeService;

@Controller("PArrange")
@RequestMapping("/system/performance/arrange")
public class ArrangeController extends BaseController{

private static final Logger LOGGER = Logger.getLogger(ArrangeController.class);
	
	@Autowired
	private IArrangeService parrangeService;
	
	@Autowired
	private IAssessProjectService assessProjectService;
	
	@Autowired
	private IAssessService assessService;
	
	@Autowired
	private IUserService userService;
	
	@RequestMapping("/list")
	public String list(ModelMap modelMap, Long projectId){
		List<EAssessProject> projects = this.assessProjectService.list();
		modelMap.addAttribute("projects", projects);
		if(projectId == null){
			if(projects != null && !projects.isEmpty()){
				projectId = projects.get(0).getId();
			}
			else{
				projectId = 0l;
			}
			
		}
		modelMap.addAttribute("projectId", projectId);
		return "/performance/arrange/list";
	}
	
	@RequestMapping("/show/{id}")
	public String show(ModelMap modelMap, @PathVariable Long id){
		LOGGER.info("Get show id: " + id);
		if(id != null){
			EAssess assess = this.assessService.load(id);
			modelMap.addAttribute("assess", assess);
			
			PArrange model = this.parrangeService.loadByAssess(id);
			modelMap.addAttribute("model", model);
			
			List<TUser> users = this.userService.list();
			modelMap.addAttribute("users", users);
		}
		
		return "/performance/arrange/show";
	}
	
	@RequestMapping("/listdata")
	public @ResponseBody Map<String, Object> listData(Long projectId){
		LOGGER.info("Get list data.");
		List<EAssess> list = assessService.list(projectId);
		
		List<PArrange> arranges = this.parrangeService.list4Project(projectId);
		
		List<ArrangePojo> listdata = new ArrayList<ArrangePojo>();
		for(EAssess assess : list){
			ArrangePojo pojo = new ArrangePojo();
			pojo.setId(assess.getId());
			pojo.setUserName(assess.getUser().getName());
			for(PArrange arrange : arranges){
				if(assess.getId().equals(arrange.getAssess().getId())){
					pojo.setSupAssessor(arrange.getSupAssessor().getName());
					pojo.setColAssessor(arrange.getColAssessor().getName());
					break;
				}
			}
			listdata.add(pojo);
		}
		
		Map<String, Object> map  = new HashMap<String, Object>();
		map.put("sEcho", 1);  
		map.put("iTotalRecords", listdata.size());  
        map.put("iTotalDisplayRecords", listdata.size());  
		map.put("aaData", listdata);
		
		return map;
	}
	
	@RequestMapping("/update")
	public String update(ModelMap modelMap, Long id, Long assessId, Long supId, Long colId){

		PArrange model = null;
		if(id != null){
			model = this.parrangeService.load(id);
		}
		else{
			model  = new PArrange();
		}
		if(assessId != null){
			EAssess assess = assessService.load(assessId);
			model.setAssess(assess);
		}
		if(supId != null){
			TUser supUser = userService.load(supId);
			model.setSupAssessor(supUser);
		}
		if(colId != null){
			TUser colUser = userService.load(colId);
			model.setColAssessor(colUser);
		}

		model.setSupStatus(Const.AssessStatus.DRAFT);
		model.setColStatus(Const.AssessStatus.DRAFT);
		model.setStatus(Const.AssessStatus.DRAFT);
		this.parrangeService.update(model);
		
		return "/success";
	}
}
