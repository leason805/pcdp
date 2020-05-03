package com.boxun.pcdp.estimate.dao.impl;

import java.util.Collection;

import org.hibernate.HibernateException;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Repository;

import com.boxun.pcdp.basic.dao.impl.GenericDaoImpl;
import com.boxun.pcdp.estimate.entity.EIndicatorAlarm;

@Repository("indicatoralarmDao")
public class IndicatorAlarmDaoImpl extends GenericDaoImpl<EIndicatorAlarm, Long>{

	@Override
	public Object getStackValue(DetachedCriteria criteria, String propertyName,
			String stackName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveOrUpdate(Collection<EIndicatorAlarm> entitys)
			throws HibernateException {
		// TODO Auto-generated method stub
		
	}

}
