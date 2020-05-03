package com.boxun.pcdp.archive.service;

import java.util.List;

import com.boxun.pcdp.archive.entity.AExpCategory;

public interface IExpCategoryService {

	public void create(AExpCategory entity);
	
	public void update(AExpCategory entity);
	
	public void delete(AExpCategory entity);
	
	public AExpCategory load(Long id);
	
	public List<AExpCategory> list();
	
	public List<AExpCategory> listTops();
}
