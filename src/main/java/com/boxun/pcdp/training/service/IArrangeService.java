package com.boxun.pcdp.training.service;

import java.util.List;

import com.boxun.pcdp.admin.entity.TUser;
import com.boxun.pcdp.training.entity.TArrange;

public interface IArrangeService {

	public void create(TArrange entity);
	
	public void update(TArrange entity);
	
	public void delete(TArrange entity);
	
	public TArrange load(Long id);
	
	public List<TArrange> list();
	
	public Long getSize4Course(Long courseId, Boolean assigned);
	
	public List<TUser> listUsers4Course(Long courseId, Boolean assigned);
}
