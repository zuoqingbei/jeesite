<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>问答表管理</title>
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
		<li><a href="${ctx}/cms/cmsQuestionAnswer/list">问答表列表</a></li>
		<li class="active"><a href="${ctx}/cms/cmsQuestionAnswer/form?id=${cmsQuestionAnswer.id}">问答表<shiro:hasPermission name="cms:cmsQuestionAnswer:edit">${not empty cmsQuestionAnswer.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="cms:cmsQuestionAnswer:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="cmsQuestionAnswer" action="${ctx}/cms/cmsQuestionAnswer/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<%--<div class="control-group">--%>
			<%--<label class="control-label">父级id 问题-1 回答不是-1：</label>--%>
			<%--<div class="controls">--%>
			<%--</div>--%>
		<%--</div>--%>
		<div class="control-group">
			<label class="control-label">源链接：</label>
			<div class="controls">
				<form:input path="link" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<%--<div class="control-group">--%>
			<%--<label class="control-label">数据来源类型：</label>--%>
			<%--<div class="controls">--%>
				<%--<form:select hidden="true" path="dataType" class="input-xlarge " value="2">--%>
					<%--<form:option value="" label=""/>--%>
					<%--<form:option value="0" label="采集"/>--%>
					<%--<form:option value="1" label="用户投稿"/>--%>
					<%--<form:option value="2" label="管理人员发布"/>--%>
				<%--</form:select>--%>
			<%--</div>--%>
		<%--</div>--%>
		<%--<div class="control-group">--%>
			<%--<label class="control-label">创建者 用户发布id  采集源也可以当做用户：</label>--%>
			<%--<div class="controls">--%>
				<%--<sys:treeselect id="user" name="user.id" value="${cmsQuestionAnswer.user.id}" labelName="user.name" labelValue="${cmsQuestionAnswer.user.name}"--%>
					<%--title="用户" url="/sys/office/treeData?type=3" cssClass="" allowClear="true" notAllowSelectParent="true"/>--%>
			<%--</div>--%>
		<%--</div>--%>
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
				<form:hidden id="image" path="image" htmlEscape="false" maxlength="255" class="input-xlarge"/>
				<sys:ckfinder input="image" type="files" uploadPath="/cms/cmsQuestionAnswer" selectMultiple="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">关键字：</label>
			<div class="controls">
				<form:input path="keywords" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">标签：</label>
			<div class="controls">
				<c:choose>
					<c:when test="${ cmsQuestionAnswer.id eq null }">
						<form:checkboxes items="${fns:getDictList('tags_type')}" path="tags" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</c:when>
					<c:otherwise>
						<c:forEach var ="dicts" items="${fns:getDictList('tags_type')}" >
							<c:choose>
								<c:when test="${fn:contains(cmsQuestionAnswer.tags,dicts.value)}">
									<input type="checkbox" checked="checked" name="tags" value="${dicts.value}">${dicts.label}
								</c:when>
								<c:otherwise>
									<input type="checkbox"  name="tags" value="${dicts.value}">${dicts.label}
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</c:otherwise>
				</c:choose>
				<%--<form:input path="tags" htmlEscape="false" maxlength="255" class="input-xlarge "/>--%>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">描述、摘要：</label>
			<div class="controls">
				<form:input path="description" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<%--<div class="control-group">--%>
			<%--<label class="control-label">内容 不包含HTML：</label>--%>
			<%--<div class="controls">--%>
				<%--<form:textarea path="content" htmlEscape="false" rows="4" class="input-xxlarge "/>--%>
			<%--</div>--%>
		<%--</div>--%>
		<div class="control-group">
			<label class="control-label">内容 ：</label>
			<div class="controls">
				<form:textarea    id="contentHtml" htmlEscape="false" path="contentHtml" rows="4" maxlength="200" class="input-xxlarge"/>
				<sys:ckeditor replace="contentHtml" uploadPath="/cms/questionAnswer" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">权重，越大越靠前：</label>
			<div class="controls">
				<form:input path="weight" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div>
		<%--<div class="control-group">--%>
			<%--<label class="control-label">点击数、阅读数：</label>--%>
			<%--<div class="controls">--%>
				<%--<form:input path="hits" htmlEscape="false" maxlength="11" class="input-xlarge "/>--%>
			<%--</div>--%>
		<%--</div>--%>
		<%--<div class="control-group">--%>
			<%--<label class="control-label">转发数 分享数：</label>--%>
			<%--<div class="controls">--%>
				<%--<form:input path="transmit" htmlEscape="false" maxlength="11" class="input-xlarge "/>--%>
			<%--</div>--%>
		<%--</div>--%>
		<%--<div class="control-group">--%>
			<%--<label class="control-label">评论数  回复数：</label>--%>
			<%--<div class="controls">--%>
				<%--<form:input path="discess" htmlEscape="false" maxlength="11" class="input-xlarge "/>--%>
			<%--</div>--%>
		<%--</div>--%>
		<%--<div class="control-group">--%>
			<%--<label class="control-label">赞数量：</label>--%>
			<%--<div class="controls">--%>
				<%--<form:input path="praise" htmlEscape="false" maxlength="11" class="input-xlarge "/>--%>
			<%--</div>--%>
		<%--</div>--%>
		<%--<div class="control-group">--%>
			<%--<label class="control-label">踩数量：</label>--%>
			<%--<div class="controls">--%>
				<%--<form:input path="tread" htmlEscape="false" maxlength="11" class="input-xlarge "/>--%>
			<%--</div>--%>
		<%--</div>--%>
		<%--<div class="control-group">--%>
			<%--<label class="control-label">收藏量：</label>--%>
			<%--<div class="controls">--%>
				<%--<form:input path="collection" htmlEscape="false" maxlength="11" class="input-xlarge "/>--%>
			<%--</div>--%>
		<%--</div>--%>
		<%--<div class="control-group">--%>
			<%--<label class="control-label">举报数量：</label>--%>
			<%--<div class="controls">--%>
				<%--<form:input path="report" htmlEscape="false" maxlength="11" class="input-xlarge "/>--%>
			<%--</div>--%>
		<%--</div>--%>
		<%--<div class="control-group">--%>
			<%--<label class="control-label">评价数量：</label>--%>
			<%--<div class="controls">--%>
				<%--<form:input path="evaluate" htmlEscape="false" maxlength="11" class="input-xlarge "/>--%>
			<%--</div>--%>
		<%--</div>--%>
		<%--<div class="control-group">--%>
			<%--<label class="control-label">打赏次数：</label>--%>
			<%--<div class="controls">--%>
				<%--<form:input path="tip" htmlEscape="false" maxlength="11" class="input-xlarge "/>--%>
			<%--</div>--%>
		<%--</div>--%>
		<%--<div class="control-group">--%>
			<%--<label class="control-label">回复数量：</label>--%>
			<%--<div class="controls">--%>
				<%--<form:input path="answer" htmlEscape="false" maxlength="11" class="input-xlarge "/>--%>
			<%--</div>--%>
		<%--</div>--%>
		<%--<div class="control-group">--%>
			<%--<label class="control-label">曝光量：</label>--%>
			<%--<div class="controls">--%>
				<%--<form:input path="view" htmlEscape="false" maxlength="11" class="input-xlarge "/>--%>
			<%--</div>--%>
		<%--</div>--%>
		<div class="control-group">
			<label class="control-label">是否推荐：</label>
			<div class="controls">
				<form:select path="recommend" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:option value="0" label="否"/>
					<form:option value="1" label="是"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否允许评论：</label>
			<div class="controls">
				<form:select path="allowComment" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:option value="0" label="是"/>
					<form:option value="1" label="否"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">评论是否需要审核：</label>
			<div class="controls">
				<form:select path="commentAudit" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:option value="0" label="是"/>
					<form:option value="1" label="否"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否允许举报：</label>
			<div class="controls">
				<form:select path="allowReport" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:option value="0" label="是"/>
					<form:option value="1" label="否"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">内容是否下架：</label>
			<div class="controls">

				<form:select path="undercarriage" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:option value="0" label="否"/>
					<form:option value="1" label="是"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">下架原因：</label>
			<div class="controls">
				<form:input path="reason" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<%--<div class="control-group">--%>
			<%--<label class="control-label">一级分类：</label>--%>
			<%--<div class="controls">--%>
				<%--<form:input path="category1" htmlEscape="false" maxlength="64" class="input-xlarge required"/>--%>
				<%--<span class="help-inline"><font color="red">*</font> </span>--%>
			<%--</div>--%>
		<%--</div>--%>
		<%--<div class="control-group">--%>
			<%--<label class="control-label">二级分类：</label>--%>
			<%--<div class="controls">--%>
				<%--<form:input path="category2" htmlEscape="false" maxlength="64" class="input-xlarge "/>--%>
			<%--</div>--%>
		<%--</div>--%>
		<%--<div class="control-group">--%>
			<%--<label class="control-label">三级分类：</label>--%>
			<%--<div class="controls">--%>
				<%--<form:input path="category3" htmlEscape="false" maxlength="64" class="input-xlarge "/>--%>
			<%--</div>--%>
		<%--</div>--%>
		<%--<div class="control-group">--%>
			<%--<label class="control-label">备注信息：</label>--%>
			<%--<div class="controls">--%>
				<%--<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>--%>
			<%--</div>--%>
		<%--</div>--%>
		<div class="form-actions">
			<shiro:hasPermission name="cms:cmsQuestionAnswer:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>