<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/common.jsp"%>

<c:forEach var="cur" items="${ilist}" varStatus="vs">   
  <tr class="odd">
	<td class=" sorting_1" width="70%"><span class="span_${cur.level}">${cur.sequence} &nbsp; ${cur.name }</span></td>
	<td class=" sorting_1" width="8%">
		<c:if test="${not empty cur.associate}">
			<span style="color:green">已关联</span>
		</c:if>
	</td>
	<td class="">
		<a href="javascript:edit(1,${cur.id});" style="color: #428bca;">添加下级指标</a>&nbsp;&nbsp;
		<a href="javascript:edit(2,${cur.id});" style="color: #428bca;">编辑</a>&nbsp;&nbsp;
		<a href="javascript:del(${cur.id});" style="color: #428bca;">删除</a>&nbsp;&nbsp;
		<c:if test="${fn:length(cur.children) <= 0}"><a href="javascript:edit(3,${cur.id});" style="color: #428bca;">关联</a></c:if> 
		
		<!--a href="${ctx}/system/estimate/indicator/pdf.htm">PDF</a-->
	</td>
</tr> 
  <c:if test="${fn:length(cur.children) > 0}"><!-- 如果有childen --> 
    <c:set var="ilist" value="${cur.children}" scope="request" /><!-- 注意此处，子列表覆盖treeList，在request作用域 -->  
    <c:import url="tag.jsp" /><!-- 这就是递归了 -->  
  </c:if> 
</c:forEach>