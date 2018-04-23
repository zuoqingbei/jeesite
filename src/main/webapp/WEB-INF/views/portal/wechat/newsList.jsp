<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html lang="zn-CN">
<head>
	<meta charset="UTF-8"  name="viewport" content="maximum-scale=1.0,minimum-scale=1.0,user-scalable=0,width=device-width,initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>我的举报</title>
    <link rel="stylesheet" href="${ctxStatic}/${portalPage}/wx/asserts/css/bootstrap.min-4.0.0.css">
	<link rel="stylesheet" type="text/css" href="https://res.wx.qq.com/open/libs/weui/0.4.1/weui.css">
     <style>
			html{
				font-size: 16px;
			}
      .list{
        width: 100%;
      }
      .header{
        width: 100%;
        background: red;
        padding: 0.5rem;
        display: flex;
      }
      .header input{
        border: 0;
        width: 100%;
      }
      .tab{
          display: flex;
          align-items: center;
          justify-content: space-between;
          padding:0.5rem;
					font-size: 0.8rem;
					background: #fff;
      }
			.list-item{
				margin-top: 5rem;
			}  
			.list-item ul{
				padding:0;
			}  
			.item{
				list-style: none;
				display: flex;
				flex-direction: column;
				padding: 0.5rem;
				background: #fff;
				border-bottom: 0.1rem solid #eee;
			}
			.item-top{
				display: flex;
				justify-content: space-between;
			}
			.item-bottom{
				display: flex;
				justify-content: space-between;
				align-items: center;
			}
			.title{
				font-size: 1rem;
			}
			.photo{
				width: 7.5rem;
				height: 4rem;
			}
			.second-title{
				font-size: 0.5rem;
				color: #808080;
			}
			.icon{
				display: flex;
			}
			.icon li{
				list-style: none;
			}
			.icon li span{
				font-size: 0.5rem;
				color: #808080;
			}
			.top{
				position: fixed;
				top: 0;
				width: 100%;
			}
    </style>
</head>
<body id="list">
<div class="list">
	<div class="top">
		<div class="header">
			<input id="searchInput" type="text" placeholder="你想搜索点什么...">
			<img onclick="freshData('')" src="${ctxStatic}/${portalPage}/wx/img/search.png" alt="">
		</div>
		<div class="tab">
			<!--<span>推荐</span>
			<span>新闻</span>
			<span>病例</span>
			<span>专题</span>
			<span>考试</span>
			<span>心内</span>-->
		</div>
	</div>
  
  <div class="list-item">
    <ul id="news_list">
			<!--<li class="item">
				<div class="item-top">
					<span class="title">这里是标题</span>
					<img src="${ctxStatic}/${portalPage}/wx/img/background2.png" alt="" class="photo">
				</div>
				<div class="item-bottom">
					<span class="second-title">这里是副标题 03-26</span>
					<ul class="icon">
						<li>
							<img src="${ctxStatic}/${portalPage}/wx/img/read.png" alt="">
							<span>26</span>
						</li>
						<li>
							<img src="${ctxStatic}/${portalPage}/wx/img/link.png" alt="">
							<span>26</span>
						</li>
					</ul>
				</div>
			</li>-->
    </ul>
  </div>
</div>
<script src="http://res.wx.qq.com/open/js/jweixin-1.1.0.js"> </script> 
<script src="${ctxStatic}/${portalPage}/wx/asserts/js/jquery-2.2.4.js"></script>
<script>
var totalPage=0;
var pageNo=1;
var new_post=1;
var tags="";
var keywords="";
    $(function () {
		judgeForBidPage();		
    });
	function judgeForBidPage(){
		var useragent = navigator.userAgent;
		if (useragent.match(/MicroMessenger/i) != 'MicroMessenger') {
			// 这里警告框会阻塞当前页面继续加载
			// 以下代码是用javascript强行关闭当前页面
			//var opened = window.open('about:blank', '_self');
			/*opened.opener = null;
			opened.close();*/
			var htmls='<div class="weui_msg"><div class="weui_icon_area"><i class="weui_icon_info weui_icon_msg"></i></div><div class="weui_text_area"><h4 class="weui_msg_title">请在微信客户端打开链接</h4></div></div>';			
			$("body").html(htmls);
		} else{
			window.alert = function(name){
				var iframe = document.createElement("IFRAME");
				iframe.style.display="none";
				iframe.setAttribute("src", 'data:text/plain,');
				document.documentElement.appendChild(iframe);
				window.frames[0].window.alert(name);
				iframe.parentNode.removeChild(iframe);
			};
			getUserSub();
			getNewsList();
		};
	};
	//获取用户订阅
	function getUserSub(){
		$.ajax({
    		url:"${portalPath}/subscribe/accountSubscribeHistory/find",
    		type:"post",
    		data:{"userId":"${userId}"},
    		dataType:"json",
    		success:function(data){
    			var htmls="";
    			if(data.status=="success"){
    				var htmls="<span onclick=freshData('')>推荐</span>";
					console.log(data);
    				$.each(data.data,function(index,item){
    					htmls+='<span onclick=freshData("'+item.value+'")>'+item.label+'</span>'; 
    				});
    				$(".tab").append(htmls);
    			}else{
    				console.log("error",data);
    			}
    		}
    	});
	}
	function freshData(tagsVal){
		tags=tagsVal;
		totalPage=0;
		pageNo=1;
		new_post=1;
		$("#news_list").empty();
		getNewsList();
	}
	//获取资讯列表
    function getNewsList(){
		keywords=$("#searchInput").val();
		console.log(keywords,tags);
    	$.ajax({
    		url:"${portalPath}/cms/cmsNews/getRank",
    		type:"post",
    		data:{"pageNo":pageNo,"pageSize":"6","userId":"${userId}","tags":tags,"keywords":keywords},
    		dataType:"json",
    		success:function(data){
    			var htmls="";
				console.log(data);
    			if(data.status=="success"){
    				var htmls="";
    				$.each(data.data,function(index,item){
						htmls+='<li class="item"><div class="item-top">';
						htmls+='<span class="title">'+item.title+'</span>';
						if(item.image!=undefined&&item.image!=""){
							htmls+='<img src="'+item.image+'" alt="" class="photo">';
						}
						
						htmls+='</div>';
						htmls+='<div class="item-bottom">';
						htmls+='<span class="second-title">'+item.description+'</span>';
						htmls+='<ul class="icon"><li><img src="${ctxStatic}/${portalPage}/wx/img/read.png" alt="">';
						htmls+='<span>'+(item.hits==undefined?0:item.hits)+'</span></li>';
						htmls+='<li><img src="${ctxStatic}/${portalPage}/wx/img/link.png" alt=""><span>'+(item.transmit==undefined?0:item.transmit)+'</span></li>';
						htmls+='</ul></div></li>';     
    				});
					totalPage=data.totalPage;
    				new_post=1;
					if(data.data.length==0&&pageNo==1){
						htmls='<div class="noData"><h4>没有获取到任何数据</h4></div>';
					};
					if(pageNo>1&&pageNo==totalPage){
						htmls+='<div class="noMore"><h6>没有更多数据</h6></div>';
					};
    				
    				$("#news_list").append(htmls);
    				
					
    			}else{
    				console.log("error",data);
    			}
    		}
    	});
    };
	//跳转明细
	function intentDetail(id){
		window.location.href="${portalPath}/wx/report?id="+id+"&userId=${userId}";
	}
</script>
<script type="text/javascript">
$(window).scroll(function(){
    if(($(window).scrollTop()+$(window).height())>$(document).height()-5){
		if(pageNo<totalPage){
    	console.log(pageNo,totalPage);
	 		if(new_post==1){
				new_post=0;
				pageNo++;
				getNewsList();
			}
		}
    }
});
 </script>
</body>
</html>
