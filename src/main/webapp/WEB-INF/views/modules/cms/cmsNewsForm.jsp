<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>资讯管理</title>
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
		<li><a href="${ctx}/cms/cmsNews/list">资讯列表</a></li>
		<li class="active"><a href="${ctx}/cms/cmsNews/form?id=${cmsNews.id}">资讯<shiro:hasPermission name="cms:cmsNews:edit">${not empty cmsNews.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="cms:cmsNews:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="cmsNews" action="${ctx}/cms/cmsNews/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">源链接：</label>
			<div class="controls">
				<form:input path="link" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group" hidden="true">
			<label class="control-label">数据来源类型：</label>
		 <div class="controls">
				<form:select hidden="true" path="dataType" class="input-xlarge " value="2">
					<form:option value="" label=""/>
					<form:option value="0" label="采集"/>
					<form:option value="1" label="用户投稿"/>
					<form:option value="2" label="管理人员发布"/>
				</form:select> 
			</div> 
		</div>
		<%-- <div class="control-group">
			<label class="control-label">创建者：</label>
			<div class="controls">
				<sys:treeselect id="user" name="user.id" value="${cmsNews.user.id}" labelName="user.name" labelValue="${cmsNews.user.name}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="" allowClear="true" notAllowSelectParent="true"/>
			</div>
		</div> --%>
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
				<sys:ckfinder input="image" type="files" uploadPath="/cms/cmsNews" selectMultiple="false"/>
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
				<%--<form:select hidden="true" path="tags" class="input-xlarge " value="2">
					<form:option value="" label=""/>
					<c:forEach var="a" items="${ cmsNews.typeDict }">
						<form:option value="${a.value}" label="${a.label}"/>
					</c:forEach>
				</form:select>--%>
				<c:choose>
					<c:when test="${ cmsNews.id eq null }">
						<form:checkboxes items="${fns:getDictList('tags_type')}" path="tags" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</c:when>
					<c:otherwise>
						<c:forEach var ="dicts" items="${fns:getDictList('tags_type')}" >
							<c:choose>
								<c:when test="${fn:contains(cmsNews.tags,dicts.value)}">
									<input type="checkbox" checked="checked" name="tags" value="${dicts.value}">${dicts.label}
								</c:when>
								<c:otherwise>
									<input type="checkbox"  name="tags" value="${dicts.value}">${dicts.label}
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</c:otherwise>
				</c:choose>


			</div>
		</div>
		<div class="control-group">
			<label class="control-label">摘要：</label>
			<div class="controls">
				<form:textarea path="description" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">权重：</label>
			<div class="controls">
				<form:input path="weight" htmlEscape="false" maxlength="11" class="digits input-xlarge "/>
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
		<%-- <div class="control-group">
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
		</div> --%>
		<%--<div class="control-group">--%>
			<%--<label class="control-label">一级分类：</label>--%>
			<%--<div class="controls">--%>
				<%--<form:input path="category1" htmlEscape="false" maxlength="64" class="input-xlarge "/>--%>
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

		<div class="control-group">
			<label class="control-label">正文:</label>
			<div class="controls">
				<form:textarea    id="contentHtml" htmlEscape="false" path="contentHtml" rows="4" maxlength="200" class="input-xxlarge"/>
				<sys:ckeditor replace="contentHtml" uploadPath="/cms/cmsNews" />
			</div>
		</div>
		<%--<div class="control-group">--%>
			<%--<label class="control-label">备注信息：</label>--%>
			<%--<div class="controls">--%>
				<%--<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>--%>
			<%--</div>--%>
		<%--</div>--%>
		<div class="form-actions">
			<shiro:hasPermission name="cms:cmsNews:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
<script>
 console.log('----------'+'${cmsNews.typeDict}')
	console.log('${table}')
</script>
</body>
</html>