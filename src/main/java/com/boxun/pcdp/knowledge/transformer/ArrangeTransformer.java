package com.boxun.pcdp.knowledge.transformer;

import org.springframework.stereotype.Component;

import com.boxun.estms.util.Const;
import com.boxun.estms.util.DateUtil;
import com.boxun.estms.util.StringUtil;
import com.boxun.pcdp.basic.transformer.ITransformer;
import com.boxun.pcdp.knowledge.entity.KArrange;
import com.boxun.pcdp.knowledge.pojo.ArrangePojo;

@Component("karrangeTransformer")
public class ArrangeTransformer implements ITransformer<KArrange, ArrangePojo> {

	@Override
	public ArrangePojo convert(KArrange entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public KArrange transform(ArrangePojo pojo) {
		KArrange arrange = new KArrange();
		arrange.setAddress(pojo.getAddress());
		arrange.setExamTime(pojo.getExamTime());
		if(StringUtil.isNotBlank(pojo.getExamDate())){
			arrange.setExamDate((DateUtil.formatDate(pojo.getExamDate(), Const.DATE_FORMAT_1)));
		}
		return arrange;
	}

	@Override
	public void update(ArrangePojo pojo, KArrange entity) {
		entity.setAddress(pojo.getAddress());
		entity.setExamTime(pojo.getExamTime());
		if(StringUtil.isNotBlank(pojo.getExamDate())){
			entity.setExamDate((DateUtil.formatDate(pojo.getExamDate(), Const.DATE_FORMAT_1)));
		}
	}

}
