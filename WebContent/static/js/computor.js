$(function(){
	$("#findByPlatformId").click(function(){
		var platformId = $("#platform").val() ;
		$("#content").html("正在查询，请稍等...") ;
		$.post("cs_computor/findByPlatformId.action",
				{platformId : platformId},
				function(data, textStatus){
					var result = data.result ;
					if(result==1){
						art.dialog.tips("查询失败");
					}else {
						var serverMessage = data.serverMessage ;
						var html = convertList2TableNormal(serverMessage) ;
						$("#content").html(html) ;
					}
				}) ;
	}) ;
}) ;