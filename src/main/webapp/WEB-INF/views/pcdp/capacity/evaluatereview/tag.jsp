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
  	
	<td class=" sorting_1" width="8%"><span class="span_1">${cur.sequence} &nbsp; </span></td>
	<td class=" sorting_1" width="25%">${cur.name }</td>
	<td class=" sorting_1" width="20%">
		<c:if test="${fn:length(cur.children) <= 0}">
			<input type="radio" name="indicator_${cur.id}" id="indicator_${cur.id}_1" value="1"> 1
			<input type="radio" name="indicator_${cur.id}" id="indicator_${cur.id}_2" value="2"> 2
			<input type="radio" name="indicator_${cur.id}" id="indicator_${cur.id}_3" value="3"> 3
			<input type="radio" name="indicator_${cur.id}" id="indicator_${cur.id}_4" value="4"> 4
			
		</c:if>
	</td>
	<td class=" sorting_1">
		<c:if test="${fn:length(cur.items) > 0}">
			<table>
				<c:forEach items="${cur.items}" var="item">
					<tr>
						<td>${item.sequence }、${item.description }
						<br/>
						<input type="radio" name="item_${cur.id}_${item.id}" id="item_${cur.id}_${item.id}_4" value="4" onclick="checkScore(this);"> 优
						<input type="radio" name="item_${cur.id}_${item.id}" id="item_${cur.id}_${item.id}_3" value="3" onclick="checkScore(this);"> 良
						<input type="radio" name="item_${cur.id}_${item.id}" id="item_${cur.id}_${item.id}_2" value="2" onclick="checkScore(this);"> 中
						<input type="radio" name="item_${cur.id}_${item.id}" id="item_${cur.id}_${item.id}_1" value="1" onclick="checkScore(this);"> 差
					</td>
					</tr>
				</c:forEach>
			</table>
		</c:if>
	</td>
</tr> 
  <c:if test="${fn:length(cur.children) > 0}"><!-- 如果有childen --> 
    <c:set var="list" value="${cur.children}" scope="request" /><!-- 注意此处，子列表覆盖treeList，在request作用域 -->  
    <c:import url="tag.jsp" /><!-- 这就是递归了 -->  
  </c:if> 
</c:forEach>