package com.boxun.pcdp.archive.service;

import java.util.List;

import com.boxun.pcdp.archive.entity.AEducationInfo;

public interface IEducationInfoService {

	public void create(AEducationInfo entity);
	
	public void update(AEducationInfo entity);
	
	public void delete(AEducationInfo entity);
	
	public AEducationInfo load(Long id);
	
	public List<AEducationInfo> list();
}
