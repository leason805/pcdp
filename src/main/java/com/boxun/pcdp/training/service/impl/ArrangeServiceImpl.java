package com.boxun.pcdp.training.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boxun.pcdp.admin.dao.impl.UserDaoImpl;
import com.boxun.pcdp.admin.entity.TUser;
import com.boxun.pcdp.training.dao.impl.ArrangeDaoImpl;
import com.boxun.pcdp.training.entity.TArrange;
import com.boxun.pcdp.training.service.IArrangeService;

@Service("tarrangeService")
public class ArrangeServiceImpl implements IArrangeService{

	@Autowired
	private ArrangeDaoImpl arrangeDao;
	
	@Autowired
	private UserDaoImpl userDao;
	
	@Override
	public void create(TArrange entity) {
		this.arrangeDao.save(entity);
	}

	@Override
	public void update(TArrange entity) {
		this.arrangeDao.saveOrUpdate(entity);
	}

	@Override
	public void delete(TArrange entity) {
		this.arrangeDao.delete(entity);
	}

	@Override
	public TArrange load(Long id) {
		return this.arrangeDao.load(id);
	}

	@Override
	public List<TArrange> list() {
		return this.arrangeDao.loadAll();
	}

	@Override
	public Long getSize4Course(Long courseId, Boolean assigned) {
		DetachedCriteria criteria = this.userDao.createDetachedCriteria();
		if(assigned){
			criteria.add(Restrictions.sqlRestriction(" id in (select user_id from TR_ARRANGE where course_id = " + courseId + ")"));
		}
		else{
			criteria.add(Restrictions.sqlRestriction(" id not in (select user_id from TR_ARRANGE where course_id = " + courseId + ")"));
		}
		return this.userDao.getRowCount(criteria);
	}

	@Override
	public List<TUser> listUsers4Course(Long courseId, Boolean assigned) {
		DetachedCriteria criteria = this.userDao.createDetachedCriteria();
		if(assigned){
			criteria.add(Restrictions.sqlRestriction(" id in (select user_id from TR_ARRANGE where course_id = " + courseId + ")"));
		}
		else{
			criteria.add(Restrictions.sqlRestriction(" id not in (select user_id from TR_ARRANGE where course_id = " + courseId + ")"));
		}
		List<TUser> list = this.userDao.findByCriteria(criteria);
		return list;
	}

}
