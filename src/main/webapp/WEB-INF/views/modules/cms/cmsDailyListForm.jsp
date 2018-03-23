<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>每日一览管理</title>
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
		<li><a href="${ctx}/cms/cmsDailyList/list">每日一览列表</a></li>
		<li class="active"><a href="${ctx}/cms/cmsDailyList/form?id=${cmsDailyList.id}">每日一览<shiro:hasPermission name="cms:cmsDailyList:edit">${not empty cmsDailyList.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="cms:cmsDailyList:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="cmsDailyList" action="${ctx}/cms/cmsDailyList/save" method="post" class="form-horizontal">
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
			<label class="control-label">图片：</label>
			<div class="controls">
				<form:hidden id="image" path="image" htmlEscape="false" maxlength="255" class="input-xlarge"/>
				<sys:ckfinder input="image" type="files" uploadPath="/cms/cmsDailyList" selectMultiple="true"/>
			</div>

		</div>

		<div class="control-group">
			<label class="control-label">资讯：</label>
			<div class="controls">
				<table path="newids" id="newids" class="table table-striped table-bordered table-condensed">
					<c:forEach items="${cmsNewsPage.list}" var="news">
						<tr>
							<td>
								<input type="checkbox" name="" id="${news.id}">
							</td>

							<td>
									${news.title}
							</td>

						</tr>

					</c:forEach>
				</table>
				<div class="pagination">${cmsNewsPage}</div>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">活动：</label>
			<div class="controls">
				<table path id="activityids" class="table table-striped table-bordered table-condensed">
					<c:forEach items="${cmsActivityPage.list}" var="act">
						<tr>
							<td>
								<input type="checkbox" name="" id="${act.id}">
							</td>

							<td>
									${act.title}
							</td>

						</tr>

					</c:forEach>
				</table>
				<div class="pagination">${cmsActivityPage}</div>
			</div>
		</div>

		<div class="form-actions">
			<shiro:hasPermission name="cms:cmsDailyList:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>