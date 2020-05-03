package com.boxun.pcdp.training.service;

import java.util.List;

import com.boxun.pcdp.training.entity.TCourse;

public interface ICourseService {

	public void create(TCourse entity);
	
	public void update(TCourse entity);
	
	public void delete(TCourse entity);
	
	public TCourse load(Long id);
	
	public List<TCourse> list();
	
	public List<TCourse> listActive();
}
