package com.boxun.pcdp.capacity.entity;

import java.io.Serializable;
import java.util.Date;

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
import com.boxun.pcdp.estimate.entity.EIndicatorCategory;

@Entity
@Table(name = "CA_ARRANGE")
public class CEvaluateArrange implements Serializable{

	private static final long serialVersionUID = 6178303284483189811L;

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false, insertable = true, updatable = true)
	private Long id;

	@Column(name = "START_TIME")
	private Date startTime;
	
	@Column(name = "ADDRESS")
	private String address;
	
	@ManyToOne//(fetch = FetchType.LAZY)
	@JoinColumn(name = "INVIG_ID")
	private TUser invigilator ;
	
	@ManyToOne//(fetch = FetchType.LAZY)
	@JoinColumn(name = "ADJUD_ID")
	private TUser adjudicator;
	
	@ManyToOne//(fetch = FetchType.LAZY)
	@JoinColumn(name = "PROJECT_ID")
	private CEvaluateProject project;
	
	@Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
	private Const.ArrangeStatus status;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public TUser getInvigilator() {
		return invigilator;
	}

	public void setInvigilator(TUser invigilator) {
		this.invigilator = invigilator;
	}

	public TUser getAdjudicator() {
		return adjudicator;
	}

	public void setAdjudicator(TUser adjudicator) {
		this.adjudicator = adjudicator;
	}

	public CEvaluateProject getProject() {
		return project;
	}

	public void setProject(CEvaluateProject project) {
		this.project = project;
	}

	public Const.ArrangeStatus getStatus() {
		return status;
	}

	public void setStatus(Const.ArrangeStatus status) {
		this.status = status;
	}
}
