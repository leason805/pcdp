package com.boxun.pcdp.estimate.service;

import java.util.List;

import com.boxun.pcdp.estimate.entity.EAssessDetail;

public interface IAssessDetailService {

	public void create(EAssessDetail entity);
	
	public void update(EAssessDetail entity);
	
	public void delete(EAssessDetail entity);
	
	public EAssessDetail load(Long id);
	
	public List<EAssessDetail> list();
	
	public List<EAssessDetail> list(Long assessId);
}
