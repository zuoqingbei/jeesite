<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>活动报名记录管理</title>
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
		<li class="active"><a href="${ctx}/cms/cmsActivityEnter/list">活动报名记录列表</a></li>
		<shiro:hasPermission name="cms:cmsActivityEnter:edit"><li><a href="${ctx}/cms/cmsActivityEnter/form">活动报名记录添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="cmsActivityEnter" action="${ctx}/cms/cmsActivityEnter/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>编号：</label>
				<form:input path="id" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>活动ID：</label>
				<form:input path="activityId" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>报名人员id：</label>
				<sys:treeselect id="user" name="user.id" value="${cmsActivityEnter.user.id}" labelName="user.name" labelValue="${cmsActivityEnter.user.name}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>编号</th>
				<th>活动ID</th>
				<th>报名人员id</th>
				<th>报名时间</th>
				<th>活动开始后签到情况 0-未签到 1-已签到</th>
				<th>remarks</th>
				<th>删除标记 0-未删除 1-已删除</th>
				<shiro:hasPermission name="cms:cmsActivityEnter:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="cmsActivityEnter">
			<tr>
				<td><a href="${ctx}/cms/cmsActivityEnter/form?id=${cmsActivityEnter.id}">
					${cmsActivityEnter.id}
				</a></td>
				<td>
					${cmsActivityEnter.activityId}
				</td>
				<td>
					${cmsActivityEnter.user.name}
				</td>
				<td>
					<fmt:formatDate value="${cmsActivityEnter.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${cmsActivityEnter.sign}
				</td>
				<td>
					${cmsActivityEnter.remarks}
				</td>
				<td>
					${fns:getDictLabel(cmsActivityEnter.delFlag, 'del_flag', '')}
				</td>
				<shiro:hasPermission name="cms:cmsActivityEnter:edit"><td>
    				<a href="${ctx}/cms/cmsActivityEnter/form?id=${cmsActivityEnter.id}">修改</a>
					<a href="${ctx}/cms/cmsActivityEnter/delete?id=${cmsActivityEnter.id}" onclick="return confirmx('确认要删除该活动报名记录吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>