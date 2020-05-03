package com.boxun.pcdp.estimate.service.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boxun.estms.entity.Const;
import com.boxun.pcdp.admin.dao.impl.UserDaoImpl;
import com.boxun.pcdp.admin.entity.TUser;
import com.boxun.pcdp.estimate.dao.impl.AssessDaoImpl;
import com.boxun.pcdp.estimate.dao.impl.AssessProjectDaoImpl;
import com.boxun.pcdp.estimate.entity.EAssessProject;
import com.boxun.pcdp.estimate.service.IAssessProjectService;

@Service("assessProjectService")
public class AssessProjectServiceImpl implements IAssessProjectService{

	@Autowired
	private AssessProjectDaoImpl assessProjectDao;
	
	@Autowired
	private AssessDaoImpl assessDao;
	
	@Autowired
	private UserDaoImpl userDao;
	
	@Override
	public void create(EAssessProject entity) {
		this.assessProjectDao.save(entity);
	}

	@Override
	public void update(EAssessProject entity) {
		this.assessProjectDao.saveOrUpdate(entity);
	}

	@Override
	public void delete(EAssessProject entity) {
		this.assessProjectDao.delete(entity);
	}

	@Override
	public EAssessProject load(Long id) {
		return this.assessProjectDao.load(id);
	}

	@Override
	public List<EAssessProject> list() {
		return this.assessProjectDao.loadAll();
	}

	@Override
	public Long getSize4Assess(Long assessId, Boolean assigned) {
		DetachedCriteria criteria = this.userDao.createDetachedCriteria();
		//criteria.add(Restrictions.eq("userType", Const.UserType.PILOT));
		
		if(assigned){
			criteria.add(Restrictions.sqlRestriction(" id in (select user_id from ES_ASSESS where user_id is not null and PROJECT_ID = " + assessId + ")"));
		}
		else{
			criteria.add(Restrictions.sqlRestriction(" id not in (select user_id from ES_ASSESS where user_id is not null and PROJECT_ID = " + assessId + ")"));
		}
		
//		if(assessId == null){
//			if(assigned){
//				criteria.add(Restrictions.sqlRestriction(" id in (select user_id from ES_ASSESS where PROJECT_ID = " + assessId + ")"));
//			}
//		}
//		else{
//			if(assigned){
//				criteria.add(Restrictions.sqlRestriction(" id in (select user_id from ES_ASSESS where PROJECT_ID = " + assessId + ")"));
//			}
//			else{
//				criteria.add(Restrictions.sqlRestriction(" id not in (select user_id from ES_ASSESS where PROJECT_ID = " + assessId + ")"));
//			}
//		}
		
		return this.userDao.getRowCount(criteria);
	}

	@Override
	public List<TUser> listUsers4Assess(Long assessId, Boolean assigned) {
		List<Map<String, Object>> objs = this.userDao.getQueryResultToListMap("select user_id from ES_ASSESS where user_id is not null and PROJECT_ID = " + assessId);
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
		//criteria.add(Restrictions.eq("userType", Const.UserType.PILOT));
		if(assigned){
			criteria.add(Restrictions.in("id", ids));
			//criteria.add(Restrictions.sqlRestriction(" id in (select user_id from ES_ASSESS where user_id is not null and PROJECT_ID = " + assessId + ")"));
		}
		else{
			criteria.add(Restrictions.not( Restrictions.in("id", ids)));
			//criteria.add(Restrictions.sqlRestriction(" id not in (select user_id from ES_ASSESS where user_id is not null and PROJECT_ID = " + assessId + ")"));
		}
		
		List<TUser> list = this.userDao.findByCriteria(criteria);
		return list;
	}


}
