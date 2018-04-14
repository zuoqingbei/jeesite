<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>问答表管理</title>
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
		<li class="active"><a href="${ctx}/cms/cmsQuestionAnswer/list">问答表列表</a></li>
		<shiro:hasPermission name="cms:cmsQuestionAnswer:edit"><li><a href="${ctx}/cms/cmsQuestionAnswer/form">问答表添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="cmsQuestionAnswer" action="${ctx}/cms/cmsQuestionAnswer/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>标题：</label>
				<form:input path="title" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<%--<li><label>标签，多个 用&ldquo;，&rdquo;分开并且开头结尾也是逗号,比如 ,1,2,3,：</label>--%>
				<%--<form:input path="tags" htmlEscape="false" maxlength="255" class="input-medium"/>--%>
			<%--</li>--%>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>数据来源类型</th>
				<th>标题</th>
				<th>文章图片</th>
				<th>更新时间</th>
				<%--<th>备注信息</th>--%>
				<shiro:hasPermission name="cms:cmsQuestionAnswer:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="cmsQuestionAnswer">
			<tr>
				<td>
					<c:if test="${cmsQuestionAnswer.dataType eq '0' }">采集</c:if>
					<c:if test="${cmsQuestionAnswer.dataType eq '1' }">用户投稿</c:if>
					<c:if test="${cmsQuestionAnswer.dataType eq '2' }">管理人员发布</c:if>
				</td>
				<td><a href="${ctx}/cms/cmsQuestionAnswer/form?id=${cmsQuestionAnswer.id}">
					${cmsQuestionAnswer.title}
				</a></td>
				<td>
					<img src="${cmsQuestionAnswer.image}" style="width:80px;height:80px;"/>

				</td>
				<td>
					<fmt:formatDate value="${cmsQuestionAnswer.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<%--<td>--%>
					<%--${cmsQuestionAnswer.remarks}--%>
				<%--</td>--%>
				<shiro:hasPermission name="cms:cmsQuestionAnswer:edit"><td>
    				<a href="${ctx}/cms/cmsQuestionAnswer/form?id=${cmsQuestionAnswer.id}">修改</a>
					<a href="${ctx}/cms/cmsQuestionAnswer/delete?id=${cmsQuestionAnswer.id}" onclick="return confirmx('确认要删除该问答表吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>