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
		<ul class="nav nav-tabs" id="myTab">
			<li class="active" id="selectAdmin"><a href="#adminSelect"
				data-toggle="tab"><s:text name="cs.admin.select" /></a></li>
			<li><a href="#addAdmin" data-toggle="tab"><s:text
						name="cs.admin.addAdmin" /></a></li>
		</ul>
		<div class="tab-content">
			<div class="tab-pane active" id="adminSelect">
				<div id="selectContent"></div>
			</div>
			<div class="tab-pane" id="addAdmin">
				<div class="well">
					<div class="control-group">
						<div class="controls">
							<div class="input-prepend">
								<span class="add-on">&nbsp;&nbsp;<s:text
										name="cs.admin.adminname" />&nbsp;&nbsp;
								</span> <input placeholder="<s:text name="cs.admin.adminname"/>"
									id="adminName" type="text">
							</div>
						</div>
					</div>
					<div class="control-group">
						<div class="controls">
							<div class="input-prepend">
								<span class="add-on">&nbsp;&nbsp;<s:text
										name="cs.admin.password" />&nbsp;&nbsp;
								</span> <input placeholder="<s:text name="cs.admin.password"/>"
									id="password" type="password">
							</div>
						</div>
					</div>
					<div class="control-group">
						<div class="controls">
							<div class="input-prepend">
								<span class="add-on">&nbsp;&nbsp;<s:text
										name="cs.admin.confirmpassword" />&nbsp;&nbsp;
								</span> <input placeholder="<s:text name="cs.admin.confirmpassword"/>"
									id="confirmpassword" type="password">
							</div>
						</div>
					</div>
					<div class="control-group">
						<div class="controls">
							<div class="input-prepend">
								<span class="add-on"><s:text name="cs.admin.security" /></span>
								<select name="security" id="security">
									<option value="<s:property value="0"/>"><s:text name="cs.admin.ordinary" /></option>
									<option value="<s:property value="1"/>"><s:text name="cs.admin.privilege" /></option>
								</select>
							</div>
						</div>
					</div>
					<button class="btn btn-primary" id="addAdminBtn">
						<s:text name="cs.common.submit" />
					</button>
				</div>
				<div id="addAdminContent"></div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	$(function() {
		
		$("#selectAdmin").click(function() {
			urlDelect = "/${applicationScope.context}/admin/DeleteAdmin.action";
			urlModify = "/${applicationScope.context}/admin/ModifyAdminPage.action";
			urlModifyPassword = "/${applicationScope.context}/admin/ModifyAdminPasswordPage.action"

			typeObj = "Admin";
			typeDelect = "delete";
			typeModify = "modify";
			clickIdModify = "modifyUser";
			titleModify = "<s:text name='cs.admin.modifyAdmin'/>";
			clickIdModifyPassword = "modifyUserPassword";
			titleModifyPassword = "<s:text name='com.comm.modifypassword'/>";
			$.post("/${applicationScope.context}/admin/AdminSelect.action", function(data, textStatus) {
				if (data.result == "true") {
					var title = [ "<s:text name='cs.admin.adminid'/>",
									"<s:text name='cs.admin.adminname'/>",
									"<s:text name='cs.admin.security'/>" ];
							var column = [ "id", "adminname", "security" ];
							var addTitle = [ "<s:text name='com.comm.operator'/>" ];
							var addColnum = [ "<a href='#' onClick='clickDel(this, urlDelect, typeObj, typeDelect)'><s:text name='com.comm.delete'/></a>&nbsp;"
									+ "<a href='#' onClick='clickModify(this, clickIdModify, urlModify, titleModify)'><s:text name='com.comm.modify'/></a>&nbsp;"
									+ "<a href='#' onClick='clickModify(this, clickIdModifyPassword, urlModifyPassword, titleModifyPassword)'><s:text name='com.comm.modifypassword'/></a>&nbsp;"];
							var result = createTable(data.adminsContainer, title, column, addTitle,
									addColnum);
							$("#selectContent").html(result);
				}else {
					bootstrapAlert("<s:text name='cs.common.selecterror'/>") ;
				}
			});
		});

		$("#addAdminBtn").click(function() {
			var adminName = $("#adminName").val();
			var password = $("#password").val();
			var confirmpassword = $("#confirmpassword").val();
			var security = $("#security").val();
			$.post("/${applicationScope.context}/admin/AddAdmin.action", {
				adminName : adminName,
				password : password,
				confirmpassword : confirmpassword,
				security : security
			}, function(data, textStatus) {
				if (data.result == "true") {
					art.dialog.tips("<s:text name='com.comm.addSuccess'/>");
				} else {
					if (isEmptyValue(data.fieldErrors)) {
						bootstrapAlert("<s:text name='cs.common.adderror'/>");
					} else {
						bootstrapAlert(data.fieldErrors.adminMessageError);
					}
				}
			});
		});
		
		$("#selectAdmin").click();

	});
	
</script>
</html>