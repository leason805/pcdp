package com.boxun.pcdp.admin.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.boxun.estms.entity.Const;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "BX_TUSER")
@JsonIgnoreProperties(value={"roles"})
public class TUser implements Serializable{

	private static final long serialVersionUID = 3658099847988155212L;
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false, insertable = true, updatable = true)
	private Long id;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "LOGINID")
	private String loginID;
	
	@Column(name = "PASSWORD")
	private String password;
	
	@Column(name = "TELEPHONE")
	private String telephone;
	
	@Column(name = "EMAIL")
	private String email;
	
	@Column(name = "CREATE_TIME")
	private Date createTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
	private Const.UserStatus status;
    
//    @Enumerated(EnumType.STRING)
//    @Column(name = "USER_TYPE")
//	private Const.UserType userType;

    @ManyToOne//(fetch = FetchType.LAZY) 
	@JoinColumn(name = "COMPANY_ID")
	private TCompany company;
    
    @ManyToMany
	@JoinTable(name = "BX_USER_ROLE", joinColumns = @JoinColumn(name = "USER_ID"), inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
    @Basic(fetch=FetchType.LAZY)
    private List<TRole> roles;
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLoginID() {
		return loginID;
	}

	public void setLoginID(String loginID) {
		this.loginID = loginID;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Const.UserStatus getStatus() {
		return status;
	}

	public void setStatus(Const.UserStatus status) {
		this.status = status;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public List<TRole> getRoles() {
		return roles;
	}

	public void setRoles(List<TRole> roles) {
		this.roles = roles;
	}

//	public Const.UserType getUserType() {
//		return userType;
//	}
//
//	public void setUserType(Const.UserType userType) {
//		this.userType = userType;
//	}

	public TCompany getCompany() {
		return company;
	}

	public void setCompany(TCompany company) {
		this.company = company;
	}

}
