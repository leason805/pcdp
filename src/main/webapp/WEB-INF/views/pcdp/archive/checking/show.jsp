<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page isELIgnored="false" %> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/common.jsp"%>
<!DOCTYPE html>
<html >
	<head>
		<meta charset="utf-8">
		<title>DevOOPS</title>
		<meta name="description" content="description">
		<meta name="author" content="DevOOPS">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link href="${ctx}/plugins/bootstrap/bootstrap.css" rel="stylesheet">
		<link href="${ctx}/plugins/jquery-ui/jquery-ui.min.css" rel="stylesheet">
		<link href="${ctx}/css/font-awesome.css" rel="stylesheet">
		<link href='${ctx}/css/G_Font_Righteous.css' rel='stylesheet' type='text/css'>
		<link href="${ctx}/plugins/fancybox/jquery.fancybox.css" rel="stylesheet">
		<link href="${ctx}/plugins/fullcalendar/fullcalendar.css" rel="stylesheet">
		<link href="${ctx}/plugins/xcharts/xcharts.min.css" rel="stylesheet">
		<link href="${ctx}/plugins/select2/select2.css" rel="stylesheet">

		<link href="${ctx}/plugins/jquery-stamper/jquery.zsign.css" rel="stylesheet">
		
		<link href="${ctx}/plugins/dropzone/dropzone.css" rel="stylesheet">
		
		<link href="${ctx}/css/style.css" rel="stylesheet">
		<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
		<!--[if lt IE 9]>
				<script src="http://getbootstrap.com/docs-assets/js/html5shiv.js"></script>
				<script src="http://getbootstrap.com/docs-assets/js/respond.min.js"></script>
		<![endif]-->
		
		<style type="text/css">
		.dropzone .dz-preview .dz-image {
		    width: 100px;
		    height: 100px;
		    padding-top: 8px;
		}
	
	  .dropzone .dz-preview {
	    min-height: 10px; 
	    margin: 1px 16px 1px 16px;
	    }
	    
	    .dropzone {
		    padding: 1px 20px 1px 20px;
		}
		
		.thum_img {
			width: 120px;
			height: 120px;
		}
		.dropzone {
	    	height: 180px;
	    }
	    </style>
	</head>
<body>

<div class="row" id="content">
	<div class="col-xs-12 col-sm-12">
		<div class="box">
			<div class="box-header">
				<div class="box-name">
					<i class="fa fa-edit"></i>
					资料审核
				</div>
				<div class="box-icons">
					<a class="collapse-link">
						<i class="fa fa-chevron-up"></i>
					</a>

				</div>
				<div class="no-move"></div>
			</div>
			
			<div class="row-fluid">
	<div id="dashboard_links" class="col-xs-12 col-sm-2 pull-right">
		<ul class="nav nav-pills nav-stacked">
			<li class="active"><a href="#" class="tab-link" id="overview">基本信息</a></li>
			<li><a href="#" class="tab-link" id="clients">资格证书</a></li>
			<li><a href="#" class="tab-link" id="graph">综合经历</a></li>
		</ul>
	</div>
	<div id="dashboard_tabs" class="col-xs-12 col-sm-10">
		<!--Start Dashboard Tab 1-->
		<div id="dashboard-overview" class="row" style="visibility: visible; position: relative;">
			<div id="ow-marketplace" class="col-sm-12 col-md-12">

				<h4 class="page-header">${model.user.name }</h4>
				<div class="col-xs-12">
							<button type="button" class="btn btn-primary btn-label-left" onclick="printdoc('info');" style="float:right">
							<span><i class="fa fa-clock-o txt-danger"></i></span>
								打印
							</button>
				</div>
				<div class="col-xs-12">
					<div class="box">
						<div class="box-header">
							<div class="box-name">
								<i class="fa fa-search"></i>
								<span>个人信息</span>
							</div>
							<div class="box-icons">
								<a class="collapse-link">
									<i class="fa fa-chevron-up"></i>
								</a>
							</div>
							<div class="no-move"></div>
						</div>
						<div class="box-content" id="basic_info">
							<div class="card" style="">
								<h4 class="page-header">&nbsp;
									<span style="float:right; font-size: 16px;">
										<a href="javascript:check('basic', 'PASS', ${model.id});"><i class="fa fa-edit"></i>通&nbsp;过</a>&nbsp;&nbsp;
										<a href="javascript:check('basic', 'REJECT', ${model.id});"><i class="fa fa-edit"></i>&nbsp;不&nbsp;通&nbsp;过</a>
									</span>
								</h4>
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
									${model.birthday }
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
							<div id="xchart-3" style="height: 250px;"></div>
						</div>
					</div>
				</div>
				<div class="col-xs-12 col-sm-12">
					<div class="box">
						<div class="box-header">
							<div class="box-name">
								<i class="fa fa-search"></i>
								<span>职业信息</span>
							</div>
							<div class="box-icons">
								<a class="collapse-link">
									<i class="fa fa-chevron-up"></i>
								</a>
							</div>
							<div class="no-move"></div>
						</div>
						<div class="box-content" id="job_info">
							<div class="card" style="">
								<h4 class="page-header">&nbsp;
									<span style="float:right; font-size: 16px;">
										<a href="javascript:void();" onclick="check('job', 'PASS', ${model.jobInfo.id})"><i class="fa fa-edit"></i>通&nbsp;过</a>&nbsp;&nbsp;
										<a href="javascript:void();" onclick="check('job', 'REJECT', ${model.jobInfo.id})"><i class="fa fa-edit"></i>&nbsp;不&nbsp;通&nbsp;过</a>
									</span>
								</h4>
							</div>
							<div class="col-xs-12" style="padding-bottom: 9px;">
							<label class="col-sm-6 control-label page-header">技术等级</label>
							<div class="col-sm-6">
								${model.jobInfo.techLevel.name }
							</div>
							</div>
							<div class="col-xs-12" style="padding-bottom: 9px;">
									<label class="col-sm-6 control-label page-header">岗位等级</label>
									<div class="col-sm-6">
										${model.jobInfo.positionLevel.name }
									</div>
							</div>
							<div class="col-xs-12" style="padding-bottom: 9px;">
									<label class="col-sm-6 control-label page-header">入职时间</label>
									<div class="col-sm-6">
										${model.jobInfo.joinDate }
									</div>
							</div>
							<div class="col-xs-12" style="padding-bottom: 9px;">
									<label class="col-sm-6 control-label page-header">参加工作</label>
									<div class="col-sm-6">
										${model.jobInfo.startDate }
									</div>
							</div>
						
							<div id="xchart-2" style="height: 200px;"></div>
						</div>
					</div>
				</div>
				<div class="col-xs-12 col-sm-12">
					<div class="box">
						<div class="box-header">
							<div class="box-name">
								<i class="fa fa-search"></i>
								<span>教育信息</span>
							</div>
							<div class="box-icons">
								<a class="collapse-link">
									<i class="fa fa-chevron-up"></i>
								</a>
							</div>
							<div class="no-move"></div>
						</div>
						<div class="box-content">
							<table id="ticker-table" class="table m-table table-bordered table-hover table-heading">
								<thead>
									<tr>
										<th>学校名称</th>
										<th>专业名称</th>
										<th>学历</th>
										<th>开始时间</th>
										<th>结束时间</th>
										<th>状态</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody>
								<c:forEach items="${model.eduInfos}" var="pojo">
									<tr class="odd">
										<td class=" sorting_1"><span class="span_"> &nbsp; ${pojo.university }</span></td>
										<td class="">${pojo.major }</td>
										<td class="">${pojo.degree }</td>
										<td class="">${pojo.startDate }</td>
										<td class="">
											<c:choose>
												<c:when test="${not empty pojo.endDate }">${pojo.endDate }</c:when>
												<c:otherwise>至今</c:otherwise>
											</c:choose> 
										</td>
										<td class="" id="edu_status_${pojo.id}">
											<c:if test="${pojo.status == 'PENDING' }">待审核</c:if>
											<c:if test="${pojo.status == 'PASS' }"><span style="color:green">审核通过</span></c:if>
											<c:if test="${pojo.status == 'REJECT' }"><span style="color:red">审核不通过</span></c:if>
										</td>
										<td class="">
											<a style="color: #428bca;" href="#" onclick="edit_edu(2, '${pojo.id}');">通过</a>&nbsp;&nbsp;
											<a style="color: #428bca;" href="#" onclick="edit_edu(3, '${pojo.id}');">不通过</a>
										</td>
									</tr>
								</c:forEach>
								</tbody>
							</table>
							<div id="xchart-2" style="height: 200px;"></div>
						</div>
					</div>
				</div>
				
			</div>
			<div class="col-xs-12 col-md-6">
				<div id="ow-donut" class="row">
					<div class="col-xs-4">
						<div id="morris_donut_1" style="width:120px;height:120px;"></div>
					</div>
					<div class="col-xs-4">
						<div id="morris_donut_2" style="width:120px;height:120px;"></div>
					</div>
					<div class="col-xs-4">
						<div id="morris_donut_3" style="width:120px;height:120px;"></div>
					</div>
				</div>
			</div>
		</div>
		<!--End Dashboard Tab 1-->
		<!--Start Dashboard Tab 2-->
		<div id="dashboard-clients" class="row" style="visibility: hidden; position: absolute;">
		<c:forEach var="cur" items="${certlist}" varStatus="vs">
			<div class="col-xs-12">
					<div class="box">
						<div class="box-header">
							<div class="box-name">
								<i class="fa fa-search"></i>
								<span>${cur.categoryName }</span>
							</div>
							<div class="box-icons">
								<a class="collapse-link">
									<i class="fa fa-chevron-up"></i>
								</a>
							</div>
							<div class="no-move"></div>
						</div>
						<div class="box-content">
							<c:forEach var="child" items="${cur.list}"> 
							
								<div class="col-xs-12 col-sm-4">
									<div class="box">
										<div class="box-header">
											<div class="box-name">
												<i class="fa fa-asterisk"></i>
												<span>${child.categoryName }</span>
											</div>
											<div class="box-icons">

												
											</div>
											<div class="no-move"></div>
										</div>
										<div class="box-content">
										
										<c:choose>
											<c:when test="${not empty child.fileName }">
												<div class="dz-preview dz-processing dz-image-preview dz-success dz-complete dz-message dropzone"  style="text-align: center;">  
													<div class="dz-image">
														<!--img src="data:image/png;base64,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" alt="logo1.jpg" data-dz-thumbnail=""-->
														<c:choose>
															<c:when test="${child.expired}">
																<img src="${ctx}/img/file-expired.png" class="thum_img" alt="file" data-dz-thumbnail="" id="cert_img_${child.id}">
															</c:when>
															<c:otherwise>
																<c:choose>
																	<c:when test="${child.status == 'PENDING' }">
																		<img src="${ctx}/img/file-pending.png" class="thum_img" alt="file" data-dz-thumbnail="" id="cert_img_${child.id}">
																	</c:when>
																	
																	<c:when test="${child.status == 'PASS' }">
																		<img src="${ctx}/img/file-pass.png" class="thum_img" alt="file" data-dz-thumbnail="" id="cert_img_${child.id}">
																	</c:when>
																	<c:when test="${child.status == 'PENDING' }">
																		<img src="${ctx}/img/file-reject.png" class="thum_img" alt="file" data-dz-thumbnail="" id="cert_img_${child.id}">
																	</c:when>
																	<c:when test="${child.status == 'EXPIRED'}">
																		<img src="${ctx}/img/file-expired.png" class="thum_img" alt="file" data-dz-thumbnail="" id="cert_img_${child.id}">
																	</c:when>
																</c:choose>
															</c:otherwise>
														</c:choose>
													</div>  
		
													<div style="padding-top: 8px; ">
														
														<c:choose>
															<c:when test="${child.expired }"><span style="color:red">已过期</span></c:when>
															<c:otherwise>有效期： <span style="color:red"> ${child.startDate } </span> 至   <span style="color:red">${child.endDate } </span></c:otherwise>
														</c:choose>
													</div>
													<div style="padding-top: 8px; ">
														
														<a href="javascript:edit_cert(1,${child.id}, ${child.userId}, '${child.fileName }');" style="color: #428bca;">查看</a>&nbsp;&nbsp;
														<c:if test="${child.status != 'EXPIRED' && !child.expired}">
															<a href="javascript:edit_cert(2, ${child.id});" style="color: #428bca;">通过</a>&nbsp;&nbsp;
															<a href="javascript:edit_cert(3, ${child.id});" style="color: #428bca;">不通过</a>&nbsp;&nbsp;
														</c:if>
													<!--a href="#" onclick="edit(3,undefined);">重新上传</a-->
													</div>
												</div>
											</c:when>
											<c:otherwise>
												<div class="dz-preview dz-processing dz-image-preview dz-success dz-complete dz-message dropzone"  style="text-align: center;">  
													<div class="dz-image">
															<img src="${ctx}/img/no-file.jpg" class="thum_img" alt="file" data-dz-thumbnail="">
													</div>  

												</div>
											</c:otherwise>
										</c:choose>
										</div>
									</div>
								</div>
							</c:forEach>
							<div id="xchart-3" style="height: 750px;"></div>
						</div>
					</div>
			</div>
		</c:forEach>	
			
			
		</div>
		<!--End Dashboard Tab 2-->
		<!--Start Dashboard Tab 3-->
		
		
		<div id="dashboard-graph" class="row" style="visibility: hidden; position: absolute;" >
			<div class="col-xs-12" style="padding-top:15px;">
							<button type="button" class="btn btn-primary btn-label-left" onclick="printdoc('exp');" style="float:right">
							<span><i class="fa fa-clock-o txt-danger"></i></span>
								打印
							</button>
			</div>
			<c:forEach var="cur" items="${explist}" varStatus="vs">
				<div class="col-xs-12">
						<div class="box">
							<div class="box-header">
								<div class="box-name">
									<i class="fa fa-search"></i>
									<span>${cur.categoryName }</span>
								</div>
								<div class="box-icons">
									<a class="collapse-link">
										<i class="fa fa-chevron-up"></i>
									</a>
								</div>
								<div class="no-move"></div>
							</div>
							<div class="box-content">
								<c:forEach var="child" items="${cur.list}"> 
								
									<div class="col-xs-12 col-sm-12">
										<div class="box">
											<div class="box-header">
												<div class="box-name">
													<i class="fa fa-asterisk"></i>
													<span>${child.categoryName }</span>
												</div>
												<div class="box-icons">
	
													
												</div>
												<div class="no-move"></div>
											</div>
											<div class="box-content">
												<table id="ticker-table" class="table m-table table-bordered table-hover table-heading">
													<thead>
														<tr>
															<th>公司名称</th>
															<th>部门名称</th>
															<th>开始时间</th>
															<th>结束时间</th>
															<th>状态</th>
															<th>操作</th>
														</tr>
													</thead>
													<tbody>
														<c:forEach items="${child.list}" var="pojo">
															<tr class="odd">
																<td class=" sorting_1"><span class="span_"> &nbsp; ${pojo.company }</span></td>
																<td class="">${pojo.department }</td>
																<td class="">${pojo.startDate }</td>
																<td class="">
																	<c:choose>
																		<c:when test="${not empty pojo.endDate }">${pojo.endDate }</c:when>
																		<c:otherwise>至今</c:otherwise>
																	</c:choose> 
																 </td>
																 <td class="" id="exp_status_${pojo.id}">
																 	<c:if test="${pojo.status == 'PENDING' }">待审核</c:if>
																 	<c:if test="${pojo.status == 'PASS' }"><span style="color:green">审核通过</span></c:if>
																 	<c:if test="${pojo.status == 'REJECT' }"><span style="color:red">审核不通过</span></c:if>
																 </td>
																<td class="">
																	<a style="color: #428bca;" href="#" onclick="edit_exp(2, '${pojo.id}');">通过</a>&nbsp;&nbsp;
																	<a style="color: #428bca;" href="#" onclick="edit_exp(3, '${pojo.id}');">不通过</a>
																</td>
															</tr>
		
														</c:forEach>
							
													</tbody>
												</table>
											</div>
										</div>
									</div>
								</c:forEach>
								<div id="xchart-3" style="height: 800px;"></div>
							</div>
						</div>
				</div>
			</c:forEach>
		</div>
		<!--End Dashboard Tab 3-->
	</div>
	<div class="clearfix"></div>
</div>
		</div>
	</div>
</div>


<script src="${ctx}/plugins/jquery/jquery-2.1.0.min.js"></script>
<script src="${ctx}/plugins/jquery/jquery-migrate-1.2.1.min.js"></script>

<script src="${ctx}/plugins/jquery-ui/jquery-ui.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="${ctx}/plugins/bootstrap/bootstrap.min.js"></script>
<script src="${ctx}/plugins/justified-gallery/jquery.justifiedgallery.min.js"></script>
<script src="${ctx}/plugins/tinymce/tinymce.min.js"></script>
<script src="${ctx}/plugins/tinymce/jquery.tinymce.min.js"></script>
<script src="${ctx}/plugins/select2/select2.min.js"></script>

<script src="${ctx}/plugins/jquery-stamper/jquery.stamper.js"></script>

<!-- All functions for this theme + document.ready processing -->
<script src="${ctx}/plugins/dropzone/dropzone.js"></script>

<script src="${ctx}/js/bx.js"></script>

<script type="text/javascript">

function edit_cert(type, id, user, fileName){
	if(type == 1){
		var url = "${ctx}/attachment/upload/"+user+"/"+fileName;
		window.open(url,'newwindow','top=0,left=0,toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no');
	}
	if(type == 2){
		var url = '${ctx}/system/archive/checking/certcheck/' + id + ".htm?type=PASS";
		$.ajax({
			//mimeType: 'text/html; charset=utf-8', // ! Need set mimeType only when run from local file
			url: url,
			type: 'POST',
			success: function(data) {
				if(data.status == 'success'){
					$("#cert_img_"+id).attr('src','${ctx}/img/file-pass.png'); 
				}
			},
			error: function (jqXHR, textStatus, errorThrown) {
				alert(errorThrown);
			},
			dataType: "json",
			async: false
		});
	}
	if(type == 3){
		var url = '${ctx}/system/archive/checking/certcheck/' + id + ".htm?type=REJECT";
		$.ajax({
			//mimeType: 'text/html; charset=utf-8', // ! Need set mimeType only when run from local file
			url: url,
			type: 'POST',
			success: function(data) {
				if(data.status == 'success'){
					$("#cert_img_"+id).attr('src','${ctx}/img/file-reject.png'); 
				}
			},
			error: function (jqXHR, textStatus, errorThrown) {
				alert(errorThrown);
			},
			dataType: "json",
			async: false
		});
	}
}

function edit_exp(type, id){
	if(type == 2){
		var url = '${ctx}/system/archive/checking/expcheck/' + id + ".htm?type=PASS";
		$.ajax({
			//mimeType: 'text/html; charset=utf-8', // ! Need set mimeType only when run from local file
			url: url,
			type: 'POST',
			success: function(data) {
				if(data.status == 'success'){
					$("#exp_status_"+id).html('<span style="color:green">审核通过</span>'); 
				}
			},
			error: function (jqXHR, textStatus, errorThrown) {
				alert(errorThrown);
			},
			dataType: "json",
			async: false
		});
	}
	if(type == 3){
		var url = '${ctx}/system/archive/checking/expcheck/' + id + ".htm?type=REJECT";
		$.ajax({
			//mimeType: 'text/html; charset=utf-8', // ! Need set mimeType only when run from local file
			url: url,
			type: 'POST',
			success: function(data) {
				if(data.status == 'success'){
					$("#exp_status_"+id).html('<span style="color:red">审核不通过</span>'); 
				}
			},
			error: function (jqXHR, textStatus, errorThrown) {
				alert(errorThrown);
			},
			dataType: "json",
			async: false
		});
	}
}

function edit_edu(type, id){
	if(type == 2){
		var url = '${ctx}/system/archive/checking/educheck/' + id + ".htm?type=PASS";
		$.ajax({
			//mimeType: 'text/html; charset=utf-8', // ! Need set mimeType only when run from local file
			url: url,
			type: 'POST',
			success: function(data) {
				if(data.status == 'success'){
					$("#edu_status_"+id).html('<span style="color:green">审核通过</span>'); 
				}
			},
			error: function (jqXHR, textStatus, errorThrown) {
				alert(errorThrown);
			},
			dataType: "json",
			async: false
		});
	}
	if(type == 3){
		var url = '${ctx}/system/archive/checking/educheck/' + id + ".htm?type=REJECT";
		$.ajax({
			//mimeType: 'text/html; charset=utf-8', // ! Need set mimeType only when run from local file
			url: url,
			type: 'POST',
			success: function(data) {
				if(data.status == 'success'){
					$("#edu_status_"+id).html('<span style="color:red">审核不通过</span>'); 
				}
			},
			error: function (jqXHR, textStatus, errorThrown) {
				alert(errorThrown);
			},
			dataType: "json",
			async: false
		});
	}
}

function DashboardTabChecker(){
	$('#content').on('click', 'a.tab-link', function(e){
		e.preventDefault();
		$('div#dashboard_tabs').find('div[id^=dashboard]').each(function(){
			$(this).css('visibility', 'hidden').css('position', 'absolute');
		});
		var attr = $(this).attr('id');
		$('#'+'dashboard-'+attr).css('visibility', 'visible').css('position', 'relative');
		$(this).closest('.nav').find('li').removeClass('active');
		$(this).closest('li').addClass('active');
	});
}

function check(type, result, id){
	
	if(type == 'basic'){
		$("#basic_info div.sign").remove();
		var url = '${ctx}/system/archive/checking/basiccheck/' + id + ".htm?type=" + result;
		$.ajax({
			//mimeType: 'text/html; charset=utf-8', // ! Need set mimeType only when run from local file
			url: url,
			type: 'POST',
			success: function(data) {
				if(data.status == 'success'){
					if(result == 'PASS'){
						$("#basic_info").stamper({
							scale: 2,
							image: "${ctx}/img/pass.png" 
						});
					}
					else{
						$("#basic_info").stamper({
							scale: 2,
							image: "${ctx}/img/reject.png" 
						});
					}
				}
			},
			error: function (jqXHR, textStatus, errorThrown) {
				alert(errorThrown);
			},
			dataType: "json",
			async: false
		});
		
		
	}
	if(type == 'job'){
		$("#job_info div.sign").remove();
		var url = '${ctx}/system/archive/checking/jobcheck/' + id + ".htm?type=" + result;
		$.ajax({
			//mimeType: 'text/html; charset=utf-8', // ! Need set mimeType only when run from local file
			url: url,
			type: 'POST',
			success: function(data) {
				if(data.status == 'success'){
					if(result == 'PASS'){
						$("#job_info").stamper({
							scale: 2,
							image: "${ctx}/img/pass.png" 
						});
					}
					else{
						$("#job_info").stamper({
							scale: 2,
							image: "${ctx}/img/reject.png" 
						});
					}
				}
			},
			error: function (jqXHR, textStatus, errorThrown) {
				alert(errorThrown);
			},
			dataType: "json",
			async: false
		});
		
	}
	
}

function printdoc(type){
	url = "${ctx}/system/archive/checking/print/${model.id}.htm?type=" +  type;
	openwin = window.open(url,'newwindow','top=0,left=0,toolbar=no,menubar=no,scrollbars=yes,resizable=no,location=no,status=no');
}

$(document).ready(function() {
	DashboardTabChecker();
	
	<c:if test="${model.status == 'PASS'}">
	$("#basic_info").stamper({
		scale: 2,
		speed: 0,
		image: "${ctx}/img/pass.png" 
	});
	</c:if>
	<c:if test="${model.status == 'REJECT'}">
		$("#basic_info").stamper({
			scale: 2,
			speed: 0,
			image: "${ctx}/img/reject.png" 
		});
	</c:if>
	
	<c:if test="${model.jobInfo.status == 'PASS'}">
		$("#job_info").stamper({
			scale: 2,
			speed: 0,
			image: "${ctx}/img/pass.png" 
		});
	</c:if>
	<c:if test="${model.jobInfo.status == 'REJECT'}">
		$("#job_info").stamper({
			scale: 2,
			speed: 0,
			image: "${ctx}/img/reject.png" 
		});
	</c:if>
});
</script>


</body>
</html>
