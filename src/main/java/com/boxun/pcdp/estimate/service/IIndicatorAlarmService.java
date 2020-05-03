package com.boxun.pcdp.estimate.service;

import java.util.List;

import com.boxun.pcdp.estimate.entity.EIndicatorAlarm;

public interface IIndicatorAlarmService {

	public void create(EIndicatorAlarm entity);
	
	public void update(EIndicatorAlarm entity);
	
	public void delete(EIndicatorAlarm entity);
	
	public EIndicatorAlarm load(Long id);
	
	public List<EIndicatorAlarm> list();
}
