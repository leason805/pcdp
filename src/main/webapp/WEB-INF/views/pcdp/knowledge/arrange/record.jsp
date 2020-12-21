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
		<title>考生记录</title>
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
		<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
		<!--[if lt IE 9]>
				<script src="http://getbootstrap.com/docs-assets/js/html5shiv.js"></script>
				<script src="http://getbootstrap.com/docs-assets/js/respond.min.js"></script>
		<![endif]-->
		
		<style type="text/css">
			.table-datatable img {
			    width: 100px;
			    height: 100px;
			}
		</style>
		
	</head>
<body>

<div class="row">
	<div class="col-xs-12">
		<div class="box">
			<div class="box-header">
				<div class="box-name">
					<i class="fa fa-linux"></i>
					考生记录详情
				</div>
				<div class="box-icons">
					
				</div>
				<div class="no-move"></div>
			</div>
			<div class="box-content no-padding table-responsive">
				<table aria-describedby="datatable-m_info" class="table table-bordered table-striped table-hover table-heading table-datatable dataTable" id="datatable-m">
					<thead>
						
						<tr role="row">
							<th aria-sort="" rowspan="1" aria-controls="datatable-m" tabindex="0" role="columnheader" class="">考生</th>
							<th aria-sort="" rowspan="1" aria-controls="datatable-m" tabindex="0" role="columnheader" class="">状态</th>
						</tr>
					</thead>
					
					<tbody aria-relevant="all" aria-live="polite" role="alert">
						<c:forEach var="cur" items="${list}" varStatus="vs">   
							<tr class="odd">
								<td class=" sorting_1" width="40%"><span class="span_">${cur.user.name }</span></td>
								<td class="" width="60%">
									<c:if test="${cur.status == 'DRAFT' }">未签到</c:if>
									<c:if test="${cur.status == 'SIGNED' }">已签到</c:if>
									<c:if test="${cur.status == 'COMPLETED' }">已完成</c:if>
									<c:if test="${cur.status == 'CANCEL' }">已取消</c:if>
									<c:if test="${cur.status == 'ABSENT' }">缺席</c:if>
								</td>
							</tr> 
						</c:forEach> 
					</tbody>
				</table>
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

function update(type){
	if("add" == type){
		var rows = $('#unassignedusers').datagrid('getSelections');
		for(var i=0; i<rows.length; i++){
			var row = rows[i];
			$('#assignedusers').datagrid('appendRow',row);
			var index = $('#unassignedusers').datagrid('getRowIndex',row);
			$('#unassignedusers').datagrid('deleteRow',index);
		}
	}
	else{
		var rows = $('#assignedusers').datagrid('getSelections');
		for(var i=0; i<rows.length; i++){
			var row = rows[i];
			$('#unassignedusers').datagrid('appendRow',row);
			var index = $('#assignedusers').datagrid('getRowIndex',row);
			$('#assignedusers').datagrid('deleteRow',index);
		}
	}
	
}

function getSelections(type){
	var array= new Array();
	var rows;
	if("assigned" == type){
		rows = $('#assignedusers').datagrid('getSelections');
	}
	else{
		rows = $('#unassignedusers').datagrid('getSelections');
	}
	if(rows && rows.length>0){
		for(var i=0; i<rows.length; i++){
			var row = rows[i];
			array.push(row.id);
		}
		return array.join(',');
	}
	return "";
}

function save(){
	
	var array= new Array();
	var rows = $('#assignedusers').datagrid('getRows');
	for(var i=0; i<rows.length; i++){
		var row = rows[i];
		array.push(row.id);
	}
	var ids = array.join(',');
	$("#userIds").val(ids);

	if(!ids){
		$.messager.confirm('确认信息', '当前没有关联任何用户，确定继续保存？', function(r){
			if (r){
				jQuery("#mForm").submit();
			}
		});
	}
	else{
		jQuery("#mForm").submit();
	}
	
}

$(document).ready(function() {
	$('#users').select2();
	$('#projects').select2();
	
	$('#examDate').datepicker({});
	
	$('#examTime').timepicker({
		hourGrid: 4,
		minuteGrid: 10,
		timeFormat: 'hh:mm tt'
	});
});
</script>
</body>
</html>
