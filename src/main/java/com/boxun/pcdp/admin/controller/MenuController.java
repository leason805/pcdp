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
import com.boxun.pcdp.admin.entity.TMenu;
import com.boxun.pcdp.admin.entity.TModule;
import com.boxun.pcdp.admin.service.IMenuService;
import com.boxun.pcdp.admin.service.IModuleService;

@Controller
@RequestMapping("/system/admin/menu")
public class MenuController {

private static final Logger LOGGER = Logger.getLogger(MenuController.class);
	
	@Autowired
	private IMenuService menuService;
	
	@Autowired
	private IModuleService moduleService;
	
	@RequestMapping("/list")
	public String list(ModelMap modelMap){
		return "/admin/menu/list";
	}
	
	@RequestMapping("/show/{menuId}")
	public String show(ModelMap modelMap, @PathVariable Long menuId){
		LOGGER.info("Get menu id: " + menuId);
		List<TMenu> topMenus = menuService.listTops();
		modelMap.addAttribute("topMenus", topMenus);
		
		if(menuId != null){
			TMenu menu = menuService.load(menuId);
			modelMap.addAttribute("menu", menu);
		}
		else{
			List<TModule> modules = moduleService.list();
			modelMap.addAttribute("modules", modules);
		}
		
		return "/admin/menu/show";
	}
	
	@RequestMapping("/listdata")
	public @ResponseBody Map<String, Object> listData(){
		LOGGER.info("Get user list data.");
		List<TMenu> listdata = menuService.list();
		Map<String, Object> map  = new HashMap<String, Object>();
		map.put("sEcho", 1);  
		map.put("iTotalRecords", listdata.size());  
        map.put("iTotalDisplayRecords", listdata.size());  
		map.put("aaData", listdata);
		
		return map;
	}
	
	@RequestMapping("/create")
	public String create(ModelMap modelMap, @RequestParam Long parentId, @RequestParam Long moduleId, TMenu menu){
		LOGGER.info("Get @RequestParam: " + parentId);
		
		if(StringUtil.isNotBlank(menu.getName()) && StringUtil.isNotBlank(menu.getUrl())){
			if(parentId != null && parentId > 0){
				TMenu parent = menuService.load(parentId);
				menu.setParent(parent);
			}
			else{
				if(moduleId != null && moduleId > 0){
					TModule module = moduleService.load(moduleId);
					menu.setModule(module);
				}
			}

			menuService.create(menu);
		}
		return "/admin/success";
	}
	
	@RequestMapping("/update/{menuId}")
	public String update(ModelMap modelMap, @PathVariable Long menuId, TMenu menu){
		LOGGER.info("Get menu id: " + menuId);
		if(menuId != null){
			TMenu dbMenu = menuService.load(menuId);
			
			dbMenu.setCode(menu.getCode());
			dbMenu.setName(menu.getName());
			dbMenu.setUrl(menu.getUrl());
			dbMenu.setOrder(menu.getOrder());
			dbMenu.setIcon(menu.getIcon());
			dbMenu.setLinkType(menu.getLinkType());
			
			menuService.update(dbMenu);
		}
		return "/admin/success";
	}
}
