package com.boxun.pcdp.estimate.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class IndicatorImpoPojo implements Serializable{

	private static final long serialVersionUID = -6999606199144320633L;
	private Long id;
	private Long parentId;
	private Long firstId;
	private String firstName;
	private Long secondId;
	private String secondName;
	private Double rating;
	private Integer order;
	
	private List<IndicatorUserImpoPojo> userScores;
	
	public Long getFirstId() {
		return firstId;
	}
	public void setFirstId(Long firstId) {
		this.firstId = firstId;
	}
	public Long getSecondId() {
		return secondId;
	}
	public void setSecondId(Long secondId) {
		this.secondId = secondId;
	}
	public Double getRating() {
		return rating;
	}
	public void setRating(Double rating) {
		this.rating = rating;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getSecondName() {
		return secondName;
	}
	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public Integer getOrder() {
		return order;
	}
	public void setOrder(Integer order) {
		this.order = order;
	}
	public List<IndicatorUserImpoPojo> getUserScores() {
		return userScores;
	}
	public void setUserScores(List<IndicatorUserImpoPojo> userScores) {
		this.userScores = userScores;
	}
	
	public void addUserScore(IndicatorUserImpoPojo score){
		if(this.userScores == null){
			userScores = new ArrayList<IndicatorUserImpoPojo>();
		}
		userScores.add(score);
	}
}
