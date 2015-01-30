<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>a</title>
</head>
<jsp:include page="../include.jsp"/>
<body>
	<div id="main" class="container-fluid">
		<div class="tab-content">
			<div class="well">
				<input value="<s:property value="adminContainer.id"/>"
					placeholder="<s:text name="cs.admin.adminname"/>" id="id"
					type="hidden">
				<div class="control-group">
					<div class="controls">
						<div class="input-prepend">
							<span class="add-on">&nbsp;&nbsp;<s:text
									name="cs.admin.adminname" />&nbsp;&nbsp;
							</span> <span class="add-on" id="adminName">&nbsp;&nbsp;<s:property
									value="adminContainer.adminname" />&nbsp;&nbsp;
							</span>
						</div>
					</div>
				</div>
				<div class="control-group">
					<div class="controls">
						<div class="input-prepend">
							<span class="add-on">&nbsp;&nbsp;<s:text
									name="cs.admin.oldpassword" />&nbsp;&nbsp;
							</span> <input placeholder="<s:text name="cs.admin.oldpassword"/>"
								id="oldpassword" type="password">
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
				<button class="btn btn-primary" id="modifyAdminBtn">
					<s:text name="cs.common.submit" />
				</button>
			</div>
			<div id="addAdminContent"></div>
		</div>
	</div>
</body>
<script type="text/javascript">
	$(function() {
		security = ${adminContainer.security};

		$("#security ").val(security);

		$("#modifyAdminBtn").click(function() {
			var id = $("#id").val();
			var adminName = $("#adminName").val();
			var oldpassword = $("#oldpassword").val();
			var confirmPassword = $("#confirmpassword").val();
			var password = $("#password").val();
			$.post(
				"/${applicationScope.context}/admin/ModifyAdminPassword.action",
				{
					id : id,
					adminName : adminName,
					password : password,
					oldpassword : oldpassword,
					confirmpassword : confirmPassword
				},
				function(data, textStatus) {
					if (data.result == "true") {
						art.dialog.tips("<s:text name='com.comm.addSuccess'/>");
						dialog.close();
						$("#selectAdmin").click();
					} else {
						if (isEmptyValue(data.fieldErrors)) {
							bootstrapAlert("<s:text name='cs.common.adderror'/>");
						} else {
							bootstrapAlert(data.fieldErrors.adminMessageError);
						}
					}
				});
		});

	});
</script>
</html>