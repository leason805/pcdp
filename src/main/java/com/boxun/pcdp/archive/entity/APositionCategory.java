package com.boxun.pcdp.archive.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.boxun.estms.entity.Const;

@Entity
@Table(name = "AR_POSIT_CATEGORY")
public class APositionCategory implements Serializable{

	private static final long serialVersionUID = 6178303284483189811L;

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false, insertable = true, updatable = true)
	private Long id;

	@Column(name = "NAME")
	private String name;
	
	@Column(name = "ORDER_NO")
	private Integer order;
	
	@Column(name = "DESCRIPTION")
	private String description;
	
	@Enumerated(EnumType.STRING)
    @Column(name = "CATEGORY_TYPE")
	private Const.PositionCategoryType categoryType;
	
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

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Const.PositionCategoryType getCategoryType() {
		return categoryType;
	}

	public void setCategoryType(Const.PositionCategoryType categoryType) {
		this.categoryType = categoryType;
	}
}
