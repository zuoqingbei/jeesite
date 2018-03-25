<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用户活跃度记录管理</title>
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
		<li class="active"><a href="${ctx}/integration/accountIntegrationHistory/list">用户活跃度记录列表</a></li>
		<%-- <shiro:hasPermission name="integration:accountIntegrationHistory:edit"><li><a href="${ctx}/integration/accountIntegrationHistory/form">用户活跃度记录添加</a></li></shiro:hasPermission> --%>
	</ul>
	<form:form id="searchForm" modelAttribute="accountIntegrationHistory" action="${ctx}/integration/accountIntegrationHistory/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>输入登录名：</label>
				<form:input path="loginName" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>用户登录名</th>
				<th>变更后积分</th>
				<th>变更积分</th>
				<th>变更原因</th>
				<th>变更时间</th>
				<%-- <shiro:hasPermission name="integration:accountIntegrationHistory:edit"><th>操作</th></shiro:hasPermission> --%>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="accountIntegrationHistory">
			<tr>
				<td>
					${accountIntegrationHistory.loginName}
				</td>
				<td>
					${accountIntegrationHistory.allIntegration}
				</td>
				<td>
					${accountIntegrationHistory.nums}
				</td>
				<td>
					${accountIntegrationHistory.reason}
				</td>
				<td>
					<fmt:formatDate value="${accountIntegrationHistory.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<%-- <shiro:hasPermission name="integration:accountIntegrationHistory:edit"><td>
    				<a href="${ctx}/integration/accountIntegrationHistory/form?id=${accountIntegrationHistory.id}">修改</a>
					<a href="${ctx}/integration/accountIntegrationHistory/delete?id=${accountIntegrationHistory.id}" onclick="return confirmx('确认要删除该用户活跃度记录吗？', this.href)">删除</a>
				</td></shiro:hasPermission> --%>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>