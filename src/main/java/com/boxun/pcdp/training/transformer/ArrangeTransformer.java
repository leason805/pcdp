package com.boxun.pcdp.training.transformer;

import org.springframework.stereotype.Component;

import com.boxun.estms.util.Const;
import com.boxun.estms.util.DateUtil;
import com.boxun.estms.util.StringUtil;
import com.boxun.pcdp.basic.transformer.ITransformer;
import com.boxun.pcdp.training.entity.TArrange;
import com.boxun.pcdp.training.pojo.ArrangePojo;

@Component("tarrangeTransformer")
public class ArrangeTransformer implements ITransformer<TArrange, ArrangePojo> {

	@Override
	public ArrangePojo convert(TArrange entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TArrange transform(ArrangePojo pojo) {
		TArrange arrange = new TArrange();
		arrange.setAddress(pojo.getAddress());
		arrange.setCourseTime(pojo.getCourseTime());
		if(StringUtil.isNotBlank(pojo.getCourseDate())){
			arrange.setCourseDate((DateUtil.formatDate(pojo.getCourseDate(), Const.DATE_FORMAT_2)));
		}
		return arrange;
	}

	@Override
	public void update(ArrangePojo pojo, TArrange entity) {
		entity.setAddress(pojo.getAddress());
		entity.setCourseTime(pojo.getCourseTime());
		if(StringUtil.isNotBlank(pojo.getCourseDate())){
			entity.setCourseDate((DateUtil.formatDate(pojo.getCourseDate(), Const.DATE_FORMAT_2)));
		}
	}

}
