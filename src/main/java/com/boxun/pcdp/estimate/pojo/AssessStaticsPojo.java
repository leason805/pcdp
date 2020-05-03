package com.boxun.pcdp.estimate.pojo;

import java.util.ArrayList;
import java.util.List;

public class AssessStaticsPojo {

	private Long id;
	//private Const.Mandatory mandatory;
	private String sequence;
	private String name;
	private Integer level;
	private Double result;
	private AssessStaticsPojo parent;
	private List<AssessStaticsPojo> children;
	private List<AssessStaticsUserPojo> userScores;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSequence() {
		return sequence;
	}
	public void setSequence(String sequence) {
		this.sequence = sequence;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public Double getResult() {
		return result;
	}
	public void setResult(Double result) {
		this.result = result;
	}
	public AssessStaticsPojo getParent() {
		return parent;
	}
	public void setParent(AssessStaticsPojo parent) {
		this.parent = parent;
	}
	public List<AssessStaticsPojo> getChildren() {
		return children;
	}
	public void setChildren(List<AssessStaticsPojo> children) {
		this.children = children;
	}
	
	public void addChild(AssessStaticsPojo child) {
		if(this.children == null){
			this.children = new ArrayList<AssessStaticsPojo>();
		}
		children.add(child);
	}
	
	
	public List<AssessStaticsUserPojo> getUserScores() {
		return userScores;
	}
	public void setUserScores(List<AssessStaticsUserPojo> userScores) {
		this.userScores = userScores;
	}
	
	public void addUserScore(AssessStaticsUserPojo score){
		if(this.userScores == null){
			this.userScores = new ArrayList<AssessStaticsUserPojo>();
		}
		userScores.add(score);
	}
}
