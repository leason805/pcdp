package com.boxun.pcdp.estimate.transformer;

import org.springframework.stereotype.Component;

import com.boxun.pcdp.basic.transformer.ITransformer;
import com.boxun.pcdp.estimate.entity.EIndicatorImportance;
import com.boxun.pcdp.estimate.pojo.IndicatorImpoPojo;

@Component("indicatorImpoTransformer")
public class IndicatorImpoTransformer implements ITransformer<EIndicatorImportance, IndicatorImpoPojo> {

	@Override
	public IndicatorImpoPojo convert(EIndicatorImportance entity) {
		IndicatorImpoPojo pojo = new IndicatorImpoPojo();
		pojo.setId(entity.getId());
		if(entity.getParent() != null){
			pojo.setParentId(entity.getParent().getId());
		}
		if(entity.getFirst() != null){
			pojo.setFirstId(entity.getFirst().getId());
		}
		if(entity.getSecond() != null){
			pojo.setSecondId(entity.getSecond().getId());
		}
		pojo.setRating(entity.getRating());
		pojo.setOrder(entity.getOrder());
		return pojo;
	}

	@Override
	public EIndicatorImportance transform(IndicatorImpoPojo pojo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(IndicatorImpoPojo pojo, EIndicatorImportance entity) {
		// TODO Auto-generated method stub
		
	}

}
