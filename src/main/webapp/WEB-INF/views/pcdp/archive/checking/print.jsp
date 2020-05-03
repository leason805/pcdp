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
		<title></title>
		<meta name="description" content="description">
		<meta name="author" content="DevOOPS">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link href="${ctx}/plugins/bootstrap/bootstrap.css" rel="stylesheet">
		<link href="${ctx}/plugins/jquery-ui/jquery-ui.min.css" rel="stylesheet">
		<link href="http://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css" rel="stylesheet">
		<link href='http://fonts.googleapis.com/css?family=Righteous' rel='stylesheet' type='text/css'>
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

	    </style>
	</head>
<body>

<div class="row" id="content">
	<div class="col-xs-12 col-sm-12">
		<div class="box">
				<div class="col-xs-12">
							<button type="button" class="btn btn-primary btn-label-left" onclick="window.print();" style="float:right">
							<span><i class="fa fa-clock-o txt-danger"></i></span>
								打印
							</button>
				</div>
				
			
			<div class="row-fluid">
	<div id="dashboard_tabs" class="col-xs-12 col-sm-10">
		<!--Start Dashboard Tab 1-->
	<c:if test="${type == 'info' }">
		<div id="dashboard-overview" class="row" style="visibility: visible; position: relative;">
			<div id="ow-marketplace" class="col-sm-12 col-md-12">

				<h4 class="page-header">${model.user.name }</h4>
				
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
									</tr>
								</c:forEach>
								</tbody>
							</table>
							<div id="xchart-2" style="height: 200px;">
								<table aria-describedby="datatable-m_info" class="table table-bordered table-striped table-hover table-heading table-datatable dataTable" id="datatable-m">
										<thead>
											<tr role="row" align="center">
												<th width="18%" height="40px;" align="center">
													被审核人：
												</th>
												<th width="32%" height="40px;">
													
												</th>
												<th width="18%" height="40px;" align="center">
													审核人：
												</th>
												<th width="32%" height="40px;">
													
												</th>
											</tr>
										</thead>
									</table>
							</div>
						</div>
					</div>
				</div>
				
			</div>
			
		</div>
	</c:if>
		<!--End Dashboard Tab 1-->

	<c:if test="${type == 'exp' }">
		<!--Start Dashboard Tab 3-->
		
		<div id="dashboard-graph" class="row" >
			<c:forEach var="cur" items="${explist}" varStatus="vs">
				
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
															</tr>
		
														</c:forEach>
							
													</tbody>
												</table>
											</div>
										</div>
									</div>
								</c:forEach>
								<div id="xchart-3" style="height: 500px;">
									<table aria-describedby="datatable-m_info" class="table table-bordered table-striped table-hover table-heading table-datatable dataTable" style="padding-top:60px;">
										<thead>
											<tr role="row" align="center">
												<th width="18%" height="40px;" align="center">
													被审核人：
												</th>
												<th width="32%" height="40px;">
													
												</th>
												<th width="18%" height="40px;" align="center">
													审核人：
												</th>
												<th width="32%" height="40px;">
													
												</th>
											</tr>
										</thead>
									</table>
								</div>
							</div>
						
			</c:forEach>
		</div>
	</c:if>
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


$(document).ready(function() {
	
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
