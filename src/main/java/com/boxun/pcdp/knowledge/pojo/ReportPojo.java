package com.boxun.pcdp.knowledge.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ReportPojo implements Serializable{

	private static final long serialVersionUID = 5351364372795186451L;
	private String name;
	private List<ReportItemPojo> items;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<ReportItemPojo> getItems() {
		return items;
	}
	public void setItems(List<ReportItemPojo> items) {
		this.items = items;
	}
	
	public void addItem(ReportItemPojo item){
		if(items == null){
			items = new ArrayList<ReportItemPojo>();
		}
		items.add(item);
	}
}
