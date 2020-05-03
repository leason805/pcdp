package com.boxun.pcdp.mi.transformer;

import org.springframework.stereotype.Component;

import com.boxun.estms.util.DateUtil;
import com.boxun.pcdp.basic.transformer.ITransformer;
import com.boxun.pcdp.mi.pojo.MiTrainingArrangePojo;
import com.boxun.pcdp.training.entity.TArrangeUser;

@Component("miTrainingTransformer")
public class MiTrainingTransformer implements ITransformer<TArrangeUser, MiTrainingArrangePojo> {

	@Override
	public MiTrainingArrangePojo convert(TArrangeUser entity) {
		MiTrainingArrangePojo pojo = new MiTrainingArrangePojo();
		pojo.setId(entity.getId());
		pojo.setName(entity.getArrange().getCourse().getName());
		if(entity.getArrange().getCourseDate() != null){
			pojo.setDate(DateUtil.formatDateTime(entity.getArrange().getCourseDate(), com.boxun.estms.util.Const.DATE_FORMAT_2));
		}
		pojo.setTime(entity.getArrange().getCourseTime());
		pojo.setAddress(entity.getArrange().getAddress());
		pojo.setDescription(entity.getArrange().getCourse().getDescription());
		pojo.setStatus(entity.getStatus().toString());
		return pojo;
	}

	@Override
	public TArrangeUser transform(MiTrainingArrangePojo pojo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(MiTrainingArrangePojo pojo, TArrangeUser entity) {
		// TODO Auto-generated method stub
		
	}

}
