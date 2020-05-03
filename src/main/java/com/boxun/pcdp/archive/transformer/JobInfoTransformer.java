package com.boxun.pcdp.archive.transformer;

import org.springframework.stereotype.Component;

import com.boxun.estms.util.Const;
import com.boxun.estms.util.DateUtil;
import com.boxun.estms.util.StringUtil;
import com.boxun.pcdp.archive.entity.AJobInfo;
import com.boxun.pcdp.archive.pojo.JobInfoPojo;
import com.boxun.pcdp.basic.transformer.ITransformer;

@Component("jobinfoTransformer")
public class JobInfoTransformer implements ITransformer<AJobInfo, JobInfoPojo> {

	@Override
	public JobInfoPojo convert(AJobInfo entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AJobInfo transform(JobInfoPojo pojo) {
		AJobInfo info =  new AJobInfo();
		if(StringUtil.isNotBlank(pojo.getStartDate())){
			info.setStartDate(DateUtil.formatDate(pojo.getStartDate(), Const.DATE_FORMAT_2));
		}
		if(StringUtil.isNotBlank(pojo.getJoinDate())){
			info.setJoinDate(DateUtil.formatDate(pojo.getJoinDate(), Const.DATE_FORMAT_2));
		}
		//info.setPositionLevel(pojo.getPositionLevel());
		//info.setTechLevel(pojo.getTechLevel());
		return info;
	}

	@Override
	public void update(JobInfoPojo pojo, AJobInfo entity) {
		if(StringUtil.isNotBlank(pojo.getStartDate())){
			entity.setStartDate(DateUtil.formatDate(pojo.getStartDate(), Const.DATE_FORMAT_2));
		}
		else{
			entity.setStartDate(null);
		}
		if(StringUtil.isNotBlank(pojo.getJoinDate())){
			entity.setJoinDate(DateUtil.formatDate(pojo.getJoinDate(), Const.DATE_FORMAT_2));
		}
		else{
			entity.setJoinDate(null);
		}
		//entity.setPositionLevel(pojo.getPositionLevel());
		//entity.setTechLevel(pojo.getTechLevel());
	}

}
