<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>热搜管理</title>
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
		<li class="active"><a href="${ctx}/search/thinkerHotSearch/list">热搜列表</a></li>
		<shiro:hasPermission name="search:thinkerHotSearch:edit"><li><a href="${ctx}/search/thinkerHotSearch/form">热搜添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="thinkerHotSearch" action="${ctx}/search/thinkerHotSearch/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>搜索关键字：</label>
				<form:input path="keyword" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>搜索次数：</label>
				<form:input path="nums" htmlEscape="false" maxlength="10" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>搜索关键字</th>
				<th>搜索次数</th>
				<th>创建人</th>
				<th>创建时间</th>
				<th>备注</th>
				<shiro:hasPermission name="search:thinkerHotSearch:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="thinkerHotSearch">
			<tr>
				<td><a href="${ctx}/search/thinkerHotSearch/form?id=${thinkerHotSearch.id}">
					${thinkerHotSearch.keyword}
				</a></td>
				<td>
					${thinkerHotSearch.nums}
				</td>
				<td>
					${thinkerHotSearch.createBy.id}
				</td>
				<td>
					<fmt:formatDate value="${thinkerHotSearch.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${thinkerHotSearch.remarks}
				</td>
				<shiro:hasPermission name="search:thinkerHotSearch:edit"><td>
    				<a href="${ctx}/search/thinkerHotSearch/form?id=${thinkerHotSearch.id}">修改</a>
					<a href="${ctx}/search/thinkerHotSearch/delete?id=${thinkerHotSearch.id}" onclick="return confirmx('确认要删除该热搜吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>