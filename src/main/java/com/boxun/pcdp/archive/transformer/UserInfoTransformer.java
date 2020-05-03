package com.boxun.pcdp.archive.transformer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.boxun.estms.util.Const;
import com.boxun.estms.util.DateUtil;
import com.boxun.estms.util.StringUtil;
import com.boxun.pcdp.archive.entity.AUserInfo;
import com.boxun.pcdp.archive.pojo.UserInfoPojo;
import com.boxun.pcdp.basic.transformer.ITransformer;

@Component("userinfoTransformer")
public class UserInfoTransformer implements ITransformer<AUserInfo, UserInfoPojo> {

	@Override
	public UserInfoPojo convert(AUserInfo entity) {
		UserInfoPojo pojo = new UserInfoPojo();
		pojo.setId(entity.getId());
		if(entity.getBirthday() != null){
			pojo.setBirthday(DateUtil.formatDateTime(entity.getBirthday(), Const.DATE_FORMAT_3));
		}
		pojo.setAddress(entity.getAddress());
		pojo.setEmail(entity.getUser().getEmail());
		pojo.setTelephone(entity.getTelephone());
		pojo.setIdCard(entity.getIdCard());
		pojo.setName(entity.getUser().getName());
		pojo.setNativePlace(entity.getNativePlace());
		if(entity.getGender() != null){
			pojo.setGender(entity.getGender());
		}
		if(entity.getDeparment() != null){
			pojo.setDepartment(entity.getDeparment().getName());
		}
		if(entity.getPosition() != null){
			pojo.setPosition(entity.getPosition().getName());
		}
		if(!StringUtils.isEmpty(entity.getStatus())){
			pojo.setCheckStatus(entity.getStatus().toString());
		}
		else{
			pojo.setCheckStatus("PENDING");
		}
		
		return pojo;
	}

	@Override
	public AUserInfo transform(UserInfoPojo pojo) {
		AUserInfo info =  new AUserInfo();
		
		if(StringUtil.isNotBlank(pojo.getBirthday())){
			info.setBirthday(DateUtil.formatDate(pojo.getBirthday(), Const.DATE_FORMAT_3));
		}
		info.setGender(pojo.getGender());
		info.setAddress(pojo.getAddress());
		info.setIdCard(pojo.getIdCard());
		info.setNativePlace(pojo.getNativePlace());
		info.setTelephone(pojo.getTelephone());
		info.setPolitical(pojo.getPolitical());
		return info;
	}

	@Override
	public void update(UserInfoPojo pojo, AUserInfo entity) {
		if(StringUtil.isNotBlank(pojo.getBirthday())){
			entity.setBirthday(DateUtil.formatDate(pojo.getBirthday(), Const.DATE_FORMAT_3));
		}
		else{
			entity.setBirthday(null);
		}
		entity.setGender(pojo.getGender());
		entity.setAddress(pojo.getAddress());
		entity.setIdCard(pojo.getIdCard());
		entity.setNativePlace(pojo.getNativePlace());
		entity.setTelephone(pojo.getTelephone());
		entity.setPolitical(pojo.getPolitical());
	}

	public List<UserInfoPojo> list(List<AUserInfo> entities) {
		List<UserInfoPojo> list = new ArrayList<UserInfoPojo>();
		for(AUserInfo info : entities){
			list.add(convert(info));
		}
		return list;
	}
}
