package com.boxun.pcdp.knowledge.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.boxun.estms.entity.Const;
import com.boxun.estms.util.StringUtil;
import com.boxun.pcdp.admin.entity.TUser;
import com.boxun.pcdp.admin.service.IUserService;
import com.boxun.pcdp.archive.entity.AExperience;
import com.boxun.pcdp.basic.controller.BaseController;
import com.boxun.pcdp.cache.CacheManager;
import com.boxun.pcdp.knowledge.entity.KArrange;
import com.boxun.pcdp.knowledge.entity.KArrangeUser;
import com.boxun.pcdp.knowledge.entity.KPaper;
import com.boxun.pcdp.knowledge.pojo.ArrangePojo;
import com.boxun.pcdp.knowledge.service.IArrangeService;
import com.boxun.pcdp.knowledge.service.IArrangeUserService;
import com.boxun.pcdp.knowledge.service.IPaperService;
import com.boxun.pcdp.knowledge.transformer.ArrangeTransformer;

@Controller("KArrange")
@RequestMapping("/system/knowledge/arrange")
public class ArrangeController extends BaseController{

private static final Logger LOGGER = Logger.getLogger(ArrangeController.class);
	
	@Autowired
	private IArrangeService karrangeService;
	
	@Autowired
	private IArrangeUserService karrangeuserService;
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private IPaperService paperService;
	
	@Autowired
	private ArrangeTransformer karrangeTransformer;
	
	@Autowired
	private CacheManager cacheManager;
	
	@RequestMapping("/list")
	public String list(ModelMap modelMap){
		return "/knowledge/arrange/list";
	}
	
	@RequestMapping("/show/{id}")
	public String show(ModelMap modelMap, @PathVariable Long id){
		
		List<KPaper> papers = this.paperService.list();
		modelMap.addAttribute("papers", papers);
		
		List<TUser> coachers = this.userService.list();
		modelMap.addAttribute("users", coachers);
		
		LOGGER.info("Get show id: " + id);
		if(id != null){
			KArrange model = karrangeService.load(id);
			modelMap.addAttribute("model", model);
		}
		
		return "/knowledge/arrange/show";
	}
	
	@RequestMapping("/listdata")
	public @ResponseBody Map<String, Object> listData(){
		LOGGER.info("Get list data.");
		List<KArrange> listdata = karrangeService.list();
		Map<String, Object> map  = new HashMap<String, Object>();
		map.put("sEcho", 1);  
		map.put("iTotalRecords", listdata.size());  
        map.put("iTotalDisplayRecords", listdata.size());  
		map.put("aaData", listdata);
		
		return map;
	}
	
	@RequestMapping("/create")
	//public String create(ModelMap modelMap, KArrange model, String userIds, Long projectId, Long userId ){
	public String create(ModelMap modelMap, ArrangePojo model ){
		KArrange arrange = this.karrangeTransformer.transform(model);
		if(model != null && model.getPaperId() != null&& model.getPaperId() > 0){
			KPaper paper = this.paperService.load(model.getPaperId());
			arrange.setPaper(paper);
		}
		
		if(model != null && model.getUserId() != null && model.getUserId() > 0){
			TUser user = userService.load(model.getUserId());
			arrange.setInvigilator(user);
		}
		
		arrange.setStatus(Const.ArrangeStatus.PENDING);
		karrangeService.create(arrange);
		
		if(StringUtil.isNotBlank(model.getUserIds())){
			List<Long> idList = StringUtil.split4Long(model.getUserIds(), Const.COMMA_DELIMITER);
			for(Long uid : idList){
				TUser user = userService.load(uid);
				KArrangeUser arrUser =  new KArrangeUser();
				arrUser.setArrange(arrange);
				arrUser.setStatus(Const.ArrangeUserStatus.DRAFT);
				arrUser.setUser(user);
				arrUser.setVcode(getVCode());
				arrUser.setUcode(StringUtil.uuid());
				arrUser.setBarcodeImage(barcodeText(arrUser));
				String barCode = this.karrangeuserService.generateBarCode(arrUser.getUser().getId(), barcodeText(arrUser));
				arrUser.setBarcodeImage(barCode);
				karrangeuserService.create(arrUser);
			}
		}
		
		return "/success";
	}
	
	private String barcodeText(KArrangeUser arrUser){
		String path = request.getContextPath();  
		String FQDN = cacheManager.get(Const.FQDN);
		String basePath = FQDN+path+"/mi/main/";
		//String text = basePath + "sign.htm?type=EX&i=" + arrUser.getId() + "&u=" + arrUser.getUser().getId() + "&v=" + arrUser.getVcode();
		String text = basePath + "sign/EX/" + arrUser.getUcode() + ".htm";
		return text;
	}
	
	@RequestMapping("/update/{id}")
	public String update(ModelMap modelMap, @PathVariable Long id, ArrangePojo model){
		LOGGER.info("Get update id: " + id);
		LOGGER.info("Update id: " + id);
		if(id != null){
			KArrange dbRecord = karrangeService.load(id);
			this.karrangeTransformer.update(model, dbRecord);
			
			if(model != null && model.getPaperId() != null&& model.getPaperId() > 0){
				KPaper paper = this.paperService.load(model.getPaperId());
				dbRecord.setPaper(paper);
			}
			
			if(model != null && model.getUserId() != null && model.getUserId() > 0){
				TUser user = userService.load(model.getUserId());
				dbRecord.setInvigilator(user);
			}
			karrangeService.update(dbRecord);
			
			List<KArrangeUser> list = this.karrangeuserService.list(dbRecord.getId());
			List<Long> idList = StringUtil.split4Long(model.getUserIds(), Const.COMMA_DELIMITER);
			
			//for new add
			if(StringUtil.isNotBlank(model.getUserIds())){
				for(Long uid : idList){
					boolean exist = false;
					for(KArrangeUser arrangeUser : list){
						if(arrangeUser.getUser() != null && arrangeUser.getUser().getId().equals(uid)){
							exist = true;
							break;
						}
					}
					if(!exist){
						KArrangeUser arrangeUser = new KArrangeUser();
						arrangeUser.setArrange(dbRecord);
						arrangeUser.setStatus(Const.ArrangeUserStatus.DRAFT);
						TUser user = userService.load(uid);
						arrangeUser.setUser(user);
						arrangeUser.setVcode(getVCode());
						arrangeUser.setUcode(StringUtil.uuid());
						arrangeUser.setBarcodeImage(barcodeText(arrangeUser));
						String barCode = this.karrangeuserService.generateBarCode(arrangeUser.getUser().getId(), barcodeText(arrangeUser));
						arrangeUser.setBarcodeImage(barCode);
						karrangeuserService.create(arrangeUser);
					}
				}
			}
			
			
			//for remove
			for(KArrangeUser arrangeUser : list){
				boolean remove = true;
				if(idList != null){
					for(Long uid : idList){
						if(arrangeUser.getUser() != null && arrangeUser.getUser().getId().equals(uid)){
							remove = false;
							break;
						}
					}
				}
				
				if(remove){
					arrangeUser.setUser(null);
					this.karrangeuserService.update(arrangeUser);
					//this.karrangeuserService.delete(arrangeUser);
				}
			}
		}
		return "/success";
	}
	
	@RequestMapping("/loaduser")
	public @ResponseBody Map<String, Object> loaduser(Long id, String type){
		
		Map<String, Object> map  = new HashMap<String, Object>();
		List<Map<String, Object>> rowsData = new ArrayList<Map<String, Object>>();
		Boolean assigned = "assigned".equals(type);
		
		Long total = karrangeuserService.getSize4Arrange(id, assigned);
		if(total > 0){
			List<TUser> list = karrangeuserService.listUsers4Arrange(id, assigned);
			if(list != null){
				for (TUser user : list) {
					Map<String, Object> map1 = new HashMap<String, Object>();
					map1.put("id", user.getId());
					map1.put("company", user.getCompany().getName());
					map1.put("account", user.getLoginID());
					map1.put("realName", user.getName());
					rowsData.add(map1);
				}
			}
		}
		map.put("total", total);
		map.put("rows", rowsData);
		return map;
	}
	
	
	@RequestMapping("/vcode/{id}")
	public String vcode(ModelMap modelMap, @PathVariable Long id){
		if(id != null){
			List<KArrangeUser> list = this.karrangeuserService.list(id);
			modelMap.addAttribute("list", list);
		}
		return "/knowledge/arrange/vcode";
	}
	
	@RequestMapping("/record/{id}")
	public String record(ModelMap modelMap, @PathVariable Long id){
		if(id != null){
			List<KArrangeUser> list = this.karrangeuserService.list(id);
			modelMap.addAttribute("list", list);
		}
		return "/knowledge/arrange/record";
	}
	
	@RequestMapping("/email/{id}")
	public String email(ModelMap modelMap, @PathVariable Long id){
		LOGGER.info("Get show id: " + id);
		if(id != null){
			KArrange model = karrangeService.load(id);
			modelMap.addAttribute("model", model);
			List<KArrangeUser> list = this.karrangeuserService.list(id);
			modelMap.addAttribute("list", list);
		}
		
		return "/knowledge/arrange/email";
	}
	
	@RequestMapping("/sendmail/{id}")
	public String sendmail(ModelMap modelMap, @PathVariable Long id, String to, String cc, String title, String content){
		LOGGER.info("Get show id: " + id);
		if(id != null){
			KArrange model = karrangeService.load(id);
			modelMap.addAttribute("model", model);
			List<String> tolist = StringUtil.split(to, StringUtil.SEMICOLON);
			List<String> cclist = StringUtil.split(cc, StringUtil.SEMICOLON);

			this.karrangeService.sendMail(tolist, cclist, title, content);
		}
		
		return "/success";
	}
	
	/*
	@RequestMapping("/image/{id}")
	public void image(@PathVariable Long id, Long user, String vcode){
		String path = request.getContextPath();  
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		String text = basePath + "verify.htm?type=EX&i=" + id + "&u=" + user + "&v=" + vcode;
		
		
		String image = this.karrangeuserService.generateBarCode(user, text);
		
		try {
			response.getOutputStream().print(path+"\\attachment\\barcode\\" + image);
			response.getOutputStream().flush();
			response.getOutputStream().close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		System.out.println(text);
//		int width = 300; 
//	    int height = 300; 
//	    String format = "gif"; 
//		Hashtable hints = new Hashtable(); 
//	    //内容所使用编码 
//	    hints.put(EncodeHintType.CHARACTER_SET, "utf-8"); 
//	    BitMatrix bitMatrix;
//		try {
//			bitMatrix = new MultiFormatWriter().encode(text,BarcodeFormat.QR_CODE, width, height, hints);
//			
//			BarCodeUtil.writeToStream(bitMatrix, format, response.getOutputStream());
//			
//			//response.getOutputStream().
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} 
	    
		
	}
	*/
	
	private String getVCode() {
		String vcode = "";
        Random random = new Random();
        for(int i=0; i<6; i++){
        	int numberResult = random.nextInt(10);
            int ret = numberResult + 48;
            vcode += (char) ret;
        }
        return vcode;
    }
	
	@RequestMapping("/delete/{id}")
	public @ResponseBody Map<String, Object> delete(@PathVariable Long id){
		Map<String, Object> map  = new HashMap<String, Object>();
		
		if(id != null){
			Long starttedSize = this.karrangeuserService.getSize4Started(id);
			if(starttedSize > 0){
				map.put("status", "exist");
			}
			else{
				KArrange record = karrangeService.load(id);
				if(record != null){
					this.karrangeuserService.delete(id);
					karrangeService.delete(record); 
					map.put("status", "success");
				}
				else{
					map.put("status", "fail");
				}
			}
				
			
		}
		return map;
	}
}
