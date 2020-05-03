package com.boxun.pcdp.estimate.pojo;

import java.util.List;

import com.boxun.pcdp.estimate.entity.EIndicator;

public class IndicatorCategoryPojo {

	private Long id;
	private String name;
	private List<EIndicator> list;
	
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
	public List<EIndicator> getList() {
		return list;
	}
	public void setList(List<EIndicator> list) {
		this.list = list;
	}
}
