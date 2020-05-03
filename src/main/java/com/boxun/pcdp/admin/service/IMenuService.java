package com.boxun.pcdp.admin.service;

import java.util.List;

import com.boxun.pcdp.admin.entity.TMenu;


public interface IMenuService {

	public void create(TMenu menu);
	
	public void update(TMenu menu);
	
	public void delete(TMenu menu);
	
	public TMenu load(Long id);
	
	public List<TMenu> list();
	
	public List<TMenu> listTops();
}
