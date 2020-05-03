package com.boxun.pcdp.estimate.pojo;

import java.util.ArrayList;
import java.util.List;

public class ReportPojo {

	private Double data;
	private List<Double> datas;
	private String name;
	private String label;
	private String color;
	private String order;
	
	public Double getData() {
		return data;
	}
	public void setData(Double data) {
		this.data = data;
	}
	public List<Double> getDatas() {
		return datas;
	}
	public void setDatas(List<Double> datas) {
		this.datas = datas;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public void addData(Double data){
		if(datas == null){
			datas = new ArrayList<Double>();
		}
		datas.add(data);
	}
}
