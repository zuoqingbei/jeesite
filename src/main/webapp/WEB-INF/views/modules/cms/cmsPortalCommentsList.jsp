<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>门户评论管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/cms/cmsPortalComments/list">门户评论列表</a></li>
		<%--<shiro:hasPermission name="cms:cmsPortalComments:edit"><li><a href="${ctx}/cms/cmsPortalComments/form">门户评论添加</a></li></shiro:hasPermission>--%>
	</ul>
	<form:form id="searchForm" modelAttribute="cmsPortalComments" action="${ctx}/cms/cmsPortalComments/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>内容来源表：</label>
				<form:input path="sourceTable" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<%--<li><label>评论姓名：</label>--%>
				<%--<sys:treeselect id="user" name="user.id" value="${cmsPortalComments.user.id}" labelName="user.name" labelValue="${cmsPortalComments.user.name}"--%>
					<%--title="用户" url="/sys/office/treeData?type=3" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>--%>
			<%--</li>--%>
			<%--<li><label>评论IP：</label>--%>
				<%--<form:input path="ip" htmlEscape="false" maxlength="100" class="input-medium"/>--%>
			<%--</li>--%>
			<li><label>评论时间：</label>
				<input name="createDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${cmsPortalComments.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>审核时间：</label>
				<input name="auditDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${cmsPortalComments.auditDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>内容来源表</th>
				<th>评论内容</th>
				<th>评论姓名</th>
				<th>评论IP</th>
				<th>评论时间</th>
				<th>状态</th>
				<th>审核人</th>
				<th>审核时间</th>
				<shiro:hasPermission name="cms:cmsPortalComments:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="cmsPortalComments">
			<tr>
				<td><a href="${ctx}/cms/cmsPortalComments/form?id=${cmsPortalComments.id}">
					${cmsPortalComments.sourceTable}
				</a></td>
				<td>
					${cmsPortalComments.content}
				</td>
				<td>
					${cmsPortalComments.user.name}
				</td>
				<td>
					${cmsPortalComments.ip}
				</td>
				<td>
					<fmt:formatDate value="${cmsPortalComments.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
						<%--${cmsPortalComments.status}--%>
					<c:if test="${cmsPortalComments.status eq '0'}">未审核</c:if>
					<c:if test="${cmsPortalComments.status eq '1'}">审核通过</c:if>
					<c:if test="${cmsPortalComments.status eq '2'}">未通过</c:if>

				</td>
				<td>
					${cmsPortalComments.auditUserId}
				</td>
				<td>
					<fmt:formatDate value="${cmsPortalComments.auditDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>


				<c:choose>
					<c:when test="${cmsPortalComments.status eq '1'}">
						<shiro:hasPermission name="cms:cmsPortalComments:edit"><td>
								<%--
                                                                <a href="${ctx}/cms/cmsContribute/form?id=${cmsContribute.id}">审核</a>
                                --%>
							<a href="${ctx}/cms/cmsPortalComments/delete?id=${cmsPortalComments.id}" onclick="return confirmx('确认要删除吗？', this.href)">删除</a>
						</td></shiro:hasPermission>
					</c:when>
					<c:otherwise>
						<shiro:hasPermission name="cms:cmsPortalComments:edit"><td>
							<a href="${ctx}/cms/cmsPortalComments/form?id=${cmsPortalComments.id}">审核</a>
							<a href="${ctx}/cms/cmsPortalComments/delete?id=${cmsPortalComments.id}" onclick="return confirmx('确认要删除吗？', this.href)">删除</a>
						</td></shiro:hasPermission>
					</c:otherwise>
				</c:choose>
				<%--<shiro:hasPermission name="cms:cmsPortalComments:edit"><td>--%>
    				<%--<a href="${ctx}/cms/cmsPortalComments/form?id=${cmsPortalComments.id}">审核</a>--%>
					<%--<a href="${ctx}/cms/cmsPortalComments/delete?id=${cmsPortalComments.id}" onclick="return confirmx('确认要删除该门户评论吗？', this.href)">删除</a>--%>
				<%--</td></shiro:hasPermission>--%>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>