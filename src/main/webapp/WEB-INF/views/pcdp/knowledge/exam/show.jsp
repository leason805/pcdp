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
		
		<link href="${ctx}/plugins/jquery-easyui/themes/default/easyui.css"  rel="stylesheet" />
		<link href="${ctx}/plugins/jquery-easyui/themes/icon.css"  rel="stylesheet" />

		<link href="${ctx}/plugins/jquery-ui-timepicker-addon/jquery-ui-timepicker-addon.min.css" rel="stylesheet">
		
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
							 考试签到
				</div>
				<div class="box-icons">
					<a class="collapse-link">
						<i class="fa fa-chevron-up"></i>
					</a>

				</div>
				<div class="no-move"></div>
			</div>
			<div class="box-content">
				<form class="form-horizontal" model="form" id="mForm" method="post" action="${ctx}/system/knowledge/exam/sign/${model.id}.htm">
					
					<div class="form-group ">
						<label class="col-sm-2 control-label">试题名称</label>
						<div class="col-sm-6">
							${model.arrange.paper.name }
						</div>
						<label class="col-sm-2 control-label"></label>
						<div class="col-sm-2">
							
						</div>
					</div>
					
					<div class="form-group ">
						<label class="col-sm-2 control-label">考试时间</label>
						<div class="col-sm-6">
							${model.arrange.examDate }
						</div>
						<label class="col-sm-2 control-label"></label>
						<div class="col-sm-2">
							
						</div>
					</div>
					
					<div class="form-group ">
						<label class="col-sm-2 control-label">考试地点 </label>
						<div class="col-sm-6">
							${model.arrange.address }
						</div>
						<label class="col-sm-2 control-label"></label>
						<div class="col-sm-2">
							
						</div>
					</div>
					
					<div class="form-group ">
						<label class="col-sm-2 control-label">签到码</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" placeholder="签到码" data-toggle="tooltip" data-placement="bottom" title="签到码" name="vcode" value="">
						</div>
						<label class="col-sm-2 control-label"></label>
						<div class="col-sm-2">
							
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

<script src="${ctx}/plugins/jquery-easyui/jquery.easyui.min.js" type="text/javascript" ></script>
<script src="${ctx}/plugins/jquery-easyui/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>

<script src="${ctx}/plugins/jquery-ui-timepicker-addon/jquery-ui-timepicker-addon.min.js"></script>
<script src="${ctx}/plugins/jquery-ui-timepicker-addon/i18n/jquery-ui-timepicker-zh-CN.js"></script>

<!-- All functions for this theme + document.ready processing -->
<script src="${ctx}/js/bx.js"></script>

<script type="text/javascript">



$(document).ready(function() {
	
});
</script>
</body>
</html>
