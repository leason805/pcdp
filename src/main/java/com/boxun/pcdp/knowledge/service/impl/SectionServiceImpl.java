package com.boxun.pcdp.knowledge.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boxun.pcdp.knowledge.dao.impl.SectionDaoImpl;
import com.boxun.pcdp.knowledge.entity.KSection;
import com.boxun.pcdp.knowledge.pojo.SectionPojo;
import com.boxun.pcdp.knowledge.service.IQuestionService;
import com.boxun.pcdp.knowledge.service.ISectionService;

@Service("sectionService")
public class SectionServiceImpl implements ISectionService{

	@Autowired
	private SectionDaoImpl sectionDao;
	
	@Autowired
	private IQuestionService questionService;
	
	@Override
	public void create(KSection entity) {
		this.sectionDao.save(entity);
	}

	@Override
	public void update(KSection entity) {
		this.sectionDao.saveOrUpdate(entity);
	}

	@Override
	public void delete(KSection entity) {
		this.sectionDao.delete(entity);
	}

	@Override
	public KSection load(Long id) {
		return this.sectionDao.load(id);
	}

	@Override
	public List<KSection> list() {
		return this.sectionDao.loadAll();
	}

	@Override
	public List<KSection> listTops() {
		return this.sectionDao.find("from KSection where parent is null order by sequence asc");
	}

	@Override
	public List<Long> listChildIds(Long parentId, Boolean include) {
		List<Long> ids = new ArrayList<Long>();
		KSection parent = this.load(parentId);
		if(parent != null && parent.getChildren() != null){
			if(include){
				ids.add(parentId);
			}
			for(KSection sec : parent.getChildren()){
				setIds(ids, sec);
			}
		}
		return ids;
	}

	private void setIds(List<Long> ids, KSection section){
		if(section != null && section.getChildren() != null){
			ids.add(section.getId());
			for(KSection sec : section.getChildren()){
				setIds(ids, sec);
			}
		}
	}

	@Override
	public List<SectionPojo> listSectionPojos() {
		List<KSection> tops = this.listTops();
		Map<String, Long> quesMap = this.questionService.listQuestSizeBySection();
		
		List<SectionPojo> list = new ArrayList<SectionPojo>();
		SectionPojo pojo = null;
		for(KSection in : tops){
			pojo = new SectionPojo();
			pojo.setId(in.getId());
			pojo.setName(in.getName());
			pojo.setDescription(in.getDescription());
			pojo.setLevel(in.getLevel());
			pojo.setSequence(in.getSequence());
			if(quesMap != null && quesMap.get(pojo.getId()) != null){
				pojo.setQuestSize(quesMap.get(pojo.getId()));
			}
			list.add(pojo);
			children(pojo, in.getChildren(), quesMap);
		}
		return list;
	}
	
	private void children(SectionPojo parent, List<KSection> sections, Map<String, Long> quesMap){
		SectionPojo pojo = null;
		if(sections != null && !sections.isEmpty()){
			for(KSection in : sections){
				pojo = new SectionPojo();
				pojo.setId(in.getId());
				pojo.setName(in.getName());
				pojo.setDescription(in.getDescription());
				pojo.setLevel(in.getLevel());
				pojo.setSequence(in.getSequence());
				pojo.setParent(parent);
				if(quesMap != null && quesMap.get(pojo.getId().toString()) != null){
					pojo.setQuestSize(quesMap.get(pojo.getId().toString()));
				}
				
				parent.addChild(pojo);
				children(pojo, in.getChildren(), quesMap);
			}
		}
	}

	@Override
	public List<SectionPojo> listSectionPojosByCompany(Long companyId) {
		List<KSection> secs = this.listTops();
		List<KSection> tops = new ArrayList<KSection>();
		for(KSection section : secs){
			if(section.getCompany().getId().equals(companyId)){
				tops.add(section);
			}
		}
		
		Map<String, Long> quesMap = this.questionService.listQuestSizeBySection();
		
		List<SectionPojo> list = new ArrayList<SectionPojo>();
		SectionPojo pojo = null;
		for(KSection in : tops){
			pojo = new SectionPojo();
			pojo.setId(in.getId());
			pojo.setName(in.getName());
			pojo.setDescription(in.getDescription());
			pojo.setLevel(in.getLevel());
			pojo.setSequence(in.getSequence());
			if(quesMap != null && quesMap.get(pojo.getId()) != null){
				pojo.setQuestSize(quesMap.get(pojo.getId()));
			}
			list.add(pojo);
			children(pojo, in.getChildren(), quesMap);
		}
		return list;
	}

	@Override
	public List<KSection> listTops(Long companyId) {
		DetachedCriteria criteria = this.sectionDao.createDetachedCriteria();
		criteria.add(Restrictions.isNull("parent"));
		criteria.add(Restrictions.eq("company.id", companyId));
		return this.sectionDao.findByCriteria(criteria);
	}

	@Override
	public List<SectionPojo> listSectionPojosById(Long id) {
		KSection secs = this.load(id);
		List<KSection> tops = new ArrayList<KSection>();
		tops.add(secs);
		
		Map<String, Long> quesMap = this.questionService.listQuestSizeBySection();
		
		List<SectionPojo> list = new ArrayList<SectionPojo>();
		SectionPojo pojo = null;
		for(KSection in : tops){
			pojo = new SectionPojo();
			pojo.setId(in.getId());
			pojo.setName(in.getName());
			pojo.setDescription(in.getDescription());
			pojo.setLevel(in.getLevel());
			pojo.setSequence(in.getSequence());
			if(quesMap != null && quesMap.get(pojo.getId()) != null){
				pojo.setQuestSize(quesMap.get(pojo.getId()));
			}
			list.add(pojo);
			children(pojo, in.getChildren(), quesMap);
		}
		return list;
	}
}
