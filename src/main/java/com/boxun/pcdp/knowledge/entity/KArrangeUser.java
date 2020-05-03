package com.boxun.pcdp.knowledge.entity;

import java.io.Serializable;
import java.util.Date;

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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.boxun.estms.entity.Const;
import com.boxun.pcdp.admin.entity.TUser;
import com.boxun.pcdp.archive.entity.AJobInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "KN_ARRANGE_USER")
@JsonIgnoreProperties(value={"user"})
public class KArrangeUser implements Serializable{

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
	private KArrange arrange;
	
	@Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
	private Const.ArrangeUserStatus status;
	
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
	
	@OneToOne(cascade=CascadeType.ALL, mappedBy = "arrangeUser")
    @JoinColumn(name="ARRANGE_USER_ID")
	private KExamScore score;
	
	
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

	public KArrange getArrange() {
		return arrange;
	}

	public void setArrange(KArrange arrange) {
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

	public String getVcode() {
		return vcode;
	}

	public void setVcode(String vcode) {
		this.vcode = vcode;
	}

	public String getBarcodeImage() {
		return barcodeImage;
	}

	public void setBarcodeImage(String barcodeImage) {
		this.barcodeImage = barcodeImage;
	}

	public String getUcode() {
		return ucode;
	}

	public void setUcode(String ucode) {
		this.ucode = ucode;
	}

	public KExamScore getScore() {
		return score;
	}

	public void setScore(KExamScore score) {
		this.score = score;
	}

	public Date getCompleteTime() {
		return completeTime;
	}

	public void setCompleteTime(Date completeTime) {
		this.completeTime = completeTime;
	}
}
