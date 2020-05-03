function OpenWindow1(header, innerID, bottomID){
	var modalbox = $('#modalbox');
	//var header = $('#'+headerID).html();
	var inner = $('#'+innerID).html();
	var bottom = $('#'+bottomID).html();
	modalbox.find('.modal-header-name span').html(header);
	modalbox.find('.devoops-modal-inner').html(inner);
	modalbox.find('.devoops-modal-bottom').html(bottom);
	modalbox.fadeIn('fast');
	$('body').addClass("body-expanded");
}


function OpenWindow(header, inner, bottom){
	var modalbox = $('#modalbox');
	modalbox.find('.modal-header-name span').html(header);
	modalbox.find('.devoops-modal-inner').html(inner);
	if(bottom){
		modalbox.find('.devoops-modal-bottom').html(bottom);
	}
	
	modalbox.fadeIn('fast');
	$('body').addClass("body-expanded");
}


function OpenWindow2(html){
	var modalbox = $('#modalbox');
	modalbox.html(html);
	modalbox.fadeIn('fast');
	$('body').addClass("body-expanded");
}

function changepwd(){
	var url = "${ctx}/system/password.htm";
	$.colorbox({href:url, iframe:true, width:"65%", height:"60%"});
}

function LoadContent(url){
	var content = "";
	$.ajax({
		mimeType: 'text/html; charset=utf-8', // ! Need set mimeType only when run from local file
		url: url,
		type: 'GET',
		success: function(data) {
			content = data;
		},
		error: function (jqXHR, textStatus, errorThrown) {
			alert(errorThrown);
			alert(textStatus);
			content = "Page failed to load!";
		},
		dataType: "html",
		async: false
	});
	return content;
}

function closeBox(){
	$.colorbox.close();
}

function closeBoxFromWin(){
	parent.closeBox();
}

function reloadTable(){
	var oTable = parent.oTable;
	oTable.fnReloadAjax();
}