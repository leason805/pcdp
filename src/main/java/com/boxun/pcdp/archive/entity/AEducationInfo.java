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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.boxun.estms.entity.Const;

@Entity
@Table(name = "AR_USER_EDU_INFO")
public class AEducationInfo implements Serializable{

	private static final long serialVersionUID = 2967693072389555978L;

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false, insertable = true, updatable = true)
	private Long id;
	
	@Column(name = "UNITERSITY")
	private String university;
	
	@Column(name = "MAJOR")
	private String major;
	
	@Column(name = "DEGREE")
	private String degree;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "START_DATE")
	private Date startDate;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "END_DATE")
	private Date endDate;
	
	@Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
	private Const.CertificationStatus status;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_INFO_ID")
	private AUserInfo userInfo;

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

	public String getUniversity() {
		return university;
	}

	public void setUniversity(String university) {
		this.university = university;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
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

	public Const.CertificationStatus getStatus() {
		return status;
	}

	public void setStatus(Const.CertificationStatus status) {
		this.status = status;
	}

}
