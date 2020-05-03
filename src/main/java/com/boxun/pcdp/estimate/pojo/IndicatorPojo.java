package com.boxun.pcdp.estimate.pojo;

import java.io.Serializable;

public class IndicatorPojo implements Serializable{

	private static final long serialVersionUID = -2594296840244185181L;
	
	private Long id;
	private String name;
	private Integer level;
	private Integer score;
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
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
}
