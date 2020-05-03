package com.boxun.pcdp.knowledge.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.boxun.estms.entity.Const;
import com.boxun.estms.util.StringUtil;
import com.boxun.pcdp.admin.entity.TCompany;
import com.boxun.pcdp.admin.service.ICompanyService;
import com.boxun.pcdp.basic.controller.BaseController;
import com.boxun.pcdp.knowledge.entity.KOption;
import com.boxun.pcdp.knowledge.entity.KQuestion;
import com.boxun.pcdp.knowledge.entity.KSection;
import com.boxun.pcdp.knowledge.pojo.QuestionPojo;
import com.boxun.pcdp.knowledge.pojo.SectionPojo;
import com.boxun.pcdp.knowledge.service.IQuestionService;
import com.boxun.pcdp.knowledge.service.ISectionService;
import com.boxun.pcdp.knowledge.transformer.QuestionTransformer;

@Controller
@RequestMapping("/system/knowledge/question")
public class QuestionController extends BaseController{

private static final Logger LOGGER = Logger.getLogger(QuestionController.class);
	
	@Autowired
	private IQuestionService questionService;
	
	@Autowired
	private ISectionService sectionService;
	
	@Autowired
	private ICompanyService companyService;
	
	@Autowired
	private QuestionTransformer questionTransformer;
	
	@RequestMapping("/list")
	public String list(ModelMap modelMap){
		List<TCompany> companys = this.companyService.list();
		modelMap.addAttribute("companys", companys);
		
		List<SectionPojo> list = sectionService.listSectionPojos();
		modelMap.addAttribute("list", list);
		
		return "/knowledge/question/list";
	}
	
	@RequestMapping("/show/{id}")
	public String show(ModelMap modelMap, @PathVariable Long id){
		LOGGER.info("Get show id: " + id);
		List<KSection> sections = this.sectionService.list();
		modelMap.addAttribute("sections", sections);
		
		if(id != null){
			KQuestion model = questionService.load(id);
			modelMap.addAttribute("model", model);
		}
		
		return "/knowledge/question/show";
	}
	
	@RequestMapping("/nav/{id}")
	public String nav(ModelMap modelMap, @PathVariable Long id){
		List<KSection> list = sectionService.listTops();
		modelMap.addAttribute("list", list);
		return "/knowledge/question/nav";
	}
	
	@RequestMapping("/content/{id}")
	public String content(ModelMap modelMap, @PathVariable Long id){
		//List<KQuestion> list = this.questionService.listBySectionId(id);
		//modelMap.addAttribute("list", list);
		modelMap.addAttribute("id", id);
		return "/knowledge/question/content";
	}
	
	@RequestMapping("/contentdata")
	public @ResponseBody Map<String, Object> contentdata(Long id){
		LOGGER.info("Get contentdata.");
		List<KQuestion> listdata = this.questionService.listBySectionId(id);
		Map<String, Object> map  = new HashMap<String, Object>();
		map.put("sEcho", 1);  
		map.put("iTotalRecords", listdata.size());  
        map.put("iTotalDisplayRecords", listdata.size());  
		map.put("aaData", listdata);
		
		return map;
	}
	
	@RequestMapping("/detail")
	public String detail(ModelMap modelMap, Long id){
		KQuestion model = this.questionService.load(id);
		modelMap.addAttribute("model", model);
		return "/knowledge/question/detail";
	}
	
	
	@RequestMapping("/showimport")
	public String showimport(ModelMap modelMap){
		return "/knowledge/question/showimport";
	}
	
	@RequestMapping("/imports")
	public String imports(ModelMap modelMap, HttpServletRequest request, HttpServletResponse response){
		List<KSection> sectionList = sectionService.list();
		Map<String, KSection> sectionMap = new HashMap<String, KSection>();
		for(KSection section : sectionList){
			sectionMap.put(section.getSequence(), section);
		}
		
		Workbook workbook = null; 
		InputStream fis = null;
		
		List<KQuestion> questList = new ArrayList<KQuestion>();
		List<KOption> optionList = null;
		
		try{
			CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());  
	        //判断 request 是否有文件上传,即多部分请求  
	        if(multipartResolver.isMultipart(request)){  
	            //转换成多部分request    
	            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;  
	            //取得request中的所有文件名  
	            Iterator<String> iter = multiRequest.getFileNames();  
	            while(iter.hasNext()){  
	                MultipartFile file = multiRequest.getFile(iter.next());  
	                if(file != null){  
	                	fis = file.getInputStream();
	                	workbook = WorkbookFactory.create(fis);
	    				Sheet sheet = workbook.getSheetAt(0);
	    				
	    				Date now = new Date();
	    				
	    				KSection section = null;
	    				KQuestion question = null;
		   				KOption option = null;
		   				boolean isNew = false;
		   				Row row = null;
		   				int codeIndex = 0;
		   				String seq = null;
		   				String quest = null;
		   				
		   				String opt = null;
		   				String answer = null;
		   				String answerDesc = null;
		   				int row_num = sheet.getLastRowNum(); 
		   				for (int i = 1; i <= row_num; i++) {
		   					isNew = false;
		   					row = sheet.getRow(i);
		   					System.out.println("row: "+i);
		   					section = null;
		   					quest = null;
		   					if(row == null)
		   						continue;
		   					if(row.getCell(0) != null){
		   						if(row.getCell(0).getCellType() == HSSFCell.CELL_TYPE_STRING){
		   							seq = StringUtil.trim(row.getCell(0).getStringCellValue());
		   						}
		   						else if(row.getCell(0).getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
		   							seq = String.valueOf(row.getCell(0).getNumericCellValue());
		   						}
		   						System.out.println("seq: " + seq);
		   					 }
		   					if(row.getCell(1) != null){
		   						if(row.getCell(1).getCellType() == HSSFCell.CELL_TYPE_STRING){
		   							quest = StringUtil.trim(row.getCell(1).getStringCellValue());
		   						}
		   						else if(row.getCell(1).getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
		   							quest = String.valueOf(row.getCell(1).getNumericCellValue());
		   						}
		   						System.out.println("quest: " + quest);
		   					 }
		   					 
		   					 if(StringUtil.isNotBlank(quest)){
		   						 isNew = true;
		   						 codeIndex = 0;
		   						 question = new KQuestion();
		   						 
		   						section = sectionMap.get(seq);
		   						if(section == null){
		   							System.out.println("can't find relate section......");
		   							break;
		   						}
		   						optionList = new ArrayList<KOption>();
		   						question.setSection(sectionMap.get(seq));
		   						question.setQuestionType(Const.QuestionType.SINGLE);
		   						question.setQuestion(quest);
		   						question.setCreateTime(now);
		   						question.setOptions(optionList);
		   						questList.add(question);
		   					 }
		   					 else{
		   						 option = new KOption();
		   						 if(row.getCell(2) != null){
		   							 if(row.getCell(2).getCellType() == HSSFCell.CELL_TYPE_STRING){
		   								 opt = StringUtil.trim(row.getCell(2).getStringCellValue());
		   							 }
		   							 else if(row.getCell(2).getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
		   								 opt = String.valueOf(row.getCell(2).getNumericCellValue());
		   							 }
		   							 option.setContent(opt);
		   							 option.setAnswerType(Const.AnswerType.NO);
		   						 }
		   						 else{
		   							 //option is null????????????
		   							 
		   						 }
		   						 
		   						 if(row.getCell(3) != null){
		   							 if(row.getCell(3).getCellType() == HSSFCell.CELL_TYPE_STRING){
		   								 answer = StringUtil.trim(row.getCell(3).getStringCellValue());
		   							 }
		   							 else if(row.getCell(3).getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
		   								 answer = String.valueOf(row.getCell(3).getNumericCellValue());
		   							 }
		   							 if("y".equalsIgnoreCase(answer)){
		   								option.setAnswerType(Const.AnswerType.YES);
		   							 }
		   							 System.out.println("answer: '" + answer + "'");
		   						 }
		   						 else{
		   							option.setAnswerType(Const.AnswerType.NO);
		   						 }
		   						 
		   						 if(row.getCell(4) != null){
		   							 if(row.getCell(4).getCellType() == HSSFCell.CELL_TYPE_STRING){
		   								 answerDesc = StringUtil.trim(row.getCell(4).getStringCellValue());
		   							 }
		   							 else if(row.getCell(4).getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
		   								 answerDesc = String.valueOf(row.getCell(4).getNumericCellValue());
		   							 }
		   							 option.setAnswerDesc(answerDesc);
		   							 System.out.println("answerDesc: '" + answerDesc + "'");
		   						 }
		   						 option.setCode(StringUtil.genOptionCode(codeIndex));
		   						 optionList.add(option);
		   						 //question.addOption(option);
		   						 codeIndex++;
		   					 }
		   					
		   				 }
	                } 
	            } 
	        }
	        
	        int answSize = 0;
	        
	        for(KQuestion question : questList){
	        	answSize = 0;
	        	for(KOption opt : question.getOptions()){
	        		if(Const.AnswerType.YES.equals(opt.getAnswerType())){
	        			answSize++;
	        			if(answSize > 1){
	        				question.setQuestionType(Const.QuestionType.MULTIPLE);
	        				break;
	        			}
	        		}
	        	}
	        }
	        this.questionService.saveOrUpdate(questList);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			 if(fis != null)
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		 }
		
		modelMap.addAttribute("refresh", "page");
		modelMap.addAttribute("url", "/system/knowledge/question/list");
		
		return "/success";
	}
	
	@RequestMapping("/create")
	public String create(ModelMap modelMap, @RequestParam Long sectionId, @RequestParam String options, QuestionPojo model){

		KQuestion question = questionTransformer.transform(model);
		question.setCreateTime(new Date());
		
		if(sectionId != null && sectionId > 0){
			KSection section =  this.sectionService.load(sectionId);
			question.setSection(section);
		}
		
		if(StringUtil.isNotBlank(options)){
			List<KOption> optionList = new ArrayList<KOption>();
			
			JSONArray array = JSONArray.parseArray(options); 
			for(int i = 0; i < array.size(); i++){     
	            JSONObject jsonObject = array.getJSONObject(i);
	            KOption option = (KOption)JSONObject.toJavaObject(jsonObject, KOption.class);
	            optionList.add(option);
	            //optionSet.add(option);
	        }
			question.setOptions(optionList);
		}
		
		questionService.create(question);
		return "/success";
	}
	
	@RequestMapping("/listoptions/{id}")
	public @ResponseBody Map<String, Object> listOptions(@PathVariable Long id){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Map<String, Object>> rowsData = new ArrayList<Map<String, Object>>();
		if(id != null){
			KQuestion dbRecord = questionService.load(id);
			if(dbRecord != null){
				for(KOption option : dbRecord.getOptions()){
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("code", option.getCode());
					map.put("answerType", option.getAnswerType());
					map.put("content", option.getContent());
					map.put("answerDesc", option.getAnswerDesc());
					rowsData.add(map);
				}
			}
		}
		resultMap.put("rows", rowsData);
		return resultMap;
	}
	
	
	@RequestMapping("/update/{id}")
	public @ResponseBody Map<String, Object> update(ModelMap modelMap, @PathVariable Long id, @RequestParam Long sectionId, @RequestParam String options, QuestionPojo model){
		LOGGER.info("Get update id: " + id);
		LOGGER.info("Update id: " + id);
		Map<String, Object> map  = new HashMap<String, Object>();
		try{
			if(id != null){
				KQuestion dbRecord = questionService.load(id);
				dbRecord.setQuestion(model.getQuestion());
				dbRecord.setQuestionType(model.getQuestionType());
				if(sectionId != null && sectionId > 0){
					KSection section =  this.sectionService.load(sectionId);
					dbRecord.setSection(section);
				}
				if(StringUtil.isNotBlank(options)){
					List<KOption> optionList = new ArrayList<KOption>();
					JSONArray array = JSONArray.parseArray(options); 
					for(int i = 0; i < array.size(); i++){     
			            JSONObject jsonObject = array.getJSONObject(i);
			            KOption option = (KOption)JSONObject.toJavaObject(jsonObject, KOption.class);
			            optionList.add(option);
			        }
					dbRecord.setOptions(optionList);
				}
				questionService.update(dbRecord);
			}
			map.put("status", "success");
		}
		catch(Exception e){
			map.put("status", "fail");
		}
		return map;
		//return "/success";
	}
}
