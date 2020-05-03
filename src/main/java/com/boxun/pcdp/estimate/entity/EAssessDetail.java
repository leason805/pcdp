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

@Entity
@Table(name = "ES_ASSESS_DTL")
public class EAssessDetail implements Serializable{

	private static final long serialVersionUID = 6178303284483189811L;

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false, insertable = true, updatable = true)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ASSESS_ITEM_ID")
	private EAssessItem assessItem;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "INDICATOR_ID")
	private EIndicator indicator;
	
	@Column(name = "SCORE")
	private Double score;
	
	@Column(name = "CALCULATION")
	private Double calculation;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public EIndicator getIndicator() {
		return indicator;
	}

	public void setIndicator(EIndicator indicator) {
		this.indicator = indicator;
	}

	public EAssessItem getAssessItem() {
		return assessItem;
	}

	public void setAssessItem(EAssessItem assessItem) {
		this.assessItem = assessItem;
	}

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	public Double getCalculation() {
		return calculation;
	}

	public void setCalculation(Double calculation) {
		this.calculation = calculation;
	}
}
