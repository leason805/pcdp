<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/common.jsp"%>
<%@ page isELIgnored="false" %>
<div class="row">
	<div id="breadcrumb" class="col-md-12">
		<ol class="breadcrumb">
			<li><a href="#">考试管理</a></li>
			<li><a href="#">试卷管理</a></li>
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
							<th colspan="6">
								<a class='iframe btn btn-primary btn-label-left' href="${ctx}/system/knowledge/paper/show/.htm">
									<span><i class="fa fa-clock-o"></i></span>
									添加
								</a>
							</th>
						</tr>
						<tr>
							<th>所属公司</th>
							<th>试卷名称</th>
							<th>考试时间</th>
							<th>试题数量</th>
							<th>总分</th>
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

<div id="print_ctn" style="display:none;">
	<div class="form-group ">
		<label class="col-sm-2 control-label">打印数量</label>
		<div class="col-sm-6">
			<input type="text" class="form-control" placeholder="数量" data-toggle="tooltip" data-placement="bottom" title="数量" name="print_size" id="print_size" value="">
		</div>
	</div>
</div>

<div id="print_btn" style="display:none;">
	<div class="form-group">
		<div class="col-sm-offset-4 col-sm-2">
			<button type="button" class="btn btn-primary btn-label-left" onclick="print();">
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
var printid;

function UserTable(){
	var asInitVals = [];
	oTable = $('#datatable-m').dataTable( {
		//"aaSorting": [[ 0, "asc" ]],
		"sAjaxSource": "${ctx}/system/knowledge/paper/listdata.htm",
		"aoColumns": [
					  { "mData": "company.name" },
		              { "mData": "name" },
		              { "mData": "minutes" },
		              { "mData": "size" },
		              { "mData": "score" },
		              { "mData": "id" }
		 ],
		 "aoColumnDefs": [{
		        "mRender": function(data, type, row) {
		        	return data + "分钟";
		        },
		        "aTargets": [2]
		 },
		 {
		        "mRender": function(data, type, row) {
		        	//alert(row.aData.length);
		          return '<a href="#" onclick="edit(1,' + data + ');">编辑</a>&nbsp;&nbsp;<a href="#" onclick="edit(2,' + data + ');">批量打印</a>';
		        },
		        "aTargets": [5]
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
		url = "${ctx}/system/knowledge/paper/show/" + id + ".htm";
		$.colorbox({href:url, iframe:true, width:"80%", height:"100%"});
	}
	if(type == 2){
		
		var print_ctn = $("#print_ctn").html();
		var print_btn = $("#print_btn").html();
		OpenWindow('打印设置', print_ctn, print_btn);
		printid = id;
	}
}


function print(){
	var size = $("#print_size").val();
	if(size){
		if(!isNaN(size)){
			var url = "${ctx}/system/knowledge/paper/prints/" + printid + ".htm?size="+size;
			window.open(url,'newwindow','top=0,left=0,toolbar=no,menubar=no,scrollbars=yes,resizable=no,location=no,status=no');
			CloseModalBox();
		}
		else{
			alert("打印数量必须为数字");
		}
	}
	else{
		alert("请填写打印数量！");
	}
}

$(document).ready(function() {
	// Load Datatables and run plugin on tables 
	LoadDataTablesScripts_(AllTables);
	// Add Drag-n-Drop feature
	WinMove();
		
	$(".iframe").colorbox({iframe:true, width:"80%", height:"100%"});
	
});
</script>
