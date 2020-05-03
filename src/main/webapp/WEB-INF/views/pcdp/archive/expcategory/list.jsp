<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/common.jsp"%>
<%@ page isELIgnored="false" %>
<div class="row">
	<div id="breadcrumb" class="col-md-12">
		<ol class="breadcrumb">
			<li><a href="#">档案管理</a></li>
			<li><a href="#">经历定义</a></li>
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
						<tr role="row">
							<th rowspan="1" colspan="2">
								<a class="iframe btn btn-primary btn-label-left cboxElement" href="${ctx}/system/archive/expcategory/show/.htm">
									<span><i class="fa fa-clock-o"></i></span>
									添加一级经历
								</a>
							</th>
						</tr>
						<tr role="row">
							<th aria-label="指标名称: activate to sort column" aria-sort="" colspan="1" rowspan="1" aria-controls="datatable-m" tabindex="0" role="columnheader" class="">指标名称</th>
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
		url = "${ctx}/system/archive/expcategory/add/" + id + ".htm";
		$.colorbox({href:url, iframe:true, width:"65%", height:"100%"});
	}
	if(type == 2){
		url = "${ctx}/system/archive/expcategory/show/" + id + ".htm";
		$.colorbox({href:url, iframe:true, width:"65%", height:"100%"});
	}
	
	//var content = LoadContent('${ctx}/admin/user/show/1.htm');
	//OpenWindow(content);
	
}

function del(id){
	url = "${ctx}/system/archive/expcategory/del/" + id + ".htm";
	$.colorbox({href:url, iframe:true, width:"65%", height:"60%"});
}

$(document).ready(function() {
	// Load Datatables and run plugin on tables 
	//LoadDataTablesScripts_(AllTables);
	// Add Drag-n-Drop feature
	//WinMove();
		
	$(".iframe").colorbox({iframe:true, width:"60%", height:"100%"});
	
});
</script>
