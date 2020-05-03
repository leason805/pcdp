package com.boxun.pcdp.training.service;

import java.util.Date;
import java.util.List;

import com.boxun.pcdp.admin.entity.TUser;
import com.boxun.pcdp.training.entity.TArrangeUser;

public interface IArrangeUserService {

	public void create(TArrangeUser entity);
	
	public void update(TArrangeUser entity);
	
	public void delete(TArrangeUser entity);
	
	public TArrangeUser load(Long id);
	
	public TArrangeUser loadByUUID(String uuid);
	
	public TArrangeUser load(Long id, String vcode);
	
	public List<TArrangeUser> list();
	
	public List<TArrangeUser> list(Long arrangeId);
	
	public List<TArrangeUser> list4User(Long userId);
	
	public List<TArrangeUser> list4User(Long userId, Date startDate, Date endDate);
	
	public Long getSize4Arrange(Long arrangeId, Boolean assigned);
	
	public List<TUser> listUsers4Arrange(Long arrangeId, Boolean assigned);
	
	public String generateBarCode(Long userId, String text);
}
