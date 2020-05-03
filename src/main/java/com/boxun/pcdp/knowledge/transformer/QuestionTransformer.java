package com.boxun.pcdp.knowledge.transformer;

import org.springframework.stereotype.Component;

import com.boxun.pcdp.basic.transformer.ITransformer;
import com.boxun.pcdp.knowledge.entity.KQuestion;
import com.boxun.pcdp.knowledge.pojo.QuestionPojo;

@Component("questionTransformer")
public class QuestionTransformer implements ITransformer<KQuestion, QuestionPojo> {

	@Override
	public QuestionPojo convert(KQuestion entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public KQuestion transform(QuestionPojo pojo) {
		KQuestion question = new KQuestion();
		question.setQuestion(pojo.getQuestion());
		question.setQuestionType(pojo.getQuestionType());
		return question;
	}

	@Override
	public void update(QuestionPojo pojo, KQuestion entity) {
		// TODO Auto-generated method stub
		
	}

}
