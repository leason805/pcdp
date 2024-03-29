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

		<link href="${ctx}/plugins/jquery-easyui/themes/default/easyui.css"  rel="stylesheet" />
		<link href="${ctx}/plugins/jquery-easyui/themes/icon.css"  rel="stylesheet" />
		
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
					<span>安排培训人员
					</span>
				</div>
				<div class="box-icons">
					<a class="collapse-link">
						<i class="fa fa-chevron-up"></i>
					</a>

				</div>
				<div class="no-move"></div>
			</div>
			<div class="box-content">
				<form class="form-horizontal" id="mForm" role="form" method="post" action="${ctx}/system/training/arrange/update/${model.id}.htm">
					<input name="userIds" type="hidden" id="userIds" value="" />
					<div class="form-group ">
						<label class="col-sm-2 control-label">培训名称</label>
						<div class="col-sm-4">
							${model.name }
						</div>
						<label class="col-sm-2 control-label">培训时间</label>
						<div class="col-sm-4">
							${model.courseDate } &nbsp; ${model.courseTime }
						</div>
					</div>
					
					<div class="form-group ">
						<label class="col-sm-2 control-label">培训地点 </label>
						<div class="col-sm-10">
							${model.address }
						</div>
					</div>

					<div class="col-sm-12" style="margin: 10px 0px 20px 45px">
						<div class="easyui-layout" style="width:700px;height:400px;">
							<div data-options="region:'east',split:true" title="已关联用户" style="width:330px;">
								<table id="assignedusers" class="easyui-datagrid" style="height:370px"
									data-options="rownumbers:true,singleSelect:false,url:'${ctx}/system/training/arrange/loaduser.htm?id=${model.id}&type=assigned',method:'get'">
									<thead>
										<tr>
											<th data-options="field:'id',checkbox:true"></th>
											<th data-options="field:'account',width:100">登录ID</th>
											<th data-options="field:'realName',width:145">真实姓名</th>
										</tr>
									</thead>
								</table>
							</div>
							<div data-options="region:'west',split:true" title="未关联用户" style="width:330px;">
								<table id="unassignedusers" class="easyui-datagrid" style="height:370px"
									data-options="rownumbers:true,singleSelect:false,url:'${ctx}/system/training/arrange/loaduser.htm?id=${model.id}&type=unassigned',method:'get'">
									<thead>
										<tr>
											<th data-options="field:'id',checkbox:true"></th>
											<th data-options="field:'account',width:100">登录ID</th>
											<th data-options="field:'realName',width:145">真实姓名</th>
										</tr>
									</thead>
								</table>
							</div>
							<div region="center" style="overflow:hidden;text-align: center;background-color: #efefef;">
								<div style="margin-top: 170px">
									<a href="javascript:void(0)" class="easyui-linkbutton" plain="true" iconCls="icon-next" onclick="update('add');"></a>
									<a href="javascript:void(0)" class="easyui-linkbutton" plain="true" iconCls="icon-previous" onclick="update('remove');"></a><br/>
					
								</div>
							</div>
						</div>
					</div>
				

					<div class="form-group">
						<div class="col-sm-offset-4 col-sm-2">
							<button type="button" class="btn btn-primary btn-label-left" onclick="save();">
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
<!-- All functions for this theme + document.ready processing -->
<script src="${ctx}/js/bx.js"></script>

<script type="text/javascript">

$(document).ready(function() {

});

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
		$.messager.confirm('确认信息', '当前培训课程没有指定任何参加人员，确定继续保存？', function(r){
			if (r){
				jQuery("#mForm").submit();
			}
		});
	}
	else{
		jQuery("#mForm").submit();
	}
	
}
</script>
</body>
</html>