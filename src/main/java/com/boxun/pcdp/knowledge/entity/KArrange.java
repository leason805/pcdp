package com.boxun.pcdp.knowledge.entity;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.boxun.estms.entity.Const;
import com.boxun.pcdp.admin.entity.TUser;

@Entity
@Table(name = "KN_ARRANGE")
public class KArrange implements Serializable{

	private static final long serialVersionUID = 6178303284483189811L;

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false, insertable = true, updatable = true)
	private Long id;
	
	//@Temporal(TemporalType.DATE)
	@Column(name = "EXAM_DATE")
	private Date examDate;
	
	@Column(name = "EXAM_TIME")
	private String examTime;
	
	@Column(name = "ADDRESS")
	private String address;
	
	@ManyToOne//(fetch = FetchType.LAZY)
	@JoinColumn(name = "INVIG_ID")
	private TUser invigilator ;
	
	@ManyToOne//(fetch = FetchType.LAZY)
	@JoinColumn(name = "PAPER_ID")
	private KPaper paper;
	
	@Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
	private Const.ArrangeStatus status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getExamDate() {
		return examDate;
	}

	public void setExamDate(Date examDate) {
		this.examDate = examDate;
	}

	public String getExamTime() {
		return examTime;
	}

	public void setExamTime(String examTime) {
		this.examTime = examTime;
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

	public Const.ArrangeStatus getStatus() {
		return status;
	}

	public void setStatus(Const.ArrangeStatus status) {
		this.status = status;
	}

	public KPaper getPaper() {
		return paper;
	}

	public void setPaper(KPaper paper) {
		this.paper = paper;
	}
}
