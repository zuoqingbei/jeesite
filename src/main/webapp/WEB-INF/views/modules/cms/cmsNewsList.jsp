<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>资讯管理</title>
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
		<li class="active"><a href="${ctx}/cms/cmsNews/list">资讯列表</a></li>
		<shiro:hasPermission name="cms:cmsNews:edit"><li><a href="${ctx}/cms/cmsNews/form">资讯添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="cmsNews" action="${ctx}/cms/cmsNews/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>资讯类型</label>
			    
				<form:select path="tags" class="input-medium">
				    <form:option value="" label="全部"/>
					<form:options items="${fns:getDictList('news_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>标题</label>
				<form:input path="title" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			
			<li><label>创建时间</label>
				<input name="createDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${cmsNews.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
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
				<th>文章图片</th>
				<th>资讯类型</th>
				<th>发布时间</th>
				<shiro:hasPermission name="cms:cmsNews:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="cmsNews">
			<tr>
				<td>
				<a href="${ctx}/cms/cmsNews/form?id=${cmsNews.id}">${cmsNews.title}</a>
					
				</td>
				<td>
					<c:if test="${ not empty cmsNews.image}">
						<img src="${cmsNews.image}" style="width:80px;height:80px;"/>
					</c:if>
				</td>
				<td>
					${fns:getDictLabel(cmsNews.tags, 'news_type', '')}
				</td>
				<td>
					<fmt:formatDate value="${cmsNews.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="cms:cmsNews:edit"><td>
    				<a href="${ctx}/cms/cmsNews/form?id=${cmsNews.id}">修改</a>
					<a href="${ctx}/cms/cmsNews/delete?id=${cmsNews.id}" onclick="return confirmx('确认要删除该资讯吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>