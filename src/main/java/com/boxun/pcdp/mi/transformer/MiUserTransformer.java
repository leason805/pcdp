package com.boxun.pcdp.mi.transformer;

import org.springframework.stereotype.Component;

import com.boxun.estms.entity.Const;
import com.boxun.pcdp.archive.entity.AUserInfo;
import com.boxun.pcdp.basic.transformer.ITransformer;
import com.boxun.pcdp.mi.pojo.MiUserPojo;

@Component("miUserTransformer")
public class MiUserTransformer implements ITransformer<AUserInfo, MiUserPojo> {

	@Override
	public MiUserPojo convert(AUserInfo entity) {
		MiUserPojo pojo = new MiUserPojo();
		if(entity != null){
			pojo.setUname(entity.getUser().getName());
			pojo.setEmail(entity.getUser().getEmail());
			pojo.setTelephone(entity.getTelephone());
			if(entity.getGender() != null){
				if(Const.Gender.MALE.equals(entity.getGender())){
					pojo.setGender("男");
				}
				if(Const.Gender.FEMALE.equals(entity.getGender())){
					pojo.setGender("女");
				}
			}
			if(entity.getDeparment() != null){
				pojo.setDeparment(entity.getDeparment().getName());
			}
			if(entity.getPosition() != null){
				pojo.setPosition(entity.getPosition().getName());
			}
			if(entity.getJobInfo() != null){
				if(entity.getJobInfo().getTechLevel() != null){
					pojo.setTechLevel(entity.getJobInfo().getTechLevel().getName());
				}
				if(entity.getJobInfo().getPositionLevel() != null){
					pojo.setPositionLevel(entity.getJobInfo().getPositionLevel().getName());
				}
			}
		}
		return pojo;
	}

	@Override
	public AUserInfo transform(MiUserPojo pojo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(MiUserPojo pojo, AUserInfo entity) {
		// TODO Auto-generated method stub
		
	}

}
