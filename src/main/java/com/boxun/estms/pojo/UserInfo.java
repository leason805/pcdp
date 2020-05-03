package com.boxun.estms.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.boxun.estms.util.SortUtil;

public class UserInfo {

	private Long userId;
	private String userName;
	private Date loginTime;
	private List<Module> modules;
	private List<MenuView> menuViews;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Date getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
	public List<Module> getModules() {
		return modules;
	}
	public void setModules(List<Module> modules) {
		this.modules = modules;
	}
	public List<MenuView> getMenuViews() {
		return menuViews;
	}
	public void setMenuViews(List<MenuView> menuViews) {
		this.menuViews = menuViews;
	}
	
	public Module getModule(String code){
		if(modules != null && !modules.isEmpty()){
			for(Module module : modules){
				if(module.getCode().equals(code)){
					return module;
				}
			}
		}
		return null;
	}
	
	public List<MenuView> getMenus(String module){
		List<MenuView> list =  new ArrayList<MenuView>();
		if(menuViews != null && !menuViews.isEmpty()){
			for(MenuView view : menuViews){
				if(module.equals(view.getModule().getCode())){
					if(!list.contains(view)){
						list.add(view);
					}
				}
			}
		}
		SortUtil.sort(list, "order");

		for(MenuView mv : list){
			SortUtil.sort(mv.getMenus(), "order");
		}
		return list;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
}
