package com.boxun.pcdp.training.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boxun.estms.entity.Const;
import com.boxun.pcdp.training.dao.impl.CourseDaoImpl;
import com.boxun.pcdp.training.entity.TCourse;
import com.boxun.pcdp.training.service.ICourseService;

@Service("courseService")
public class CourseServiceImpl implements ICourseService{

	@Autowired
	private CourseDaoImpl courseDao;
	
	@Override
	public void create(TCourse entity) {
		this.courseDao.save(entity);
	}

	@Override
	public void update(TCourse entity) {
		this.courseDao.saveOrUpdate(entity);
	}

	@Override
	public void delete(TCourse entity) {
		this.courseDao.delete(entity);
	}

	@Override
	public TCourse load(Long id) {
		return this.courseDao.load(id);
	}

	@Override
	public List<TCourse> list() {
		return this.courseDao.loadAll();
	}

	@Override
	public List<TCourse> listActive() {
		DetachedCriteria criteria = this.courseDao.createDetachedCriteria();
		criteria.add(Restrictions.eq("status", Const.CourseStatus.ACTIVE));
		return this.courseDao.findByCriteria(criteria);
	}

}
