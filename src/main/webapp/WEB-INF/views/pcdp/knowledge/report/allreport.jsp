<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/common.jsp"%>
<%@ page isELIgnored="false" %>
<div class="row">
	<div id="breadcrumb" class="col-md-12">
		<ol class="breadcrumb">
			<li><a href="#">考试管理</a></li>
			<li><a href="#">综合分析</a></li>
		</ol>
	</div>
</div>

<div class="row">
		<div class="col-xs-12 col-sm-12">
			<div class="box-content no-padding table-responsive">
					<table class="table table-bordered table-striped table-hover table-heading table-datatable" id="datatable-m">
						<thead>
							<tr>
								<th width="50%">
									<select class="populate placeholder" name="companyId" id="companys" onchange="changeCompany();">
										<option value="">请选择</option>
										<c:forEach items="${companys}" var="item">
											<option value="${item.id }" <c:if test="${item.id == companyId }">selected="selected"</c:if>>${item.name }</option>
										</c:forEach>
										
									</select>
								</th>
								<th>
									<c:forEach items="${companys}" var="item">
										<select class="populate placeholder sub" name="arrId" id="papers_${item.id}" style="display:none;" onchange="changeReport();">
									        <option value="">请选择</option>
									        <c:forEach items="${arranges}" var="arrange">
									        	<c:if test="${item.id == arrange.paper.company.id }">
									        		<option value="${arrange.id }" <c:if test="${arrange.id == arrId }">selected="selected"</c:if>>${arrange.paper.name }</option>
									        	</c:if>
									        </c:forEach>
									    </select>
									</c:forEach>
									
								</th>
							</tr>
						</thead>
					</table>
				</div>
				
			<div class="box">
				<div class="box-header">
					<div class="box-name">
						<i class="fa fa-search"></i>
						<span>各知识点正确率</span>
					</div>
					<div class="box-icons">
						<a class="collapse-link">
							<i class="fa fa-chevron-up"></i>
						</a>
						<a class="expand-link">
							<i class="fa fa-expand"></i>
						</a>
						<a class="close-link">
							<i class="fa fa-times"></i>
						</a>
					</div>
					<div class="no-move"></div>
				</div>
				
				<div class="box-content">
					<div id="report-chart"></div>
				</div>
				
				<div class="box-content">
					<div id="report-chart2"></div>
				</div>
			</div>
	</div>
	
</div>

<script type="text/javascript">
// Run Datables plugin and create 3 variants of settings
	
	var data = [
				<c:forEach var="cur" items="${model.items}" varStatus="status">   
				
					{name:"${cur.name}",value:${cur.rate},color:"rgba(236,173,85,0.90)"}
					<c:if test="${(status.index+1) < fn:length(model.items)}">
						,
					</c:if>
				</c:forEach>
        	
    	];
    	
	function chart(){
		var chart = iChart.create({
            render:"report-chart",
            width:1080,
            height:600,
            background_color:"#353757",
            gradient:true,
            color_factor:0.3,
            border:{
                  color:"#bdbdbd",
                  width:1
            },
            align:"center",
            offsetx:98,
            offsety:15,
            sub_option:{
                  border:{
                        color:"#3b8ad9",
                        width:4
                  },
                  label:{
                        fontweight:500,
                        fontsize:14,
                        color:"#ffffff",
                        sign:"square",
                        sign_size:12,
                        border:{
                              color:"#BCBCBC",
                              width:1
                        },
                        background_color:"#fefefe"
                  }
            },
            shadow:true,
            shadow_color:"#666666",
            shadow_blur:5,
            //showpercent:true,
            decimalsnum:2,
            column_width:"70%",
            bar_height:"70%",
            radius:"90%",
            title:{
                  text:"${model.name}",
                  color:"#c0c8e7",
                  fontsize:20,
                  textAlign:"left",
                  font:"微软雅黑",
                  height:30,
                  offsetx:30,
                  offsety:0
            },
            subtitle:{
                  text:"知识点正确率(%)",
                  color:"#e0d5bc",
                  fontsize:18,
                  textAlign:"left",
                  font:"微软雅黑",
                  height:20,
                  offsetx:30,
                  offsety:0
            },
            legend:{
                  enable:false,
                  background_color:"#fefefe",
                  color:"#333333",
                  fontsize:14,
                  border:{
                        color:"#BCBCBC",
                        width:1
                  },
                  column:1,
                  align:"right",
                  valign:"center",
                  offsetx:0,
                  offsety:0
            },
            coordinate:{
                  width:"76%",
                  height:"82%",
                  background_color:"rgba(53,55,87,0)",
                  //axis:{
                 ////       color:"#666791",
                 //       width:["20","40","",""]
                 // },
                  grid_color:"#c0c0c0",
                  label:{
                        fontweight:500,
                        color:"#d3d4f0",
                        fontsize:12
                  },
                  scale:[{
						 position:'bottom',	
						 start_scale:0,
						 end_scale:100,
						 scale_space:20,
						 listeners:{
							parseText:function(t,x,y){
								return {text:t+"%"}
							}
						}
					}]
            },
            label:{
                  fontweight:500,
                  color:"#d3d4f0",
                  fontsize:13
            },
            type:"bar2d",
            data:data
      });
      chart.draw();
	}
	
	function chart2(){
		var data2 = [
					{
						name : '正确数量',
						value:[<c:forEach var="cur" items="${model.items}" varStatus="status">${cur.correctSize}<c:if test="${(status.index+1) < fn:length(model.items)}">,</c:if></c:forEach>],
						color:'#47AAB3'
					},
		         	{
		         		name : '不正确数量',
		         		value:[<c:forEach var="cur" items="${model.items}" varStatus="status">${cur.incorrectSize}<c:if test="${(status.index+1) < fn:length(model.items)}">,</c:if></c:forEach>],
		         		color:'#ECAD55'
		         	}
		         ];
        
		var chart = new iChart.BarStacked2D({
				render : 'report-chart2',
				data: data2,
				labels:[<c:forEach var="cur" items="${model.items}" varStatus="status">'${cur.name}'<c:if test="${(status.index+1) < fn:length(model.items)}">,</c:if></c:forEach>],
				title : {
					text:"知识点正确数量",
					width:400,
					align:'left',
					background_color : '#495998',
					color:'#c0c8e7'
				},
				padding:10,
				width:1080,
		        height:600,
				bar_height:20,
				background_color : '#353757',
				shadow : true,
				shadow_blur : 2,
				shadow_color : '#aaaaaa',
				shadow_offsetx : 1,
				shadow_offsety : 0, 
				sub_option:{
					label:{color:'#ffffff',fontsize:12,fontweight:600},
					border : {
						width : 2,
						color : '#d3d4f0'
					} 
				},
				label:{color:'#d3d4f0',fontsize:12,fontweight:600},
				//showpercent:true,
				decimalsnum:0,
				legend:{
					enable:true,
					background_color : null,
					line_height:25,
					color:'#d3d4f0',
					fontsize:12,
					fontweight:600,
					border : {
						enable : false
					}
				},
				coordinate:{
					background_color : 0,
					axis : {
						color : '#c0c8e7',
						width : 0
					}, 
					scale:[{
						 position:'bottom',	
						 scale_enable : false,
						 start_scale:0,
						 scale_space:20,
						 end_scale:${largest},
						 label:{color:'#d3d4f0',fontsize:11,fontweight:600},
						 listeners:{
							parseText:function(t,x,y){
								return {text:t}
							}
						 }
					}],
					 width:"65%",
	                  height:"82%"
				}
		});

		//利用自定义组件构造左侧说明文本
		chart.plugin(new iChart.Custom({
				drawFn:function(){
					//计算位置
					var coo = chart.getCoordinate(),
						x = coo.get('originx'),
						y = coo.get('originy'),
						h = coo.height;
					//在左下侧的位置，渲染一个单位的文字
					chart.target.textAlign('start')
					.textBaseline('bottom')
					.textFont('600 11px Verdana')
					
				}
		}));
		
		chart.draw();
	}
	
$(document).ready(function() {
	// Load Datatables and run plugin on tables 
	
	///MorrisChart4();
	chart();
	chart2();
	
	$('#companys').select2();
	
	<c:forEach items="${companys}" var="item">
		$('#papers_${item.id}').select2();
	</c:forEach>
	
	var val = $("#companys").val();
	if(val){
		$('#s2id_papers_'+val).show();  //the prefix s2id_ is added by the select2 plugin
	}
});

function changeReport(){
	var compId = $("#companys").val();
	if(compId){
		var val = $("#papers_"+compId).val();
		LoadAjaxContent('${ctx}/system/knowledge/report/allreport.htm?arrId='+val+"&companyId="+compId);
	}
}

function changeCompany(){
	var val = $("#companys").val();
	if(val){
		$(".sub").hide();
		//$(".sub").show();
		$('#s2id_papers_'+val).show();  //the prefix s2id_ is added by the select2 plugin
		
		//$("div has(select[id=papers_" + val + "])").hide();
	}
	
}
</script>
