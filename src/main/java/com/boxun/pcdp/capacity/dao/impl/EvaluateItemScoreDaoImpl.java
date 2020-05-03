package com.boxun.pcdp.capacity.dao.impl;

import java.util.Collection;

import org.hibernate.HibernateException;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Repository;

import com.boxun.pcdp.basic.dao.impl.GenericDaoImpl;
import com.boxun.pcdp.capacity.entity.CEvaluateItemScore;

@Repository("evaluateitemscoreDao")
public class EvaluateItemScoreDaoImpl extends GenericDaoImpl<CEvaluateItemScore, Long>{

	@Override
	public Object getStackValue(DetachedCriteria criteria, String propertyName,
			String stackName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveOrUpdate(Collection<CEvaluateItemScore> entitys)
			throws HibernateException {
		// TODO Auto-generated method stub
		
	}

}
