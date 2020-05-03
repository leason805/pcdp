package com.boxun.pcdp.capacity.service;

import java.util.List;

import com.boxun.pcdp.admin.entity.TUser;
import com.boxun.pcdp.capacity.entity.CEvaluateArrangeUser;

public interface IEvaluateArrangeUserService {

	public void create(CEvaluateArrangeUser entity);
	
	public void update(CEvaluateArrangeUser entity);
	
	public void delete(CEvaluateArrangeUser entity);
	
	public CEvaluateArrangeUser load(Long id);
	
	public List<CEvaluateArrangeUser> list();
	
	public List<CEvaluateArrangeUser> list(Long arrangeId);
	
	public Long getSize4Arrange(Long arrangeId, Boolean assigned);
	
	public List<TUser> listUsers4Arrange(Long arrangeId, Boolean assigned);
	
	public Long getSize4Started(Long arrangeId);
	
	public void delete(Long arrangeId);
	
	public List<CEvaluateArrangeUser> list4User(Long userId, Boolean completed);
	
	public CEvaluateArrangeUser load(Long id, String vcode);
}
