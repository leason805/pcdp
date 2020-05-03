package com.boxun.pcdp.performance.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.boxun.estms.entity.Const;
import com.boxun.pcdp.admin.entity.TUser;
import com.boxun.pcdp.estimate.entity.EAssess;

@Entity
@Table(name = "PF_ARRANGE")
public class PArrange implements Serializable{

	private static final long serialVersionUID = 6178303284483189811L;

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false, insertable = true, updatable = true)
	private Long id;

	@ManyToOne//(fetch = FetchType.LAZY)
	@JoinColumn(name = "ASSESS_ID")
	public EAssess assess;
	
	@ManyToOne//(fetch = FetchType.LAZY)
	@JoinColumn(name = "SUP_USER_ID")
	private TUser supAssessor ;
	
	@ManyToOne//(fetch = FetchType.LAZY)
	@JoinColumn(name = "COL_USER_ID")
	private TUser colAssessor;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "STATUS")
	private Const.AssessStatus status;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "SUP_STATUS")
	private Const.AssessStatus supStatus;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "COL_STATUS")
	private Const.AssessStatus colStatus;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public EAssess getAssess() {
		return assess;
	}

	public void setAssess(EAssess assess) {
		this.assess = assess;
	}

	public TUser getSupAssessor() {
		return supAssessor;
	}

	public void setSupAssessor(TUser supAssessor) {
		this.supAssessor = supAssessor;
	}

	public TUser getColAssessor() {
		return colAssessor;
	}

	public void setColAssessor(TUser colAssessor) {
		this.colAssessor = colAssessor;
	}

	public Const.AssessStatus getStatus() {
		return status;
	}

	public void setStatus(Const.AssessStatus status) {
		this.status = status;
	}

	public Const.AssessStatus getSupStatus() {
		return supStatus;
	}

	public void setSupStatus(Const.AssessStatus supStatus) {
		this.supStatus = supStatus;
	}

	public Const.AssessStatus getColStatus() {
		return colStatus;
	}

	public void setColStatus(Const.AssessStatus colStatus) {
		this.colStatus = colStatus;
	}
}
