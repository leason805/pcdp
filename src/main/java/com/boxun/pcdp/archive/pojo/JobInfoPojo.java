package com.boxun.pcdp.archive.pojo;

import java.io.Serializable;

public class JobInfoPojo implements Serializable{

	private static final long serialVersionUID = 756897031552137082L;

	private Long id;
	private String techLevel;
	private String positionLevel;
	private String startDate;
	private String joinDate;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTechLevel() {
		return techLevel;
	}
	public void setTechLevel(String techLevel) {
		this.techLevel = techLevel;
	}
	public String getPositionLevel() {
		return positionLevel;
	}
	public void setPositionLevel(String positionLevel) {
		this.positionLevel = positionLevel;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}
}
