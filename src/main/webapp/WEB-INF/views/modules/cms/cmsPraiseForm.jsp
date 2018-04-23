<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用户赞 踩记录管理</title>
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
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/cms/cmsPraise/list">用户赞 踩记录列表</a></li>
		<li class="active"><a href="${ctx}/cms/cmsPraise/form?id=${cmsPraise.id}">用户赞 踩记录<shiro:hasPermission name="cms:cmsPraise:edit">${not empty cmsPraise.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="cms:cmsPraise:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="cmsPraise" action="${ctx}/cms/cmsPraise/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<%--<div class="control-group">--%>
			<%--<label class="control-label">赞踩内容源id：</label>--%>
			<%--<div class="controls">--%>
				<%--<form:input path="sourceId" htmlEscape="false" maxlength="64" class="input-xlarge required"/>--%>
				<%--<span class="help-inline"><font color="red">*</font> </span>--%>
			<%--</div>--%>
		<%--</div>--%>
		<div class="control-group">
			<label class="control-label">内容来源表：</label>
			<div class="controls">
				<form:input path="sourceTable" htmlEscape="false" maxlength="64" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<%--<div class="control-group">--%>
			<%--<label class="control-label">用户id：</label>--%>
			<%--<div class="controls">--%>
				<%--<sys:treeselect id="user" name="user.id" value="${cmsPraise.user.id}" labelName="user.name" labelValue="${cmsPraise.user.name}"--%>
					<%--title="用户" url="/sys/office/treeData?type=3" cssClass="" allowClear="true" notAllowSelectParent="true"/>--%>
			<%--</div>--%>
		<%--</div>--%>
		<div class="control-group">
			<label class="control-label">操作类型：</label>
			<div class="controls">
				<%--<form:input path="operateType" htmlEscape="false" maxlength="1" class="input-xlarge "/>--%>
				<form:select hidden="true" path="operateType" class="input-xlarge " >
					<form:option value="" label=""/>
					<form:option value="0" label="踩"/>
					<form:option value="1" label="赞"/>
					<%--<form:option value="2" label="管理人员发布"/>--%>
				</form:select>
			</div>
		</div>
		<div class="form-actions">
			<%--<shiro:hasPermission name="cms:cmsPraise:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>--%>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>