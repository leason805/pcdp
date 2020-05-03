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
					关联指标
				</div>
				<div class="box-icons">
					<a class="collapse-link">
						<i class="fa fa-chevron-up"></i>
					</a>

				</div>
				<div class="no-move"></div>
			</div>
			<div class="box-content">
				<form class="form-horizontal" role="form" method="post" action="${ctx}/system/estimate/indicator/save/${model.id}.htm">

					<div class="form-group">
								<label class="col-sm-2 control-label">上级指标</label>
								<div class="col-sm-4">
									${model.parent.name }
								</div>
								<label class="col-sm-2 control-label">指标编号</label>
								<div class="col-sm-4">
									${model.psequence}.${model.number}
								</div>
					</div>
							
					<div class="form-group ">
						<label class="col-sm-2 control-label">指标名称</label>
						<div class="col-sm-4">
							${model.name }
						</div>
						<label class="col-sm-2 control-label">硬性指标</label>
							<div class="col-sm-4">
								<div class="toggle-switch toggle-switch-success">
									<label>
										<input type="checkbox" name="mandatory" value="YES" disabled="disabled" <c:if test="${model.mandatory == 'YES' }">checked="checked"</c:if>>
										<div class="toggle-switch-inner"></div>
										<div class="toggle-switch-switch"><i class="fa fa-check"></i></div>
									</label>
								</div>
							</div>
					</div>
					
					<div class="form-group">
						<label class="col-sm-2 control-label" for="form-styles">评估点关联</label>
						<div class="col-sm-10">
								<div class="box-content">
				<div id="tabs">
					<ul>
						<li><a href="#tabs-1">资格证书</a></li>
						<li><a href="#tabs-2">综合经历</a></li>
						<li><a href="#tabs-3">知识考核</a></li>
						<li><a href="#tabs-4">培训签到</a></li>
						<li><a href="#tabs-5">资格等级</a></li>
					</ul>
					<div id="tabs-1">
					
						<c:forEach items="${cateList}" var="cate">
							<div class="col-xs-12">
								<div class="box">
									<div class="box-header">
										<div class="box-name">
											<i class="fa fa-home"></i>
											<span>${cate.name }</span>
										</div>
										<div class="box-icons">
											
										</div>
										<div class="no-move"></div>
									</div>
									<div class="box-content">
										<div class="card">
											<h4 class="page-header"></h4>
											
										</div>
										<div class="row form-group">
										
											<c:forEach items="${cate.children}" var="child" varStatus="status">
												<c:if test="${status.index%3==0}"><div class="col-sm-4"></c:if>
												
												<div class="checkbox">
													<label>
														<input type="checkbox"> ${child.name }
														<i class="fa fa-square-o"></i>
													</label>
												</div>
												
												<c:choose>
													<c:when test="${status.index%3==2}"></div></c:when>
													<c:when test="${fn:length(cate.children) == (status.index+1)}"></div></c:when>
													<c:otherwise></c:otherwise>
												</c:choose>
											</c:forEach>
										</div>
									</div>
								</div>
							</div>
						</c:forEach>
					</div>
					<div id="tabs-2">
					
						<c:forEach items="${expList}" var="exp">
							<div class="col-xs-12">
								<div class="box">
									<div class="box-header">
										<div class="box-name">
											<i class="fa fa-home"></i>
											<span>${exp.name }</span>
										</div>
										<div class="box-icons">
											
										</div>
										<div class="no-move"></div>
									</div>
									<div class="box-content">
										<c:forEach items="${exp.children}" var="child" varStatus="status">
											<div class="card">
												<h4 class="page-header" style="font-size: 15px;">${child.name }</h4>
											</div>
											<div class="row form-group">
												<label class="col-sm-2 control-label">1分</label>
												<div class="col-sm-4">
													<select class="populate placeholder" name="number" id="exps_${child.id}_1">
															<option value=""></option>
															<option value="1">></option>
															<option value="2">>=</option>
															<option value="3"><</option>
															<option value="4"><=</option>
															<option value="5">==</option>
														</select>
												</div>
												<div class="col-sm-4">
													<div class="input-group">
													  <input type="text" class="form-control" placeholder="">
													  <span class="input-group-addon"><i class="fa ">月</i></span>
													</div>
												</div>
											</div>
											<div class="row form-group">
												<label class="col-sm-2 control-label">2分</label>
												<div class="col-sm-4">
													<select class="populate placeholder" name="number" id="exps_${child.id }_2">
															<option value=""></option>
															<option value="1">></option>
															<option value="2">>=</option>
															<option value="3"><</option>
															<option value="4"><=</option>
															<option value="5">==</option>
														</select>
												</div>
												<div class="col-sm-4">
													<div class="input-group">
													  <input type="text" class="form-control" placeholder="">
													  <span class="input-group-addon"><i class="fa ">月</i></span>
													</div>
												</div>
											</div>
											<div class="row form-group">
												<label class="col-sm-2 control-label">3分</label>
												<div class="col-sm-4">
													<select class="populate placeholder" name="number" id="exps_${child.id }_3">
															<option value=""></option>
															<option value="1">></option>
															<option value="2">>=</option>
															<option value="3"><</option>
															<option value="4"><=</option>
															<option value="5">==</option>
														</select>
												</div>
												<div class="col-sm-4">
													<div class="input-group">
													  <input type="text" class="form-control" placeholder="">
													  <span class="input-group-addon"><i class="fa ">月</i></span>
													</div>
												</div>
											</div>
											<div class="row form-group">
												<label class="col-sm-2 control-label">4分</label>
												<div class="col-sm-4">
													<select class="populate placeholder" name="number" id="exps_${child.id }_4">
															<option value=""></option>
															<option value="1">></option>
															<option value="2">>=</option>
															<option value="3"><</option>
															<option value="4"><=</option>
															<option value="5">==</option>
														</select>
												</div>
												<div class="col-sm-4">
													<div class="input-group">
													  <input type="text" class="form-control" placeholder="">
													  <span class="input-group-addon"><i class="fa ">月</i></span>
													</div>
												</div>
											</div>
										</c:forEach>
									</div>
								</div>
							</div>										
						</c:forEach>

					</div>
					
					
					
					<div id="tabs-3">
						<div class="col-xs-12">
							<div class="box">
								<div class="box-header">
									<div class="box-name">
										<i class="fa fa-home"></i>
										<span>知识考点</span>
									</div>
									<div class="box-icons">
										
									</div>
									<div class="no-move"></div>
								</div>
								<div class="box-content">
									<div class="card">
										<select class="populate placeholder" name="number" id="kn_sections">
													<option value="" ></option>
													<c:forEach items="${sections}" var="section">
														<option value="${section.id}">${section.project.title}->${section.name}</option>
													</c:forEach>
												</select>
										<h4 class="page-header"></h4>
									</div>
									<div class="row form-group">
										
										<label class="col-sm-4 control-label">所在公司</label>
										<div class="col-sm-8">
										</div>
										
									</div>
									<div class="row form-group">
										<label class="col-sm-2 control-label">1分</label>
										<div class="col-sm-4">
											<select class="populate placeholder" name="number" id="kn_rule_1">
													<option value=""></option>
													<option value="1">></option>
													<option value="2">>=</option>
													<option value="3"><</option>
													<option value="4"><=</option>
													<option value="5">==</option>
												</select>
										</div>
										<div class="col-sm-4">
											<div class="input-group">
											  <input type="text" class="form-control" placeholder="">
											  <span class="input-group-addon"><i class="fa ">%</i></span>
											</div>
										</div>
									</div>
									<div class="row form-group">
										<label class="col-sm-2 control-label">2分</label>
										<div class="col-sm-4">
											<select class="populate placeholder" name="number" id="kn_rule_2">
													<option value=""></option>
													<option value="1">></option>
													<option value="2">>=</option>
													<option value="3"><</option>
													<option value="4"><=</option>
													<option value="5">==</option>
												</select>
										</div>
										<div class="col-sm-4">
											<div class="input-group">
											  <input type="text" class="form-control" placeholder="">
											  <span class="input-group-addon"><i class="fa ">%</i></span>
											</div>
										</div>
									</div>
									<div class="row form-group">
										<label class="col-sm-2 control-label">3分</label>
										<div class="col-sm-4">
											<select class="populate placeholder" name="number" id="kn_rule_3">
													<option value=""></option>
													<option value="1">></option>
													<option value="2">>=</option>
													<option value="3"><</option>
													<option value="4"><=</option>
													<option value="5">==</option>
												</select>
										</div>
										<div class="col-sm-4">
											<div class="input-group">
											  <input type="text" class="form-control" placeholder="">
											  <span class="input-group-addon"><i class="fa ">%</i></span>
											</div>
										</div>
									</div>
									<div class="row form-group">
										<label class="col-sm-2 control-label">4分</label>
										<div class="col-sm-4">
											<select class="populate placeholder" name="number" id="kn_rule_4">
													<option value=""></option>
													<option value="1">></option>
													<option value="2">>=</option>
													<option value="3"><</option>
													<option value="4"><=</option>
													<option value="5">==</option>
												</select>
										</div>
										<div class="col-sm-4">
											<div class="input-group">
											  <input type="text" class="form-control" placeholder="">
											  <span class="input-group-addon"><i class="fa ">%</i></span>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					
					
					<div id="tabs-4">
						<div class="col-xs-12">
							<div class="box">
								<div class="box-header">
									<div class="box-name">
										<i class="fa fa-home"></i>
										<span>签到率</span>
									</div>
									<div class="box-icons">
										
									</div>
									<div class="no-move"></div>
								</div>
								<div class="box-content">
									<div class="card">
										<h4 class="page-header"></h4>
									</div>
									<div class="row form-group">
										<label class="col-sm-2 control-label">1分</label>
										<div class="col-sm-4">
											<select class="populate placeholder" name="number" id="tr_rule_1">
													<option value=""></option>
													<option value="1">></option>
													<option value="2">>=</option>
													<option value="3"><</option>
													<option value="4"><=</option>
													<option value="5">==</option>
												</select>
										</div>
										<div class="col-sm-4">
											<div class="input-group">
											  <input type="text" class="form-control" placeholder="">
											  <span class="input-group-addon"><i class="fa ">%</i></span>
											</div>
										</div>
									</div>
									<div class="row form-group">
										<label class="col-sm-2 control-label">2分</label>
										<div class="col-sm-4">
											<select class="populate placeholder" name="number" id="tr_rule_2">
													<option value=""></option>
													<option value="1">></option>
													<option value="2">>=</option>
													<option value="3"><</option>
													<option value="4"><=</option>
													<option value="5">==</option>
												</select>
										</div>
										<div class="col-sm-4">
											<div class="input-group">
											  <input type="text" class="form-control" placeholder="">
											  <span class="input-group-addon"><i class="fa ">%</i></span>
											</div>
										</div>
									</div>
									<div class="row form-group">
										<label class="col-sm-2 control-label">3分</label>
										<div class="col-sm-4">
											<select class="populate placeholder" name="number" id="tr_rule_3">
													<option value=""></option>
													<option value="1">></option>
													<option value="2">>=</option>
													<option value="3"><</option>
													<option value="4"><=</option>
													<option value="5">==</option>
												</select>
										</div>
										<div class="col-sm-4">
											<div class="input-group">
											  <input type="text" class="form-control" placeholder="">
											  <span class="input-group-addon"><i class="fa ">%</i></span>
											</div>
										</div>
									</div>
									<div class="row form-group">
										<label class="col-sm-2 control-label">4分</label>
										<div class="col-sm-4">
											<select class="populate placeholder" name="number" id="tr_rule_4">
													<option value=""></option>
													<option value="1">></option>
													<option value="2">>=</option>
													<option value="3"><</option>
													<option value="4"><=</option>
													<option value="5">==</option>
												</select>
										</div>
										<div class="col-sm-4">
											<div class="input-group">
											  <input type="text" class="form-control" placeholder="">
											  <span class="input-group-addon"><i class="fa ">%</i></span>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					
					
					<div id="tabs-5">
						<div class="col-xs-12">
							<div class="box">
								<div class="box-header">
									<div class="box-name">
										<i class="fa fa-home"></i>
										<span>签到率</span>
									</div>
									<div class="box-icons">
										
									</div>
									<div class="no-move"></div>
								</div>
								<div class="box-content">
									<div class="card">
										<h4 class="page-header"></h4>
									</div>
									<div class="row form-group">
										<label class="col-sm-2 control-label">1分</label>
										<div class="col-sm-4">
											<select class="populate placeholder" name="number" id="tr_rule_1">
													<option value=""></option>
													<option value="1">></option>
													<option value="2">>=</option>
													<option value="3"><</option>
													<option value="4"><=</option>
													<option value="5">==</option>
												</select>
										</div>
										<div class="col-sm-4">
											<div class="input-group">
											  <input type="text" class="form-control" placeholder="">
											  <span class="input-group-addon"><i class="fa ">%</i></span>
											</div>
										</div>
									</div>
									<div class="row form-group">
										<label class="col-sm-2 control-label">2分</label>
										<div class="col-sm-4">
											<select class="populate placeholder" name="number" id="tr_rule_2">
													<option value=""></option>
													<option value="1">></option>
													<option value="2">>=</option>
													<option value="3"><</option>
													<option value="4"><=</option>
													<option value="5">==</option>
												</select>
										</div>
										<div class="col-sm-4">
											<div class="input-group">
											  <input type="text" class="form-control" placeholder="">
											  <span class="input-group-addon"><i class="fa ">%</i></span>
											</div>
										</div>
									</div>
									<div class="row form-group">
										<label class="col-sm-2 control-label">3分</label>
										<div class="col-sm-4">
											<select class="populate placeholder" name="number" id="tr_rule_3">
													<option value=""></option>
													<option value="1">></option>
													<option value="2">>=</option>
													<option value="3"><</option>
													<option value="4"><=</option>
													<option value="5">==</option>
												</select>
										</div>
										<div class="col-sm-4">
											<div class="input-group">
											  <input type="text" class="form-control" placeholder="">
											  <span class="input-group-addon"><i class="fa ">%</i></span>
											</div>
										</div>
									</div>
									<div class="row form-group">
										<label class="col-sm-2 control-label">4分</label>
										<div class="col-sm-4">
											<select class="populate placeholder" name="number" id="tr_rule_4">
													<option value=""></option>
													<option value="1">></option>
													<option value="2">>=</option>
													<option value="3"><</option>
													<option value="4"><=</option>
													<option value="5">==</option>
												</select>
										</div>
										<div class="col-sm-4">
											<div class="input-group">
											  <input type="text" class="form-control" placeholder="">
											  <span class="input-group-addon"><i class="fa ">%</i></span>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
						</div>
					</div>
					
					
					<div class="form-group ">
						<div class="col-sm-offset-2 col-sm-4">
							<button type="submit" class="btn btn-primary btn-label-left">
							<span><i class="fa fa-clock-o"></i></span>
								确定
							</button>
						</div>
						<div class="col-sm-4">
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
	$("#tabs").tabs();
	
	$('#kn_sections').select2();
	$('#kn_rule_1').select2();
	$('#kn_rule_2').select2();
	$('#kn_rule_3').select2();
	$('#kn_rule_4').select2();
	
	$('#tr_rule_1').select2();
	$('#tr_rule_2').select2();
	$('#tr_rule_3').select2();
	$('#tr_rule_4').select2();
	
	
	<c:forEach items="${expList}" var="exp">
		<c:forEach items="${exp.children }" var="child">
			$('#exps_${child.id }_1').select2();
			$('#exps_${child.id }_2').select2();
			$('#exps_${child.id }_3').select2();
			$('#exps_${child.id }_4').select2();
		</c:forEach>
	</c:forEach>
});
</script>
</body>
</html>