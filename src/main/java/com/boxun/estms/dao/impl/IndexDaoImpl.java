package com.boxun.estms.dao.impl;

import java.util.Collection;

import org.hibernate.HibernateException;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Repository;

import com.boxun.estms.entity.EIndex;
import com.boxun.pcdp.basic.dao.impl.GenericDaoImpl;

@Repository("indexDao")
public class IndexDaoImpl extends GenericDaoImpl<EIndex, Long> {

	@Override
	public Object getStackValue(DetachedCriteria criteria, String propertyName,
			String stackName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveOrUpdate(Collection<EIndex> entitys)
			throws HibernateException {
		// TODO Auto-generated method stub
		
	}

}
