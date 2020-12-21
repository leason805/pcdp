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

		<link href="${ctx}/plugins/jquery-easyui/themes/default/easyui.css" rel="stylesheet">
		<link href="${ctx}/plugins/jquery-easyui/themes/icon.css" rel="stylesheet">
		
		<link href="${ctx}/css/style.css" rel="stylesheet">
		
		<link href="${ctx}/plugins/fancytree/skin-lion/ui.fancytree.css" rel="stylesheet">
		
		<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
		<!--[if lt IE 9]>
				<script src="http://getbootstrap.com/docs-assets/js/html5shiv.js"></script>
				<script src="http://getbootstrap.com/docs-assets/js/respond.min.js"></script>
		<![endif]-->
	


<script src="${ctx}/plugins/jquery/jquery-2.1.0.min.js"></script>
<script src="${ctx}/plugins/jquery/jquery-migrate-1.2.1.min.js"></script>

<script src="${ctx}/plugins/jquery-ui/jquery-ui.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="${ctx}/plugins/bootstrap/bootstrap.min.js"></script>
<script src="${ctx}/plugins/justified-gallery/jquery.justifiedgallery.min.js"></script>
<script src="${ctx}/plugins/tinymce/tinymce.min.js"></script>
<script src="${ctx}/plugins/tinymce/jquery.tinymce.min.js"></script>
<script src="${ctx}/plugins/select2/select2.min.js"></script>

<script src="${ctx}/plugins/datatables/jquery.dataTables.js"></script>
<script src="${ctx}/plugins/datatables/ZeroClipboard.js"></script>
<script src="${ctx}/plugins/datatables/TableTools.js"></script>
<script src="${ctx}/plugins/datatables/dataTables.bootstrap.js"></script>
<script src="${ctx}/plugins/datatables/fnReloadAjax.js"></script>

<script src="${ctx}/plugins/jquery-easyui/json2.js"></script>
<script src="${ctx}/plugins/jquery-easyui/jquery.easyui.min.js"></script>
<script src="${ctx}/plugins/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>

<script src="${ctx}/plugins/fancytree/jquery.fancytree.js"></script>

<!-- All functions for this theme + document.ready processing -->
<script src="${ctx}/js/bx.js"></script>

<script type="text/javascript">

</script>

</head>


<body>
<form class="form-horizontal" role="form" method="post" action="${ctx}/system/capacity/evaluatereview/update/${model.id}.htm">
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
						
					</div>
					<div class="no-move"></div>
				</div>
				<div class="box-content no-padding table-responsive">
						<table aria-describedby="datatable-m_info" class="table table-bordered  table-hover table-heading table-datatable dataTable" id="datatable-m">
							<thead>
								<tr role="row">
									<th aria-sort="" colspan="1" rowspan="1" aria-controls="datatable-m" tabindex="0" role="columnheader" class="">序号</th>
									<th colspan="1" rowspan="1" aria-controls="datatable-m" tabindex="0" role="columnheader" class="">资质能力评估指标</th>
									<th colspan="1" rowspan="1" aria-controls="datatable-m" tabindex="0" role="columnheader" class="">评分</th>
									<th colspan="1" rowspan="1" aria-controls="datatable-m" tabindex="0" role="columnheader" class="">评估点</th>
								</tr>
							</thead>
							
							<tbody aria-relevant="all" aria-live="polite" role="alert">
								<c:set var="list" value="${set.list}" scope="request" /><!-- 注意此处，子列表覆盖treeList，在request作用域 -->  
								<c:import url="r_tag.jsp" />
							</tbody>
						</table>
				</div>

			</div>
		</div>
	</div>
</form>	
	<script type="text/javascript">
// Run Datables plugin and create 3 variants of settings



	$(document).ready(function() {
		
	});
	
	
	var curr;
	
	function edit(obj, id){
		curr = obj;
		var nTr = $(obj).parents('tr')[0];
        if (oTable.fnIsOpen(nTr)) //判断是否已打开
        {
            /* This row is already open - close it */
            $(this).addClass("row-details-close").removeClass("row-details-open");
            oTable.fnClose(nTr);
        } else {
            /* Open this row */
            $(this).addClass("row-details-open").removeClass("row-details-close");
            //  alert($(this).attr("data_id"));
            //oTable.fnOpen( nTr,
            // 调用方法显示详细信息 data_id为自定义属性 存放配置ID
            fnFormatDetails(nTr, id);
        }
		
	}
	
	function closeTr(){
		if(curr){
			var nTr = $(curr).parents('tr')[0];
	        if (oTable.fnIsOpen(nTr)) //判断是否已打开
	        {
	            /* This row is already open - close it */
	            $(this).addClass("row-details-close").removeClass("row-details-open");
	            oTable.fnClose(nTr);
	        }
			curr = null;
		}
	}
	 
	   function fnFormatDetails(nTr, pdataId) {
		   var url ="${ctx}/system/knowledge/question/detail.htm?id="+pdataId;
		   
		   $.ajax({
				mimeType: 'text/html; charset=utf-8', // ! Need set mimeType only when run from local file
				url: url,
				type: 'GET',
				success: function(data) {
					oTable.fnOpen(nTr, data, 'details');
				},
				error: function (jqXHR, textStatus, errorThrown) {
					alert(errorThrown);
				},
				dataType: "html",
				async: false
			});
		   
		   
		   /*
	       //根据配置Id 异步查询数据
	       $.get("../resources/user_share/row_details/language.txt",
	               function(json) {
	                   var array = json.data;
	                   for (var i = 0; i < array.length; i++) {
	                       if (pdataId == array[i].language) {
	                           var sOut = '<center> <p style="width:70%">' + array[i].desc + '<a target="_blank" href="' + array[i].url + '">更多</a></p></center>';
	                           oTable.fnOpen(nTr, sOut, 'details');
	                       }
	                   }
	        });
	       */
	   }
</script>
</body>

</html>
