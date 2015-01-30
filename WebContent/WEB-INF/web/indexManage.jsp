<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><s:text name="gccp.login"/></title>
<jsp:include page="include.jsp"/>
<style>

.navbar.navbar-static.navbar_as_heading .navbar-inner {
    border-radius: 0 0 0 0;
    margin: -20px -20px 20px;
}
</style>
<script>
	$(function(){
		if (self != top) {  
			<%
			String path = request.getContextPath();
	    	out.println("window.open ('"+path+"/login.action?type=timeout','_top')");  
			%>
		}
		$("#background").css({
				'height' : $(window).height() +'px',
				'width'  : $(window).width() +'px'
			});
	});
</script>
</head>
<body>
	<div>
		<div style="height:100%;position:absolute;width:100%;background: none repeat scroll 0 0 rgba(0, 0, 0, 0.4);z-index: 100;" ></div>
		<div id="zoomWallpaperGrid" style="position:absolute;z-index:-10;top:0;left:0;height:100%;width:100%;background: #000000 url(/${applicationScope.context}/static/artDialog4.1.6/skins/icons/wallpaper3.jpg) no-repeat 50% 50%"></div>
		<!-- <img id="background" src="static/image/wallpaper/loading_ernv.jpg"> -->
	</div>
	<div style="position:absolute; top:35%;left:0px; width:100%;z-index:5555">
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="well" style="width:576px;margin:auto auto">
				<div class="navbar navbar-static navbar_as_heading navbar-inverse">
					<div class="navbar-inner">
						<div class="container" style="width: auto;">
							<a class="brand"><s:text name="gccp.common.login"/></a>
						</div>
					</div>
				</div>
				<s:actionerror />
				<form id="validationForm" method="post"  action="/${applicationScope.context}/manage/login.action">

					<div class="form-horizontal">
					<div class="control-group ">
						<div class="controls">
							<s:fielderror cssStyle="color:red">
								<s:param>errorInfo</s:param>
							</s:fielderror>
						</div>
					</div>
					<div class="control-group ">
						<label class="control-label"><s:text name="gccp.common.adminname" />:</label>
						<div class="controls">
							<input type="text" name="adminname" value=""
								id="validationForm_name" />
							<s:fielderror cssStyle="color:red">
								<s:param>adminname</s:param>
							</s:fielderror>
						</div>
					</div>
					<div class="control-group ">
						<label class="control-label" for="validationForm_password"><s:text name="gccp.common.password" />:</label>
						<div class="controls">
							<input type="password" name="password"
								id="validationForm_password" />
							<s:fielderror>
								<s:param>password</s:param>
							</s:fielderror>
						</div>
					</div>
					<input type="submit" value="<s:text name="cs.common.submit" />" style="margin-left:180px;"
						class="btn btn-primary">
					</div>
				</form>
			</div>
		</div>
	</div>
	</div>
</body>
</html>