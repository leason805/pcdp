<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/common.jsp"%>

<c:forEach var="cur" items="${list}" varStatus="vs">

	<c:if test="${fn:length(cur.children) > 0}"><!-- 如果有childen --> 
			<li class="folder">${cur.name }
			<ul>
				<c:set var="list" value="${cur.children}" scope="request" /><!-- 注意此处，子列表覆盖treeList，在request作用域 -->  
				<c:import url="tag_tree.jsp" />
			</ul>
	</c:if>
	<c:if test="${fn:length(cur.children) <= 0}"><!-- 如果有childen --> 
		<li><a target="content" href="${ctx}/system/capacity/item/content/${cur.id }.htm">${cur.name }</a>
	</c:if>
</c:forEach>