<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/common.jsp"%>

<c:forEach var="cur" items="${ilist}" varStatus="vs">   
  <tr class="odd">
	<td class=" sorting_1"><span class="span_${cur.level}">${cur.sequence} &nbsp; ${cur.name }</span></td>
	<td class=" sorting_1">${cur.weight}</td>
	<td class=" sorting_1">${cur.adjustWeight }</td>
	<td class="">
		<a href="javascript:edit(1,${cur.id});" style="color: #428bca;">调整权重</a>&nbsp;&nbsp;
	</td>
</tr> 
  <c:if test="${fn:length(cur.children) > 0}"><!-- 如果有childen --> 
    <c:set var="ilist" value="${cur.children}" scope="request" /><!-- 注意此处，子列表覆盖treeList，在request作用域 -->  
    <c:import url="tag.jsp" /><!-- 这就是递归了 -->  
  </c:if> 
</c:forEach>