package com.boxun.pcdp.archive.pojo;

public class TechStaticsPojo {

	private Integer size = 0;
	private String name;
	private String color;
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	
	public void increaseSize(){
		size += 1;
	}
}
