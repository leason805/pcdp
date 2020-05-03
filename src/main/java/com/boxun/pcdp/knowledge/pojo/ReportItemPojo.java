package com.boxun.pcdp.knowledge.pojo;

import java.io.Serializable;
import java.math.BigDecimal;

public class ReportItemPojo implements Serializable{

	private static final long serialVersionUID = 2915134481703100605L;
	private String name;
	private Double rate;
	private Integer total;
	private Integer correctSize;
	private Integer incorrectSize;
	
	public ReportItemPojo() {
		super();
		this.rate = 0d;
		this.total = 0;
		this.correctSize = 0;
		this.incorrectSize = 0;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getRate() {
		return rate;
	}
	public void setRate(Double rate) {
		this.rate = rate;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public Integer getCorrectSize() {
		return correctSize;
	}
	public void setCorrectSize(Integer correctSize) {
		this.correctSize = correctSize;
	}
	public Integer getIncorrectSize() {
		return incorrectSize;
	}
	public void setIncorrectSize(Integer incorrectSize) {
		this.incorrectSize = incorrectSize;
	}
	
	public void addTotal(Integer increasement){
		this.total += increasement;
	}
	
	public void increaseTotal(){
		this.total++;
	}
	
	public void addCorrectSize(Integer increasement){
		this.correctSize += increasement;
	}
	
	public void increaseCorrectSize(){
		this.correctSize++;
	}
	
	public void addIncorrectSize(Integer increasement){
		this.incorrectSize += increasement;
	}
	
	public void increaseIncorrectSize(){
		this.incorrectSize++;
	}
	
	public void calRate(){
		if(total > 0){
			//rate = (this.correctSize*1.0)/this.total*100;
			BigDecimal b = new  BigDecimal((this.correctSize*1.0)/this.total*100);  
			rate = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();  

		}
	}
}
