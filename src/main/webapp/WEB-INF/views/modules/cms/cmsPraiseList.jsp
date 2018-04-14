<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用户赞 踩记录管理</title>
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
		<li class="active"><a href="${ctx}/cms/cmsPraise/list">用户赞 踩记录列表</a></li>
		<%--<shiro:hasPermission name="cms:cmsPraise:edit"><li><a href="${ctx}/cms/cmsPraise/form">用户赞 踩记录添加</a></li></shiro:hasPermission>--%>
	</ul>
	<form:form id="searchForm" modelAttribute="cmsPraise" action="${ctx}/cms/cmsPraise/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<%--<li><label>赞踩内容源id：</label>--%>
				<%--<form:input path="sourceId" htmlEscape="false" maxlength="64" class="input-medium"/>--%>
			<%--</li>--%>
			<li><label>内容来源表：</label>
				<form:input path="sourceTable" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<%--<li><label>用户id：</label>--%>
				<%--<sys:treeselect id="user" name="user.id" value="${cmsPraise.user.id}" labelName="user.name" labelValue="${cmsPraise.user.name}"--%>
					<%--title="用户" url="/sys/office/treeData?type=3" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>--%>
			<%--</li>--%>
			<li><label>操作类型：</label>
				<form:select hidden="true" path="operateType" class="input-xlarge " >
					<form:option value="" label=""/>
					<form:option value="0" label="踩"/>
					<form:option value="1" label="赞"/>
					<%--<form:option value="2" label="管理人员发布"/>--%>
				</form:select>
			</li>
			<li><label>收藏时间：</label>
				<input name="createDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${cmsPraise.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
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
				<%--<th>赞踩内容源id</th>--%>
				<th>内容来源表</th>
				<th>用户</th>
				<th>操作类型</th>
				<th>收藏时间</th>
				<shiro:hasPermission name="cms:cmsPraise:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="cmsPraise">
			<tr>
				<%--<td>--%>
						<%--${cmsPraise.sourceId}--%>
					<%--&lt;%&ndash;<a href="${ctx}/cms/cmsPraise/form?id=${cmsPraise.id}">&ndash;%&gt;--%>
					<%--&lt;%&ndash;${cmsPraise.sourceId}&ndash;%&gt;--%>
				<%--&lt;%&ndash;</a>&ndash;%&gt;--%>
				<%--</td>--%>
				<td>
					${cmsPraise.sourceTable}
				</td>
				<td>
					${cmsPraise.user.name}
				</td>
				<td>
					<c:if test="${cmsPraise.operateType eq '0'}">踩</c:if>
					<c:if test="${cmsPraise.operateType eq '1'}">赞</c:if>

				</td>
				<td>
					<fmt:formatDate value="${cmsPraise.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="cms:cmsPraise:edit"><td>
    				<%--<a href="${ctx}/cms/cmsPraise/form?id=${cmsPraise.id}">修改</a>--%>
					<a href="${ctx}/cms/cmsPraise/delete?id=${cmsPraise.id}" onclick="return confirmx('确认要删除该用户赞 踩记录吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>