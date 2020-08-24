package com.boxun.pcdp.admin.service.impl;

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
import com.boxun.estms.util.MD5;
import com.boxun.pcdp.admin.dao.impl.UserDaoImpl;
import com.boxun.pcdp.admin.entity.TUser;
import com.boxun.pcdp.admin.service.IUserService;
import com.boxun.pcdp.archive.dao.impl.UserInfoDaoImpl;
import com.boxun.pcdp.archive.entity.AUserInfo;

@Service("userService")
public class UserServiceImpl implements IUserService{

	@Autowired
	private UserDaoImpl userDao;

	@Autowired
	private UserInfoDaoImpl userinfoDao;
	
	@Override
	public void create(TUser user) {
		this.userDao.save(user);
		AUserInfo userInfo =  new AUserInfo();
		userInfo.setUser(user);
		this.userinfoDao.save(userInfo);
	}
	
	@Override
	public void update(TUser user) {
		this.userDao.saveOrUpdate(user);
	}
	
	@Override
	public void delete(TUser user) {
		this.userDao.delete(user);
	}

	@Override
	public TUser load(Long id) {
		return this.userDao.load(id);
	}
	
	@Override
	public TUser find(String loginId, String password){
		DetachedCriteria criteria = this.userDao.createDetachedCriteria();
		criteria.add(Restrictions.eq("loginID", loginId)).add(Restrictions.eq("password", MD5.compute(password)));
		List<TUser> list = this.userDao.findByCriteria(criteria);
		if(list != null && !list.isEmpty())
			return list.get(0);
		return null;
	}

	@Override
	public List<TUser> list() {
//		return this.userDao.loadAll();
		DetachedCriteria criteria = this.userDao.createDetachedCriteria();
		criteria.add(Restrictions.ne("status", Const.UserStatus.DELETE));
		List<TUser> list = this.userDao.findByCriteria(criteria);
		return list;
	}

	@Override
	public List<TUser> listUsers4Role(Long roleid, Boolean assigned) {
		DetachedCriteria criteria = this.userDao.createDetachedCriteria();
		if(assigned){
			DetachedCriteria roleCriteria = criteria.createAlias("roles", "role");
			roleCriteria.add(Restrictions.eq("role.id", roleid));
		}
		else{
			List<Map<String, Object>> objs = this.userDao.getQueryResultToListMap("select user_id from BX_USER_ROLE where role_id = " + roleid);
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
			criteria.add(Restrictions.not( Restrictions.in("id", ids)));
			criteria.add(Restrictions.ne("status", Const.UserStatus.DELETE));
			//criteria.add(Restrictions.sqlRestriction(" id not in (select user_id from BX_USER_ROLE where role_id = " + roleid + ")"));
		}
		List<TUser> list = this.userDao.findByCriteria(criteria);
		return list;
	}

	@Override
	public Long getSize4Role(Long roleid, Boolean assigned) {
		
		
		DetachedCriteria criteria = this.userDao.createDetachedCriteria();
		if(assigned){
			DetachedCriteria roleCriteria = criteria.createAlias("roles", "role");
			roleCriteria.add(Restrictions.eq("role.id", roleid));
		}
		else{
			criteria.add(Restrictions.sqlRestriction(" id not in (select user_id from BX_USER_ROLE where role_id = " + roleid + ")"));
		}
		return this.userDao.getRowCount(criteria);
	}

	@Override
	public List<TUser> listPilots() {
		DetachedCriteria criteria = this.userDao.createDetachedCriteria();
		//criteria.add(Restrictions.eq("userType", Const.UserType.PILOT));
		criteria.addOrder(Order.asc("createTime"));
		return this.userDao.findByCriteria(criteria);
	}

	
}
