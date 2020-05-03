package com.boxun.pcdp.estimate.pojo;

import java.util.List;

public class IndicatorScoreCategoryPojo {

	private Long id;
	private String name;
	private List<String> names;
	private Integer size;
	private List<IndicatorScorePojo> list;
	
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
	public List<String> getNames() {
		return names;
	}
	public void setNames(List<String> names) {
		this.names = names;
	}
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
	public List<IndicatorScorePojo> getList() {
		return list;
	}
	public void setList(List<IndicatorScorePojo> list) {
		this.list = list;
	}
}
