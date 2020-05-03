package com.boxun.pcdp.admin.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.boxun.estms.pojo.MenuView;
import com.boxun.estms.pojo.UserInfo;
import com.boxun.pcdp.basic.controller.BaseController;

@Controller("AindexController")
@RequestMapping("/system/admin")
public class IndexController extends BaseController{

	private static final Logger LOGGER = Logger.getLogger(IndexController.class);

	@RequestMapping("/index")
	public String index(ModelMap modelMap){
		LOGGER.info("/system/admin/index");
		UserInfo userInfo = (UserInfo)this.session.getAttribute("userInfo");
		if(userInfo != null){
			List<MenuView> menus = userInfo.getMenus("m_system");
			modelMap.addAttribute("menus", menus);
		}
		
		return "/admin/index";
		
	}
	
	@RequestMapping("/dashboard")
	public String dashboard(ModelMap modelMap){		
		return "/admin/dashboard";
		
	}
}
