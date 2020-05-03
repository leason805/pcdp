package com.boxun.pcdp.capacity.pojo;

import java.io.Serializable;

public class ArrangePojo implements Serializable{

	private static final long serialVersionUID = -6834800028906059164L;
	
	private String userIds;
	private Long projectId;
	private Long invigilator;
	private Long adjudicator;
	private String title;
	private String startTime;
	private String address;
	
	public String getUserIds() {
		return userIds;
	}
	public void setUserIds(String userIds) {
		this.userIds = userIds;
	}

	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Long getProjectId() {
		return projectId;
	}
	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}
	public Long getInvigilator() {
		return invigilator;
	}
	public void setInvigilator(Long invigilator) {
		this.invigilator = invigilator;
	}
	public Long getAdjudicator() {
		return adjudicator;
	}
	public void setAdjudicator(Long adjudicator) {
		this.adjudicator = adjudicator;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

}
