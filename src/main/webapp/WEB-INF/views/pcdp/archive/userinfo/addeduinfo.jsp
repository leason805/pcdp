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
		<link href="http://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css" rel="stylesheet">
		<link href='http://fonts.googleapis.com/css?family=Righteous' rel='stylesheet' type='text/css'>
		<link href="${ctx}/plugins/fancybox/jquery.fancybox.css" rel="stylesheet">
		<link href="${ctx}/plugins/fullcalendar/fullcalendar.css" rel="stylesheet">
		<link href="${ctx}/plugins/xcharts/xcharts.min.css" rel="stylesheet">
		<link href="${ctx}/plugins/select2/select2.css" rel="stylesheet">
		<link href="${ctx}/plugins/foundation-datepicker/foundation-datepicker.css" rel="stylesheet">
		<link href="${ctx}/css/style.css" rel="stylesheet">
		<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
		<!--[if lt IE 9]>
				<script src="http://getbootstrap.com/docs-assets/js/html5shiv.js"></script>
				<script src="http://getbootstrap.com/docs-assets/js/respond.min.js"></script>
		<![endif]-->
	</head>
<body>

<div class="row">
	<div class="col-xs-12 col-sm-12">
		<div class="box">
			<div class="box-header">
				<div class="box-name">
					<i class="fa fa-edit"></i>
					 添加教育经历
				</div>
				<div class="box-icons">
					<a class="collapse-link">
						<i class="fa fa-chevron-up"></i>
					</a>

				</div>
				<div class="no-move"></div>
			</div>
			<div class="box-content">
				<form class="form-horizontal" role="form" method="post" action="${ctx}/system/archive/userinfo/createeduinfo.htm">
					
					<input type="hidden" name="userId" value="${model.id}"/>
					
					<div class="form-group ">
						<label class="col-sm-2 control-label">学校名称</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" placeholder="学校名称" data-toggle="tooltip" data-placement="bottom" title="学校名称" name="university" value="">
						</div>
						<label class="col-sm-2 control-label"></label>
						<div class="col-sm-4">
							
						</div>
					</div>
					<div class="form-group ">
						<label class="col-sm-2 control-label">专业</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" placeholder="专业" data-toggle="tooltip" data-placement="bottom" title="专业" name="major" value="">
						</div>
						<label class="col-sm-2 control-label">学历</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" placeholder="大专/本科/研究生等" data-toggle="tooltip" data-placement="bottom" title="学历" name="degree" value="">
						</div>
					</div>
					
					<div class="form-group ">
						<label class="col-sm-2 control-label">开始时间</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" placeholder="开始时间" data-toggle="tooltip" data-placement="bottom" title="开始时间" id="startDate" name="startDate" value="" readonly="readonly">
						</div>
						<label class="col-sm-2 control-label">结束时间</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" placeholder="留空表示至今" data-toggle="tooltip" data-placement="bottom" title="结束时间" id="endDate" name="endDate" value="" readonly="readonly">
						</div>
					</div>

					<div class="form-group">
						<div class="col-sm-offset-4 col-sm-2">
							<button type="submit" class="btn btn-primary btn-label-left">
							<span><i class="fa fa-clock-o"></i></span>
								确定
							</button>
						</div>
						<div class="col-sm-2">
							<button type="button" class="btn btn-default btn-label-left" onclick="closeBoxFromWin();">
							<span><i class="fa fa-clock-o txt-danger"></i></span>
								取消
							</button>
						</div>
					</div>
				</form>
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

<script src="${ctx}/plugins/foundation-datepicker/foundation-datepicker.js"></script>
<script src="${ctx}/plugins/foundation-datepicker/foundation-datepicker.zh-CN.js"></script>
<!-- All functions for this theme + document.ready processing -->
<script src="${ctx}/js/bx.js"></script>

<script type="text/javascript">

$(document).ready(function() {
	$('#startDate').fdatepicker({
		format: 'yyyymmdd'
	});
	$('#endDate').fdatepicker({
		format: 'yyyymmdd'
	});
});
</script>
</body>
</html>
