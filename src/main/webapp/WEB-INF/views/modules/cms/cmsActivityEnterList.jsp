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
		<li class="active"><a href="${ctx}/cms/cmsActivityEnter/">活动报名记录列表</a></li>
		<shiro:hasPermission name="cms:cmsActivityEnter:edit"><li><a href="${ctx}/cms/cmsActivityEnter/form">活动报名记录添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="cmsActivityEnter" action="${ctx}/cms/cmsActivityEnter/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>报名时间：</label>
				<input name="createDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${cmsActivityEnter.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
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
				<th>报名时间</th>
				<th>活动开始后签到情况 0-未签到 1-已签到</th>
				<th>remarks</th>
				<shiro:hasPermission name="cms:cmsActivityEnter:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="cmsActivityEnter">
			<tr>
				<td><a href="${ctx}/cms/cmsActivityEnter/form?id=${cmsActivityEnter.id}">
					<fmt:formatDate value="${cmsActivityEnter.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</a></td>
				<td>
					${cmsActivityEnter.sign}
				</td>
				<td>
					${cmsActivityEnter.remarks}
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