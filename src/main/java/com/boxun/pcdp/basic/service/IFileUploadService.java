package com.boxun.pcdp.basic.service;

import org.springframework.web.multipart.MultipartFile;

public interface IFileUploadService {

	public String upload(MultipartFile file);
}
