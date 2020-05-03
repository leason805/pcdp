package com.boxun.pcdp.estimate.service;

import java.util.List;

import com.boxun.estms.entity.Const;
import com.boxun.pcdp.estimate.entity.EIndicator;

public interface IIndicatorService {

	public void create(EIndicator entity);
	
	public void update(EIndicator entity);
	
	public void delete(EIndicator entity);
	
	public EIndicator load(Long id);
	
	public List<EIndicator> list();
	
	public List<EIndicator> listByCategory(Long categoryId);
	
	public List<Long> listIds(List<EIndicator> indicators);
	
	public List<EIndicator> listTops();
	
	public List<EIndicator> listTops4Category(Long categoryId);
	
	public List<EIndicator> listTops4Category(Long categoryId, Const.IndicatorType type);
	
	public void updateScore();
	
}
