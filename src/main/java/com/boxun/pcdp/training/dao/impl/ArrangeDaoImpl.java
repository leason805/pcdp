package com.boxun.pcdp.training.dao.impl;

import java.util.Collection;

import org.hibernate.HibernateException;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Repository;

import com.boxun.pcdp.basic.dao.impl.GenericDaoImpl;
import com.boxun.pcdp.training.entity.TArrange;

@Repository("arrangeDao")
public class ArrangeDaoImpl extends GenericDaoImpl<TArrange, Long>{

	@Override
	public Object getStackValue(DetachedCriteria criteria, String propertyName,
			String stackName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveOrUpdate(Collection<TArrange> entitys)
			throws HibernateException {
		// TODO Auto-generated method stub
		
	}

}
