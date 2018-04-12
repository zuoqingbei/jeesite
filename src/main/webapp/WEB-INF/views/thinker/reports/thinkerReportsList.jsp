<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>报表管理管理</title>
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
		<li class="active"><a href="${ctx}/reports/thinkerReports/list">报表管理列表</a></li>
		<shiro:hasPermission name="reports:thinkerReports:edit"><li><a href="${ctx}/reports/thinkerReports/form">报表管理添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="thinkerReports" action="${ctx}/reports/thinkerReports/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>category1：</label>
				<form:input path="category1" htmlEscape="false" maxlength="400" class="input-medium"/>
			</li>
			<li><label>category2：</label>
				<form:input path="category2" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>category3：</label>
				<form:input path="category3" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>维度：</label>
				<form:input path="dimension" htmlEscape="false" maxlength="400" class="input-medium"/>
			</li>
			<li><label>标签：</label>
				<form:input path="tags" htmlEscape="false" maxlength="400" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>显示系统</th>
				<th>显示系统连接地址</th>
				<th>打开类型 _balnk _target</th>
				<th>名称</th>
				<th>报表类型 字典表</th>
				<th>报表路径</th>
				<th>报表地址</th>
				<th>category1</th>
				<th>category2</th>
				<th>category3</th>
				<th>维度</th>
				<th>标签</th>
				<th>更新时间</th>
				<th>备注</th>
				<shiro:hasPermission name="reports:thinkerReports:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="thinkerReports">
			<tr>
				<td><a href="${ctx}/reports/thinkerReports/form?id=${thinkerReports.id}">
					${thinkerReports.systemName}
				</a></td>
				<td>
					${thinkerReports.link}
				</td>
				<td>
					${thinkerReports.openType}
				</td>
				<td>
					${thinkerReports.name}
				</td>
				<td>
					${thinkerReports.types}
				</td>
				<td>
					${thinkerReports.path}
				</td>
				<td>
					${thinkerReports.url}
				</td>
				<td>
					${thinkerReports.category1}
				</td>
				<td>
					${thinkerReports.category2}
				</td>
				<td>
					${thinkerReports.category3}
				</td>
				<td>
					${thinkerReports.dimension}
				</td>
				<td>
					${thinkerReports.tags}
				</td>
				<td>
					<fmt:formatDate value="${thinkerReports.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${thinkerReports.remarks}
				</td>
				<shiro:hasPermission name="reports:thinkerReports:edit"><td>
    				<a href="${ctx}/reports/thinkerReports/form?id=${thinkerReports.id}">修改</a>
					<a href="${ctx}/reports/thinkerReports/delete?id=${thinkerReports.id}" onclick="return confirmx('确认要删除该报表管理吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>