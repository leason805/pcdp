package com.boxun.pcdp.admin.controller;

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
import com.boxun.estms.pojo.MenuView;
import com.boxun.estms.service.ICommonService;
import com.boxun.estms.util.StringUtil;
import com.boxun.pcdp.admin.entity.TMenu;
import com.boxun.pcdp.admin.entity.TMenuOperation;
import com.boxun.pcdp.admin.entity.TRole;
import com.boxun.pcdp.admin.entity.TUser;
import com.boxun.pcdp.admin.service.IMenuService;
import com.boxun.pcdp.admin.service.IRoleService;
import com.boxun.pcdp.admin.service.IUserService;

@Controller
@RequestMapping("/system/admin/role")
public class RoleController {

	private static final Logger LOGGER = Logger.getLogger(RoleController.class);
	
	@Autowired
	private IRoleService roleService;
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private IMenuService menuService;
	
	@Autowired
	private ICommonService commonService;
	
	@RequestMapping("/list")
	public String list(ModelMap modelMap){
		return "/admin/role/list";
	}
	
	@RequestMapping("/show/{id}")
	public String show(ModelMap modelMap, @PathVariable Long id){
		LOGGER.info("Show id: " + id);
		if(id != null){
			TRole role = roleService.load(id);
			modelMap.addAttribute("role", role);
		}
		return "/admin/role/show";
	}
	
	@RequestMapping("/listdata")
	public @ResponseBody Map<String, Object> listData(){
		LOGGER.info("Get user list data.");
		List<TRole> listdata = roleService.list();
		Map<String, Object> map  = new HashMap<String, Object>();
		map.put("sEcho", 1);  
		map.put("iTotalRecords", listdata.size());  
        map.put("iTotalDisplayRecords", listdata.size());  
		map.put("aaData", listdata);
		
		return map;
	}
	
	@RequestMapping("/create")
	public String create(ModelMap modelMap, TRole role){			
		roleService.create(role);
		return "/admin/success";
	}
	
	@RequestMapping("/update/{id}")
	public String update(ModelMap modelMap, @PathVariable Long id, TRole role){
		LOGGER.info("Update id: " + id);
		if(id != null){
			TRole dbRecord = roleService.load(id);
			dbRecord.setName(role.getName());
			dbRecord.setDescription(role.getDescription());
			roleService.update(dbRecord);
		}
		return "/admin/success";
	}
	
	@RequestMapping("/users/{id}")
	public String users(ModelMap modelMap, @PathVariable Long id){
		LOGGER.info("Show id: " + id);
		if(id != null){
			TRole role = roleService.load(id);
			modelMap.addAttribute("role", role);
		}
		return "/admin/role/users";
	}
	
	@RequestMapping("/loaduser")
	public @ResponseBody Map<String, Object> loaduser(Long id, String type){
		
		Map<String, Object> map  = new HashMap<String, Object>();
		List<Map<String, Object>> rowsData = new ArrayList<Map<String, Object>>();
		Boolean assigned = "assigned".equals(type);
		
		Long total = userService.getSize4Role(id, assigned);
		if(total > 0){
			List<TUser> list = userService.listUsers4Role(id, assigned);
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
	
	@RequestMapping("/updateusers/{id}")
	public String updateusers(ModelMap modelMap, @PathVariable Long id, String userIds){
		LOGGER.info("Update id: " + id);
		if(id != null){
			TRole dbRecord = roleService.load(id);
			
			
			if(StringUtil.isNotBlank(userIds)){
				List<Long> idList = StringUtil.split4Long(userIds, Const.COMMA_DELIMITER);
				if(dbRecord != null){
					dbRecord.getUsers().clear();
					for(Long uid : idList){
						TUser user = userService.load(uid);
						dbRecord.getUsers().add(user);
					}
					roleService.update(dbRecord);
				}
			}
		}
		return "/admin/success";
	}
	
	@RequestMapping("/perms/{id}")
	public String perms(ModelMap modelMap, @PathVariable Long id){
		LOGGER.info("Show id: " + id);
		if(id != null){
			List<MenuView> list = new ArrayList<MenuView>();
			
			TRole role = roleService.load(id);
			modelMap.addAttribute("role", role);
			
			List<TMenu> tops = menuService.listTops();
			List<TMenuOperation> menuOperations = commonService.listMenuOperations();
			
			MenuView view = null;
			MenuView.Menu menu = null;
			MenuView.Operation operation = null;
			for(TMenu parent : tops){
				view = new MenuView();
				view.setName(parent.getName());

				for(TMenu child : parent.getChildren()){
					 menu = view.new Menu();
					 menu.setId(child.getId());
					 menu.setName(child.getName());
					 for(TMenuOperation menuOperation : menuOperations){
						 if(child.getId().equals(menuOperation.getMenu().getId())){
							 operation = view.new Operation();
							 operation.setId(menuOperation.getId());
							 operation.setName(menuOperation.getOperation().getName());
							 if(role.getOperations().contains(menuOperation)){
								 operation.setSelected(true);
							 }
							 menu.addOperation(operation);
						 }
					 }
					 view.addMenu(menu);
				}
				list.add(view);
			}
			
			modelMap.addAttribute("list", list);
		}
		return "/admin/role/perms";
	}
	
	@RequestMapping("/updateperms/{id}")
	public String updateperms(ModelMap modelMap, @PathVariable Long id, String permIds){
		LOGGER.info("Update id: " + id);
		if(id != null){
			TRole dbRecord = roleService.load(id);
			if(StringUtil.isNotBlank(permIds)){
				List<Long> idList = StringUtil.split4Long(permIds, Const.COMMA_DELIMITER);
				if(dbRecord != null){
					dbRecord.getOperations().clear();
					
					if(idList != null){
						for(Long permissionId : idList){
							TMenuOperation operation = commonService.loadMenuOperation(permissionId);
							dbRecord.getOperations().add(operation);
						}
					}
					roleService.update(dbRecord);
				}
			}
		}
		return "/admin/success";
	}
}
