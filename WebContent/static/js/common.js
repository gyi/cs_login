function createTable(data, title, colnum, addTitle, addColnum) {
	if (title.length != colnum.length) {
		return "";
	} else {
		if(addTitle!=undefined&&addColnum!=undefined){
			if(addTitle.length != addColnum.length) {
				return "" ;
			}
		}
		var table = "<table class='table table-bordered  table-striped table-hover' ><thead><tr>";
		for (var i = 0; i < title.length; i++) {
			table = table + "<th>" + title[i] + "</th>";
		}
		if(addTitle!=undefined){
			for(var i = 0; i < addTitle.length; i++) {
				table = table + "<th>" + addTitle[i] + "</th>";
			}
		}
		table += "</tr></thead><tbody>";
		for (var i = 0; i < data.length; i++) {
			table += "<tr>";
			var res = data[i];
			for (var j = 0; j < colnum.length; j++) {
				var value = typeof (res[colnum[j]]) == "undefined" ? ""
						: res[colnum[j]];
				table += "<td>" + value + "</td>";
			}
			if(addColnum!=undefined){
				for (var j = 0; j < addColnum.length; j++) {
					var value = addColnum[j] ;
					table += "<td>" + value + "</td>";
				}
			}
			table += "</tr>";
		}

		if (data.length == 0) {
			table += "<tr> <td colspan=" + title.length + ">Empty !</td></tr>";
		}

		table += "</tbody></table>";
		return table;
	}
}

function bootstrapAlert(message){
	var div = "<div style='width:300px;margin:0 0 0 0;left:40%;top:30%;' class='modal hide' id='confirmModal'><div class='modal-body'><span><div style='text-align:center; min-height:30px; _height:30px; padding-top:20px'>"+message+"</div></span></div><div style='padding:7px;' class='modal-footer'><a href='javascript:void(0);' class='btn' id='no'>确定</a></div></div>";
	$("#main").append(div);
	$("#confirmModal").modal({
		backdrop:'static'
	});
	$("#no").click(function(){
		$('#confirmModal').modal('hide');
		$('#confirmModal').remove();
	});
};

$(document).ajaxStart(function() {
	self.loading();
});

var isEmptyValue = function(value) {

	var type;
	if (value == null) { // 等同于 value === undefined || value === null
		return true;
	}
	type = Object.prototype.toString.call(value).slice(8, -1);
	switch (type) {
	case 'String':
		return !$.trim(value);
	case 'Array':
		return !value.length;
	case 'Object':
		return $.isEmptyObject(value); // 普通对象使用 for...in 判断，有 key 即为 false
	default:
		return false; // 其他对象均视作非空
	}
};

$(document).ajaxComplete(function(event, request, settings) {
	var list = art.dialog.list['loading'];
	if (!isEmptyValue(list)) {
		list.close();
	}
});

self.loading = function() {
	art.dialog({
		id : "loading",
		padding : 0,
		width : 200,
		height : 40,
		content : '<p>' + gccp_common_loading + '...</p>',
		lock : true,
		cancel : false
	});
};

//在进行操作之前进行提示
function showPrompt(typeObj, prompt, url, json) {
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
		onAjax(url, json, typeObj, successed, fail);
		
	}, function() {
		art.dialog.tips(cancel);
	});
}

// ajax提交action，里面带提示的
function onAjax(url, json, typeObj, successed, fail) {
	$.ajax({url:url, data:json, 
		success:function(data, textStatus) {
			
			if(data.result!=null&&data.result!=''){
				if(data.result=='true'){
					art.dialog.tips(successed);
					$('#select'+typeObj).click();
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
function onClickBottom(isPrompt, typeObj, prompt, url, json) {
	if (isPrompt != '0') {
		showPrompt(typeObj, prompt, url, json);
	} else {
		onPost(url, json);
	}
}

function clickDel(obj, url, typeObj, typeButton) {
	onClickBottom(1, typeObj, typeButton, url, {
				id : obj.parentNode.parentNode.cells[0].innerText
			});
};

function clickModify(obj, id, urlModify, title){
	$.post(urlModify, 
			{id: obj.parentNode.parentNode.cells[0].innerText}, 
			function(data, textStatus) {
				var dialog = art.dialog({id: id, title: title, content: data,lock : true});
			});
	var list = art.dialog.list;
	for (var i in list) {
	    list[i].close();
	};
}

