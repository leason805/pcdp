package com.boxun.pcdp.knowledge.service.impl;

import java.io.File;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.stereotype.Service;

import com.boxun.estms.entity.Const;
import com.boxun.estms.util.BarCodeUtil;
import com.boxun.pcdp.admin.dao.impl.UserDaoImpl;
import com.boxun.pcdp.admin.entity.TUser;
import com.boxun.pcdp.cache.CacheManager;
import com.boxun.pcdp.knowledge.dao.impl.ArrangeUserDaoImpl;
import com.boxun.pcdp.knowledge.entity.KArrangeUser;
import com.boxun.pcdp.knowledge.service.IArrangeUserService;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;

@Service("karrangeuserService")
public class ArrangeUserServiceImpl implements IArrangeUserService{

	@Autowired
	private ArrangeUserDaoImpl karrangeuserDao;
	
	@Autowired
	private UserDaoImpl userDao;
	
	@Autowired
	private CacheManager cacheManager;
	
	@Override
	public void create(KArrangeUser entity) {
		this.karrangeuserDao.save(entity);
	}

	@Override
	public void update(KArrangeUser entity) {
		this.karrangeuserDao.saveOrUpdate(entity);
	}

	@Override
	public void delete(KArrangeUser entity) {
		this.karrangeuserDao.delete(entity);
	}

	@Override
	public KArrangeUser load(Long id) {
		return this.karrangeuserDao.load(id);
	}

	@Override
	public List<KArrangeUser> list() {
		return this.karrangeuserDao.loadAll();
	}

	@Override
	public Long getSize4Arrange(Long arrangeId, Boolean assigned) {
		
		
		DetachedCriteria criteria = this.userDao.createDetachedCriteria();
		//criteria.add(Restrictions.eq("userType", Const.UserType.PILOT));
		
		if(assigned){
			criteria.add(Restrictions.sqlRestriction(" id in (select user_id from KN_ARRANGE_USER where user_id is not null and ARRANGE_ID = " + arrangeId + ")"));
		}
		else{
			criteria.add(Restrictions.sqlRestriction(" id not in (select user_id from KN_ARRANGE_USER where user_id is not null and ARRANGE_ID = " + arrangeId + ")"));
		}
		
		return this.userDao.getRowCount(criteria);
	}

	@Override
	public List<TUser> listUsers4Arrange(Long arrangeId, Boolean assigned) {
		
		List<Map<String, Object>> objs = this.userDao.getQueryResultToListMap("select user_id from KN_ARRANGE_USER where user_id is not null and ARRANGE_ID = " + arrangeId);
		
		DetachedCriteria criteria = this.userDao.createDetachedCriteria();
		//criteria.add(Restrictions.eq("userType", Const.UserType.PILOT));
		
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
		
		if(assigned){
			
			
//			if(objs.isEmpty()){
//				criteria.add(Restrictions.in("id", new Object[]{-1l}));
//			}
//			else{
//				
//				
//				criteria.add(Restrictions.in("id", ids));
//			}
			
			criteria.add(Restrictions.in("id", ids));
			//criteria.add(Restrictions.sqlRestriction(" id in (select user_id from KN_ARRANGE_USER where user_id is not null and ARRANGE_ID = " + arrangeId + ")"));
		}
		else{
//			if(objs.isEmpty()){
//				criteria.add(Restrictions.not( Restrictions.in("id", new Object[]{-1l})));
//			}
//			else{
//				criteria.add(Restrictions.not( Restrictions.in("id", objs.toArray())));
//			}
			
			criteria.add(Restrictions.not( Restrictions.in("id", ids)));
			criteria.add(Restrictions.ne("status", Const.UserStatus.DELETE));
			
			//criteria.add(Restrictions.sqlRestriction(" id not in (select user_id from KN_ARRANGE_USER where user_id is not null and ARRANGE_ID = " + arrangeId + ")"));
		}
		return this.userDao.findByCriteria(criteria);
	}

	@Override
	public List<KArrangeUser> list(Long arrangeId) {
		DetachedCriteria criteria = this.karrangeuserDao.createDetachedCriteria();
		criteria.add(Restrictions.eq("arrange.id", arrangeId));
		criteria.add(Restrictions.isNotNull("user.id"));
		return this.karrangeuserDao.findByCriteria(criteria);
	}

	@Override
	public String generateBarCode(Long userId, String text) {
		String format = "gif"; 
		int width = 200; 
	    int height = 200;
	    
		String barcodeFolder = cacheManager.get(Const.BARCODE_FILE_PATH);
		Long curr = System.currentTimeMillis();
		String newFileName = "E" + Const.UNDER_DELIMITER + userId + Const.UNDER_DELIMITER + curr + "." + format;
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
	public List<KArrangeUser> list4User(Long userId) {
		DetachedCriteria criteria = this.karrangeuserDao.createDetachedCriteria();
		criteria.add(Restrictions.eq("user.id", userId));
		return this.karrangeuserDao.findByCriteria(criteria);
	}

	@Override
	public KArrangeUser load(Long id, String vcode) {
		DetachedCriteria criteria = this.karrangeuserDao.createDetachedCriteria();
		criteria.add(Restrictions.eq("id", id));
		criteria.add(Restrictions.eq("vcode", vcode));
		List<KArrangeUser> list =this.karrangeuserDao.findByCriteria(criteria);
		if(list != null && !list.isEmpty()){
			return list.get(0);
		}
		return null;
	}

	@Override
	public KArrangeUser loadByUUID(String uuid) {
		DetachedCriteria criteria = this.karrangeuserDao.createDetachedCriteria();
		criteria.add(Restrictions.eq("ucode", uuid));
		List<KArrangeUser> list =this.karrangeuserDao.findByCriteria(criteria);
		if(list != null && !list.isEmpty()){
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<KArrangeUser> list4User(Long userId, Boolean completed) {
		DetachedCriteria criteria = this.karrangeuserDao.createDetachedCriteria();
		criteria.add(Restrictions.eq("user.id", userId));
		if(completed){
			criteria.add(Restrictions.eq("status", Const.ArrangeUserStatus.COMPLETED));
			criteria.addOrder(Order.desc("completeTime"));
		}
		else{
			criteria.add(Restrictions.ne("status", Const.ArrangeUserStatus.COMPLETED));
		}
		
		return this.karrangeuserDao.findByCriteria(criteria);
	}

	@Override
	public Long getSize4Started(Long arrangeId) {
		DetachedCriteria criteria = this.karrangeuserDao.createDetachedCriteria();
		criteria.add(Restrictions.eq("arrange.id", arrangeId));
		criteria.add(Restrictions.ne("status", Const.ArrangeUserStatus.DRAFT));
		
		return this.userDao.getRowCount(criteria);
	}

	@Override
	public void delete(Long arrangeId) {
		final String id = arrangeId + "";
		this.karrangeuserDao.getHibernateTemplate().execute(new HibernateCallback<Object>(){   
            public Object doInHibernate(Session session) throws HibernateException{ 
            	String sql = "DELETE FROM kn_arrange_user WHERE ARRANGE_ID = " + id;
            	session.createSQLQuery(sql).executeUpdate(); 
            	session.flush();
            	return null;
            }
		});
	}

}
