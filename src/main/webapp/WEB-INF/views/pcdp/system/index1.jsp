<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/common.jsp"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>登录</title>
		<meta name="description" content="description">
		<meta name="author" content="Evgeniya">
		<meta name="keyword" content="keywords">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link href="<%=request.getContextPath()%>/plugins/bootstrap/bootstrap.css" rel="stylesheet">
		<link href="<%=request.getContextPath()%>/css/font-awesome.css" rel="stylesheet">
		<link href='<%=request.getContextPath()%>/css/G_Font_Righteous.css' rel='stylesheet' type='text/css'>
		<link href="${ctx}/css/font-awesome.css" rel="stylesheet">
		<link href='${ctx}/css/G_Font_Righteous.css' rel='stylesheet' type='text/css'>
		<link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet">
		<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
		<!--[if lt IE 9]>
				<script src="http://getbootstrap.com/docs-assets/js/html5shiv.js"></script>
				<script src="http://getbootstrap.com/docs-assets/js/respond.min.js"></script>
		<![endif]-->
	</head>
<body>
<div class="container-fluid">
	<div id="page-login" class="row">
		<div class="col-xs-12 col-md-4 col-md-offset-4 col-sm-6 col-sm-offset-3">

			<div class="box">
				<div class="box-content">
					<form action="${ctx}/system/main.htm" method="post">
					<div class="text-center">
						<h3 class="page-header">系统登录</h3>
					</div>
					
					<c:if test="${not empty message}">
						<p class="bg-danger">${message}</p>
					</c:if>
			
					<div class="form-group">
						<label class="control-label">用户名</label>
						<input type="text" class="form-control" name="username" />
					</div>
					<div class="form-group">
						<label class="control-label">密码</label>
						<input type="password" class="form-control" name="password" />
					</div>
					<div class="text-center">
						<button type="submit" class="btn btn-primary">确定</button>
					</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>
<script src="${ctx}/plugins/jquery/jquery-2.1.0.min.js"></script>
</body>
</html>
