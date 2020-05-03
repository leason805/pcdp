package com.boxun.pcdp.archive.service.impl;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.boxun.estms.entity.Const;
import com.boxun.estms.util.FileUtil;
import com.boxun.estms.util.StringUtil;
import com.boxun.pcdp.admin.dao.impl.UserDaoImpl;
import com.boxun.pcdp.admin.entity.TUser;
import com.boxun.pcdp.archive.dao.impl.CertCategoryDaoImpl;
import com.boxun.pcdp.archive.dao.impl.CertificationDaoImpl;
import com.boxun.pcdp.archive.entity.ACertCategory;
import com.boxun.pcdp.archive.entity.ACertification;
import com.boxun.pcdp.archive.service.ICertificationService;
import com.boxun.pcdp.cache.CacheManager;

@Service("certificationService")
public class CertificationServiceImpl implements ICertificationService{

	@Autowired
	private CertificationDaoImpl certificationDao;
	
	@Autowired
	private UserDaoImpl userDao;

	@Autowired
	private CacheManager cacheManager;
	
	
	@Autowired
	private CertCategoryDaoImpl certcategoryDao;
	
	@Override
	public void create(ACertification entity) {
		this.certificationDao.save(entity);
	}

	@Override
	public void update(ACertification entity) {
		this.certificationDao.saveOrUpdate(entity);
	}

	@Override
	public void delete(ACertification entity) {
		this.certificationDao.delete(entity);
	}

	@Override
	public ACertification load(Long id) {
		return this.certificationDao.load(id);
	}

	@Override
	public List<ACertification> list() {
		return this.certificationDao.loadAll();
	}

	@Override
	public Long createFile(MultipartFile file, Long userId, Long categoryId) {
		ACertification cert = null;
		try{
			String oriFileName = file.getOriginalFilename();
			 String uploadFolder = cacheManager.get(Const.UPLOAD_FILE_PATH);//CacheManager.getInstance().get(Const.UPLOAD_FILE_PATH);
			 Long curr = System.currentTimeMillis();
			 if(StringUtil.isNotBlank(oriFileName)){  //如果名称不为“”,说明该文件存在，否则说明该文件不存在  
				 String newFileName = userId + Const.UNDER_DELIMITER + categoryId + Const.UNDER_DELIMITER + curr + StringUtil.getExt(oriFileName);
				 String path = uploadFolder + userId;
				 FileUtil.createDir(path);
				 path =	path + System.getProperty("file.separator") + newFileName;  
		         File uploadlFile = new File(path);
		         file.transferTo(uploadlFile);
		         
		         cert = new ACertification();
		         cert.setStatus(Const.CertificationStatus.PENDING);
		         cert.setFileName(newFileName);
		         cert.setCreateTime(new Date());
		         if(userId != null){
		        	 TUser user =  this.userDao.load(userId);
		        	 cert.setUser(user);
		         }
		         if(categoryId != null){
		        	 ACertCategory category = this.certcategoryDao.load(categoryId);
		        	 cert.setCategory(category);
		         }
		         this.certificationDao.save(cert);
			 }
		}
		catch(Exception e){
			e.printStackTrace();
			return 0l;
		}
		if(cert != null){
			return cert.getId();
		}
		return 0l;
	}


	@Override
	public List<ACertification> list(Long userId) {
		DetachedCriteria criteria = this.certificationDao.createDetachedCriteria();
		criteria.add(Restrictions.eq("user.id", userId));
		return this.certificationDao.findByCriteria(criteria);
	}

	@Override
	public List<ACertification> list(Long userId, List<Long> ids) {
		DetachedCriteria criteria = this.certificationDao.createDetachedCriteria();
		criteria.add(Restrictions.eq("user.id", userId));
		criteria.add(Restrictions.in("category.id", ids));
		return this.certificationDao.findByCriteria(criteria);
	}

}
