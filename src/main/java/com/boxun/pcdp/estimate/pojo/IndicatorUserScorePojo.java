package com.boxun.pcdp.estimate.pojo;

import java.io.Serializable;

public class IndicatorUserScorePojo implements Serializable{

	private static final long serialVersionUID = 6706431334923143770L;
	
	private Long userId;
	private String userName;
	private Integer score;
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}

}
