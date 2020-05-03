<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/common.jsp"%>
<%@ page isELIgnored="false" %>
<div class="row">
	<div id="breadcrumb" class="col-md-12">
		<ol class="breadcrumb">
			<li><a href="#">考核管理</a></li>
			<li><a href="#">考核安排</a></li>
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
							<th colspan="5">
								<a class='iframe btn btn-primary btn-label-left' href="${ctx}/system/capacity/evaluatearrange/show/.htm">
									<span><i class="fa fa-clock-o"></i></span>
									添加
								</a>
							</th>
						</tr>
						<tr>
							<th>考核名称</th>
							<th>考核时间</th>
							<th>考核地点</th>
							<th>状态</th>
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
	
<div style="display:none">
    	<span id="header_">删除</span>
    	<span id="inner_">确定删除吗?</span>
    	<div class="form-group" id="bottom_">
			<div class="col-sm-offset-4 col-sm-2">
				<button type="submit" class="btn btn-primary btn-label-left" onclick="deleteAction();">
					<span><i class="fa fa-clock-o"></i></span>
						确定
				</button>
			</div>
			<div class="col-sm-2">
				<button type="button" class="btn btn-default btn-label-left" onclick="CloseModalBox();">
					<span><i class="fa fa-clock-o txt-danger"></i></span>
					取消
				</button>
			</div>
		</div>
</div>

<script type="text/javascript">
// Run Datables plugin and create 3 variants of settings
function UserTable(){
	var asInitVals = [];
	oTable = $('#datatable-m').dataTable( {
		//"aaSorting": [[ 0, "asc" ]],
		"sAjaxSource": "${ctx}/system/capacity/evaluatearrange/listdata.htm",
		"aoColumns": [
		              { "mData": "project.name" },
		              { "mData": "startTime" },
		              { "mData": "address" },
		              { "mData": "status" },
		              { "mData": "id" }
		 ],
		 "aoColumnDefs": [{
			 "mRender": function(data, type, row) {
		        	if(data == 'PENDING'){
		        		return '待考' ;
		        	}
		        	if(data == 'COMPLETED'){
		        		return '完成';
		        	}
		        	if(data == 'CANCEL'){
		        		return '取消';
		        	}
		        },
		        "aTargets": [3]
		 },
		 {
		        "mRender": function(data, type, row) {
		        	//alert(row.aData.length);
		        	 return '<a href="#" onclick="edit(1,' + data + ');">编辑</a>&nbsp;&nbsp;' + '<a href="#" onclick="edit(0,' + data + ');">删除</a>&nbsp;&nbsp;' + '<a href="#" onclick="edit(2,' + data + ');">签到码</a>&nbsp;&nbsp;' + '<a href="#" onclick="edit(3,' + data + ');">记录</a>&nbsp;&nbsp;';
		        },
		        "aTargets": [4]
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

var fid;
function edit(type, id){
	var url = "";
	if(type == 0){
		var header = $('#header_').html();
		var inner = $('#inner_').html();
		var bottom = $("#bottom_").html();
		fid = id;
		OpenWindow(header, inner, bottom);
	}
	if(type == 1){
		url = "${ctx}/system/capacity/evaluatearrange/show/" + id + ".htm";
		$.colorbox({href:url, iframe:true, width:"85%", height:"100%"});
	}
	if(type == 2){
		url = "${ctx}/system/capacity/evaluatearrange/vcode/" + id + ".htm";
		window.open(url,'newwindow','top=0,left=0,toolbar=no,menubar=no,scrollbars=yes,resizable=no,location=no,status=no');
		//$.colorbox({href:url, iframe:true, width:"80%", height:"100%"});
	}
	if(type == 3){
		url = "${ctx}/system/capacity/evaluatearrange/record/" + id + ".htm";
		window.open(url,'newwindow','top=0,left=0,toolbar=no,menubar=no,scrollbars=yes,resizable=no,location=no,status=no');
	}
	
	//var content = LoadContent('${ctx}/admin/user/show/1.htm');
	//OpenWindow(content);
	
}

function deleteAction(){
	if(fid){
		url = "${ctx}/system/capacity/evaluatearrange/delete/" + fid + ".htm";
		var aj = $.ajax( {  
			url: url,// 跳转到 action   
			type:'post',  
			cache:false,  
			dataType:'json',  
			success:function(data) {
				if(data.status == 'success'){
					LoadAjaxContent('${ctx}/system/capacity/evaluatearrange/list.htm');
					CloseModalBox();
					OpenWindow('操作结果', '您的操作已成功，记录已删除！');
				}
				else if(data.status == 'exist'){
					OpenWindow('操作结果', '该考试已经有开始记录，无法删除！');
				} 
				else{  
					OpenWindow('操作结果', '您的操作出现错误，请重新再试！');
				}  
			},  
			error : function() {  
				OpenWindow('操作结果', '您的操作出现错误，请重新再试！');
			}  
		});
	}
}

$(document).ready(function() {
	// Load Datatables and run plugin on tables 
	LoadDataTablesScripts_(AllTables);
	// Add Drag-n-Drop feature
	WinMove();
		
	$(".iframe").colorbox({iframe:true, width:"85%", height:"100%"});
	
});
</script>
