<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

<div id="tabs">
	<ul>
		<c:forEach items="${list}" var="cate">
			<li><a href="#tabs-${cate.id}" ondblclick="editcat(${cate.id})">${cate.name }</a></li>
		</c:forEach>
		
	</ul>
	
<c:forEach items="${list}" var="item">
		<div id="tabs-${item.id}">	
		
			<form class="form-horizontal" role="form" id="mForm_${item.id }" method="post" action="${ctx}/system/estimate/indicatoralarm/update.htm">
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
												超过预警值将在统计分析中显示红色，并要求签派员参加培训。			
										</th>
									</tr>
									<tr role="row">
													<th rowspan="1" colspan="2" >
														<button type="submit" class="btn btn-primary btn-label-left">
															<span><i class="fa fa-clock-o"></i></span>
															提交打分
														</button>
														
													</th>
												</tr>
									<tr role="row">
										<th aria-sort="" colspan="2" rowspan="1" aria-controls="datatable-m" tabindex="0" role="columnheader" class="">预警设置</th>
									</tr>
								</thead>
								
								<tbody aria-relevant="all" aria-live="polite" role="alert">
									<tr class="odd">
											<td class=" sorting_1"><span class="span_">平均分低于</span></td>
											<td class="">
												<div class="col-sm-4">
													<input class="form-control" placeholder="分数" data-toggle="tooltip" data-placement="bottom" title="分数" name="field_${item.id}_0" value="${item.parentAlaram }" type="text">
												</div>
											</td>
									</tr> 
									<c:forEach items="${item.list}" var="cur">
										<tr class="odd">
											<td class=" sorting_1"><span class="span_">【 ${cur.name }】低于</span></td>
											<td class="">
												<div class="col-sm-4">
													<input class="form-control" placeholder="分数" data-toggle="tooltip" data-placement="bottom" title="分数" name="field_${item.id}_${cur.id }" value="${cur.alarm }" type="text">
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
			</form>	
		</div>
</c:forEach>

<script type="text/javascript">

function showRequest(){
	var pass = true;
	var z= /^d+(.d+)?$/;
	$("input").each(function(index, element) {
		val = $(this).val();
		if(!!isNaN(val)){
			pass = false;
		}
    });
	
	if(!pass){
		OpenWindow('操作提示', '分数应为数字！');
	}
	return pass;
}

function showResponse(responseText, statusText, xhr, $form)  {
	var result = eval("("+responseText+")"); 

   if(result.status == 'success'){
	   LoadAjaxContent('${ctx}/system/estimate/indicatoralarm/list.htm');
	   OpenWindow('操作结果', '您的操作已成功，记录已保存！');
   }
   else{
	   OpenWindow('操作结果', '您的操作出现错误，请重新再试！');
   }
}

$(document).ready(function() {
	// Load Datatables and run plugin on tables 
	
	// Add Drag-n-Drop feature
	WinMove();
	$("#tabs").tabs();	
	var options = {
	        //target:        '#output1',   // target element(s) to be updated with server response
	        beforeSubmit:  showRequest,  // pre-submit callback
	        success:       showResponse  // post-submit callback
	 };
// bind form using 'ajaxForm'
	<c:forEach items="${list}" var="item">
		$('#mForm_${item.id}').ajaxForm(options);
	</c:forEach>
	
});
</script>
