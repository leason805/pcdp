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
       					<c:when test="${empty user}">
							 添加用户 
       					</c:when>
       					<c:otherwise>
              				 编辑用户 
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
       					<c:when test="${empty user}">
							${ctx}/system/admin/user/create.htm
       					</c:when>
       					<c:otherwise>
              				 ${ctx}/system/admin/user/update/${user.id}.htm
       					</c:otherwise>
					</c:choose>">
					<div class="form-group">
						<label class="col-sm-2 control-label">帐号</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" placeholder="帐号" data-toggle="tooltip" data-placement="bottom" title="登录帐号" name="loginID" value="${user.loginID }"/>
						</div>
						<label class="col-sm-2 control-label">所属公司</label>
						<div class="col-sm-4">
							<select class="populate placeholder" name="companyId" id="companys">
									<option value=""></option>
									<c:forEach items="${companys}" var="company">
										<option value="${company.id}" <c:if test="${user.company.id == company.id }">selected="selected"</c:if>>${company.name}</option>
									</c:forEach>
							</select>

						</div>
					</div>
					
					<div class="form-group ">
						<label class="col-sm-2 control-label">名称</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" placeholder="名称" data-toggle="tooltip" data-placement="bottom" title="用户姓名" name="name" value="${user.name }">
						</div>
						<label class="col-sm-2 control-label">密码</label>
						<div class="col-sm-4">
							<c:choose>
								<c:when test="${empty user}">
									<input type="text" class="form-control" placeholder="密码默认为123456" data-toggle="tooltip" data-placement="bottom" title="登录密码，默认为123456" name="password" value="123456">
		       					</c:when>
		       					<c:otherwise>
		              				 <input type="text" class="form-control" placeholder="留空表示不修过" data-toggle="tooltip" data-placement="bottom" title="登录密码" name="password" value="">
		       					</c:otherwise>
							</c:choose>
						</div>
					</div>
					<div class="form-group ">
						<label class="col-sm-2 control-label">邮箱</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" placeholder="邮箱" name="email" value="${user.email }">
						</div>
						<label class="col-sm-2 control-label">电话</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" placeholder="电话" name="telephone" value="${user.telephone }">
							
						</div>
						
					</div>
					
					<div class="form-group ">
						<label class="col-sm-2 control-label">
	       					<c:if test="${!empty user}">
								 创建时间 
	       					</c:if>
						</label>
						<div class="col-sm-4">
							<c:if test="${!empty user}">
								<label class="control-label">
								 ${user.createTime }
								 </label>
	       					</c:if>
						</div>
						<label class="col-sm-2 control-label"></label>
						<div class="col-sm-4">
							
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
	$('#userTypes').select2();
	$('#companys').select2();
});
</script>
</body>
</html>