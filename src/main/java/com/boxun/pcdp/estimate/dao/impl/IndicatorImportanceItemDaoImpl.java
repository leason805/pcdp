package com.boxun.pcdp.estimate.dao.impl;

import java.util.Collection;

import org.hibernate.HibernateException;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Repository;

import com.boxun.pcdp.basic.dao.impl.GenericDaoImpl;
import com.boxun.pcdp.estimate.entity.EIndicatorImportanceItem;

@Repository("indicatorimportanceitemDao")
public class IndicatorImportanceItemDaoImpl extends GenericDaoImpl<EIndicatorImportanceItem, Long>{

	@Override
	public Object getStackValue(DetachedCriteria criteria, String propertyName,
			String stackName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveOrUpdate(Collection<EIndicatorImportanceItem> entities)
			throws HibernateException {
		for(EIndicatorImportanceItem item : entities){
			this.saveOrUpdate(item);
		}
	}

}
