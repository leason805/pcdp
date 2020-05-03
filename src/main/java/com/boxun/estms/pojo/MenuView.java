package com.boxun.estms.pojo;

import java.util.ArrayList;
import java.util.List;

public class MenuView {
	private Long id;
	private String name;
	private String icon;
	private Integer order;
	private Module module;
	private List<Menu> menus;
	
	
	public class Menu{
		private Long id;
		private String name;
		private String url;
		private Integer order;
		private List<Operation> operations;
		
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getUrl() {
			return url;
		}
		public void setUrl(String url) {
			this.url = url;
		}
		public Integer getOrder() {
			return order;
		}
		public void setOrder(Integer order) {
			this.order = order;
		}
		public List<Operation> getOperations() {
			return operations;
		}
		public void setOperations(List<Operation> operations) {
			this.operations = operations;
		}
		public void addOperation(Operation operation){
			if(operations == null){
				operations = new ArrayList<Operation>();
			}
			operations.add(operation);
		}
	}
	
	public class Operation{
		private Long id;
		private String name;
		private String code;
		private Boolean selected;
		
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getCode() {
			return code;
		}
		public void setCode(String code) {
			this.code = code;
		}
		public Boolean getSelected() {
			return selected;
		}
		public void setSelected(Boolean selected) {
			this.selected = selected;
		}
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getOrder() {
		return order;
	}
	public void setOrder(Integer order) {
		this.order = order;
	}
	public List<Menu> getMenus() {
		return menus;
	}
	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}
	public void addMenu(Menu menu){
		if(menus == null){
			menus = new ArrayList<Menu>();
		}
		menus.add(menu);
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public Module getModule() {
		return module;
	}
	public void setModule(Module module) {
		this.module = module;
	}
}
