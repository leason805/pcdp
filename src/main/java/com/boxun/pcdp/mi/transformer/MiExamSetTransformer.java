package com.boxun.pcdp.mi.transformer;

import java.util.List;

import org.springframework.stereotype.Component;

import com.boxun.pcdp.basic.transformer.ITransformer;
import com.boxun.pcdp.knowledge.entity.KOption;
import com.boxun.pcdp.knowledge.entity.KQuestion;
import com.boxun.pcdp.mi.pojo.MiExamOptionPojo;
import com.boxun.pcdp.mi.pojo.MiExamQuestionPojo;
import com.boxun.pcdp.mi.pojo.MiExamSetPojo;

@Component("miExamSetTransformer")
public class MiExamSetTransformer implements ITransformer<KQuestion, MiExamSetPojo> {

	@Override
	public MiExamSetPojo convert(KQuestion entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public KQuestion transform(MiExamSetPojo pojo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(MiExamSetPojo pojo, KQuestion entity) {
		// TODO Auto-generated method stub
		
	}
	
/*
	public MiExamSetPojo convert(List<KQuestion> entities) {
		MiExamSetPojo pojo = new MiExamSetPojo();
		if(entities != null && !entities.isEmpty()){
			pojo.setProjectId(entities.get(0).getSection().getProject().getId());
			pojo.setProjectName(entities.get(0).getSection().getProject().getTitle());
			for(KQuestion question : entities){
				MiExamQuestionPojo quesPojo = new MiExamQuestionPojo();
				quesPojo.setId(question.getId());
				quesPojo.setQuestion(question.getQuestion());
				quesPojo.setQuestionType(question.getQuestionType());
				for(KOption option : question.getOptions()){
					MiExamOptionPojo optPojo = new MiExamOptionPojo();
					optPojo.setId(option.getId());
					optPojo.setCode(option.getCode());
					optPojo.setContent(option.getContent());
					optPojo.setAnswerType(option.getAnswerType());
					quesPojo.addOption(optPojo);
				}
				pojo.addQuestion(quesPojo);
			}
		}
		
		return pojo;
	}
*/
}
