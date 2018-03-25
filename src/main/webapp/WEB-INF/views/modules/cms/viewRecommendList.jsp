<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>推荐管理</title>
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
		<li class="active"><a href="${ctx}/cms/viewRecommend/list">推荐列表</a></li>
		<shiro:hasPermission name="cms:viewRecommend:edit"><li><a href="${ctx}/cms/viewRecommend/form">推荐添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="viewRecommend" action="${ctx}/cms/viewRecommend/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>name：</label>
				<form:input path="name" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>title：</label>
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
				<th>name</th>
				<th>title</th>
				<shiro:hasPermission name="cms:viewRecommend:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="viewRecommend">
			<tr>
				<td><a href="${ctx}/cms/viewRecommend/form?id=${viewRecommend.id}">
					${viewRecommend.name}
				</a></td>
				<td>
					${viewRecommend.title}
				</td>
				<shiro:hasPermission name="cms:viewRecommend:edit"><td>
    				<a href="${ctx}/cms/viewRecommend/form?id=${viewRecommend.id}">修改</a>
					<a href="${ctx}/cms/viewRecommend/delete?id=${viewRecommend.id}" onclick="return confirmx('确认要删除该推荐吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>