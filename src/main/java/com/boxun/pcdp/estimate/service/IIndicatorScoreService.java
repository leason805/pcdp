package com.boxun.pcdp.estimate.service;

import java.util.List;

import com.boxun.pcdp.estimate.entity.EIndicatorScoreItem;

public interface IIndicatorScoreService {

	public void create(EIndicatorScoreItem entity);
	
	public void update(EIndicatorScoreItem entity);
	
	public void delete(EIndicatorScoreItem entity);
	
	public EIndicatorScoreItem load(Long id);
	
	public List<EIndicatorScoreItem> list();
	
	public List<EIndicatorScoreItem> listOrderByUser();
	
	public List<EIndicatorScoreItem> listOrderByUser(List<Long> indicatorIds);
	
	public List<EIndicatorScoreItem> list(Long userId);
	
	public List<EIndicatorScoreItem> listByIndicators(List<Long> ids);
}
