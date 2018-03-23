<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>每日一览详情管理</title>
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
		<li><a href="${ctx}/cms/cmsDailyListContent/list">每日一览详情列表</a></li>
		<li class="active"><a href="${ctx}/cms/cmsDailyListContent/form?id=${cmsDailyListContent.id}">每日一览详情<shiro:hasPermission name="cms:cmsDailyListContent:edit">${not empty cmsDailyListContent.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="cms:cmsDailyListContent:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="cmsDailyListContent" action="${ctx}/cms/cmsDailyListContent/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">表名：</label>
			<div class="controls">
				<sys:treeselect id="tableName" name="tableName" value="${cmsDailyListContent.tableName}" labelName="" labelValue="${cmsDailyListContent.}"
					title="区域" url="/sys/area/treeData" cssClass="" allowClear="true" notAllowSelectParent="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">id：</label>
			<div class="controls">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="cms:cmsDailyListContent:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>