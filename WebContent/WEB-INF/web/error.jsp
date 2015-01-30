<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="refresh" content="5;url=../manage/loginSuccess.action">
<jsp:include page="include.jsp"/>
<title>error</title>
</head>
<style type="text/css">
.error {
	position: absolute;
	left: 60%;
	top: 45%
}
</style>

<script type="text/javascript">
<!--最初的时间-->
	var time = 6;
	<!--动态时间的显示函数-->
	$(function() {
		returnUrlByTime();
	});
	function returnUrlByTime() {
		if(time>0){
			<!--每隔1秒调用该函数-->
			window.setTimeout('returnUrlByTime()', 1000);
			<!--时间递减-->
			time = time - 1;
			<!--显示动态时间的层-->
			document.getElementById("layer").innerHTML = time;
		}
	}
</script>
<body>
	<div id="zoomWallpaperGrid"
		style="-webkit-filter:blur(5px);position:absolute;z-index:-10;top:0;left:0;height:100%;width:100%;background: #000000 url('/${applicationScope.context}/static/artDialog4.1.6/skins/icons/sad_bobby-wallpaper-1920x1080.jpg') no-repeat 50% 50%"></div>
	<div class="error">
		<h1 class="text-danger"
			style="font-size: 130px; padding-bottom: 50px;">
			<s:text name="gccp.error.error"/>
			&nbsp;
		</h1>
		<br />
		<h2>
			<span id="layer">5</span>
			<s:text name="gccp.error.seconds"/>
			<br />
			<s:text name="gccp.error.jump"/>
			<a href="../manage/loginSuccess.action" class="text-info">
			<s:text name="gccp.error.here"/></a>
		</h2>
	</div>

</body>
</html>