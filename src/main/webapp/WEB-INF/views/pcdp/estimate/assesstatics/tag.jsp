<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/common.jsp"%>

<c:forEach var="cur" items="${list}" varStatus="vs">   
  <tr 
  	<c:if test="${fn:length(cur.children) > 0}">
  		<c:choose>	
  			<c:when test="${empty cur.parent }">bgcolor="#C0C0C0"</c:when>
  			<c:otherwise>bgcolor="#DDDDDD"</c:otherwise>
  		</c:choose>
  	</c:if>
  	>
  	
	<td class=" sorting_1" width="5%"><span class="span_${cur.level}">${cur.sequence} &nbsp; </span></td>
	<td class=" sorting_1" width="20%">${cur.name }</td>
	<td class=" sorting_1" width="25%">
		${cur.result }
	</td>
	<c:forEach items="${cur.userScores}" var="userscore">
		<td>
			${userscore.score }
		</td>
	</c:forEach>
</tr> 
  <c:if test="${fn:length(cur.children) > 0}"><!-- 如果有childen --> 
    <c:set var="list" value="${cur.children}" scope="request" /><!-- 注意此处，子列表覆盖treeList，在request作用域 -->  
    <c:import url="tag.jsp" /><!-- 这就是递归了 -->  
  </c:if> 
</c:forEach>