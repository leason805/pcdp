package com.boxun.pcdp.archive.service;

import java.util.List;

import com.boxun.pcdp.archive.entity.AUserInfo;

public interface IUserInfoService {

	public void create(AUserInfo entity);
	
	public void update(AUserInfo entity);
	
	public void delete(AUserInfo entity);
	
	public AUserInfo load(Long id);
	
	public AUserInfo loadByUser(Long userId);
	
	public List<AUserInfo> list();
}
