<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>活动日程管理</title>
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
		<li class="active"><a href="${ctx}/cms/cmsActivityFlow/list">活动日程列表</a></li>
		<%--<shiro:hasPermission name="cms:cmsActivityFlow:edit"><li><a href="${ctx}/cms/cmsActivityFlow/form">活动日程添加</a></li></shiro:hasPermission>--%>
	</ul>
	<form:form id="searchForm" modelAttribute="cmsActivityFlow" action="${ctx}/cms/cmsActivityFlow/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>标题：</label>
				<form:input path="title" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<%--<li><label>当前流程开始时间：</label>--%>
				<%--<input name="flowStartTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"--%>
					<%--value="<fmt:formatDate value="${cmsActivityFlow.flowStartTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"--%>
					<%--onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>--%>
			<%--</li>--%>
			<%--<li><label>流程结束时间：</label>--%>
				<%--<input name="flowEndTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"--%>
					<%--value="<fmt:formatDate value="${cmsActivityFlow.flowEndTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"--%>
					<%--onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>--%>
			<%--</li>--%>
			<%--<li><label>收藏时间：</label>--%>
				<%--<input name="createDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"--%>
					<%--value="<fmt:formatDate value="${cmsActivityFlow.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"--%>
					<%--onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>--%>
			<%--</li>--%>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>标题</th>
				<th>图片</th>
				<th>流程结束时间</th>
				<th>流程排序</th>
				<th>当前流程内容介绍 支持HTML</th>
				<th>创建人</th>
				<th>收藏时间</th>
				<%--<th>remarks</th>--%>
				<shiro:hasPermission name="cms:cmsActivityFlow:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="cmsActivityFlow">
			<tr>
				<td><a href="${ctx}/cms/cmsActivityFlow/form?id=${cmsActivityFlow.id}">
					${cmsActivityFlow.title}
				</a></td>
				<td>
					<img src="${cmsActivityFlow.images}" style="width:80px;height:80px;"/>

				</td>
				<td>
					<fmt:formatDate value="${cmsActivityFlow.flowEndTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${cmsActivityFlow.orderNum}
				</td>
				<td>
					${cmsActivityFlow.descs}
				</td>
				<td>
					${cmsActivityFlow.createBy.id}
				</td>
				<td>
					<fmt:formatDate value="${cmsActivityFlow.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<%--<td>--%>
					<%--${cmsActivityFlow.remarks}--%>
				<%--</td>--%>
				<shiro:hasPermission name="cms:cmsActivityFlow:edit"><td>
    				<%--<a href="${ctx}/cms/cmsActivityFlow/form?id=${cmsActivityFlow.id}">修改</a>--%>
					<a href="${ctx}/cms/cmsActivityFlow/delete?id=${cmsActivityFlow.id}" onclick="return confirmx('确认要删除该活动日程吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>