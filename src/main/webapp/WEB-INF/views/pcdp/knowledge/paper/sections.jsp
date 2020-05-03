<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page isELIgnored="false" %> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/common.jsp"%>



					<table aria-describedby="datatable-m_info" class="table table-bordered table-striped table-hover table-heading table-datatable dataTable" id="datatable-m">
						<thead>
							<tr role="row">
								<th aria-label="指标名称: activate to sort column" aria-sort="" colspan="1" rowspan="1" aria-controls="datatable-m" tabindex="0" role="columnheader" class="">
									<select class="populate placeholder" name="sectionId" id="sections" onchange="changeSection();">
										<c:forEach items="${tops}" var="top">
											<option value=""></option>
											<option value="${top.id }" <c:if test="${fn:length(tops) == 1}">selected="selected"</c:if>>${top.name }</option>
										</c:forEach>
									</select>
								</th>
								<th aria-label="操作: activate to sort column" colspan="1" rowspan="1" aria-controls="datatable-m" tabindex="0" role="columnheader" class=""></th>
								<th aria-label="操作: activate to sort column" colspan="1" rowspan="1" aria-controls="datatable-m" tabindex="0" role="columnheader" class=""></th>
							</tr>
							<tr role="row">
								<th aria-label="指标名称: activate to sort column" aria-sort="" colspan="1" rowspan="1" aria-controls="datatable-m" tabindex="0" role="columnheader" class="">章节名称</th>
								<th aria-label="操作: activate to sort column" colspan="1" rowspan="1" aria-controls="datatable-m" tabindex="0" role="columnheader" class="">题库数量</th>
								<th aria-label="操作: activate to sort column" colspan="1" rowspan="1" aria-controls="datatable-m" tabindex="0" role="columnheader" class="">抽题数量</th>
							</tr>
						</thead>
						
						<tbody aria-relevant="all" aria-live="polite" role="alert" id="question_content">
							<c:import url="tag.jsp" />
						</tbody>
						<thead>
							<tr role="row">
								<th align="center" aria-label="指标名称: activate to sort column" aria-sort="" colspan="1" rowspan="1" aria-controls="datatable-m" tabindex="0" role="columnheader" class=""><span style="color:red">题目总数</span></th>
								<td></td>
								<th aria-label="操作: activate to sort column" colspan="1" rowspan="1" aria-controls="datatable-m" tabindex="0" role="columnheader" class=""><span style="color:red" id="totalsize"></span></th>
							</tr>
						</thead>
					</table>


<script>
	$('#sections').select2();
</script>