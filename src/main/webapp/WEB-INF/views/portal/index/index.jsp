<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<meta name="decorator" content="portal"/>
	<title>首页</title>
	<style>

a{
cursor:pointer;
color:red
}
</style>
</head>
    <body>
         <!-- START CONTAINER -->
         我是<div style="color:red;font-size:20em;">电脑端</div>测试首页1--${portalPath }--${portalPage }
        <!-- END CONTAINER -->
        
        <a id="sendMessage">发送验证码</a>
        
        <a href="${portalPath }/logout">登出</a>
        <!-- BEGIN FOOTER -->
        <!-- js必须引用在body前面 -->
        <div>window.document.location.href+/${portalPath}分享：<a id="shareqq">qq</a> <a id="sharewb">微博</a> <a id="sharewx">微信</a></div>
	   <script>
	   
		   $(function(){
			  console.log(portalPath+"---"+portalPage);
			  console.log(window.document.location.href)
			    console.log(document.title)
			});
		   $("#shareqq").on('click',function(){
			   var url=' http://sns.qzone.qq.com/cgi-bin/qzshare/cgi_qzshare_onekey?url=';
			   openWindow(url);
		   });
		   $("#sharewb").on('click',function(){
			   var url='http://v.t.sina.com.cn/share/share.php?url=';
			   openWindow(url);
		   });
		   $("#sharewx").on('click',function(){
			   var url='http://s.jiathis.com/qrcode.php?url=?url=';
			  	openWindow(url);
		   });
	  
		   function openWindow(url){
			   url = url+window.document.location.href+'&title='+document.title;
			   window.open(url,'_blank')
		   }
		   //发送验证码
		   $("#sendMessage").on('click',sendMessage);
		   function sendMessage(){
			   $.ajax({
		             type: "post",
		             url: "test.json",
		             data: {tel:$("#tel").val(), content:$("#content").val()},
		             dataType: "json",
		             success: function(data){
		                        consolg.log(data)
		                      }
		   		 });

		   }
		
	
	   </script>
    </body>
</html>
