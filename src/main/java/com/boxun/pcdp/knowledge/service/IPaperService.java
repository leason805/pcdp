package com.boxun.pcdp.knowledge.service;

import java.util.List;

import com.boxun.pcdp.knowledge.entity.KPaper;

public interface IPaperService {

	public void create(KPaper entity);
	
	public void update(KPaper entity);
	
	public void delete(KPaper entity);
	
	public KPaper load(Long id);
	
	public List<KPaper> list();
}
