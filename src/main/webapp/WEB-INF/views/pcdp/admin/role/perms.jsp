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
					<span>关联权限</span>
				</div>
				<div class="box-icons">
					<a class="collapse-link">
						<i class="fa fa-chevron-up"></i>
					</a>

				</div>
				<div class="no-move"></div>
			</div>
			<div class="box-content">
			<form class="form-horizontal" role="form" id="mForm" method="post" action="${ctx}/system/admin/role/updateperms/${role.id}.htm">
			    <input name="permIds" type="hidden" id="permIds" value="" />
			     
			     <h4 class="page-header">${role.name }</h4>
			      		
				<div class="cont">
			      	<div class="easyui-accordion" data-options="multiple:true" style="margin:20px;width:800px;"> <!-- style="width:500px;height:300px;"-->
			      		<c:forEach items="${list}" var="parent">
							<div title="${parent.name}" style="overflow:auto;padding:10px;">
				            	<table border="1" style="width:90%;">
				            		<c:forEach items="${parent.menus}" var="menu">
				            			<tr>
					            			<td width="20%">
					            				${menu.name}
					            			</td>
					            			<td>
					            				<c:forEach items="${menu.operations}" var="operation">
					            					
					            					<input type="checkbox" name="permissions" value="${operation.id}" <c:if test="${operation.selected}">checked="checked"</c:if>/>${operation.name}
					            				</c:forEach>
					            			</td>
					            		</tr>
				            		</c:forEach>
				            	</table>
				        	</div>
						</c:forEach>
				    </div>
				
					<div class="submit" style="height:50px; TEXT-ALIGN: center;padding-TOP: 6px;">
						<button type="button" class="btn btn-primary btn-label-left" onclick="save();">
							<span><i class="fa fa-clock-o"></i></span>
							确定
						</button>
								&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="cancel" class="btn btn-default btn-label-left" onclick="closeBoxFromWin();">
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
	
	
	function save(){
		var array= new Array();
		$('input[name="permissions"]:checked').each(function(){
			array.push($(this).val());
		});
		$("#permIds").val(array.join(','));
		jQuery("#mForm").submit();
		
	}
	</script>
</body>
</html>