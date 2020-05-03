package com.boxun.pcdp.knowledge.service;

import java.util.List;

import com.boxun.pcdp.admin.entity.TUser;
import com.boxun.pcdp.knowledge.entity.KArrangeUser;

public interface IArrangeUserService {

	public void create(KArrangeUser entity);
	
	public void update(KArrangeUser entity);
	
	public void delete(KArrangeUser entity);
	
	public KArrangeUser load(Long id);
	
	public KArrangeUser loadByUUID(String uuid);
	
	public KArrangeUser load(Long id, String vcode);
	
	public List<KArrangeUser> list();
	
	public List<KArrangeUser> list(Long arrangeId);
	
	public List<KArrangeUser> list4User(Long userId);
	
	public List<KArrangeUser> list4User(Long userId, Boolean completed);
	
	public Long getSize4Arrange(Long arrangeId, Boolean assigned);
	
	public List<TUser> listUsers4Arrange(Long arrangeId, Boolean assigned);
	
	public String generateBarCode(Long userId, String text);
	
	public Long getSize4Started(Long arrangeId);
	
	public void delete(Long arrangeId);
}
