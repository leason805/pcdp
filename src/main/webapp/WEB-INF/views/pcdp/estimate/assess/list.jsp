<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/common.jsp"%>
<%@ page isELIgnored="false" %>
<div class="row">
	<div id="breadcrumb" class="col-md-12">
		<ol class="breadcrumb">
			<li><a href="#">资质评估</a></li>
			<li><a href="#">资质能力评估</a></li>
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
							<th>分配状态</th>
							<th>检查员</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>

					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>
	

<script type="text/javascript">
// Run Datables plugin and create 3 variants of settings
function UserTable(){
	var asInitVals = [];
	oTable = $('#datatable-m').dataTable( {
		//"aaSorting": [[ 0, "asc" ]],
		"sAjaxSource": "${ctx}/system/estimate/assess/listdata.htm?projectId=${projectId}",
		"aoColumns": [
		              { "mData": "name" },
		              { "mData": "status" },
		              { "mData": "users" },
		              { "mData": "id" }
		 ],
		 "aoColumnDefs": [{
		        "mRender": function(data, type, row) {
		        	if(data == null){
		        		return '';
		        	}
		        	else{
		        		if(data == 'UNASSIGN'){
		        			return "<span style='color:red'>未分配</span>";
		        		}
		        		if(data == 'ASSIGNED'){
		        			return "已分配";
		        		}
		        		if(data == 'COMPLETED'){
		        			return "已完成";
		        		}
		        	}
		        },
		        "aTargets": [1]
		 },
		 {
		        "mRender": function(data, type, row) {
		          var str = "";
		        	if(row.status == 'UNASSIGN' || row.status == 'ASSIGNED' || row.status == 'COMPLETED'){
		        		str = '<a href="javascript:edit(1,' + data + ');">分配</a>&nbsp;&nbsp;';
		        	}
		          return str;
		        },
		        "aTargets": [3]
		 }
		 ],
		"sDom": "<'box-content'<'col-sm-6'f><'col-sm-6 text-right'l><'clearfix'>>rt<'box-content'<'col-sm-6'i><'col-sm-6 text-right'p><'clearfix'>>",
		"sPaginationType": "bootstrap",
		"oLanguage": {
			"sSearch": "",
			"sLengthMenu": '_MENU_'
		},
		bAutoWidth: false
		//"iDisplayLength": 20
	});
	var header_inputs = $("#datatable-m thead input");
	header_inputs.on('keyup', function(){
		/* Filter on the column (the index) of this element */
		oTable.fnFilter( this.value, header_inputs.index(this) );
	})
	.on('focus', function(){
		if ( this.className == "search_init" ){
			this.className = "";
			this.value = "";
		}
	})
	.on('blur', function (i) {
		if ( this.value == "" ){
			this.className = "search_init";
			this.value = asInitVals[header_inputs.index(this)];
		}
	});
	header_inputs.each( function (i) {
		asInitVals[i] = this.value;
	});
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
		url = "${ctx}/system/estimate/assess/show/" + id + ".htm";
		$.colorbox({href:url, iframe:true, width:"60%", height:"100%"});
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
	LoadDataTablesScripts_(AllTables);
	// Add Drag-n-Drop feature
	WinMove();
	
	$('#projects').select2();
	
	$(".iframe").colorbox({iframe:true, width:"65%", height:"60%"});
	
});
</script>
