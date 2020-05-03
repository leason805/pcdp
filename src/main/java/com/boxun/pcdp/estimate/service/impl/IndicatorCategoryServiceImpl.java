package com.boxun.pcdp.estimate.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boxun.pcdp.estimate.dao.impl.IndicatorCategoryDaoImpl;
import com.boxun.pcdp.estimate.entity.EIndicatorCategory;
import com.boxun.pcdp.estimate.service.IIndicatorCategoryService;

@Service("indicatorcategoryService")
public class IndicatorCategoryServiceImpl implements IIndicatorCategoryService{

	@Autowired
	private IndicatorCategoryDaoImpl indicatorcategoryDao;
	
	@Override
	public void create(EIndicatorCategory entity) {
		this.indicatorcategoryDao.save(entity);
	}

	@Override
	public void update(EIndicatorCategory entity) {
		this.indicatorcategoryDao.saveOrUpdate(entity);
	}

	@Override
	public void delete(EIndicatorCategory entity) {
		this.indicatorcategoryDao.delete(entity);
	}

	@Override
	public EIndicatorCategory load(Long id) {
		return this.indicatorcategoryDao.load(id);
	}

	@Override
	public List<EIndicatorCategory> listActive() {
		return this.indicatorcategoryDao.loadAll();
	}

}
