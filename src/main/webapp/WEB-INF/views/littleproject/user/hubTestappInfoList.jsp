<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>基础信息维护管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(function() {

	});
	function page(n, s) {
		$("#pageNo").val(n);
		$("#pageSize").val(s);
		$("#searchForm").submit();
		return false;
	}
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/user/hubTestappInfo/list">基础信息维护列表</a></li>
		<shiro:hasPermission name="user:hubTestappInfo:edit">
			<li><a href="${ctx}/user/hubTestappInfo/form">基础信息维护添加</a></li>
		</shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="hubTestappInfo"
		action="${ctx}/user/hubTestappInfo/list" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<ul class="ul-form">
			<li><label>姓名：</label> <form:input path="fname"
					htmlEscape="false" maxlength="60" class="input-medium" /></li>
			<li><label>电话：</label> <form:input path="ftel"
					htmlEscape="false" maxlength="20" class="input-medium" /></li>
			<li><label>状态：</label> <form:input path="status"
					htmlEscape="false" maxlength="2" class="input-medium" /></li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary"
				type="submit" value="查询" /></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}" />
	<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>姓名</th>
				<th>电话</th>
				<th>状态</th>
				<th>创建时间</th>
				<th>更新时间</th>
				<shiro:hasPermission name="user:hubTestappInfo:edit">
					<th>操作</th>
				</shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="hubTestappInfo">
				<tr>
					<td><a
						href="${ctx}/user/hubTestappInfo/form?ftel=${hubTestappInfo.ftel}">
							${hubTestappInfo.fname} </a></td>
					<td>${hubTestappInfo.ftel}</td>
					<td>${hubTestappInfo.status}</td>
					<td><fmt:formatDate value="${hubTestappInfo.createDate}" type="both" />
						 </td>
					<td>
					<fmt:formatDate value="${hubTestappInfo.updateDate}" type="both" />
					</td>
					<shiro:hasPermission name="user:hubTestappInfo:edit">
						<td><a
							href="${ctx}/user/hubTestappInfo/delete?ftel=${hubTestappInfo.ftel}"
							onclick="return confirmx('确认要删除该基础信息维护吗？', this.href)">删除</a></td>
					</shiro:hasPermission>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>