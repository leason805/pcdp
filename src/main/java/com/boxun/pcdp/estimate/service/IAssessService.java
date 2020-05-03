package com.boxun.pcdp.estimate.service;

import java.util.List;

import com.boxun.pcdp.estimate.entity.EAssess;
import com.boxun.pcdp.estimate.entity.EAssessDetail;
import com.boxun.pcdp.estimate.pojo.AssessPojo;

public interface IAssessService {

	public void create(EAssess entity);
	
	public void update(EAssess entity);
	
	public void delete(EAssess entity);
	
	public EAssess load(Long id);
	
	public List<EAssess> list();
	
	public List<EAssess> list(Long projectId);
	
	public List<AssessPojo> assessList(Long userId, Long categoryId, Long assessId);
	
	public List<AssessPojo> assessList(List<EAssessDetail> details, Long categoryId);
	
	public Double cauculate(Long assessId);
}
