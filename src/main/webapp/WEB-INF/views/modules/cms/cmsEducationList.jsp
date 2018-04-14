<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>客户案例管理</title>
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
		<li class="active"><a href="${ctx}/cms/cmsEducation/list">客户案例列表</a></li>
		<li><a href="${ctx}/cms/cmsEducation/form">客户案例添加</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="cmsEducation" action="${ctx}/cms/cmsEducation/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>案例类型</label>
			    
				<form:select path="tags" class="input-medium">
				    <form:option value="" label="全部"/>
					<form:options items="${fns:getDictList('customer_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>标题：</label>
				<form:input path="title" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>发布时间：</label>
				<input name="createDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${cmsEducation.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
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
				<th>案例图片</th>
				<th>案例类型</th>
				<th>是否推荐</th>
				<th>上传时间</th>
				<shiro:hasPermission name="cms:cmsEducation:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="cmsEducation">
			<tr>
				<td><a href="${ctx}/cms/cmsEducation/form?id=${cmsEducation.id}">
					${cmsEducation.title}
				</a></td>
				<td>
					<c:if test="${ not empty cmsEducation.image}">
						<img src="${cmsEducation.image}" style="width:80px;height:80px;"/>
					</c:if>
				</td>
				<td>
					${fns:getDictLabel(cmsEducation.tags, 'customer_type', '')}
				</td>
				
				<td>
					${fns:getDictLabel(cmsEducation.recommend, 'top_type', '')}
				</td>
				
				<td>
					<fmt:formatDate value="${cmsEducation.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
			
				<shiro:hasPermission name="cms:cmsEducation:edit"><td>
    				<a href="${ctx}/cms/cmsEducation/form?id=${cmsEducation.id}">修改</a>
					<a href="${ctx}/cms/cmsEducation/delete?id=${cmsEducation.id}" onclick="return confirmx('确认要删除该案例教育吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>