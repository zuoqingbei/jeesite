<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>每日一览管理</title>
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
		<li class="active"><a href="${ctx}/cms/cmsDailyList/list">每日一览列表</a></li>
		<shiro:hasPermission name="cms:cmsDailyList:edit"><li><a href="${ctx}/cms/cmsDailyList/form">每日一览添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="cmsDailyList" action="${ctx}/cms/cmsDailyList/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>标题：</label>
				<form:input path="title" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>创建时间：</label>
				<input name="createDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${cmsDailyList.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>更新时间：</label>
				<input name="updateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${cmsDailyList.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
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
				<th>标题</th>
				<th>图片</th>
				<th>创建时间</th>
				<th>更新时间</th>
				<%--<th>备注</th>--%>
				<shiro:hasPermission name="cms:cmsDailyList:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="cmsDailyList">
			<tr>
				<td><a href="${ctx}/cms/cmsDailyList/form?id=${cmsDailyList.id}">
					${cmsDailyList.title}
				</a></td>
				<td>
					<c:if test="${not empty cmsDailyList.image}">
						<img src="${cmsDailyList.image}"style="width:80px;height:80px;"/>
					</c:if>


				</td>
				<td>
					<fmt:formatDate value="${cmsDailyList.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${cmsDailyList.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<%--<td>--%>
					<%--${cmsDailyList.remarks}--%>
				<%--</td>--%>
				<shiro:hasPermission name="cms:cmsDailyList:edit"><td>
    				<a href="${ctx}/cms/cmsDailyList/form?id=${cmsDailyList.id}">修改</a>
					<a href="${ctx}/cms/cmsDailyList/delete?id=${cmsDailyList.id}" onclick="return confirmx('确认要删除该每日一览吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>