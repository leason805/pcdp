package com.boxun.pcdp.performance.dao.impl;

import java.util.Collection;

import org.hibernate.HibernateException;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Repository;

import com.boxun.pcdp.basic.dao.impl.GenericDaoImpl;
import com.boxun.pcdp.performance.entity.PReview;

@Repository("reviewDao")
public class ReviewDaoImpl extends GenericDaoImpl<PReview, Long>{

	@Override
	public Object getStackValue(DetachedCriteria criteria, String propertyName,
			String stackName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveOrUpdate(Collection<PReview> entitys)
			throws HibernateException {
		// TODO Auto-generated method stub
		
	}

}
