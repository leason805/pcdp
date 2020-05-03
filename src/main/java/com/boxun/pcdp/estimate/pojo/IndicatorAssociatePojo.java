package com.boxun.pcdp.estimate.pojo;

import java.io.Serializable;

import com.boxun.estms.entity.Const;

public class IndicatorAssociatePojo implements Serializable{

	private static final long serialVersionUID = 2024523017552201978L;
	
	private Long id;
	private Long indicator;
	private String items;
	private Const.AssociateType associateType;
	private String formula1;
	private String formula2;
	private String formula3;
	private String formula4;
	
	private String benchmark1;
	private String benchmark2;
	private String benchmark3;
	private String benchmark4;
	
	private String val1_1;
	private String val1_2;
	private String val1_3;
	private String val1_4;
	
	private String val2_1;
	private String val2_2;
	private String val2_3;
	private String val2_4;
	
	private Integer score1;
	private Integer score2;
	private Integer score3;
	private Integer score4;
	
	private String startDate;
	private String endDate;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getIndicator() {
		return indicator;
	}
	public void setIndicator(Long indicator) {
		this.indicator = indicator;
	}
	public Const.AssociateType getAssociateType() {
		return associateType;
	}
	public void setAssociateType(Const.AssociateType associateType) {
		this.associateType = associateType;
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
	public String getItems() {
		return items;
	}
	public void setItems(String items) {
		this.items = items;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getVal1_1() {
		return val1_1;
	}
	public void setVal1_1(String val1_1) {
		this.val1_1 = val1_1;
	}
	public String getVal1_2() {
		return val1_2;
	}
	public void setVal1_2(String val1_2) {
		this.val1_2 = val1_2;
	}
	public String getVal1_3() {
		return val1_3;
	}
	public void setVal1_3(String val1_3) {
		this.val1_3 = val1_3;
	}
	public String getVal1_4() {
		return val1_4;
	}
	public void setVal1_4(String val1_4) {
		this.val1_4 = val1_4;
	}
	public String getVal2_1() {
		return val2_1;
	}
	public void setVal2_1(String val2_1) {
		this.val2_1 = val2_1;
	}
	public String getVal2_2() {
		return val2_2;
	}
	public void setVal2_2(String val2_2) {
		this.val2_2 = val2_2;
	}
	public String getVal2_3() {
		return val2_3;
	}
	public void setVal2_3(String val2_3) {
		this.val2_3 = val2_3;
	}
	public String getVal2_4() {
		return val2_4;
	}
	public void setVal2_4(String val2_4) {
		this.val2_4 = val2_4;
	}
}
