<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用户订阅历史管理</title>
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
		<li class="active"><a href="${ctx}/subscribe/accountSubscribeHistory/">用户订阅历史列表</a></li>
		<%-- <shiro:hasPermission name="subscribe:accountSubscribeHistory:edit">
		<li><a href="${ctx}/subscribe/accountSubscribeHistory/form">用户订阅历史添加</a></li>
		</shiro:hasPermission> --%>
	</ul>
	<form:form id="searchForm" modelAttribute="accountSubscribeHistory" action="${ctx}/subscribe/accountSubscribeHistory/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>用户登录名：</label>
				<form:input path="lgoinName" htmlEscape="false" maxlength="100" class="input-medium"/>
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
				<th>关注标签</th>
				<th>关注时间</th>
				<%-- <shiro:hasPermission name="subscribe:accountSubscribeHistory:edit"><th>操作</th></shiro:hasPermission> --%>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="accountSubscribeHistory">
			<tr>
				
				<td>
					${accountSubscribeHistory.lgoinName}
				</td>
				<td>
					${accountSubscribeHistory.label}
				</td>
				<td>
					<fmt:formatDate value="${accountSubscribeHistory.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<%-- <shiro:hasPermission name="subscribe:accountSubscribeHistory:edit"><td>
    				<a href="${ctx}/subscribe/accountSubscribeHistory/form?id=${accountSubscribeHistory.id}">修改</a>
					<a href="${ctx}/subscribe/accountSubscribeHistory/delete?id=${accountSubscribeHistory.id}" onclick="return confirmx('确认要删除该用户订阅历史吗？', this.href)">删除</a>
					</td>
				</shiro:hasPermission> --%>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>