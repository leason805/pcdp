package com.boxun.pcdp.archive.transformer;

import org.springframework.stereotype.Component;

import com.boxun.estms.util.Const;
import com.boxun.estms.util.DateUtil;
import com.boxun.estms.util.StringUtil;
import com.boxun.pcdp.archive.entity.AExperience;
import com.boxun.pcdp.archive.pojo.ExperiencePojo;
import com.boxun.pcdp.basic.transformer.ITransformer;

@Component("experienceTransformer")
public class ExperienceTransformer implements ITransformer<AExperience, ExperiencePojo> {

	@Override
	public ExperiencePojo convert(AExperience entity) {
		ExperiencePojo pojo = new ExperiencePojo();
		pojo.setId(entity.getId());
		pojo.setCompany(entity.getCompany());
		pojo.setDepartment(entity.getDepartment());
		pojo.setDescription(entity.getDescription());
		if(entity.getStartDate() != null){
			pojo.setStartDate(DateUtil.formatDateTime(entity.getStartDate(), Const.DATE_FORMAT_2));
		}
		if(entity.getEndDate() != null){
			pojo.setEndDate(DateUtil.formatDateTime(entity.getEndDate(), Const.DATE_FORMAT_2));
		}
		return pojo;
	}

	@Override
	public AExperience transform(ExperiencePojo pojo) {
		AExperience experience = new AExperience();
		if(StringUtil.isNotBlank(pojo.getStartDate())){
			experience.setStartDate(DateUtil.formatDate(pojo.getStartDate(), Const.DATE_FORMAT_2));
		}
		if(StringUtil.isNotBlank(pojo.getEndDate())){
			experience.setEndDate(DateUtil.formatDate(pojo.getEndDate(), Const.DATE_FORMAT_2));
		}
		experience.setDepartment(pojo.getDepartment());
		experience.setCompany(pojo.getCompany());
		experience.setDescription(pojo.getDescription());
		return experience;
	}

	@Override
	public void update(ExperiencePojo pojo, AExperience entity) {
		if(StringUtil.isNotBlank(pojo.getStartDate())){
			entity.setStartDate(DateUtil.formatDate(pojo.getStartDate(), Const.DATE_FORMAT_2));
		}
		else{
			//entity.setStartDate(null);
		}
		if(StringUtil.isNotBlank(pojo.getEndDate())){
			entity.setEndDate(DateUtil.formatDate(pojo.getEndDate(), Const.DATE_FORMAT_2));
		}
		else{
			entity.setEndDate(null);
		}
		entity.setDepartment(pojo.getDepartment());
		entity.setCompany(pojo.getCompany());
		entity.setDescription(pojo.getDescription());
	}

}
