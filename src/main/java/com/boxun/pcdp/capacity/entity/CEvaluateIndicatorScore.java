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

import com.boxun.pcdp.estimate.entity.EIndicator;

@Entity
@Table(name = "CA_INDICATOR_SCORE")
public class CEvaluateIndicatorScore implements Serializable{

	private static final long serialVersionUID = 6178303284483189811L;

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false, insertable = true, updatable = true)
	private Long id;

	@Column(name = "SCORE")
	private Integer score;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "INDICATOR_ID")
	private EIndicator indicator;
	
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

	public EIndicator getIndicator() {
		return indicator;
	}

	public void setIndicator(EIndicator indicator) {
		this.indicator = indicator;
	}

	public CEvaluateArrangeUser getArrangeUser() {
		return arrangeUser;
	}

	public void setArrangeUser(CEvaluateArrangeUser arrangeUser) {
		this.arrangeUser = arrangeUser;
	}
}
