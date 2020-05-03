package com.boxun.pcdp.estimate.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.boxun.pcdp.admin.entity.TUser;

@Entity
@Table(name = "ES_INDICATOR_IMPO_ITEM")
public class EIndicatorImportanceItem implements Serializable{

	private static final long serialVersionUID = -5506443089147687561L;

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false, insertable = true, updatable = true)
	private Long id;
	
	@Column(name = "RATING")
	private Double rating;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IMPORTANCE_ID")
	private EIndicatorImportance importance;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID")
	private TUser user ;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public EIndicatorImportance getImportance() {
		return importance;
	}

	public void setImportance(EIndicatorImportance importance) {
		this.importance = importance;
	}

	public TUser getUser() {
		return user;
	}

	public void setUser(TUser user) {
		this.user = user;
	}

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}
}
