package com.boxun.pcdp.estimate.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.stereotype.Service;

import com.boxun.estms.entity.Const.IndicatorType;
import com.boxun.pcdp.estimate.dao.impl.IndicatorDaoImpl;
import com.boxun.pcdp.estimate.entity.EIndicator;
import com.boxun.pcdp.estimate.service.IIndicatorService;
import com.boxun.pcdp.knowledge.entity.KSection;

@Service("indicatorService")
public class IndicatorServiceImpl implements IIndicatorService{

	@Autowired
	private IndicatorDaoImpl indicatorDao;
	
	@Override
	public void create(EIndicator entity) {
		this.indicatorDao.save(entity);
	}

	@Override
	public void update(EIndicator entity) {
		this.indicatorDao.saveOrUpdate(entity);
	}

	@Override
	public void delete(EIndicator entity) {
		this.indicatorDao.delete(entity);
	}

	@Override
	public EIndicator load(Long id) {
		return this.indicatorDao.load(id);
	}

	@Override
	public List<EIndicator> list() {
		return this.indicatorDao.loadAll();
	}
	
	@Override
	public List<EIndicator> listByCategory(Long categoryId) {
		DetachedCriteria criteria = this.indicatorDao.createDetachedCriteria();
		criteria.add(Restrictions.eq("category.id", categoryId));
		return this.indicatorDao.findByCriteria(criteria);
	}

	@Override
	public List<EIndicator> listTops() {
		return this.indicatorDao.find("from EIndicator where parent is null order by sequence asc");
	}

	
	@Override
	public void updateScore() {
		this.indicatorDao.getHibernateTemplate().execute(new HibernateCallback<Object>(){   
            public Object doInHibernate(Session session) throws HibernateException{ 
            	String sql = "UPDATE es_indicator a, (SELECT SUM(score) score, INDICATOR_ID FROM es_indicator_score GROUP BY INDICATOR_ID) b SET a.score = b.score WHERE a.id = b.indicator_id";
            	//String sql = "UPDATE es_indicator INNER JOIN (SELECT SUM(score) score, INDICATOR_ID FROM es_indicator_score GROUP BY INDICATOR_ID) b ON es_indicator.id=b.indicator_id SET es_indicator.score = b.score"; 
            	session.createSQLQuery(sql).executeUpdate(); 
            	return null;
            }
		});
		//this.indicatorDao.bulkUpdate("UPDATE es_indicator a, (SELECT SUM(score) score, INDICATOR_ID FROM es_indicator_score GROUP BY INDICATOR_ID) b SET a.score = b.score WHERE a.id = b.indicator_id");
	}

	@Override
	public List<EIndicator> listTops4Category(Long categoryId) {
		DetachedCriteria criteria = this.indicatorDao.createDetachedCriteria();
		criteria.add(Restrictions.eq("category.id", categoryId));
		criteria.add(Restrictions.isNull("parent"));
		criteria.addOrder(Order.asc("sequence"));
		return this.indicatorDao.findByCriteria(criteria);
	}

	@Override
	public List<Long> listIds(List<EIndicator> indicators) {
		List<Long> ids = new ArrayList<Long>();
		for(EIndicator ind : indicators){
			ids.add(ind.getId());
			if(ind.getChildren() != null){
				for(EIndicator child : ind.getChildren()){
					setIds(ids, child);
				}
			}
		}
		return ids;
	}
	
	private void setIds(List<Long> ids, EIndicator indicator){
		if(indicator != null && indicator.getChildren() != null){
			ids.add(indicator.getId());
			for(EIndicator ind : indicator.getChildren()){
				setIds(ids, ind);
			}
		}
	}

	@Override
	public List<EIndicator> listTops4Category(Long categoryId, IndicatorType type) {
		DetachedCriteria criteria = this.indicatorDao.createDetachedCriteria();
		criteria.add(Restrictions.eq("category.id", categoryId));
		criteria.add(Restrictions.eq("indicatorType", type));
		criteria.add(Restrictions.isNull("parent"));
		criteria.addOrder(Order.asc("sequence"));
		return this.indicatorDao.findByCriteria(criteria);
	}

}
