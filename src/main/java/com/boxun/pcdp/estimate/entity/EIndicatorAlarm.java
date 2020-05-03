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
@Table(name = "ES_INDICATOR_ALARM")
public class EIndicatorAlarm implements Serializable{

	private static final long serialVersionUID = 6178303284483189811L;

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false, insertable = true, updatable = true)
	private Long id;
	
	@Column(name = "ALARM", precision=12, scale=3)
	private Double alarm;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "INDICATOR_ID")
	private EIndicator indicator;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CATEGORY_ID")
	private EIndicatorCategory category;
	
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

	public Double getAlarm() {
		return alarm;
	}

	public void setAlarm(Double alarm) {
		this.alarm = alarm;
	}

	public EIndicatorCategory getCategory() {
		return category;
	}

	public void setCategory(EIndicatorCategory category) {
		this.category = category;
	}
}
