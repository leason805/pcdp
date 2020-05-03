<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="com.boxun.estms.util.*"%>
<!DOCTYPE html>

<html>
    <head>
	<%@ include file="/WEB-INF/common.jsp"%>
        <title>Master</title>
        <meta charset="UTF-8">
		<meta name="viewport" content="width=device-width initial-scale=1.0 maximum-scale=1.0 user-scalable=yes" />
		<link type="text/css" rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" />
		<!-- Bootstrap -->
    	<link rel="stylesheet" href="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.css">
        <style type="text/css">
			#menu {
				background: #eee;
			}
			.mm-panel {
				padding: 0 !important;
			}
			.mm-panel:before,
			.mm-panel:after {
				content: none !important;
				display: none !important;
			}
			.mm-listview {
				height: 100%;
				padding: 0 !important;
				margin: 0 !important;
			}
			.mm-listview li {
				height: 20%;
				overflow: hidden;
			}
			.mm-listview > li > a {
				font-weight: bold;
				line-height: 100px;
				padding: 0 0 0 30px;
				margin-top: -50px;
				position: absolute;
				left: 0;
				right: 0;
				top: 50%;
				transition: padding 0.5s ease;
			}
			.mm-listview > li > a:hover {
				padding-left: 40px;
			}
			.mm-listview li:nth-child( 1 ) { background: #ffd; }
			.mm-listview li:nth-child( 2 ) { background: #dfd; }
			.mm-listview li:nth-child( 3 ) { background: #dff; }
			.mm-listview li:nth-child( 4 ) { background: #ddf; }
			.mm-listview li:nth-child( 5 ) { background: #fdf; }
		</style>
        <script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
        <!-- Include all compiled plugins (below), or include individual files as needed -->
    	<script src="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>
        <script type="text/javascript">
			$(function() {
				$("#menu")
					.mmenu({
						extensions 	: [ "theme-white", "border-full", "effect-slide-listitems" ],
						navbar 		: false
					});
			});

		</script>
        
		<%
        			String topiclink = request.getParameter("topiclink");
        		        		        				  String topicsequence = request.getParameter("topicsequence");
        		        		        				  String topicloopsize = request.getParameter("topicloopsize");
        		        		        				  String jobtoggle = request.getParameter("jobtoggle");
        		        		        				  String filetoggle = request.getParameter("filetoggle");
        		        		        				  String mastername = request.getParameter("mastername");
        		        		        				  String htmlfile = request.getParameter("htmlfile");
        		        		        				  String uid = request.getParameter("uid");
        		        		        				  if(StringUtil.isNotBlank(topiclink)){
        		        		        			  com.boxun.pcdp.cache.CacheManager.getInstance().set(com.boxun.estms.util.Const.TOPIC_LINK, topiclink);
        		        		        				  }
        		        		        				  if(StringUtil.isNotBlank(topicsequence)){
        		        		        			  com.boxun.pcdp.cache.CacheManager.getInstance().set(com.boxun.estms.util.Const.TOPIC_SEQUENCE, topicsequence);
        		        		        				  }
        		        		        				  if(StringUtil.isNotBlank(topicloopsize)){
        		        		        			  com.boxun.pcdp.cache.CacheManager.getInstance().set(com.boxun.estms.util.Const.TOPIC_LOOP_SIZE, topicloopsize);
        		        		        				  }
        		        		        				  if(StringUtil.isNotBlank(jobtoggle)){
        		        		        			  com.boxun.pcdp.cache.CacheManager.getInstance().set(com.boxun.estms.util.Const.JOB_TOGGLE, jobtoggle);
        		        		        				  }
        		        		        				  if(StringUtil.isNotBlank(filetoggle)){
        		        		        			  com.boxun.pcdp.cache.CacheManager.getInstance().set(com.boxun.estms.util.Const.FILE_GENERATOR, filetoggle);
        		        		        				  }
        		        		        				  if(StringUtil.isNotBlank(mastername)){
        		        		        			  com.boxun.pcdp.cache.CacheManager.getInstance().set(com.boxun.estms.util.Const.MASTER_NAMES, mastername);
        		        		        				  }
        		        		        				  if(StringUtil.isNotBlank(htmlfile)){
        		        		        			  com.boxun.pcdp.cache.CacheManager.getInstance().set(com.boxun.estms.util.Const.HTML_FILES, htmlfile);
        		        		        				  }
        		        		        				  if(StringUtil.isNotBlank(uid)){
        		        		        			  com.boxun.pcdp.cache.CacheManager.getInstance().set(com.boxun.estms.util.Const.U_TOKEN, uid);
        		        		        				  }
        		%>
    </head>
    <body>
		<div id="page">
			<div class="header">
				<a href="#menu"></a>
				Setting
			</div>
			<div style="margin: 15px 15px 15px 15px">
				<form action="<%=request.getContextPath()%>/cache.jsp" method="post" data-ajax="false">
					
					<div data-demo-html="true">
		                <label for="topiclink">Link:</label>
		                <input type="text" name="topiclink" id="topiclink" placeholder="Text input" value="">
		            </div>
					<div data-demo-html="true">
		                <label for="topicsequence">Sequence:</label>
		                <input type="text" name="topicsequence" id="topicsequence" placeholder="Text input" value="">
		            </div>
            
					<div data-demo-html="true">
		                <label for="topicloopsize">Loop Size:</label>
		                <input type="range" name="topicloopsize" id="topicloopsize" value="" min="0" max="100" data-highlight="true">
		            </div>
					<div data-demo-html="true">
		                <label for="jobtoggle">Subscribe Job Toggle:</label>
		                <select name="jobtoggle" id="jobtoggle" data-role="slider">
		                    <option value="OFF" selected="selected">Off</option>
		                    <option value="ON" >On</option>
		                </select>
		            </div>
					<div data-demo-html="true">
		                <label for="jobtoggle">File Generate Toggle:</label>
		                <select name="filetoggle" id="filetoggle" data-role="slider">
		                    <option value="OFF" selected="selected">Off</option>
		                    <option value="ON">On</option>
		                </select>
		            </div>
					<div data-demo-html="true">
		                <label for="topiclink">Master Name:</label>
		                <input type="text" name="mastername" id="mastername" placeholder="Text input" value="">
		            </div>
					<div data-demo-html="true">
		                <label for="topiclink">HTML File:</label>
		                <input type="text" name="htmlfile" id="htmlfile" placeholder="Text input" value="">
		            </div>
					<div data-demo-html="true">
		                <label for="topiclink">U-TOKENs:</label>
		                <input type="text" name="uid" id="htmlfile" placeholder="Text input" value="">
		            </div>
    	            <button class="ui-shadow ui-btn ui-corner-all" type="submit" id="submit-2">确&nbsp;&nbsp;定</button>
				</form>
			</div>
		</div>
	</body>
</html>
