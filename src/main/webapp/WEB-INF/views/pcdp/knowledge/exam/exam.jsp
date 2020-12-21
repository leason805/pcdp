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
		<title>考试</title>
		<meta name="description" content="description">
		<meta name="author" content="DevOOPS">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link href="${ctx}/plugins/bootstrap/bootstrap.css" rel="stylesheet">
		<link href="${ctx}/plugins/jquery-ui/jquery-ui.min.css" rel="stylesheet">
		<link href="${ctx}/css/font-awesome.css" rel="stylesheet">
		<link href="http://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css" rel="stylesheet">
		<link href='http://fonts.googleapis.com/css?family=Righteous' rel='stylesheet' type='text/css'>
		<link href='${ctx}/css/G_Font_Righteous.css' rel='stylesheet' type='text/css'>
		<link href="${ctx}/plugins/fancybox/jquery.fancybox.css" rel="stylesheet">
		<link href="${ctx}/plugins/fullcalendar/fullcalendar.css" rel="stylesheet">
		<link href="${ctx}/plugins/xcharts/xcharts.min.css" rel="stylesheet">
		<link href="${ctx}/plugins/select2/select2.css" rel="stylesheet">
		
		<link href="${ctx}/plugins/jquery-easyui/themes/default/easyui.css"  rel="stylesheet" />
		<link href="${ctx}/plugins/jquery-easyui/themes/icon.css"  rel="stylesheet" />

		<link href="${ctx}/plugins/jquery-ui-timepicker-addon/jquery-ui-timepicker-addon.min.css" rel="stylesheet">
		
		<link href="${ctx}/css/style.css" rel="stylesheet">
		<link href="${ctx}/css/exam.css" rel="stylesheet">
		<link href="${ctx}/css/examstyle.css" rel="stylesheet">
		<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
		<!--[if lt IE 9]>
				<script src="http://getbootstrap.com/docs-assets/js/html5shiv.js"></script>
				<script src="http://getbootstrap.com/docs-assets/js/respond.min.js"></script>
		<![endif]-->
		
		<style type="text/css">
			.rnlt1 li {
			    width: 29%;
			}
		</style>
		
	</head>
<body oncontextmenu="self.event.returnValue=false">

<div class="row">
	<div class="col-xs-12">
		<div class="box">
			<div class="box-header">
				<div class="box-name">
					<i class="fa fa-linux"></i>
					${paper.name}	
				</div>
				<div class="box-icons">
					
				</div>
				<div class="no-move"></div>
			</div>
			
		<form class="form-horizontal" role="form" method="post" id="mForm" action="${ctx}/system/knowledge/exam/submit/${model.id }.htm">
			<input type="hidden" name="qids"  value="<c:forEach var="cur" items="${questions}">${cur.id},</c:forEach> "/>
			 <input type="hidden" name="paperId"  value="${paper.id}"/>
			 <input name="answers" type="hidden" id="answers" value="" />
			
			<div class="box-content table-responsive" style="padding:30px">
				<table>
					<tr>
						<td width="80%">
							<table aria-describedby="datatable-m_info" class="table table-bordered  table-hover table-heading table-datatable dataTable" id="datatable-m">
								<thead>
									
									<tr role="row">
										<th aria-sort="" rowspan="1" aria-controls="datatable-m" tabindex="0" role="columnheader" class="">单/多选题&nbsp;<span></span></th>
									</tr>
								</thead>
								<tbody aria-relevant="all" aria-live="polite" role="alert">
									<c:forEach var="question" items="${questions}" varStatus="vs">   
										<tr class="odd" bgcolor="#C0C0C0">
											<td class=" sorting_1"><div id="d${vs.index+1}"></div><span class="span_">${vs.index+1}. ${question.question }</span> </td>
											
										</tr> 
										<tr class="odd">
											<td class=" sorting_1">
												<c:forEach items="${question.options}" var="option">
													<c:choose>
														<c:when test="${question.questionType == 'SINGLE'}">
															<div class="radio" style="padding-left:10px;">
																<label>
																	<input type="radio" value="${question.id}_${option.id}" name="q_${question.id}" onclick="setselect(this);">&nbsp;&nbsp;&nbsp;&nbsp;${option.code}.&nbsp;${option.content }
																	<i class="fa fa-circle-o small"></i>
																</label>
															</div>
														</c:when>
														<c:otherwise>
															<div class="checkbox" style="padding-left:10px;">
																<label>
																	<input type="checkbox" value="${question.id}_${option.id}" name="q_${question.id}" onclick="setselect(this);">&nbsp;&nbsp;&nbsp;&nbsp;${option.code}.&nbsp;${option.content }
																	<i class="fa fa-square-o small"></i>
																</label>
															</div>
														</c:otherwise>
													</c:choose>
												</c:forEach>
												<div style="padding-top:15px;"><input type="button" value="待确定" onclick="tbc($(this), '${question.id}');"/></div>
											</td>
										</tr> 
									</c:forEach> 
								</tbody>
							</table>
						</td>
						<td>
							<div style="overflow:auto;OVERFLOW-Y: auto;margin:20px 0px 80px 0px;height:1000px;" class="rnav" id="ansdiv">
							    <!--div class="rnavhd">答题卡</div-->
							    <div class="rnavct" style="overflow:auto;OVERFLOW-Y: auto">
							      <ul class="rnlt1 fc">
							        <li><span class="bg1"></span>已答题</li>
							        <li><span class="bg2"></span>待确定</li>
							        <li><span class="bg3"></span>未答题</li>
							      </ul>
							      <ul class="rnlt2 fc" tabindex="5000" style="outline: medium none;">
							      	<c:forEach var="question" items="${questions}" varStatus="vs">   
										
										<li><a href="#d${vs.index+1}" id="fc_${question.id}" class="bg3">${vs.index+1}</a></li>
									</c:forEach>
							      </ul>
							    </div>
							    <div class="rnavft"></div>
							</div>
						</td>
					</tr>
				</table>
			
				
			</div>
			<div class="form-group" id="haha">
						<div class="col-sm-offset-4 col-sm-2">
							
						</div>
						<div class="col-sm-2">
							
						</div>
					</div>
					
					<div  id="times"  class="footbar">
						<div  style="margin-left:30px;vertical-align:middle; width:100%;height:120px;"  id="zyjs">
							<table  border="0" width="98%">
								<tbody>
									<tr>
										<td width="60%" align="center">
											<button type="button" class="btn btn-primary btn-label-left btn-lg" style="height: 40px; width:100px;" onclick="submitAnswer();">
											<span><i class="fa fa-clock-o"></i></span>
												提交
											</button>
										</td>
										<td  align="right"  id="Time1">
											<div class="example exampleA" style="float:right">
												<div class="timer"></div>
											</div>
										</td>
									</tr>
								</tbody>
							</table>
						</div>
						<div  class="clear0"></div>
					</div>
		</form>
		</div>
	</div>
</div>


<script src="${ctx}/plugins/jquery/jquery-2.1.0.min.js"></script>
<script src="${ctx}/plugins/jquery/jquery-migrate-1.2.1.min.js"></script>

<script src="${ctx}/plugins/jquery-ui/jquery-ui.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="${ctx}/plugins/bootstrap/bootstrap.min.js"></script>
<script src="${ctx}/plugins/justified-gallery/jquery.justifiedgallery.min.js"></script>
<script src="${ctx}/plugins/tinymce/tinymce.min.js"></script>
<script src="${ctx}/plugins/tinymce/jquery.tinymce.min.js"></script>
<script src="${ctx}/plugins/select2/select2.min.js"></script>

<script src="${ctx}/plugins/jquery-easyui/jquery.easyui.min.js" type="text/javascript" ></script>
<script src="${ctx}/plugins/jquery-easyui/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>

<script src="${ctx}/plugins/jquery-ui-timepicker-addon/jquery-ui-timepicker-addon.min.js"></script>
<script src="${ctx}/plugins/spriteTimer/jquery.spriteTimer-1.3.6.js"></script>

<!-- All functions for this theme + document.ready processing -->
<script src="${ctx}/plugins/posfixed/posfixed.js"></script>
<script src="${ctx}/plugins/jquery.nicescroll/jquery.nicescroll.min.js"></script>

<script src="${ctx}/js/bx.js"></script>

<script type="text/javascript">

function runWhenFinished() {
	setAnswer();
	$.messager.alert('时间已到','时间已到，系统将自动提交答案！');
	setTimeout("autoSubmit()",3000); 
}

var submitted = false;
function autoSubmit(){
	jQuery("#mForm").submit();
}

function submitAnswer(){
	$.messager.confirm('交卷', '确定提交答案？', function(r){
		if (r){
			submitted = true;
			var all = true;
			$('input:radio').each(function(){
				if(!$(this).is(':checked')){
					//all = false;
					//alert("radio miss for: " + $(this).attr('name'));
				}
			});
			
			$('input:checkbox').each(function(){
				if(!$(this).is(':checked')){
					//all = false;
					//alert("checkbox miss for: " + $(this).attr('name'));
				}
			});
			
			if(all){
				setAnswer();
				jQuery("#mForm").submit();
			}
			else{
				$.messager.alert('提交','尚有答题未完成,请确保全部问题都完整填写！');
			}
			
		}
	});
}

function setAnswer(){
	var array= new Array();
	$('input:radio:checked').each(function(){
		array.push($(this).val());
	});
	$('input:checkbox:checked').each(function(){
		array.push($(this).val());
	});
	$('#answers').val(array.join(','));
}

function runWhenFinished() {
	setAnswer();
	$.messager.alert('时间已到','时间已到，系统将自动提交答案！');
	setTimeout("autoSubmit()",3000); 
}

var submitted = false;
function autoSubmit(){
	jQuery("#mForm").submit();
}

$(document).ready(function() {
	
	$('.exampleA .timer').spriteTimer({
		   'seconds': ${availableTimeMinutes}*60,
		   'isCountDown': true,

		   'digitImagePath': '${ctx}/img/numbers.png',
		   'callback': runWhenFinished
	});
	
	$("#times").scrollFix("bottom", "top");
	//$("#ansdiv").scrollFix("bottom", "top");

	
     window.niceObj = $("#ansdiv").niceScroll({ cursorcolor: "#6E737B", cursoropacitymin: 1, cursorwidth: "6px", cursorborder: "none", cursorborderradius: "4px" });

     $(window).scroll(function() { 
    		var top = $(window).scrollTop()- 20; 
    		//var left= $(window).scrollLeft()+500; 
    		$("#ansdiv").css({ top: top + "px" }); 
     });
     
     $(document).bind("keydown", function(e) {//文档绑定键盘按下事件
    	    e = window.event || e;//解决浏览器兼容的问题   
    	    if(e.keyCode == 116) {//F5按下
    	    　　e.keyCode = 0;
    	    　　return false;
    	    }else{
    	    　　//让其刷新
    	    }
    	});
	
});

//window.onbeforeunload = closeWin;

function closeWin(){
	$.messager.confirm('Confirm','Are you sure you want to delete record?',function(r){
		if (r){   
			alert('ok');   
		}   
	});
}

function setselect(obj){
	var val = $(obj).val();
	//alert(val);
	var id = $(obj).val().split("_")[0];
	//alert(id)
	$("#fc_"+id).attr("class", "bg1");
}

function tbc(thi, id){
	var val = $(thi).val();
	if(val == "待确定"){
		$(thi).val("取消待确定");
		$("#fc_"+id).attr("class", "bg2");
	}
	if(val == "取消待确定"){
		$(thi).val("待确定");
		$("#fc_"+id).attr("class", "bg1");
	}
}


var switchScreenTimes = 0; // 允许三次切屏，超过则提交
window.onblur = function () {
	if(!submitted){
	    if(switchScreenTimes >= 3) {
	    	setAnswer();
	    	$.messager.alert('自动提交','您已切屏超过限制，系统将自动提交答案！');
	    	setTimeout("autoSubmit()",1000); 
		}
	    else{
	    	switchScreenTimes++;
	    	$.messager.alert("操作提示","考试过程中，禁止切屏，超过三次将自动提交试卷，您已经切屏" + switchScreenTimes + "次，请注意！");
	    }
	}
}

//   window.onfocus = function () {
// 	  alert("亲，欢迎你回来");
//   }

</script>
</body>
</html>
