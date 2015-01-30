// 在进行操作之前进行提示
function showPrompt(prompt, url, json) {
	var tip = "";
	var confirm = "";
	var successed = "";
	var fail = "";
	var cancel = gccp_common_cancel;
	if (prompt == "add") {
		tip = gccp_common_isadd;
		confirm = gccp_common_adding;
		successed = gccp_common_added;
		fail = gccp_common_adderror;
	}
	if (prompt == "modify") {
		tip = gccp_common_ismodify;
		confirm = gccp_common_modifying;
		successed = gccp_common_modifyed;
		fail = gccp_common_modifyerror;
	}
	if (prompt == "delete") {
		tip = gccp_common_isdelete;
		confirm = gccp_common_deleteing;
		successed = gccp_common_deleteed;
		fail = gccp_common_deleteerror;
	}
	if (prompt == "validateConn") {
		tip = gccp_common_isvalidateConn;
		confirm = gccp_common_validateConning;
		successed = gccp_common_validateConned;
		fail = gccp_common_validateConnerror;
	}
	art.dialog.confirm(tip, function() {
		art.dialog.tips(confirm);
		onAjax(url, json, successed, fail);
		
	}, function() {
		art.dialog.tips(cancel);
	});
}

// ajax提交action，里面带提示的
function onAjax(url, json, successed, fail) {
	$.ajax({url:url, data:json, 
		success:function(data, textStatus) {
			
			if(data.result!=null&&data.result!=''){
				if(data.result=='success'){
					art.dialog.tips(successed);
				}
				else{
					art.dialog.tips(fail);
				}
			}
			else{
				art.dialog.tips(successed);
				$("#content").html(data);
			}
		},
		error:function(data, textStatus){
			art.dialog.tips(fail);
		}
	});
}

//ajax提交action,里面不带提示的
function onPost(url, json) {
	$.post(url, json, function(data, textStatus) {
		if(data.result!=null&&data.result!=''){

		}
		else{
			$("#content").html(data);
		}
	});
}

/**
 * 点击按钮触发事件
 * 参数：isPrompt 是否需要提示，prompt 提示类型，url 前往地址，json 传参
 */
function onClickBottom(isPrompt, prompt, url, json) {
	if (isPrompt != '0') {
		showPrompt(prompt, url, json);
	} else {
		onPost(url, json);
	}

	// onPost(url);

}