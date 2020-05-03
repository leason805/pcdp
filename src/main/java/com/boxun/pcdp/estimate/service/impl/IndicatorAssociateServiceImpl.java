package com.boxun.pcdp.estimate.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boxun.pcdp.estimate.dao.impl.IndicatorAssociateDaoImpl;
import com.boxun.pcdp.estimate.entity.EIndicatorAssociate;
import com.boxun.pcdp.estimate.service.IIndicatorAssociateService;

@Service("indicatorassociateService")
public class IndicatorAssociateServiceImpl implements IIndicatorAssociateService{

	@Autowired
	private IndicatorAssociateDaoImpl indicatorassociateDao;
	
	@Override
	public void create(EIndicatorAssociate entity) {
		this.indicatorassociateDao.save(entity);
	}

	@Override
	public void update(EIndicatorAssociate entity) {
		this.indicatorassociateDao.saveOrUpdate(entity);
	}

	@Override
	public void delete(EIndicatorAssociate entity) {
		this.indicatorassociateDao.delete(entity);
	}

	@Override
	public EIndicatorAssociate load(Long id) {
		return this.indicatorassociateDao.load(id);
	}

	@Override
	public List<EIndicatorAssociate> list() {
		return this.indicatorassociateDao.loadAll();
	}

}
