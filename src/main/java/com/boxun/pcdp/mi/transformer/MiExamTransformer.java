package com.boxun.pcdp.mi.transformer;

import org.springframework.stereotype.Component;

import com.boxun.estms.entity.Const;
import com.boxun.estms.util.DateUtil;
import com.boxun.pcdp.basic.transformer.ITransformer;
import com.boxun.pcdp.knowledge.entity.KArrangeUser;
import com.boxun.pcdp.mi.pojo.MiExamArrangePojo;

@Component("miExamTransformer")
public class MiExamTransformer implements ITransformer<KArrangeUser, MiExamArrangePojo> {

	@Override
	public MiExamArrangePojo convert(KArrangeUser entity) {
		return null;
		/*
		MiExamArrangePojo pojo = new MiExamArrangePojo();
		pojo.setId(entity.getId().toString());
		pojo.setName(entity.getArrange().getProject().getTitle());
		pojo.setMins(entity.getArrange().getProject().getMinutes()+"");
		pojo.setScore(entity.getArrange().getProject().getScore()+"");
		if(entity.getArrange().getExamDate() != null){
			pojo.setDate(DateUtil.formatDateTime(entity.getArrange().getExamDate(), com.boxun.estms.util.Const.DATE_FORMAT_2));
		}
		pojo.setTime(entity.getArrange().getExamTime());
		pojo.setAddress(entity.getArrange().getAddress());
		pojo.setDescription(entity.getArrange().getProject().getDescription());
		pojo.setStatus(entity.getStatus().toString());
		if(Const.ArrangeUserStatus.DRAFT.equals(entity.getStatus())){
			pojo.setStatusDesc("待签订");
		}
		if(Const.ArrangeUserStatus.SIGNED.equals(entity.getStatus())){
			pojo.setStatusDesc("已签订");
		}
		if(Const.ArrangeUserStatus.DRAFT.equals(entity.getStatus())){
			pojo.setStatusDesc("待签订");
		}
		return pojo;
		*/
	}

	@Override
	public KArrangeUser transform(MiExamArrangePojo pojo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(MiExamArrangePojo pojo, KArrangeUser entity) {
		// TODO Auto-generated method stub
		
	}

}
