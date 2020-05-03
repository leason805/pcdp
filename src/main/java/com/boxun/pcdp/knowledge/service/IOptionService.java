package com.boxun.pcdp.knowledge.service;

import java.util.List;

import com.boxun.pcdp.knowledge.entity.KOption;

public interface IOptionService {

	public void create(KOption entity);
	
	public void update(KOption entity);
	
	public void delete(KOption entity);
	
	public KOption load(Long id);
	
	public List<KOption> list();
}
