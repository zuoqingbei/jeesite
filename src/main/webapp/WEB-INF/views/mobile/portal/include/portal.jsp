<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="sitemeshPage" uri="http://www.opensymphony.com/sitemesh/page" %>
<!DOCTYPE html>
<html style="overflow-x:auto;overflow-y:auto;">
<head>
	<title><sitemesh:title default="${fnc:getSite(1).title}"/></title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1" name="viewport" />
    <meta content="${fnc:getSite(1).description}" name="description" />
    <!-- 引入清算所监管平台PC主题文件 css、js -->
	<%@include file="/WEB-INF/views/mobile/portal/include/theme.jsp" %>		
	<sitemesh:head/>
</head>
<body>
    <!-- 追加公共头  -->
	<sitemeshPage:applyDecorator  name="mheader"></sitemeshPage:applyDecorator> 
	<sitemesh:body/>
	<!-- 公共底部 -->
	<sitemeshPage:applyDecorator  name="mfooter"></sitemeshPage:applyDecorator> 
</body>
</html>