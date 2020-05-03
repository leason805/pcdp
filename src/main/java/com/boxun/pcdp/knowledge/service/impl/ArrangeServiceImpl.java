package com.boxun.pcdp.knowledge.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boxun.estms.entity.Const;
import com.boxun.estms.util.DESUtil;
import com.boxun.estms.util.MailUtil;
import com.boxun.estms.util.StringUtil;
import com.boxun.pcdp.cache.CacheManager;
import com.boxun.pcdp.knowledge.dao.impl.ArrangeDaoImpl;
import com.boxun.pcdp.knowledge.entity.KArrange;
import com.boxun.pcdp.knowledge.service.IArrangeService;

@Service("karrangeService")
public class ArrangeServiceImpl implements IArrangeService{

	@Autowired
	private ArrangeDaoImpl karrangeDao;
	
	@Autowired
	private CacheManager cacheManager;
	
	@Override
	public void create(KArrange entity) {
		this.karrangeDao.save(entity);
	}

	@Override
	public void update(KArrange entity) {
		this.karrangeDao.saveOrUpdate(entity);
	}

	@Override
	public void delete(KArrange entity) {
		this.karrangeDao.delete(entity);
	}

	@Override
	public KArrange load(Long id) {
		return this.karrangeDao.load(id);
	}

	@Override
	public List<KArrange> list() {
		return this.karrangeDao.loadAll();
	}

	@Override
	public void sendMail(List<String> tolist, List<String> cclist, String title, String text) {
		String mail_host = cacheManager.get(Const.MAIL_HOST);
		String mail_from = cacheManager.get(Const.MAIL_FROM);
		String mail_user = cacheManager.get(Const.MAIL_USER);
		String mail_pwd = cacheManager.get(Const.MAIL_PASSWORD);
		if(StringUtil.isNotBlank(mail_host) && StringUtil.isNotBlank(mail_from) && StringUtil.isNotBlank(mail_user) && StringUtil.isNotBlank(mail_pwd)){
			try {
				mail_pwd = DESUtil.decrypt(mail_pwd);
				boolean sent = MailUtil.send(mail_host, mail_user, mail_pwd, mail_from, tolist, cclist, title, text);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
	}

}
