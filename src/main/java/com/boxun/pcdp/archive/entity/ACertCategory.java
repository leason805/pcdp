package com.boxun.pcdp.archive.entity;

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
@Table(name = "AR_CERT_CATEGORY")
public class ACertCategory implements Serializable{

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
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PARENT_ID")
	private ACertCategory parent;
	
	@OneToMany(fetch = FetchType.LAZY, cascade=(CascadeType.ALL))
	@JoinColumn(name = "PARENT_ID")
	@OrderBy("order asc")
	private List<ACertCategory> children;

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

	public ACertCategory getParent() {
		return parent;
	}

	public void setParent(ACertCategory parent) {
		this.parent = parent;
	}

	public List<ACertCategory> getChildren() {
		return children;
	}

	public void setChildren(List<ACertCategory> children) {
		this.children = children;
	}
}
