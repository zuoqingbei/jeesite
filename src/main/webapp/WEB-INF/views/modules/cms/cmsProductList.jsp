<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>产品管理管理</title>
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
		<li class="active"><a href="${ctx}/cms/cmsProduct/list">产品管理列表</a></li>
		<shiro:hasPermission name="cms:cmsProduct:edit"><li><a href="${ctx}/cms/cmsProduct/form">产品管理添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="cmsProduct" action="${ctx}/cms/cmsProduct/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>产品名称：</label>
				<form:input path="title" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>产品分类：</label>
				<form:select path="tags" class="input-medium">
					<form:option value="" label="全部"/>
					<form:options items="${fns:getDictList('product_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>创建时间：</label>
				<input name="beginCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${cmsProduct.beginCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/> - 
				<input name="endCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${cmsProduct.endCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>产品名称</th>
				<th>产品图片</th>
				<th>产品分类</th>
				<th>是否推荐</th>
				<th>排序</th>
				<th>发布时间</th>
				<shiro:hasPermission name="cms:cmsProduct:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="cmsProduct">
			<tr>
				<td><a href="${ctx}/cms/cmsProduct/form?id=${cmsProduct.id}">
					${cmsProduct.title}
				</a></td>
				<td style="width:80px;height:80px;">
					<img src="${cmsProduct.image}"></img>
				</td>
				<td>
					${fns:getDictLabel(cmsProduct.tags, 'product_type', '')}
				</td>
				<td>
					${fns:getDictLabel(cmsProduct.recommend, 'top_type', '')}
				</td>
				<td>
					${cmsProduct.orderNo}
				</td>
				<td>
					<fmt:formatDate value="${cmsProduct.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="cms:cmsProduct:edit"><td>
    				<a href="${ctx}/cms/cmsProduct/form?id=${cmsProduct.id}">修改</a>
					<a href="${ctx}/cms/cmsProduct/delete?id=${cmsProduct.id}" onclick="return confirmx('确认要删除该产品吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>