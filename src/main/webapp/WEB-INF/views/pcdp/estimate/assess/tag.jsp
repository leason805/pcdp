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
						<div class="radio-inline">
							<label> 
								<input type="radio" name="ass_${cur.id}" value="1" <c:if test="${cur.auto }">disabled="disabled"</c:if> <c:if test="${cur.score == 1 }">checked="checked"</c:if>> &nbsp;&nbsp;&nbsp;符合
								<i class="fa fa-circle-o small"></i>
							</label>
						</div>
						<div class="radio-inline">
							<label>
								<input type="radio" name="ass_${cur.id}" value="0" <c:if test="${cur.auto }">disabled="disabled"</c:if> <c:if test="${cur.score == 0 }">checked="checked"</c:if>> &nbsp;&nbsp;&nbsp;不符合
								<i class="fa fa-circle-o small"></i>
							</label>
						</div>
						<c:if test="${cur.auto }"><input type="hidden" name="ass_${cur.id}" value="${cur.score}"/></c:if>
					</div>
	  			</c:when>
	  			<c:otherwise>
	  				<div class="col-sm-12">
						<div class="radio-inline">
							<label>
								<input type="radio" name="ass_${cur.id}" value="1" <c:if test="${cur.auto }">disabled="disabled"</c:if> <c:if test="${cur.score == 1 }">checked="checked"</c:if>> &nbsp;&nbsp;&nbsp;1分
								<i class="fa fa-circle-o small"></i>
							</label>
						</div>
						<div class="radio-inline">
							<label>
								<input type="radio" name="ass_${cur.id}" value="2" <c:if test="${cur.auto }">disabled="disabled"</c:if> <c:if test="${cur.score == 2 }">checked="checked"</c:if>> &nbsp;&nbsp;&nbsp;2分
								<i class="fa fa-circle-o small"></i>
							</label>
						</div>
						<div class="radio-inline">
							<label>
								<input type="radio" name="ass_${cur.id}" value="3" <c:if test="${cur.auto }">disabled="disabled"</c:if> <c:if test="${cur.score == 3 }">checked="checked"</c:if>> &nbsp;&nbsp;&nbsp;3分
								<i class="fa fa-circle-o small"></i>
							</label>
						</div>
						<div class="radio-inline">
							<label>
								<input type="radio" name="ass_${cur.id}" value="4" <c:if test="${cur.auto }">disabled="disabled"</c:if> <c:if test="${cur.score == 4 }">checked="checked"</c:if>> &nbsp;&nbsp;&nbsp;4分
								<i class="fa fa-circle-o small"></i>
							</label>
						</div>
						<c:if test="${cur.auto }"><input type="hidden" name="ass_${cur.id}" value="${cur.score}"/></c:if>
					</div>
	  			</c:otherwise>
	  		</c:choose>
		</c:if>
	</td>
	<td class="" width="25%">
		${cur.summary }
	</td>
	<td class="">
		${cur.description }
	</td>
</tr> 
  <c:if test="${fn:length(cur.children) > 0}"><!-- 如果有childen --> 
    <c:set var="list" value="${cur.children}" scope="request" /><!-- 注意此处，子列表覆盖treeList，在request作用域 -->  
    <c:import url="tag.jsp" /><!-- 这就是递归了 -->  
  </c:if> 
</c:forEach>