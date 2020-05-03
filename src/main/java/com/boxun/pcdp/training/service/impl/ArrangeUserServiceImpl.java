package com.boxun.pcdp.training.service.impl;

import java.io.File;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boxun.estms.entity.Const;
import com.boxun.estms.util.BarCodeUtil;
import com.boxun.pcdp.admin.dao.impl.UserDaoImpl;
import com.boxun.pcdp.admin.entity.TUser;
import com.boxun.pcdp.cache.CacheManager;
import com.boxun.pcdp.training.dao.impl.ArrangeUserDaoImpl;
import com.boxun.pcdp.training.entity.TArrangeUser;
import com.boxun.pcdp.training.service.IArrangeUserService;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;

@Service("tarrangeuserService")
public class ArrangeUserServiceImpl implements IArrangeUserService{

	@Autowired
	private ArrangeUserDaoImpl tarrangeuserDao;
	
	@Autowired
	private UserDaoImpl userDao;
	
	@Autowired
	private CacheManager cacheManager;
	
	@Override
	public void create(TArrangeUser entity) {
		this.tarrangeuserDao.save(entity);
	}

	@Override
	public void update(TArrangeUser entity) {
		this.tarrangeuserDao.saveOrUpdate(entity);
	}

	@Override
	public void delete(TArrangeUser entity) {
		this.tarrangeuserDao.delete(entity);
	}

	@Override
	public TArrangeUser load(Long id) {
		return this.tarrangeuserDao.load(id);
	}

	@Override
	public List<TArrangeUser> list() {
		return this.tarrangeuserDao.loadAll();
	}

	@Override
	public Long getSize4Arrange(Long arrangeId, Boolean assigned) {
		DetachedCriteria criteria = this.userDao.createDetachedCriteria();
		//criteria.add(Restrictions.eq("userType", Const.UserType.PILOT));
		
		if(assigned){
			criteria.add(Restrictions.sqlRestriction(" id in (select user_id from TR_ARRANGE_USER where user_id is not null and ARRANGE_ID = " + arrangeId + ")"));
		}
		else{
			criteria.add(Restrictions.sqlRestriction(" id not in (select user_id from TR_ARRANGE_USER where user_id is not null and ARRANGE_ID = " + arrangeId + ")"));
		}
		
		return this.userDao.getRowCount(criteria);
	}

	@Override
	public List<TUser> listUsers4Arrange(Long arrangeId, Boolean assigned) {
		
		List<Map<String, Object>> objs = this.userDao.getQueryResultToListMap("select user_id from TR_ARRANGE_USER where user_id is not null and ARRANGE_ID = " + arrangeId);
		List<Object> ids = new ArrayList<Object>();
		if(objs == null || objs.isEmpty()){
			ids.add(-1l);
		}
		else{
			for(Map<String, Object> map : objs){
				for(Object obj : map.values()){
					ids.add(((BigInteger)obj).longValue());
				}
			}
		}
		
		DetachedCriteria criteria = this.userDao.createDetachedCriteria();
		//criteria.add(Restrictions.eq("userType", Const.UserType.PILOT));
		
		if(assigned){
			criteria.add(Restrictions.in("id", ids));
			//criteria.add(Restrictions.sqlRestriction(" id in (select user_id from TR_ARRANGE_USER where user_id is not null and ARRANGE_ID = " + arrangeId + ")"));
		}
		else{
			criteria.add(Restrictions.not( Restrictions.in("id", ids)));
			//criteria.add(Restrictions.sqlRestriction(" id not in (select user_id from TR_ARRANGE_USER where user_id is not null and ARRANGE_ID = " + arrangeId + ")"));
		}
		return this.userDao.findByCriteria(criteria);
	}

	@Override
	public String generateBarCode(Long userId, String text) {
		String format = "gif"; 
		int width = 200; 
	    int height = 200;
	    
		String barcodeFolder = cacheManager.get(Const.BARCODE_FILE_PATH);
		Long curr = System.currentTimeMillis();
		String newFileName = "T" + Const.UNDER_DELIMITER + userId + Const.UNDER_DELIMITER + curr + "." + format;
		String path = barcodeFolder + newFileName;

		Hashtable hints = new Hashtable(); 
	    //内容所使用编码 
	    hints.put(EncodeHintType.CHARACTER_SET, "utf-8"); 
	    BitMatrix bitMatrix;
		try {
			bitMatrix = new MultiFormatWriter().encode(text,BarcodeFormat.QR_CODE, width, height, hints);
			File outputFile = new File(path); 
	        BarCodeUtil.writeToFile(bitMatrix, format, outputFile); 
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return newFileName;
	}

	@Override
	public List<TArrangeUser> list(Long arrangeId) {
		DetachedCriteria criteria = this.tarrangeuserDao.createDetachedCriteria();
		criteria.add(Restrictions.eq("arrange.id", arrangeId));
		criteria.add(Restrictions.isNotNull("user.id"));
		return this.tarrangeuserDao.findByCriteria(criteria);
	}

	@Override
	public List<TArrangeUser> list4User(Long userId) {
		DetachedCriteria criteria = this.tarrangeuserDao.createDetachedCriteria();
		criteria.add(Restrictions.eq("user.id", userId));
		return this.tarrangeuserDao.findByCriteria(criteria);
	}

	@Override
	public TArrangeUser load(Long id, String vcode) {
		DetachedCriteria criteria = this.tarrangeuserDao.createDetachedCriteria();
		criteria.add(Restrictions.eq("id", id));
		criteria.add(Restrictions.eq("vcode", vcode));
		List<TArrangeUser> list =this.tarrangeuserDao.findByCriteria(criteria);
		if(list != null && !list.isEmpty()){
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<TArrangeUser> list4User(Long userId, Date startDate,Date endDate) {
		DetachedCriteria criteria = this.tarrangeuserDao.createDetachedCriteria();
		criteria.createAlias("arrange", "arr"); 
		criteria.add(Restrictions.eq("user.id", userId));
		if(startDate != null){
			criteria.add(Restrictions.ge("arr.courseDate", startDate));
		}
		if(endDate != null){
			criteria.add(Restrictions.le("arr.courseDate", endDate));
		}
		return this.tarrangeuserDao.findByCriteria(criteria);
	}

	@Override
	public TArrangeUser loadByUUID(String uuid) {
		DetachedCriteria criteria = this.tarrangeuserDao.createDetachedCriteria();
		criteria.add(Restrictions.eq("ucode", uuid));
		List<TArrangeUser> list =this.tarrangeuserDao.findByCriteria(criteria);
		if(list != null && !list.isEmpty()){
			return list.get(0);
		}
		return null;
	}

}
