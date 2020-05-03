package com.boxun.pcdp.estimate.transformer;

import com.boxun.pcdp.basic.transformer.ITransformer;
import com.boxun.pcdp.estimate.entity.EAssociateItem;
import com.boxun.pcdp.estimate.pojo.IndicatorAssociateItemPojo;

public class IndicatorAssociateItemTransformer implements ITransformer<EAssociateItem, IndicatorAssociateItemPojo> {

	@Override
	public IndicatorAssociateItemPojo convert(EAssociateItem entity) {
		IndicatorAssociateItemPojo pojo = new IndicatorAssociateItemPojo();
		pojo.setId(entity.getId());
		pojo.setItemId(entity.getItemId());
		//pojo.setFormula1(entity.getf);
		return pojo;
	}

	@Override
	public EAssociateItem transform(IndicatorAssociateItemPojo pojo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(IndicatorAssociateItemPojo pojo, EAssociateItem entity) {
		// TODO Auto-generated method stub
		
	}

}
