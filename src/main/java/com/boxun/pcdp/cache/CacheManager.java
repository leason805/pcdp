package com.boxun.pcdp.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.boxun.pcdp.admin.dao.impl.ParamsDaoImpl;
import com.boxun.pcdp.admin.entity.TParams;

@Component("cacheManager")
public class CacheManager {

	private static Object obj = new Object();
	private static CacheManager cacheMaster = null;
	private static Map<String, String> paramCacheMap;
	private static boolean initialized = false;;
	
	public static Logger log = Logger.getLogger(CacheManager.class);
	
	@Autowired
	private ParamsDaoImpl paramsDao;
	
	
	public CacheManager(){
		System.out.println("init cache manager.");
		CacheManager.paramCacheMap = new HashMap<String, String>();
	}
	
	public static CacheManager getInstance(){
		synchronized (obj) {
			if(cacheMaster == null){
				cacheMaster = new CacheManager();
				CacheManager.paramCacheMap = new HashMap<String, String>();
				//init();
			}
			return cacheMaster;
		}
	}
	
	private void init(){
		List<TParams> list = paramsDao.loadAll();
		for(TParams param : list){
			paramCacheMap.put(param.getCode(), param.getValue());
		}
		initialized = true;
		//paramCacheMap.put(Const.UPLOAD_FILE_PATH, "H:\\");
	}
	
	public String get(String key){
		if(!initialized){
			init();
		}
		return paramCacheMap.get(key);
//		if(cacheMaster != null){
//			return paramCacheMap.get(key);
//		}
//		return "";
	}
	
	public void set(String key, String value){
		log.info("setting:" + key + "--" + value);
		if(cacheMaster != null){
			paramCacheMap.put(key, value);
		}
	}

	public static void reset(){
		
		try {
			log.info("Reseting Cache.");
			paramCacheMap.clear();
		} catch (Exception e) {
			log.error("Error while reset cache.", e);
		}
		
	}
}
