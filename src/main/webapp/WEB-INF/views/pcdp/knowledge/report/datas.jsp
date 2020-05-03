<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/common.jsp"%>
<%@ page isELIgnored="false" %>
<div class="row">
	<div id="breadcrumb" class="col-md-12">
		<ol class="breadcrumb">
			<li><a href="#">考试管理</a></li>
			<li><a href="#">成绩统计</a></li>
		</ol>
	</div>
</div>

<div class="row">
		<div class="col-xs-12 col-sm-12">
			<div class="box-content no-padding table-responsive">
					<table class="table table-bordered table-striped table-hover table-heading table-datatable" id="datatable-m">
						<thead>
							<tr>
								<th width="50%">
									<select class="populate placeholder" name="companyId" id="companys" onchange="changeCompany();">
										<option value="">请选择</option>
										<c:forEach items="${companys}" var="item">
											<option value="${item.id }" <c:if test="${item.id == companyId }">selected="selected"</c:if>>${item.name }</option>
										</c:forEach>
										
									</select>
								</th>
								<th>
									<c:forEach items="${companys}" var="item">
										<select class="populate placeholder sub" name="arrId" id="papers_${item.id}" style="display:none;" onchange="changeReport();">
									        <option value="">请选择</option>
									        <c:forEach items="${arranges}" var="arrange">
									        	<c:if test="${item.id == arrange.paper.company.id }">
									        		<option value="${arrange.id }" <c:if test="${arrange.id == arrId }">selected="selected"</c:if>>【<fmt:formatDate value="${arrange.examDate }" type="date"/> 】- ${arrange.paper.name }</option>
									        	</c:if>
									        </c:forEach>
									    </select>
									</c:forEach>
									
								</th>
							</tr>
						</thead>
					</table>
				</div>
				
			<div class="box-content no-padding table-responsive">
				<table aria-describedby="datatable-m_info" class="table table-bordered table-striped table-hover table-heading table-datatable dataTable" id="datatable-m">
					<thead>
						
						<tr role="row">
							<th>考生名称</th>
							<th>考试状态</th>
							<th>成绩(分)</th>
							<th>结果</th>
							<th>操作</th>
						</tr>
					</thead>
					
					<tbody aria-relevant="all" aria-live="polite" role="alert">
						<c:forEach var="cur" items="${list}" varStatus="vs">   
							<tr class="odd">
								<td class=" sorting_1"><span class="span_">${cur.user.name }</span></td>
								<td class=""">
									<c:if test="${cur.status == 'DRAFT' }">未签到</c:if>
									<c:if test="${cur.status == 'SIGNED' }">已签到</c:if>
									<c:if test="${cur.status == 'COMPLETED' }">已完成</c:if>
									<c:if test="${cur.status == 'CANCEL' }">已取消</c:if>
									<c:if test="${cur.status == 'ABSENT' }">缺席</c:if>
								</td>
								<td class=" sorting_1"><span class="span_">${cur.score.score }</span></td>
								<td class=""">
									<c:if test="${cur.score.passType == 'YES' }"><span style="color:green">通过</span></c:if>
									<c:if test="${cur.score.passType == 'NO' }"><span style="color:red">不通过</span></c:if>
								</td>
								<td class=" sorting_1">
									<c:if test="${cur.status == 'COMPLETED'}">
										<a href="#" onclick="edit(1,'${cur.id}');">查看成绩</a>&nbsp;&nbsp;<a href="#" onclick="edit(2,'${cur.id}');">打印</a>
									</c:if>
								</td>
								
							</tr> 
						</c:forEach> 
					</tbody>
				</table>
			</div>
	</div>
	
</div>

<script type="text/javascript">
	
$(document).ready(function() {

	
	$('#companys').select2();
	
	<c:forEach items="${companys}" var="item">
		$('#papers_${item.id}').select2();
	</c:forEach>
	
	var val = $("#companys").val();
	if(val){
		$('#s2id_papers_'+val).show();  //the prefix s2id_ is added by the select2 plugin
	}
});

function changeReport(){
	var compId = $("#companys").val();
	if(compId){
		var val = $("#papers_"+compId).val();
		LoadAjaxContent('${ctx}/system/knowledge/report/datas.htm?arrId='+val+"&companyId="+compId);
	}
}

function changeCompany(){
	var val = $("#companys").val();
	if(val){
		$(".sub").hide();
		//$(".sub").show();
		$('#s2id_papers_'+val).show();  //the prefix s2id_ is added by the select2 plugin
		
		//$("div has(select[id=papers_" + val + "])").hide();
	}
	
}

function edit(type, id){
	var url = "";
	if(type == 1){
		url = "${ctx}/system/knowledge/score/detail/" + id + ".htm";
		window.open(url,'newwindow','top=0,left=0,toolbar=no,menubar=no,scrollbars=yes,resizable=no,location=no,status=no');
	}
	if(type == 2){
		url = "${ctx}/system/knowledge/score/print/" + id + ".htm";
		window.open(url,'newwindow','top=0,left=0,toolbar=no,menubar=no,scrollbars=yes,resizable=no,location=no,status=no');
	}
	//var content = LoadContent('${ctx}/admin/user/show/1.htm');
	//OpenWindow(content);
	
}
</script>
