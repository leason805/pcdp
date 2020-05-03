package com.boxun.estms.service;

import java.util.List;

import com.boxun.estms.entity.EIndex;

public interface IIndexService {

	public void create(EIndex index);
	
	public void update(EIndex index);
	
	public void delete(EIndex index);
	
	public EIndex load(Long id);
	
	public List<EIndex> list();
	
	public List<EIndex> listTops();
}
