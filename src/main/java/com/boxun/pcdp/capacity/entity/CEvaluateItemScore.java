package com.boxun.pcdp.capacity.entity;

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

import com.boxun.pcdp.estimate.entity.EIndicatorItem;

@Entity
@Table(name = "CA_ITEM_SCORE")
public class CEvaluateItemScore implements Serializable{

	private static final long serialVersionUID = 6178303284483189811L;

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false, insertable = true, updatable = true)
	private Long id;

	@Column(name = "SCORE")
	private Integer score;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ITEM_ID")
	private EIndicatorItem item;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ARRANGE_USER_ID")
	private CEvaluateArrangeUser arrangeUser;
	
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

	public EIndicatorItem getItem() {
		return item;
	}

	public void setItem(EIndicatorItem item) {
		this.item = item;
	}

	public CEvaluateArrangeUser getArrangeUser() {
		return arrangeUser;
	}

	public void setArrangeUser(CEvaluateArrangeUser arrangeUser) {
		this.arrangeUser = arrangeUser;
	}
}
