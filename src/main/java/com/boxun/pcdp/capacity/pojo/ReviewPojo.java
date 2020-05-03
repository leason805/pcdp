package com.boxun.pcdp.capacity.pojo;

import java.util.ArrayList;
import java.util.List;

import com.boxun.estms.entity.Const;

public class ReviewPojo {

	private Long id;
	private String sequence;
	private String name;
	private Double result;
	private Integer score;
	private List<ReviewItemPojo> items;
	private Const.ReviewType type;
	
	private ReviewPojo parent;
	private List<ReviewPojo> children;
	
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
	public Double getResult() {
		return result;
	}
	public void setResult(Double result) {
		this.result = result;
	}
	public List<ReviewItemPojo> getItems() {
		return items;
	}
	public void setItems(List<ReviewItemPojo> items) {
		this.items = items;
	}
	public Const.ReviewType getType() {
		return type;
	}
	public void setType(Const.ReviewType type) {
		this.type = type;
	}
	public ReviewPojo getParent() {
		return parent;
	}
	public void setParent(ReviewPojo parent) {
		this.parent = parent;
	}
	public List<ReviewPojo> getChildren() {
		return children;
	}
	public void setChildren(List<ReviewPojo> children) {
		this.children = children;
	}
	public void addChild(ReviewPojo child) {
		if(this.children == null){
			this.children = new ArrayList<ReviewPojo>();
		}
		children.add(child);
	}
	
	public void addItem(ReviewItemPojo item){
		if(this.items == null){
			this.items = new ArrayList<ReviewItemPojo>();
		}
		items.add(item);
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
}
