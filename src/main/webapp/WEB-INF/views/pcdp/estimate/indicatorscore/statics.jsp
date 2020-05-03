<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/common.jsp"%>
<%@ page isELIgnored="false" %>
<div class="row">
	<div id="breadcrumb" class="col-md-12">
		<ol class="breadcrumb">
			<li><a href="#">资质评估</a></li>
			<li><a href="#">指标重要性分数</a></li>
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
			<div class="row">
				<div class="col-xs-12">
					<div class="box">
						<div class="box-content no-padding table-responsive">
							<table aria-describedby="datatable-m_info" class="table table-bordered table-striped table-hover table-heading table-datatable dataTable" id="datatable-m">
								<thead>
									<tr role="row">
										<th rowspan="1" colspan="${item.size+2}">
												<span><i class="fa fa-clock-o"></i></span>
												共 <b style="color:red;">${item.size}</b> 位专家评分			
										</th>
									</tr>
									
									<tr role="row">
										<th aria-sort="" colspan="${item.size+2}" rowspan="1" aria-controls="datatable-m" tabindex="0" role="columnheader" class="">一级指标</th>
									</tr>
								</thead>
								
								<tbody aria-relevant="all" aria-live="polite" role="alert">
									<tr class="odd">
											<td class=" sorting_1" width="50%">指标</td>
											<td class="" width="18%"><b>总分</b></td>
											<c:forEach items="${item.names}" var="name">
											 	<td class="">${name}</td>
											</c:forEach>
											
										</tr> 
										
									<c:forEach var="cur" items="${item.list}" varStatus="vs">   
										<tr class="odd">
											<td class=" sorting_1"><span class="span_">${cur.sequence} &nbsp; ${cur.name }</span></td>
											<td class="">
												<div class="col-sm-4">
													<b>${cur.score}</b>
												</div>
											</td>
											<c:forEach items="${cur.userScores}" var="userscore">
												<td>
													${userscore.score }
													<c:if test="${empty userscore.score}"><span style="color:red;font-weight:bold;">N/A</span></c:if>
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
			
			<c:forEach var="cur" items="${item.list}" varStatus="vs">
				<div class="row">
					<div class="col-xs-12">
						<div class="box">
							<div class="box-content no-padding table-responsive">
								<table aria-describedby="datatable-m_info" class="table table-bordered table-striped table-hover table-heading table-datatable dataTable" id="datatable-m">
									<thead>
										<tr role="row">
											<th aria-sort="" colspan="${item.size+2}" rowspan="1" aria-controls="datatable-m" tabindex="0" role="columnheader" class="">【${cur.name }】 的下级指标</th>
										</tr>
									</thead>
									
									<tbody aria-relevant="all" aria-live="polite" role="alert">
										<tr class="odd">
												<td class=" sorting_1" width="50%">指标</td>
												<td class=""><b>总分</b></td>
												<c:forEach items="${item.names}" var="name">
												 	<td class="">${name}</td>
												</c:forEach>
											</tr> 
											
											<c:set var="children" value="${cur.children}" scope="request" />
											<c:import url="stat_tag.jsp" />
											
										
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</c:forEach>
			
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
	return true;
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
	$('#mForm').ajaxForm(options);
	
});
</script>
