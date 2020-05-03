package com.boxun.pcdp.estimate.service;

import java.util.Collection;
import java.util.List;

import com.boxun.pcdp.estimate.entity.EIndicatorImportanceItem;

public interface IIndicatorImportanceItemService {

	public void create(EIndicatorImportanceItem entity);
	
	public void create(Collection<EIndicatorImportanceItem> entities);
	
	public void update(EIndicatorImportanceItem entity);
	
	public void delete(EIndicatorImportanceItem entity);
	
	public void delete(Long userId, Long categoryId);
	
	public EIndicatorImportanceItem load(Long id);
	
	public List<EIndicatorImportanceItem> list();
	
	public List<EIndicatorImportanceItem> list4Category(Long categoryId);
	
	public List<EIndicatorImportanceItem> list(Long userId);
}
