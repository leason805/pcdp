<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/common.jsp"%>
<%@ page isELIgnored="false" %>
<div class="row">
	<div id="breadcrumb" class="col-md-12">
		<ol class="breadcrumb">
			<li><a href="#">考试管理</a></li>
			<li><a href="#">章节管理</a></li>
		</ol>
	</div>
</div>

<div class="row">
	<div class="col-xs-12">
		<div class="box">
			<div class="box-header">
				<div class="box-name">
					<i class="fa fa-linux"></i>
					
				</div>
				<div class="box-icons">
					<a class="collapse-link">
						<i class="fa fa-chevron-up"></i>
					</a>
					<a class="expand-link">
						<i class="fa fa-expand"></i>
					</a>
					<a class="close-link">
						<i class="fa fa-times"></i>
					</a>
				</div>
				<div class="no-move"></div>
			</div>
			<div class="box-content no-padding table-responsive">
				<table aria-describedby="datatable-m_info" class="table table-bordered table-striped table-hover table-heading table-datatable dataTable" id="datatable-m">
					
					<thead>
						<tr>
							<th width="30%">
									<select class="populate placeholder" name="companyId" id="companys" onchange="changeCompany();">
										<option value="">请选择公司</option>
										<c:forEach items="${companys}" var="item">
											<option value="${item.id }" <c:if test="${item.id == companyId }">selected="selected"</c:if>>${item.name }</option>
										</c:forEach>
										
									</select>
							</th>
							<th>
							</th>
							<th>
							</th>
						</tr>	
						<tr role="row">
							<th rowspan="1" colspan="3">
								<a class="iframe btn btn-primary btn-label-left cboxElement" href="${ctx}/system/knowledge/section/show/.htm">
									<span><i class="fa fa-clock-o"></i></span>
									添加根章节
								</a>
							</th>
						</tr>
						<tr role="row">
							<th aria-label="指标名称: activate to sort column" aria-sort="" colspan="2" rowspan="1" aria-controls="datatable-m" tabindex="0" role="columnheader" class="">章节名称</th>
							<th aria-label="操作: activate to sort column" colspan="1" rowspan="1" aria-controls="datatable-m" tabindex="0" role="columnheader" class="">操作</th>
						</tr>
					</thead>
					
					<tbody aria-relevant="all" aria-live="polite" role="alert">
						<c:import url="tag.jsp" />
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>
	

<script type="text/javascript">
// Run Datables plugin and create 3 variants of settings


function edit(type, id){
	var url = "";
	if(type == 1){
		url = "${ctx}/system/knowledge/section/add/" + id + ".htm";
		$.colorbox({href:url, iframe:true, width:"65%", height:"100%"});
	}
	if(type == 2){
		url = "${ctx}/system/knowledge/section/show/" + id + ".htm";
		$.colorbox({href:url, iframe:true, width:"65%", height:"100%"});
	}
}

function del(id){
	url = "${ctx}/system/estimate/indicator/del/" + id + ".htm";
	$.colorbox({href:url, iframe:true, width:"65%", height:"60%"});
}

function changeCompany(){
	var compId = $("#companys").val();
	if(compId){
		LoadAjaxContent('${ctx}/system/knowledge/section/list.htm?companyId='+compId);
	}
}

$(document).ready(function() {
	// Load Datatables and run plugin on tables 
	//LoadDataTablesScripts_(AllTables);
	// Add Drag-n-Drop feature
	//WinMove();
	$('#companys').select2();
	
	$(".iframe").colorbox({iframe:true, width:"60%", height:"100%"});
	
});
</script>
