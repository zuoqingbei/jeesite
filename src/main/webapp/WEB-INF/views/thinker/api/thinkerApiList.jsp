<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>api管理管理</title>
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
		<li class="active"><a href="${ctx}/api/thinkerApi/list">api管理列表</a></li>
		<shiro:hasPermission name="api:thinkerApi:edit"><li><a href="${ctx}/api/thinkerApi/form">api管理添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="thinkerApi" action="${ctx}/api/thinkerApi/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>分类：</label>
				<form:input path="category1" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>category2：</label>
				<form:input path="category2" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>category3：</label>
				<form:input path="category3" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>名称</th>
				<th>是否支持https  0-不支持 1-支持</th>
				<th>描述</th>
				<th>数据ID</th>
				<th>应用数</th>
				<th>接口状态 0-正常 1-故障</th>
				<th>分类</th>
				<th>category2</th>
				<th>category3</th>
				<th>标签</th>
				<th>接口地址</th>
				<th>请求类型</th>
				<th>数据类型 json xml其实可以支持多种</th>
				<th>样例地址</th>
				<th>json返回样例 html格式</th>
				<th>xml返回样例 html格式</th>
				<th>创建人</th>
				<th>创建时间</th>
				<th>更新时间</th>
				<th>备注</th>
				<shiro:hasPermission name="api:thinkerApi:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="thinkerApi">
			<tr>
				<td><a href="${ctx}/api/thinkerApi/form?id=${thinkerApi.id}">
					${thinkerApi.name}
				</a></td>
				<td>
					${thinkerApi.https}
				</td>
				<td>
					${thinkerApi.descs}
				</td>
				<td>
					${thinkerApi.dataId}
				</td>
				<td>
					${thinkerApi.appNum}
				</td>
				<td>
					${thinkerApi.status}
				</td>
				<td>
					${thinkerApi.category1}
				</td>
				<td>
					${thinkerApi.category2}
				</td>
				<td>
					${thinkerApi.category3}
				</td>
				<td>
					${thinkerApi.tags}
				</td>
				<td>
					${thinkerApi.url}
				</td>
				<td>
					${thinkerApi.requestType}
				</td>
				<td>
					${thinkerApi.dataType}
				</td>
				<td>
					${thinkerApi.demoUrl}
				</td>
				<td>
					${thinkerApi.jsonDemo}
				</td>
				<td>
					${thinkerApi.xmlDemo}
				</td>
				<td>
					${thinkerApi.createBy.id}
				</td>
				<td>
					<fmt:formatDate value="${thinkerApi.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${thinkerApi.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${thinkerApi.remarks}
				</td>
				<shiro:hasPermission name="api:thinkerApi:edit"><td>
    				<a href="${ctx}/api/thinkerApi/form?id=${thinkerApi.id}">修改</a>
					<a href="${ctx}/api/thinkerApi/delete?id=${thinkerApi.id}" onclick="return confirmx('确认要删除该api管理吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>