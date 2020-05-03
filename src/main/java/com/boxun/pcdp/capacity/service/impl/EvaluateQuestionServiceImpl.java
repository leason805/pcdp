package com.boxun.pcdp.capacity.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boxun.pcdp.capacity.dao.impl.EvaluateQuestionDaoImpl;
import com.boxun.pcdp.capacity.entity.CEvaluateQuestion;
import com.boxun.pcdp.capacity.service.IEvaluateQuestionService;

@Service("evaluatequestionService")
public class EvaluateQuestionServiceImpl implements IEvaluateQuestionService{

	@Autowired
	private EvaluateQuestionDaoImpl evaluatequestionDao;
	
	@Override
	public void create(CEvaluateQuestion entity) {
		this.evaluatequestionDao.save(entity);
	}

	@Override
	public void update(CEvaluateQuestion entity) {
		this.evaluatequestionDao.saveOrUpdate(entity);
	}

	@Override
	public void delete(CEvaluateQuestion entity) {
		this.evaluatequestionDao.delete(entity);
	}

	@Override
	public CEvaluateQuestion load(Long id) {
		return this.evaluatequestionDao.load(id);
	}

	@Override
	public List<CEvaluateQuestion> list() {
		return this.evaluatequestionDao.loadAll();
	}

	@Override
	public List<CEvaluateQuestion> list(Long projectId) {
		DetachedCriteria criteria = this.evaluatequestionDao.createDetachedCriteria();
		criteria.add(Restrictions.eq("project.id", projectId));
		criteria.addOrder(Order.asc("id"));
		return this.evaluatequestionDao.findByCriteria(criteria);
	}

}
