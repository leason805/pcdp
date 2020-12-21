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

		<link href="${ctx}/plugins/jquery-easyui/themes/default/easyui.css"  rel="stylesheet" />
		<link href="${ctx}/plugins/jquery-easyui/themes/icon.css"  rel="stylesheet" />
		
		<link href="${ctx}/plugins/bootstrapvalidator/bootstrapValidator.min.css" rel="stylesheet">
		
		<link href="${ctx}/css/style.css" rel="stylesheet">
		<link href="${ctx}/css/custom.css" rel="stylesheet">
		<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
		<!--[if lt IE 9]>
				<script src="http://getbootstrap.com/docs-assets/js/html5shiv.js"></script>
				<script src="http://getbootstrap.com/docs-assets/js/respond.min.js"></script>
		<![endif]-->
	</head>
<body>
<form class="form-horizontal" role="form" id="mForm" method="post" action="
					<c:choose>
       					<c:when test="${empty model}">
							${ctx}/system/knowledge/paper/create.htm
       					</c:when>
       					<c:otherwise>
              				 ${ctx}/system/knowledge/paper/update/${model.id}.htm
       					</c:otherwise>
					</c:choose>">					
	<div class="row">
		<div class="col-xs-12 col-sm-12">
			<div class="box">
				<div class="box-header">
					<div class="box-name">
						<i class="fa fa-edit"></i>
						<span>
						<c:choose>
	       					<c:when test="${empty model}">
								 添加试卷
	       					</c:when>
	       					<c:otherwise>
	              				 编辑试卷 
	       					</c:otherwise>
						</c:choose>
					</div>
					<div class="box-icons">
						<a class="collapse-link">
							<i class="fa fa-chevron-up"></i>
						</a>
	
					</div>
					<div class="no-move"></div>
				</div>
				<div class="box-content">
						<div class="form-group ">
							<label class="col-sm-2 control-label">试卷名称</label>
							<div class="col-sm-4">
								<input type="text" class="form-control" placeholder="名称" data-toggle="tooltip" data-placement="bottom" title="试卷名称" name="name" value="${model.name }">
							</div>
							<label class="col-sm-2 control-label">所属公司</label>
							<div class="col-sm-4"> 
								<select class="populate placeholder" name="companyId" id="companys" <c:if test="${not empty model }">disabled="disabled"</c:if> onchange="changeCompany();">
										<option value=""></option>
										<c:forEach items="${companys}" var="company">
											<option value="${company.id}" <c:if test="${model.company.id == company.id }">selected="selected"</c:if>>${company.name}</option>
										</c:forEach>
								</select>
							</div>
						</div>
						
						<div class="form-group ">
							<label class="col-sm-2 control-label">试题数量</label>
							<div class="col-sm-4">
								<input type="text" class="form-control" placeholder="数量" data-toggle="tooltip" data-placement="bottom" title="试题数量" name="size" id="size" value="${model.size }">
							</div>
							<label class="col-sm-2 control-label">考试时间(分钟) </label>
							<div class="col-sm-4">
								<input type="text" class="form-control" placeholder="时间" data-toggle="tooltip" data-placement="bottom" title="考试时间" name="minutes" id="minutes" value="${model.minutes }">
							</div>
						</div>
						
						<div class="form-group ">
							<label class="col-sm-2 control-label">试卷总分</label>
							<div class="col-sm-4">
								<input type="text" class="form-control" placeholder="总分" data-toggle="tooltip" data-placement="bottom" title="试卷总分" name="score" value="${model.score }">
							</div>
							<label class="col-sm-2 control-label">及格分数 </label>
							<div class="col-sm-4">
								<input type="text" class="form-control" placeholder="及格分数" data-toggle="tooltip" data-placement="bottom" title="及格分数" name="passscore" value="${model.passscore }">
							</div>
						</div>

						
						<div class="form-group">
							<label class="col-sm-2 control-label" for="form-styles">试卷描述</label>
							<div class="col-sm-10">
									<textarea class="form-control" rows="5" name="description">${model.description }</textarea>
							</div>
						</div>
					
				</div>
			</div>
		</div>
	</div>
	
	<div class="row">
		<div class="col-xs-12">
			<div class="box">
				<div class="box-header">
					<div class="box-name">
						<i class="fa fa-linux"></i>试题选择
					</div>
					<div class="box-icons">

					</div>
					<div class="no-move"></div>
				</div>
				<div class="box-content no-padding table-responsive" id="table_content">
					<table aria-describedby="datatable-m_info" class="table table-bordered table-striped table-hover table-heading table-datatable dataTable" id="datatable-m">
						<thead>
							<tr role="row">
								<th aria-label="指标名称: activate to sort column" aria-sort="" colspan="1" rowspan="1" aria-controls="datatable-m" tabindex="0" role="columnheader" class="">章节名称</th>
								<th aria-label="操作: activate to sort column" colspan="1" rowspan="1" aria-controls="datatable-m" tabindex="0" role="columnheader" class="">题库数量</th>
								<th aria-label="操作: activate to sort column" colspan="1" rowspan="1" aria-controls="datatable-m" tabindex="0" role="columnheader" class="">抽题数量</th>
							</tr>
						</thead>
						
						<tbody aria-relevant="all" aria-live="polite" role="alert">
							<c:import url="tag.jsp" />
						</tbody>
						<thead>
							<tr role="row">
								<th align="center" aria-label="指标名称: activate to sort column" aria-sort="" colspan="1" rowspan="1" aria-controls="datatable-m" tabindex="0" role="columnheader" class=""><span style="color:red">题目总数</span></th>
								<td></td>
								<th aria-label="操作: activate to sort column" colspan="1" rowspan="1" aria-controls="datatable-m" tabindex="0" role="columnheader" class=""><span style="color:red" id="totalsize">${model.size }</span></th>
							</tr>
						</thead>
					</table>
					
					
				</div>
				
			</div>
			
			<div class="form-group">
							<div class="col-sm-offset-4 col-sm-2">
								
								<!--button type="button" class="btn btn-primary btn-label-left" onclick="saveForm();"-->
								<button type="submit" class="btn btn-primary btn-label-left">
								<span><i class="fa fa-clock-o"></i></span>
									确定
								</button>
							</div>
							<div class="col-sm-2">
								<button type="button" class="btn btn-default btn-label-left" onclick="closeBoxFromWin();">
								<span><i class="fa fa-clock-o txt-danger"></i></span>
									取消
								</button>
							</div>
							
				</div>
		</div>
	</div>
</form>

<script src="${ctx}/plugins/jquery/jquery-2.1.0.min.js"></script>
<script src="${ctx}/plugins/jquery/jquery-migrate-1.2.1.min.js"></script>

<script src="${ctx}/plugins/jquery-ui/jquery-ui.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="${ctx}/plugins/bootstrap/bootstrap.min.js"></script>
<script src="${ctx}/plugins/justified-gallery/jquery.justifiedgallery.min.js"></script>
<script src="${ctx}/plugins/tinymce/tinymce.min.js"></script>
<script src="${ctx}/plugins/tinymce/jquery.tinymce.min.js"></script>
<script src="${ctx}/plugins/select2/select2.min.js"></script>

<script src="${ctx}/plugins/bootstrapvalidator/bootstrapValidator.min.js"></script>

<script src="${ctx}/plugins/jquery-easyui/jquery.easyui.min.js" type="text/javascript" ></script>
<script src="${ctx}/plugins/jquery-easyui/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>
<!-- All functions for this theme + document.ready processing -->
<script src="${ctx}/js/bx.js"></script>

<script type="text/javascript">

$(document).ready(function() {
	$('#companys').select2();
	
	<c:forEach items="${model.items}" var="item">
		$("#item_${item.section.id}").val("${item.size}");
	</c:forEach>
	
	$('#mForm').bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
        	<c:if test="${empty model}">
        		companyId: {
	                message: '选择不正确',
	                validators: {
	                    notEmpty: {
	                        message: '请选择所属公司'
	                    }
	                }
	            },
	            
        	</c:if>
        	name: {
                message: '输入不正确',
                validators: {
                    notEmpty: {
                        message: '请输入试卷名称'
                    }
                }
            },
            size: {
                message: '输入不正确',
                validators: {
                    notEmpty: {
                        message: '请填写试题数量'
                    },
                    digits: {
                    	message: '试题数量应为数字'
                    }
                    /*
                    ,
                    callback: {
                        message: '   ',
                        callback: function(value, validator) {
                        	var totalsize = 0;
                    		$("#datatable-m input[type='text'][class='form-control']").each(function(){
                    			totalsize = totalsize*1 + $(this).val()*1;
                    		});
                    		var size = $("#size").val();
                    		if(size){
                    			if((size*1) != totalsize){
                    				//$.messager.alert('提示信息','题目数量与抽取数量不一致！');
                    				alert('题目数量与抽取数量不一致！');
                    				return false;
                    			}
                    			else{
                    				return true;	
                    			}
                    		}
                    		return false;
                        }
                    }
                    */
                }
            },
            minutes: {
                message: '输入不正确',
                validators: {
                    notEmpty: {
                        message: '请填写考试时间'
                    },
                    digits: {
                    	message: '考试时间应为数字'
                    }
                }
            },
            score: {
                message: '输入不正确',
                validators: {
                    notEmpty: {
                        message: '请填写试卷总分'
                    },
                    digits: {
                    	message: '试卷总分应为数字'
                    }
                }
            },
            passscore: {
                message: '输入不正确',
                validators: {
                    notEmpty: {
                        message: '请填写及格分数'
                    },
                    numeric: {
                    	message: '及格分数应为数字'
                    }
                }
                
            }
        },
        submitHandler: function (validator, form, submitButton) {
            var totalsize = 0;
    		$("#datatable-m input[type='text'][class='form-control']").each(function(){
    			totalsize = totalsize*1 + $(this).val()*1;
    		});
    		var size = $("#size").val();
    		if(size){
    			if((size*1) != totalsize){
    				$.messager.alert('提示信息','题目数量与抽取数量不一致！');
    				return;
    			}
    			else{
    				jQuery("#mForm").submit();	
    			}
    		}
    		return;
        }
    });
});

function totalsize(){
	var totalsize = 0;
	$("#datatable-m input[type='text'][class='form-control']").each(function(){
		totalsize = totalsize*1 + $(this).val()*1;
	});
	$("#totalsize").html(totalsize*1);
}

function saveForm(){
	
	
	
	//jQuery("#mForm").submit();
}

function changeCompany(){
	var compId = $("#companys").val();
	if(compId){
		var url = "${ctx}/system/knowledge/paper/sections/"+compId+".htm";
		$.ajax({
			mimeType: 'text/html; charset=utf-8', // ! Need set mimeType only when run from local file
			url: url,
			type: 'GET',
			success: function(data) {
				$('#table_content').html(data);
			},
			error: function (jqXHR, textStatus, errorThrown) {
				alert(errorThrown);
			},
			dataType: "html",
			async: false
		});
	}
}

function changeSection(){
	var secId = $("#sections").val();
	if(secId){
		var url = "${ctx}/system/knowledge/paper/questions/"+secId+".htm";
		$.ajax({
			mimeType: 'text/html; charset=utf-8', // ! Need set mimeType only when run from local file
			url: url,
			type: 'GET',
			success: function(data) {
				$('#question_content').html(data);
			},
			error: function (jqXHR, textStatus, errorThrown) {
				alert(errorThrown);
			},
			dataType: "html",
			async: false
		});
	}
}
</script>
</body>
</html>
