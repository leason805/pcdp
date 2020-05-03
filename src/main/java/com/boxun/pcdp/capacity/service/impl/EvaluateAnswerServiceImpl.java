package com.boxun.pcdp.capacity.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boxun.pcdp.capacity.dao.impl.EvaluateAnswerDaoImpl;
import com.boxun.pcdp.capacity.entity.CEvaluateAnswer;
import com.boxun.pcdp.capacity.service.IEvaluateAnswerService;

@Service("evaluateanswerService")
public class EvaluateAnswerServiceImpl implements IEvaluateAnswerService{

	@Autowired
	private EvaluateAnswerDaoImpl evaluateanswerDao;
	
	@Override
	public void create(CEvaluateAnswer entity) {
		this.evaluateanswerDao.save(entity);
	}

	@Override
	public void update(CEvaluateAnswer entity) {
		this.evaluateanswerDao.saveOrUpdate(entity);
	}

	@Override
	public void delete(CEvaluateAnswer entity) {
		this.evaluateanswerDao.delete(entity);
	}

	@Override
	public CEvaluateAnswer load(Long id) {
		return this.evaluateanswerDao.load(id);
	}

	@Override
	public List<CEvaluateAnswer> list() {
		return this.evaluateanswerDao.loadAll();
	}

	@Override
	public List<CEvaluateAnswer> list(Long arrangeUserId) {
		DetachedCriteria criteria = this.evaluateanswerDao.createDetachedCriteria();
		criteria.add(Restrictions.eq("arrangeUser.id", arrangeUserId));
		criteria.addOrder(Order.asc("question.id"));
		return this.evaluateanswerDao.findByCriteria(criteria);
	}

}
