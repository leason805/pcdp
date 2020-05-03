package com.boxun.pcdp.knowledge.service;

import java.util.List;

import com.boxun.pcdp.knowledge.entity.KSection;
import com.boxun.pcdp.knowledge.pojo.SectionPojo;

public interface ISectionService {

	public void create(KSection entity);
	
	public void update(KSection entity);
	
	public void delete(KSection entity);
	
	public KSection load(Long id);
	
	public List<KSection> list();
	
	public List<Long> listChildIds(Long parentId, Boolean include);
	
	public List<KSection> listTops();
	
	public List<KSection> listTops(Long companyId);
	
	public List<SectionPojo> listSectionPojos();
	
	public List<SectionPojo> listSectionPojosByCompany(Long companyId);
	
	public List<SectionPojo> listSectionPojosById(Long id);
}
