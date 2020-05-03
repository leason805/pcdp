package com.boxun.pcdp.archive.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ExperienceSetPojo implements Serializable{

	private static final long serialVersionUID = -643482196287925058L;
	private Long categoryId;
	private String categoryName;
	private List<ExperienceCollectionPojo> list;
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public List<ExperienceCollectionPojo> getList() {
		return list;
	}
	public void setList(List<ExperienceCollectionPojo> list) {
		this.list = list;
	}
	
	public void add(ExperienceCollectionPojo pojo){
		if(list == null){
			list = new ArrayList<ExperienceCollectionPojo>();
		}
		list.add(pojo);
	}
	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
}
