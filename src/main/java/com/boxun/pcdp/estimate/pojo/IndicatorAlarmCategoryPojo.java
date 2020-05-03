package com.boxun.pcdp.estimate.pojo;

import java.util.ArrayList;
import java.util.List;

public class IndicatorAlarmCategoryPojo {

	private Long id;
	private String name;
	private List<IndicatorAlarmPojo> list;
	private Double parentAlaram;
	
	
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
	public List<IndicatorAlarmPojo> getList() {
		return list;
	}
	public void setList(List<IndicatorAlarmPojo> list) {
		this.list = list;
	}
	
	public void addAlarm(IndicatorAlarmPojo alarm){
		if(list == null){
			list = new ArrayList<IndicatorAlarmPojo>();
		}
		list.add(alarm);
	}
	public Double getParentAlaram() {
		return parentAlaram;
	}
	public void setParentAlaram(Double parentAlaram) {
		this.parentAlaram = parentAlaram;
	}
}
