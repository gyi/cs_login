$(function(){
	$("#xmlbtn").live('click', function(){
		var clazzType = $("#clazzType").val();
		
		art.dialog({
		    content: '上传后配置将改变，确认上传？？？',
		    ok: function () {
		    	$.ajaxFileUpload({
		        	//用于文件上传的服务器端请求地址
		            url:'cs_upload/uploadXml.action',
		            //一般设置为false
		            secureuri:false,
		            data: {clazzType: clazzType},
		          	//文件上传空间的id属性  <input type="file" id="file" name="file" />
		            fileElementId:'xmlfile',
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
		        });
		    },
		    cancelVal: '关闭',
		    cancel: true //为true等价于function(){}
		});
	}) ;
	
	$("#excelbtn").live('click', function(){
		var version = $("#excelversion").val() ;
		$.ajaxFileUpload({
        	//用于文件上传的服务器端请求地址
            url:'cs_upload/uploadExcel.action',
            //一般设置为false
            secureuri:false,
            data: {version : version},
          	//文件上传空间的id属性  <input type="file" id="file" name="file" />
            fileElementId:'excelfile',
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
        });
	}) ;
}) ;