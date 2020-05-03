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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.boxun.estms.entity.Const;
import com.boxun.pcdp.admin.entity.TUser;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "ES_ASSESS_ITEM")
@JsonIgnoreProperties(value={"owner", "details"})
public class EAssessItem implements Serializable{

	private static final long serialVersionUID = 6178303284483189811L;

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false, insertable = true, updatable = true)
	private Long id;

	@Column(name = "ASSESS_SCORE", precision=12, scale=3)
	private Double assessScore;
	
	@Column(name = "ASSESS_TIME")
	private Date assessTime;
	
	@Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
	private Const.AssessStatus status;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID")
	private TUser owner;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ASSESS_ID")
	public EAssess assess;
	
	@OneToMany(fetch = FetchType.LAZY, cascade={ CascadeType.ALL })
	@JoinColumn(name = "ASSESS_ITEM_ID")
	private List<EAssessDetail> details;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getAssessScore() {
		return assessScore;
	}

	public void setAssessScore(Double assessScore) {
		this.assessScore = assessScore;
	}

	public Date getAssessTime() {
		return assessTime;
	}

	public void setAssessTime(Date assessTime) {
		this.assessTime = assessTime;
	}

	public Const.AssessStatus getStatus() {
		return status;
	}

	public void setStatus(Const.AssessStatus status) {
		this.status = status;
	}

	public TUser getOwner() {
		return owner;
	}

	public void setOwner(TUser owner) {
		this.owner = owner;
	}

	public EAssess getAssess() {
		return assess;
	}

	public void setAssess(EAssess assess) {
		this.assess = assess;
	}

	public List<EAssessDetail> getDetails() {
		return details;
	}

	public void setDetails(List<EAssessDetail> details) {
		this.details = details;
	}
}
