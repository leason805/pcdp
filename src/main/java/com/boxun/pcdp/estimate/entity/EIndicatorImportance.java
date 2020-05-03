package com.boxun.pcdp.estimate.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

@Entity
@Table(name = "ES_INDICATOR_IMPO")
public class EIndicatorImportance implements Serializable{

	private static final long serialVersionUID = 6178303284483189811L;

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false, insertable = true, updatable = true)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CATEGORY_ID")
	private EIndicatorCategory category;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PARENT_ID")
	private EIndicator parent;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FIRST_ID")
	private EIndicator first;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SECOND_ID")
	private EIndicator second;
	
	@Column(name = "RATING")
	private Double rating;
	
	@Column(name = "ORDER_NO")
	private Integer order;
	
	@OneToMany(fetch = FetchType.LAZY, cascade={ CascadeType.PERSIST })
	@JoinColumn(name = "IMPORTANCE_ID")
	@OrderBy("user asc")
	private List<EIndicatorImportanceItem> items;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public EIndicator getFirst() {
		return first;
	}

	public void setFirst(EIndicator first) {
		this.first = first;
	}

	public EIndicator getSecond() {
		return second;
	}

	public void setSecond(EIndicator second) {
		this.second = second;
	}

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}

	public EIndicator getParent() {
		return parent;
	}

	public void setParent(EIndicator parent) {
		this.parent = parent;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public List<EIndicatorImportanceItem> getItems() {
		return items;
	}

	public void setItems(List<EIndicatorImportanceItem> items) {
		this.items = items;
	}

	public EIndicatorCategory getCategory() {
		return category;
	}

	public void setCategory(EIndicatorCategory category) {
		this.category = category;
	}
}
