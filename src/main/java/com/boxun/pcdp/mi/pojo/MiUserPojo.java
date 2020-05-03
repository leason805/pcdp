package com.boxun.pcdp.mi.pojo;

import java.io.Serializable;

public class MiUserPojo implements Serializable{

	private static final long serialVersionUID = 1L;
	private String uname;
	private String email;
	private String gender;
	private String telephone;
	private String deparment;
	private String position;
	private String techLevel;
	private String positionLevel;
	
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getDeparment() {
		return deparment;
	}
	public void setDeparment(String deparment) {
		this.deparment = deparment;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getTechLevel() {
		return techLevel;
	}
	public void setTechLevel(String techLevel) {
		this.techLevel = techLevel;
	}
	public String getPositionLevel() {
		return positionLevel;
	}
	public void setPositionLevel(String positionLevel) {
		this.positionLevel = positionLevel;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
}
