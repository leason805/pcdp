package com.boxun.pcdp.estimate.pojo;

import java.io.Serializable;

public class IndicatorAssociateItemPojo implements Serializable{

	private static final long serialVersionUID = -4321129616486219512L;
	private Long id;
	private Long itemId;
	private String formula1;
	private String formula2;
	private String formula3;
	private String formula4;
	
	private String benchmark1;
	private String benchmark2;
	private String benchmark3;
	private String benchmark4;
	
	private Integer score1;
	private Integer score2;
	private Integer score3;
	private Integer score4;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getItemId() {
		return itemId;
	}
	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}
	public String getFormula1() {
		return formula1;
	}
	public void setFormula1(String formula1) {
		this.formula1 = formula1;
	}
	public String getFormula2() {
		return formula2;
	}
	public void setFormula2(String formula2) {
		this.formula2 = formula2;
	}
	public String getFormula3() {
		return formula3;
	}
	public void setFormula3(String formula3) {
		this.formula3 = formula3;
	}
	public String getFormula4() {
		return formula4;
	}
	public void setFormula4(String formula4) {
		this.formula4 = formula4;
	}
	public String getBenchmark1() {
		return benchmark1;
	}
	public void setBenchmark1(String benchmark1) {
		this.benchmark1 = benchmark1;
	}
	public String getBenchmark2() {
		return benchmark2;
	}
	public void setBenchmark2(String benchmark2) {
		this.benchmark2 = benchmark2;
	}
	public String getBenchmark3() {
		return benchmark3;
	}
	public void setBenchmark3(String benchmark3) {
		this.benchmark3 = benchmark3;
	}
	public String getBenchmark4() {
		return benchmark4;
	}
	public void setBenchmark4(String benchmark4) {
		this.benchmark4 = benchmark4;
	}
	public Integer getScore1() {
		return score1;
	}
	public void setScore1(Integer score1) {
		this.score1 = score1;
	}
	public Integer getScore2() {
		return score2;
	}
	public void setScore2(Integer score2) {
		this.score2 = score2;
	}
	public Integer getScore3() {
		return score3;
	}
	public void setScore3(Integer score3) {
		this.score3 = score3;
	}
	public Integer getScore4() {
		return score4;
	}
	public void setScore4(Integer score4) {
		this.score4 = score4;
	}
}
