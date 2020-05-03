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
<div id="modalbox">
	<div class="devoops-modal">
		<div class="devoops-modal-header">
			<div class="modal-header-name">
				<span></span>
			</div>
			<div class="box-icons">
				<a class="close-link">
					<i class="fa fa-times"></i>
				</a>
			</div>
		</div>
		<div class="devoops-modal-inner">
		</div>
		<div class="devoops-modal-bottom">
		</div>
	</div>
</div>
<div class="row">
	<div class="col-xs-12 col-sm-12">
		<div class="box">
			<div class="box-header">
				<div class="box-name">
					<i class="fa fa-edit"></i>
					 编辑教育经历
				</div>
				<div class="box-icons">
					<a class="collapse-link">
						<i class="fa fa-chevron-up"></i>
					</a>

				</div>
				<div class="no-move"></div>
			</div>
			<div class="box-content">
				<form class="form-horizontal" role="form" method="post" action="${ctx}/system/archive/userinfo/updateeduinfo.htm">
					
				<c:forEach items="${model.eduInfos }" var="edu">
					<div class="col-xs-12">
						<div class="box">
							<div class="box-header">
								<div class="box-name">
									<i class="fa fa-search"></i>
									<span>${edu.university}</span>
								</div>
								<div class="box-icons">
									<a class="collapse-link">
										<i class="fa fa-chevron-up"></i>
									</a>
								</div>
								<div class="no-move"></div>
							</div>
							<div class="box-content">
								<div class="form-group ">
									<label class="col-sm-2 control-label">学校名称</label>
									<div class="col-sm-4">
										<input type="text" class="form-control" placeholder="学校名称" data-toggle="tooltip" data-placement="bottom" title="学校名称" name="university_${edu.id}" value="${edu.university}">
									</div>
									<label class="col-sm-2 control-label"></label>
									<div class="col-sm-4">
										
									</div>
								</div>
								<div class="form-group ">
									<label class="col-sm-2 control-label">专业</label>
									<div class="col-sm-4">
										<input type="text" class="form-control" placeholder="专业" data-toggle="tooltip" data-placement="bottom" title="专业" name="major_${edu.id}" value="${edu.major}">
									</div>
									<label class="col-sm-2 control-label">学历</label>
									<div class="col-sm-4">
										<input type="text" class="form-control" placeholder="大专/本科/研究生等" data-toggle="tooltip" data-placement="bottom" title="学历" name="degree_${edu.id}" value="${edu.degree}">
									</div>
								</div>
								
								<div class="form-group ">
									<label class="col-sm-2 control-label">开始时间</label>
									<div class="col-sm-4">
										<input type="text" class="form-control" placeholder="开始时间" data-toggle="tooltip" data-placement="bottom" title="开始时间" id="startDate_${edu.id}" name="startDate_${edu.id}" value="<fmt:formatDate value="${edu.startDate}" type="date" pattern="yyyyMMdd"/>" readonly="readonly">
									</div>
									<label class="col-sm-2 control-label">结束时间</label>
									<div class="col-sm-4">
										<input type="text" class="form-control" placeholder="结束时间" data-toggle="tooltip" data-placement="bottom" title="结束时间" id="endDate_${edu.id}" name="endDate_${edu.id}" value="<fmt:formatDate value="${edu.endDate}" type="date" pattern="yyyyMMdd"/>" readonly="readonly">
									</div>
								</div>
								<div class="form-group ">
									<div class="col-sm-offset-10 col-sm-2">
										<button type="button" class="btn btn-primary btn-label-left" onclick="del(${edu.id})">
										<span><i class="fa fa-clock-o"></i></span>
											删除
										</button>
									</div>
								</div>
							</div>
						</div>
					</div>
				</c:forEach>
					

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

<div style="display:none">
    	<span id="header_">删除</span>
    	<span id="inner_">确定删除吗?</span>
    	<div class="form-group" id="bottom_">
			<div class="col-sm-offset-4 col-sm-2">
				<button type="submit" class="btn btn-primary btn-label-left" onclick="deleteAction();">
					<span><i class="fa fa-clock-o"></i></span>
						确定
				</button>
			</div>
			<div class="col-sm-2">
				<button type="button" class="btn btn-default btn-label-left" onclick="CloseModalBox();">
					<span><i class="fa fa-clock-o txt-danger"></i></span>
					取消
				</button>
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

var fid;
function del(id){
	fid = id;
	var header = $('#header_').html();
	var inner = $('#inner_').html();
	var bottom = $("#bottom_").html();
	OpenWindow(header, inner, bottom);
}

function CloseModalBox(){
	var modalbox = $('#modalbox');
	modalbox.fadeOut('fast', function(){
		modalbox.find('.modal-header-name span').children().remove();
		modalbox.find('.devoops-modal-inner').children().remove();
		modalbox.find('.devoops-modal-bottom').children().remove();
		$('body').removeClass("body-expanded");
	});
}

function deleteAction(){
	if(fid){
		var url = '${ctx}/system/archive/userinfo/deleteeduinfo/' + fid + ".htm";
		$.ajax({
			//mimeType: 'text/html; charset=utf-8', // ! Need set mimeType only when run from local file
			url: url,
			type: 'POST',
			success: function(data) {
				if(data.status == 'success'){
					CloseModalBox();
					refresh();
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

function refresh(){
	window.location.href = '${ctx}/system/archive/userinfo/editeduinfo/${model.id}.htm';
	parent.LoadAjaxContent('${ctx}/system/archive/userinfo/index.htm');
}

$(document).ready(function() {
	<c:forEach items="${model.eduInfos }" var="edu">
	$('#startDate_${edu.id}').fdatepicker({format: 'yyyymmdd'});
	$('#endDate_${edu.id}').fdatepicker({format: 'yyyymmdd'});
	</c:forEach>
});
</script>
</body>
</html>
