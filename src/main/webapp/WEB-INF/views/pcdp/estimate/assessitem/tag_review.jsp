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
		<c:if test="${fn:length(cur.children) <= 0}">
			<c:choose>	
	  			<c:when test="${cur.mandatory == 'YES' }">
					<div class="col-sm-12">
						<c:if test="${cur.score == 1 }"> 符合 </c:if>
						<c:if test="${cur.score == 0 }"> 不符合 </c:if>
					</div>
	  			</c:when>
	  			<c:otherwise>
	  				<div class="col-sm-12">
						${cur.score}
					</div>
	  			</c:otherwise>
	  		</c:choose>
		</c:if>
	</td>
	<td class="" width="25%">
		<c:if test="${cur.mandatory ne 'YES' }">${cur.calculation}</c:if>
	</td>
</tr> 
  <c:if test="${fn:length(cur.children) > 0}"><!-- 如果有childen --> 
    <c:set var="list" value="${cur.children}" scope="request" /><!-- 注意此处，子列表覆盖treeList，在request作用域 -->  
    <c:import url="tag_review.jsp" /><!-- 这就是递归了 -->  
  </c:if> 
</c:forEach>