package com.boxun.pcdp.estimate.transformer;

import org.springframework.stereotype.Component;

import com.boxun.pcdp.basic.transformer.ITransformer;
import com.boxun.pcdp.estimate.entity.EIndicator;
import com.boxun.pcdp.estimate.pojo.IndicatorScorePojo;

@Component("indicatorScoreTransformer")
public class IndicatorScoreTransformer implements ITransformer<EIndicator, IndicatorScorePojo> {

	@Override
	public IndicatorScorePojo convert(EIndicator entity) {
		IndicatorScorePojo pojo = new IndicatorScorePojo();
		pojo.setId(entity.getId());
		pojo.setName(entity.getName());
		pojo.setMandatory(entity.getMandatory());
		pojo.setSequence(entity.getSequence());
		pojo.setScore(entity.getScore());
		pojo.setLevel(entity.getLevel());
		return pojo;
	}

	@Override
	public EIndicator transform(IndicatorScorePojo pojo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(IndicatorScorePojo pojo, EIndicator entity) {
		// TODO Auto-generated method stub
		
	}

}
