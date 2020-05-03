package com.boxun.estms.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.UUID;

public class StringUtil {
	
	public static final String UNDERLINE = "_";
	public static final String COMMA = ",";
	public static final String SEMICOLON = ";";

	public static String trim(String str){
		if(str != null)
			return str.trim();
		return "";
	}
	
	public static boolean isBlank(String str){
		String val = trim(str);
		if("".equals(val))
			return true;
		return false;
	}
	
	public static boolean isNotBlank(String str){
		return !isBlank(str);
	}
	
	public static List<String> split(String source, String delim){
		List<String> result = null;
		if(isNotBlank(source) && isNotBlank(delim)){
			StringTokenizer st = new StringTokenizer(source, delim);
			result = new ArrayList<String>();
			String str = null;
			while(st.hasMoreElements() ){
				str = st.nextElement().toString();
				if(isNotBlank(str)){
					result.add(trim(str));
				}
			}
		}
		return result;
	}
	
	public static List<Long> split4Long(String source, String delim){
		List<Long> result = null;
		if(isNotBlank(source) && isNotBlank(delim)){
			StringTokenizer st = new StringTokenizer(source, delim);
			result = new ArrayList<Long>();
			String str = null;
			while(st.hasMoreElements() ){
				str = st.nextElement().toString();
				if(isNotBlank(str)){
					result.add(Long.parseLong(trim(str)));
				}
			}
		}
		return result;
	}
	
	public static String genOptionCode(int index){
		char a = (char)(65+index);
		return String.valueOf(a);
	}
	
	public static String uuid(){
		 UUID uuid = UUID.randomUUID();
		 return uuid.toString();
	}
	
	public static Double getDouble(Double value, int scale){
		try{
			BigDecimal b = new BigDecimal(value);  
			double f1 = b.setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
			return f1;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return 0d;
	}
	
	public static String getRandColorCode(){ 
		String r,g,b; 
		Random random = new Random(); 
		r = Integer.toHexString(random.nextInt(256)).toUpperCase(); 
		g = Integer.toHexString(random.nextInt(256)).toUpperCase(); 
		b = Integer.toHexString(random.nextInt(256)).toUpperCase(); 

		r = r.length()==1 ? "0" + r : r ; 
		g = g.length()==1 ? "0" + g : g ; 
		b = b.length()==1 ? "0" + b : b ; 

		return r+g+b; 
	}
	
	public static String getVCode(){
		String vcode = "";
        Random random = new Random();
        for(int i=0; i<6; i++){
        	int numberResult = random.nextInt(10);
            int ret = numberResult + 48;
            vcode += (char) ret;
        }
        return vcode;
	}
	
	public static String getExt(String file){
		if(StringUtil.isNotBlank(file)){
			return file.substring(file.lastIndexOf("."));
		}
		return "";
	}
	public static void main(String[] args) {
		System.out.println(StringUtil.getDouble(3.2366d, 3));
	}
}
