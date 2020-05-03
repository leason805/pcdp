package com.boxun.pcdp.estimate.entity;

import java.io.Serializable;
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
@Table(name = "ES_ASSESS")
@JsonIgnoreProperties(value={"assessTime", "project", "items", "statics"})
public class EAssess implements Serializable{

	private static final long serialVersionUID = 6178303284483189811L;

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false, insertable = true, updatable = true)
	private Long id;

	@ManyToOne//(fetch = FetchType.LAZY)
	@JoinColumn(name = "PROJECT_ID")
	public EAssessProject project;
	
	@Column(name = "ASSESS_SCORE", precision=12, scale=3)
	private Double assessScore;
	
//	@Column(name = "ASSESS_TIME")
//	private Date assessTime;
	
//	@Enumerated(EnumType.STRING)
//    @Column(name = "ASSIGN_STATUS")
//	private Const.AssignStatus assignStatus;
	
	@Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
	private Const.AssessStatus status;
	
	@ManyToOne//(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID")
	private TUser user;
	
	@Column(name = "SUMMARY")
	private String summary;
	
	@Column(name = "REMARK")
	private String remark;
	
	@OneToMany(fetch = FetchType.LAZY, cascade={ CascadeType.ALL })
    @JoinColumn(name="ASSESS_ID")
	private List<EAssessStatics> statics;
	
	@OneToMany(fetch = FetchType.LAZY, cascade={ CascadeType.ALL })
	@JoinColumn(name = "ASSESS_ID")
	private List<EAssessItem> items;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

//	public Date getAssessTime() {
//		return assessTime;
//	}
//
//	public void setAssessTime(Date assessTime) {
//		this.assessTime = assessTime;
//	}

	public TUser getUser() {
		return user;
	}

	public void setUser(TUser user) {
		this.user = user;
	}

	public EAssessProject getProject() {
		return project;
	}

	public void setProject(EAssessProject project) {
		this.project = project;
	}

	public Double getAssessScore() {
		return assessScore;
	}

	public void setAssessScore(Double assessScore) {
		this.assessScore = assessScore;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<EAssessItem> getItems() {
		return items;
	}

	public void setItems(List<EAssessItem> items) {
		this.items = items;
	}
//
//	public Const.AssignStatus getAssignStatus() {
//		return assignStatus;
//	}
//
//	public void setAssignStatus(Const.AssignStatus assignStatus) {
//		this.assignStatus = assignStatus;
//	}

	public List<EAssessStatics> getStatics() {
		return statics;
	}

	public void setStatics(List<EAssessStatics> statics) {
		this.statics = statics;
	}

	public Const.AssessStatus getStatus() {
		return status;
	}

	public void setStatus(Const.AssessStatus status) {
		this.status = status;
	}

}
