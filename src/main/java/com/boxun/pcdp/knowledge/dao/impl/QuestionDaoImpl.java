package com.boxun.pcdp.knowledge.dao.impl;

import java.util.Collection;

import org.hibernate.HibernateException;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Repository;

import com.boxun.pcdp.basic.dao.impl.GenericDaoImpl;
import com.boxun.pcdp.knowledge.entity.KQuestion;

@Repository("questionDao")
public class QuestionDaoImpl extends GenericDaoImpl<KQuestion, Long>{

	@Override
	public Object getStackValue(DetachedCriteria criteria, String propertyName,
			String stackName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveOrUpdate(Collection<KQuestion> entities)
			throws HibernateException {
		for(KQuestion que : entities){
			this.saveOrUpdate(que);
		}
		
	}

}
