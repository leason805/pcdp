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
		
		<link href="${ctx}/plugins/bootstrap-multiselect/bootstrap-multiselect.css" rel="stylesheet">
		
		<link href="${ctx}/plugins/jquery-ui-timepicker-addon/jquery-ui-timepicker-addon.min.css" rel="stylesheet">
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
				<form class="form-horizontal" id="mForm" method="post" action="
				
				<c:choose>
       					<c:when test="${empty model.associate}">
							${ctx}/system/estimate/indicatorassociate/create.htm
       					</c:when>
       					<c:otherwise>
              				 ${ctx}/system/estimate/indicatorassociate/update/${model.associate.id}.htm
       					</c:otherwise>
					</c:choose>">
					<input type="hidden" name="indicator" value="${model.id}"/>
					<input type="hidden" name="associateType" id="associateType" value="${model.associate.associateType}"/>
					<input type="hidden" name="items" id="items" value=""/>
					
					<input type="hidden" name="formula1" id="formula1" value=""/>
					<input type="hidden" name="formula2" id="formula2" value=""/>
					<input type="hidden" name="formula3" id="formula3" value=""/>
					<input type="hidden" name="formula4" id="formula4" value=""/>
					<input type="hidden" name="benchmark1" id="benchmark1" value=""/>
					<input type="hidden" name="benchmark2" id="benchmark2" value=""/>
					<input type="hidden" name="benchmark3" id="benchmark3" value=""/>
					<input type="hidden" name="benchmark4" id="benchmark4" value=""/>
					
					<input type="hidden" name="val1_1" id="val1_1" value=""/>
					<input type="hidden" name="val1_2" id="val1_2" value=""/>
					<input type="hidden" name="val1_3" id="val1_3" value=""/>
					<input type="hidden" name="val1_4" id="val1_4" value=""/>
					
					<input type="hidden" name="val2_1" id="val2_1" value=""/>
					<input type="hidden" name="val2_2" id="val2_2" value=""/>
					<input type="hidden" name="val2_3" id="val2_3" value=""/>
					<input type="hidden" name="val2_4" id="val2_4" value=""/>
					
					<input type="hidden" name="startDate" id="startDate" value=""/>
					<input type="hidden" name="endDate" id="endDate" value=""/>
					
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
						<li><a href="#tabs-1" onclick="setType('CERTIFICATION')">资格证书</a></li>
						<li><a href="#tabs-2" onclick="setType('EXPERIENCE')">综合经历</a></li>
						<li><a href="#tabs-3" onclick="setType('KNOWLEDGE')">知识考核</a></li>
						<li><a href="#tabs-4" onclick="setType('TRAINING')">培训签到</a></li>
						<li><a href="#tabs-5" onclick="setType('QUALIFICATION')">资格等级</a></li>
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
														<input type="checkbox" name="cert_item" value="${child.id }" 
														<c:if test="${model.associate.associateType == 'CERTIFICATION'}">
															<c:forEach items="${model.associate.items}" var="item">
																<c:if test="${item.itemId eq child.id}">
																	checked="checked"
																</c:if>
															</c:forEach>
														</c:if>
														> ${child.name }
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
											<div class="card">
												<select class="populate placeholder" name="exp_cate" id="exp_cate">
													<option value="" ></option>
													<c:forEach items="${exp.children}" var="child" varStatus="status">
														<option value="${child.id}">${child.name}</option>
													</c:forEach>
												</select>
												<h4 class="page-header" style="font-size: 15px;">&nbsp;</h4>
											</div>
											<div class="row form-group">
												<label class="col-sm-2 control-label">1分</label>
												<div class="col-sm-4">
													<select class="populate placeholder" name="exp_op_1" id="exp_op_1">
															<option value=""></option>
															<option value=">">></option>
															<option value=">=">>=</option>
															<option value="<"><</option>
															<option value="<="><=</option>
															<option value="==">==</option>
														</select>
												</div>
												<div class="col-sm-4">
													<div class="input-group">
													  <input type="text" class="form-control" placeholder="" name="exp_val_1" id="exp_val_1">
													  <span class="input-group-addon"><i class="fa ">月</i></span>
													</div>
												</div>
											</div>
											<div class="row form-group">
												<label class="col-sm-2 control-label">2分</label>
												<div class="col-sm-4">
													<select class="populate placeholder" name="exp_op_2" id="exp_op_2">
															<option value=""></option>
															<option value=">">></option>
															<option value=">=">>=</option>
															<option value="<"><</option>
															<option value="<="><=</option>
															<option value="==">==</option>
														</select>
												</div>
												<div class="col-sm-4">
													<div class="input-group">
													  <input type="text" class="form-control" placeholder="" name="exp_val_2" id="exp_val_2">
													  <span class="input-group-addon"><i class="fa ">月</i></span>
													</div>
												</div>
											</div>
											<div class="row form-group">
												<label class="col-sm-2 control-label">3分</label>
												<div class="col-sm-4">
													<select class="populate placeholder" name="exp_op_3" id="exp_op_3">
															<option value=""></option>
															<option value=">">></option>
															<option value=">=">>=</option>
															<option value="<"><</option>
															<option value="<="><=</option>
															<option value="==">==</option>
														</select>
												</div>
												<div class="col-sm-4">
													<div class="input-group">
													  <input type="text" class="form-control" placeholder="" name="exp_val_3" id="exp_val_3">
													  <span class="input-group-addon"><i class="fa ">月</i></span>
													</div>
												</div>
											</div>
											<div class="row form-group">
												<label class="col-sm-2 control-label">4分</label>
												<div class="col-sm-4">
													<select class="populate placeholder" name="exp_op_4" id="exp_op_4">
															<option value=""></option>
															<option value=">">></option>
															<option value=">=">>=</option>
															<option value="<"><</option>
															<option value="<="><=</option>
															<option value="==">==</option>
														</select>
												</div>
												<div class="col-sm-4">
													<div class="input-group">
													  <input type="text" class="form-control" placeholder="" name="exp_val_4" id="exp_val_4">
													  <span class="input-group-addon"><i class="fa ">月</i></span>
													</div>
												</div>
											</div>
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
										<select class="populate placeholder" name="kn_cate" id="kn_cate">
													<option value="" ></option>
													<c:forEach items="${sections}" var="section">
														<option value="${section.id}">${section.name}</option>
													</c:forEach>
												</select>
										<h4 class="page-header"></h4>
									</div>
									<div class="form-group">
										<label class="col-sm-2 control-label">有效时间</label>
										<div class="col-sm-4">
											<input type="text" class="form-control" id="kn_start_date" placeholder="起始时间">
										</div>
										<div class="col-sm-4">
											<input type="text" class="form-control" id="kn_end_date" placeholder="结束时间">
										</div>
									</div>
									<div class="row form-group">
										<label class="col-sm-2 control-label">1分</label>
										<div class="col-sm-4">
											<select class="populate placeholder" name="kn_op_1" id="kn_op_1">
													<option value=""></option>
													<option value=">">></option>
													<option value=">=">>=</option>
													<option value="<"><</option>
													<option value="<="><=</option>
													<option value="==">==</option>
												</select>
										</div>
										<div class="col-sm-4">
											<div class="input-group">
											  <input type="text" class="form-control" placeholder="" name="kn_val_1" id="kn_val_1">
											  <span class="input-group-addon"><i class="fa ">%</i></span>
											</div>
										</div>
									</div>
									<div class="row form-group">
										<label class="col-sm-2 control-label">2分</label>
										<div class="col-sm-4">
											<select class="populate placeholder" name="kn_op_2" id="kn_op_2">
													<option value=""></option>
													<option value=">">></option>
													<option value=">=">>=</option>
													<option value="<"><</option>
													<option value="<="><=</option>
													<option value="==">==</option>
												</select>
										</div>
										<div class="col-sm-4">
											<div class="input-group">
											  <input type="text" class="form-control" placeholder="" name="kn_val_2" id="kn_val_2">
											  <span class="input-group-addon"><i class="fa ">%</i></span>
											</div>
										</div>
									</div>
									<div class="row form-group">
										<label class="col-sm-2 control-label">3分</label>
										<div class="col-sm-4">
											<select class="populate placeholder" name="kn_op_3" id="kn_op_3">
													<option value=""></option>
													<option value=">">></option>
													<option value=">=">>=</option>
													<option value="<"><</option>
													<option value="<="><=</option>
													<option value="==">==</option>
												</select>
										</div>
										<div class="col-sm-4">
											<div class="input-group">
											  <input type="text" class="form-control" placeholder="" name="kn_val_3" id="kn_val_3">
											  <span class="input-group-addon"><i class="fa ">%</i></span>
											</div>
										</div>
									</div>
									<div class="row form-group">
										<label class="col-sm-2 control-label">4分</label>
										<div class="col-sm-4">
											<select class="populate placeholder" name="kn_op_4" id="kn_op_4">
													<option value=""></option>
													<option value=">">></option>
													<option value=">=">>=</option>
													<option value="<"><</option>
													<option value="<="><=</option>
													<option value="==">==</option>
												</select>
										</div>
										<div class="col-sm-4">
											<div class="input-group">
											  <input type="text" class="form-control" placeholder="" name="kn_val_4" id="kn_val_4">
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
									<div class="form-group">
										<label class="col-sm-2 control-label">有效时间</label>
										<div class="col-sm-4">
											<input type="text" class="form-control" id="tr_start_date" placeholder="起始时间">
										</div>
										<div class="col-sm-4">
											<input type="text" class="form-control" id="tr_end_date" placeholder="结束时间">
										</div>
									</div>
									<div class="row form-group">
										<label class="col-sm-2 control-label">1分</label>
										<div class="col-sm-4">
											<select class="populate placeholder" name="tr_op_1" id="tr_op_1">
													<option value=""></option>
													<option value=">">></option>
													<option value=">=">>=</option>
													<option value="<"><</option>
													<option value="<="><=</option>
													<option value="==">==</option>
												</select>
										</div>
										<div class="col-sm-4">
											<div class="input-group">
											  <input type="text" class="form-control" placeholder="" name="tr_val_1" id="tr_val_1">
											  <span class="input-group-addon"><i class="fa ">%</i></span>
											</div>
										</div>
									</div>
									<div class="row form-group">
										<label class="col-sm-2 control-label">2分</label>
										<div class="col-sm-4">
											<select class="populate placeholder" name="tr_op_2" id="tr_op_2">
													<option value=""></option>
													<option value=">">></option>
													<option value=">=">>=</option>
													<option value="<"><</option>
													<option value="<="><=</option>
													<option value="==">==</option>
												</select>
										</div>
										<div class="col-sm-4">
											<div class="input-group">
											  <input type="text" class="form-control" placeholder="" name="tr_val_2" id="tr_val_2">
											  <span class="input-group-addon"><i class="fa ">%</i></span>
											</div>
										</div>
									</div>
									<div class="row form-group">
										<label class="col-sm-2 control-label">3分</label>
										<div class="col-sm-4">
											<select class="populate placeholder" name="tr_op_3" id="tr_op_3">
													<option value=""></option>
													<option value=">">></option>
													<option value=">=">>=</option>
													<option value="<"><</option>
													<option value="<="><=</option>
													<option value="==">==</option>
												</select>
										</div>
										<div class="col-sm-4">
											<div class="input-group">
											  <input type="text" class="form-control" placeholder="" name="tr_val_3" id="tr_val_3">
											  <span class="input-group-addon"><i class="fa ">%</i></span>
											</div>
										</div>
									</div>
									<div class="row form-group">
										<label class="col-sm-2 control-label">4分</label>
										<div class="col-sm-4">
											<select class="populate placeholder" name="tr_op_4" id="tr_op_4">
													<option value=""></option>
													<option value=">">></option>
													<option value=">=">>=</option>
													<option value="<"><</option>
													<option value="<="><=</option>
													<option value="==">==</option>
												</select>
										</div>
										<div class="col-sm-4">
											<div class="input-group">
											  <input type="text" class="form-control" placeholder="" name="tr_val_4" id="tr_val_4">
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
										<span>职位等级</span>
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
											<select class="populate placeholder" name="pos_op_1" id="pos_op_1" multiple="multiple">
												<c:forEach items="${positions}" var="item">
													<option value="${item.id }">${item.name }</option>
												</c:forEach>
											</select>
										</div>
										<div class="col-sm-4">
											<select class="populate placeholder" name="tech_op_1" id="tech_op_1" multiple="multiple">
												<c:forEach items="${techs}" var="item">
													<option value="${item.id }">${item.name }</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<div class="row form-group">
										<label class="col-sm-2 control-label">2分</label>
										<div class="col-sm-4">
											<select class="populate placeholder" name="pos_op_2" id="pos_op_2" multiple="multiple">
													<c:forEach items="${positions}" var="item">
													<option value="${item.id }">${item.name }</option>
												</c:forEach>
											</select>
										</div>
										<div class="col-sm-4">
											<select class="populate placeholder" name="tech_op_2" id="tech_op_2" multiple="multiple">
												<c:forEach items="${techs}" var="item">
													<option value="${item.id }">${item.name }</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<div class="row form-group">
										<label class="col-sm-2 control-label">3分</label>
										<div class="col-sm-4">
											<select class="populate placeholder" name="pos_op_3" id="pos_op_3" multiple="multiple">
												<c:forEach items="${positions}" var="item">
													<option value="${item.id }">${item.name }</option>
												</c:forEach>
											</select>
										</div>
										<div class="col-sm-4">
											<select class="populate placeholder" name="tech_op_3" id="tech_op_3" multiple="multiple">
												<c:forEach items="${techs}" var="item">
													<option value="${item.id }">${item.name }</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<div class="row form-group">
										<label class="col-sm-2 control-label">4分</label>
										<div class="col-sm-4">
											<select class="populate placeholder" name="pos_op_4" id="pos_op_4" multiple="multiple">
												<c:forEach items="${positions}" var="item">
													<option value="${item.id }">${item.name }</option>
												</c:forEach>
											</select>
										</div>
										<div class="col-sm-4">
											<select class="populate placeholder" name="tech_op_4" id="tech_op_4" multiple="multiple">
												<c:forEach items="${techs}" var="item">
													<option value="${item.id }">${item.name }</option>
												</c:forEach>
											</select>
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
							<button type="button" class="btn btn-primary btn-label-left" onclick="setForm();">
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

<script src="${ctx}/plugins/bootstrap-multiselect/bootstrap-multiselect.js"></script>

<script src="${ctx}/plugins/jquery-ui-timepicker-addon/jquery-ui-timepicker-addon.min.js"></script>
<script src="${ctx}/plugins/jquery-ui-timepicker-addon/i18n/jquery-ui-timepicker-zh-CN.js"></script>

<!-- All functions for this theme + document.ready processing -->
<script src="${ctx}/js/bx.js"></script>

<script type="text/javascript">

function setType(type){
	$("#associateType").val(type);
}

function setForm(){
	var associateType = $("#associateType").val();
	if(associateType){
		if(associateType == 'CERTIFICATION'){
			var array= new Array();
			$("#tabs-1 input[type='checkbox'][name='cert_item']:checked").each(function(){
				array.push($(this).val());
			});
			if(array.length <= 0){
				alert("您当前已选择关联‘资格证书’栏，请选择资格证书相关选项！");
				return;
			}
			var ids = array.join(',');
			$("#items").val(ids);
			$("#formula1").val("");
			$("#formula2").val("");
			$("#formula3").val("");
			$("#formula4").val("");
			
			$("#benchmark1").val("");
			$("#benchmark2").val("");
			$("#benchmark3").val("");
			$("#benchmark4").val("");
		}
		if(associateType == 'EXPERIENCE'){
			var all = true;
			$("#tabs-2 select").each(function(){
				$(this).find("option:selected").each(function(){
					var val = $(this).val();
					if(!val){
						all = false;
					}
	                
	       		});
			});
			$("#tabs-2 input[type='text'][class='form-control']").each(function(){
				if(!$(this).val()){
					all = false;
					//alert('no input for ' + $(this).attr('name'))
				}
			});
			if(!all){
				alert("您当前已选择关联‘综合经历’栏，必须选择/填写综合经历所有选项！");
				return;
			}

			$("#items").val($("#exp_cate").val());
			$("#formula1").val($("#exp_op_1").val());
			$("#formula2").val($("#exp_op_2").val());
			$("#formula3").val($("#exp_op_3").val());
			$("#formula4").val($("#exp_op_4").val());
			
			var val_1 = $("#exp_val_1").val();
			var val_2 = $("#exp_val_2").val();
			var val_3 = $("#exp_val_3").val();
			var val_4 = $("#exp_val_4").val();
			$("#benchmark1").val(val_1);
			$("#benchmark2").val(val_2);
			$("#benchmark3").val(val_3);
			$("#benchmark4").val(val_4);
		}
		if(associateType == 'KNOWLEDGE'){
			var all = true;
			$("#tabs-3 select").each(function(){
				$(this).find("option:selected").each(function(){
					var val = $(this).val();
					if(!val){
						all = false;
					}
	                
	       		});
			});
			$("#tabs-3 input[type='text'][class='form-control']").each(function(){
				if(!$(this).val()){
					all = false;
					//alert('no input for ' + $(this).attr('name'))
				}
			});
			if(!all){
				alert("您当前已选择关联‘知识考核’栏，必须选择/填写知识考核所有选项！");
				return;
			}

			$("#items").val($("#kn_cate").val());
			
			$("#startDate").val($("#kn_start_date").val())
			$("#endDate").val($("#kn_end_date").val())
			
			$("#formula1").val($("#kn_op_1").val());
			$("#formula2").val($("#kn_op_2").val());
			$("#formula3").val($("#kn_op_3").val());
			$("#formula4").val($("#kn_op_4").val());
			
			$("#benchmark1").val($("#kn_val_1").val());
			$("#benchmark2").val($("#kn_val_2").val());
			$("#benchmark3").val($("#kn_val_3").val());
			$("#benchmark4").val($("#kn_val_4").val());
		}
		if(associateType == 'TRAINING'){
			var all = true;
			$("#tabs-4 select").each(function(){
				$(this).find("option:selected").each(function(){
					var val = $(this).val();
					if(!val){
						all = false;
					}
	                
	       		});
			});
			$("#tabs-4 input[type='text'][class='form-control']").each(function(){
				if(!$(this).val()){
					all = false;
					//alert('no input for ' + $(this).attr('name'))
				}
			});
			if(!all){
				alert("您当前已选择关联‘培训签到’栏，必须选择/填写培训签到所有选项！");
				return;
			}
			
			$("#items").val("");
			
			$("#startDate").val($("#tr_start_date").val());
			$("#endDate").val($("#tr_end_date").val());
			
			$("#formula1").val($("#tr_op_1").val());
			$("#formula2").val($("#tr_op_2").val());
			$("#formula3").val($("#tr_op_3").val());
			$("#formula4").val($("#tr_op_4").val());
			
			$("#benchmark1").val($("#tr_val_1").val());
			$("#benchmark2").val($("#tr_val_2").val());
			$("#benchmark3").val($("#tr_val_3").val());
			$("#benchmark4").val($("#tr_val_4").val());
		}
		if(associateType == 'QUALIFICATION'){
			var all = true;
			/*
			$("#tabs-5 select").each(function(){
				$(this).find("option:selected").each(function(){
					var val = $(this).val();
					if(!val){
						all = false;
					}
	                
	       		});
			});
			$("#tabs-5 input[type='text'][class='select2-input']").each(function(){
				if(!$(this).val()){
					all = false;
					//alert('no input for ' + $(this).attr('name'))
				}
			});
			*/
			if(!$("#pos_op_1").val() || !$("#pos_op_2").val() || !$("#pos_op_3").val() || !$("#pos_op_4").val()){
				all = false;
			}
			
			if(!$("#tech_op_1").val() || !$("#tech_op_2").val() || !$("#tech_op_3").val() || !$("#tech_op_4").val()){
				all = false;
			}
			
			if(!all){
				alert("您当前已选择关联‘资格等级’栏，必须选择/填写资格等级所有选项！");
				return;
			}

			$("#items").val("");

			$("#val1_1").val($("#pos_op_1").val());
			$("#val1_2").val($("#pos_op_2").val());
			$("#val1_3").val($("#pos_op_3").val());
			$("#val1_4").val($("#pos_op_4").val());
			
			
			$("#val2_1").val($("#tech_op_1").val());
			$("#val2_2").val($("#tech_op_2").val());
			$("#val2_3").val($("#tech_op_3").val());
			$("#val2_4").val($("#tech_op_4").val());
		}
	}
	else{
		alert('还没有设置任何关联！');
		return;
	}
	
	jQuery("#mForm").submit();
}

$(document).ready(function() {
	<c:choose>
		<c:when test="${model.associate.associateType == 'EXPERIENCE'}">
			<c:forEach items="${model.associate.items}" var="item" varStatus="status">
				$("#exp_cate option[value='${item.itemId}']").attr("selected","selected");
				
				<c:if test="${item.score eq 1}">
					$("#exp_op_1 option[value='${item.formula}']").attr("selected","selected");
					$("#exp_val_1").val('${item.benchmark}');
				</c:if>
				<c:if test="${item.score eq 2}">
					$("#exp_op_2 option[value='${item.formula}']").attr("selected","selected");
					$("#exp_val_2").val('${item.benchmark}');
				</c:if>
				<c:if test="${item.score eq 3}">
					$("#exp_op_3 option[value='${item.formula}']").attr("selected","selected");
					$("#exp_val_3").val('${item.benchmark}');
				</c:if>
				<c:if test="${item.score eq 4}">
					$("#exp_op_4 option[value='${item.formula}']").attr("selected","selected");
					$("#exp_val_4").val('${item.benchmark}');
				</c:if>
			</c:forEach>
			$("#tabs").tabs({active: 1});
		</c:when>
		<c:when test="${model.associate.associateType == 'KNOWLEDGE'}">
			<c:forEach items="${model.associate.items}" var="item" varStatus="status">
				$("#kn_cate option[value='${item.itemId}']").attr("selected","selected");
				
				$("#kn_start_date").val('${model.associate.startDate}');
				$("#kn_end_date").val('${model.associate.endDate}');
				
				<c:if test="${item.score eq 1}">
					$("#kn_op_1 option[value='${item.formula}']").attr("selected","selected");
					$("#kn_val_1").val('${item.benchmark}');
				</c:if>
				<c:if test="${item.score eq 2}">
					$("#kn_op_2 option[value='${item.formula}']").attr("selected","selected");
					$("#kn_val_2").val('${item.benchmark}');
				</c:if>
				<c:if test="${item.score eq 3}">
					$("#kn_op_3 option[value='${item.formula}']").attr("selected","selected");
					$("#kn_val_3").val('${item.benchmark}');
				</c:if>
				<c:if test="${item.score eq 4}">
					$("#kn_op_4 option[value='${item.formula}']").attr("selected","selected");
					$("#kn_val_4").val('${item.benchmark}');
				</c:if>
			</c:forEach>
			$("#tabs").tabs({active: 2});
		</c:when>
		<c:when test="${model.associate.associateType == 'TRAINING'}">
			<c:forEach items="${model.associate.items}" var="item" varStatus="status">
				<c:if test="${item.score eq 1}">
					$("#tr_op_1 option[value='${item.formula}']").attr("selected","selected");
					$("#tr_val_1").val('${item.benchmark}');
				</c:if>
				<c:if test="${item.score eq 2}">
					$("#tr_op_2 option[value='${item.formula}']").attr("selected","selected");
					$("#tr_val_2").val('${item.benchmark}');
				</c:if>
				<c:if test="${item.score eq 3}">
					$("#tr_op_3 option[value='${item.formula}']").attr("selected","selected");
					$("#tr_val_3").val('${item.benchmark}');
				</c:if>
				<c:if test="${item.score eq 4}">
					$("#tr_op_4 option[value='${item.formula}']").attr("selected","selected");
					$("#tr_val_4").val('${item.benchmark}');
				</c:if>
			</c:forEach>
			$("#tabs").tabs({active: 3});
			$("#tr_start_date").val('${model.associate.startDate}');
			$("#tr_end_date").val('${model.associate.endDate}');
		</c:when>
		<c:when test="${model.associate.associateType == 'QUALIFICATION'}">
			<c:forEach items="${model.associate.items}" var="item" varStatus="status">
				<c:set value="${ fn:split(item.val1, ',') }" var="val1s" />
				<c:forEach items="${val1s}" var="val1">
					$("#pos_op_${item.score} option[value='${val1}']").attr("selected","selected");
				</c:forEach>
				
				<c:set value="${ fn:split(item.val2, ',') }" var="val2s" />
				<c:forEach items="${val2s}" var="val2">
					$("#tech_op_${item.score} option[value='${val2}']").attr("selected","selected");
				</c:forEach>
			</c:forEach>
			$("#tabs").tabs({active: 4});
		</c:when>
		<c:otherwise>
			$("#tabs").tabs();
			$("#associateType").val('CERTIFICATION');
		</c:otherwise>
	</c:choose>
	
	$('#exp_cate').select2();
	$('#exp_op_1').select2();
	$('#exp_op_2').select2();
	$('#exp_op_3').select2();
	$('#exp_op_4').select2();
	
	$('#kn_cate').select2();
	$('#kn_op_1').select2();
	$('#kn_op_2').select2();
	$('#kn_op_3').select2();
	$('#kn_op_4').select2();
	
	$('#tr_op_1').select2();
	$('#tr_op_2').select2();
	$('#tr_op_3').select2();
	$('#tr_op_4').select2();
	
	$('#pos_op_1').select2();
	$('#tech_op_1').select2();
	
	
	$('#pos_op_2').select2();
	$('#tech_op_2').select2();
	
	$('#pos_op_3').select2();
	$('#tech_op_3').select2();
	
	$('#pos_op_4').select2();
	$('#tech_op_4').select2();
	
	$('#kn_start_date').datepicker({dateFormat: "yy-mm-dd"});
	$('#kn_end_date').datepicker({dateFormat: "yy-mm-dd"});
	$('#tr_start_date').datepicker({dateFormat: "yy-mm-dd"});
	$('#tr_end_date').datepicker({dateFormat: "yy-mm-dd"});
	
});
</script>
</body>
</html>