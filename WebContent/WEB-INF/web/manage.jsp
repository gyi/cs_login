<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><s:text name="cs.login.manage"/></title>
<jsp:include page="include.jsp"/>
<link rel="stylesheet" href="/${applicationScope.context}/static/img/hoorayos/index.css">
</head>
<body>
	<div id="zoomWallpaperGrid" style="position:absolute;z-index:-10;top:0;left:0;height:100%;width:100%;background: #000000 url('/${applicationScope.context}/static/artDialog4.1.6/skins/icons/main5.jpg') no-repeat 50% 50%"></div>
	<!-- 桌面 -->
	<div id="desktop" style="display: block;">
		<div id="desk">
			<div id="desk-1" class="desktop-container" style="width: 1920px; height: 789px; left: 0px; top: 80px;">
				<div class="desktop-apps-container">
					<li id="d_55" class="appbtn" appName="1" title='<s:text name="cs.login.servermanage"/>' left="36" top="107" style="top: 27px; left: 36px;" action="/${applicationScope.context}/server/serverPage.action">
						<div>
							<img src="/${applicationScope.context}/static/img/hoorayos/d_55.ico" alt='<s:text name="cs.login.servermanage"/>'>
						</div>
						<span><s:text name="cs.login.servermanage"/></span>
					</li>
					<li id="d_54" class="appbtn" appName="2" title="<s:text name="cs.login.playermanage"/>" left="156" top="107" style="top: 27px; left: 156px;" action="/${applicationScope.context}/player/playerPage.action">
						<div>
							<img src="/${applicationScope.context}/static/img/hoorayos/d_54.ico" alt='<s:text name="cs.login.playermanage"/>'>
						</div>
						<span><s:text name="cs.login.playermanage"/></span>
					</li>
					<li id="d_53" class="appbtn" appName="3" title="<s:text name="cs.login.platformmanage"/>" left="276" top="107" style="top: 27px; left: 276px;" action="/${applicationScope.context}/platform/platformPage.action">
						<div>
							<img src="/${applicationScope.context}/static/img/hoorayos/d_53.ico" alt="<s:text name="cs.login.platformmanage"/>">
						</div>
						<span><s:text name="cs.login.platformmanage"/></span>
					</li>
					<div id="hiddle">
						<li id="d_52" class="appbtn" appName="4" title="<s:text name="cs.login.adminmanage"/>" left="396" top="107" style="top: 27px; left: 396px;" action="/${applicationScope.context}/admin/AdminPage.action">
							<div>
								<img src="/${applicationScope.context}/static/img/hoorayos/d_52.ico" alt="<s:text name="cs.login.adminmanage"/>">
							</div>
							<span><s:text name="cs.login.adminmanage"/></span>
						</li>
					</div>
					<div id="hiddle">
						<li id="d_51" class="appbtn" appName="5" title="<s:text name="cs.clientvesion.name"/>" left="516" top="107" style="top: 27px; left: 516px;" action="/${applicationScope.context}/clientversion/clientVersion.action">
							<div>
								<img src="/${applicationScope.context}/static/img/hoorayos/d_51.png" alt="<s:text name="cs.clientvesion.name"/>">
							</div>
							<span><s:text name="cs.clientvesion.name"/></span>
						</li>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div id="task-bar-bg1"></div>
	<div id="task-bar-bg2"></div>
	<div id="task-bar">
		<div id="task-next"><a href="javascript:;" id="task-next-btn" hidefocus="true"></a></div>
		<div id="task-content">
		</div>
		<div id="task-pre"><a href="javascript:;" id="task-pre-btn" hidefocus="true"></a></div>
	</div>
</body>
<script type="text/javascript" src="/${applicationScope.context}/static/js/index.js"></script>
<script type="text/javascript">
	$(function() {
		var secrurity = ${SECRURITYSTYLE};
		if(secrurity != '01'){
			$("#hiddle").hide();
		}
		else{
			$("#hiddle").show();
		}
	});

</script>
</html>