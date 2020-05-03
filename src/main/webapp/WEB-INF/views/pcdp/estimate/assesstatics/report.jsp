<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/common.jsp"%>
<%@ page isELIgnored="false" %>
<div class="row">
	<div id="breadcrumb" class="col-md-12">
		<ol class="breadcrumb">
			<li><a href="#">统计报表</a></li>
			<li><a href="#">统计报表汇总</a></li>
		</ol>
	</div>
</div>

<div class="box-content no-padding table-responsive">
				<table class="table table-bordered table-striped table-hover table-heading table-datatable" id="datatable-m">
					<thead>
						<tr>
							<th>
								<select class="populate placeholder" name="projedtId" id="projects" onchange="changeProject();">
									<c:forEach items="${projects}" var="prj">
										<option value="${prj.id }" <c:if test="${prj.id == projectId }">selected="selected"</c:if>>${prj.name }</option>
									</c:forEach>
									
								</select>
							</th>
							<th>

							</th>
						</tr>

					</thead>
					<tbody>
						
					</tbody>
				</table>
</div>


<div id="tabs">
	<ul>
		<li><a href="#tabs-1">评估分析</a></li>
		<li><a href="#tabs-2">评估得分</a></li>
		<li><a href="#tabs-3">指标得分</a></li>
		<li><a href="#tabs-4">班组分析</a></li>
	</ul>
	<div id="tabs-1">
		<div class="row">
			<div class="col-xs-12">
				<div class="box">
					<div id="pgfx_div"></div>
				</div>
			</div>
		</div>
	</div>

	<div id="tabs-2">
		<div class="row">
			<div class="col-xs-12">
				<div class="box">
					<div id="pgdf_div"></div>
				</div>
			</div>
		</div>
	</div>

	<div id="tabs-3">
		<div class="row">
			<div class="col-xs-12">
				<div class="box">
					<div id="zbdf_div"></div>
				</div>
			</div>
		</div>
	</div>

	<div id="tabs-4">
		<div class="row">
			<div class="col-xs-12">
				<div class="box">
					<div id="bzfx_div"></div>
				</div>
			</div>
		</div>
	</div>
</div>


<script type="text/javascript">
	// Run Datables plugin and create 3 variants of settings

	function changeProject(){
		var compId = $("#projects").val();
		if(compId){
			LoadAjaxContent('${ctx}/system/estimate/assesstatics/report.htm?projectId='+compId);
		}
	}
	
	function pgfx() {
		var data = [ {
			name : 'A产品',
			value : [<c:forEach var="report" items="${pgfxSet.reports}" varStatus="status">'${report.data}'<c:if test="${(status.index+1) < fn:length(pgfxSet.reports)}">,</c:if></c:forEach>],
			color : '#01acb6',
			line_width : 2
		} ];
		//创建x轴标签文本   
		var labels = [<c:forEach var="report" items="${pgfxSet.reports}" varStatus="status">'${report.label}'<c:if test="${(status.index+1) < fn:length(pgfxSet.reports)}">,</c:if></c:forEach>];

		var chart = new iChart.Area2D({
			render : 'pgfx_div',
			data : data,
			title : {
				text : '评估结果统计分析',
				color : '#eff4f8',
				background_color : '#1c4156',
				height : 40,
				border : {
					enable : true,
					width : [ 0, 0, 4, 0 ],//只显示下边框
					color : '#173a4e'
				}
			},
			padding : '5 1',//设置padding,以便title能占满x轴
			sub_option : {
				label : false,
				hollow_inside : false,//设置一个点的亮色在外环的效果
				point_size : 10
			},
			tip : {
				enable : true,
				listeners : {
					//tip:提示框对象、name:数据名称、value:数据值、text:当前文本、i:数据点的索引
					parseText : function(tip, name, value, text, i) {
						return labels[i] + "平均分：<span style='color:red'>"
								+ value + "</span>";
					}
				}
			},
			width : 1080,
			height : 500,
			background_color : '#0c222f',
			gradient : true,
			shadow : true,
			shadow_blur : 2,
			shadow_color : '#4e8bae',
			shadow_offsetx : 0,
			shadow_offsety : 0,
			gradient_mode : 'LinearGradientDownUp',//设置一个从下到上的渐变背景
			border : {
				radius : 5
			},
			coordinate : {
				width : 900,
				height : 340,
				grid_color : '#506e7d',
				background_color : null,//设置坐标系为透明背景
				scale : [ {
					position : 'left',
					label : {
						color : '#eff4f8',
						fontsize : 14,
						fontweight : 600
					},
					start_scale : 0,
					end_scale : 5,
					scale_space : 1
				}, {
					position : 'bottom',
					label : {
						color : '#ffffff',
						rotate : -45,
						fontweight : 600
					},
					labels : labels
				} ]
			}
		});

		chart.draw();
	}

	function pgdf() {
		var data = [ {
			name : 'A',
			value : [ <c:forEach var="report" items="${pgdfSet.reports}" varStatus="status">'${report.data}'<c:if test="${(status.index+1) < fn:length(pgdfSet.reports)}">,</c:if></c:forEach> ],
			color : '#01acb6',
			line_width : 2
		} ];
		//创建x轴标签文本   
		var labels = [ <c:forEach var="report" items="${pgdfSet.reports}" varStatus="status">'${report.label}'<c:if test="${(status.index+1) < fn:length(pgdfSet.reports)}">,</c:if></c:forEach>];

		var chart = new iChart.Area2D({
			render : 'pgdf_div',
			data : data,
			title : {
				text : '签派员资质评估得分',
				color : '#eff4f8',
				background_color : '#1c4156',
				height : 40,
				border : {
					enable : true,
					width : [ 0, 0, 4, 0 ],//只显示下边框
					color : '#173a4e'
				}
			},

			padding : '5 1',//设置padding,以便title能占满x轴
			sub_option : {
				label : false,
				hollow_inside : false,//设置一个点的亮色在外环的效果
				point_size : 10
			},
			tip : {
				enable : true,
				listeners : {
					//tip:提示框对象、name:数据名称、value:数据值、text:当前文本、i:数据点的索引
					parseText : function(tip, name, value, text, i) {
						return labels[i] + "得分:<span style='color:red'>"
								+ value + "</span>";
					}
				}
			},
			width : 1080,
			height : 500,
			background_color : '#0c222f',
			gradient : true,
			shadow : true,
			shadow_blur : 2,
			shadow_color : '#4e8bae',
			shadow_offsetx : 0,
			shadow_offsety : 0,
			gradient_mode : 'LinearGradientDownUp',//设置一个从下到上的渐变背景
			border : {
				radius : 5
			},
			coordinate : {
				width : 900,
				height : 340,
				grid_color : '#506e7d',
				background_color : null,//设置坐标系为透明背景
				scale : [ {
					position : 'left',
					label : {
						color : '#eff4f8',
						fontsize : 14,
						fontweight : 600
					},
					start_scale : 0,
					end_scale : 4,
					scale_space : 0.5
				}, {
					position : 'bottom',
					label : {
						color : '#ffffff',
						fontweight : 600,
						rotate : -45,
					},
					labels : labels
				} ]
			}
		});

		chart.draw();
	}

	function zbdf() {
		var data = [ 
		<c:forEach var="report" items="${zbdfSet.reports}" varStatus="status">
				{
				name : '${report.label}',
				value : ${report.data},
				color : '#${report.color}'
			}
			<c:if test="${(status.index+1) < fn:length(zbdfSet.reports)}">,</c:if>
		</c:forEach>
		];

		new iChart.Column3D({
			render : 'zbdf_div',
			data : data,
			title : '公司签派员资质能力各指标得分情况',
			width : 1080,
			height : 500,
			align : 'left',
			offsetx : 50,
			legend : {
				enable : false
			},
			sub_option : {
				label : {
					color : '#111111'
				}
			},
			coordinate : {
				width : 980,
				scale : [ {
					position : 'left',
					start_scale : 0,
					end_scale : 4,
					scale_space : 0.5,
					listeners : {
						parseText : function(t, x, y) {
							return {
								text : t
							}
						}
					}
				} ]
			}
		}).draw();
	}

	function bzfx() {
		var data = [ 
		             
				<c:forEach var="report" items="${bzfxSet.reports}" varStatus="status">
				{
					name : '${report.name}',
					value : [ <c:forEach var="repo" items="${report.datas}" varStatus="sta">${repo}<c:if test="${(sta.index+1) < fn:length(report.datas)}">,</c:if></c:forEach> ],  
					color : '#${report.color}'
				}
				<c:if test="${(status.index+1) < fn:length(bzfxSet.reports)}">,</c:if>
				</c:forEach>       
		             
		 ];
		var chart = new iChart.ColumnMulti3D({
			render : 'bzfx_div',
			data : data,
			labels : [<c:forEach var="label" items="${bzfxSet.labels}" varStatus="status">'${label}'<c:if test="${(status.index+1) < fn:length(bzfxSet.labels)}">,</c:if></c:forEach>],
			title : {
				text : '签派班组评估情况',
				color : '#3e576f'
			},
			width : 1080,
			height : 500,
			background_color : '#ffffff',
			legend : {
				enable : true,
				background_color : null,
				align : 'center',
				valign : 'bottom',
				row : 1,
				column : 'max',
				border : {
					enable : false
				}
			},
			column_width : 8,//柱形宽度
			//zScale:8,//z轴深度倍数
			xAngle : 50,
			bottom_scale : 1.1,
			label : {
				color : '#4c4f48'
			},
			sub_option : {
				label : false
			},
			tip : {
				enable : true
			},
			text_space : 16,//坐标系下方的label距离坐标系的距离。
			coordinate : {
				background_color : '#d7d7d5',
				grid_color : '#a4a4a2',
				color_factor : 0.24,
				board_deep : 10,
				offsety : -10,
				pedestal_height : 10,
				left_board : false,//取消左侧面板
				width : 820,
				height : 340,
				scale : [ {
					position : 'left',
					start_scale : 0,
					end_scale : 4,
					scale_space : 0.5,
					scale_enable : false,
					label : {
						color : '#4c4f48'
					}
				} ]
			}
		});

		//利用自定义组件构造左侧说明文本
		chart
				.plugin(new iChart.Custom(
						{
							drawFn : function() {
								//计算位置
								var coo = chart.getCoordinate(), x = coo
										.get('originx'), y = coo.get('originy');
								//在左上侧的位置，渲染一个单位的文字
								chart.target.textAlign('start').textBaseline(
										'bottom').textFont('600 11px Verdana')
								//.fillText('招生人数(万人)',x-40,y-28,false,'#6d869f');

							}
						}));

		chart.draw();
	}

	$(document).ready(function() {
		$('#projects').select2();

		$("#tabs").tabs();

		pgfx();
		pgdf();
		zbdf();
		bzfx();
	});
</script>
