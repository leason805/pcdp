<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/common.jsp"%>
						
<c:forEach var="cur" items="${children}" varStatus="vs">   
	<tr class="odd">
		<td class=" sorting_1"><span class="span_${cur.level}">${cur.sequence} &nbsp; ${cur.name }</span></td>
		<td class="">
			<div class="col-sm-4">
				<c:choose>
       				<c:when test="${cur.mandatory == 'YES' }">
							硬性指标
       				</c:when>
       				<c:otherwise>
              				${cur.score}
       				</c:otherwise>
				</c:choose>
			</div>
		</td>
	</tr>
  <c:if test="${fn:length(cur.children) > 0}"><!-- 如果有childen --> 
    <c:set var="children" value="${cur.children}" scope="request" /><!-- 注意此处，子列表覆盖treeList，在request作用域 -->  
    <c:import url="r_tag.jsp" /><!-- 这就是递归了 -->  
  </c:if> 
</c:forEach> 