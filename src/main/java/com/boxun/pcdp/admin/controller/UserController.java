package com.boxun.pcdp.admin.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.boxun.estms.entity.Const;
import com.boxun.estms.util.MD5;
import com.boxun.estms.util.StringUtil;
import com.boxun.pcdp.admin.entity.TCompany;
import com.boxun.pcdp.admin.entity.TUser;
import com.boxun.pcdp.admin.service.ICompanyService;
import com.boxun.pcdp.admin.service.IUserService;
import com.boxun.pcdp.basic.controller.BaseController;

@Controller
@RequestMapping("/system/admin/user")
public class UserController extends BaseController{

	private static final Logger LOGGER = Logger.getLogger(UserController.class);
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private ICompanyService companyService;
	
	@RequestMapping("/list")
	public String list(ModelMap modelMap){
		return "/admin/user/list";
	}
	
	@RequestMapping("/show/{userId}")
	public String show(ModelMap modelMap, @PathVariable Long userId){
		LOGGER.info("Get user id: " + userId);
		List<TCompany> companys = this.companyService.list();
		modelMap.addAttribute("companys", companys);
		
		if(userId != null){
			TUser user = userService.load(userId);
			modelMap.addAttribute("user", user);
		}
		return "/admin/user/show";
	}
	
	@RequestMapping("/listdata")
	public @ResponseBody Map<String, Object> listData(){
		LOGGER.info("Get user list data.");
		List<TUser> listdata = userService.list();
		Map<String, Object> map  = new HashMap<String, Object>();
		map.put("sEcho", 1);  
		map.put("iTotalRecords", listdata.size());  
        map.put("iTotalDisplayRecords", listdata.size());  
		map.put("aaData", listdata);
		
		return map; 
	}
	
	@RequestMapping("/create")
	public String create(ModelMap modelMap, @RequestParam String loginID, TUser user, @RequestParam Long companyId){
		LOGGER.info("Get @RequestParam: " + user.getLoginID());
		LOGGER.info("Get @RequestParam: loginID" + loginID);
		if(user.getLoginID() != null){
			//check duplicate id from db
			user.setCreateTime(new Date());
			user.setPassword(MD5.compute(user.getPassword()));
			user.setStatus(Const.UserStatus.ENABLE);
			if(companyId != null){
				TCompany  company = this.companyService.load(companyId);
				user.setCompany(company);
			}
			
			userService.create(user);
		}
		return "/admin/success";
	}
	
	@RequestMapping("/update/{userId}")
	public String update(ModelMap modelMap, @PathVariable Long userId, TUser user, @RequestParam Long companyId){
		LOGGER.info("Get user id: " + userId);
		if(userId != null){
			TUser dbUser = userService.load(userId);
			
			dbUser.setEmail(user.getEmail());
			dbUser.setLoginID(user.getLoginID());
			dbUser.setTelephone(user.getTelephone());
			dbUser.setName(user.getName());
//			dbUser.setUserType(user.getUserType());
			if(StringUtil.isNotBlank(user.getPassword())){
				dbUser.setPassword(MD5.compute(user.getPassword()));
			}
			if(companyId != null){
				TCompany  company = this.companyService.load(companyId);
				dbUser.setCompany(company);
			}
			userService.update(dbUser);
		}
		return "/admin/success";
	}
	
	@RequestMapping("/enable")
	public @ResponseBody Map<String, Object> enable(Long id){
		Map<String, Object> map  = new HashMap<String, Object>();
		if(id != null){
			try{
				TUser user = this.userService.load(id);
				user.setStatus(Const.UserStatus.ENABLE);
				this.userService.update(user);
			}
			catch(Exception e){
				map.put("status", "fail");  
			}
			map.put("status", "success");  
		}
		return map; 
	}
	
	@RequestMapping("/disable")
	public @ResponseBody Map<String, Object> disable(Long id){
		Map<String, Object> map  = new HashMap<String, Object>();
		if(id != null){
			try{
				TUser user = this.userService.load(id);
				user.setStatus(Const.UserStatus.DISABLE);
				this.userService.update(user);
			}
			catch(Exception e){
				map.put("status", "fail");  
			}
			map.put("status", "success");  
		}
		return map; 
	}
	
	@RequestMapping("/delete")
	public @ResponseBody Map<String, Object> delete(Long id){
		Map<String, Object> map  = new HashMap<String, Object>();
		if(id != null){
			try{
				TUser user = this.userService.load(id);
				user.setStatus(Const.UserStatus.DELETE);
				this.userService.update(user);
			}
			catch(Exception e){
				map.put("status", "fail");  
			}
			map.put("status", "success");  
		}
		return map; 
	}
}
