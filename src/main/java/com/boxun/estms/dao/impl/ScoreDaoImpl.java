package com.boxun.estms.dao.impl;

import java.util.Collection;

import org.hibernate.HibernateException;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Repository;

import com.boxun.estms.entity.EScore;
import com.boxun.pcdp.basic.dao.impl.GenericDaoImpl;

@Repository("scoreDao")
public class ScoreDaoImpl extends GenericDaoImpl<EScore, Long> {

	@Override
	public Object getStackValue(DetachedCriteria criteria, String propertyName,
			String stackName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveOrUpdate(Collection<EScore> entitys)
			throws HibernateException {
		// TODO Auto-generated method stub
		
	}

}
