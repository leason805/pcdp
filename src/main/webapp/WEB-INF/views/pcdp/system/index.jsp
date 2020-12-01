<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/common.jsp"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"><head>
<title>签派员评估系统</title> 
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta charset="utf-8">
		<title>登录</title>
		<meta name="description" content="description">
		<meta name="author" content="Evgeniya">
		<meta name="keyword" content="keywords">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link href="<%=request.getContextPath()%>/plugins/bootstrap/bootstrap.css" rel="stylesheet">
		<link href="<%=request.getContextPath()%>/css/font-awesome.css" rel="stylesheet">
		<link href='<%=request.getContextPath()%>/css/G_Font_Righteous.css' rel='stylesheet' type='text/css'>
		<link href="http://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css" rel="stylesheet">
		<link href='http://fonts.googleapis.com/css?family=Righteous' rel='stylesheet' type='text/css'>
		<link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet">
		<link href="${ctx}/css/login.css" rel="stylesheet" type="text/css" />
		
</head>
<body>
<h1>系统登录</h1>

<div class="login" style="margin-top:50px;">
    
    <div class="header">
        <div class="switch" id="switch">
        	<c:if test="${not empty message}">
						<p class="bg-danger">${message}</p>
					</c:if>
        </div>
    </div>    
  
    
    <div class="web_qr_login" id="web_qr_login" style="display: block; height: 235px;">    

            <div class="web_login" id="web_login">
               
               
               <div class="login-box">
    
            
			<div class="login_form">
				<form action="${ctx}/system/main.htm" name="loginform" accept-charset="utf-8" id="login_form" class="loginForm" method="post">
                <div class="uinArea" id="uinArea">
                <label class="input-tips" for="u">帐号:</label>
                <div class="inputOuter" id="uArea">
                    
                    <input type="text" id="u" name="username" class="inputstyle"/>
                </div>
                </div>
                <div class="pwdArea" id="pwdArea">
               <label class="input-tips" for="p">密码:</label> 
               <div class="inputOuter" id="pArea">
                    
                    <input type="password" id="password" name="password" class="inputstyle"/>
                </div>
                </div>
               
                <div style="padding-left:50px;margin-top:20px;"><input type="submit" value="登 录" style="width:150px;" class="button_blue"/></div>
              </form>
           </div>
           
            	</div>
               
            </div>
            <!--ç»å½end-->
  </div>
</div>
<div class="jianyi">*推荐使用谷歌浏览器或火狐浏览器访问</div>
<script type="text/javascript">
window.onload = function(){
	var type = browserType();
	if(type != "firefox" && type != "chrome"){
		alert("您使用的不是谷歌浏览器或火狐浏览器，为保证功能正常使用，请使用谷歌浏览器或火狐浏览器");
	}
}


function browserType(){
    var userAgent = navigator.userAgent; //取得浏览器的userAgent字符串
    if (userAgent.indexOf("Opera") > -1) {
        return "opera"
    }; //判断是否Opera浏览器
    if (userAgent.indexOf("Firefox") > -1) {
        return "firefox";
    } //判断是否Firefox浏览器
    if (userAgent.indexOf("Chrome") > -1){
        return "chrome";
    }
    if (userAgent.indexOf("Safari") > -1) {
        return "safari";
    } //判断是否Safari浏览器
    if (userAgent.indexOf("compatible") > -1 && userAgent.indexOf("MSIE") > -1 && !isOpera) {
          return "ie";
    }; //判断是否IE浏览器
}
</script>
</body></html>