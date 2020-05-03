package com.boxun.pcdp.archive.pojo;

import java.util.ArrayList;
import java.util.List;

public class CertificationSetPojo {

	private Long categoryId;
	private String categoryName;
	private List<CertificationPojo> list;
	
	public List<CertificationPojo> getList() {
		return list;
	}
	public void setList(List<CertificationPojo> list) {
		this.list = list;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	public void add(CertificationPojo pojo){
		if(list == null){
			list = new ArrayList<CertificationPojo>();
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
