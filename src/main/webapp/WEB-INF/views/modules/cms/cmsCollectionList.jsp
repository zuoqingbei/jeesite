<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用户收藏记录管理</title>
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
		<li class="active"><a href="${ctx}/cms/cmsCollection/list">用户收藏记录列表</a></li>
		<%--<shiro:hasPermission name="cms:cmsCollection:edit"><li><a href="${ctx}/cms/cmsCollection/form">用户收藏记录添加</a></li></shiro:hasPermission>--%>
	</ul>
	<form:form id="searchForm" modelAttribute="cmsCollection" action="${ctx}/cms/cmsCollection/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>内容来源表：</label>
				<form:input path="sourceTable" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>收藏时间：</label>
				<input name="createDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value="<fmt:formatDate value="${cmsPraise.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
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
				<%--<th>收藏内容源id</th>--%>
				<th>内容来源表</th>
				<th>用户</th>
				<th>收藏时间</th>
				<shiro:hasPermission name="cms:cmsCollection:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="cmsCollection">
			<tr>
				<%--<td>--%>
					<%--&lt;%&ndash;<a href="${ctx}/cms/cmsCollection/form?id=${cmsCollection.id}">&ndash;%&gt;--%>
					<%--&lt;%&ndash;${cmsCollection.sourceId}&ndash;%&gt;--%>
				<%--&lt;%&ndash;</a>&ndash;%&gt;--%>
							<%--${cmsCollection.sourceId}--%>

				<%--</td>--%>
				<td>
					${cmsCollection.sourceTable}
				</td>
				<td>
					${cmsCollection.user.name}
				</td>
				<td>
					<fmt:formatDate value="${cmsCollection.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="cms:cmsCollection:edit"><td>
    				<%--<a href="${ctx}/cms/cmsCollection/form?id=${cmsCollection.id}">修改</a>--%>
					<a href="${ctx}/cms/cmsCollection/delete?id=${cmsCollection.id}" onclick="return confirmx('确认要删除该用户收藏记录吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>