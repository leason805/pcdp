package com.boxun.pcdp.estimate.pojo;

import java.util.ArrayList;
import java.util.List;

public class AssessStaticsSetPojo {

	private List<AssessStaticsPojo> list;
	private Integer userSize;
	private List<Double> scoreList;
	
	public Integer getUserSize() {
		return userSize;
	}
	public void setUserSize(Integer userSize) {
		this.userSize = userSize;
	}
	public List<AssessStaticsPojo> getList() {
		return list;
	}
	public void setList(List<AssessStaticsPojo> list) {
		this.list = list;
	}
	public List<Double> getScoreList() {
		return scoreList;
	}
	public void setScoreList(List<Double> scoreList) {
		this.scoreList = scoreList;
	}
	
	public void addScore(Double score){
		if(this.scoreList == null){
			this.scoreList = new ArrayList<Double>();
		}
		this.scoreList.add(score);
	}
}
