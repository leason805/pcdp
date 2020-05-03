<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/common.jsp"%>
<%@ page isELIgnored="false" %>
<div class="row">
	<div id="breadcrumb" class="col-md-12">
		<ol class="breadcrumb">
			<li><a href="#">Dashboard</a></li>
			<li><a href="#">Tables</a></li>
			<li><a href="#">Data Tables</a></li>
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
									<span><i class="fa fa-clock-o"></i></span>
									指标重要性评分为1-6分，分数越高，表面越重要。			
							</th>
						</tr>
					</thead>
					
					<tbody aria-relevant="all" aria-live="polite" role="alert">
						
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>
	
<div class="row">
	<div class="col-xs-12">
		<div class="box">
			<div class="box-content no-padding table-responsive">
				<table aria-describedby="datatable-m_info" class="table table-bordered table-striped table-hover table-heading table-datatable dataTable" id="datatable-m">
					<thead>
						<tr role="row">
							<th aria-sort="" colspan="2" rowspan="1" aria-controls="datatable-m" tabindex="0" role="columnheader" class="">一级指标</th>
						</tr>
					</thead>
					
					<tbody aria-relevant="all" aria-live="polite" role="alert">
						<tr class="odd">
								<td class=" sorting_1" width="50%">指标</td>
								<td class="">分数</td>
							</tr> 
							
						<c:forEach var="cur" items="${list}" varStatus="vs">   
							<tr class="odd">
								<td class=" sorting_1"><span class="span_">${cur.sequence} &nbsp; ${cur.name }</span></td>
								<td class="">
									<div class="col-sm-4">
										<input type="text" class="form-control" placeholder="分数" data-toggle="tooltip" data-placement="bottom" title="分数" name="field_${cur.id}" value="${cur.scores[0].score}">
									</div>
								</td>
							</tr> 
						</c:forEach> 
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>

<c:forEach var="cur" items="${list}" varStatus="vs">
	<div class="row">
		<div class="col-xs-12">
			<div class="box">
				<div class="box-content no-padding table-responsive">
					<table aria-describedby="datatable-m_info" class="table table-bordered table-striped table-hover table-heading table-datatable dataTable" id="datatable-m">
						<thead>
							<tr role="row">
								<th aria-sort="" colspan="2" rowspan="1" aria-controls="datatable-m" tabindex="0" role="columnheader" class="">【${cur.name }】 的下级指标</th>
							</tr>
						</thead>
						
						<tbody aria-relevant="all" aria-live="polite" role="alert">
							<tr class="odd">
									<td class=" sorting_1" width="50%">指标</td>
									<td class="">分数</td>
								</tr> 
								
								<c:set var="children" value="${cur.children}" scope="request" />
								<c:import url="tag.jsp" />
								
							
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</c:forEach>


<script type="text/javascript">
// Run Datables plugin and create 3 variants of settings


function edit(type, id){
	var url = "";
	if(type == 1){
		url = "${ctx}/admin/index/add/" + id + ".htm";
		$.colorbox({href:url, iframe:true, width:"65%", height:"60%"});
	}
	if(type == 2){
		url = "${ctx}/admin/index/show/" + id + ".htm";
		$.colorbox({href:url, iframe:true, width:"65%", height:"60%"});
	}
	
	//var content = LoadContent('${ctx}/admin/user/show/1.htm');
	//OpenWindow(content);
	
}

function del(id){
	url = "${ctx}/admin/index/del/" + id + ".htm";
	$.colorbox({href:url, iframe:true, width:"65%", height:"60%"});
}

$(document).ready(function() {
	// Load Datatables and run plugin on tables 
	//LoadDataTablesScripts_(AllTables);
	// Add Drag-n-Drop feature
	//WinMove();
		
	$(".iframe").colorbox({iframe:true, width:"60%", height:"60%"});
	
});
</script>
