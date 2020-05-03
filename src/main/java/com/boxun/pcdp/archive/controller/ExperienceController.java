package com.boxun.pcdp.archive.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.boxun.estms.entity.Const;
import com.boxun.estms.pojo.UserInfo;
import com.boxun.estms.util.DateUtil;
import com.boxun.pcdp.admin.entity.TUser;
import com.boxun.pcdp.admin.service.IUserService;
import com.boxun.pcdp.archive.entity.ACertification;
import com.boxun.pcdp.archive.entity.AExpCategory;
import com.boxun.pcdp.archive.entity.AExperience;
import com.boxun.pcdp.archive.pojo.ExperienceCollectionPojo;
import com.boxun.pcdp.archive.pojo.ExperiencePojo;
import com.boxun.pcdp.archive.pojo.ExperienceSetPojo;
import com.boxun.pcdp.archive.service.IExpCategoryService;
import com.boxun.pcdp.archive.service.IExperienceService;
import com.boxun.pcdp.archive.transformer.ExperienceTransformer;
import com.boxun.pcdp.basic.controller.BaseController;

@Controller
@RequestMapping("/system/archive/experience")
public class ExperienceController extends BaseController{

private static final Logger LOGGER = Logger.getLogger(ExperienceController.class);
	
	@Autowired
	private IExperienceService experienceService;
	
	@Autowired
	private IExpCategoryService expcategoryService;
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private ExperienceTransformer experienceTransformer;
	
	@RequestMapping("/index")
	public String index(ModelMap modelMap){
		List<AExpCategory> cateList = expcategoryService.listTops();
		UserInfo userInfo = (UserInfo)this.session.getAttribute("userInfo");
		List<AExperience> expList =  this.experienceService.list(userInfo.getUserId());
		
		List<ExperienceSetPojo> list = new ArrayList<ExperienceSetPojo>();
		for(AExpCategory category : cateList){
			ExperienceSetPojo set = new ExperienceSetPojo();
			set.setCategoryId(category.getId());
			set.setCategoryName(category.getName());
			
			if(category.getChildren() != null && !category.getChildren().isEmpty()){
				for(AExpCategory cate : category.getChildren()){
					ExperienceCollectionPojo collection = new ExperienceCollectionPojo();
					collection.setCategoryId(cate.getId());
					collection.setCategoryName(cate.getName());
					
					for(AExperience exp : expList){
						if(cate.getId().equals(exp.getCategory().getId())){
							ExperiencePojo pojo = new ExperiencePojo();
							pojo.setId(exp.getId());
							pojo.setStatus(exp.getStatus().toString());
							pojo.setCompany(exp.getCompany());
							pojo.setCategoryId(exp.getCategory().getId());
							pojo.setDepartment(exp.getDepartment());
							if(exp.getStartDate() != null){
								pojo.setStartDate(DateUtil.formatDateTime(exp.getStartDate(), "yyyy-MM-dd"));
							}
							if(exp.getEndDate() != null){
								pojo.setEndDate(DateUtil.formatDateTime(exp.getEndDate(), "yyyy-MM-dd"));
							}
							collection.add(pojo);
							//break;
						}
						
					}
					set.add(collection);
				}
			}
			list.add(set);
		}
		
		modelMap.addAttribute("list", list);
		return "/archive/experience/index";
	}
	
	@RequestMapping("/show/{id}")
	public String show(ModelMap modelMap, @PathVariable Long id, @RequestParam Long categoryId){
		LOGGER.info("Get show id: " + id);
		modelMap.addAttribute("categoryId", categoryId);
		if(id != null){
			AExperience entity = experienceService.load(id);
			ExperiencePojo model = experienceTransformer.convert(entity);
			modelMap.addAttribute("model", model);
		}
		
		return "/archive/experience/show";
	}
	
	@RequestMapping("/listdata")
	public @ResponseBody Map<String, Object> listData(){
		LOGGER.info("Get list data.");
		List<AExperience> listdata = experienceService.list();
		Map<String, Object> map  = new HashMap<String, Object>();
		map.put("sEcho", 1);  
		map.put("iTotalRecords", listdata.size());  
        map.put("iTotalDisplayRecords", listdata.size());  
		map.put("aaData", listdata);
		
		return map;
	}
	
	@RequestMapping("/create")
	public String create(ModelMap modelMap, ExperiencePojo model){
		
		AExperience experience = this.experienceTransformer.transform(model);
		experience.setStatus(Const.CertificationStatus.PENDING);
		UserInfo userInfo = (UserInfo)this.session.getAttribute("userInfo");
		if(userInfo != null){
			TUser user = this.userService.load(userInfo.getUserId());
			experience.setUser(user);
		}
		
		if(model != null && model.getCategoryId() != null){
			AExpCategory category  = this.expcategoryService.load(model.getCategoryId());
			experience.setCategory(category);
		}
		
		experienceService.create(experience);
		
		modelMap.addAttribute("refresh", "page");
		modelMap.addAttribute("url", "/system/archive/experience/index");
		return "/success";
	}
	
	@RequestMapping("/update/{id}")
	public String update(ModelMap modelMap, @PathVariable Long id, ExperiencePojo model){
		LOGGER.info("Update id: " + id);
		if(id != null){
			AExperience dbRecord = experienceService.load(id);
			this.experienceTransformer.update(model, dbRecord);
			dbRecord.setStatus(Const.CertificationStatus.PENDING);
			experienceService.update(dbRecord);
		}
		modelMap.addAttribute("refresh", "page");
		modelMap.addAttribute("url", "/system/archive/experience/index");
		return "/success";
	}
	
	@RequestMapping("/delete/{id}")
	public @ResponseBody Map<String, Object> delete(@PathVariable Long id){
		Map<String, Object> map  = new HashMap<String, Object>();
		
		if(id != null){
			AExperience record = experienceService.load(id);
			experienceService.delete(record); 
			map.put("status", "success");
		}
		return map;
	}
}
