<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/common.jsp"%>
<%@ page isELIgnored="false" %>
<div class="row">
	<div id="breadcrumb" class="col-md-12">
		<ol class="breadcrumb">
			<li><a href="#">资质评估</a></li>
			<li><a href="#">指标重要程度分数</a></li>
		</ol>
	</div>
</div>

<div id="tabs">
	<ul>
		<c:forEach items="${list}" var="cate">
			<li><a href="#tabs-${cate.id}" ondblclick="editcat(${cate.id})">${cate.name }</a></li>
		</c:forEach>
	</ul>
	
<c:forEach items="${list}" var="impo">
	<div id="tabs-${impo.id}">
	
		<div class="row">
			<div class="col-xs-12">
				<div class="box">
					<div class="box-content no-padding table-responsive">
						<table aria-describedby="datatable-m_info" class="table table-bordered table-striped table-hover table-heading table-datatable dataTable" id="datatable-m">
							<thead>
								<tr role="row">
									<th rowspan="1" colspan="2">
											<span><i class="fa fa-clock-o"></i></span>
											共 <b style="color:red;">${impo.size}</b> 位专家评分					
									</th>
								</tr>
							</thead>
						</table>
					</div>
				</div>
			</div>
		</div>
		
		<c:forEach items="${impo.list}" var="item">
			<div class="row">
				<div class="col-xs-12">
					<div class="box">
						<div class="box-content no-padding table-responsive">
							<table aria-describedby="datatable-m_info" class="table table-bordered table-striped table-hover table-heading table-datatable dataTable" id="datatable-m">
								<thead>
									<tr role="row">
										<th aria-sort="" colspan="1" rowspan="1" aria-controls="datatable-m" tabindex="0" role="columnheader" class="">
											
											<c:choose>
						       					<c:when test="${item.level == 1}">
													 一级指标 
						       					</c:when>
						       					<c:otherwise>
						              				【${item.label }】的 ${item.level} 级指标
						       					</c:otherwise>
											</c:choose>
											
										</th>
										<c:forEach items="${impo.names}" var="name">
												<td class="">${name}</td>
										</c:forEach>
									</tr>
								</thead>
								<tbody aria-relevant="all" aria-live="polite" role="alert">	
									<c:forEach var="cur" items="${item.list}" varStatus="status">   
									
									<tr class="odd">
										<td class=" sorting_1" width="33%">
												<table width="100%"  class="table table-bordered table-striped table-hover table-heading table-datatable dataTable">
													<tr>
														<td class=" sorting_1" align="center" width="60%" style="padding: 6px;">${cur.firstName }</td>
														<td class="" rowspan="2" align="center" style="padding: 6px;">
															<div class="col-sm-4">
																<select  class="form-control" name="field_${cur.firstId}_${cur.secondId}_${status.index+1}" disabled="disabled" style="width:65px; padding: 2px 4px;">
																	<option value="${cur.rating}"">${cur.rating}</option> 
																</select>
															</div>
														</td>
													</tr>
													<tr>
														<td class=" sorting_1" align="center" style="padding: 6px;"><span class="span_">${cur.secondName }</span></td>
													</tr>
												</table>
										</td>
									<c:forEach items="${cur.userScores}" var="userscore">
										<td>
											<c:if test="${userscore.rating <= 0}"><span style="color:red;font-weight:bold;"></c:if>
											${userscore.rating }
											<c:if test="${userscore.rating <= 0}"></span></c:if>
										</td>
									</c:forEach>
									</tr> 								
										
									</c:forEach> 
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</c:forEach>
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

function showRequest(){
	return true;
}

function showResponse(responseText, statusText, xhr, $form)  {
	var result = eval("("+responseText+")"); 

   // alert('status: ' + statusText + '\n\nresponseText: \n' + responseText +
    //    '\n\nThe output div should have already been updated with the responseText.');
   if(result.status == 'success'){
	   LoadAjaxContent('${ctx}/system/estimate/indicatorimpo/list.htm');
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
	$('#mForm').ajaxForm(options);
	
});
</script>
