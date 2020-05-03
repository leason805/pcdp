package com.boxun.estms.util;

import java.lang.reflect.Field;
import java.util.Comparator;
import java.util.Date;

public class SortComparator implements Comparator{

	public SortComparator(String fieldName) {
		super();
		this.fieldName = fieldName;
	}

	public SortComparator(String fieldName, String orderBy) {
		this.fieldName = fieldName;
		this.orderBy = orderBy;
	}
	
	private String fieldName;
	
	private String orderBy;
	
//	public String getFieldName() {
//		return fieldName;
//	}
//
//	public void setFieldName(String fieldName) {
//		this.fieldName = fieldName;
//	}
	
	@Override
	public int compare(Object o1, Object o2) {
		try{
			Class o1Class = o1.getClass();
			Class o2Class = o2.getClass();
			Object field1 = null;
			Field[] o1Fields = o1Class.getDeclaredFields();
			Object field2 = null;
			Field[] o2Fields = o2Class.getDeclaredFields();
			for(Field field : o1Fields){
				if(field.getName().equals(fieldName)){
					field.setAccessible(true);
					field1 = field.get(o1);
					break;
				}
			}
			for(Field field : o2Fields){
				if(field.getName().equals(fieldName)){
					field.setAccessible(true);
					field2 = field.get(o2);
					break;
				}
			}

			if(field1 != null && field2 != null){
				if(field1 instanceof String){
					if("desc".equals(orderBy)){
						return ((String)field2).compareTo((String)field1);
					}
					return ((String)field1).compareTo((String)field2);
				}
				else if(field1 instanceof Integer){
					if("desc".equals(orderBy)){
						return ((Integer)field2).compareTo((Integer)field1);
					}
					return ((Integer)field1).compareTo((Integer)field2);
				}
				else if(field1 instanceof Long){
					if("desc".equals(orderBy)){
						return ((Long)field2).compareTo((Long)field1);
					}
					return ((Long)field1).compareTo((Long)field2);
				}
				else if(field1 instanceof Float){
					if("desc".equals(orderBy)){
						return ((Float)field2).compareTo((Float)field1);
					}
					return ((Float)field1).compareTo((Float)field2);
				}
				else if(field1 instanceof Date){
					if("desc".equals(orderBy)){
						return ((Date)field2).compareTo((Date)field1);
					}
					return ((Date)field1).compareTo((Date)field2);
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return 0;
	}

//	public String getOrderBy() {
//		return orderBy;
//	}
//
//	public void setOrderBy(String orderBy) {
//		this.orderBy = orderBy;
//	}
	
}
