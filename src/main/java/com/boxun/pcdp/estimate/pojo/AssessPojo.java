package com.boxun.pcdp.estimate.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.boxun.estms.entity.Const;

public class AssessPojo implements Serializable{

	private static final long serialVersionUID = 3426380473368946330L;
	private Long id;
	private Const.Mandatory mandatory;
	private Boolean auto;
	private String sequence;
	private String name;
	private Integer level;
	private Double score;
	private Double calculation;
	private String summary;
	private String description;
	private AssessPojo parent;
	private List<AssessPojo> children;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Const.Mandatory getMandatory() {
		return mandatory;
	}
	public void setMandatory(Const.Mandatory mandatory) {
		this.mandatory = mandatory;
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
	public Double getScore() {
		return score;
	}
	public void setScore(Double score) {
		this.score = score;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public AssessPojo getParent() {
		return parent;
	}
	public void setParent(AssessPojo parent) {
		this.parent = parent;
	}
	public List<AssessPojo> getChildren() {
		return children;
	}
	public void setChildren(List<AssessPojo> children) {
		this.children = children;
	}
	
	public void addChild(AssessPojo assess){
		if(children == null){
			children = new ArrayList<AssessPojo>();
		}
		children.add(assess);
	}
	public Boolean getAuto() {
		return auto;
	}
	public void setAuto(Boolean auto) {
		this.auto = auto;
	}
	public Double getCalculation() {
		return calculation;
	}
	public void setCalculation(Double calculation) {
		this.calculation = calculation;
	}
}
