<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common.jsp"%>
<%@ page isELIgnored="false" %> 
<%@ page contentType="text/html; charset=UTF-8" %>

<div class="row">
	<div class="col-xs-12 col-sm-12">
		<div class="box">
			<div class="box-content">
				<form class="form-horizontal" model="form" id="mForm" method="post" action="${ctx}/system/knowledge/question/update/${model.id}.htm">
					<input name="options" type="hidden" id="options"/>
					<input name="sectionId" id="sectionId" type="hidden" id="${model.section.id }"/>
					
					<table width="98%" border="1" style="color: #666; background-color: #ffffff;">
						<tr height="40px;">
							<td width="20%" align="center">题目类型</td>
							<td>
								<div class="col-sm-4">
										<div class="radio-inline">
										<label>
											<input type="radio" name="questionType" id="questionType" value="SINGLE" <c:if test="${model.questionType == 'SINGLE'}">checked</c:if>>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;单选 
											<i class="fa fa-circle-o"></i>
										</label>
										</div>
										<div class="radio-inline">
											<label>
												<input type="radio" name="questionType" value="MULTIPLE" <c:if test="${model.questionType == 'MULTIPLE'}">checked</c:if>>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 多选
												<i class="fa fa-circle-o"></i>
											</label>
										</div>
									</div>
							</td>
						</tr>
						<tr>
							<td width="20%" align="center">题目内容</td>
							<td><textarea class="form-control" rows="2" style="height:70px;width:640px" name="question" id="question">${model.question }</textarea></td>
						</tr>
						<tr style="margin: 5px;">
							<td align="center">答案选项</td>
							<td>
								<table id="dg" style="margin-left:50px"></table>
							</td>
						</tr>
						<!--tr>
							<td width="20%" align="center">答案解析</td>
							<td><textarea class="form-control" rows="2" name="question">${model.question }</textarea></td>
						</tr-->
						<tr>
							<td colspan="2" align="center" style="padding-top:20px;">
								<div class="col-sm-offset-6 col-sm-4">
									<button type="button" class="btn btn-primary btn-label-left" onclick="saveForm();">
									<span><i class="fa fa-clock-o"></i></span>
										确定
									</button>
								</div>
								<div class="col-sm-2">
									<button type="button" class="btn btn-default btn-label-left" onclick="closeTr();">
									<span><i class="fa fa-clock-o txt-danger"></i></span>
										取消
									</button>
								</div>
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</div>
</div>

<script>
	var answerJSON = [
		{  
			"text" : "是",  
                    //"id" : 1
			 "id" : "YES"
			}, {  
			"text" : "否",  
                	    //"id" : 0
			"id" : "NO"
	}];
                
	var $dg = $("#dg");
	$dg.datagrid({
		url : "<c:if test="${not empty model }">${ctx}/system/knowledge/question/listoptions/${model.id}.htm</c:if>",
		width : 800,
		height : 210,
		singleSelect:true,
		columns : [ [ {
			field : 'answerType',
			title : '正确答案',
			width : 80,
			formatter : unitFormatter,
			editor : {type:'combobox',options:{valueField:'id',textField:'text', editable:false,
			data: answerJSON,
			required:true}
			}
		}, {
			field : 'code',
			title : '编号',
			width : 80,
			editor : {type:'combobox',options:{valueField:'id',textField:'text', editable:false,
				data:  
					[  
					{'id':'A','text':'A'},  
					{'id':'B','text':'B'}, 
					{'id':'C','text':'C'},  
					{'id':'D','text':'D'}, 
					{'id':'E','text':'E'},  
					{'id':'F','text':'F'},
					{'id':'G','text':'G'} 
					],
				required:true}
				}
		}, {
			field : 'content',
			title : '内容',
			width : 320,
			editor : "validatebox"
		} , {
			field : 'answerDesc',
			title : '答题详解',
			width : 310,
			editor : "validatebox"
		}
		] ],
		toolbar : [ {
			text : "添加",
			iconCls : "icon-add",
			handler : function() {
				$dg.datagrid('appendRow', {});
				var rows = $dg.datagrid('getRows');
				$dg.datagrid('beginEdit', rows.length - 1);
			}
		}, {
			text : "编辑",
			iconCls : "icon-edit",
			handler : function() {
				var row = $dg.datagrid('getSelected');
				if (row) {
					var rowIndex = $dg.datagrid('getRowIndex', row);
					$dg.datagrid('beginEdit', rowIndex);
				}
			}
		}, {
			text : "删除",
			iconCls : "icon-remove",
			handler : function() {
				var row = $dg.datagrid('getSelected');
				if (row) {
					var rowIndex = $dg.datagrid('getRowIndex', row);
					$dg.datagrid('deleteRow', rowIndex);
				}
			}
		}, {
			text : "结束编辑",
			iconCls : "icon-cancel",
			handler :endEdit
		} ]
	});
	
	function unitFormatter(value, rowData, rowIndex) {  
	    for (var i = 0; i < answerJSON.length; i++) {  
	        if (answerJSON[i].id == value) {  
	            return answerJSON[i].text;  
	        }  
	    }  
	    return value;  
	}    
	
	function endEdit(){
		var rows = $dg.datagrid('getRows');
		for ( var i = 0; i < rows.length; i++) {
			$dg.datagrid('endEdit', i);
		}
	}
	
	function saveForm(){
		var $dg = $("#dg");
		var rows = $dg.datagrid('getRows');
		for ( var i = 0; i < rows.length; i++) {
			$dg.datagrid('endEdit', i);
		}
		var type = jQuery('input[type="radio"][name="questionType"]:checked').val();
		if(!type){
			$.messager.alert('提示','请正确选择题目类型！','warning');
			return;
		}
		if (rows.length) {
			var answerSize = 0;
			var answerArray = [["A",0],["B",0],["C",0],["D",0],["E",0],["F",0],["G",0]];
			
			for ( var i = 0; i < rows.length; i++) {
				//answerSize += (rows[i].answer*1);
				if(rows[i].answerType == 'YES'){
					answerSize += 1;
				}
				for(var j=0; j<answerArray.length; j++){
					if(answerArray[j][0] == rows[i].code){
						answerArray[j][1] += 1;
					}
				}
			}
			if(type == 'SINGLE'){
				if(answerSize != 1){
					$.messager.alert('提示','单选题应只有一个正确答案！','warning');
					return;
				}
			}
			else{
				if(answerSize < 2){
					$.messager.alert('提示','多选题应有多于一个正确答案！','warning');
					return;
				}
			}

			var lastAnswer = 0;
			for(var j=answerArray.length-1; j>=0; j--){
				if(answerArray[j][1] > 1){
					$.messager.alert('提示','答题选项出现重复编号！','warning');
					return;
				}
				if(lastAnswer ==0 ){
					if(answerArray[j][1] > 0){
						lastAnswer = j;
					}
				}
				if(lastAnswer > j){
					if(answerArray[j][1] < 1){
						$.messager.alert('提示','答题选项编号顺序不正确！','warning');
						return;
					}
				}
			}

			try{
				var options = JSON.stringify(rows);
				///alert(options);
			}
			catch(err){
				alert(err.description);
			}
			$("#options").val(options);
			//jQuery("#mForm").submit();
			
			var url = '${ctx}/system/knowledge/question/update/${model.id}.htm';
			var data = {
					sectionId: '${model.section.id}',
					questionType:$("#questionType").val(),
					question:$("#question").val(),
					options:$("#options").val()
		    };
			//alert(data.sectionId);
			//alert(data.questionType);
			//alert(data.question);
			//alert(data.options);
			$.ajax({
				mimeType: 'text/html; charset=utf-8', // ! Need set mimeType only when run from local file
				url: url,
				data:data,
				type: 'POST',
				success: function(data) {
					var result = eval("("+data+")"); 
					if(result.status == 'success'){
						$.messager.alert('提示','操作成功！','warning');
						//closeTr();
						oTable.fnReloadAjax();
					}
				},
				error: function (jqXHR, textStatus, errorThrown) {
					alert(errorThrown);
				},
				dataType: "html",
				async: false
			});
		}
		else{
			$.messager.alert('提示','请正确添加答题选项！','warning');
			return;
		}
	}
</script>