<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/common.jsp"%>
<%@ page isELIgnored="false" %>
<div class="row">
	<div id="breadcrumb" class="col-md-12">
		<ol class="breadcrumb">
			<li><a href="#">资质评估</a></li>
			<li><a href="#">指标重要性打分</a></li>
		</ol>
	</div>
</div>


<div id="tabs">
	<ul>
		<c:forEach items="${mylist}" var="cate">
			<li><a href="#tabs-${cate.id}" ondblclick="editcat(${cate.id})">${cate.name }</a></li>
		</c:forEach>
	</ul>
	
<c:forEach items="${mylist}" var="item">
	<div id="tabs-${item.id}">
		
		<form class="form-horizontal" role="form" id="mForm_${item.id}" method="post" action="${ctx}/system/estimate/indicatorscore/update.htm">
			<div class="row">
				<div class="col-xs-12">
					<div class="box">
						<div class="box-content no-padding table-responsive">
							<table aria-describedby="datatable-m_info" class="table table-bordered table-striped table-hover table-heading table-datatable dataTable" id="datatable-m">
								<thead>
									<tr role="row">
										<th rowspan="1" colspan="2">
												<span><i class="fa fa-clock-o"></i></span>
												指标重要性评分，分数越高，表明越重要。所有指标完成打分后请点击‘提交打分’按钮进行保存。			
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
										<th aria-sort="" colspan="2" rowspan="1" aria-controls="datatable-m" tabindex="0" role="columnheader" class="">一级指标</th>
									</tr>
								</thead>
								
								<tbody aria-relevant="all" aria-live="polite" role="alert">
									<tr class="odd">
											<td class=" sorting_1" width="50%">指标</td>
											<td class="">分数</td>
										</tr> 
										
									<c:forEach var="cur" items="${item.list}" varStatus="vs">   
										<tr class="odd">
											<td class=" sorting_1"><span class="span_">${cur.sequence} &nbsp; ${cur.name }</span></td>
											<td class="">
												<div class="col-sm-4">
													<input type="text" class="form-control" placeholder="分数" data-toggle="tooltip" data-placement="bottom" title="分数" name="field_${cur.id}" value="${cur.score}">
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
			
			<c:forEach var="cur" items="${item.list}" varStatus="vs">
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
			<div class="row">
					<div class="col-xs-12">
						<div class="box">
							<button type="submit" class="btn btn-primary btn-label-left">
												<span><i class="fa fa-clock-o"></i></span>
												提交打分
											</button>
						</div>
					</div>
			</div>
			
			<div id="output1"></div>
		</form>
	</div>
</c:forEach>
</div>
				

	


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

function showRequest(){
	var pass = true;
	var z= /^[0-9]*$/;
	$("input").each(function(index, element) {
		val = $(this).val();
		if(!z.test(val)){
			pass = false;
		}
    });
	
	if(!pass){
		OpenWindow('操作提示', '指标分数应为数字！');
	}
	return pass;
}

function showResponse(responseText, statusText, xhr, $form)  {
	var result = eval("("+responseText+")"); 

   if(result.status == 'success'){
	   LoadAjaxContent('${ctx}/system/estimate/indicatorscore/list.htm');
	   OpenWindow('操作结果', '您的操作已成功，记录已保存！');
   }
   else{
	   OpenWindow('操作结果', '您的操作出现错误，请重新再试！');
   }
}

$(document).ready(function() {
	
	$("#tabs").tabs();
	
	 var options = {
		        //target:        '#output1',   // target element(s) to be updated with server response
		        beforeSubmit:  showRequest,  // pre-submit callback
		        success:       showResponse  // post-submit callback
		 };
	// bind form using 'ajaxForm'
	<c:forEach items="${mylist}" var="item">
		$('#mForm_${item.id}').ajaxForm(options);
	</c:forEach>
});
</script>
