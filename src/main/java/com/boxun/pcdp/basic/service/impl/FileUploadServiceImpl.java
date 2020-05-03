package com.boxun.pcdp.basic.service.impl;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.boxun.estms.entity.Const;
import com.boxun.estms.util.FileUtil;
import com.boxun.estms.util.StringUtil;
import com.boxun.pcdp.basic.service.IFileUploadService;
import com.boxun.pcdp.cache.CacheManager;

@Service("fileUploadService")
public class FileUploadServiceImpl implements IFileUploadService{

	@Autowired
	private CacheManager cacheManager;
	
	@Override
	public String upload(MultipartFile file) {
		String fileStr = "";
		try{
			String oriFileName = file.getOriginalFilename();
			 String uploadFolder = cacheManager.get(Const.UPLOAD_ATTACHMENT_PATH);//CacheManager.getInstance().get(Const.UPLOAD_FILE_PATH);
			 Long curr = System.currentTimeMillis();
			 if(StringUtil.isNotBlank(oriFileName)){  //如果名称不为“”,说明该文件存在，否则说明该文件不存在  
				 String newFileName = Const.UNDER_DELIMITER + curr + StringUtil.getExt(oriFileName);
				 String path = uploadFolder;// + "kindeditor";
				 //FileUtil.createDir(path);
				 fileStr = newFileName;
				 path =	path + System.getProperty("file.separator") + newFileName;  
		         File uploadlFile = new File(path);
		         file.transferTo(uploadlFile);
			 }
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return fileStr;
	}

}
