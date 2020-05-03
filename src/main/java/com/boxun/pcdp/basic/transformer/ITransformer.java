package com.boxun.pcdp.basic.transformer;


public interface ITransformer<E, P> {

	public P convert(E entity);
	
	public E transform(P pojo);
	
	public void update(P pojo, E entity);
}
