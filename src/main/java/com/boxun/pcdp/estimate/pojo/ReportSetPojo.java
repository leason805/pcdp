package com.boxun.pcdp.estimate.pojo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ReportSetPojo {

	private String title;
	private List<ReportPojo> reports;
	private List<String> labels;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<ReportPojo> getReports() {
		return reports;
	}
	public void setReports(List<ReportPojo> reports) {
		this.reports = reports;
	}
	public void addReports(Collection<ReportPojo> reports){
		if(this.reports == null){
			this.reports = new ArrayList<ReportPojo>();
		}
		this.reports.addAll(reports);
	}
	
	public void addReport(ReportPojo report){
		if(this.reports == null){
			this.reports = new ArrayList<ReportPojo>();
		}
		this.reports.add(report);
	}
	public List<String> getLabels() {
		return labels;
	}
	public void setLabels(List<String> labels) {
		this.labels = labels;
	}
	public void addLable(String label){
		if(this.labels == null){
			this.labels = new ArrayList<String>();
		}
		this.labels.add(label);
	}
}
