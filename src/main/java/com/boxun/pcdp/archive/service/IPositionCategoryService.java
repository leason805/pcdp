package com.boxun.pcdp.archive.service;

import java.util.List;

import com.boxun.estms.entity.Const;
import com.boxun.pcdp.archive.entity.APositionCategory;

public interface IPositionCategoryService {

	public void create(APositionCategory entity);
	
	public void update(APositionCategory entity);
	
	public void delete(APositionCategory entity);
	
	public APositionCategory load(Long id);
	
	public List<APositionCategory> list();
	
	public List<APositionCategory> listByType(Const.PositionCategoryType type);
}
