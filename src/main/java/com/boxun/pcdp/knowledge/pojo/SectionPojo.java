package com.boxun.pcdp.knowledge.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SectionPojo implements Serializable{

	private static final long serialVersionUID = -5527710120532041710L;

	private Long id;
	private String name;
	private String description;
	private String sequence;
	private Integer level;
	private Long questSize = 0l;
	private SectionPojo parent;
	private List<SectionPojo> children;
	
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getQuestSize() {
		return questSize;
	}
	public void setQuestSize(Long questSize) {
		this.questSize = questSize;
	}
	public String getSequence() {
		return sequence;
	}
	public void setSequence(String sequence) {
		this.sequence = sequence;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public SectionPojo getParent() {
		return parent;
	}
	public void setParent(SectionPojo parent) {
		this.parent = parent;
	}
	public List<SectionPojo> getChildren() {
		return children;
	}
	public void setChildren(List<SectionPojo> children) {
		this.children = children;
	}
	public void addChild(SectionPojo child){
		if(children == null){
			children = new ArrayList<SectionPojo>();
		}
		children.add(child);
	}
}
