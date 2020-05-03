package com.boxun.pcdp.archive.controller;

import java.util.ArrayList;
import java.util.Enumeration;
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
import com.boxun.pcdp.admin.entity.TUser;
import com.boxun.pcdp.admin.service.IUserService;
import com.boxun.pcdp.archive.entity.AEducationInfo;
import com.boxun.pcdp.archive.entity.AJobInfo;
import com.boxun.pcdp.archive.entity.APositionCategory;
import com.boxun.pcdp.archive.entity.AUserInfo;
import com.boxun.pcdp.archive.pojo.EduInfoPojo;
import com.boxun.pcdp.archive.pojo.JobInfoPojo;
import com.boxun.pcdp.archive.pojo.UserInfoPojo;
import com.boxun.pcdp.archive.service.IEducationInfoService;
import com.boxun.pcdp.archive.service.IJobInfoService;
import com.boxun.pcdp.archive.service.IPositionCategoryService;
import com.boxun.pcdp.archive.service.IUserInfoService;
import com.boxun.pcdp.archive.transformer.EduInfoTransformer;
import com.boxun.pcdp.archive.transformer.JobInfoTransformer;
import com.boxun.pcdp.archive.transformer.UserInfoTransformer;
import com.boxun.pcdp.basic.controller.BaseController;

@Controller
@RequestMapping("/system/archive/userinfo")
public class UserInfoController extends BaseController{

private static final Logger LOGGER = Logger.getLogger(UserInfoController.class);
	
	@Autowired
	private IUserInfoService userinfoService;
	
	@Autowired
	private UserInfoTransformer userinfoTransformer;
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private IJobInfoService jobinfoService;
	
	@Autowired
	private IEducationInfoService educationinfoService;
	
	@Autowired
	private IPositionCategoryService positioncategoryService;
	
	@Autowired
	private JobInfoTransformer jobinfoTransformer;
	
	@Autowired
	private EduInfoTransformer eduInfoTransformer;
	
	@RequestMapping("/index")
	public String index(ModelMap modelMap){
		UserInfo userInfo = (UserInfo)this.session.getAttribute("userInfo");
		if(userInfo != null){
			AUserInfo model = this.userinfoService.loadByUser(userInfo.getUserId());
			if(model != null){
				modelMap.addAttribute("model", model);	
			}
			
		}
		return "/archive/userinfo/index";
	}
	
	@RequestMapping("/show/{id}")
	public String show(ModelMap modelMap, @PathVariable Long id){
		LOGGER.info("Get show id: " + id);
		if(id != null){
			AUserInfo model = userinfoService.load(id);
			modelMap.addAttribute("model", model);
		}
		
		return "/archive/userinfo/show";
	}
//	
//	@RequestMapping("/listdata")
//	public @ResponseBody Map<String, Object> listData(){
//		LOGGER.info("Get list data.");
//		List<AUserInfo> listdata = userinfoService.list();
//		Map<String, Object> map  = new HashMap<String, Object>();
//		map.put("sEcho", 1);  
//		map.put("iTotalRecords", listdata.size());  
//        map.put("iTotalDisplayRecords", listdata.size());  
//		map.put("aaData", listdata);
//		
//		return map;
//	}
	
	@RequestMapping("/create")
	public String create(ModelMap modelMap, AUserInfo model){
		model.setStatus(Const.CertificationStatus.PENDING);
		userinfoService.create(model);
		return "/success";
	}
	
	@RequestMapping("/update/{id}")
	public String update(ModelMap modelMap, @PathVariable Long id, AUserInfo model){
		LOGGER.info("Get update id: " + id);
		LOGGER.info("Update id: " + id);
		if(id != null){
			AUserInfo dbRecord = userinfoService.load(id);
			dbRecord.setStatus(Const.CertificationStatus.PENDING);
			//dbRecord.setDescription(model.getDescription());
			userinfoService.update(dbRecord);
		}
		return "/success";
	}
	
	
	@RequestMapping("/basic/{id}")
	public String basic(ModelMap modelMap, @PathVariable Long id){
		LOGGER.info("Get show id: " + id);
		if(id != null){
			AUserInfo model = userinfoService.loadByUser(id);
			if(model != null){
				modelMap.addAttribute("model", model);
			}
		}
		modelMap.addAttribute("userId", id);
		List<APositionCategory> list = this.positioncategoryService.list();
		List<APositionCategory> deptList = new ArrayList<APositionCategory>();
		List<APositionCategory> positList = new ArrayList<APositionCategory>();
		for(APositionCategory cate : list){
			if(Const.PositionCategoryType.DEPARTMENT.equals(cate.getCategoryType())){
				deptList.add(cate);
			}
			if(Const.PositionCategoryType.POSITION.equals(cate.getCategoryType())){
				positList.add(cate);
			}
		}
		
		modelMap.addAttribute("deptList", deptList);
		modelMap.addAttribute("positList", positList);
		return "/archive/userinfo/basic";
	}
	
	@RequestMapping("/createbasic")
	public String createbasic(ModelMap modelMap, UserInfoPojo model, @RequestParam Long userId, @RequestParam Long deptId, @RequestParam Long positId){
		
		AUserInfo userInfo = this.userinfoTransformer.transform(model);
		if(userId != null){
			TUser user =  this.userService.load(userId);
			userInfo.setUser(user);
		}
		if(deptId != null){
			APositionCategory cate =  this.positioncategoryService.load(deptId);
			userInfo.setDeparment(cate);
		}
		if(positId != null){
			APositionCategory cate =  this.positioncategoryService.load(positId);
			userInfo.setPosition(cate);
		}
		userInfo.setStatus(Const.CertificationStatus.PENDING);
		userinfoService.create(userInfo);
		modelMap.addAttribute("refresh", "page");
		modelMap.addAttribute("url", "/system/archive/userinfo/index");
		
		return "/success";
	}
	
	@RequestMapping("/updatebasic/{id}")
	public String updatebasic(ModelMap modelMap, UserInfoPojo model, @PathVariable Long id, @RequestParam Long deptId, @RequestParam Long positId){
		
		if(id != null){
			AUserInfo dbRecord = userinfoService.load(id);
			this.userinfoTransformer.update(model, dbRecord);
			if(deptId != null){
				APositionCategory cate =  this.positioncategoryService.load(deptId);
				dbRecord.setDeparment(cate);
			}
			if(positId != null){
				APositionCategory cate =  this.positioncategoryService.load(positId);
				dbRecord.setPosition(cate);
			}
			dbRecord.setStatus(Const.CertificationStatus.PENDING);
			this.userinfoService.update(dbRecord);
		}
		
		modelMap.addAttribute("refresh", "page");
		modelMap.addAttribute("url", "/system/archive/userinfo/index");
		
		return "/success";
	}
	
	@RequestMapping("/jobinfo/{id}")
	public String jobinfo(ModelMap modelMap, @PathVariable Long id){
		LOGGER.info("Get jobinfo id: " + id);
		if(id != null){
			AUserInfo model = userinfoService.loadByUser(id);
			if(model != null){
				modelMap.addAttribute("model", model);
			}
		}
		
		List<APositionCategory> list = this.positioncategoryService.list();
		List<APositionCategory> techList = new ArrayList<APositionCategory>();
		List<APositionCategory> positList = new ArrayList<APositionCategory>();
		for(APositionCategory cate : list){
			if(Const.PositionCategoryType.TECHLEVEL.equals(cate.getCategoryType())){
				techList.add(cate);
			}
			if(Const.PositionCategoryType.POSITIONLEVEL.equals(cate.getCategoryType())){
				positList.add(cate);
			}
		}
		
		modelMap.addAttribute("techList", techList);
		modelMap.addAttribute("positList", positList);
		
		return "/archive/userinfo/jobinfo";
	}
	
	@RequestMapping("/createjobinfo")
	public String createjobinfo(ModelMap modelMap, JobInfoPojo model, @RequestParam Long userId, @RequestParam Long techId, @RequestParam Long positId){
		
		AJobInfo jobInfo = this.jobinfoTransformer.transform(model);
		if(userId != null){
			AUserInfo userInfo = userinfoService.load(userId);
			jobInfo.setUserInfo(userInfo);
		}
		if(techId != null){
			APositionCategory cate =  this.positioncategoryService.load(techId);
			jobInfo.setTechLevel(cate);
		}
		if(positId != null){
			APositionCategory cate =  this.positioncategoryService.load(positId);
			jobInfo.setPositionLevel(cate);
		}
		
		jobInfo.setStatus(Const.CertificationStatus.PENDING);
		jobinfoService.create(jobInfo);
		modelMap.addAttribute("refresh", "page");
		modelMap.addAttribute("url", "/system/archive/userinfo/index");
		
		return "/success";
	}
	
	@RequestMapping("/updatejobinfo/{id}")
	public String updatejobinfo(ModelMap modelMap, JobInfoPojo model, @PathVariable Long id, @RequestParam Long techId, @RequestParam Long positId){
		
		if(id != null){
			AJobInfo dbRecord = jobinfoService.load(id);
			this.jobinfoTransformer.update(model, dbRecord);
			if(techId != null){
				APositionCategory cate =  this.positioncategoryService.load(techId);
				dbRecord.setTechLevel(cate);
			}
			if(positId != null){
				APositionCategory cate =  this.positioncategoryService.load(positId);
				dbRecord.setPositionLevel(cate);
			}
			dbRecord.setStatus(Const.CertificationStatus.PENDING);
			this.jobinfoService.update(dbRecord);
		}
		modelMap.addAttribute("refresh", "page");
		modelMap.addAttribute("url", "/system/archive/userinfo/index");
		
		return "/success";
	}
	
	@RequestMapping("/addeduinfo/{id}")
	public String addeduinfo(ModelMap modelMap, @PathVariable Long id){
		LOGGER.info("Get eduinfo id: " + id);
		if(id != null){
			AUserInfo model = userinfoService.load(id);
			if(model != null){
				modelMap.addAttribute("model", model);
			}
		}
		
		return "/archive/userinfo/addeduinfo";
	}
	
	@RequestMapping("/createeduinfo")
	public String createeduinfo(ModelMap modelMap, EduInfoPojo model, @RequestParam Long userId){
		
		AEducationInfo eduInfo = this.eduInfoTransformer.transform(model);
		eduInfo.setStatus(Const.CertificationStatus.PENDING);
		if(userId != null){
			AUserInfo userInfo = userinfoService.load(userId);
			eduInfo.setUserInfo(userInfo);
		}
		
		this.educationinfoService.create(eduInfo);
		modelMap.addAttribute("refresh", "page");
		modelMap.addAttribute("url", "/system/archive/userinfo/index");
		
		return "/success";
	}
	
	@RequestMapping("/editeduinfo/{id}")
	public String editeduinfo(ModelMap modelMap, @PathVariable Long id){
		LOGGER.info("Get eduinfo id: " + id);
		if(id != null){
			AUserInfo model = userinfoService.load(id);
			if(model != null){
				modelMap.addAttribute("model", model);
			}
		}
		
		return "/archive/userinfo/editeduinfo";
	}
	
	@RequestMapping("/updateeduinfo")
	public String updateeduinfo(ModelMap modelMap){
		
		Map<String, EduInfoPojo> map  = new HashMap<String, EduInfoPojo>();
		
		String paramName = null;
		String paramValue = null;
		Enumeration<String> enu = this.request.getParameterNames();  
		while(enu.hasMoreElements()){  
			paramName = enu.nextElement();
			paramValue = this.request.getParameter(paramName);
			String[] values = paramName.split("_");
			if(values.length == 2){
				EduInfoPojo pojo = null;
				if(!map.containsKey(values[1])){
					pojo = new EduInfoPojo();
					pojo.setId(Long.valueOf(values[1]));
					map.put(values[1], pojo);
				}
				else{
					pojo = map.get(values[1]);
				}
				if("university".equals(values[0])){
					pojo.setUniversity(paramValue);
				}
				if("major".equals(values[0])){
					pojo.setMajor(paramValue);
				}
				if("degree".equals(values[0])){
					pojo.setDegree(paramValue);
				}
				if("startDate".equals(values[0])){
					pojo.setStartDate(paramValue);
				}
				if("endDate".equals(values[0])){
					pojo.setEndDate(paramValue);
				}
			}
			
		}
		
		if(!map.isEmpty()){
			for(EduInfoPojo pojo : map.values()){
				AEducationInfo dbRecord = educationinfoService.load(pojo.getId());
				this.eduInfoTransformer.update(pojo, dbRecord);
				this.educationinfoService.update(dbRecord);
			}
		}
		modelMap.addAttribute("refresh", "page");
		modelMap.addAttribute("url", "/system/archive/userinfo/index");
		
		return "/success";
	}
	
	@RequestMapping("/deleteeduinfo/{id}")
	public @ResponseBody Map<String, Object> deleteeduinfo(ModelMap modelMap, @PathVariable Long id){
		Map<String, Object> map  = new HashMap<String, Object>();
		if(id != null){
			AEducationInfo dbRecord = educationinfoService.load(id);
			this.educationinfoService.delete(dbRecord);
			map.put("status", "success");
		}
		return map;
	}
}
