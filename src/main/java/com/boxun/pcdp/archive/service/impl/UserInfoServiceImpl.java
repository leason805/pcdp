package com.boxun.pcdp.archive.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boxun.pcdp.archive.dao.impl.UserInfoDaoImpl;
import com.boxun.pcdp.archive.entity.AUserInfo;
import com.boxun.pcdp.archive.service.IUserInfoService;

@Service("userinfoService")
public class UserInfoServiceImpl implements IUserInfoService{

	@Autowired
	private UserInfoDaoImpl userinfoDao;
	
	@Override
	public void create(AUserInfo entity) {
		this.userinfoDao.save(entity);
	}

	@Override
	public void update(AUserInfo entity) {
		this.userinfoDao.saveOrUpdate(entity);
	}

	@Override
	public void delete(AUserInfo entity) {
		this.userinfoDao.delete(entity);
	}

	@Override
	public AUserInfo load(Long id) {
		return this.userinfoDao.load(id);
	}

	@Override
	public List<AUserInfo> list() {
		return this.userinfoDao.loadAll();
	}

	@Override
	public AUserInfo loadByUser(Long userId) {
		DetachedCriteria criteria = this.userinfoDao.createDetachedCriteria();
		criteria.add(Restrictions.eq("user.id", userId));
		List<AUserInfo> list = this.userinfoDao.findByCriteria(criteria);
		if(list != null && !list.isEmpty()){
			return list.get(0);
		}
		return null;
	}

}
