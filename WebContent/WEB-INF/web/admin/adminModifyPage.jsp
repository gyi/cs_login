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
				<input value="<s:property value="adminContainer.id"/>" placeholder="<s:text name="cs.admin.adminname"/>"
								id="id" type="hidden">
				<div class="control-group">
					<div class="controls">
						<div class="input-prepend">
							<span class="add-on">&nbsp;&nbsp;<s:text
									name="cs.admin.adminname" />&nbsp;&nbsp;
							</span> <input value="<s:property value="adminContainer.adminname"/>" placeholder="<s:text name="cs.admin.adminname"/>"
								id="adminName" type="text">
						</div>
					</div>
				</div>
				<div class="control-group">
					<div class="controls">
						<div class="input-prepend">
							<span class="add-on"><s:text name="cs.admin.security" /></span>
							<select name="security" id="security">
								<option value="<s:property value="0"/>"><s:text
										name="cs.admin.ordinary" /></option>
								<option value="<s:property value="1"/>"><s:text
										name="cs.admin.privilege" /></option>
							</select>
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
			var security = $("#security").val();
			$.post("/${applicationScope.context}/admin/ModifyAdmin.action", {
				id : id,
				adminName : adminName,
				security : security
			}, function(data, textStatus) {
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