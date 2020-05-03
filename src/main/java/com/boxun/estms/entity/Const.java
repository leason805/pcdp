package com.boxun.estms.entity;

public class Const {

	public static final String COMMA_DELIMITER = ",";
	
	public static final String UNDER_DELIMITER = "_";
	
	public static final String UPLOAD_ATTACHMENT_PATH = "UPLOAD_ATTACHMENT_PATH";
	
	public static final String UPLOAD_FILE_PATH = "UPLOAD_FILE_PATH";
	
	public static final String BARCODE_FILE_PATH = "BARCODE_FILE_PATH";
	
	public static final String QIANPAIZULI = "QIANPAIZULI";
	
	public static final String FANGXINGQIANPAIYUAN = "FANGXINGQIANPAIYUAN";
	
	public static final String QIANPAIXUEYUAN = "QIANPAIXUEYUAN";
	
	public static final String MAIL_HOST = "MAIL_HOST";
	public static final String MAIL_FROM = "MAIL_FROM";
	public static final String MAIL_USER = "MAIL_USER";
	public static final String MAIL_PASSWORD = "MAIL_PASSWORD";
	
	public static final String FQDN = "FQDN";
	
	public enum UserStatus {
		 ENABLE("启用"), DISABLE("禁用");
		 
		 private String value;
		 
		 private UserStatus(String value) {
			 this.value = value;
		 }
		 
		 @Override
		 public String toString() {
			 return this.value;
		 }
	}
	
	public enum UserType {
		 ADMIN, PILOT, SUPERVISOR;
	}
	
	public enum LinkType {
		 YES, NO;
	}
	
	public enum ProjectType {
		 RANDOM, SPECIFY;
	}
	
	public enum IndicatorType {
		 CAPACITY, PERFORMANCE;
	}
	
	public enum Mandatory {
		 YES, NO;
	}
	
	public enum CourseStatus {
		 ACTIVE, DISCARD;
	}
	
	public enum ArrangeStatus {
		 PENDING, COMPLETED, CANCEL;
	}
	
	public enum ArrangeUserStatus {
		 DRAFT, SIGNED, COMPLETED, CANCEL, ABSENT;
	}
	
	public enum AssessStatus {
		 DRAFT, COMPLETED, CANCEL, UNASSIGN, ASSIGNED;;
	}
	
//	public enum AssignStatus {
//		 UNASSIGN, ASSIGNED;
//	}
	
	public enum CertificationStatus {
		 PENDING, PASS, REJECT, EXPIRED;
	}
	
	public enum QuestionType {
		 SINGLE, MULTIPLE;
	}
	
	public enum AnswerType {
		 YES, NO;
	}
	
	public enum PassType {
		 YES, NO;
	}
	
	public enum ScoreType {
		 VALID, INVALID;
	}
	
	public enum CorrectType {
		 YES, NO;
	}
	
	public enum Gender {
		 MALE, FEMALE;
	}
	
	public enum PositionCategoryType {
		 DEPARTMENT, POSITION, TECHLEVEL, POSITIONLEVEL;
	}
	
	public enum AssociateType {
		 CERTIFICATION, EXPERIENCE, KNOWLEDGE, TRAINING, QUALIFICATION;
	}
	
	public enum ReviewType {
		SINGLE, MULTIPLE;
	}
}
