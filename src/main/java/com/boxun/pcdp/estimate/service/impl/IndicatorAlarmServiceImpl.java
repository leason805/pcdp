package com.boxun.pcdp.estimate.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boxun.pcdp.estimate.dao.impl.IndicatorAlarmDaoImpl;
import com.boxun.pcdp.estimate.entity.EIndicatorAlarm;
import com.boxun.pcdp.estimate.service.IIndicatorAlarmService;

@Service("indicatoralarmService")
public class IndicatorAlarmServiceImpl implements IIndicatorAlarmService{

	@Autowired
	private IndicatorAlarmDaoImpl indicatoralarmDao;
	
	@Override
	public void create(EIndicatorAlarm entity) {
		this.indicatoralarmDao.save(entity);
	}

	@Override
	public void update(EIndicatorAlarm entity) {
		this.indicatoralarmDao.saveOrUpdate(entity);
	}

	@Override
	public void delete(EIndicatorAlarm entity) {
		this.indicatoralarmDao.delete(entity);
	}

	@Override
	public EIndicatorAlarm load(Long id) {
		return this.indicatoralarmDao.load(id);
	}

	@Override
	public List<EIndicatorAlarm> list() {
		return this.indicatoralarmDao.loadAll();
	}

}
