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

		<link href="${ctx}/plugins/jquery-easyui/themes/default/easyui.css" rel="stylesheet">
		<link href="${ctx}/plugins/jquery-easyui/themes/icon.css" rel="stylesheet">
		
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
					<span>
					评估检查单
				</div>
				<div class="box-icons">
					<a class="collapse-link">
						<i class="fa fa-chevron-up"></i>
					</a>

				</div>
				<div class="no-move"></div>
			</div>
			<div class="box-content">
				<form class="form-horizontal" id="mForm" role="form" method="post" action="${ctx}/system/estimate/assessitem/update/${model.id}.htm">
					
					<div class="form-group ">
						<label class="col-sm-2 control-label"><h4>签派员姓名</h4></label>
						<div class="col-sm-4">
							<h3>${model.assess.user.name }</h3>
						</div>
						<label class="col-sm-2 control-label"></label>
						<div class="col-sm-4">
							
						</div>
					</div>
					
					<div class="box-content no-padding table-responsive">
						<table aria-describedby="datatable-m_info" class="table table-bordered  table-hover table-heading table-datatable dataTable" id="datatable-m">
							<thead>
								<tr role="row">
									<th aria-sort="" colspan="1" rowspan="1" aria-controls="datatable-m" tabindex="0" role="columnheader" class="">序号</th>
									<th colspan="1" rowspan="1" aria-controls="datatable-m" tabindex="0" role="columnheader" class="">资质能力评估指标</th>
									<th colspan="1" rowspan="1" aria-controls="datatable-m" tabindex="0" role="columnheader" class="">评分</th>
									<th colspan="1" rowspan="1" aria-controls="datatable-m" tabindex="0" role="columnheader" class="">参考</th>
									<th colspan="1" rowspan="1" aria-controls="datatable-m" tabindex="0" role="columnheader" class="">评估点</th>
								</tr>
							</thead>
							
							<tbody aria-relevant="all" aria-live="polite" role="alert">
								<c:import url="tag.jsp" />
							</tbody>
						</table>
					</div>

					<div class="form-group">
						<div class="col-sm-offset-4 col-sm-2">
							<button type="button" class="btn btn-primary btn-label-left" onclick="saveForm();">
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

<script src="${ctx}/plugins/jquery-easyui/jquery.easyui.min.js"></script>
<script src="${ctx}/plugins/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
<!-- All functions for this theme + document.ready processing -->
<script src="${ctx}/js/bx.js"></script>

<script type="text/javascript">

$(document).ready(function() {
});

function saveForm(){
	var index = 0;
	var nameArr = new Array();
	//alert(nameArr.length);
	$("input[type='radio'][name^='ass_']").each(function(){   //每一个id为1C开头的事件都触发的事件
		
		var exist = false;
		var name = $(this).attr("name");
		for(var i=0; i<nameArr.length; i++){
			if(name == nameArr[i]){
				exist = true;
			}
		}
		if(!exist){
			nameArr[index] = name;
			index++;
		}
	});

	var size = 0;
	//alert(nameArr.length);
	for(var i=0; i<nameArr.length; i++){
		var name = nameArr[i];
		$("input[name='" + name + "']").each(function(){
			if($(this).attr("checked")){
				size++;
			}
		});
	}
	if(size == nameArr.length){
		jQuery("#mForm").submit();	
	}
	else{
		$.messager.alert('提示信息','请完整填写所有评分！');
		return;
	}
}

</script>
</body>
</html>
