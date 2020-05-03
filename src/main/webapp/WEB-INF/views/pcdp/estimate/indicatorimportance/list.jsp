<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/common.jsp"%>
<%@ page isELIgnored="false" %>
<div class="row">
	<div id="breadcrumb" class="col-md-12">
		<ol class="breadcrumb">
			<li><a href="#">资质评估</a></li>
			<li><a href="#">指标重要程度评估</a></li>
		</ol>
	</div>
</div>

<div id="tabs">
	<ul>
		<c:forEach items="${mylist}" var="cate">
			<li><a href="#tabs-${cate.id}" ondblclick="editcat(${cate.id})">${cate.name }</a></li>
		</c:forEach>
	</ul>
	
<c:forEach items="${mylist}" var="impo">
	<div id="tabs-${impo.id}">
		<form class="form-horizontal" role="form" id="mForm_${impo.id}" method="post" action="${ctx}/system/estimate/indicatorimpo/update.htm">

		<div class="row">
			<div class="col-xs-12">
				<div class="box">
					<div class="box-content no-padding table-responsive">
						<table aria-describedby="datatable-m_info" class="table table-bordered table-striped table-hover table-heading table-datatable dataTable" id="datatable-m">
							<thead>
								<tr role="row">
									<th rowspan="1" colspan="2">
											<span><i class="fa fa-clock-o"></i></span>
											指标重要性评估，前一个指标是后一个指标的1.0 - 1.8倍，所以指标的重要程度评估完成后请点击‘提交评估结果’按钮进行保存		
									</th>
								</tr>
								<tr role="row">
									<th rowspan="1" colspan="2" >
										<button type="submit" class="btn btn-primary btn-label-left">
												<span><i class="fa fa-clock-o"></i></span>
												提交评估结果
											</button>
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
										<th aria-sort="" colspan="6" rowspan="1" aria-controls="datatable-m" tabindex="0" role="columnheader" class="">
											
											<c:choose>
						       					<c:when test="${item.level == 1}">
													 一级指标 
						       					</c:when>
						       					<c:otherwise>
						              				【${item.label }】的 ${item.level} 级指标
						       					</c:otherwise>
											</c:choose>
											
										</th>
									</tr>
								</thead>
								<tbody aria-relevant="all" aria-live="polite" role="alert">	
									<c:forEach var="cur" items="${item.list}" varStatus="status">   
										<c:if test="${status.index%3==0}">
											<tr class="odd">
										</c:if>
										
										<td class=" sorting_1" width="33%">
												<table width="100%"  class="table table-bordered table-striped table-hover table-heading table-datatable dataTable">
													<tr>
														<td class=" sorting_1" align="center" width="60%">${cur.firstName }</td>
														<td class="" rowspan="2" align="center">
															<div class="col-sm-4">
																<select  class="form-control" name="field_${cur.firstId}_${cur.secondId}_${status.index+1}" style="width:55px;padding: 2px 8px;">
																	<option value="0"></option>
																	<option value="1.0" <c:if test="${cur.rating==1}">selected="selected"</c:if>>1.0</option>
																	<option value="1.2" <c:if test="${cur.rating==1.2}">selected="selected"</c:if>>1.2</option>
																	<option value="1.4" <c:if test="${cur.rating==1.4}">selected="selected"</c:if>>1.4</option>
																	<option value="1.6" <c:if test="${cur.rating==1.6}">selected="selected"</c:if>>1.6</option>
																	<option value="1.8" <c:if test="${cur.rating==1.8}">selected="selected"</c:if>>1.8</option> 
																</select>
															</div>
														</td>
													</tr>
													<tr>
														<td class=" sorting_1" align="center"><span class="span_">${cur.secondName }</span></td>
													</tr>
												</table>
											</td>
										
										<c:if test="${status.index%3==2}">
											</tr> 
										</c:if>
										<c:if test="${fn:length(item.list) == (status.index+1)}">
											<c:if test="${fn:length(item.list)%3 ==2}">
												<td class=" sorting_1" width="33%">
												</td>
												
											</c:if>
											<c:if test="${fn:length(item.list)%3 ==1}">
												<td class=" sorting_1" width="33%">
												</td>
												<td class=" sorting_1" width="33%">
												</td>
											</c:if>
											</tr> 
										</c:if>
										
									</c:forEach> 
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
											提交评估结果
										</button>
					</div>
				</div>
		</div>
		
		</form>
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
	var pass = true;
	$("select").each(function(index, element) {
		val = $(this).val();
		if(!val){
			pass = false;
		}
    });
	
	if(!pass){
		OpenWindow('操作提示', '请选择指标重要程度！');
	}
	return pass;
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
	 <c:forEach items="${mylist}" var="item">
		$('#mForm_${item.id}').ajaxForm(options);
	</c:forEach>
	
});
</script>
