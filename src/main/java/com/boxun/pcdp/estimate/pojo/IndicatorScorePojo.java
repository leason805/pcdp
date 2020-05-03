package com.boxun.pcdp.estimate.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.boxun.estms.entity.Const;

public class IndicatorScorePojo implements Serializable{

	private static final long serialVersionUID = 1385745753805048749L;

	private Long id;
	private String name;
	private String sequence;
	private Integer score;
	private Integer level;
	private Const.Mandatory mandatory;
	private IndicatorScorePojo parent;
	private List<IndicatorScorePojo> children;
	
	private List<IndicatorUserScorePojo> userScores;
	
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
	public String getSequence() {
		return sequence;
	}
	public void setSequence(String sequence) {
		this.sequence = sequence;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public Const.Mandatory getMandatory() {
		return mandatory;
	}
	public void setMandatory(Const.Mandatory mandatory) {
		this.mandatory = mandatory;
	}
	public List<IndicatorScorePojo> getChildren() {
		return children;
	}
	public void setChildren(List<IndicatorScorePojo> children) {
		this.children = children;
	}
	
	public void addChild(IndicatorScorePojo score){
		if(children == null){
			children = new ArrayList<IndicatorScorePojo>();
		}
		children.add(score);
	}
	
	public IndicatorScorePojo getParent() {
		return parent;
	}
	
	public void setParent(IndicatorScorePojo parent) {
		this.parent = parent;
	}
	
	public Integer getLevel() {
		return level;
	}
	
	public void setLevel(Integer level) {
		this.level = level;
	}
	
	public List<IndicatorUserScorePojo> getUserScores() {
		return userScores;
	}
	
	public void setUserScores(List<IndicatorUserScorePojo> userScores) {
		this.userScores = userScores;
	}
	
	public void addUserScore(IndicatorUserScorePojo pojo){
		if(userScores == null){
			userScores = new ArrayList<IndicatorUserScorePojo>();
		}
		userScores.add(pojo);
	}
}
