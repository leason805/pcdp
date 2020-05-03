<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/common.jsp"%>

<c:forEach var="cur" items="${list}" varStatus="vs">   
  <tr class="odd">
	<td class=" sorting_1" width="80%"><span class="span_${cur.level}">${cur.sequence} &nbsp; ${cur.name }</span></td>
	<td class="">
		<a href="#" onclick="edit(1,${cur.id});">添加下级指标</a>&nbsp;&nbsp;
		<a href="#" onclick="edit(2,${cur.id});">编辑</a>&nbsp;&nbsp;
		<a href="#" onclick="del(${cur.id});">删除</a>
	</td>
</tr> 
  <c:if test="${fn:length(cur.children) > 0}"><!-- 如果有childen --> 
    <c:set var="list" value="${cur.children}" scope="request" /><!-- 注意此处，子列表覆盖treeList，在request作用域 -->  
    <c:import url="tag.jsp" /><!-- 这就是递归了 -->  
  </c:if> 
</c:forEach>