package com.boxun.pcdp.archive.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.boxun.estms.entity.Const;
import com.boxun.estms.util.SortUtil;
import com.boxun.estms.util.StringUtil;
import com.boxun.pcdp.archive.entity.AUserInfo;
import com.boxun.pcdp.archive.pojo.TechStaticsPojo;
import com.boxun.pcdp.archive.pojo.TypeStaticsPojo;
import com.boxun.pcdp.archive.service.IUserInfoService;
import com.boxun.pcdp.basic.controller.BaseController;
import com.boxun.pcdp.cache.CacheManager;

@Controller
@RequestMapping("/system/archive/statics")
public class StaticsController extends BaseController{

	@Autowired
	private IUserInfoService userinfoService;
	
	@Autowired
	private CacheManager cacheManager;
	
	@RequestMapping("/list")
	public String list(ModelMap modelMap, Long companyId){
//		if(id != null){
//			AExperience entity = experienceService.load(id);
//			ExperiencePojo model = experienceTransformer.convert(entity);
//			modelMap.addAttribute("model", model);
//		}
		
		List<AUserInfo> userList = this.userinfoService.list();
		
		String QIANPAIZULI = cacheManager.get(Const.QIANPAIZULI);
		String FANGXINGQIANPAIYUAN = cacheManager.get(Const.FANGXINGQIANPAIYUAN);
		String QIANPAIXUEYUAN = cacheManager.get(Const.QIANPAIXUEYUAN);
		
		Integer zuliSize = 0;
		Integer nonzuliSize = 0;
		List<TypeStaticsPojo> types = new ArrayList<TypeStaticsPojo>();
		List<TechStaticsPojo> techs = new ArrayList<TechStaticsPojo>();
		for(AUserInfo inf : userList){
			if(inf.getJobInfo() != null && inf.getJobInfo().getTechLevel() != null){
				TechStaticsPojo pojo = null;
				for(TechStaticsPojo tp : techs){
					if(inf.getJobInfo().getTechLevel().getName().equals(tp.getName())){
						pojo = tp;
						break;
					}
				}
				if(pojo == null){
					pojo = new TechStaticsPojo();
					pojo.setName(inf.getJobInfo().getTechLevel().getName());
					pojo.setColor(StringUtil.getRandColorCode());
					techs.add(pojo);
				}
				pojo.increaseSize();
				
				if(inf.getJobInfo().getTechLevel().getName().equals(QIANPAIZULI)){
					zuliSize++;
				}
				else{
					if(!inf.getJobInfo().getTechLevel().getName().equals(QIANPAIXUEYUAN)){
						nonzuliSize++;
					}
				}
	
			}
		}
		
		SortUtil.sort(techs, "size");

		TypeStaticsPojo zuli = new TypeStaticsPojo();
		zuli.setName(QIANPAIZULI);
		zuli.setColor(StringUtil.getRandColorCode());
		zuli.setSize(zuliSize);
		types.add(zuli);
		
		TypeStaticsPojo nonzuli = new TypeStaticsPojo();
		nonzuli.setName(FANGXINGQIANPAIYUAN);
		nonzuli.setColor(StringUtil.getRandColorCode());
		nonzuli.setSize(nonzuliSize);
		types.add(nonzuli);
		
		modelMap.addAttribute("types", types);
		
		modelMap.addAttribute("techs", techs);
		return "/archive/statics/list";
	}
}
