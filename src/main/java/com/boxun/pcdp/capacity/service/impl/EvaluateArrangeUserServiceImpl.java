package com.boxun.pcdp.capacity.service.impl;

import java.math.BigInteger;
import java.util.ArrayList;
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

import com.boxun.estms.entity.Const;
import com.boxun.pcdp.admin.dao.impl.UserDaoImpl;
import com.boxun.pcdp.admin.entity.TUser;
import com.boxun.pcdp.capacity.dao.impl.EvaluateArrangeUserDaoImpl;
import com.boxun.pcdp.capacity.entity.CEvaluateArrangeUser;
import com.boxun.pcdp.capacity.service.IEvaluateArrangeUserService;

@Service("carrangeuserService")
public class EvaluateArrangeUserServiceImpl implements IEvaluateArrangeUserService{

	@Autowired
	private EvaluateArrangeUserDaoImpl carrangeuserDao;
	
	@Autowired
	private UserDaoImpl userDao;
	
	@Override
	public void create(CEvaluateArrangeUser entity) {
		this.carrangeuserDao.save(entity);
	}

	@Override
	public void update(CEvaluateArrangeUser entity) {
		this.carrangeuserDao.saveOrUpdate(entity);
	}

	@Override
	public void delete(CEvaluateArrangeUser entity) {
		this.carrangeuserDao.delete(entity);
	}

	@Override
	public CEvaluateArrangeUser load(Long id) {
		return this.carrangeuserDao.load(id);
	}

	@Override
	public List<CEvaluateArrangeUser> list() {
		return this.carrangeuserDao.loadAll();
	}

	@Override
	public List<CEvaluateArrangeUser> list(Long arrangeId) {
		DetachedCriteria criteria = this.carrangeuserDao.createDetachedCriteria();
		criteria.add(Restrictions.eq("arrange.id", arrangeId));
		criteria.add(Restrictions.isNotNull("user.id"));
		return this.carrangeuserDao.findByCriteria(criteria);
	}

	@Override
	public Long getSize4Arrange(Long arrangeId, Boolean assigned) {
		DetachedCriteria criteria = this.userDao.createDetachedCriteria();

		if(assigned){
			criteria.add(Restrictions.sqlRestriction(" id in (select user_id from CA_ARRANGE_USER where user_id is not null and ARRANGE_ID = " + arrangeId + ")"));
		}
		else{
			criteria.add(Restrictions.sqlRestriction(" id not in (select user_id from CA_ARRANGE_USER where user_id is not null and ARRANGE_ID = " + arrangeId + ")"));
		}
		
		return this.userDao.getRowCount(criteria);
	}

	@Override
	public List<TUser> listUsers4Arrange(Long arrangeId, Boolean assigned) {
		List<Map<String, Object>> objs = this.userDao.getQueryResultToListMap("select user_id from CA_ARRANGE_USER where user_id is not null and ARRANGE_ID = " + arrangeId);
		
		DetachedCriteria criteria = this.userDao.createDetachedCriteria();
		
		List<Object> ids = new ArrayList<Object>();
		if(objs == null || objs.isEmpty()){
			ids.add(-1l);
		}
		else{
			for(Map<String, Object> map : objs){
				for(Object obj : map.values()){
					ids.add(((BigInteger)obj).longValue());
				}
			}
		}
		
		if(assigned){
			criteria.add(Restrictions.in("id", ids));
		}
		else{
			criteria.add(Restrictions.not( Restrictions.in("id", ids)));
		}
		return this.userDao.findByCriteria(criteria);
	}

	@Override
	public Long getSize4Started(Long arrangeId) {
		DetachedCriteria criteria = this.carrangeuserDao.createDetachedCriteria();
		criteria.add(Restrictions.eq("arrange.id", arrangeId));
		criteria.add(Restrictions.ne("status", Const.ArrangeUserStatus.DRAFT));
		
		return this.userDao.getRowCount(criteria);
	}

	@Override
	public void delete(Long arrangeId) {
		final String id = arrangeId + "";
		this.carrangeuserDao.getHibernateTemplate().execute(new HibernateCallback<Object>(){   
            public Object doInHibernate(Session session) throws HibernateException{ 
            	String sql = "DELETE FROM ca_arrange_user WHERE ARRANGE_ID = " + id;
            	session.createSQLQuery(sql).executeUpdate(); 
            	session.flush();
            	return null;
            }
		});
	}

	@Override
	public List<CEvaluateArrangeUser> list4User(Long userId, Boolean completed) {
		DetachedCriteria criteria = this.carrangeuserDao.createDetachedCriteria();
		criteria.add(Restrictions.eq("user.id", userId));
		if(completed){
			criteria.add(Restrictions.eq("status", Const.ArrangeUserStatus.COMPLETED));
			criteria.addOrder(Order.desc("completeTime"));
		}
		
		return this.carrangeuserDao.findByCriteria(criteria);
	}

	@Override
	public CEvaluateArrangeUser load(Long id, String vcode) {
		DetachedCriteria criteria = this.carrangeuserDao.createDetachedCriteria();
		criteria.add(Restrictions.eq("id", id));
		criteria.add(Restrictions.eq("vcode", vcode));
		List<CEvaluateArrangeUser> list =this.carrangeuserDao.findByCriteria(criteria);
		if(list != null && !list.isEmpty()){
			return list.get(0);
		}
		return null;
	}

}
