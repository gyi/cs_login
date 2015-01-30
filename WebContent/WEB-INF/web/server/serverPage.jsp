<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="../include.jsp"/>
</head>
<body>
<div id="main" class="container-fluid">
		<ul class="nav nav-tabs"  id="myTab">
		  <li class="active"><a href="#server" data-toggle="tab"><s:text name="cs.server.select"/></a></li>
		  <li><a href="#areaSelect" data-toggle="tab"><s:text name="cs.server.areaSelect"/></a></li>
		  <li><a href="#addarea" data-toggle="tab"><s:text name="cs.server.addarea"/></a></li>
		  <li><a href="#addserver" data-toggle="tab"><s:text name="cs.server.addserver"/></a></li>
		</ul>
		<!-- Ã¦ÂÂÃ¥ÂÂ¡Ã¥ÂÂ¨Ã¦ÂÂ¥Ã¨Â¯Â¢ -->
		<div class="tab-content">
		  <div class="tab-pane active" id="server">
		  	<div class="well">
		  		 <div class="control-group">
			          <div class="controls">
			            <div class="input-prepend">
			              <span class="add-on"><s:text name="cs.area"/></span>
			              <select id="areaId">
					     	<option value="-999"><s:text name="cs.common.all"/></option>
					     	<s:iterator value="areas" id="area">
					     		<option value='<s:property value="#area.id"/>'>
					     			<s:property value="#area.areaname"/>
					     		</option>
					     	</s:iterator>
					     </select>
					     <button class="btn btn-primary" id="selectServers"><s:text name="cs.common.select"/></button>
			            </div>
			          </div>
		         </div>
			</div>
			<div id="selectContent">
			</div>
		  </div>
		  <!-- Ã¥ÂÂºÃ¦ÂÂ¥Ã¨Â¯Â¢ -->
		  <div class="tab-pane" id="areaSelect">
		  	<div class="well">
		  		 <div class="control-group">
			          <div class="controls">
			          	<button class="btn btn-primary" id="selectArea"><s:text name="cs.common.select"/></button>
			          </div>
		         </div>
			</div>
			<div id="areaSelectContent">
			</div>
		  </div>
		  <!-- Ã¦Â·Â»Ã¥ÂÂ Ã¥ÂÂº -->
		   <div class="tab-pane" id="addarea">
		  	<div class="well">
          		 <div class="control-group">
		          <div class="controls">
		            <div class="input-prepend">
		              <span class="add-on"><s:text name="cs.server.areaName"/></span>
		              <input placeholder="<s:text name="cs.server.areaName"/>" id="areaName" type="text">
		            </div>
		          </div>
		        </div>
		        <div class="control-group">
		          <div class="controls">
		            <div class="input-prepend">
		              <span class="add-on"><s:text name="com.comm.desc"/></span>
		              <input placeholder="<s:text name="com.comm.desc"/>" id="areaDesc" type="text">
		            </div>
		          </div>
		        </div>
		        <button class="btn btn-primary" id="addArea"><s:text name="cs.common.submit"/></button>
			</div>
			<div id="addAreaContent">
			</div>
		  </div>
		  <!-- Ã¦Â·Â»Ã¥ÂÂ Ã¦ÂÂÃ¥ÂÂ¡Ã¥ÂÂ¨ -->
		  <div class="tab-pane" id="addserver">
		  	<div class="well">
		  		 <div class="control-group">
		          <div class="controls">
		            <div class="input-prepend">
		              <span class="add-on"><s:text name="com.comm.sName"/></span>
		              <input placeholder="<s:text name="cs.server.serverName"/>" id="serverName" type="text">
		            </div>
		          </div>
		        </div>
		        <div class="control-group">
		          <div class="controls">
		            <div class="input-prepend">
		              <span class="add-on"><s:text name="cs.server.areaName"/></span>
		              <select id="areaAddId">
				     	<s:iterator value="areas" id="area">
				     		<option value='<s:property value="#area.id"/>'>
				     			<s:property value="#area.areaname"/>
				     		</option>
				     	</s:iterator>
				      </select>
		            </div>
		          </div>
		        </div>
		        <div class="control-group">
		          <div class="controls">
		            <div class="input-prepend">
		              <span class="add-on">&nbsp;&nbsp;IP&nbsp;&nbsp;</span>
		              <input placeholder="IP" id="ip" type="text">
		            </div>
		          </div>
		        </div>
		        <div class="control-group">
		          <div class="controls">
		            <div class="input-prepend">
		              <span class="add-on"><s:text name="cs.server.port"/></span>
		              <input placeholder="<s:text name="cs.server.port"/>" id="port" type="text">
		            </div>
		          </div>
		        </div>
		        <div class="control-group">
		          <div class="controls">
		            <div class="input-prepend">
		              <span class="add-on"><s:text name="cs.server.lowlimit"/></span>
		              <input placeholder="<s:text name="cs.server.lowlimit"/>" id="low" type="text">
		            </div>
		          </div>
		        </div>
		        <div class="control-group">
		          <div class="controls">
		            <div class="input-prepend">
		              <span class="add-on"><s:text name="cs.server.middlelimit"/></span>
		              <input placeholder="<s:text name="cs.server.middlelimit"/>" id="middle" type="text">
		            </div>
		          </div>
		        </div>
		        <div class="control-group">
		          <div class="controls">
		            <div class="input-prepend">
		              <span class="add-on"><s:text name="cs.server.maxlimit"/></span>
		              <input placeholder="<s:text name="cs.server.maxlimit"/>" id="max" type="text">
		            </div>
		          </div>
		        </div>
		        <button class="btn btn-primary" id="addServer"><s:text name="cs.common.submit"/></button>
			</div>
			<div id="addServerContent">
			</div>
		  </div>
		</div>
	</div>
</body>
<script type="text/javascript">
	var url = "";
	var typeDel = "";
	$(function(){
		//Ã¦ÂÂ¥Ã§ÂÂÃ¦ÂÂÃ¥ÂÂ¡Ã¥ÂÂ¨
		$("#selectServers").click(function(){
			var areaId = $("#areaId").val() ;
			$.post("/${applicationScope.context}/server/serverSelect.action",{areaId : areaId},
					function(data, textStatus){
						if(data.result=="true") {
							url = "/${applicationScope.context}/server/deleteServer.action";
							typeObj = "Servers";
							typeDelect = "delete";
							var title = ["<s:text name='cs.server.serverId'/>", "<s:text name='cs.server.serverName'/>",
							             "<s:text name='cs.server.areaName'/>","<s:text name='cs.server.ip'/>", 
							             "<s:text name='cs.server.port'/>","<s:text name='cs.server.perSizecondition'/>","<s:text name='cs.server.isActive'/>","<s:text name='cs.server.onOffCondition'/>"] ;
							var column = ["id", "servername", "areaname", "ip", "port", "perSizecondition", "isActive", "onOffCondition"] ;
							var addTitle = ["<s:text name='com.comm.operator'/>"] ;
							var addColnum = [
							                 "<a href='#' onClick='clickDel(this, url, typeObj, typeDelect)'><s:text name='com.comm.delete'/></a>&nbsp;"+
							                 "<a href='#' onClick='clickOnOff(this)'><s:text name='cs.server.onOff'/></a>&nbsp;"+
							                 "<a href='#' onClick='clickToActive(this)'><s:text name='cs.server.toactive'/></a>"] ;
							var result = createTable(data.serversContainer, title, column, addTitle, addColnum) ;
							$("#selectContent").html(result) ;
						}else {
							bootstrapAlert("<s:text name='cs.common.selecterror'/>") ;
						}
					}) ;
		}) ;
		//Ã¦ÂÂ¥Ã§ÂÂÃ¥ÂÂº
		$("#selectArea").click(function(){
			$.post("/${applicationScope.context}/server/areaSelect.action",{},
					function(data, textStatus){
						if(data.result=="true") {
							url = "/${applicationScope.context}/server/deleteArea.action";
							typeObj = "Area";
							typeDelect = "delete";
							var title = ["<s:text name='cs.server.areaId'/>", "<s:text name='cs.server.areaName'/>",
							             "<s:text name='cs.server.areaDesc'/>"] ;
							var column = ["id", "areaname", "describle"] ;
							var addTitle = ["<s:text name='cs.server.operator'/>"] ;
							var addColnum = ["<a href='#' onClick='clickDel(this, url, typeObj, typeDelect)'><s:text name='com.comm.delete'/></a>"] ;
							var result = createTable(data.areas, title, column, addTitle, addColnum) ;
							$("#areaSelectContent").html(result) ;
						}else {
							bootstrapAlert("<s:text name='cs.common.selecterror'/>") ;
						}
					}) ;
		}) ;
		//Ã¦Â·Â»Ã¥ÂÂ Ã¦ÂÂÃ¥ÂÂ¡Ã¥ÂÂ¨
		$("#addArea").click(function(){
			var areaName = $("#areaName").val() ;
			var areaDesc = $("#areaDesc").val() ;
			$.post("/${applicationScope.context}/server/addArea.action",
					{areaName : areaName, areaDesc : areaDesc},
					function(data, textStatus){
						if(data.result=="true") {
							art.dialog.tips("<s:text name='com.comm.addSuccess'/>");
						}else {
							if(isEmptyValue(data.fieldErrors)) {
								bootstrapAlert("<s:text name='cs.common.adderror'/>") ;
							}else {
								bootstrapAlert(data.fieldErrors.areaMessageError) ;
							}
						}
					}) ;
		}) ;
		//Ã¦Â·Â»Ã¥ÂÂ Ã¥ÂÂº
		$("#addServer").click(function(){
			var serverName = $("#serverName").val() ;
			var areaAddId = $("#areaAddId").val() ;
			var ip = $("#ip").val() ;
			var port = $("#port").val() ;
			var low = $("#low").val() ;
			var middle = $("#middle").val() ;
			var max = $("#max").val();
			$.post("/${applicationScope.context}/server/addServer.action",
					{serverName : serverName, areaId : areaAddId, ip : ip, port : port, low : low, middle : middle, max : max},
					function(data, textStatus){
						if(data.result=="true") {
							art.dialog.tips("<s:text name='com.comm.addSuccess'/>");
						}else {
							if(isEmptyValue(data.fieldErrors)) {
								bootstrapAlert("<s:text name='cs.common.adderror'/>") ;
							}else {
								bootstrapAlert(data.fieldErrors.serverMessageError) ;
							}
						}
					}) ;
		}) ;
	}) ;
	//Ã¦ÂÂ§Ã¨Â¡ÂÃ¥Â¼ÂÃ¥ÂÂ¯Ã¥ÂÂ³Ã©ÂÂ­Ã¦ÂÂÃ¥ÂÂ¡Ã¥ÂÂ¨Ã¦ÂÂÃ¤Â½Â
	function clickOnOff(obj) {
		var serverInfo=obj.parentNode.parentNode;
		var tip = '';
		if(serverInfo.cells[7].innerText==(gccp_server_open)){
			tip = gccp_server_toClose;
		}else if(serverInfo.cells[7].innerText==(gccp_server_close)){
			tip = gccp_server_toOpen;
		}
		art.dialog.confirm(tip, function () {
			$.post("/${applicationScope.context}/server/serverOnOff.action",
					{serverId : serverInfo.cells[0].innerText},
					function(data, textStatus){
						if(data.result=="true") {
							art.dialog.tips(gccp_common_success);
							$("#selectServers").click();
						}else {
							if(isEmptyValue(data.fieldErrors)) {
								bootstrapAlert("<s:text name='cs.common.error'/>") ;
							}else {
								bootstrapAlert(data.fieldErrors.serverConnectError) ;
							}
						}
					}) ;
		}, function () {
		    art.dialog.tips(gccp_common_cancel);
		});
		

	};
	
	function clickToActive(obj) {
		var serverInfo=obj.parentNode.parentNode;
		var tip = '';
		if(serverInfo.cells[6].innerText==(gccp_server_active)){
			tip = gccp_server_toFreeze;
		}else if(serverInfo.cells[6].innerText==(gccp_server_freeze)){
			tip = gccp_server_toActive;
		}
		art.dialog.confirm(tip, function () {
			$.post("/${applicationScope.context}/server/serverToActive.action",
					{serverId : serverInfo.cells[0].innerText},
					function(data, textStatus){
						if(data.result=="true") {
							art.dialog.tips(gccp_common_success);
							$("#selectServers").click();
						}else {
							bootstrapAlert("<s:text name='cs.common.error'/>") ;
						}
					}) ;
		}, function () {
		    art.dialog.tips(gccp_common_cancel);
		});
		

	};
</script>
</html>