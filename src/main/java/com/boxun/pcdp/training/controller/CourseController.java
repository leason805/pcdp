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

import com.boxun.estms.entity.Const;
import com.boxun.estms.pojo.UserInfo;
import com.boxun.pcdp.admin.entity.TUser;
import com.boxun.pcdp.admin.service.IUserService;
import com.boxun.pcdp.basic.controller.BaseController;
import com.boxun.pcdp.training.entity.TCourse;
import com.boxun.pcdp.training.service.ICourseService;

@Controller
@RequestMapping("/system/training/course")
public class CourseController extends BaseController{

private static final Logger LOGGER = Logger.getLogger(CourseController.class);
	
	@Autowired
	private ICourseService courseService;
	
	@Autowired
	private IUserService userService;
	
	@RequestMapping("/list")
	public String list(ModelMap modelMap){
		return "/training/course/list";
	}
	
	@RequestMapping("/show/{id}")
	public String show(ModelMap modelMap, @PathVariable Long id){
		LOGGER.info("Get show id: " + id);
		if(id != null){
			TCourse model = courseService.load(id);
			modelMap.addAttribute("model", model);
		}
		
		return "/training/course/show";
	}
	
	@RequestMapping("/listdata")
	public @ResponseBody Map<String, Object> listData(){
		LOGGER.info("Get list data.");
		List<TCourse> listdata = courseService.list();
		Map<String, Object> map  = new HashMap<String, Object>();
		map.put("sEcho", 1);  
		map.put("iTotalRecords", listdata.size());  
        map.put("iTotalDisplayRecords", listdata.size());  
		map.put("aaData", listdata);
		
		return map;
	}
	
	@RequestMapping("/create")
	public String create(ModelMap modelMap, TCourse model){

		UserInfo userInfo = (UserInfo)this.session.getAttribute("userInfo");
		if(userInfo != null){
			TUser user = userService.load(userInfo.getUserId());
			model.setCreator(user);
		}
		model.setStatus(Const.CourseStatus.ACTIVE);
		courseService.create(model);
		return "/success";
	}
	
	@RequestMapping("/update/{id}")
	public String update(ModelMap modelMap, @PathVariable Long id, TCourse model){
		LOGGER.info("Get update id: " + id);
		LOGGER.info("Update id: " + id);
		if(id != null){
			TCourse dbRecord = courseService.load(id);
			dbRecord.setName(model.getName());
			dbRecord.setDescription(model.getDescription());
			courseService.update(dbRecord);
		}
		return "/success";
	}
}
