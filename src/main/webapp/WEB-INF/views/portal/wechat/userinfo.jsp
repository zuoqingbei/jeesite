<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<meta name="decorator" content="portal"/>
	<title>首页</title>
	<style>

</style>
</head>
    <body>
    ${to }------${accountId }
	<input type="hidden" id="accountId" value="${accountId}" />
        用户名<input id="username"/> </br>
             手机号<input id="mobile"/> <button id="sendBtn">发送</button></br>
       验证码<input id="code"/>       
<button value="">提交</button></br>
    </body>
    <script>
    $(function(){
    	$("#sendBtn").one(sendMobileCode);
    	function sendMobileCode(){
    		var tel=$("#mobile").val();
			var accountId=$("#accountId").val();
    		if(tel==""){
				alert("请输入手机号");
				$("#sendBtn").one(sendMobileCode);
				return false;
			}
			//发送验证码
			$.post("${portalPath}/cms/cmsShare/saveShare",{},function(data){
				//console.log(data);
			});
			 
    	};
    });
    </script>
</html>
