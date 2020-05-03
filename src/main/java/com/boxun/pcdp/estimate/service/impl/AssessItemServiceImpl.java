package com.boxun.pcdp.estimate.service.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boxun.estms.entity.Const;
import com.boxun.pcdp.admin.dao.impl.UserDaoImpl;
import com.boxun.pcdp.admin.entity.TUser;
import com.boxun.pcdp.estimate.dao.impl.AssessItemDaoImpl;
import com.boxun.pcdp.estimate.entity.EAssessItem;
import com.boxun.pcdp.estimate.service.IAssessItemService;

@Service("assessitemService")
public class AssessItemServiceImpl implements IAssessItemService{

	@Autowired
	private AssessItemDaoImpl assessitemDao;
	
	@Autowired
	private UserDaoImpl userDao;
	
	@Override
	public void create(EAssessItem entity) {
		this.assessitemDao.save(entity);
	}

	@Override
	public void update(EAssessItem entity) {
		this.assessitemDao.saveOrUpdate(entity);
	}

	@Override
	public void delete(EAssessItem entity) {
		this.assessitemDao.delete(entity);
	}

	@Override
	public EAssessItem load(Long id) {
		return this.assessitemDao.load(id);
	}

	@Override
	public List<EAssessItem> list() {
		return this.assessitemDao.loadAll();
	}

	@Override
	public List<EAssessItem> list4User(Long userId, Long projectId) {
		DetachedCriteria criteria = this.assessitemDao.createDetachedCriteria();
		criteria.createAlias("assess", "assess"); 
		criteria.add(Restrictions.eq("owner.id", userId));
		criteria.add(Restrictions.eq("assess.project.id", projectId));
		return this.assessitemDao.findByCriteria(criteria);
	}

	@Override
	public List<TUser> listUsers4Assess(Long assessId, Boolean assigned) {
		List<Map<String, Object>> objs = this.userDao.getQueryResultToListMap("select user_id from ES_ASSESS_ITEM where user_id is not null and ASSESS_ID = " + assessId);
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
		
		DetachedCriteria criteria = this.userDao.createDetachedCriteria();
		//criteria.add(Restrictions.ne("userType", Const.UserType.PILOT));
		if(assigned){
			criteria.add(Restrictions.in("id", ids));
			//criteria.add(Restrictions.sqlRestriction(" id in (select user_id from ES_ASSESS_ITEM where user_id is not null and ASSESS_ID = " + assessId + ")"));
		}
		else{
			criteria.add(Restrictions.not( Restrictions.in("id", ids)));
			//criteria.add(Restrictions.sqlRestriction(" id not in (select user_id from ES_ASSESS_ITEM where user_id is not null and ASSESS_ID = " + assessId + ")"));
		}
		List<TUser> list = this.userDao.findByCriteria(criteria);
		return list;
	}

	@Override
	public Long getSize4Assess(Long assessId, Boolean assigned) {
		DetachedCriteria criteria = this.userDao.createDetachedCriteria();
		//criteria.add(Restrictions.ne("userType", Const.UserType.PILOT));
		
		if(assigned){
			criteria.add(Restrictions.sqlRestriction(" id in (select user_id from ES_ASSESS_ITEM where user_id is not null and ASSESS_ID = " + assessId + ")"));
		}
		else{
			criteria.add(Restrictions.sqlRestriction(" id not in (select user_id from ES_ASSESS_ITEM where user_id is not null and ASSESS_ID = " + assessId + ")"));
		}
		
		return this.userDao.getRowCount(criteria);
	}

	@Override
	public List<EAssessItem> list4Assess(Long assessId) {
		DetachedCriteria criteria = this.assessitemDao.createDetachedCriteria();
		criteria.add(Restrictions.eq("assess.id", assessId));
		return this.assessitemDao.findByCriteria(criteria);
	}
}
