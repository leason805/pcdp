package com.boxun.pcdp.performance.pojo;

import java.util.ArrayList;
import java.util.List;

import com.boxun.pcdp.capacity.pojo.ReviewItemPojo;
import com.boxun.pcdp.capacity.pojo.ReviewPojo;

public class ResultPojo {

	private Long id;
	private String sequence;
	private String name;
	private Integer score;
	private List<ReviewItemPojo> supItems;
	private List<ReviewItemPojo> colItems;
	
	private ResultPojo parent;
	private List<ResultPojo> children;
	
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
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public List<ReviewItemPojo> getSupItems() {
		return supItems;
	}
	public void setSupItems(List<ReviewItemPojo> supItems) {
		this.supItems = supItems;
	}
	public List<ReviewItemPojo> getColItems() {
		return colItems;
	}
	public void setColItems(List<ReviewItemPojo> colItems) {
		this.colItems = colItems;
	}
	public ResultPojo getParent() {
		return parent;
	}
	public void setParent(ResultPojo parent) {
		this.parent = parent;
	}
	public List<ResultPojo> getChildren() {
		return children;
	}
	public void setChildren(List<ResultPojo> children) {
		this.children = children;
	}
	
	public void addChild(ResultPojo child) {
		if(this.children == null){
			this.children = new ArrayList<ResultPojo>();
		}
		children.add(child);
	}
	
	public void addSupItem(ReviewItemPojo item){
		if(this.supItems == null){
			this.supItems = new ArrayList<ReviewItemPojo>();
		}
		supItems.add(item);
	}
	
	public void addColItem(ReviewItemPojo item){
		if(this.colItems == null){
			this.colItems = new ArrayList<ReviewItemPojo>();
		}
		colItems.add(item);
	}
}
