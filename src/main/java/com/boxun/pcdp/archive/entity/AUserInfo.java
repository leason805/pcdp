package com.boxun.pcdp.archive.entity;

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
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.boxun.estms.entity.Const;
import com.boxun.pcdp.admin.entity.TUser;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "AR_USER_INFO")
@JsonIgnoreProperties(value={"eduInfos"})
public class AUserInfo implements Serializable{

	private static final long serialVersionUID = 6178303284483189811L;

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false, insertable = true, updatable = true)
	private Long id;

	@Column(name = "TELEPHONE")
	private String telephone;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "BIRTHDAY")
	private Date birthday;
	
	@Enumerated(EnumType.STRING)
    @Column(name = "GENDER")
	private Const.Gender gender;
	
	@Column(name = "NATIVE_PLACE")
	private String nativePlace;
	
	@Column(name = "POLITICAL")
	private String political; //政治面貌
	
	@Column(name = "ID_CARD")
	private String idCard;
	
	@Column(name = "ADDRESS")
	private String address;
	
	@ManyToOne//(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID")
	private TUser user;
	
//	@OneToOne(cascade=CascadeType.ALL, mappedBy = "userInfo")
//	private AEducationInfo eduInfo;
	
	@OneToOne(cascade=CascadeType.ALL, mappedBy = "userInfo")
    @JoinColumn(name="USER_INFO_ID")
	private AJobInfo jobInfo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DEPAREMENT_ID")
	private APositionCategory deparment;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "POSITION_ID")
	private APositionCategory position;
	
	@OneToMany(fetch = FetchType.LAZY, cascade={ CascadeType.ALL })
	@JoinColumn(name = "USER_INFO_ID")
	@OrderBy(value = "startDate DESC")
	private List<AEducationInfo> eduInfos;
	
	@Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
	private Const.CertificationStatus status;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Const.Gender getGender() {
		return gender;
	}

	public void setGender(Const.Gender gender) {
		this.gender = gender;
	}

	public String getNativePlace() {
		return nativePlace;
	}

	public void setNativePlace(String nativePlace) {
		this.nativePlace = nativePlace;
	}

	public String getPolitical() {
		return political;
	}

	public void setPolitical(String political) {
		this.political = political;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public TUser getUser() {
		return user;
	}

	public void setUser(TUser user) {
		this.user = user;
	}

//	public AEducationInfo getEduInfo() {
//		return eduInfo;
//	}
//
//	public void setEduInfo(AEducationInfo eduInfo) {
//		this.eduInfo = eduInfo;
//	}

	public AJobInfo getJobInfo() {
		return jobInfo;
	}

	public void setJobInfo(AJobInfo jobInfo) {
		this.jobInfo = jobInfo;
	}

	public APositionCategory getDeparment() {
		return deparment;
	}

	public void setDeparment(APositionCategory deparment) {
		this.deparment = deparment;
	}

	public APositionCategory getPosition() {
		return position;
	}

	public void setPosition(APositionCategory position) {
		this.position = position;
	}

	public List<AEducationInfo> getEduInfos() {
		return eduInfos;
	}

	public void setEduInfos(List<AEducationInfo> eduInfos) {
		this.eduInfos = eduInfos;
	}

	public Const.CertificationStatus getStatus() {
		return status;
	}

	public void setStatus(Const.CertificationStatus status) {
		this.status = status;
	}
}
