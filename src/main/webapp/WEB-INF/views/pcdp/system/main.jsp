<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/common.jsp"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html >
	<head>
		<meta charset="utf-8">
		<title>签派员评估系统</title>
		<meta name="description" content="description">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link href="${ctx}/plugins/bootstrap/bootstrap.css" rel="stylesheet">
		<link href="${ctx}/plugins/jquery-ui/jquery-ui.min.css" rel="stylesheet">
		<link href="http://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css" rel="stylesheet">
		<link href='http://fonts.googleapis.com/css?family=Righteous' rel='stylesheet' type='text/css'>
		<link href="${ctx}/plugins/fancybox/jquery.fancybox.css" rel="stylesheet">
		<link href="${ctx}/plugins/fullcalendar/fullcalendar.css" rel="stylesheet">
		<link href="${ctx}/plugins/xcharts/xcharts.min.css" rel="stylesheet">
		<link href="${ctx}/plugins/select2/select2.css" rel="stylesheet">
		
		<link href="${ctx}/plugins/colorbox-master/colorbox.css" rel="stylesheet">
		
		<link href="${ctx}/css/coolShow.css" rel="stylesheet">
		
		<link href="${ctx}/css/style.css" rel="stylesheet">
		
		<link href="${ctx}/css/custom.css" rel="stylesheet">
		<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
		<!--[if lt IE 9]>
				<script src="http://getbootstrap.com/docs-assets/js/html5shiv.js"></script>
				<script src="http://getbootstrap.com/docs-assets/js/respond.min.js"></script>
		<![endif]-->
	</head>
<body>
<!--Start Header-->
<div id="screensaver">
	<canvas id="canvas"></canvas>
	<i class="fa fa-lock" id="screen_unlock"></i>
</div>
<div id="modalbox">
	<!--div class="devoops-modal">
		<div class="devoops-modal-header">
			<div class="modal-header-name">
				<span>Basic table</span>
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
	</div-->
</div>
<header class="navbar">
	<div class="container-fluid expanded-panel">
		<div class="row">
			<div id="logo" class="col-xs-12 col-sm-2">
				<a href="#">签派员评估系统</a>
			</div>
			<div id="top-panel" class="col-xs-12 col-sm-10">
				<div class="row">
					<div class="col-xs-8 col-sm-4">
						<a href="#" class="show-sidebar">
						  <i class="fa fa-bars"></i>
						</a>
						<div id="search">
							<input type="text" placeholder="search"/>
							<i class="fa fa-search"></i>
						</div>
					</div>
					<div class="col-xs-4 col-sm-8 top-panel-right">
						<ul class="nav navbar-nav pull-right panel-menu">
							<!--li class="hidden-xs">
								<a href="index.html" class="modal-link">
									<i class="fa fa-bell"></i>
									<span class="badge">7</span>
								</a>
							</li>
							<li class="hidden-xs">
								<a class="ajax-link" href="${ctx}/ajax/calendar.html">
									<i class="fa fa-calendar"></i>
									<span class="badge">7</span>
								</a>
							</li>
							<li class="hidden-xs">
								<a href="${ctx}/ajax/page_messages.html" class="ajax-link">
									<i class="fa fa-envelope"></i>
									<span class="badge">7</span>
								</a>
							</li-->
							<li class="dropdown">
								<a href="#" class="dropdown-toggle account" data-toggle="dropdown">
									<div class="avatar">
										<img src="${ctx}/img/user.png" class="img-rounded" alt="avatar" />
									</div>
									<i class="fa fa-angle-down pull-right"></i>
									<div class="user-mini pull-right">
										<span ></span>
										<span class="welcome">欢迎，${userInfo.userName }</span>
									</div>
								</a>
								<ul class="dropdown-menu">
									<!--li>
										<a href="#">
											<i class="fa fa-user"></i>
											<span class="hidden-sm text">Profile</span>
										</a>
									</li-->
									<li>
										<a href="javascript:changepwd();">
											<i class="fa fa-cog"></i>
											<span class="hidden-sm text">修改密码</span>
										</a>
									</li>
									<li>
										<a href="${ctx}/system/logout.htm">
											<i class="fa fa-power-off"></i>
											<span class="hidden-sm text">退出</span>
										</a>
									</li>
								</ul>
							</li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
</header>
<!--End Header-->
<!--Start Container-->
<div id="main" class="container-fluid">
	
	<div class = "main" >
		<div id = "coolShow" ></div>	
		<div id="handBar">
			<c:forEach items="${modules}" var="module">
				<div class="col-xs-12 col-sm-3">
					<div class="box box-pricing">
						
						<div class="box-content no-padding" style="text-align:center">
							<span><a href="${ctx}${module.url}"><img src="${ctx}/img/${module.icon}" data-img="0"></a></span>
							<div class="row-fluid bg-default">
								<div class="col-sm-12">
									${module.name}
								</div>
								<div class="clearfix"></div>
							</div>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>


<div class="main">
	<div id="coolShow1"></div>
	<div id="handBar">
	</div>
</div>


</div>
<!--End Container-->
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<!--<script src="http://code.jquery.com/jquery.js"></script>-->
<script src="${ctx}/plugins/jquery/jquery-2.1.1.min.js"></script>
<script src="${ctx}/plugins/jquery/jquery-migrate-1.2.1.min.js"></script>

<script src="${ctx}/plugins/jquery-ui/jquery-ui.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="${ctx}/plugins/bootstrap/bootstrap.min.js"></script>
<script src="${ctx}/plugins/justified-gallery/jquery.justifiedgallery.min.js"></script>
<script src="${ctx}/plugins/tinymce/tinymce.min.js"></script>
<script src="${ctx}/plugins/tinymce/jquery.tinymce.min.js"></script>
<!-- All functions for this theme + document.ready processing -->

<script src="${ctx}/plugins/datatables/jquery.dataTables.js"></script>
<script src="${ctx}/plugins/datatables/ZeroClipboard.js"></script>
<script src="${ctx}/plugins/datatables/TableTools.js"></script>
<script src="${ctx}/plugins/datatables/dataTables.bootstrap.js"></script>
<script src="${ctx}/plugins/datatables/fnReloadAjax.js"></script>

<script src="${ctx}/js/devoops.js"></script>
<script src="${ctx}/js/bx.js"></script>

<script src="${ctx}/plugins/colorbox-master/jquery.colorbox-min.js"></script>
<script src="${ctx}/plugins/coolShow/coolShow.js"></script>


<script type="text/javascript">
// Run Datables plugin and create 3 variants of settings
var oTable;
var dashboard_url;

$(document).ready(function() {
	/*
	$('#coolShow1').coolShow({
		imgSrc:[
			<c:forEach items="${modules}" var="module" varStatus="status">
				'${ctx}/img/${module.icon}'
				<c:if test="${status.index < (fn:length(modules)-1) }">
					,
				</c:if>
			</c:forEach>
		       ],
		href:[
				<c:forEach items="${modules}" var="module" varStatus="status">
					'${ctx}${module.url}'
					<c:if test="${status.index < (fn:length(modules)-1) }">
						,
					</c:if>
				</c:forEach>
		      ],
		speed:40
	});
	*/
});

function changepwd(){
	var url = "${ctx}/system/password.htm";
	$.colorbox({href:url, iframe:true, width:"65%", height:"60%"});
}

</script>
</body>
</html>
