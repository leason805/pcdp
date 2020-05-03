package com.boxun.pcdp.capacity.entity;

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

import com.boxun.estms.entity.Const;
import com.boxun.pcdp.admin.entity.TUser;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "CA_ARRANGE_USER")
@JsonIgnoreProperties(value={"user"})
public class CEvaluateArrangeUser implements Serializable{

	private static final long serialVersionUID = 6178303284483189811L;

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false, insertable = true, updatable = true)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID")
	private TUser user;
	
	@ManyToOne//(fetch = FetchType.LAZY)
	@JoinColumn(name = "ARRANGE_ID")
	private CEvaluateArrange arrange;
	
	@Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
	private Const.ArrangeUserStatus status;
	
	@Enumerated(EnumType.STRING)
    @Column(name = "CHECK_STATUS")
	private Const.ArrangeUserStatus checkStatus;
	
	@Column(name = "SIGN_TIME")
	private Date signTime;
	
	@Column(name = "COMPLETE_TIME")
	private Date completeTime;
	
	@Column(name = "VCODE")
	private String vcode;
	
	@Column(name = "UCODE")	//UNIQUE ID
	private String ucode;
	
	@Column(name = "BARCODE_IMAGEA")
	private String barcodeImage;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TUser getUser() {
		return user;
	}

	public void setUser(TUser user) {
		this.user = user;
	}

	public CEvaluateArrange getArrange() {
		return arrange;
	}

	public void setArrange(CEvaluateArrange arrange) {
		this.arrange = arrange;
	}

	public Const.ArrangeUserStatus getStatus() {
		return status;
	}

	public void setStatus(Const.ArrangeUserStatus status) {
		this.status = status;
	}

	public Date getSignTime() {
		return signTime;
	}

	public void setSignTime(Date signTime) {
		this.signTime = signTime;
	}

	public Date getCompleteTime() {
		return completeTime;
	}

	public void setCompleteTime(Date completeTime) {
		this.completeTime = completeTime;
	}

	public String getVcode() {
		return vcode;
	}

	public void setVcode(String vcode) {
		this.vcode = vcode;
	}

	public String getUcode() {
		return ucode;
	}

	public void setUcode(String ucode) {
		this.ucode = ucode;
	}

	public String getBarcodeImage() {
		return barcodeImage;
	}

	public void setBarcodeImage(String barcodeImage) {
		this.barcodeImage = barcodeImage;
	}

	public Const.ArrangeUserStatus getCheckStatus() {
		return checkStatus;
	}

	public void setCheckStatus(Const.ArrangeUserStatus checkStatus) {
		this.checkStatus = checkStatus;
	}
}
