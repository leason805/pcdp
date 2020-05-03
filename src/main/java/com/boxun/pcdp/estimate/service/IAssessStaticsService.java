package com.boxun.pcdp.estimate.service;

import java.util.List;

import com.boxun.pcdp.estimate.entity.EAssess;
import com.boxun.pcdp.estimate.entity.EAssessStatics;
import com.boxun.pcdp.estimate.pojo.AssessStaticsSetPojo;

public interface IAssessStaticsService {

	public void create(EAssessStatics entity);
	
	public void update(EAssessStatics entity);
	
	public void delete(EAssessStatics entity);
	
	public EAssessStatics load(Long id);
	
	public List<EAssessStatics> list();
	
	public List<EAssessStatics> list(Long assessId, List<Long> indicators);
	
	public Boolean calcResult(EAssess assess);
	
	public AssessStaticsSetPojo assessList(EAssess assess);
}
