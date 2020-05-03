<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/common.jsp"%>
<%@ page isELIgnored="false" %>
<div class="row">
	<div id="breadcrumb" class="col-md-12">
		<ol class="breadcrumb">
			<li><a href="#">绩效管理</a></li>
			<li><a href="#">考评安排</a></li>
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
				<div id="datatable-m_wrapper" class="dataTables_wrapper form-inline" role="grid"><div class="box-content"><div class="col-sm-6"><div id="datatable-m_filter" class="dataTables_filter"><label><input placeholder="查询" aria-controls="datatable-m" type="text"></label></div></div><div class="col-sm-6 text-right"><div class="dataTables_length" id="datatable-m_length"><label><div id="s2id_autogen10" class="select2-container"><a href="javascript:void(0)" onclick="return false;" class="select2-choice" tabindex="-1">   <span class="select2-chosen">10</span><abbr class="select2-search-choice-close"></abbr>   <span class="select2-arrow"><b></b></span></a><input id="s2id_autogen11" class="select2-focusser select2-offscreen" type="text"><div class="select2-drop select2-display-none select2-with-searchbox">   <div class="select2-search">       <input autocomplete="off" autocorrect="off" autocapitalize="off" spellcheck="false" class="select2-input" type="text">   </div>   <ul class="select2-results">   </ul></div></div><select class="select2-offscreen" tabindex="-1" aria-controls="datatable-m" size="1" name="datatable-m_length"><option selected="selected" value="10">10</option><option value="25">25</option><option value="50">50</option><option value="100">100</option></select></label></div></div><div class="clearfix"></div></div><table aria-describedby="datatable-m_info" class="table table-bordered table-striped table-hover table-heading table-datatable dataTable" id="datatable-m">
					<thead>
						<tr role="row"><th colspan="1" rowspan="1">
								<div id="s2id_projects" class="select2-container populate placeholder"><a href="javascript:void(0)" onclick="return false;" class="select2-choice" tabindex="-1">   <span class="select2-chosen">2016签派员资质评估</span><abbr class="select2-search-choice-close"></abbr>   <span class="select2-arrow"><b></b></span></a><input id="s2id_autogen12" class="select2-focusser select2-offscreen" type="text"></div><select tabindex="-1" class="populate placeholder select2-offscreen" name="projedtId" id="projects" onchange="changeProject();">
									
										<option value="1" selected="selected">2016签派员资质评估</option>
									
									
								</select>
							</th><th rowspan="1" colspan="4">

							</th></tr>
						<tr role="row">
							<th aria-label="签派员姓名: activate to sort column descending" aria-sort="ascending" colspan="1" rowspan="1" aria-controls="datatable-m" tabindex="0" role="columnheader" class="sorting_asc">签派员姓名</th>
							<th aria-label="分配状态: activate to sort column ascending" colspan="1" rowspan="1" aria-controls="datatable-m" tabindex="0" role="columnheader" class="sorting">分配状态</th>
							<th aria-label="检查员: activate to sort column ascending" colspan="1" rowspan="1" aria-controls="datatable-m" tabindex="0" role="columnheader" class="sorting">同级考评员</th>
							<th aria-label="检查员: activate to sort column ascending" colspan="1" rowspan="1" aria-controls="datatable-m" tabindex="0" role="columnheader" class="sorting">上级考评员</th>
							<th aria-label="操作: activate to sort column ascending" colspan="1" rowspan="1" aria-controls="datatable-m" tabindex="0" role="columnheader" class="sorting">操作</th>
						</tr>
					</thead>
					
				<tbody aria-relevant="all" aria-live="polite" role="alert">
					<tr class="odd">
						<td class=" sorting_1">何云博</td><td class="">已分配</td><td class="">冯振山</td><td class="">庄圣</td><td class=""><a href="javascript:edit(1,4);">分配</a>&nbsp;&nbsp;</td>
					</tr>
					<tr class="even">
						<td class=" sorting_1">何姚</td><td class="">已分配</td><td class="">冯振山</td><td class="">庄圣</td><td class=""><a href="javascript:edit(1,1);">分配</a>&nbsp;&nbsp;</td>
					</tr>
					<tr class="odd">
						<td class=" sorting_1">冯振山</td><td class="">已分配</td><td class="">冯振山</td><td class="">庄圣</td><td class=""><a href="javascript:edit(1,2);">分配</a>&nbsp;&nbsp;</td>
					</tr>
					<tr class="even">
						<td class=" sorting_1">吴彬</td><td class="">已分配</td><td class="">冯振山</td><td class="">庄圣</td><td class=""><a href="javascript:edit(1,3);">分配</a>&nbsp;&nbsp;</td>
					</tr>
					<tr class="odd">
						<td class=" sorting_1">杨永彬</td><td class="">已分配</td><td class="">冯振山</td><td class="">庄圣</td><td class=""><a href="javascript:edit(1,6);">分配</a>&nbsp;&nbsp;</td>
					</tr>
					<tr class="even">
						<td class=" sorting_1">郭晓刚</td><td class="">已分配</td><td class="">冯振山</td><td class="">庄圣</td><td class=""><a href="javascript:edit(1,5);">分配</a>&nbsp;&nbsp;</td>
					</tr>
					<tr class="odd">
						<td class=" sorting_1">黄达</td><td class="">已分配</td><td class="">冯振山</td><td class="">庄圣</td><td class=""><a href="javascript:edit(1,7);">分配</a>&nbsp;&nbsp;</td>
					</tr>
				</tbody>
			</table>
			<div class="box-content"><div class="col-sm-6"><div id="datatable-m_info" class="dataTables_info">Showing 1 to 7 of 7 entries</div></div><div class="col-sm-6 text-right"><div class="dataTables_paginate paging_bootstrap"><ul class="pagination"><li class="prev disabled"><a href="#">← Previous</a></li><li class="active"><a href="#">1</a></li><li class="next disabled"><a href="#">Next → </a></li></ul></div></div><div class="clearfix"></div></div></div>
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
		"sAjaxSource": "${ctx}/admin/role/listdata.htm",
		"aoColumns": [
		              { "mData": "name" },
		              { "mData": "description" },
		              { "mData": "id" }
		 ],
		 "aoColumnDefs": [{
		        "mRender": function(data, type, row) {
		        	if(data == null){
		        		return '';
		        	}
		        	else{
		        		if(data.length > 50){
		        			return data.substring(0, 50) + "......";
		        		}
		        		else{
		        			return data;
		        		}
		        	}
		        },
		        "aTargets": [1]
		 },
		 {
		        "mRender": function(data, type, row) {
		        	//alert(row.aData.length);
		          return '<a href="#" onclick="edit(1,' + data + ');">编辑</a>&nbsp;&nbsp;' + '<a href="#" onclick="edit(2,' + data + ');">关联用户</a>&nbsp;&nbsp;' + '<a href="#" onclick="edit(3,' + data + ');">关联权限</a>';
		        },
		        "aTargets": [2]
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
		url = "${ctx}/admin/role/show/" + id + ".htm";
		$.colorbox({href:url, iframe:true, width:"65%", height:"60%"});
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

$(document).ready(function() {
	// Load Datatables and run plugin on tables 
	//LoadDataTablesScripts_(AllTables);
	// Add Drag-n-Drop feature
	WinMove();
		
	$(".iframe").colorbox({iframe:true, width:"65%", height:"60%"});
	
});
</script>
