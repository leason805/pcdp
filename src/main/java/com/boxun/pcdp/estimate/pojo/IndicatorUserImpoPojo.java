package com.boxun.pcdp.estimate.pojo;

import java.io.Serializable;

public class IndicatorUserImpoPojo implements Serializable{

	private static final long serialVersionUID = -8835027960796424648L;

	private Long userId;
	private Long userName;
	private Double rating;
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getUserName() {
		return userName;
	}
	public void setUserName(Long userName) {
		this.userName = userName;
	}
	public Double getRating() {
		return rating;
	}
	public void setRating(Double rating) {
		this.rating = rating;
	}
}
