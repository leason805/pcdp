package com.boxun.pcdp.training.entity;

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
@Table(name = "TR_ARRANGE")
//@JsonIgnoreProperties(value={"arrangeUsers"})
public class TArrange implements Serializable{

	private static final long serialVersionUID = 6178303284483189811L;

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false, insertable = true, updatable = true)
	private Long id;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "COURSE_DATE")
	private Date courseDate;
	
	@Column(name = "COURSE_TIME")
	private String courseTime;
	
	@Column(name = "ADDRESS")
	private String address;
	
	@ManyToOne//(fetch = FetchType.LAZY)
	@JoinColumn(name = "COACHER_ID")
	private TUser coacher;
	
	@ManyToOne//(fetch = FetchType.LAZY)
	@JoinColumn(name = "COURSE_ID")
	private TCourse course;
	
	@Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
	private Const.ArrangeStatus status;
	
	//@OneToMany(fetch = FetchType.LAZY, cascade={ CascadeType.ALL })
	//@JoinColumn(name = "ARRANGE_ID")
	//private List<TArrangeUser> arrangeUsers;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCourseDate() {
		return courseDate;
	}

	public void setCourseDate(Date courseDate) {
		this.courseDate = courseDate;
	}

	public String getCourseTime() {
		return courseTime;
	}

	public void setCourseTime(String courseTime) {
		this.courseTime = courseTime;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public TCourse getCourse() {
		return course;
	}

	public void setCourse(TCourse course) {
		this.course = course;
	}

	public Const.ArrangeStatus getStatus() {
		return status;
	}

	public void setStatus(Const.ArrangeStatus status) {
		this.status = status;
	}

	public TUser getCoacher() {
		return coacher;
	}

	public void setCoacher(TUser coacher) {
		this.coacher = coacher;
	}

//	public List<TArrangeUser> getArrangeUsers() {
//		return arrangeUsers;
//	}
//
//	public void setArrangeUsers(List<TArrangeUser> arrangeUsers) {
//		this.arrangeUsers = arrangeUsers;
//	}

}
