package com.boxun.pcdp.admin.service;

import java.util.List;

import com.boxun.pcdp.admin.entity.TRole;

public interface IRoleService {

	public void create(TRole role);
	
	public void update(TRole role);
	
	public void delete(TRole role);
	
	public TRole load(Long id);
	
	public List<TRole> list();
}
