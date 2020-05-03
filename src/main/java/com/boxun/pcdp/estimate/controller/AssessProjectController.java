package com.boxun.pcdp.estimate.controller;

import java.util.ArrayList;
import java.util.Date;
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
import com.boxun.estms.util.StringUtil;
import com.boxun.pcdp.admin.entity.TUser;
import com.boxun.pcdp.admin.service.IUserService;
import com.boxun.pcdp.basic.controller.BaseController;
import com.boxun.pcdp.estimate.entity.EAssess;
import com.boxun.pcdp.estimate.entity.EAssessProject;
import com.boxun.pcdp.estimate.entity.EIndicatorCategory;
import com.boxun.pcdp.estimate.service.IAssessProjectService;
import com.boxun.pcdp.estimate.service.IAssessService;
import com.boxun.pcdp.estimate.service.IIndicatorCategoryService;

@Controller
@RequestMapping("/system/estimate/assessproject")
public class AssessProjectController extends BaseController{

private static final Logger LOGGER = Logger.getLogger(AssessProjectController.class);
	
	@Autowired
	private IAssessProjectService assessProjectService;
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private IAssessService assessService;
	
	@Autowired
	private IIndicatorCategoryService indicatorcategoryService;
	
	@RequestMapping("/list")
	public String list(ModelMap modelMap){
		return "/estimate/assessproject/list";
	}
	
	@RequestMapping("/show/{id}")
	public String show(ModelMap modelMap, @PathVariable Long id){
		LOGGER.info("Get show id: " + id);
		if(id != null){
			EAssessProject model = assessProjectService.load(id);
			modelMap.addAttribute("model", model);
		}
		
		List<EIndicatorCategory> categories = this.indicatorcategoryService.listActive();
		modelMap.addAttribute("categories", categories);
		
		return "/estimate/assessproject/show";
	}
	
	@RequestMapping("/listdata")
	public @ResponseBody Map<String, Object> listData(){
		LOGGER.info("Get list data.");
		List<EAssessProject> listdata = assessProjectService.list();
		Map<String, Object> map  = new HashMap<String, Object>();
		map.put("sEcho", 1);  
		map.put("iTotalRecords", listdata.size());  
        map.put("iTotalDisplayRecords", listdata.size());  
		map.put("aaData", listdata);
		
		return map;
	}
	
	@RequestMapping("/create")
	public String create(ModelMap modelMap, EAssessProject model, String userIds, Long categoryId){
		model.setCreateTime(new Date());
		
		if(categoryId != null){
			EIndicatorCategory category = this.indicatorcategoryService.load(categoryId);
			model.setCategory(category);
		}
		
		assessProjectService.create(model);
		
		if(StringUtil.isNotBlank(userIds)){
			List<Long> idList = StringUtil.split4Long(userIds, Const.COMMA_DELIMITER);
			
			for(Long uid : idList){
				EAssess assess = new EAssess();
				assess.setProject(model);
				assess.setStatus(Const.AssessStatus.UNASSIGN);
				TUser user = userService.load(uid);
				assess.setUser(user);
				assessService.create(assess);
			}
		}
		
		return "/success";
	}
	
	@RequestMapping("/update/{id}")
	public String update(ModelMap modelMap, @PathVariable Long id, EAssessProject model, String userIds, Long categoryId){
		LOGGER.info("Get update id: " + id);
		LOGGER.info("Update id: " + id);
		if(id != null){
			EAssessProject dbRecord = assessProjectService.load(id);
			dbRecord.setName(model.getName());
			dbRecord.setDescription(model.getDescription());
			
			if(categoryId != null){
				EIndicatorCategory category = this.indicatorcategoryService.load(categoryId);
				dbRecord.setCategory(category);
			}
			assessProjectService.update(dbRecord);
			
			List<EAssess> list = this.assessService.list(dbRecord.getId());
			List<Long> idList = StringUtil.split4Long(userIds, Const.COMMA_DELIMITER);
			
			//for new add
			if(StringUtil.isNotBlank(userIds)){
				for(Long uid : idList){
					boolean exist = false;
					for(EAssess assess : list){
						if(assess.getUser() != null && assess.getUser().getId().equals(uid)){
							exist = true;
							break;
						}
					}
					if(!exist){
						EAssess assess = new EAssess();
						assess.setProject(model);
						assess.setStatus(Const.AssessStatus.UNASSIGN);
						TUser user = userService.load(uid);
						assess.setUser(user);
						assessService.create(assess);
					}
				}
			}
			
			
			//for remove
			for(EAssess assess : list){
				boolean remove = true;
				if(idList != null){
					for(Long uid : idList){
						if(assess.getUser() != null && assess.getUser().getId().equals(uid)){
							remove = false;
							break;
						}
					}
				}
				
				if(remove){
					assess.setUser(null);
					this.assessService.update(assess);
				}
			}
		}
		return "/success";
	}
	
	@RequestMapping("/loaduser")
	public @ResponseBody Map<String, Object> loaduser(Long id, String type){
		
		Map<String, Object> map  = new HashMap<String, Object>();
		List<Map<String, Object>> rowsData = new ArrayList<Map<String, Object>>();
		Boolean assigned = "assigned".equals(type);
		
		Long total = assessProjectService.getSize4Assess(id, assigned);
		if(total > 0){
			List<TUser> list = assessProjectService.listUsers4Assess(id, assigned);
			if(list != null){
				for (TUser user : list) {
					Map<String, Object> map1 = new HashMap<String, Object>();
					map1.put("id", user.getId());
					map1.put("account", user.getLoginID());
					map1.put("realName", user.getName());
					rowsData.add(map1);
				}
			}
		}
		map.put("total", total);
		map.put("rows", rowsData);
		return map;
	}

}
