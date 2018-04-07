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
       body {
			background-color: #eee;
		}

		.container-fluid {
			padding-left: 0;
			padding-right: 0;
		}

		.itemBox {
			margin-bottom: .6rem;
			padding: 1.6rem 1.4rem 1.4rem;
			background-color: #fff;
		}

		h3 {
			position: relative;
			padding-right: 1rem;
			color: #484848;
			font-size: 1.2rem;
		}

		h3:after {
			content: '';
			position: absolute;
			right: 0;
			top: .375rem;
			width: .75rem;
			height: .75rem;
			border-top: .0625rem solid;
			border-right: .0625rem solid;
			transform: rotate(45deg);
			color: rgba(0, 0, 0, .6);

		}

		h4{
			font-size: 1rem;
			/*font-weight: bold;*/
		}
		ul {
			margin-top: 1.1rem;
			margin-bottom: 0;
			padding: 0;
			color: rgba(0, 0, 0, .6);
			font-size: 1.1rem;
			list-style: none;
		}

		ul > li {
			position: relative;
			line-height: 2rem;
		}

		ul > li > .badge {
			position: absolute;
			right: 0;
			padding: .3125em .5125em;
			color: #fff;
			font-size: 1.0625rem;
			font-weight: normal;
		}

		ul > li > .badge-unaccepted {
			background-color: rgba(254, 225, 157, 1);
		}

		ul > li > .badge-accepted {
			background-color: rgba(64, 169, 255, .5);
		}

		ul > li > .badge-rejected {
			background-color: rgba(255, 120, 117, .5);
		}

		ul > li > .badge-ended {
			background-color: rgba(102, 102, 102, .5);
		}

		.noData {
			margin-top: 50%;
		}

		.noData, .noMore {
			text-align: center;
			padding: 2rem;
			color: #bbb;
		}
    </style>
</head>
<body id="reportList">
<div class="container-fluid">
   
   <!--  <div class="itemBox">
        <h3>举报质量服务态度存在严重问题</h3>
        <ul>
            <li>举报对象：<span class="target">**信息有限公司</span></li>
            <li>发现时间：
                <span class="date">2018/03/12</span>
                <span class="badge badge-accepted">已受理</span>
            </li>
        </ul>
    </div> -->
</div>
<script src="http://res.wx.qq.com/open/js/jweixin-1.1.0.js"> </script> 
<script src="${ctxStatic}/${portalPage}/wx/asserts/js/jquery-2.2.4.js"></script>
<script>
var totalPage=0;
var pageNo=1;
var new_post=1;
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
			getReportList();
		};
	};
    function getReportList(){
    	$.ajax({
    		url:"${portalPath}/cms/cmsComplaint/listData",
    		type:"post",
    		data:{"pageNo":pageNo,"pageSize":"6","userId":"${userId}"},
    		dataType:"json",
    		success:function(data){
    			var htmls="";
				//data=eval('('+data+')');
				console.log(data)
    			if(data.status=="success"){
    				var htmls="";
    				$.each(data.data,function(index,item){
    					htmls+='<div onclick=intentDetail("'+item.id+'") class="itemBox">';
						htmls+='<h3>'+item.title+'</h3>';
						htmls+='<ul>';
						htmls+='<li>举报对象：<span class="target">'+item.companyName+'</span></li>';
						htmls+='<li>发现时间：';
						htmls+='<span class="date">'+item.findDate+'</span>';
						if(item.status==null||item.status=='0'){
							htmls+='<span class="badge badge-unaccepted">未受理</span>';
						}else if(item.status=='1'){
							htmls+='<span class="badge badge-accepted">已受理</span>';
						}else if(item.status=='2'){
							htmls+='<span class="badge badge-rejected">驳&emsp;回</span>';
						}else if(item.status=='3'){
							htmls+='<span class="badge badge-ended">结&emsp;束</span>';
						}
						htmls+='</li></ul></div>';      
    				});
					totalPage=data.totalPage;
    				new_post=1;
					if(data.data.length==0&&pageNo==1){
						htmls='<div class="noData"><h4>没有获取到任何数据</h4></div>';
					};
					if(pageNo>1&&pageNo==totalPage){
						htmls+='<div class="noMore"><h6>没有更多数据</h6></div>';
					};
    				
    				$(".container-fluid").append(htmls);
    				
					
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
				getReportList();
			}
		}
    }
});
 </script>
</body>
</html>
