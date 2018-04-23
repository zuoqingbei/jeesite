<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>活动管理</title>
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
		<li class="active"><a href="${ctx}/cms/cmsActivity/list">活动列表</a></li>
		<li class="active"><a href="${ctx}/cms/cmsActivity/form">活动添加</a></li>
		<%-- <shiro:hasPermission name="cms:cmsActivity:edit"><li><a href="${ctx}/cms/cmsActivity/form">活动添加</a></li></shiro:hasPermission> --%>
	</ul>
	<form:form id="searchForm" modelAttribute="cmsActivity" action="${ctx}/cms/cmsActivity/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>标题:</label>
				<form:input path="title" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>标签:</label>
				<form:select path="tags" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('tags_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>活动地点:</label>
				<form:input path="address" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>活动开始时间:</label>
				<input name="startDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${cmsActivity.startDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>活动创建人</th>
				<th>标题</th>
				<th>标签</th>
				<th>报名人数</th>
				<th>活动主办方</th>
				<th>活动举办地点</th>
				<th>活动开始时间</th>
				<th>活动结束时间</th>
				<th>报名开始时间</th>
				<th>报名结束时间</th>
				<th>操作</th>
				<%-- <shiro:hasPermission name="cms:cmsActivity:edit"><th>操作</th></shiro:hasPermission> --%>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="cmsActivity">
			<tr>
				<td><a href="${ctx}/cms/cmsActivity/form?id=${cmsActivity.id}">
					${cmsActivity.user.name}
				</a></td>
				<td>
					${cmsActivity.title}
				</td>
				<td>
					${fns:getDictLabel(cmsActivity.tags, 'tags_type', '')}
				</td>
				<td>
					${cmsActivity.enter}
				</td>
				<td>
					${cmsActivity.leader}
				</td>
				<td>
					${cmsActivity.address}
				</td>
				<td>
					<fmt:formatDate value="${cmsActivity.startDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${cmsActivity.endDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${cmsActivity.enterStartDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${cmsActivity.enterEndDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
    				<a href="${ctx}/cms/cmsActivity/form?id=${cmsActivity.id}">修改</a>&nbsp;&nbsp;
					<a href="${ctx}/cms/cmsActivity/delete?id=${cmsActivity.id}" onclick="return confirmx('确认要删除该活动吗？', this.href)">删除</a>
				</td>
				<%-- <shiro:hasPermission name="cms:cmsActivity:edit"><td>
    				<a href="${ctx}/cms/cmsActivity/form?id=${cmsActivity.id}">修改</a>
					<a href="${ctx}/cms/cmsActivity/delete?id=${cmsActivity.id}" onclick="return confirmx('确认要删除该活动吗？', this.href)">删除</a>
				</td></shiro:hasPermission> --%>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>