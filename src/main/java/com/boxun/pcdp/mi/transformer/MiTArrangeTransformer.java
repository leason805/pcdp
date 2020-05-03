package com.boxun.pcdp.mi.transformer;

import org.springframework.stereotype.Component;

import com.boxun.estms.util.Const;
import com.boxun.estms.util.DateUtil;
import com.boxun.pcdp.basic.transformer.ITransformer;
import com.boxun.pcdp.mi.pojo.MiArrangePojo;
import com.boxun.pcdp.training.entity.TArrangeUser;

@Component("miTArrangeTransformer")
public class MiTArrangeTransformer implements ITransformer<TArrangeUser, MiArrangePojo> {

	@Override
	public MiArrangePojo convert(TArrangeUser entity) {
		MiArrangePojo pojo = new MiArrangePojo();
		pojo.setType("TR");
		pojo.setId(entity.getId());
		pojo.setName(entity.getArrange().getCourse().getName());
		pojo.setTime(DateUtil.formatDateTime(entity.getArrange().getCourseDate(), Const.DATE_FORMAT_2) + " " + entity.getArrange().getCourseTime());
		pojo.setAddress(entity.getArrange().getAddress());
		return pojo;
	}

	@Override
	public TArrangeUser transform(MiArrangePojo pojo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(MiArrangePojo pojo, TArrangeUser entity) {
		// TODO Auto-generated method stub
		
	}

}
