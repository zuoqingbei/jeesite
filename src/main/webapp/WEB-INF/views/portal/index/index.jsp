<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<meta name="decorator" content="portal"/>
</head>
    <body>
         <!-- START CONTAINER -->
         我是<div style="color:red;font-size:20em;">电脑端</div>测试首页1--${portalPath }--${portalPage }
        <!-- END CONTAINER -->
        
        <!-- BEGIN FOOTER -->
        <!-- js必须引用在body前面 -->
	   <script>
	   
	   $(function(){
		  console.log(portalPath+"---"+portalPage);
		});
	  
		
	
	   </script>
    </body>
</html>
