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
		<title>考试</title>
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
		
		<link href="${ctx}/plugins/jquery-easyui/themes/default/easyui.css"  rel="stylesheet" />
		<link href="${ctx}/plugins/jquery-easyui/themes/icon.css"  rel="stylesheet" />

		<link href="${ctx}/plugins/jquery-ui-timepicker-addon/jquery-ui-timepicker-addon.min.css" rel="stylesheet">
		
		<link href="${ctx}/css/style.css" rel="stylesheet">
		<link href="${ctx}/css/exam.css" rel="stylesheet">
		<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
		<!--[if lt IE 9]>
				<script src="http://getbootstrap.com/docs-assets/js/html5shiv.js"></script>
				<script src="http://getbootstrap.com/docs-assets/js/respond.min.js"></script>
		<![endif]-->
		
		
		<style type="text/css">
			@media print{
				.noprint{
				display:none
				}
			}
		</style>
		
	</head>
<body style="font-size: 12px;">

<c:forEach items="${list}" var="item" varStatus="ind">

	<div class="row">
		<div class="col-xs-12">
			<div class="box">
				<div class="box-header">
					<div class="box-name">
						<i class="fa fa-linux"></i>
						${paper.name}	
					</div>
					<div class="box-icons" style="padding-right:20px;">
						${date}-#${ind.index+1}
					</div>
					<div class="no-move"></div>
				</div>
				<div class="box-content" style="padding:5px">
					<table aria-describedby="datatable-m_info" class="table table-bordered  table-hover table-heading table-datatable dataTable" id="datatable-m" >
						<thead>
							
							<tr role="row">
								<th aria-sort="" rowspan="1" aria-controls="datatable-m" tabindex="0" role="columnheader" class="">单/多选题&nbsp;<span></span></th>
							</tr>
						</thead>
					</table>
				</div>
				
				<div class="box-content" style="padding:5px">
					<table aria-describedby="datatable-m_info" class="table table-bordered  table-hover table-heading table-datatable dataTable" id="datatable-m" >

						<tbody aria-relevant="all" aria-live="polite" role="alert">
							<c:forEach var="question" items="${item.questions}" varStatus="vs">   
								<tr class="odd" bgcolor="#C0C0C0">
									<td class=" sorting_1"><span class="span_">${vs.index+1}. ${question.question }</span></td>
									
								</tr> 
								<tr class="odd">
									<td class=" sorting_1">
										<c:forEach items="${question.options}" var="option">
											<c:choose>
												<c:when test="${question.questionType == 'SINGLE'}">
													<div class="radio" style="padding-left:10px;">
														<label>
															<input type="radio" value="${question.id}_${option.id}" name="q_${question.id}">&nbsp;&nbsp;&nbsp;&nbsp;${option.code}.&nbsp;${option.content }
															<i class="fa fa-circle-o small"></i>
														</label>
													</div>
												</c:when>
												<c:otherwise>
													<div class="checkbox" style="padding-left:10px;">
														<label>
															<input type="checkbox" value="${question.id}_${option.id}" name="q_${question.id}">&nbsp;&nbsp;&nbsp;&nbsp;${option.code}.&nbsp;${option.content }
															<i class="fa fa-square-o small"></i>
														</label>
													</div>
												</c:otherwise>
											</c:choose>
											
											
												
										</c:forEach>
									</td>
								</tr> 
							</c:forEach> 
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	
	<p style="page-break-after:always;"></p>
	
	<div class="row">
		<div class="col-xs-12 col-sm-12 col-md-12">
			<div class="box">
				<div class="box-content col-xs-12">
					<table aria-describedby="datatable-m_info" class="table table-bordered table-striped table-hover table-heading table-datatable dataTable" id="datatable-m">
						<thead>
							<tr role="row" align="center">
								<th width="18%" height="40px;" align="center">
									考生姓名：
								</th>
								<th width="32%" height="40px;">
									
								</th>
								<th width="18%" height="40px;" align="center">
									考试日期：
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
	<div class="row">
		<div class="col-xs-12">
			<div class="box">
				<div class="box-header">
					<div class="box-name">
						<i class="fa fa-linux"></i>
						答题卡	
					</div>
					<div class="box-icons" style="padding-right:20px;">
						${date}-#${ind.index+1}
					</div>
					<div class="no-move"></div>
				</div>
				
				<div class="box-content" style="padding:5px">
					<table aria-describedby="datatable-m_info" class="table table-bordered  table-hover table-heading table-datatable dataTable" id="datatable-m" >
						
						<tbody aria-relevant="all" aria-live="polite" role="alert">
							<c:forEach var="question" items="${item.questions}" varStatus="vs">  
							
								<c:if test="${vs.index%5==0}">
									<tr class="odd">
								</c:if>
										 
									<td class=" sorting_1"><span class="span_">${vs.index+1}. <c:if test="${vs.index<9}">&nbsp;</c:if>[&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;]</span></td>
								<c:if test="${vs.index%5==4}">
									</tr> 
								</c:if>
								<c:if test="${fn:length(item.questions) == (vs.index+1)}">
									</tr> 
								</c:if>
							</c:forEach> 
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	
	<p style="page-break-after:always;"></p>
	
	<div class="row">
		<div class="col-xs-12">
			<div class="box">
				<div class="box-header">
					<div class="box-name">
						<i class="fa fa-linux"></i>
						试卷答案	
					</div>
					<div class="box-icons" style="padding-right:20px;">
						${date}-#${ind.index+1}
					</div>
					<div class="no-move"></div>
				</div>
				
				<div class="box-content table-responsive" style="padding:5px">
					<table aria-describedby="datatable-m_info" class="table table-bordered  table-hover table-heading table-datatable dataTable" id="datatable-m" >
						
						<tbody aria-relevant="all" aria-live="polite" role="alert">
							<c:forEach var="question" items="${item.questions}" varStatus="vs">   
							
								<c:if test="${vs.index%5==0}">
									<tr class="odd">
								</c:if>
										 
									<td class=" sorting_1"><span class="span_">${vs.index+1}. <c:if test="${vs.index<9}">&nbsp;</c:if>[
											<c:forEach items="${question.options}" var="option">
												<c:if test="${option.answerType == 'YES' }">${option.code }、&nbsp;</c:if>											
											</c:forEach>
										]</span>
									</td>
								<c:if test="${vs.index%5==4}">
									</tr> 
								</c:if>
								<c:if test="${fn:length(item.questions) == (vs.index+1)}">
									</tr> 
								</c:if>

							</c:forEach> 
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	
	<p style="page-break-after:always;"></p>
</c:forEach>

<div align="center">

       <p><button type="button" onclick="window.print()" class="printer noprint">打    印</button></p>
       <br/><br/>
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

<script src="${ctx}/plugins/jquery-easyui/jquery.easyui.min.js" type="text/javascript" ></script>
<script src="${ctx}/plugins/jquery-easyui/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>

<script src="${ctx}/plugins/jquery-ui-timepicker-addon/jquery-ui-timepicker-addon.min.js"></script>
<script src="${ctx}/plugins/spriteTimer/jquery.spriteTimer-1.3.6.js"></script>

<!-- All functions for this theme + document.ready processing -->
<script src="${ctx}/plugins/posfixed/posfixed.js"></script>

<script src="${ctx}/js/bx.js"></script>

<script type="text/javascript">



$(document).ready(function() {

});

</script>
</body>
</html>
