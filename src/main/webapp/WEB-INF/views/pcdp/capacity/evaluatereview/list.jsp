<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/common.jsp"%>
<%@ page isELIgnored="false" %>
<div class="row">
	<div id="breadcrumb" class="col-md-12">
		<ol class="breadcrumb">
			<li><a href="#">考核管理</a></li>
			<li><a href="#">考核评定</a></li>
		</ol>
	</div>
</div>

<div class="row">
		<div class="col-xs-12 col-sm-12">
			<div class="box-content no-padding table-responsive">
					<table class="table table-bordered table-striped table-hover table-heading table-datatable" id="datatable-m">
						<thead>
							<tr>
								<th width="50%">
									<select class="populate placeholder" name="arrId" id="arranges" onchange="changeArrange();">
										<option value="">请选择</option>
										<c:forEach items="${arranges}" var="item">
											<option value="${item.id }" <c:if test="${item.id == arrId }">selected="selected"</c:if>>${item.project.name }</option>
										</c:forEach>
										
									</select>
								</th>
								<th>

									
								</th>
							</tr>
						</thead>
					</table>
				</div>
				
			<div class="box-content no-padding table-responsive">
				<table aria-describedby="datatable-m_info" class="table table-bordered table-striped table-hover table-heading table-datatable dataTable" id="datatable-m">
					<thead>
						
						<tr role="row">
							<th>考生名称</th>
							<th>考核状态</th>
							<th>评估状态</th>
							<th>操作</th>
						</tr>
					</thead>
					
					<tbody aria-relevant="all" aria-live="polite" role="alert">
						<c:forEach var="cur" items="${list}" varStatus="vs">   
							<tr class="odd">
								<td class=" sorting_1"><span class="span_">${cur.user.name }</span></td>
								<td class=""">
									<c:if test="${cur.status == 'DRAFT' }">未签到</c:if>
									<c:if test="${cur.status == 'SIGNED' }">已签到</c:if>
									<c:if test="${cur.status == 'COMPLETED' }">已完成</c:if>
									<c:if test="${cur.status == 'CANCEL' }">已取消</c:if>
									<c:if test="${cur.status == 'ABSENT' }">缺席</c:if>
								</td>
								<td class=""">
									<c:if test="${cur.checkStatus == 'DRAFT' }">未评估</c:if>
									<c:if test="${cur.checkStatus == 'COMPLETED' }">已完成</c:if>
								</td>
								<td class=" sorting_1">
									<c:if test="${cur.checkStatus == 'COMPLETED'}">
										<a href="#" onclick="edit(2,'${cur.id}');">查看</a>&nbsp;&nbsp;
									</c:if>
									<c:if test="${cur.status == 'COMPLETED' && cur.checkStatus == 'DRAFT'}">
										<a href="#" onclick="edit(1,'${cur.id}');">评估</a>&nbsp;&nbsp;
									</c:if>
								</td>
								
							</tr> 
						</c:forEach> 
					</tbody>
				</table>
			</div>
	</div>
	
</div>
	

<script type="text/javascript">
// Run Datables plugin and create 3 variants of settings
function changeArrange(){
	var arrId = $("#arranges").val();
	if(arrId){
		LoadAjaxContent('${ctx}/system/capacity/evaluatereview/list.htm?arrId='+arrId);
	}
}

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
		url = "${ctx}/system/capacity/evaluatereview/show/" + id + ".htm";
		$.colorbox({href:url, iframe:true, width:"98%", height:"100%"});
	}
	if(type == 2){
		url = "${ctx}/system/capacity/evaluatereview/view/" + id + ".htm";
		$.colorbox({href:url, iframe:true, width:"98%", height:"100%"});
	}
	if(type == 3){
		url = "${ctx}/admin/role/perms/" + id + ".htm";
		$.colorbox({href:url, iframe:true, width:"65%", height:"70%"});
	}
	
	//var content = LoadContent('${ctx}/admin/user/show/1.htm');
	//OpenWindow(content);
	
}

$(document).ready(function() {
	// Load Datatables and run plugin on tables 
	//LoadDataTablesScripts_(AllTables);
	// Add Drag-n-Drop feature
	$('#arranges').select2();
	
	WinMove();
		
	$(".iframe").colorbox({iframe:true, width:"65%", height:"60%"});
	
});
</script>
