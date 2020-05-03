<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/common.jsp"%>
<%@ page isELIgnored="false" %>
<div class="row">
	<div id="breadcrumb" class="col-md-12">
		<ol class="breadcrumb">
			<li><a href="#">我的档案</a></li>
			<li><a href="#">基本信息</a></li>
			
		</ol>
	</div>
</div>

<div class="row">
	<div class="col-xs-8">
		<div class="box">
			<div class="box-header">
				<div class="box-name">
					<i class="fa fa-home"></i>
					<span>个人信息</span>
				</div>
				<div class="box-icons">
					<a class="collapse-link">
						<i class="fa fa-chevron-up"></i>
					</a>
					<a class="expand-link">
						<i class="fa fa-expand"></i>
					</a>
					<a class="close-link">
						<i class="fa fa-times"></i>
					</a>
				</div>
				<div class="no-move"></div>
			</div>
			<div class="box-content" style="font-size: 16px;">
				<div class="card" style="">
					<h4 class="page-header">${userInfo.userName }  <span style="float:right; font-size: 16px;"><a href="javascript:edit(1, ${userInfo.userId})"><i class="fa fa-edit"></i>&nbsp;&nbsp;编&nbsp;辑</a></span></h4>
				</div>

				<div class="col-xs-12" style="padding-bottom: 9px; ">
						<label class="col-sm-2 control-label page-header">邮箱</label>
						<div class="col-sm-4">
							${model.user.email }
						</div>
						<label class="col-sm-2 control-label page-header">手机</label>
						<div class="col-sm-4">
							${model.telephone }
						</div>
				</div>
				
				<div class="col-xs-12" style="padding-bottom: 9px;">
						<label class="col-sm-2 control-label page-header" >出生日期</label>
						<div class="col-sm-4">
							<fmt:formatDate value="${model.birthday}" type="date" pattern="yyyyMMdd"/>
						</div>
						<label class="col-sm-2 control-label page-header">性别</label>
						<div class="col-sm-4">
							<c:if test="${model.gender == 'MALE' }">男</c:if>
							<c:if test="${model.gender == 'FEMALE' }">女</c:if>
						</div>
				</div>
				<div class="col-xs-12" style="padding-bottom: 9px;">
						<label class="col-sm-2 control-label page-header">籍贯</label>
						<div class="col-sm-4">
							${model.nativePlace }
						</div>
						<label class="col-sm-2 control-label page-header">政治面貌</label>
						<div class="col-sm-4">
							${model.political }
						</div>
				</div>
				
				<div class="col-xs-12" style="padding-bottom: 9px;">
						<label class="col-sm-2 control-label page-header">身份证</label>
						<div class="col-sm-4">
							${model.idCard }
						</div>
						<label class="col-sm-2 control-label page-header">通讯地址</label>
						<div class="col-sm-4">
							${model.address }
						</div>
				</div>
				
				<div class="col-xs-12" style="padding-bottom: 9px;">
						<label class="col-sm-2 control-label page-header">工作部门</label>
						<div class="col-sm-4">
							${model.deparment.name }
						</div>
						<label class="col-sm-2 control-label page-header">职位</label>
						<div class="col-sm-4">
							${model.position.name }
						</div>
				</div>
					
					<div class="map" id="map-1" style="height: 350px;"></div>
			</div>
		</div>
	</div>
	<div class="col-xs-4">
		<div class="box">
			<div class="box-header">
				<div class="box-name">
					<i class="fa fa-user"></i>
					<span>职业信息</span>
				</div>
				<div class="box-icons">
					<a class="collapse-link">
						<i class="fa fa-chevron-up"></i>
					</a>
					<a class="expand-link">
						<i class="fa fa-expand"></i>
					</a>
					<a class="close-link">
						<i class="fa fa-times"></i>
					</a>
				</div>
				<div class="no-move"></div>
			</div>
			<div class="box-content" style="font-size: 16px;">
				<div class="card">
					<h4 class="page-header">&nbsp; <span style="float:right; font-size: 16px;"><a href="javascript:edit(4, ${userInfo.userId})"><i class="fa fa-edit"></i>&nbsp;&nbsp;编&nbsp;辑</a></span></h4>
					
				</div>
				
				<div class="col-xs-12" style="padding-bottom: 9px;">
						<label class="col-sm-5 control-label page-header">技术等级</label>
						<div class="col-sm-7">
							${model.jobInfo.techLevel.name }
						</div>
				</div>
				<div class="col-xs-12" style="padding-bottom: 9px;">
						<label class="col-sm-5 control-label page-header">岗位等级</label>
						<div class="col-sm-7">
							${model.jobInfo.positionLevel.name }
						</div>
				</div>
				<div class="col-xs-12" style="padding-bottom: 9px;">
						<label class="col-sm-5 control-label page-header">入职时间</label>
						<div class="col-sm-7">
							<fmt:formatDate value="${model.jobInfo.joinDate}" type="date" pattern="yyyyMMdd"/>
						</div>
				</div>
				<div class="col-xs-12" style="padding-bottom: 9px;">
						<label class="col-sm-5 control-label page-header">参加工作</label>
						<div class="col-sm-7">
							<fmt:formatDate value="${model.jobInfo.startDate}" type="date" pattern="yyyyMMdd"/>
						</div>
				</div>
				
				
				<div class="card">

					<h5 class="page-header">&nbsp;</h5>
					
				</div>
				
			</div>
		</div>
		
		<div class="box">
			<div class="box-header">
				<div class="box-name">
					<i class="fa fa-user"></i>
					<span>教育信息</span>
				</div>
				<div class="box-icons">
					<a class="collapse-link">
						<i class="fa fa-chevron-up"></i>
					</a>
					<a class="expand-link">
						<i class="fa fa-expand"></i>
					</a>
					<a class="close-link">
						<i class="fa fa-times"></i>
					</a>
				</div>
				<div class="no-move"></div>
			</div>
			<div class="box-content " style="font-size: 16px;">
				<div class="card">
					<h4 class="page-header">&nbsp; 
						<span style="float:right; font-size: 16px;">
							<a href="javascript:edit(3, ${model.id})"><i class="fa fa-edit"></i>&nbsp;&nbsp;添&nbsp;加</a>&nbsp;&nbsp;
							<a href="javascript:edit(2, ${model.id})"><i class="fa fa-edit"></i>&nbsp;&nbsp;编&nbsp;辑</a>
						</span>
					</h4>
					
				</div>
				
				<c:forEach items="${model.eduInfos }" var="edu">
					<div class="col-xs-12" style="padding-bottom: 9px;">
						<label class="col-sm-5 control-label page-header">毕业院校</label>
						<div class="col-sm-7">
							${edu.university }
						</div>
					</div>
					<div class="col-xs-12" style="padding-bottom: 9px;">
							<label class="col-sm-5 control-label page-header">专业</label>
							<div class="col-sm-7">
								${edu.major }
							</div>
					</div>
					<div class="col-xs-12" style="padding-bottom: 9px;">
							<label class="col-sm-5 control-label page-header">学历</label>
							<div class="col-sm-7">
								${edu.degree }
							</div>
					</div>
	
					
					<div class="card">
						<h4 class="page-header">&nbsp; </h4>
						
					</div>
				</c:forEach>
				
				
			</div>
			
		</div>
		
		
	</div>
</div>
	

<script type="text/javascript">

function edit(type, id){
	var url = "${ctx}/system/archive/userinfo/basic/" + id + ".htm";
	if(type == 1){
		url = "${ctx}/system/archive/userinfo/basic/" + id + ".htm";
	}
	if(type == 2){
		url = "${ctx}/system/archive/userinfo/editeduinfo/" + id + ".htm";
	}
	if(type == 3){
		url = "${ctx}/system/archive/userinfo/addeduinfo/" + id + ".htm";
	}
	if(type == 4){
		url = "${ctx}/system/archive/userinfo/jobinfo/" + id + ".htm";
	}
	
	$.colorbox({href:url, iframe:true, width:"65%", height:"100%"});
}

$(document).ready(function() {
	
});
</script>
