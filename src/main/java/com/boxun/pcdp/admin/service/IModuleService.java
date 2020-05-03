package com.boxun.pcdp.admin.service;

import java.util.List;

import com.boxun.pcdp.admin.entity.TModule;

public interface IModuleService {

	public void create(TModule module);
	
	public void update(TModule module);
	
	public void delete(TModule module);
	
	public TModule load(Long id);
	
	public List<TModule> list();
}
