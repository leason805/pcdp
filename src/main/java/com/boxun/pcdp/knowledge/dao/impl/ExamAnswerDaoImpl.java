package com.boxun.pcdp.knowledge.dao.impl;

import java.util.Collection;

import org.hibernate.HibernateException;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Repository;

import com.boxun.pcdp.basic.dao.impl.GenericDaoImpl;
import com.boxun.pcdp.knowledge.entity.KExamAnswer;

@Repository("examanswerDao")
public class ExamAnswerDaoImpl extends GenericDaoImpl<KExamAnswer, Long>{

	@Override
	public Object getStackValue(DetachedCriteria criteria, String propertyName,
			String stackName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveOrUpdate(Collection<KExamAnswer> entitys)
			throws HibernateException {
		// TODO Auto-generated method stub
		
	}

}
