package com.boxun.pcdp.estimate.transformer;

import org.springframework.stereotype.Component;

import com.boxun.pcdp.basic.transformer.ITransformer;
import com.boxun.pcdp.estimate.entity.EIndicatorAssociate;
import com.boxun.pcdp.estimate.pojo.IndicatorAssociatePojo;

@Component("indicatorAssociateTransformer")
public class IndicatorAssociateTransformer implements ITransformer<EIndicatorAssociate, IndicatorAssociatePojo> {

	@Override
	public IndicatorAssociatePojo convert(EIndicatorAssociate entity) {
		IndicatorAssociatePojo pojo = new IndicatorAssociatePojo();
		return null;
	}

	@Override
	public EIndicatorAssociate transform(IndicatorAssociatePojo pojo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(IndicatorAssociatePojo pojo, EIndicatorAssociate entity) {
		// TODO Auto-generated method stub
		
	}

}
