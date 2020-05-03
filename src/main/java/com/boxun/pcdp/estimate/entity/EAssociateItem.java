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
@Table(name = "ES_INDICATOR_ASSO_ITEM")
public class EAssociateItem implements Serializable{

	private static final long serialVersionUID = 5487445686356261053L;
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false, insertable = true, updatable = true)
	private Long id;
	
	@Column(name = "ITEM_ID")
	private Long itemId;
	
	@Column(name = "FORMULA")
	private String formula;
	
	@Column(name = "BENCHMARK")
	private Double benchmark;
	
	@Column(name = "SCORE")
	private Integer score;
	
	@Column(name = "VAL_1")
	private String val1;
	
	@Column(name = "VAL_2")
	private String val2;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ASSOCIATE_ID")
	private EIndicatorAssociate associate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public String getFormula() {
		return formula;
	}

	public void setFormula(String formula) {
		this.formula = formula;
	}

	public Double getBenchmark() {
		return benchmark;
	}

	public void setBenchmark(Double benchmark) {
		this.benchmark = benchmark;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public EIndicatorAssociate getAssociate() {
		return associate;
	}

	public void setAssociate(EIndicatorAssociate associate) {
		this.associate = associate;
	}

	public String getVal1() {
		return val1;
	}

	public void setVal1(String val1) {
		this.val1 = val1;
	}

	public String getVal2() {
		return val2;
	}

	public void setVal2(String val2) {
		this.val2 = val2;
	}
}
