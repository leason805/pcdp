package com.boxun.pcdp.archive.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.boxun.pcdp.archive.entity.ACertification;

public interface ICertificationService {

	public void create(ACertification entity);
	
	public void update(ACertification entity);
	
	public void delete(ACertification entity);
	
	public ACertification load(Long id);
	
	public List<ACertification> list();
	
	public List<ACertification> list(Long userId);
	
	public List<ACertification> list(Long userId, List<Long> ids);
	
	public Long createFile(MultipartFile file, Long userId, Long typeId);
}
