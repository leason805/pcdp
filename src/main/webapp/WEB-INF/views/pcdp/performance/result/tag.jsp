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
			<input type="radio" name="indicator_${cur.id}" value="1" disabled="disabled" <c:if test="${cur.score == 1 }">checked="checked"</c:if>> 1
			<input type="radio" name="indicator_${cur.id}" value="2" disabled="disabled" <c:if test="${cur.score == 2 }">checked="checked"</c:if>> 2
			<input type="radio" name="indicator_${cur.id}" value="3" disabled="disabled" <c:if test="${cur.score == 3 }">checked="checked"</c:if>> 3
			<input type="radio" name="indicator_${cur.id}" value="4" disabled="disabled" <c:if test="${cur.score == 4 }">checked="checked"</c:if>> 4
			
		</c:if>
	</td>
	<td class=" sorting_1">
		<c:if test="${fn:length(cur.supItems) > 0}">
			<table>
				<c:forEach items="${cur.supItems}" var="item">
					<tr>
						<td>${item.sequence }、${item.description }
						<br/>
						<input type="radio" name="item_${item.id}" value="4" disabled="disabled" <c:if test="${item.score == 4 }">checked="checked"</c:if>> 优
						<input type="radio" name="item_${item.id}" value="3" disabled="disabled" <c:if test="${item.score == 3 }">checked="checked"</c:if>> 良
						<input type="radio" name="item_${item.id}" value="2" disabled="disabled" <c:if test="${item.score == 2 }">checked="checked"</c:if>> 中
						<input type="radio" name="item_${item.id}" value="1" disabled="disabled" <c:if test="${item.score == 1 }">checked="checked"</c:if>> 差
					</td>
					</tr>
				</c:forEach>
			</table>
		</c:if>
	</td>
	<td class=" sorting_1">
		<c:if test="${fn:length(cur.colItems) > 0}">
			<table>
				<c:forEach items="${cur.colItems}" var="item">
					<tr>
						<td>${item.sequence }、${item.description }
						<br/>
						<input type="radio" name="item_${item.id}_c" value="4" disabled="disabled" <c:if test="${item.score == 4 }">checked="checked"</c:if>> 优
						<input type="radio" name="item_${item.id}_c" value="3" disabled="disabled" <c:if test="${item.score == 3 }">checked="checked"</c:if>> 良
						<input type="radio" name="item_${item.id}_c" value="2" disabled="disabled" <c:if test="${item.score == 2 }">checked="checked"</c:if>> 中
						<input type="radio" name="item_${item.id}_c" value="1" disabled="disabled" <c:if test="${item.score == 1 }">checked="checked"</c:if>> 差
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