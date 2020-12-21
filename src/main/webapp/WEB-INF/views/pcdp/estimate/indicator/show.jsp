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
       					<c:when test="${empty model}">
							 添加一级指标 
       					</c:when>
       					<c:otherwise>
              				 编辑指标  
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
       					<c:when test="${empty model}">
							${ctx}/system/estimate/indicator/create.htm
       					</c:when>
       					<c:otherwise>
              				 ${ctx}/system/estimate/indicator/update/${model.id}.htm
       					</c:otherwise>
					</c:choose>">
					<input name="categoryId" type="hidden" id="categoryId" value="${category.id }" />
					<input name="parentId" type="hidden" id="parentId" value="" />
					<input name="level" type="hidden" id="level" value="1" />
					
				<c:if test="${empty model}">
					<div class="form-group">
						<label class="col-sm-2 control-label">指标方案</label>
						<div class="col-sm-4">
							${category.name }
						</div>
						<label class="col-sm-2 control-label"></label>
						<div class="col-sm-4">
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-sm-2 control-label">一级指标编号</label>
						<div class="col-sm-4">
							<select class="populate placeholder" name="number" id="topindexs">
									<option value="-1">选择编号</option>
									<c:forEach var="s"  begin="1" end="30">
										<option value="${s}">${s}</option>
									</c:forEach>
								</select>
						</div>
						<label class="col-sm-2 control-label"></label>
						<div class="col-sm-4">
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-sm-2 control-label">指标类型</label>
						<div class="col-sm-4">
							<select class="populate placeholder" name="indicatorType" id="types">
									<option value="">选择类型</option>
									<option value="">其它</option>
									<option value="CAPACITY">能力</option>
									<option value="PERFORMANCE">绩效</option>
								</select>
						</div>
						<label class="col-sm-2 control-label"></label>
						<div class="col-sm-4">
						</div>
					</div>
				</c:if>
				<c:if test="${not empty model}">
					<c:choose>
       					<c:when test="${empty model.parent}">
							<div class="form-group">
								<label class="col-sm-2 control-label">一级指标编号</label>
								<div class="col-sm-4">
									<select class="populate placeholder" name="number" id="topindexs">
											<option value="-1">选择编号</option>
											<option value="1" <c:if test='${model.number == 1}'> selected="selected" </c:if>>1</option>
											<option value="2" <c:if test='${model.number == 2}'> selected="selected" </c:if>>2</option>
											<option value="3" <c:if test='${model.number == 3}'> selected="selected" </c:if>>3</option>
											<option value="4" <c:if test='${model.number == 4}'> selected="selected" </c:if>>4</option>
											<option value="5" <c:if test='${model.number == 5}'> selected="selected" </c:if>>5</option>
											<option value="6" <c:if test='${model.number == 6}'> selected="selected" </c:if>>6</option>
									</select>
								</div>
								<label class="col-sm-2 control-label"></label>
								<div class="col-sm-4">
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-sm-2 control-label">指标类型</label>
								<div class="col-sm-4">
									<select class="populate placeholder" name="indicatorType" id="types">
											<option value="">选择类型</option>
											<option value="">其它</option>
											<option value="CAPACITY" <c:if test="${model.indicatorType == 'CAPACITY' }">selected="selected"</c:if>>能力</option>
											<option value="PERFORMANCE" <c:if test="${model.indicatorType == 'PERFORMANCE' }">selected="selected"</c:if>>绩效</option>
										</select>
								</div>
								<label class="col-sm-2 control-label"></label>
								<div class="col-sm-4">
								</div>
							</div>
       					</c:when>
       					<c:otherwise>
              				 <div class="form-group">
								<label class="col-sm-2 control-label">上级指标</label>
								<div class="col-sm-4">
									${model.parent.name }
								</div>
								<label class="col-sm-2 control-label"></label>
								<div class="col-sm-4">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">指标编号</label>
								<div class="col-sm-4">
									<div class="input-group">
									  <span class="input-group-addon" style="font-weight: bold; font-style:italic; font-size: 16;">${model.psequence}.</span>
									  <input type="text" class="form-control" placeholder="指标编号" data-toggle="tooltip" data-placement="bottom" title="指标编号" name="number" value="${model.number}">
									</div>
								</div>
								<label class="col-sm-2 control-label"></label>
								<div class="col-sm-4">
								</div>
							</div>
       					</c:otherwise>
					</c:choose>
				</c:if>
					
					<div class="form-group ">
						<label class="col-sm-2 control-label">指标名称</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" placeholder="指标名称" data-toggle="tooltip" data-placement="bottom" title="指标名称" name="name" value="${model.name }">
						</div>
						<label class="col-sm-2 control-label"></label>
						<div class="col-sm-4">
							
						</div>
					</div>
				<c:if test="${not empty model}">
					<c:if test="${not empty model.parent}">
						<div class="form-group ">
							<label class="col-sm-2 control-label">硬性指标</label>
							<div class="col-sm-4">
								<div class="toggle-switch toggle-switch-success">
									<label>
										<input type="checkbox" name="mandatory" value="YES" <c:if test="${model.mandatory == 'YES' }">checked="checked"</c:if>>
										<div class="toggle-switch-inner"></div>
										<div class="toggle-switch-switch"><i class="fa fa-check"></i></div>
									</label>
								</div>
							</div>
							<label class="col-sm-2 control-label"></label>
							<div class="col-sm-4">
								
							</div>
						</div>
					</c:if>
				</c:if>
					<div class="form-group">
						<label class="col-sm-2 control-label" for="form-styles">评估点描述</label>
						<div class="col-sm-10">
								<textarea class="form-control" rows="5" name="description">${model.description }</textarea>
						</div>
					</div>
					
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-2">
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
	$('#topindexs').select2();
	$('#types').select2();
});
</script>
</body>
</html>