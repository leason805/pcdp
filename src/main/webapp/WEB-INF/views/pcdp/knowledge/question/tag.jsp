<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/common.jsp"%>

<c:forEach var="cur" items="${list}" varStatus="vs">   
  <tr class="odd">
	<td class=" sorting_1" width="70%"><span class="span_${cur.level}">${cur.sequence} &nbsp; ${cur.name }</span></td>
	<td class=" sorting_1" width="10%">
		<c:if test="${fn:length(cur.children) <= 0}">${cur.questSize }</c:if> 
	</td>
	<td class="">
		<c:if test="${fn:length(cur.children) <= 0}"><a href="javascript:edit(1,${cur.id});">编辑试题</a></c:if> 
		<!--a href="${ctx}/system/estimate/indicator/pdf.htm">PDF</a-->
	</td>
</tr> 
  <c:if test="${fn:length(cur.children) > 0}"><!-- 如果有childen --> 
    <c:set var="list" value="${cur.children}" scope="request" /><!-- 注意此处，子列表覆盖treeList，在request作用域 -->  
    <c:import url="tag.jsp" /><!-- 这就是递归了 -->  
  </c:if> 
</c:forEach>