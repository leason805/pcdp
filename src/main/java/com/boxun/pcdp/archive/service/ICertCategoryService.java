package com.boxun.pcdp.archive.service;

import java.util.List;

import com.boxun.pcdp.archive.entity.ACertCategory;

public interface ICertCategoryService {

	public void create(ACertCategory entity);
	
	public void update(ACertCategory entity);
	
	public void delete(ACertCategory entity);
	
	public ACertCategory load(Long id);
	
	public List<ACertCategory> list();
	
	public List<ACertCategory> listTops();
}
