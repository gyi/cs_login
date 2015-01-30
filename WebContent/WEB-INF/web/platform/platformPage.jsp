<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<jsp:include page="../include.jsp"/>
<body>
	<div id="main" class="container-fluid">
		<ul class="nav nav-tabs"  id="myTab">
		  <li class="active"><a href="#platformSelect" data-toggle="tab"><s:text name="cs.platform.select"/></a></li>
		  <li><a href="#addPlatform" data-toggle="tab"><s:text name="cs.platform.addPlatform"/></a></li>
		</ul>
		<div class="tab-content">
		  <div class="tab-pane active" id="platformSelect">
		  	<div class="well">
		  		 <div class="control-group">
			          <div class="controls">
			            <div class="input-prepend">
			              <span class="add-on"><s:text name="com.comm.selectType"/></span>
			              <select id="platformId">
			              	<option value="-999"><s:text name="cs.common.all"/></option>
			              	<s:iterator value="platforms" id="platform">
					     		<option value='<s:property value="#platform.id"/>'>
					     			<s:property value="#platform.platformname"/>
					     		</option>
					     	</s:iterator>
					     </select>
					     <button class="btn btn-primary" id="selectPlatform"><s:text name="cs.common.select"/></button>
			            </div>
			          </div>
		         </div>
			</div>
			<div id="selectContent">
			</div>
		  </div>
		  <div class="tab-pane" id="addPlatform">
		  	<div class="well">
		  		 <div class="control-group">
		          <div class="controls">
		            <div class="input-prepend">
		              <span class="add-on">&nbsp;&nbsp;<s:text name="cs.platform.platformName"/>&nbsp;&nbsp;</span>
		              <input placeholder="<s:text name="cs.platform.platformName"/>" id="platformName" type="text">
		            </div>
		          </div>
		         </div>
		        <div class="control-group">
		          <div class="controls">
		            <div class="input-prepend">
		              <span class="add-on"><s:text name="cs.platform.platformDesc"/></span>
		              <input placeholder="<s:text name="cs.platform.platformDesc"/>" id="platformDesc" type="text">
		            </div>
		          </div>
		        </div>
		        <div class="control-group">
		          <div class="controls">
		            <div class="input-prepend">
		              <span class="add-on">&nbsp;<s:text name="cs.platform.platformKey"/>&nbsp;</span>
		              <input placeholder="<s:text name="cs.platform.platformKey"/>" id="platformKey" type="text">
		            </div>
		          </div>
		        </div>
		        <button class="btn btn-primary" id="addPlatformBtn"><s:text name="cs.common.submit"/></button>
			</div>
			<div id="addPlatformContent">
			</div>
		  </div>
		</div>
	</div>
</body>
<script type="text/javascript">
$(function(){
	$("#selectPlatform").click(function(){
		var platformId = $("#platformId").val() ;
		$.post("/${applicationScope.context}/platform/platformSelect.action",{platformId : platformId},
				function(data, textStatus){
					if(data.result=="true") {
						url = "/${applicationScope.context}/platform/deletePlatform.action";
						typeObj = "Platform";
						typeDelect = "delete";
						var title = ["<s:text name='cs.platform.platformId'/>", "<s:text name='cs.platform.platformName'/>",
						             "<s:text name='cs.platform.platformDesc'/>","<s:text name='cs.platform.platformKey'/>"] ;
						var column = ["id", "platformname", "describle", "platformkey"] ;
						var addTitle = ["<s:text name='com.comm.operator'/>"] ;
						var addColnum = ["<a href='#' onClick='clickDel(this, url, typeObj, typeDelect)'><s:text name='com.comm.delete'/></a>"] ;
						var result = createTable(data.platforms, title, column, addTitle, addColnum) ;
						$("#selectContent").html(result) ;
					}else {
						bootstrapAlert("<s:text name='cs.common.selecterror'/>") ;
					}
				}) ;
	}) ;
	
	$("#addPlatformBtn").click(function(){
		var platformName = $("#platformName").val() ;
		var platformDesc = $("#platformDesc").val() ;
		var platformKey = $("#platformKey").val() ;
		$.post("/${applicationScope.context}/platform/addPlatform.action",
				{platformName : platformName, platformDesc : platformDesc, platformKey : platformKey},
				function(data, textStatus){
					if(data.result=="true") {
						art.dialog.tips("<s:text name='com.comm.addSuccess'/>");
					}else {
						if(isEmptyValue(data.fieldErrors)) {
							bootstrapAlert("<s:text name='cs.common.adderror'/>") ;
						}else {
							bootstrapAlert(data.fieldErrors.platformMessageError) ;
						}
					}
				}) ;
	}) ;
});
</script>
</html>