<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>活动管理</title>
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
		<li><a href="${ctx}/cms/cmsActivity/list">活动列表</a></li>
		<li class="active"><a href="${ctx}/cms/cmsActivity/form?id=${cmsActivity.id}">活动<shiro:hasPermission name="cms:cmsActivity:edit">${not empty cmsActivity.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="cms:cmsActivity:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="cmsActivity" action="${ctx}/cms/cmsActivity/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">类型 0-线下活动 1-线上活动：</label>
			<div class="controls">
				<form:input path="dataType" htmlEscape="false" maxlength="1" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">活动创建人：</label>
			<div class="controls">
				<sys:treeselect id="user" name="user.id" value="${cmsActivity.user.id}" labelName="user.name" labelValue="${cmsActivity.user.name}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="" allowClear="true" notAllowSelectParent="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">标题：</label>
			<div class="controls">
				<form:input path="title" htmlEscape="false" maxlength="255" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">文章图片：</label>
			<div class="controls">
				<form:input path="image" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">关键字：</label>
			<div class="controls">
				<form:input path="keywords" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">标签，多个 用&ldquo;，&rdquo;分开并且开头结尾也是逗号,比如 ,1,2,3,：</label>
			<div class="controls">
				<form:input path="tags" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">描述、摘要：</label>
			<div class="controls">
				<form:input path="description" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">活动内容 不含HTML：</label>
			<div class="controls">
				<form:textarea path="content" htmlEscape="false" rows="4" class="input-xxlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">活动内容 含HTML：</label>
			<div class="controls">
				<form:input path="contentHtml" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">权重，越大越靠前：</label>
			<div class="controls">
				<form:input path="weight" htmlEscape="false" maxlength="11" class="input-xlarge " value="0"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">点击数、阅读数：</label>
			<div class="controls">
				<form:input path="hits" htmlEscape="false" maxlength="11" class="input-xlarge " value="0"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">转发数 分享数：</label>
			<div class="controls">
				<form:input path="transmit" htmlEscape="false" maxlength="11" class="input-xlarge " value="0"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">评论数  回复数：</label>
			<div class="controls">
				<form:input path="discess" htmlEscape="false" maxlength="11" class="input-xlarge " value="0"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">赞数量：</label>
			<div class="controls">
				<form:input path="praise" htmlEscape="false" maxlength="11" class="input-xlarge " value="0"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">踩数量：</label>
			<div class="controls">
				<form:input path="tread" htmlEscape="false" maxlength="11" class="input-xlarge " value="0"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">收藏量：</label>
			<div class="controls">
				<form:input path="collection" htmlEscape="false" maxlength="11" class="input-xlarge " value="0"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">评价数量：</label>
			<div class="controls">
				<form:input path="evaluate" htmlEscape="false" maxlength="11" class="input-xlarge " value="0"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">报名人数：</label>
			<div class="controls">
				<form:input path="enter" htmlEscape="false" maxlength="11" class="input-xlarge " value="0"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">曝光量：</label>
			<div class="controls">
				<form:input path="view" htmlEscape="false" maxlength="11" class="input-xlarge " value="0"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否推荐 0-普通 1-推荐：</label>
			<div class="controls">
				<form:input path="recommend" htmlEscape="false" maxlength="1" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否允许评论 0-允许 1-不允许：</label>
			<div class="controls">
				<form:input path="allowComment" htmlEscape="false" maxlength="1" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">活动主办方：</label>
			<div class="controls">
				<form:input path="leader" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">活动举办地点：</label>
			<div class="controls">
				<form:input path="address" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">举办开始时间：</label>
			<div class="controls">
				<input name="startDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${cmsActivity.startDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">活动结束时间：</label>
			<div class="controls">
				<input name="endDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${cmsActivity.endDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">报名开始时间：</label>
			<div class="controls">
				<input name="enterStartDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${cmsActivity.enterStartDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">报名结束时间：</label>
			<div class="controls">
				<input name="enterEndDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${cmsActivity.enterEndDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">一级分类：</label>
			<div class="controls">
				<form:input path="category1" htmlEscape="false" maxlength="64" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">二级分类：</label>
			<div class="controls">
				<form:input path="category2" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">三级分类：</label>
			<div class="controls">
				<form:input path="category3" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注信息 报名须知：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="500" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="cms:cmsActivity:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>