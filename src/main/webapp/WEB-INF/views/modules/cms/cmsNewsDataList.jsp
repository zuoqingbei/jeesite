<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>资讯详表管理</title>
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
		<li class="active"><a href="${ctx}/cms/cmsNewsData/list">资讯详表列表</a></li>
		<shiro:hasPermission name="cms:cmsNewsData:edit"><li><a href="${ctx}/cms/cmsNewsData/form">资讯详表添加</a></li></shiro:hasPermission>
	</ul>
	<%-- <form:form id="searchForm" modelAttribute="cmsNewsData" action="${ctx}/cms/cmsNewsData/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>编号：</label>
				<form:input path="id" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>源数据ID：</label>
				<form:input path="newsId" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>文章内容 不包含HTML：</label>
				<form:input path="content" htmlEscape="false" class="input-medium"/>
			</li>
			<li><label>文章内容 包含HTML：</label>
				<form:input path="contentHtml" htmlEscape="false" class="input-medium"/>
			</li>
			<li><label>del_flag：</label>
				<form:radiobuttons path="delFlag" items="${fns:getDictList('del_flag')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form> --%>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<!-- <th>编号</th> -->
				<th>标题</th>
				<!-- <th>文章内容 不包含HTML</th>
				<th>文章内容 包含HTML</th>
				<th>del_flag</th> -->
				<shiro:hasPermission name="cms:cmsNewsData:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="cmsNewsData">
			<tr>
				<%-- <td><a href="${ctx}/cms/cmsNewsData/form?id=${cmsNewsData.id}">
					${cmsNewsData.id}
				</a></td> --%>
				<td><a href="${ctx}/cms/cmsNewsData/form?id=${cmsNewsData.id}&titleid=${cmsNewsData.titleid}">
					${cmsNewsData.title}
					</a>
				</td>
				<%-- <td>
					${cmsNewsData.content}
				</td>
				<td>
					${cmsNewsData.contentHtml}
				</td>
				<td>
					${fns:getDictLabel(cmsNewsData.delFlag, 'del_flag', '')}
				</td> --%>
				<shiro:hasPermission name="cms:cmsNewsData:edit"><td>
    				<a href="${ctx}/cms/cmsNewsData/form?id=${cmsNewsData.id}">修改</a>
					<a href="${ctx}/cms/cmsNewsData/delete?id=${cmsNewsData.id}" onclick="return confirmx('确认要删除该资讯详表吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>