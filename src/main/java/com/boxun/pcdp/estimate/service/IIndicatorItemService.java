package com.boxun.pcdp.estimate.service;

import java.util.List;

import com.boxun.pcdp.estimate.entity.EIndicatorItem;

public interface IIndicatorItemService {

	public void create(EIndicatorItem entity);
	
	public void update(EIndicatorItem entity);
	
	public void delete(EIndicatorItem entity);
	
	public EIndicatorItem load(Long id);
	
	public List<EIndicatorItem> list();
	
	public List<EIndicatorItem> list4Indicator(Long indicatorId);
}
