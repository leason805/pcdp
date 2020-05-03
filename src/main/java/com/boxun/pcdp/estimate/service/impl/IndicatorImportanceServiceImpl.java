package com.boxun.pcdp.estimate.service.impl;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.stereotype.Service;

import com.boxun.pcdp.estimate.dao.impl.IndicatorImportanceDaoImpl;
import com.boxun.pcdp.estimate.entity.EIndicatorImportance;
import com.boxun.pcdp.estimate.service.IIndicatorImportanceService;

@Service("indicatorimportanceService")
public class IndicatorImportanceServiceImpl implements IIndicatorImportanceService{

	@Autowired
	private IndicatorImportanceDaoImpl indicatorimportanceDao;
	
	@Override
	public void create(EIndicatorImportance entity) {
		this.indicatorimportanceDao.save(entity);
	}

	@Override
	public void update(EIndicatorImportance entity) {
		this.indicatorimportanceDao.saveOrUpdate(entity);
	}

	@Override
	public void delete(EIndicatorImportance entity) {
		this.indicatorimportanceDao.delete(entity);
	}

	@Override
	public EIndicatorImportance load(Long id) {
		return this.indicatorimportanceDao.load(id);
	}

	@Override
	public List<EIndicatorImportance> list() {
		return this.indicatorimportanceDao.loadAll();
		//return this.indicatorimportanceDao.find("from EIndicatorImportance where parent.id = 11");
	}

	@Override
	public void deleteAll() {
		this.indicatorimportanceDao.bulkUpdate("delete from EIndicatorImportance");
	}

	@Override
	public void create(Collection<EIndicatorImportance> entities) {
		this.indicatorimportanceDao.saveOrUpdate(entities);
		
	}

	@Override
	public void updateRating() {
		this.indicatorimportanceDao.getHibernateTemplate().execute(new HibernateCallback<Object>(){   
            public Object doInHibernate(Session session) throws HibernateException{ 
            	String sql = "UPDATE es_indicator_impo a, (SELECT CAST(AVG(rating) AS DECIMAL(10,3)) rating, IMPORTANCE_ID FROM es_indicator_impo_item GROUP BY IMPORTANCE_ID ) b SET a.rating = b.rating WHERE a.id = b.IMPORTANCE_ID";
            	session.createSQLQuery(sql).executeUpdate(); 
            	session.flush();
            	return null;
            }
		});
	}

	@Override
	public List<Map<String, Object>> queryListMap() {
		return this.indicatorimportanceDao.getQueryResultToListMap("select id, rating from ES_INDICATOR_IMPO");
	}

	@Override
	public List<EIndicatorImportance> list4Category(Long categoryId) {
		DetachedCriteria criteria = this.indicatorimportanceDao.createDetachedCriteria();
		criteria.add(Restrictions.eq("category.id", categoryId));
		return this.indicatorimportanceDao.findByCriteria(criteria);
	}

}
