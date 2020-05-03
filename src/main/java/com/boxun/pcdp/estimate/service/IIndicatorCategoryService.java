package com.boxun.pcdp.estimate.service;

import java.util.List;

import com.boxun.pcdp.estimate.entity.EIndicatorCategory;

public interface IIndicatorCategoryService {

	public void create(EIndicatorCategory entity);
	
	public void update(EIndicatorCategory entity);
	
	public void delete(EIndicatorCategory entity);
	
	public EIndicatorCategory load(Long id);
	
	public List<EIndicatorCategory> listActive();
}
