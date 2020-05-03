package com.boxun.pcdp.main.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.boxun.estms.entity.Const;
import com.boxun.estms.pojo.MenuView;
import com.boxun.estms.pojo.Module;
import com.boxun.estms.pojo.UserInfo;
import com.boxun.estms.util.MD5;
import com.boxun.estms.util.SortUtil;
import com.boxun.estms.util.StringUtil;
import com.boxun.pcdp.admin.entity.TMenu;
import com.boxun.pcdp.admin.entity.TMenuOperation;
import com.boxun.pcdp.admin.entity.TOperation;
import com.boxun.pcdp.admin.entity.TRole;
import com.boxun.pcdp.admin.entity.TUser;
import com.boxun.pcdp.admin.service.IUserService;
import com.boxun.pcdp.basic.controller.BaseController;

@Controller("SindexController")
@RequestMapping("/system")
public class LoginController extends BaseController{

	private static final Logger LOGGER = Logger.getLogger(LoginController.class);
	
	@Autowired
	private IUserService userService;
	
	@RequestMapping("/login")
	public String index(){
		return "/system/index";
	}
	
	@RequestMapping("/logout")
	public String logout(){
		this.session.removeAttribute("userInfo");
		return "/system/index";
	}
	
	@RequestMapping("/main")
	public String main(ModelMap modelMap, String username, String password){
		LOGGER.info("username: " + username);

		UserInfo userInf = (UserInfo)this.session.getAttribute("userInfo");
		if(userInf != null){
			modelMap.addAttribute("modules", userInf.getModules());
			return "/system/main";
		}
		
		if(StringUtil.isNotBlank(username) && StringUtil.isNotBlank(password)){
			TUser user = userService.find(username, password);
			if(user == null){
				modelMap.addAttribute("message", "用户名/密码不正确。");
				return "/system/index";
			}
			if(!Const.UserStatus.ENABLE.equals(user.getStatus())){
				modelMap.addAttribute("message", "用户已被禁用，请联系管理员。");
				return "/system/index";
			}

			List<MenuView> menuViews = menus(user);
			
			//Map<String, List<MenuView>> menuMap = new HashMap<String, List<MenuView>>();
			List<Module> modules = new ArrayList<Module>();
			
			for(MenuView view : menuViews){
				if(!existMenuModule(view.getModule(), modules)){
					modules.add(view.getModule());
				}
//				if(!menuMap.containsKey(view.getModule().getCode())){
//					List<MenuView> list =  new ArrayList<MenuView>();
//					menuMap.put(view.getModule().getCode(), list);
//				}
//				menuMap.get(view.getModule().getCode()).add(view);
			}
			
			SortUtil.sort(modules, "order");
			modelMap.addAttribute("modules", modules);
			
			UserInfo userInfo = new UserInfo();
			userInfo.setLoginTime(new Date());
			userInfo.setUserId(user.getId());
			userInfo.setUserName(user.getName());
			userInfo.setModules(modules);
			userInfo.setMenuViews(menuViews);
			
			this.session.setAttribute("userInfo", userInfo);
			
//			this.session.setAttribute("menuMap", menuMap);
//			this.session.setAttribute("modules", modules);
			
			return "/system/main";
		}
		modelMap.addAttribute("message", "用户名/密码不能为空。");
		return "/system/index";
		
	}
	
	@RequestMapping("/password")
	public String password(ModelMap modelMap){
		LOGGER.info("change password: ");

		UserInfo userInf = (UserInfo)this.session.getAttribute("userInfo");
		TUser dbUser = userService.load(userInf.getUserId());
		modelMap.addAttribute("model", dbUser);
		return "/system/password";
	}
	
	@RequestMapping("/savepwd")
	public String savepwd(ModelMap modelMap, String password){
		LOGGER.info("change password: ");

		UserInfo userInf = (UserInfo)this.session.getAttribute("userInfo");
		TUser dbUser = userService.load(userInf.getUserId());
		if(StringUtil.isNotBlank(password)){
			dbUser.setPassword(MD5.compute(password));
			this.userService.update(dbUser);
		}
		modelMap.addAttribute("autoclose", true);
		return "/success";
	}
	
	private static MenuView existMenuView(TMenu menu, List<MenuView> menus){
		if(menus != null){
			for(MenuView menuView : menus){
				if(menu.getId().equals(menuView.getId())){
					return menuView;
				}
			}
		}
		return null;
	}
	
	private static boolean existMenuModule(Module module, List<Module> modules){
		if(modules != null && !modules.isEmpty()){
			for(Module m : modules){
				if(m.getId().equals(module.getId())){
					return true;
				}
			}
		}
		return false;
	}
	
	private static MenuView.Menu existMenu(TMenu menu, MenuView menuView){
		if(menuView != null && menuView.getMenus() != null){
			for(MenuView.Menu vMenu : menuView.getMenus()){
				if(menu.getId().equals(vMenu.getId())){
					return vMenu;
				}
			}
		}
		return null;
	}
	
	private static MenuView.Operation existOperation(TOperation operatioin, MenuView.Menu menu){
		if(menu != null && menu.getOperations() != null){
			for(MenuView.Operation vOperation : menu.getOperations()){
				if(operatioin.getCode().equals(vOperation.getCode())){
					return vOperation;
				}
			}
		}
		return null;
	}
	
	
	private List<MenuView> menus(TUser user){
		MenuView view = null;
		Module module = null;
		MenuView.Menu menu = null;
		MenuView.Operation operation = null;
		List<MenuView> menus = new ArrayList<MenuView>();
		
		if(user != null){
			if(user.getRoles() != null && !user.getRoles().isEmpty()){
				for(TRole role : user.getRoles()){
					for(TMenuOperation menuOperation : role.getOperations()){
						view = existMenuView(menuOperation.getMenu().getParent(), menus);
						if(view == null){
							view = new MenuView();
							view.setId(menuOperation.getMenu().getParent().getId());
							view.setName(menuOperation.getMenu().getParent().getName());
							view.setOrder(menuOperation.getMenu().getParent().getOrder());
							view.setIcon(menuOperation.getMenu().getParent().getIcon());
							
							module = new Module();
							module.setId(menuOperation.getMenu().getParent().getModule().getId());
							module.setName(menuOperation.getMenu().getParent().getModule().getName());
							module.setOrder(menuOperation.getMenu().getParent().getModule().getOrder());
							module.setUrl(menuOperation.getMenu().getParent().getModule().getUrl());
							module.setCode(menuOperation.getMenu().getParent().getModule().getCode());
							module.setIcon(menuOperation.getMenu().getParent().getModule().getIcon());
							view.setModule(module);
							
							menus.add(view);
						}
						menu = existMenu(menuOperation.getMenu(), view);
						if(menu == null){
							menu = view.new Menu();
							menu.setId(menuOperation.getMenu().getId());
							menu.setName(menuOperation.getMenu().getName());
							menu.setUrl(menuOperation.getMenu().getUrl());
							menu.setOrder(menuOperation.getMenu().getOrder());
							view.addMenu(menu);
						}
						operation = existOperation(menuOperation.getOperation(), menu);
						if(operation == null){
							operation = view.new Operation();
							operation.setName(menuOperation.getOperation().getName());
							operation.setCode(menuOperation.getOperation().getCode());
							menu.addOperation(operation);
						}
					}
				}
			}
		}
		
		return menus;
	}
}
