package com.boxun.pcdp.estimate.service;

import java.util.List;

import com.boxun.pcdp.estimate.entity.EIndicatorAssociate;

public interface IIndicatorAssociateService {

	public void create(EIndicatorAssociate entity);
	
	public void update(EIndicatorAssociate entity);
	
	public void delete(EIndicatorAssociate entity);
	
	public EIndicatorAssociate load(Long id);
	
	public List<EIndicatorAssociate> list();
}
