var isButton = false;
$(function(){
	$(".appbtn").click(function(){
		//$(".aui_narrow").click();
		isButton = false;
		var action = $(this).attr("action") ;//请求地址
		var title = $(this).attr("title") ;//标题
		var id = $(this).attr("id") ;//id
		var windowTags = art.dialog.list[id];
		//判断是否已经打开
		if(!isEmptyValue(windowTags)){
			windowTags.close();
		}
		if($("#task-content-inner[hidenId=" + id + "]").size()!=0){
			$("#task-content-inner[hidenId=" + id + "]").remove() ;
		}

		//art.dialog.open(action, {id : id,title: title, width:1280, height:782});

		art.dialog.open(action, {id : id,title: title, width:1280, height:782, narrow:false,
			close: function () {
				auiCloseClick(id);
		    }	
		});

		if($("#L" + id).find(".aui_narrow").size()==0){
			var auiClose = $("#L" + id).find(".aui_close") ;
			if(auiClose.length==0){
				//alert(id) ;
				//alert($(".aui_outer[id*=LartDialog]").find(".aui_close").length) ;
				$($(".aui_outer[id*=LartDialog]").find(".aui_close")[0]).attr("id", id);
				$($(".aui_outer[id*=LartDialog]").find(".aui_close")[0]).before('<a class="aui_narrow" href="javascript:;" id=' + id + '>\u005f</a>') ;
				if($(".aui_outer[id*=LartDialog]").find(".aui_close").size()>0){
					$($(".aui_outer[id*=LartDialog]").find(".aui_close")[0]).before('<a class="aui_narrow" href="javascript:;" id=' + id + '>\u005f</a>') ;
					$($(".aui_outer[id*=LartDialog]").find(".aui_title")[0]).before('<img class="aui_narrow_icon" src="../static/img/hoorayos/' + id + '.ico"/>') ;
				}else if($(".aui_outer[id*=LTips]").find(".aui_close")){
					$($(".aui_outer[id*=LTips]").find(".aui_close")[0]).before('<a class="aui_narrow" href="javascript:;" id=' + id + '>\u005f</a>') ;
					$($(".aui_outer[id*=LTips]").find(".aui_title")[0]).before('<img class="aui_narrow_icon" src="../static/img/hoorayos/' + id + '.ico"/>') ;
				}if($(".aui_outer[id*=LConfirm]").find(".aui_close")){
					$($(".aui_outer[id*=LConfirm]").find(".aui_close")[0]).before('<a class="aui_narrow" href="javascript:;" id=' + id + '>\u005f</a>') ;
				}
			}else{
				$($("#L" + id).find(".aui_close")[0]).attr("id", id);
				$($("#L" + id).find(".aui_close")[0]).before('<a class="aui_narrow" href="javascript:;" id=' + id + '>\u005f</a>') ;
				$($("#L" + id).find(".aui_title")[0]).before('<img class="aui_narrow_icon" src="../static/img/hoorayos/' + id + '.ico"/>') ;
			}
		}
		taskbar(id, title);
		$('.task-item').removeClass("task-item-current");
		$('.task-item[title='+title+']').addClass("task-item-current");
	}) ;
	
	$(".aui_outer").live("click", function(){
		if(isButton==true){
			isButton=false;
			return;
		}
		if($(this).is(".aui_narrow")){
			return;
		}
		var id = $(this).attr("id");
		id=id.substring(1,id.Length) ;
		var title = $('.appbtn[id='+id+']').attr('title');
		$('.task-item').removeClass("task-item-current");
		$('.task-item[title='+title+']').addClass("task-item-current");
	});
	
	$(".aui_narrow").live("click",function(){
		isButton = true;
		var id = $(this).attr("id") ;
		var windowTags = art.dialog.list[id];
		if(isEmptyValue(windowTags)||windowTags.title()){
			$(".aui_state_focus").css("display","none") ;
			$(".aui_state_focus").attr("id", "LartDialog"+new String(new Date().getTime())) ;
			$(".aui_state_focus").removeClass("aui_state_focus") ;
			return ;
		}
		var title = $(windowTags.title()).html() ;
		if(isEmptyValue($("#task-content-inner[hidenId=" + id + "]"))||$("#task-content-inner[hidenId=" + id + "]").size()==0){
			if(isEmptyValue(id))
				return ;
			if(isEmptyValue(windowTags)){
				$(".aui_state_focus").css("display","none") ;
				$(".aui_state_focus").attr("id", "LartDialog"+new String(new Date().getTime())) ;
				$(".aui_state_focus").removeClass("aui_state_focus") ;
				return ;
			}
			taskbar(id, title);
		}else{
			$('.task-item').removeClass("task-item-current");
		}
		
		windowTags.zIndex();
		windowTags.hide() ;
	}) ;
	
	$(".task-inner").live("click", function(){
		isButton = false;
		var id = $(this).attr("hidenId") ;
		if(isEmptyValue(id))
			return;
		var windowTags = art.dialog.list[id];
		windowTags.zIndex();
		windowTags.show() ;
		$(".task-item").removeClass("task-item-current");
		$(this).children('.task-item').addClass("task-item-current");
		//$(this).remove();
	}) ;
	
});

function auiCloseClick(id){
	isButton = true;
	if(!isEmptyValue($("#task-content-inner[hidenId=" + id + "]"))&&$("#task-content-inner[hidenId=" + id + "]").size()!=0){
		$("#task-content-inner[hidenId=" + id + "]").remove();
	}
}

function taskbar(id, title) {
	var hidden_temp = '<div id="task-content-inner" class="task-inner" hidenId="'+id+'" style="width: 114px; margin-left: 0px; margin-right: 0px;">'
			+ '<a id="t_hoorayos-yysc" class="task-item" title="'+title+'" appid="hoorayos-yysc" realappid="hoorayos-yysc" type="window">'
			+ '<div class="task-item-icon"><img src="../static/img/hoorayos/'+id+'.ico" alt="'+title+'"/></div>'
			+ '<div class="task-item-txt">'+title+'</div>'
			+ '</a>'
			+ '</div>';
	$("#task-content").prepend(hidden_temp);
}