<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/common.jsp"%>

<c:forEach var="cur" items="${sections}" varStatus="vs">   
  <tr class="odd">
	<td class=" sorting_1" width="70%"><span class="span_${cur.level}">${cur.sequence} &nbsp; ${cur.name }</span></td>
	<td class=" sorting_1" width="10%"><c:if test="${fn:length(cur.children) <= 0}">${cur.questSize }</c:if></td>
	<td class="">
		<c:if test="${fn:length(cur.children) <= 0}">
		
			<input type="text" class="form-control" placeholder="该章节抽题目数量" data-toggle="tooltip" data-placement="bottom" style="width: 60%;" id="item_${cur.id}" name="item_${cur.id}" onblur="totalsize();" value=""  <c:if test="${cur.questSize <= 0}">readonly="readonly"</c:if>>
		</c:if>
	</td>
</tr> 
  <c:if test="${fn:length(cur.children) > 0}"><!-- 如果有childen --> 
    <c:set var="sections" value="${cur.children}" scope="request" /><!-- 注意此处，子列表覆盖treeList，在request作用域 -->  
    <c:import url="tag.jsp" /><!-- 这就是递归了 -->  
  </c:if> 
</c:forEach>