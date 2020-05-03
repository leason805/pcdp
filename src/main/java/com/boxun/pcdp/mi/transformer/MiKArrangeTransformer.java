package com.boxun.pcdp.mi.transformer;

import org.springframework.stereotype.Component;

import com.boxun.estms.util.Const;
import com.boxun.estms.util.DateUtil;
import com.boxun.pcdp.basic.transformer.ITransformer;
import com.boxun.pcdp.knowledge.entity.KArrangeUser;
import com.boxun.pcdp.mi.pojo.MiArrangePojo;

@Component("miKArrangeTransformer")
public class MiKArrangeTransformer implements ITransformer<KArrangeUser, MiArrangePojo> {

	@Override
	public MiArrangePojo convert(KArrangeUser entity) {
		return null;
		/*
		MiArrangePojo pojo = new MiArrangePojo();
		pojo.setType("EX");
		pojo.setId(entity.getId());
		pojo.setName(entity.getArrange().getProject().getTitle());
		pojo.setTime(DateUtil.formatDateTime(entity.getArrange().getExamDate(), Const.DATE_FORMAT_2) + " " + entity.getArrange().getExamTime());
		pojo.setAddress(entity.getArrange().getAddress());
		return pojo;
		*/
	}

	@Override
	public KArrangeUser transform(MiArrangePojo pojo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(MiArrangePojo pojo, KArrangeUser entity) {
		// TODO Auto-generated method stub
		
	}

}
