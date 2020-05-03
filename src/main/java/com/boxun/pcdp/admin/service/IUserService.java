package com.boxun.pcdp.admin.service;

import java.util.List;

import com.boxun.pcdp.admin.entity.TUser;

public interface IUserService {
	public void create(TUser user);
	
	public void update(TUser user);
	
	public void delete(TUser user);
	
	public TUser load(Long id);
	
	public TUser find(String loginId, String password);
	
	public List<TUser> list();
	
	public List<TUser> listPilots();
	
	public List<TUser> listUsers4Role(Long roleid, Boolean assigned);
	
	public Long getSize4Role(Long roleid, Boolean assigned);
}
