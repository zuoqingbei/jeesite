<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用户分享记录管理</title>
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
		<li class="active"><a href="${ctx}/cms/cmsShare/list">用户分享记录列表</a></li>
		<%--<shiro:hasPermission name="cms:cmsShare:edit"><li><a href="${ctx}/cms/cmsShare/form">用户分享记录添加</a></li></shiro:hasPermission>--%>
	</ul>
	<form:form id="searchForm" modelAttribute="cmsShare" action="${ctx}/cms/cmsShare/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>分享标题：</label>
				<form:input path="title" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<%--<th>赞踩内容源id</th>--%>
				<th>内容来源表</th>
				<th>用户</th>
				<th>分享标题</th>
				<th>分享地址</th>
				<th>分享时间</th>
				<shiro:hasPermission name="cms:cmsShare:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="cmsShare">
			<tr>
				<%--<td>${cmsShare.sourceId}</td>--%>
				<td>
						${cmsShare.sourceTable}
				</td>
				<td>
						${cmsShare.user.name}
				</td>
				<td>${cmsShare.title}</td>
				<td>
					${cmsShare.url}
				</td>
				<td>
					<fmt:formatDate value="${cmsShare.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="cms:cmsShare:edit"><td>
    				<%--<a href="${ctx}/cms/cmsShare/form?id=${cmsShare.id}">修改</a>--%>
					<a href="${ctx}/cms/cmsShare/delete?id=${cmsShare.id}" onclick="return confirmx('确认要删除该用户分享记录吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>