$(function(){
	
	$('.input-daterange').datepicker({
		language: "zh-CN",
    	autoclose: true,
    	todayBtn: "linked"
	});
	
	$('.input-daterange input').datepicker("setDate",new Date);
	
	$("#click_server").click(function(){
		$.post("cs_def/fileVersionPage.action",
				{},
				function(data,textStatus){
					var table = convertList2Table(data.versionslist) ;
					$("#content_server").html(table) ;
				}) ;
	}) ;
	
	$(".icon").live('mouseover',function(){
		$(this).addClass("icon-white") ;
	});
	
	$(".icon").live('mouseout',function(){
		$(this).removeClass("icon-white") ;
	});
	
	$(".icon-arrow-up").live('click', function(){
		var version = $(this).attr("props") ;
		var right = $(this).attr("right") ;
		var url ;
		if(right=="excel"){
			url = "cs_upload/uploadExcel.action" ;
		}else {
			url = "cs_upload/uploadXml.action" ;
		}
		art.dialog({
			 title : '文件上传' ,
			 lock: true,
			 opacity: 0.5,
			 drag: false,
			 height: 150,
			 content: '<table><tr><td style="height:50px">版本号：</td><td><input id="version" value="' + version + '" disabled/><span style="font-size:12px;"></span></td></tr><tr><td>文&nbsp;&nbsp;件</td><td><input type="file" id="file" name="file"></td></tr></table>',
			 ok: function () {
				 $.ajaxFileUpload
			        (
			            {
			            	//用于文件上传的服务器端请求地址
			                url:url,
			                //一般设置为false
			                secureuri:false,
			                data: {version : version, right : right},
			              	//文件上传空间的id属性  <input type="file" id="file" name="file" />
			                fileElementId:'file',
			              	//返回值类型 一般设置为json
			                dataType: 'json',
			              	//服务器成功响应处理函数
			                success: function (data, status){
			                    //从服务器返回的json中取出message中的数据,其中message为在struts2中定义的成员变量
			                	art.dialog.tips(data.result);
			                },
			                error: function (data, status, e){
			                	art.dialog.tips(data.result);
			                }
			            }
			        );
		     }
		});
	}) ;
	
	$(".icon-arrow-down").live('click', function(){
		var fileName = $(this).attr("props") ;
		var right = $(this).attr("right") ;
		var form = $("<form>");
        form.attr('style', 'display:none');
        form.attr('target', '');
        form.attr('method', 'post');
        form.attr('action', "cs_def/downloadFile.action");
        var input1 = $('<input>');
        input1.attr('type', 'hidden');
        input1.attr('name', 'fileName');
        input1.attr('value', fileName);
        var inputRight = $('<input>');
        inputRight.attr('type', 'hidden');
        inputRight.attr('name', 'right');
        inputRight.attr('value', right);
        $('body').append(form);
        form.append(input1);
        form.append(inputRight);
        form.submit();
        form.remove();
	}) ;
	
	$("#hisxmlselect").click(function(){
		var start = $("#start").val() ;
		var end = $("#end").val() ;
		
		$.post("cs_def/hisXmlSelect.action",
				{start : start, end : end},
				function(data, textStatus){
					if(data.success==true) {
						$("#history #content_server").html(convertHisXmlList2Table(data.versions)) ;
					}else {
						art.dialog.tips(data.result);
					}
				});
	}) ; 
}) ;