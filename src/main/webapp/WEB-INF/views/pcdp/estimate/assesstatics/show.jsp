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
					评估明细
				</div>
				<div class="box-icons">
					<a class="collapse-link">
						<i class="fa fa-chevron-up"></i>
					</a>

				</div>
				<div class="no-move"></div>
			</div>
			
			
					
			<div class="box-content">
				<form class="form-horizontal" role="form" method="post">
					<div class="form-group ">
						<label class="col-sm-2 control-label"><h4>签派员姓名</h4></label>
						<div class="col-sm-4">
							<h3>${model.user.name }</h3>
						</div>
						<label class="col-sm-2 control-label"><h4>评估人数</h4></label>
						<div class="col-sm-4">
							<h3>${statics.userSize} 位</h3>
						</div>
					</div>

					<div class="box-content no-padding table-responsive" style="margin-bottom:20px;">
						<table aria-describedby="datatable-m_info" class="table table-bordered  table-hover table-heading table-datatable dataTable" >
							<thead>
								<tr role="row">
									<th aria-sort="" colspan="1" rowspan="1" aria-controls="datatable-m" tabindex="0" role="columnheader" class="">最终平均得分</th>
									<c:forEach var="s"  begin="1" end="${statics.userSize}">
										<th class="">最终专家${s}得分 </th>
									</c:forEach>
								</tr>
							</thead>
							
							<tbody aria-relevant="all" aria-live="polite" role="alert">
								<tr role="row">
										<c:forEach items="${statics.scoreList}" var="score">
											<td><h3>${score}</h3></td>
										</c:forEach>
								</tr>
							</tbody>
						</table>
					</div>
					
					<div class="box-content no-padding table-responsive">
						
						
						<table aria-describedby="datatable-m_info" class="table table-bordered  table-hover table-heading table-datatable dataTable" id="datatable-m">
							<thead>
								<tr role="row">
									<th aria-sort="" colspan="1" rowspan="1" aria-controls="datatable-m" tabindex="0" role="columnheader" class="">序号</th>
									<th colspan="1" rowspan="1" aria-controls="datatable-m" tabindex="0" role="columnheader" class="">评估指标</th>
									<th colspan="1" rowspan="1" aria-controls="datatable-m" tabindex="0" role="columnheader" class="">综合得分</th>
									<c:forEach var="s"  begin="1" end="${statics.userSize}">
										<th class="">专家${s} </th>
									</c:forEach>
								</tr>
							</thead>
							
							<tbody aria-relevant="all" aria-live="polite" role="alert">
								<c:set var="list" value="${statics.list}" scope="request" /><!-- 注意此处，子列表覆盖treeList，在request作用域 -->  
								<c:import url="tag.jsp" />
							</tbody>
						</table>
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
});
</script>
</body>
</html>
