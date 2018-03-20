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
        
        <div class="fenxiang">
  <div class="list_top fxx">分享得积分 </div>
  <div class="am-g">
    <!--01-->
    <div class="am-u-sm-3 -mob-share-weixin"> <a href="#">
      <div class="icon_list">
        <div class="icon_list_txt">微信</div>
      </div>
      </a> </div>
    <!--02-->
    <div class="am-u-sm-3 -mob-share-weibo" > <a href="#">
      <div class="icon_list">
        <div class="icon_list_txt">新浪微博</div>
      </div>
      </a> </div>
    <!--03-->
    <div class="am-u-sm-3 -mob-share-qq" > <a href="#">
      <div class="icon_list">
        <div class="icon_list_txt">QQ好友</div>
      </div>
      </a> </div>
    <!--04-->
    <div class="am-u-sm-3 -mob-share-qzone"> <a href="#">
      <div class="icon_list">
        <div class="icon_list_txt">QQ空间</div>
      </div>
      </a> </div>
  </div>
</div>
<script id="-mob-share" src="http://f1.webshare.mob.com/code/mob-share.js?appkey=16c0f74b339ca"></script>
	   <script>
	   
	   mobShare.config( {

	        debug: true, // 开启调试，将在浏览器的控制台输出调试信息

	        appkey: '16c0f74b339ca', // appkey

	        params: {
	            url: 'http://www.baidu.com', // 分享链接
	            title: "分享", // 分享标题
	            description: '试试', // 分享内容
	            pic: '', // 分享图片，使用逗号,隔开
	        },
	        /**
	         * 分享时触发的回调函数
	         * 分享是否成功，目前第三方平台并没有相关接口，因此无法知道分享结果
	         * 所以此函数只会提供分享时的相关信息
	         * 
	         * @param {String} plat 平台名称
	         * @param {Object} params 实际分享的参数 { url: 链接, title: 标题, description: 内容, pic: 图片连接 }
	         */
	        callback: function( plat, params ) {
	        	console.log(params)

                $.ajax({
                    type: "post",
                    url: "${portalPath}/cms/cmsShare/saveShare",
                    data: {platform:plat,sourceId:window.document.location.href,
                        sourceTable:'cmsnews',
                    title:params.title,url:params.url},
                    dataType: "json",
                    success: function(data){
                        console.log('--'+data);

                    }
                });
	        }
	    } );
	   
		   $(function(){
			  console.log(portalPath+"---"+portalPage);
			  console.log(window.document.location.href);
               console.log(document.title)
			});
		   <!--
		   -->
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
		   var newWindow;
		   
		  
	  
		   function openWindow(url){
			   url = url+window.document.location.href+'&title='+document.title;
			   if(window.open(url,'_blank').closed){
				   console.log($("#weiboPublisher").html())
			   }
			   
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
		   /**
		   
		   var shareWindow;//分享窗口
		   document.getElementById("sharewb").onclick = function () {
		   var url = 'http://www.baidu.com';
		   var content = 'test';
		   var img = 'http://jidian-php-prod.oss-cn-hangzhou.aliyuncs.com/kinder-choc161108/static/photo/3941639e4102cfd730f5f0b882cc4b781478943874.jpeg';
		   var appkey = '';
		   shareToWeibo(url, content, img, appkey, function () {
			   var loop = setInterval(function () {
				   if (shareWindow.closed) {
				   clearInterval(loop);
				   alert('closed');
				   }
				   }, 1000);
			   });
		   };

		   function shareToWeibo(url, content, img, appkey, callback) {
		   		callback = callback || function () {};
		   javascript:void((function (s, d, e) {
		   var f = 'http://v.t.sina.com.cn/share/share.php?';
		   var p = ['url=' + e(url), '&title=', e(content), '&appkey=' + appkey].join('');
		   if (img !== false) {
		   p += '&pic=' + img;
		   }
		   function a() {
		   shareWindow = window.open([f, p].join(''), 'mb', ['toolbar=0,status=0,resizable=1,width=620,height=450,left=', (s.width - 620) / 2, ',top=', (s.height - 450) / 2].join(''))
		   if (!shareWindow) {
		   }
		   }
		   if (/Firefox/.test(navigator.userAgent)) {
		   setTimeout(a, 0);
		   } else {
		   a();
		   }
		   callback();
		   })(screen, document, encodeURIComponent));
		   }
		**/
	
	   </script>
    </body>
</html>
