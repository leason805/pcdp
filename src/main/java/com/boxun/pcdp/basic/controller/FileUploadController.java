package com.boxun.pcdp.basic.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.boxun.pcdp.basic.service.IFileUploadService;

@Controller
@RequestMapping("/system/upload")
public class FileUploadController {

	private static final Logger LOGGER = Logger.getLogger(FileUploadController.class);
	
	@Autowired
	private IFileUploadService fileUploadService;
	
	@RequestMapping("/upload")
	public @ResponseBody Map<String, Object> upload(HttpServletRequest request,HttpServletResponse response){
		LOGGER.info("upload file.");
		Map<String, Object> map  = new HashMap<String, Object>();
		String fileUrl = null;
		Integer error = 0;
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
	                	fileUrl = this.fileUploadService.upload(file);  
	                } 
	            } 
	        }
	        
	        String path = request.getContextPath();  
	        fileUrl = path+"/attachment/kindeditor/"+fileUrl;
		}
		catch(Exception e){
			e.printStackTrace();
			error = 1;
		}

		map.put("error", error);  
		map.put("url", fileUrl); 
		
		return map;
	}
}
