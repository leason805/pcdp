package com.boxun.pcdp.estimate.transformer;

import org.springframework.stereotype.Component;

import com.boxun.pcdp.basic.transformer.ITransformer;
import com.boxun.pcdp.estimate.entity.EIndicator;
import com.boxun.pcdp.estimate.pojo.IndicatorPojo;

@Component("indicatorTransformer")
public class IndicatorTransformer implements ITransformer<EIndicator, IndicatorPojo> {

	@Override
	public IndicatorPojo convert(EIndicator entity) {
		IndicatorPojo indicator = new IndicatorPojo();
		indicator.setId(entity.getId());
		indicator.setName(entity.getName());
		indicator.setLevel(entity.getLevel());
		indicator.setScore(entity.getScore());
		return indicator;
	}

	@Override
	public EIndicator transform(IndicatorPojo pojo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(IndicatorPojo pojo, EIndicator entity) {
		// TODO Auto-generated method stub
		
	}

}
