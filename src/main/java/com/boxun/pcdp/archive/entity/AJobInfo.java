package com.boxun.pcdp.archive.entity;

import java.io.Serializable;
import java.util.Date;

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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.boxun.estms.entity.Const;

@Entity
@Table(name = "AR_USER_JOB_INFO")
public class AJobInfo implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false, insertable = true, updatable = true)
	private Long id;
	
	@OneToOne//(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_INFO_ID")
	private AUserInfo userInfo;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "START_DATE")
	private Date startDate;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "JOIN_DATE")
	private Date joinDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TECH_ID")
	private APositionCategory techLevel;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "POSITION_ID")
	private APositionCategory positionLevel;
	
	@Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
	private Const.CertificationStatus status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public AUserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(AUserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	public APositionCategory getTechLevel() {
		return techLevel;
	}

	public void setTechLevel(APositionCategory techLevel) {
		this.techLevel = techLevel;
	}

	public APositionCategory getPositionLevel() {
		return positionLevel;
	}

	public void setPositionLevel(APositionCategory positionLevel) {
		this.positionLevel = positionLevel;
	}

	public Const.CertificationStatus getStatus() {
		return status;
	}

	public void setStatus(Const.CertificationStatus status) {
		this.status = status;
	}

}
