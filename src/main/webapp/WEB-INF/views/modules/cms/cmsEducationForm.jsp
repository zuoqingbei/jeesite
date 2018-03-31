<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>客户案例管理</title>
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
		<li><a href="${ctx}/cms/cmsEducation/list">客户案例列表</a></li>
		<li class="active"><a href="${ctx}/cms/cmsEducation/form?id=${cmsEducation.id}">客户案例<shiro:hasPermission name="cms:cmsEducation:edit">${not empty cmsEducation.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="cms:cmsEducation:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="cmsEducation" action="${ctx}/cms/cmsEducation/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
	
		<div class="control-group">
			<label class="control-label">标题：</label>
			<div class="controls">
				<form:input path="title" htmlEscape="false" maxlength="255" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">案例图片：</label>
			<div class="controls">
				<form:hidden id="image" path="image" htmlEscape="false" maxlength="255" class="input-xlarge"/>
				<sys:ckfinder input="image" type="files" uploadPath="/cms/education" selectMultiple="false"/>
			</div>
		</div>
	
		<div class="control-group">
			<label class="control-label">案例类型：</label>
			<div class="controls">
				<form:select path="tags" class="input-xlarge ">
					<form:options items="${fns:getDictList('customer_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">内容：</label>
		
			<div class="controls">
				<form:textarea    id="contentHtml" htmlEscape="false" path="contentHtml" rows="4" maxlength="200" class="input-xxlarge"/>
				<sys:ckeditor replace="contentHtml" uploadPath="/cms/education" />
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">是否推荐(置顶)：</label>
			<div class="controls">
				<form:select path="recommend" class="input-xlarge ">
					<form:options items="${fns:getDictList('top_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		
		<div class="form-actions">
			<shiro:hasPermission name="cms:cmsEducation:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>