package com.boxun.pcdp.archive.service;

import java.util.List;

import com.boxun.pcdp.archive.entity.AJobInfo;

public interface IJobInfoService {

	public void create(AJobInfo entity);
	
	public void update(AJobInfo entity);
	
	public void delete(AJobInfo entity);
	
	public AJobInfo load(Long id);
	
	public List<AJobInfo> list();
}
