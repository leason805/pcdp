package com.boxun.pcdp.performance.pojo;

import com.boxun.estms.entity.Const;

public class ArrangePojo {

	private Long id;
	private String userName;
	private String supAssessor;
	private String colAssessor;
	private Const.AssessStatus status;
	private String percentage;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getSupAssessor() {
		return supAssessor;
	}
	public void setSupAssessor(String supAssessor) {
		this.supAssessor = supAssessor;
	}
	public String getColAssessor() {
		return colAssessor;
	}
	public void setColAssessor(String colAssessor) {
		this.colAssessor = colAssessor;
	}
	public Const.AssessStatus getStatus() {
		return status;
	}
	public void setStatus(Const.AssessStatus status) {
		this.status = status;
	}
	public String getPercentage() {
		return percentage;
	}
	public void setPercentage(String percentage) {
		this.percentage = percentage;
	}
}
