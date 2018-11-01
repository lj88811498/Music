<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>欢迎来到抖音音乐集合  - Monkey</title>
		<meta name="Keywords" content="关键词,关键词">
		<meta name="description" content="">
		<%@ include file="base_url.jsp"%>
		<style type="text/css">
			*{margin:0;padding:0;}
			html{height:100%;}
			body{
			height:100%;
			 background:url("jsp/images/123.jpg") no-repeat top center; 
			 background-size:100% 100%;
			font-size:12px;font-family:"微软雅黑";}
			
			.page,.searchName{
			   color:#9aaf4c;
			}
			.searchName{
			font-size:16px;
			}
			.page a,.search{
			width: 30px;
		    height: 30px;
		    border: 1px solid #dcdcdc;
		    padding: 5px;
		    background: #03A9F4;
		    border-radius: 5px;
		    font-size: 14px;
		    color: #fff;}
			#gopage{
			    width: 25px;
               height: 25px;
                border-radius: 5px;
             border: 1px solid #dcdcdc;
             outline: none;
			}
			a{
			    text-decoration: none;
			    }
			img{border:0;}
			.main {font-size:18px;text-align:center;color:red;}
			.main a{font-size:14px;color: #E91E63;}
			/*music start*/
			.music1{
			width:960px;
			margin:50px auto;
			heigth:50px;
			    text-align: center;
			}
			.music1 input{
			    width: 300px;
			    padding-left: 10px;
			    height: 30px;
			    border-radius: 5px;
			    outline: none;
			    border: 1px solid #dcdcdc;
			}
			.music{width:960px;height:450px;margin:100px auto;position:relative;}
			.music ul li{list-style:none;position:absolute;}
			.music ul li span,.music ul li a{color: #9aaf4c!important; font-size: 16px;}
			.music ul .m_1{left:10px;top:30px; color: #E91E63;}
			
			.music ul .m_2{left:170px;top:88px;}
			.music ul .m_2 a img{border-radius:50px;}
			.music ul .m_3{left:336px;top:25px;}
			.music ul .m_3 a img{border-radius:62px;}
			.music ul .m_4{left:496px;top:108px;}
			.music ul .m_4 a img{border-radius:30px;}
			.music ul .m_5{left:662px;top:47px;}
			.music ul .m_5 a img{border-radius:45px;}
			
			.music ul .m_6{left:10px;top:280px;}
			.music ul .m_6 a img{border-radius:30px;}
			.music ul .m_7{left:170px;top:213px;}
			.music ul .m_7 a img{border-radius:50px;}
			.music ul .m_8{left:336px;top:303px;}
			.music ul .m_8 a img{border-radius:62px;}
			.music ul .m_9{left:496px;top:230px;}
			.music ul .m_9 a img{border-radius:30px;}
			.music ul .m_10{left:662px;top:329px;}
			.music ul .m_10 a img{border-radius:45px;}
			
			/*end music*/

			/*player start*/
			.player{width:700px;height:115px;background:#000;position:absolute;bottom:20px;left:0;box-shadow: 0px 0px 15px 0px #000;}
			.player .p_pic img{float:left;margin-top:12px;}
			.player .p_pic p{font-size:20px;color:#FFF;float:left;line-height:115px;padding-left:20px;width:120px}
			.player .p_pic .p_title_r{padding-left:80px}
			
			.player .p_btn{width:180px;height:52px;float:left;margin: 30px 0 0 60px;}
			.player .p_btn a{display:block;background:url("jsp/images/player_bg.png") no-repeat;float:left;margin: 5px 10px; }
			.player .p_btn .b_pre{width:36px;height:28px;background-position:-68px 0;margin-top: 12px;}
			.player .p_btn .b_pre:hover{background-position:-68px -32px;}
			.player .p_btn .b_play{width:42px;height:42px;background-position:-105px 0;}
			.player .p_btn .b_play:hover{background-position:-105px -42px;}

			.player .p_btn .b_pause{width:42px;height:42px;background-position:-291px -88px;display:none;}
			.player .p_btn .b_pause:hover{background-position:-333px -88px;}

			.player .p_btn .b_next{width:36px;height:28px;background-position:-147px 0;margin-top: 12px;}
			.player .p_btn .b_next:hover{background-position:-147px -32px;}
			.p_packup{width:22px;height:115px;background:url("jsp/images/player_bg.png") no-repeat;float:right;cursor: pointer;}
			/*end player*/
			
			/* keke：关键帧名字  2s：完成时间   infinite：无限循环  linear:匀速*/
		 	
			.xz{-webkit-animation:keke 2s infinite linear;animation:keke 2s infinite linear;}
			@-webkit-keyframes keke{
				from{
					transform:rotate(0deg);
					-webkit-transform:rotate(0deg)
				}
				to{
					transform:rotate(360deg);
					-webkit-transform:rotate(360deg);
				}
			}

		</style>
			
	</head>
<body>
	<p class="main">欢迎来到抖音音乐集合  - Monkey    <a href="upload.do">我要共享我的歌曲</a></p>
	
	
	
	
	<!--music start-->
	<div class="music1">
	<p>
	<input type="text" name="name" id="name"/>
		<a href="javascript:search();" class="search">搜索</a>
		<a href="javascript:clear();" class="search">清空</a>
		
	</p>
	</div>
	
	<div class="music">
	
	</div>
    <div class="music1">
    <p style="top=330px;left:80%;" class="page">
	 	<a id="first" href="javascript:goPage(1);">首页</a>
	 	<a id="prefix" href="javascript:goPage(2);"> 上一页</a>
	 	<a id="next" href="javascript:goPage(3);"> 下一页</a>
	 	<a id="end" href="javascript:goPage(4);"> 末页</a>
	 	当前第<span id="currentPage"></span>页，共 <span id="totalPage"></span> 页
	 	跳转到第
	 	<input type="text" name="gopage" id="gopage"/>
	 	页
	 	<a href="javascript:gotoPage();" class="go">GO</a>
	</p>
    </div>
	<!--end music-->

	<!--player start-->
	<div class="player">
		<div class="p_pic">
			<img src="jsp/images/41.jpg" alt="" width="90" height="90" />
			<p class="p_title" style="width=250px;"><marquee><span id="p_title">夜空最亮的星.mp3</span></marquee></p>
			<p class="p_title_r" style="left:50px;width=250px;">Monkey123</p>
		</div>
		<div class="p_btn">
			<a href="javascript:;" class="b_pre"></a>
			<a href="javascript:;" class="b_play"></a>
			<a href="javascript:;" class="b_pause"></a>
			<a href="javascript:;" class="b_next"></a>
		</div>
		<div class="p_packup"></div>
		
		<div id="audiobox" style="position:relative;">
			<audio autoplay controls>
				<source id="aud" src="<%=basePath%>/jsp/mp3/yk.mp3">
			</audio>
		</div>
	</div>
	<!--end player-->


<!--引入jQuery官方类库-->
<script type="text/javascript" src="jsp/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="jsp/js/jquery.min.js"></script>
<script type="text/javascript" src="jsp/js/ajaxupload.js"></script>
	<p>
		<span class="searchName">PS: 由于服务器性能一般，播放歌曲时请耐心等待几秒钟~~~</span>
	</p>
<script type="text/javascript">
	var currentPage = 1;
	var totalPage = 1;
	var size = 1;
	var index = 1;
	var num = 0;
	var lastRunTime=Date.now();
	$(function(){
		
		$(".p_packup").click(function(){
			var width = $(".player").width() -22;
			var left = $(".player").offset().left;
			var n = 1;
			if(left < 0){
				n = 0;
			}
			$(".player").animate({left:-width*n},1000);
		});


		// 初始化播放器
		var audioDom = initMp3("<%=basePath%>/jsp/mp3/yk.mp3");
		//var audioDom = createMap3("d:/mp3/DJ马哥 - C哩C哩.mp3");
		//console.log(num);
		//audioDom.play();
		show(1);

	});

	// 创建一个播放器
	function initMp3(src){
		// 创建一个mp3播放器
		var audioDom = document.createElement("audio");
		// 设置播放器的地址
		audioDom.src = src;
		audioDom.id  ="aud";
		//去掉下载原生按钮
		audioDom.controlsList="nodownload";
		//控制台
		audioDom.controls = "controls";
		//audioDom.autoplay = "autoplay";
		//audioDom.loop = "loop";
		audioDom.style = "position:absolute;left:170px;";
		// 把播放器放入div容器中
		document.getElementById("audiobox").innerHTML = "";
		document.getElementById("audiobox").appendChild(audioDom);
		return audioDom;
	}

	
	// 创建一个播放器
	function createMp3(src){
		// 创建一个mp3播放器
		//var audioDom = document.createElement("audio");
		var audioDom = document.getElementById("aud");
		// 设置播放器的地址
		audioDom.src = src;
		audioDom.id  ="aud";
		//去掉下载原生按钮
		audioDom.controlsList="nodownload";
		//控制台
		//audioDom.controls = "controls";
		//audioDom.autoplay = "autoplay";
		//audioDom.loop = "loop";
		audioDom.style = "position:absolute;left:170px;";
		// 把播放器放入div容器中
		document.getElementById("audiobox").innerHTML = "";
		document.getElementById("audiobox").appendChild(audioDom);
		return audioDom;
	}

	function clear() {
		currentPage = 1;
		$("#name").val("");
		var sql = currentPage;
		show(sql);
	}
	
	

	function search() {
		var name = $("#name").val();
		
		var pattern = new RegExp("[`~!@#%$^&*()=|{}':;',\\[\\].<>/?~！@#￥……&*（）——|{}【】‘；：”“'。，、？]") ;
		 
		if(pattern.test(name)){
			alert("不能有特殊字符！");
		     return false;
		}
		
		currentPage = 1;
		var sql = currentPage;
		if (name != null && name.length > 0) {
			sql =  currentPage + "&name=" + name;
		}
		show(sql);
	}
	
	
	function show(page){
		index = 1;
		var url = "<%=basePath%>/list.do?page=" + page;
		$.get(url,function(data){
			$(".music").html("");
			var datas = data.data;
			var bodyHtml = '<ul>'; 
			currentPage = data.currentPage;
			totalPage = data.totalPage;
			$("#currentPage").html(currentPage);
			$("#totalPage").html(totalPage);
			size = data.size;
			var datalength = datas.length;
			for(var i=0;i<datas.length;i++) {
				var d = datas[i];
				var name = JSON.stringify(d.name);
				bodyHtml +=  
				'<li class="m_'+(i+1)+'">' +
					'<a href="#" title="' + d.name + '" class="items" data-url="<%=basePath%>/'+ d.url +'" data-author="'+d.author+'" data-mid="'+(i+1)+'">' +
						'<p class="t_one" width="100" height="100">' +
							'<span class="p1">' + 
							'<span>'+ d.name +'</span><br>' +
							'上传者 ：<span class="author'+(i+1)+'">' + d.author + '</span><br>' +
							'<a href="<%=basePath%>/download.do?fileName='+d.id+'"  target="_blank">下载Ta</a>' +
							//'<span onclick=javascript:downloadMp3("'+ d.name +'");>下载并播放</span>' +
							//'<button onclick=javascript:downloadMp3("1.mp3")>下载并播放</button>' +
							'</span>'
						'</p>' +
					'</a>' +
				'</li>';
				if (i == 4) {
					bodyHtml += "</ul><ul>";
				}
			}
			bodyHtml += '</ul>';
			$(".music").html(bodyHtml);
			//重新初始化
			
			$(".items").click(function(){
				var title = $(this).attr("title");
				var url = $(this).data("url");
				var author = $(this).data("author");
				index = $(this).data("mid");
				console.log(index);
				//var title_r = $(this).find("span").eq(2).html();
				//var title_r = $(this).find(".author").html();
				// 点击同步播放器的图片和文字
				//var imgsrc = $(this).find("img").attr("src");
				//$(".p_pic").find("img").attr("src",imgsrc);
				//<marquee>夜空最亮的星.mp3</marquee>
				//$(".p_pic").find(".p_title").text(title.substring(0,title.lastIndexOf(".")) );
				//$(".p_pic").find(".p_title").text('<marquee>'+ "夜空最亮的星.mp3"+'</marquee>' );
				$("#p_title").text(title);
				$(".p_pic").find(".p_title_r").text(author);

				// 重新初始化播放器
				audioDom = createMp3(url);
				// 自动触发播放器按钮
				$(".b_play").trigger("click");
				//$(".items").find("img").removeClass("xz");
				$(".items").find("p").removeClass("xz");
				//$(this).find("img").addClass("xz");
				
				$(this).find("p").addClass("xz");
			});

			// 点击图片播放音乐
			// 播放
			$(".b_play").click(function(){
				$(this).hide();
				$(".b_pause").show();
				//audioDom.play(); // 播放
				audioPlay(); // 播放
				//isend(audioDom);
			});

			// 暂停
			$(".b_pause").click(function(){
				
				$(this).hide();
				$(".b_play").show();
				audioDom.pause(); // 暂停
				//alert($(".items").eq(index).getRotateAngle());
				 
			});

			// 下一首
			$(".b_next").click(function(){
				var currentTime = Date.now();
			    var protectTime = 100;//设置保护性延时 单位毫秒，不要小于50 建议100以上
			    if((currentTime - lastRunTime) < protectTime){
			    	lastRunTime = Date.now();
			        return;//两次执行太过频繁，直接退出
			    }
				num = datalength;
				index++;
				index=index>num?1:index;

				//$(".items").eq(index).trigger("click");
				$(".m_" + (index)).find("a").eq(0).trigger("click");
				var title_r = $(".author" + (index)).html();
				$(".p_pic").find(".p_title_r").text(title_r);
			});
			
			// 上一首
			$(".b_pre").click(function(){
				num = datalength;
				index--;
				index=index<1?num:index;
				//$(".items").eq(index).trigger("click");
				$(".m_" + (index)).find("a").eq(0).trigger("click");
				var title_r = $(".author" + (index)).html();
				$(".p_pic").find(".p_title_r").text(title_r);
			});

			setMusic(datas[0]);
		});
	}
	//设置第一首歌
	function setMusic(data) {
		var title = data.name;
		//$(".p_pic").find(".p_title").text(title.substring(0,title.lastIndexOf(".")));
		$("#p_title").text(title);
		$(".p_pic").find(".p_title_r").text(data.author);

		// 重新初始化播放器
		audioDom = createMp3(data.url);
		// 自动触发播放器按钮
		$(".b_play").trigger("click");
	}
	
	function goPage(flag) {
		if (flag == 1) {
			currentPage=1;
		}
		if (flag == 2) {
			currentPage--;
			currentPage=currentPage<0?1:currentPage;
		}
		if (flag == 3) {
			currentPage++;
			currentPage=currentPage>totalPage?totalPage:currentPage;
		}
		if (flag == 4) {
			currentPage=totalPage;
		}
		var name = $("#name").val();
		if (name != null && name.length > 0) {
			show(currentPage + "&name=" + name);
			return;
		}
		show(currentPage);
	}

	//跳转
	function gotoPage(){
		var page = $("#gopage").val();
		if (page < 1) {
			page = 1;
		} else if (page > totalPage) {
			page = totalPage;
		}
		try {
			if (page > 0) {
				show(page);
			} else {
				alert("请输入数字！~~");
				$("#gopage").val("");
			}
		} catch (e){
			alert("请输入数字！");
			$("#gopage").val("");
		}
	}
	
	function downloadMp3(name) {
		 //阻止冒泡上层click
		window.open("localhost:8080/AnnotationMVC/download.do?fileName=" + name,'_blank');
		//location.href="localhost:8080/AnnotationMVC/download.do?fileName=" + name;
		//window.location.href=history.go(-1);
		//window.open("localhost:8080/AnnotationMVC/jsp/index2.jsp?fileName=" + name,'_blank');
	    
	    
	    //var url = "http://localhost:8080/AnnotationMVC/download.do";
        //var fileName = "1";
       // var form = $("<form></form>").attr("action", url).attr("method", "get");
       	//form.append($("<input></input>").attr("type", "hidden").attr("name", "fileName").attr("value", fileName));
       //	form.appendTo('body').submit().remove();
	    
	    
	    event.stopPropagation();
	}

	 function audioPlay(){
			var shapeAudio=document.getElementById("aud");
			shapeAudio.play();
			/*判断声音是否播放完成，播放完成之后执行回调函数*/
			lastRunTime=Date.now();
			var is_playFinish = setInterval(function(){
				if(shapeAudio.ended){
					window.clearInterval(is_playFinish);
					//audioPlay();
					$(".b_next").trigger("click");
				}
			}, 1000);
		}
	
</script>

</body>
</html>