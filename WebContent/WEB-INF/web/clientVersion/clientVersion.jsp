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
		  <li class="active"><a href="#versionSelect" data-toggle="tab"><s:text name="cs.clientversion.version.select"/></a></li>
		  <li><a href="#addVersion" data-toggle="tab"><s:text name="cs.clientversion.version.insert"/></a></li>
		</ul>
		<div class="tab-content">
		  <div class="tab-pane active" id="versionSelect">
		  	<div class="well">
		  		 <div class="control-group">
			          <div class="controls">
					     <button class="btn btn-primary" id="selectVersion"><s:text name="cs.common.select"/></button>
			          </div>
		         </div>
			</div>
			<div id="selectContent">
			</div>
		  </div>
		  <div class="tab-pane" id="addVersion">
		  	<div class="well">
		  		 <div class="control-group">
		          <div class="controls">
		            <div class="input-prepend">
		              <span class="add-on">&nbsp;&nbsp;<s:text name="cs.clentversion.version"/>&nbsp;&nbsp;</span>
		              <input placeholder="<s:text name="cs.clentversion.version"/>" id="version" type="text">
		            </div>
		          </div>
		         </div>
		        <div class="control-group">
		          <div class="controls">
		            <div class="input-prepend">
		              <span class="add-on"><s:text name="cs.clientversion.version.isMust"/></span>
		              <select id="isMust">
		              	<option value="0"><s:text name="com.comm.yes"/></option>
		              	<option value="1"><s:text name="com.comm.no"/></option>
		              </select>
		            </div>
		          </div>
		        </div>
		        <button class="btn btn-primary" id="addVersionBtn"><s:text name="cs.common.submit"/></button>
			</div>
			<div id="addVersionContent">
			</div>
		  </div>
		</div>
	</div>
</body>
<script type="text/javascript">
$(function(){
	$("#selectVersion").click(function(){
		$.post("/${applicationScope.context}/clientversion/versionSelect.action",{},
				function(data, textStatus){
					if(data.result=="success") {
						var title = ["<s:text name='cs.clentversion.version'/>",
						             "<s:text name='cs.clientversion.version.isMust'/>",
						             "<s:text name='com.comm.addtime'/>"] ;
						var column = ["version", "ismust", "addtime"] ;
						var addTitle = ["<s:text name='com.comm.operator'/>"] ;
						var addColnum = ["<a href='#' onClick='clickDelVersion(this, 1)'><s:text name='com.comm.delete'/></a>"] ;
						var result = createTable(data.clientVersions, title, column, addTitle, addColnum) ;
						$("#selectContent").html(result) ;
					}else {
						bootstrapAlert("<s:text name='cs.common.selecterror'/>") ;
					}
				}) ;
	}) ;
	
	$("#addVersionBtn").click(function(){
		var version = $("#version").val() ;
		var isMust = $("#isMust").val() ;
		$.post("/${applicationScope.context}/clientversion/addVersion.action",
				{version : version, isMust : isMust},
				function(data, textStatus){
					if(data.result=="success") {
						art.dialog.tips("<s:text name='com.comm.addSuccess'/>");
					}else {
						if(isEmptyValue(data.fieldErrors)) {
							bootstrapAlert("<s:text name='cs.common.adderror'/>") ;
						}else {
							bootstrapAlert(data.fieldErrors.versionIsExsist) ;
						}
					}
				}) ;
	}) ;
});

function clickDelVersion(obj, colNum) {
	var col = $(obj).parents("tr") ;
	var p = $(col).children()[0] ;
	var version = $(p).html() ;
	$.post("/${applicationScope.context}/clientversion/deleteVersion.action",
			{version : version},
			function(data, textStatus){
				if(data.result=="success") {
					$(col).remove() ;
				}else {
					if(isEmptyValue(data.fieldErrors)) {
						bootstrapAlert("<s:text name='com.comm.deletefalse'/>") ;
					}else {
						if(isEmptyValue(data.fieldErrors.versionIsNull)){
							bootstrapAlert(data.fieldErrors.versionIsNotExsist) ;
						}else {
							bootstrapAlert(data.fieldErrors.versionIsNull) ;
						}
					}
				}
			});
}
</script>
</html>