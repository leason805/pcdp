package com.boxun.pcdp.estimate.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.boxun.pcdp.estimate.entity.EIndicatorImportance;

public interface IIndicatorImportanceService {

	public void create(EIndicatorImportance entity);
	
	public void create(Collection<EIndicatorImportance> entities);
	
	public void update(EIndicatorImportance entity);
	
	public void delete(EIndicatorImportance entity);
	
	public EIndicatorImportance load(Long id);
	
	public List<EIndicatorImportance> list();
	
	public List<EIndicatorImportance> list4Category(Long categoryId);
	
	public void deleteAll();
	
	public void updateRating();
	
	public List<Map<String, Object>> queryListMap();
}
