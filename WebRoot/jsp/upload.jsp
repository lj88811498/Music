<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <title>文件上传</title>
    <link rel="stylesheet" href="test.css" type="text/css" />
    <%@ include file="base_url.jsp"%>
</head>
<body>

<style type="text/css">	
.overlay{position:fixed;top:0;right:0;bottom:0;left:0;z-index:998;width:100%;height:100%;_padding:0 20px 0 0;background:#f6f4f5;display:none;}
.showbox{position:fixed;top:0;left:50%;z-index:9999;opacity:0;filter:alpha(opacity=0);margin-left:-80px;}
#AjaxLoading{border:1px solid #8CBEDA;color:#37a;font-size:12px;font-weight:bold;}
#AjaxLoading div.loadingWord{width:180px;height:50px;line-height:50px;border:2px solid #D6E7F2;background:#fff;}
#AjaxLoading img{margin:10px 15px;float:left;display:inline;}
</style>

<h1><a href="<%=basePath %>/index.do?page=1">回首页</a></h1>

<h2 style="color:red"><% Object info = request.getSession().getAttribute("info");
if (!"".endsWith(info + "") || info != "null" || info != null) {
	out.println(info + "");
}

%>

</h2>

<form action="uploadFile.do" method="post" enctype="multipart/form-data" id="form">
    <table>
        <tbody >
        <tr>
            <td>上传文件(可选择多个进行批量上传~~~格式必须全部正确):</td>
            <td style="padding-left: 10px;">
                <!-- <input type="file" name="file" id="file" multiple="multiple"> -->
                <input id="fileToUpload" type="file" size="45" name="fileToUpload" class="input"  multiple>
            </td>
            <td style="padding-left: 80px;">
                
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <span style="color:red">*上传文件格式为mp3等音乐格式文件!</span>
                &nbsp;&nbsp;
            </td>
    　　　 </tr>
    	<tr>
    		<td>上传者（昵称或姓名~~~尽量填写）:<br/></td>
    		<td><input type="text" name="author" id="author"/></td>
    		
    	</tr>
    	<tr>
    		<td><button type="button" class="btn btn-primary btn-q btn-outline fa fa-upload easyui-validatebox action" data-options="required:true" onclick="ajaxFileUpload()">上传</button></td>
    	</tr>
    	<img id="viewImg0">
		<img id="viewImg1">
		<img id="viewImg2">
　　</tbody>
</table></form>
<!-- 弹出层 -->
<div class="overlay"></div>

<div id="AjaxLoading" class="showbox">
	<div class="loadingWord"><img src="<%=basePath%>/jsp/images/waiting.gif">上传中，请稍候...</div>
</div>

<div style="height:1200px;">
	
</div>
<!-- 弹出层  end-->
</body>
<script type="text/javascript" src="jsp/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="jsp/js/ajaxfileupload.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	var h = $(document).height();
	$(".overlay").css({"height": h });	
	
	$(".action").click(function(){
	
		$(".overlay").css({'display':'block','opacity':'0.8'});
		
		$(".showbox").stop(true).animate({'margin-top':'300px','opacity':'1'},1000);
		
		
		/*
		setTimeout(function(){
			
			$(".showbox").stop(true).animate({'margin-top':'250px','opacity':'0'},1000);
			
			$(".overlay").css({'display':'none','opacity':'0'});
			
		},18000);
		*/
	});
	
});

	function ajaxFileUpload() {
		$.ajaxFileUpload({
			url : 'uploadMp3.do?author=' + $("#author").val(),
			//url : 'uploadFile.do',
			//secureuri : false,
			fileElementId : 'fileToUpload',
			dataType : 'json',
			//data : {author : $("#author").val()},
			success : function(data) {
				history.go(0);
			},
			error : function(data, status, e) {
				//alert('上传出错');
				history.go(0);
			}
		})

		return false;

	}
</script>
</html>