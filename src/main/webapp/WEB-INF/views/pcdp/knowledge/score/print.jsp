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
		<link href="http://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css" rel="stylesheet">
		<link href='http://fonts.googleapis.com/css?family=Righteous' rel='stylesheet' type='text/css'>
		<link href="${ctx}/plugins/fancybox/jquery.fancybox.css" rel="stylesheet">
		<link href="${ctx}/plugins/fullcalendar/fullcalendar.css" rel="stylesheet">
		<link href="${ctx}/plugins/xcharts/xcharts.min.css" rel="stylesheet">
		<link href="${ctx}/plugins/select2/select2.css" rel="stylesheet">
		
		<link href="${ctx}/plugins/jquery-easyui/themes/default/easyui.css"  rel="stylesheet" />
		<link href="${ctx}/plugins/jquery-easyui/themes/icon.css"  rel="stylesheet" />

		<link href="${ctx}/plugins/jquery-ui-timepicker-addon/jquery-ui-timepicker-addon.min.css" rel="stylesheet">
		
		<link href="${ctx}/css/style.css" rel="stylesheet">
		<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
		<!--[if lt IE 9]>
				<script src="http://getbootstrap.com/docs-assets/js/html5shiv.js"></script>
				<script src="http://getbootstrap.com/docs-assets/js/respond.min.js"></script>
		<![endif]-->
		
		<style type="text/css">
			.sorting_1 img {
			    width: 18px;
			    height: 18px;
			}
			@media print{
				.noprint{
				display:none
				}
			}
		</style>
	</head>
<body style="font-size: 13px;">

<div class="row">
	<div class="col-xs-12 col-sm-12 col-md-12">
		<div class="box">
			<div class="box-header">
				<div class="box-name">
					<i class="fa fa-edit"></i>
							 考试成绩
				</div>
				<div class="box-icons">
					<a class="collapse-link">
						<i class="fa fa-chevron-up"></i>
					</a>

				</div>
				<div class="no-move"></div>
			</div>
			<div class="box-content col-xs-12">
				<h4 class="page-header">${model.arrangeUser.user.name }</h4>
				<table aria-describedby="datatable-m_info" class="table table-bordered table-striped table-hover table-heading table-datatable dataTable" id="datatable-m">
					<thead>
						<tr role="row" >
							<th width="18%" height="40px;">
								考生签名：
							</th>
							<th width="32%" height="40px;">
								
							</th>
							<th width="18%" height="40px;">
								监考员/领导签名：
							</th>
							<th width="32%" height="40px;">
								
							</th>
						</tr>
						<tr role="row" >
							<th width="18%" height="30px;">
								试题名称：
							</th>
							<th width="32%" height="30px;">
								${model.arrangeUser.arrange.paper.name }
							</th>
							<th width="18%" height="30px;">
								考试地点：
							</th>
							<th width="32%" height="30px;">
								${model.arrangeUser.arrange.address }
							</th>
						</tr>
						<tr role="row">
							<th height="30px;">试题数量：</th>
							<th height="30px;">${model.arrangeUser.arrange.paper.size }</th>
							<th height="30px;">试题总分：</th>
							<th height="30px;">${model.arrangeUser.arrange.paper.score }</th>
						</tr>
						<tr role="row">
							<th height="30px;">考试时间：</th>
							<th height="30px;">${model.arrangeUser.arrange.examDate }</th>
							<th height="30px;">考试时长：</th>
							<th height="30px;">${model.arrangeUser.arrange.paper.minutes } (分钟)</th>
						</tr>
						<tr role="row">
							<th height="30px;">考试结果：</th>
							<th height="30px;">
								<c:choose>
									<c:when test="${model.passType == 'YES' }">通过</c:when>
									<c:otherwise>不通过</c:otherwise>
								</c:choose>
							</th>
							<th height="30px;">考试成绩：</th>
							<th height="30px;">${model.score } 分</th>
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
			<div class="" style="padding:16px;background: #FCFCFC;">
				<table aria-describedby="datatable-m_info" class="table table-bordered  table-hover table-heading table-datatable dataTable" id="datatable-m">

					
					<tbody aria-relevant="all" aria-live="polite" role="alert">
						<c:forEach var="answer" items="${model.answers}" varStatus="vs">   
							<tr class="odd" bgcolor="#C0C0C0">
								<td class=" sorting_1">
								<c:if test="${answer.correctType == 'YES'}"><img src="${ctx}/img/duih.jpg" height="20px" width="20px"/></c:if>
								<c:if test="${answer.correctType == 'NO'}"><img src="${ctx}/img/cuoh.jpg" height="20px" width="20px"/></c:if>
								<span class="span_">${vs.index+1}. ${answer.question.question }</span>
							</td>
								
							</tr> 
							<tr class="odd">
								<td class=" sorting_1" style="padding: 2px;">
									<c:forEach items="${answer.question.options}" var="option">
										<c:choose>
											<c:when test="${answer.question.questionType == 'SINGLE'}">
												<div class="radio" style="padding-left:10px;">
													<label>
														<input type="radio" value="" name="q_${option.id }" disabled="disabled" <c:forEach items="${answer.options}" var="ansop"><c:if test="${ansop.id == option.id }">checked="checked"</c:if></c:forEach>>&nbsp;&nbsp;&nbsp;&nbsp;${option.code}.&nbsp;${option.content }
														<i class="fa fa-circle-o small"></i>
													</label>
												</div>
											</c:when>
											<c:otherwise>
												<div class="checkbox" style="padding-left:10px;">
													<label>
														<input type="checkbox" value="" name="q_" disabled="disabled" <c:forEach items="${answer.options}" var="ansop"><c:if test="${ansop.id == option.id }">checked="checked"</c:if></c:forEach>>&nbsp;&nbsp;&nbsp;&nbsp;${option.code}.&nbsp;${option.content }
														<i class="fa fa-square-o small"></i>
													</label>
												</div>
											</c:otherwise>
										</c:choose>
									</c:forEach>
									
									<div style="padding-left:15px; padding-top: 10px;">
										正确答案：
										<c:forEach items="${answer.question.options}" var="option">
											<c:if test="${option.answerType == 'YES' }"> <b> ${option.code } 、&nbsp;&nbsp; &nbsp;</c:if>
										</c:forEach>
									</div>
								</td>
							</tr> 
						</c:forEach> 
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>

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

<!-- All functions for this theme + document.ready processing -->
<script src="${ctx}/js/bx.js"></script>

<script type="text/javascript">

function runWhenFinished() {
	setAnswer();
	$.messager.alert('时间已到','时间已到，系统将自动提交答案！');
	setTimeout("autoSubmit()",3000); 
}

var submitted = false;
function autoSubmit(){
	jQuery("#mForm").submit();
}

function submitAnswer(){
	$.messager.confirm('交卷', '确定提交答案？', function(r){
		if (r){
			setAnswer();
			jQuery("#mForm").submit();
		}
	});
}

function setAnswer(){
	var array= new Array();
	$('input:radio:checked').each(function(){
		array.push($(this).val());
	});
	$('input:checkbox:checked').each(function(){
		array.push($(this).val());
	});
	$('#answers').val(array.join(','));
}

$(document).ready(function() {

});
</script>
</body>
</html>
