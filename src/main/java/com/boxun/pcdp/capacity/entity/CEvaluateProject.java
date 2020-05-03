package com.boxun.pcdp.capacity.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.boxun.pcdp.estimate.entity.EIndicatorCategory;

@Entity
@Table(name = "CA_PROJECT")
public class CEvaluateProject implements Serializable{

	private static final long serialVersionUID = 6178303284483189811L;

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false, insertable = true, updatable = true)
	private Long id;

	@Column(name = "name")
	private String name;
	
	@Column(name = "DESCRIPTION")
	private String description;
	
	@ManyToOne//(fetch = FetchType.LAZY)
	@JoinColumn(name = "CATEGORY_ID")
	private EIndicatorCategory category;
	
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

	public EIndicatorCategory getCategory() {
		return category;
	}

	public void setCategory(EIndicatorCategory category) {
		this.category = category;
	}
}
