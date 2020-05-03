<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/common.jsp"%>
<%@ page isELIgnored="false" %>
<div class="row">
	<div id="breadcrumb" class="col-md-12">
		<ol class="breadcrumb">
			<li><a href="#">我的档案</a></li>
			<li><a href="#">综合经历</a></li>
		</ol>
	</div>
</div>

<div class="row">
	<div class="col-xs-12">
		<div class="box">
			<div class="box-header">
				<div class="box-name">
					<i class="fa fa-search"></i>
					<span>综合经历</span>
				</div>
				<div class="box-icons pull-right">
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
			<div class="box-content">
				<div id="tabs">
					<ul>
						<c:forEach var="cur" items="${list}" varStatus="vs"> 
							<li><a href="#tabs-${cur.categoryId}">${cur.categoryName }</a></li>
						</c:forEach>						
					</ul>
					<c:forEach var="cur" items="${list}" varStatus="vs">
						<div id="tabs-${cur.categoryId}">
							<div class="box-content accor" id="accordion_${cur.categoryId}">
							
								<c:forEach items="${cur.list}" var="child">
									<h3>${child.categoryName }</h3>
								
									<div class="box-content no-padding table-responsive">
										<table aria-describedby="datatable-m_info" class="table table-bordered table-striped table-hover table-heading table-datatable dataTable" id="datatable-m">
											<thead>
												<tr role="row">
													<th rowspan="1" colspan="6">
														<a class="iframe btn btn-primary btn-label-left cboxElement" href="${ctx}/system/archive/experience/show/.htm?categoryId=${child.categoryId }">
															<span><i class="fa fa-clock-o"></i></span>
															添加
														</a>
													</th>
												</tr>
												<tr role="row">
													<th aria-label="指标名称: activate to sort column" aria-sort="" colspan="1" rowspan="1" aria-controls="datatable-m" tabindex="0" role="columnheader" class="">公司名称</th>
													<th aria-label="指标名称: activate to sort column" aria-sort="" colspan="1" rowspan="1" aria-controls="datatable-m" tabindex="0" role="columnheader" class="">部门名称</th>
													<th aria-label="指标名称: activate to sort column" aria-sort="" colspan="1" rowspan="1" aria-controls="datatable-m" tabindex="0" role="columnheader" class="">开始时间</th>
													<th aria-label="指标名称: activate to sort column" aria-sort="" colspan="1" rowspan="1" aria-controls="datatable-m" tabindex="0" role="columnheader" class="">结束时间</th>
													<th aria-label="指标名称: activate to sort column" aria-sort="" colspan="1" rowspan="1" aria-controls="datatable-m" tabindex="0" role="columnheader" class="">审核状态</th>
													<th aria-label="操作: activate to sort column" colspan="1" rowspan="1" aria-controls="datatable-m" tabindex="0" role="columnheader" class="">操作</th>
												</tr>
											</thead>
											
											<tbody aria-relevant="all" aria-live="polite" role="alert">
												
												<c:forEach items="${child.list}" var="pojo">
													<tr class="odd">
														<td class=" sorting_1"><span class="span_"> &nbsp; ${pojo.company }</span></td>
														<td class="">${pojo.department }</td>
														<td class="">${pojo.startDate }</td>
														<td class="">
															<c:choose>
																<c:when test="${not empty pojo.endDate }">${pojo.endDate }</c:when>
																<c:otherwise>至今</c:otherwise>
															</c:choose> 
														 </td>
														 <td class="">
																 	<c:if test="${pojo.status == 'PENDING' }">待审核</c:if>
																 	<c:if test="${pojo.status == 'PASS' }"><span style="color:green">审核通过</span></c:if>
																 	<c:if test="${pojo.status == 'REJECT' }"><span style="color:red">审核不通过</span></c:if>
																 </td>
														<td class="">
															<a style="color: #428bca;" href="#" onclick="edit('${pojo.id}', '${pojo.categoryId }');">编辑</a>&nbsp;&nbsp;
															<a style="color: #428bca;" href="#" onclick="del('${pojo.id}');">删除</a>
														</td>
													</tr>

												</c:forEach>
											</tbody>
											
											
											
										</table>
									</div>
								</c:forEach>
							</div>
						</div>
					</c:forEach>
				</div>
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
var icons = {
		header: "ui-icon-circle-arrow-e",
		activeHeader: "ui-icon-circle-arrow-s"
};

$(document).ready(function() {

	$(".iframe").colorbox({iframe:true, width:"60%", height:"100%"});
	
	$("#tabs").tabs();
	
	
		// Make accordion feature of jQuery-UI
		$("div.accor").accordion({icons: icons });
		
		<c:forEach var="cur" items="${list}" varStatus="vs">
		 	$("#accordion_${cur.categoryId}").accordion({icons: icons });
		</c:forEach>
		
});

function edit(id, categoryId){
	var url = "${ctx}/system/archive/experience/show/" + id + ".htm?categoryId="+categoryId;
	$.colorbox({href:url, iframe:true, width:"60%", height:"100%"});
}

function refresh(){
	LoadAjaxContent('${ctx}/system/archive/experience/index.htm');
}

var fid;
function del(id){
	var header = $('#header_').html();
	var inner = $('#inner_').html();
	var bottom = $("#bottom_").html();
	fid = id;
	OpenWindow(header, inner, bottom);
}


function deleteAction(){
	if(fid){
		var url = '${ctx}/system/archive/experience/delete/' + fid + ".htm";
		$.ajax({
			//mimeType: 'text/html; charset=utf-8', // ! Need set mimeType only when run from local file
			url: url,
			type: 'POST',
			success: function(data) {
				if(data.status == 'success'){
					CloseModalBox();
					refresh();
				}
			},
			error: function (jqXHR, textStatus, errorThrown) {
				alert(errorThrown);
			},
			dataType: "json",
			async: false
		});
	}
}
</script>
