<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>资讯详表管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
		console.log('${cmsNewsData.titleid }');
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/cms/cmsNewsData/list">资讯详表列表</a></li>
		<li class="active"><a href="${ctx}/cms/cmsNewsData/form?id=${cmsNewsData.id}">资讯详表<shiro:hasPermission name="cms:cmsNewsData:edit">${not empty cmsNewsData.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="cms:cmsNewsData:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="cmsNewsData" action="${ctx}/cms/cmsNewsData/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
 		<form:hidden hidden="true" path="newsId" value='${cmsNewsData.titleid }' htmlEscape="false" maxlength="64" class="input-xlarge "/>
		<sys:message content="${message}"/>		
	<%-- 	<div class="control-group">
			<label class="control-label">源数据ID：</label>
			<div class="controls">
				<form:input path="newsId" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div> --%>
		<%-- <div class="control-group">
			<label class="control-label">标题id：</label>
			<div class="controls">
				<form:hidden hidden="true" path="newsId" value='${titleid }' htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div> --%>
		<div class="control-group">
			<label class="control-label">文章内容 不包含HTML：</label>
			<div class="controls">
				<form:textarea path="content" htmlEscape="false" rows="4" class="input-xxlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">文章内容 包含HTML：</label>
			<div class="controls">
				<form:textarea path="contentHtml" htmlEscape="false" rows="8" class="input-xlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="cms:cmsNewsData:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>