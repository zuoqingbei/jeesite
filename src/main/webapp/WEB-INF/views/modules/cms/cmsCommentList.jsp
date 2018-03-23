<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>评论表管理</title>
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
		<li class="active"><a href="${ctx}/cms/cmsComment/list">评论表列表</a></li>
		<shiro:hasPermission name="cms:cmsComment:edit"><li><a href="${ctx}/cms/cmsComment/form">评论表添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="cmsComment" action="${ctx}/cms/cmsComment/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>栏目内容的标题：</label>
				<form:input path="title" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>评论姓名：</label>
				<form:input path="name" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>栏目编号</th>
				<th>栏目内容的编号</th>
				<th>栏目内容的标题</th>
				<th>评论内容</th>
				<th>评论姓名</th>
				<th>评论IP</th>
				<th>评论时间</th>
				<th>审核人</th>
				<th>审核时间</th>
				<shiro:hasPermission name="cms:cmsComment:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="cmsComment">
			<tr>
				<td><a href="${ctx}/cms/cmsComment/form?id=${cmsComment.id}">
					${cmsComment.categoryId}
				</a></td>
				<td>
					${cmsComment.contentId}
				</td>
				<td>
					${cmsComment.title}
				</td>
				<td>
					${cmsComment.content}
				</td>
				<td>
					${cmsComment.name}
				</td>
				<td>
					${cmsComment.ip}
				</td>
				<td>
					<fmt:formatDate value="${cmsComment.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${cmsComment.auditUserId}
				</td>
				<td>
					<fmt:formatDate value="${cmsComment.auditDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="cms:cmsComment:edit"><td>
    				<a href="${ctx}/cms/cmsComment/form?id=${cmsComment.id}">修改</a>
					<a href="${ctx}/cms/cmsComment/delete?id=${cmsComment.id}" onclick="return confirmx('确认要删除该评论表吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>