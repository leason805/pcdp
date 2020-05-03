<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/common.jsp"%>
<%@ page isELIgnored="false" %>
<div class="row">
	<div id="breadcrumb" class="col-md-12">
		<ol class="breadcrumb">
			<li><a href="#">绩效管理</a></li>
			<li><a href="#">考评结果</a></li>
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
				<table class="table table-bordered table-striped table-hover table-heading table-datatable" id="datatable-m">
					<thead>
						<tr>
							<th>
								<select class="populate placeholder" name="projedtId" id="projects" onchange="changeProject();">
									<c:forEach items="${projects}" var="prj">
										<option value="${prj.id }" <c:if test="${prj.id == projectId }">selected="selected"</c:if>>${prj.name }</option>
									</c:forEach>
									
								</select>
							</th>
							<th colspan="4">

							</th>
						</tr>
						<tr>
							<th>签派员姓名</th>
							<th>同级考评员</th>
							<th>上级考评员</th>
							<th>考评状态</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="cur" items="${list}" varStatus="vs">   
							<tr class="odd">
								<td class=" sorting_1"><span class="span_">${cur.userName }</span></td>
								<td class="">${cur.colAssessor }</td>
								<td class="">${cur.supAssessor }</td>
								<td class="">
									<c:if test="${cur.status == 'DRAFT' }">未完成</c:if>
									<c:if test="${cur.status == 'COMPLETED' }">已完成</c:if>
								</td>
								<td class=" sorting_1">
									<c:if test="${cur.status == 'COMPLETED'}">
										<a href="#" onclick="edit(1,'${cur.id}');">查看</a>&nbsp;&nbsp;
									</c:if>									
								</td>
								
							</tr> 
						</c:forEach> 
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>
	

<script type="text/javascript">
// Run Datables plugin and create 3 variants of settings


function AllTables(){
	UserTable();
	LoadSelect2Script_(MakeSelect2);
}

function LoadSelect2Script_(callback){
	if (!$.fn.select2){
		$.getScript('${ctx}/plugins/select2/select2.min.js', callback);
	}
	else {
		if (callback && typeof(callback) === "function") {
			callback();
		}
	}
}

function LoadDataTablesScripts_(callback){
	function LoadDatatables(){
		$.getScript('${ctx}/plugins/datatables/jquery.dataTables.js', function(){
			$.getScript('${ctx}/plugins/datatables/ZeroClipboard.js', function(){
				$.getScript('${ctx}/plugins/datatables/TableTools.js', function(){
					$.getScript('${ctx}/plugins/datatables/dataTables.bootstrap.js', callback);
				});
			});
		});
	}
	if (!$.fn.dataTables){
		LoadDatatables();
	}
	else {
		if (callback && typeof(callback) === "function") {
			callback();
		}
	}
}

function MakeSelect2(){
	$('select').select2();
	$('.dataTables_filter').each(function(){
		$(this).find('label input[type=text]').attr('placeholder', '查询');
	});
}

function create(){
	var content = LoadContent('${ctx}/admin/role/show/.htm');
	OpenWindow(content);
}

function edit(type, id){
	var url = "";
	if(type == 1){
		url = "${ctx}/system/performance/result/show/" + id + ".htm";
		openwin = window.open(url,'newwindow','top=0,left=0,toolbar=no,menubar=no,scrollbars=yes,resizable=no,location=no,status=no');
	}
	if(type == 2){
		url = "${ctx}/admin/role/users/" + id + ".htm";
		$.colorbox({href:url, iframe:true, width:"60%", height:"92%"});
	}
	if(type == 3){
		url = "${ctx}/admin/role/perms/" + id + ".htm";
		$.colorbox({href:url, iframe:true, width:"65%", height:"70%"});
	}
	
	//var content = LoadContent('${ctx}/admin/user/show/1.htm');
	//OpenWindow(content);
	
}

function changeProject(){
	var compId = $("#projects").val();
	if(compId){
		LoadAjaxContent('${ctx}/system/estimate/assess/list.htm?projectId='+compId);
	}
}

$(document).ready(function() {
	// Load Datatables and run plugin on tables 

	// Add Drag-n-Drop feature
	WinMove();
	
	$('#projects').select2();
	
	$(".iframe").colorbox({iframe:true, width:"65%", height:"60%"});
	
});
</script>
