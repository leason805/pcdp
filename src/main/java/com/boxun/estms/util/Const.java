package com.boxun.estms.util;

import java.io.Serializable;

public class Const {

	public static final String DATE_FORMAT_0= "yyyyMMddHHmmss";
	public static final String DATE_FORMAT_1= "yyyy-MM-dd HH:mm";
	public static final String DATE_FORMAT_2= "yyyy-MM-dd";
	public static final String DATE_FORMAT_3= "yyyyMMdd";
	
	public static final String CACHE_LIST = "CACHE_LIST";
	public static final String TOPIC_SEQUENCE = "TOPIC_SEQUENCE";
	public static final String TOPIC_LINK = "TOPIC_LINK";
	public static final String TOPIC_LOOP_SIZE = "TOPIC_LOOP_SIZE";
	public static final String JOB_TOGGLE = "JOB_TOGGLE";
	public static final String FILE_GENERATOR = "FILE_GENERATOR";
	public static final String DB_PROCESS = "DB_PROCESS";
	public static final String MASTER_NAMES = "MASTER_NAMES";
	public static final String HTML_FILES = "HTML_FILES";
	public static final String U_TOKEN = "U_TOKEN";
	
	public enum ChargeStatus implements Serializable{
		YES,
		NO;
	}
}
