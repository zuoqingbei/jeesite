<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>产品管理管理</title>
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
		<li><a href="${ctx}/cms/cmsProduct/list">产品管理列表</a></li>
		<li class="active"><a href="${ctx}/cms/cmsProduct/form?id=${cmsProduct.id}">产品管理<shiro:hasPermission name="cms:cmsProduct:edit">${not empty cmsProduct.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="cms:cmsProduct:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="cmsProduct" action="${ctx}/cms/cmsProduct/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">产品名称：</label>
			<div class="controls">
				<form:input path="title" htmlEscape="false" maxlength="255" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<%-- <div class="control-group">
			<label class="control-label">缩略图：</label>
			<div class="controls">
				<form:hidden id="smalImage" path="smalImage" htmlEscape="false" maxlength="255" class="input-xlarge"/>
				<sys:ckfinder input="smalImage" type="files" uploadPath="/cms/cmsProduct" selectMultiple="false"/>
			</div>
		</div> --%>
		<div class="control-group">
			<label class="control-label">产品图片：</label>
			<div class="controls">
				<form:hidden id="image" path="image" htmlEscape="false" maxlength="1000" class="input-xlarge"/>
				<sys:ckfinder input="image" type="files" uploadPath="/cms/cmsProduct" selectMultiple="false"/>
			</div>
		</div>
		<%-- <div class="control-group">
			<label class="control-label">关键字：</label>
			<div class="controls">
				<form:input path="keywords" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div> --%>
		<div class="control-group">
			<label class="control-label">产品分类：</label>
			<div class="controls">
				<form:select path="tags" class="input-xlarge ">
					<%-- <form:option value="" label=""/> --%>
					<form:options items="${fns:getDictList('product_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<%-- <div class="control-group">
			<label class="control-label">描述、摘要：</label>
			<div class="controls">
				<form:textarea path="description" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div> --%>
		<div class="control-group">
			<label class="control-label">产品介绍：</label>
			<div class="controls">
				<form:textarea    id="contentHtml" htmlEscape="false" path="contentHtml" rows="4" maxlength="200" class="input-xxlarge"/>
				<sys:ckeditor replace="contentHtml" uploadPath="/cms/cmsProduct" />
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
		<div class="control-group">
			<label class="control-label">排序：</label>
			<div class="controls">
				<form:input path="orderNo" htmlEscape="false" maxlength="11" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="cms:cmsProduct:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>