package com.boxun.pcdp.archive.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ExperienceCollectionPojo implements Serializable{

	private static final long serialVersionUID = 3050869608117011950L;
	private Long categoryId;
	private String categoryName;
	private List<ExperiencePojo> list;
	
	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public List<ExperiencePojo> getList() {
		return list;
	}
	public void setList(List<ExperiencePojo> list) {
		this.list = list;
	}
	
	public void add(ExperiencePojo pojo){
		if(list == null){
			list = new ArrayList<ExperiencePojo>();
		}
		list.add(pojo);
	}
}
