<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用户关注管理</title>
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
		<li class="active"><a href="${ctx}/account/accountAttention/">用户关注列表</a></li>
		<%-- <shiro:hasPermission name="account:accountAttention:edit"> --%>
		<%-- <li><a href="${ctx}/account/accountAttention/form">用户关注添加</a></li> --%>
		<%-- </shiro:hasPermission> --%>
	</ul>
	<form:form id="searchForm" modelAttribute="accountAttention" action="${ctx}/account/accountAttention/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>输入登录名：</label>
				<form:input path="fromUserName" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<!-- <th>编号</th> -->
				<th>关注方登录名</th>
				<th>被关注方登录名</th>
				<th>关注时间</th>
				<!-- <th>删除标记 </th> -->
				<%-- <shiro:hasPermission name="account:accountAttention:edit"><th>操作</th></shiro:hasPermission> --%>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="accountAttention">
			<tr>
				<!--  <td><a href="${ctx}/account/accountAttention/form?id=${accountAttention.id}">
					${accountAttention.id}
				</a></td> -->
				<td>
					${accountAttention.fromUserName}
				</td>
				<td>
					${accountAttention.toUserName}
				</td>
				<td>
					<fmt:formatDate value="${accountAttention.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<!--  <td>
					${fns:getDictLabel(accountAttention.delFlag, 'del_flag', '')}
				</td>-->
				<%-- <shiro:hasPermission name="account:accountAttention:edit"><td>
    				<a href="${ctx}/account/accountAttention/form?id=${accountAttention.id}">修改</a>
					<a href="${ctx}/account/accountAttention/delete?id=${accountAttention.id}" onclick="return confirmx('确认要删除该用户关注吗？', this.href)">删除</a>
				</td></shiro:hasPermission> --%>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>