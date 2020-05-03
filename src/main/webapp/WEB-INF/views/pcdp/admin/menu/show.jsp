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
					<c:choose>
       					<c:when test="${empty menu}">
							 添加菜单 
       					</c:when>
       					<c:otherwise>
              				 编辑菜单 
       					</c:otherwise>
					</c:choose>
				</div>
				<div class="box-icons">
					<a class="collapse-link">
						<i class="fa fa-chevron-up"></i>
					</a>

				</div>
				<div class="no-move"></div>
			</div>
			<div class="box-content">
				<form class="form-horizontal" role="form" method="post" action="
					<c:choose>
       					<c:when test="${empty menu}">
							${ctx}/system/admin/menu/create.htm
       					</c:when>
       					<c:otherwise>
              				 ${ctx}/system/admin/menu/update/${menu.id}.htm
       					</c:otherwise>
					</c:choose>">
					
				<c:if test="${empty menu}">
					<div class="form-group">
						<label class="col-sm-2 control-label">上级菜单</label>
						<div class="col-sm-4">
							<select class="populate placeholder" name="parentId" id="topmenus">
									<option value="0">顶级菜单</option>
									
									<c:forEach items="${topMenus}" var="menu">
										<option value="${menu.id}">${menu.name}</option>
									</c:forEach>
								</select>
						</div>
						<label class="col-sm-2 control-label">所属模块</label>
						<div class="col-sm-4">
							<select class="populate placeholder" name="moduleId" id="topmodules">
									<option value=""></option>
									<c:forEach items="${modules}" var="module">
										<option value="${module.id}">${module.name}</option>
									</c:forEach>
								</select>
						</div>
					</div>
				</c:if>
				
				<c:if test="${not empty menu.module}">
					<div class="form-group">
						<label class="col-sm-2 control-label">所属模块</label>
						<div class="col-sm-4">
							${menu.module.name }
						</div>
						<label class="col-sm-2 control-label"></label>
						<div class="col-sm-4">
							
						</div>
					</div>
				</c:if>
					
					<div class="form-group ">
						<label class="col-sm-2 control-label">名称</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" placeholder="名称" data-toggle="tooltip" data-placement="bottom" title="用户姓名" name="name" value="${menu.name }">
						</div>
						<label class="col-sm-2 control-label">URL</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" placeholder="URL" name="url" value="${menu.url }">
						</div>
					</div>
					<div class="form-group ">
						<label class="col-sm-2 control-label">代码</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" placeholder="代码" name="code" value="${menu.code }">
						</div>
						<label class="col-sm-2 control-label">顺序</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" placeholder="顺序" name="order" value="${menu.order }">
						</div>
					</div>
					<div class="form-group ">
						<label class="col-sm-2 control-label">图标</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" placeholder="图标" name="icon" value="${menu.icon }">
						</div>
						<label class="col-sm-2 control-label">类型</label>
						<div class="col-sm-4">
							<div class="checkbox-inline">
							<label>
								<input type="checkbox" name="linkType" value="YES" <c:if test="${menu.linkType == 'YES' }">checked="checked"</c:if>>
								<i class="fa fa-square-o"></i>
							</label>
						</div>
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

<!-- All functions for this theme + document.ready processing -->
<script src="${ctx}/js/bx.js"></script>

<script type="text/javascript">

$(document).ready(function() {
	$('#topmenus').select2();
	$('#topmodules').select2();
});
</script>
</body>
</html>