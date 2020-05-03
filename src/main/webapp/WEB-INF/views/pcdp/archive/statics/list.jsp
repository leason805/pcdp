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

<div id="tabs">
	<ul>
		<li><a href="#tabs-1">人员概况</a></li>
		<li><a href="#tabs-2">技术等级</a></li>
		<li><a href="#tabs-3">放行经历</a></li>
		<li><a href="#tabs-4">运行控制经历</a></li>
	</ul>
	<div id="tabs-1">
		<div class="row">
			<div class="col-xs-12">
				<div class="box">
					<div id="renyuan_div">

					</div>
				</div>
			</div>
		</div>
	</div>
	
	<div id="tabs-2">
		<div class="row">
			<div class="col-xs-12">
				<div class="box">
					<div id="jishu_div">

					</div>
				</div>
			</div>
		</div>
	</div>
	
	<div id="tabs-3">
		<div class="row">
			<div class="col-xs-12">
				<div class="box">
					<div id="fangxing_div">

					</div>
				</div>
			</div>
		</div>
	</div>

	<div id="tabs-4">
		<div class="row">
			<div class="col-xs-12">
				<div class="box">
					<div id="yunxing_div">
					</div>
				</div>
			</div>
		</div>
	</div>


<script type="text/javascript">
// Run Datables plugin and create 3 variants of settings

function renyuan(){
	var data = [
				<c:forEach var="cur" items="${types}" varStatus="status">
				{name : '${cur.name}',value : ${cur.size},color:'#${cur.color}'}<c:if test="${(status.index+1) < fn:length(techs)}">,</c:if>
				</c:forEach>
        	];
	
	var chart = new iChart.Donut2D({
		render : 'renyuan_div',
		center:{
			text:'人员概括',
			shadow:true,
			shadow_offsetx:0,
			shadow_offsety:2,
			shadow_blur:2,
			shadow_color:'#b7b7b7',
			color:'#6f6f6f'
		},
		data: data,
		offsetx:-60,
		shadow:true,
		background_color:'#f4f4f4',
		separate_angle:10,//分离角度
		tip:{
			enable:true,
			showType:'fixed'
		},
		legend : {
			enable : true,
			shadow:true,
			background_color:null,
			border:false,
			legend_space:30,//图例间距
			line_height:34,//设置行高
			sign_space:10,//小图标与文本间距
			sign_size:30,//小图标大小
			color:'#6f6f6f',
			fontsize:30//文本大小
		},
		sub_option:{
			label:false,
			color_factor : 0.3
		},
		//showpercent:true,
		decimalsnum:2,
		width : 800,
		height : 400,
		radius:140
	});

	/**
	 *利用自定义组件构造左侧说明文本。
	 */
	chart.plugin(new iChart.Custom({
			drawFn:function(){
				/**
				 *计算位置
				 */	
				var y = chart.get('originy');
				/**
				 *在左侧的位置，设置竖排模式渲染文字。
				 */
				chart.target.textAlign('center')
				.textBaseline('middle')
				.textFont('600 24px 微软雅黑')
				//.fillText('攻城师需要掌握的核心技能',100,y,false,'#6d869f', 'tb',26,false,0,'middle');
				
			}
	}));
	
	chart.draw();
	
}
function jishu(){
	var data = [
			<c:forEach var="cur" items="${techs}" varStatus="status">
				{name : '${cur.name}',value : ${cur.size},color:'#${cur.color}'}<c:if test="${(status.index+1) < fn:length(techs)}">,</c:if>
			</c:forEach>
        	];
	
	//是否启用动画
	var animation = false;
	
	var chart = new iChart.Column2D({
		render : 'jishu_div',
		data: data,
		title : {
			text : '技术等级人员统计',
			color : '#3e576f'
		},
		
		//subtitle : {
		//	text : 'Top 5 Sales Person,2011',
		//	color : '#6d869f'
		//},

		width : 1080,
		height : 500,
		animation : animation,
		animation_duration:600,
		shadow : true,
		shadow_blur : 2,
		shadow_color : '#aaaaaa',
		shadow_offsetx : 1,
		shadow_offsety : 0,
		column_width : 48,
		label:{
			color:'#4c4f48'
		},
		sub_option:{
			label : {
				color : '#ffffff'
			},
			listeners:{
				parseText:function(r,t){
					//自定义柱形图上方label的格式。
					return t;
				}
			}
		},
		coordinate:{
			background_color : '#4a4b4f',
			grid_color : '#676a73',
			striped_factor:0.06,
			height:'94%',
			width:'84%',
			scale:[{
				 position:'left',	
				 start_scale:0,
				 scale_space:10,
				 label:{
					color:'#4c4f48'
				 },
				 listeners:{
					parseText:function(t,x,y){
					//自定义左侧坐标系刻度文本的格式。
						return {text: t}
					}
				 }
			}]
		}
	});
	var pie = new iChart.Pie2D({
		data: data,
		label:{
			color:'#4c4f48'
		},
		sub_option:{
			mini_label_threshold_angle : 60,//迷你label的阀值,单位:角度
			mini_label:{//迷你label配置项
				fontsize:10,
				fontweight:600,
				color : '#ffffff'
			},
			label : {
				background_color:null,
				sign:false,//设置禁用label的小图标
				padding:'0 4',
				border:{
					enable:false,
					color:'#666666'
				},
				fontsize:10,
				fontweight:600,
				color : '#ffe383'
			},
			listeners:{
				parseText:function(d, t){
					return d.get('value');//自定义label文本
				}
			} 
		},
		text_space : 16,
		showpercent:true,
		decimalsnum:1,
		align : 'left',
		offsetx:chart.coo.get('originx')+80,
		offsety:-(chart.get('centery')-chart.coo.get('originy')-90),
		animation : animation,
		radius:60
	});
	
	chart.plugin(pie);
	
	 //利用自定义组件构造左侧说明文本。
	chart.plugin(new iChart.Custom({
			drawFn:function(){
				 //计算位置
				var coo = chart.getCoordinate(),
					x = coo.get('originx'),
					y = coo.get('originy'),
					H = coo.height;
				//在左侧的位置，渲染说明文字。
				chart.target.textAlign('center')
				.textBaseline('middle')
				.textFont('600 13px Verdana')
				//.fillText('Sales Figure',x-50,y+H/2,false,'#6d869f', false,false,false,-90);
				
			}
	}));

	chart.draw();
};
	
function fangxing(){
	var data = [
	        	{name : '小于2年 - 【1】',value : 13,color:'#4572a7'},
	        	{name : '大于等于2年，小于4年 - 【3】',value : 37,color:'#aa4643'},
	        	{name : '大于等于4年，小于等于6年 - 【2】',value : 25,color:'#89a54e'},
	        	{name : '大于6年 - 【2】',value : 25,color:'#80699b'}
        	];

	
	var chart = new iChart.Pie3D({
		render : 'fangxing_div',
		data: data,
		title : {
			text : '放行经历（放行年限）',
			height:30,
			fontsize : 20,
			color : '#282828'
		},
		sub_option : {
			mini_label_threshold_angle : 40,//迷你label的阀值,单位:角度
			mini_label:{//迷你label配置项
				fontsize:20,
				fontweight:600,
				color : '#ffffff'
			},
			label : {
				background_color:null,
				sign:false,//设置禁用label的小图标
				padding:'0 4',
				border:{
					enable:false,
					color:'#666666'
				},
				fontsize:11,
				fontweight:600,
				color : '#4572a7'
			},
			border : {
				width : 2,
				color : '#ffffff'
			},
			listeners:{
				parseText:function(d, t){
					return d.get('value')+"%";//自定义label文本
				}
			} 
		},
		legend:{
			enable:true,
			padding:0,
			offsetx:120,
			offsety:50,
			color:'#3e576f',
			fontsize:20,//文本大小
			sign_size:20,//小图标大小
			line_height:28,//设置行高
			sign_space:10,//小图标与文本间距
			border:false,
			align:'left',
			background_color : null//透明背景
		}, 
		shadow : true,
		shadow_blur : 6,
		shadow_color : '#aaaaaa',
		shadow_offsetx : 0,
		shadow_offsety : 0,
		background_color:'#f1f1f1',
		align:'right',//右对齐
		offsetx:-100,//设置向x轴负方向偏移位置60px
		offset_angle:-90,//逆时针偏移120度
		width : 980,
		height : 500,
		radius:150
	});
	//利用自定义组件构造右侧说明文本
	chart.plugin(new iChart.Custom({
			drawFn:function(){
				//在右侧的位置，渲染说明文字
				chart.target.textAlign('start')
				.textBaseline('top')
				.textFont('600 20px Verdana')
				.textFont('600 12px Verdana')
			}
	}));
	
	chart.draw();
}

function yunxing(){
	var data = [
	        	{name : '小于2年 - 【2】',value : 25,color:'#4572a7'},
	        	{name : '大于等于2年，小于4年 - 【3】',value : 37,color:'#aa4643'},
	        	{name : '大于等于4年，小于等于6年 - 【1】',value : 13,color:'#89a54e'},
	        	{name : '大于6年 - 【2】',value : 25,color:'#80699b'}
        	];

	
	var chart = new iChart.Pie3D({
		render : 'yunxing_div',
		data: data,
		title : {
			text : '运行控制经历（持照年限）',
			height:30,
			fontsize : 20,
			color : '#282828'
		},
		sub_option : {
			mini_label_threshold_angle : 40,//迷你label的阀值,单位:角度
			mini_label:{//迷你label配置项
				fontsize:20,
				fontweight:600,
				color : '#ffffff'
			},
			label : {
				background_color:null,
				sign:false,//设置禁用label的小图标
				padding:'0 4',
				border:{
					enable:false,
					color:'#666666'
				},
				fontsize:11,
				fontweight:600,
				color : '#4572a7'
			},
			border : {
				width : 2,
				color : '#ffffff'
			},
			listeners:{
				parseText:function(d, t){
					return d.get('value')+"%";//自定义label文本
				}
			} 
		},
		legend:{
			enable:true,
			padding:0,
			offsetx:120,
			offsety:50,
			color:'#3e576f',
			fontsize:20,//文本大小
			sign_size:20,//小图标大小
			line_height:28,//设置行高
			sign_space:10,//小图标与文本间距
			border:false,
			align:'left',
			background_color : null//透明背景
		}, 
		shadow : true,
		shadow_blur : 6,
		shadow_color : '#aaaaaa',
		shadow_offsetx : 0,
		shadow_offsety : 0,
		background_color:'#f1f1f1',
		align:'right',//右对齐
		offsetx:-100,//设置向x轴负方向偏移位置60px
		offset_angle:-90,//逆时针偏移120度
		width : 980,
		height : 500,
		radius:150
	});
	//利用自定义组件构造右侧说明文本
	chart.plugin(new iChart.Custom({
			drawFn:function(){
				//在右侧的位置，渲染说明文字
				chart.target.textAlign('start')
				.textBaseline('top')
				.textFont('600 20px Verdana')
				.textFont('600 12px Verdana')
			}
	}));
	
	chart.draw();
}

$(document).ready(function() {
	$("#tabs").tabs();
	
	renyuan();
	jishu();
	fangxing();
	yunxing();
});
</script>
