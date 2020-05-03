package com.boxun.pcdp.archive.transformer;

import org.springframework.stereotype.Component;

import com.boxun.estms.util.Const;
import com.boxun.estms.util.DateUtil;
import com.boxun.estms.util.StringUtil;
import com.boxun.pcdp.archive.entity.AEducationInfo;
import com.boxun.pcdp.archive.pojo.EduInfoPojo;
import com.boxun.pcdp.basic.transformer.ITransformer;

@Component("eduInfoTransformer")
public class EduInfoTransformer implements ITransformer<AEducationInfo, EduInfoPojo> {

	@Override
	public EduInfoPojo convert(AEducationInfo entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AEducationInfo transform(EduInfoPojo pojo) {
		AEducationInfo info = new AEducationInfo();
		info.setUniversity(pojo.getUniversity());
		info.setMajor(pojo.getMajor());
		info.setDegree(pojo.getDegree());
		if(StringUtil.isNotBlank(pojo.getStartDate())){
			info.setStartDate(DateUtil.formatDate(pojo.getStartDate(), Const.DATE_FORMAT_3));
		}
		if(StringUtil.isNotBlank(pojo.getEndDate())){
			info.setEndDate(DateUtil.formatDate(pojo.getEndDate(), Const.DATE_FORMAT_3));
		}
		return info;
	}

	@Override
	public void update(EduInfoPojo pojo, AEducationInfo entity) {
		entity.setUniversity(pojo.getUniversity());
		entity.setMajor(pojo.getMajor());
		entity.setDegree(pojo.getDegree());
		if(StringUtil.isNotBlank(pojo.getStartDate())){
			entity.setStartDate(DateUtil.formatDate(pojo.getStartDate(), Const.DATE_FORMAT_3));
		}
		if(StringUtil.isNotBlank(pojo.getEndDate())){
			entity.setEndDate(DateUtil.formatDate(pojo.getEndDate(), Const.DATE_FORMAT_3));
		}
		else{
			entity.setEndDate(null);
		}
		entity.setStatus(com.boxun.estms.entity.Const.CertificationStatus.PENDING);
	}

}
