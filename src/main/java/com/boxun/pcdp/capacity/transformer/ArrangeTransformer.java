package com.boxun.pcdp.capacity.transformer;

import org.springframework.stereotype.Component;

import com.boxun.estms.util.Const;
import com.boxun.estms.util.DateUtil;
import com.boxun.estms.util.StringUtil;
import com.boxun.pcdp.basic.transformer.ITransformer;
import com.boxun.pcdp.capacity.entity.CEvaluateArrange;
import com.boxun.pcdp.capacity.pojo.ArrangePojo;

@Component("carrangeTransformer")
public class ArrangeTransformer implements ITransformer<CEvaluateArrange, ArrangePojo> {

	@Override
	public ArrangePojo convert(CEvaluateArrange entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CEvaluateArrange transform(ArrangePojo pojo) {
		CEvaluateArrange arrange = new CEvaluateArrange();
		arrange.setAddress(pojo.getAddress());
		if(StringUtil.isNotBlank(pojo.getStartTime())){
			arrange.setStartTime((DateUtil.formatDate(pojo.getStartTime(), Const.DATE_FORMAT_1)));
		}
		return arrange;
	}

	@Override
	public void update(ArrangePojo pojo, CEvaluateArrange entity) {
		entity.setAddress(pojo.getAddress());		
		if(StringUtil.isNotBlank(pojo.getStartTime())){
			entity.setStartTime((DateUtil.formatDate(pojo.getStartTime(), Const.DATE_FORMAT_1)));
		}
	}

}
