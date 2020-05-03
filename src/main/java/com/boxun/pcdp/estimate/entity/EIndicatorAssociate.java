package com.boxun.pcdp.estimate.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.boxun.estms.entity.Const;

@Entity
@Table(name = "ES_INDICATOR_ASSO")
public class EIndicatorAssociate implements Serializable{

	private static final long serialVersionUID = 6178303284483189811L;

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false, insertable = true, updatable = true)
	private Long id;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "INDICATOR_ID")
	private EIndicator indicator;
	
	@Enumerated(EnumType.STRING)
    @Column(name = "ASSOCIATE_TYPE")
	private Const.AssociateType associateType;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "START_DATE")
	private Date startDate;

	@Temporal(TemporalType.DATE)
	@Column(name = "END_DATE")
	private Date endDate;
	
	@OneToMany(fetch = FetchType.LAZY, cascade={ CascadeType.ALL })
	@JoinColumn(name = "ASSOCIATE_ID")
	private List<EAssociateItem> items;
	
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

	public Const.AssociateType getAssociateType() {
		return associateType;
	}

	public void setAssociateType(Const.AssociateType associateType) {
		this.associateType = associateType;
	}

	public List<EAssociateItem> getItems() {
		return items;
	}

	public void setItems(List<EAssociateItem> items) {
		this.items = items;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
}
