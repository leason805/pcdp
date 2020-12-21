<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page isELIgnored="false" %> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/common.jsp"%>
<!DOCTYPE html>
<html >
	<head>
		<meta charset="utf-8">
		<title>DevOOPS</title>
		<meta name="description" content="description">
		<meta name="author" content="DevOOPS">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link href="${ctx}/plugins/bootstrap/bootstrap.css" rel="stylesheet">
		<link href="${ctx}/plugins/jquery-ui/jquery-ui.min.css" rel="stylesheet">
		<link href="${ctx}/css/font-awesome.css" rel="stylesheet">
		<link href='${ctx}/css/G_Font_Righteous.css' rel='stylesheet' type='text/css'>
		<link href="${ctx}/plugins/fancybox/jquery.fancybox.css" rel="stylesheet">
		<link href="${ctx}/plugins/fullcalendar/fullcalendar.css" rel="stylesheet">
		<link href="${ctx}/plugins/xcharts/xcharts.min.css" rel="stylesheet">
		<link href="${ctx}/plugins/select2/select2.css" rel="stylesheet">

		<link href="${ctx}/css/style.css" rel="stylesheet">
		
		<link href="${ctx}/plugins/fancytree/skin-lion/ui.fancytree.css" rel="stylesheet">
		
		<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
		<!--[if lt IE 9]>
				<script src="http://getbootstrap.com/docs-assets/js/html5shiv.js"></script>
				<script src="http://getbootstrap.com/docs-assets/js/respond.min.js"></script>
		<![endif]-->
	
<style type="text/css">
body {

	color: white;
	font-family: Helvetica, Arial, sans-serif;
	font-size: smaller;
	background-image: url("nav_bg.png");
	background-repeat: repeat-x;
}
div#tree {
	position: absolute;
	height: 95%;
	width: 95%;
	padding: 5px;
	margin-right: 16px;
}
ul.fancytree-container {
	height: 100%;
	width: 100%;
	background-color: transparent;
}
span.fancytree-node span.fancytree-title {
	color: white;
	text-decoration: none;
}
span.fancytree-focused span.fancytree-title {
	outline-color: white;
}
span.fancytree-node:hover span.fancytree-title,
span.fancytree-active span.fancytree-title,
span.fancytree-active.fancytree-focused span.fancytree-title,
.fancytree-treefocus span.fancytree-title:hover,
.fancytree-treefocus span.fancytree-active span.fancytree-title {
	color: #39414A;
}
span.external span.fancytree-title:after {
	content: "";
	background: url("data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAoAAAAKCAMAAAC67D+PAAAAFVBMVEVmmcwzmcyZzP8AZswAZv////////9E6giVAAAAB3RSTlP///////8AGksDRgAAADhJREFUGFcly0ESAEAEA0Ei6/9P3sEcVB8kmrwFyni0bOeyyDpy9JTLEaOhQq7Ongf5FeMhHS/4AVnsAZubxDVmAAAAAElFTkSuQmCC") 100% 50% no-repeat;
	padding-right: 13px;
}
/* Remove system outline for focused container */
.ui-fancytree.fancytree-container:focus {
	outline: none;
}
.ui-fancytree.fancytree-container {
	border: none;
}
</style>

<script src="${ctx}/plugins/jquery/jquery-2.1.0.min.js"></script>
<script src="${ctx}/plugins/jquery/jquery-migrate-1.2.1.min.js"></script>

<script src="${ctx}/plugins/jquery-ui/jquery-ui.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="${ctx}/plugins/bootstrap/bootstrap.min.js"></script>
<script src="${ctx}/plugins/justified-gallery/jquery.justifiedgallery.min.js"></script>
<script src="${ctx}/plugins/tinymce/tinymce.min.js"></script>
<script src="${ctx}/plugins/tinymce/jquery.tinymce.min.js"></script>
<script src="${ctx}/plugins/select2/select2.min.js"></script>

<script src="${ctx}/plugins/jquery-easyui/json2.js"></script>
<script src="${ctx}/plugins/jquery-easyui/jquery.easyui.min.js"></script>
<script src="${ctx}/plugins/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>

<script src="${ctx}/plugins/fancytree/jquery.fancytree.js"></script>

<!-- All functions for this theme + document.ready processing -->
<script src="${ctx}/js/bx.js"></script>

<script type="text/javascript">


$(document).ready(function() {
	// --- Initialize sample trees
	$("#tree").fancytree({
		autoActivate: false, // we use scheduleAction()
		autoCollapse: true,
//			autoFocus: true,
		autoScroll: true,
		clickFolderMode: 3, // expand with single click
		minExpandLevel: 2,
		tabindex: "-1", // we don't want the focus frame
		// scrollParent: null, // use $container
		focus: function(event, data) {
			var node = data.node;
			// Auto-activate focused node after 1 second
			if(node.data.href){
				node.scheduleAction("activate", 1000);
			}
		},
		blur: function(event, data) {
			data.node.scheduleAction("cancel");
		},
		activate: function(event, data){
			var node = data.node,
				orgEvent = data.originalEvent || {};

			if(node.data.href){
				window.open(node.data.href, (orgEvent.ctrlKey || orgEvent.metaKey) ? "_blank" : node.data.target);
			}
			if( window.parent &&  parent.history && parent.history.pushState ) {
				// Add #HREF to URL without actually loading content
				parent.history.pushState({title: node.title}, "", "#" + (node.data.href || ""));
			}
		},
		click: function(event, data){ // allow re-loads
			var node = data.node,
				orgEvent = data.originalEvent;

			if(node.isActive() && node.data.href){
				// data.tree.reactivate();
				window.open(node.data.href, (orgEvent.ctrlKey || orgEvent.metaKey) ? "_blank" : node.data.target);
			}
		}
	});
	// On page load, activate node if node.data.href matches the url#href
	var tree = $(":ui-fancytree").fancytree("getTree"),
		frameHash = window.parent && window.parent.location.hash;

	if( frameHash ) {
		frameHash = frameHash.replace("#", "");
		tree.visit(function(n) {
			if( n.data.href && n.data.href === frameHash ) {
				n.setActive();
				return false; // done: break traversal
			}
		});
	}
});

function saveForm(){

   
}
</script>

</head>

	<body>
			<div id="tree">
		<ul>
			<c:forEach var="cur" items="${list}" varStatus="vs"> 
				<li class="folder expanded"> ${cur.name }
					<ul>
						<c:set var="list" value="${cur.children}" scope="request" /><!-- 注意此处，子列表覆盖treeList，在request作用域 -->  
						<c:import url="tag_tree.jsp" />
					</ul>
				</li>
			</c:forEach>
	
		</ul>
	</div>
	</body>


</html>
