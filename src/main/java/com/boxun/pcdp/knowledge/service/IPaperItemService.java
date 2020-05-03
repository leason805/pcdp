package com.boxun.pcdp.knowledge.service;

import java.util.List;

import com.boxun.pcdp.knowledge.entity.KPaperItem;

public interface IPaperItemService {

	public void create(KPaperItem entity);
	
	public void update(KPaperItem entity);
	
	public void delete(KPaperItem entity);
	
	public KPaperItem load(Long id);
	
	public List<KPaperItem> list();
}
