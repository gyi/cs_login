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
		<ul class="nav nav-tabs" id="myTab">
			<li class="active"><a href="#server" data-toggle="tab"><s:text
						name="cs.player.select" /></a></li>
		</ul>
		<div class="tab-content">
			<div class="tab-pane active" id="server">
				<div class="well">
					<div class="control-group">
						<div class="controls">
							<div class="input-prepend">
								<span class="add-on"><s:text name="com.comm.selectType" /></span>
								<select id="type">
									<option value="1"><s:text name="cs.player.playerId" /></option>
									<option value="2"><s:text name="cs.player.userId" /></option>
								</select>
							</div>
						</div>
					</div>
					<div class="control-group">
						<div class="controls">
							<div class="input-prepend">
								<span class="add-on"><s:text
										name="cs.player.playerMessage" /></span> <input
									placeholder="<s:text name="cs.player.playerMessage"/>"
									id="playerMessage" type="text">
							</div>
						</div>
					</div>
					<button class="btn btn-primary" id="playerSelect">
						<s:text name="cs.common.select" />
					</button>
				</div>
				<div id="selectContent"></div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	$(function() {
		$("#playerSelect")
			.click(
				function() {
					var type = $("#type").val();
					var playerMessage = $("#playerMessage").val();
					$.post(
						"/${applicationScope.context}/player/playerSelect.action",
						{
							type : type,
							playerMessage : playerMessage
						},
						function(data, textStatus) {
							if (data.result == "true") {
								var title = [
										"<s:text name='cs.player.playerId'/>",
										"<s:text name='cs.player.userId'/>",
										"<s:text name='cs.platform.platformId'/>",
										"<s:text name='cs.player.lastLoginTime'/>" ];
								var column = [ "playerid",
										"userid",
										"platformid",
										"lastlogintime" ];
								var result = createTable(
										data.players,
										title, column);
								$("#selectContent").html(
										result);
							} else {
								if (isEmptyValue(data.fieldErrors)) {
									bootstrapAlert("<s:text name='cs.common.selecterror'/>");
								} else {
									bootstrapAlert(data.fieldErrors.playerMessageError);
								}
							}
						});
				});
	});
</script>
</html>