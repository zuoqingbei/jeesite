<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>指标管理管理</title>
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
		<li class="active"><a href="${ctx}/indexs/thinkerIndex/list">指标管理列表</a></li>
		<shiro:hasPermission name="indexs:thinkerIndex:edit"><li><a href="${ctx}/indexs/thinkerIndex/form">指标管理添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="thinkerIndex" action="${ctx}/indexs/thinkerIndex/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>指标描述：</label>
				<form:input path="descs" htmlEscape="false" maxlength="1000" class="input-medium"/>
			</li>
			<li><label>一级分类：</label>
				<form:input path="category1" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>二级分类：</label>
				<form:input path="category2" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>三级分类：</label>
				<form:input path="category3" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>标签：</label>
				<form:input path="tags" htmlEscape="false" maxlength="64" class="input-medium"/>
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
				<th>指标编码</th>
				<th>屏幕编号</th>
				<th>屏幕名称</th>
				<th>屏幕地址</th>
				<th>展示形式</th>
				<th>指标描述</th>
				<th>显示表</th>
				<th>使用字段</th>
				<th>计算公式</th>
				<th>一级分类</th>
				<th>二级分类</th>
				<th>三级分类</th>
				<th>标签</th>
				<th>创建人</th>
				<th>浏览量</th>
				<th>创建时间</th>
				<th>点赞量</th>
				<th>更新人</th>
				<th>分享数</th>
				<th>更新时间</th>
				<th>备注</th>
				<shiro:hasPermission name="indexs:thinkerIndex:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="thinkerIndex">
			<tr>
				<td><a href="${ctx}/indexs/thinkerIndex/form?id=${thinkerIndex.id}">
					${thinkerIndex.name}
				</a></td>
				<td>
					${thinkerIndex.code}
				</td>
				<td>
					${thinkerIndex.screenNo}
				</td>
				<td>
					${thinkerIndex.screenName}
				</td>
				<td>
					${thinkerIndex.screenUrl}
				</td>
				<td>
					${thinkerIndex.showType}
				</td>
				<td>
					${thinkerIndex.descs}
				</td>
				<td>
					${thinkerIndex.showTable}
				</td>
				<td>
					${thinkerIndex.useFiled}
				</td>
				<td>
					${thinkerIndex.expression}
				</td>
				<td>
					${thinkerIndex.category1}
				</td>
				<td>
					${thinkerIndex.category2}
				</td>
				<td>
					${thinkerIndex.category3}
				</td>
				<td>
					${thinkerIndex.tags}
				</td>
				<td>
					${thinkerIndex.createBy.id}
				</td>
				<td>
					${thinkerIndex.viewNum}
				</td>
				<td>
					<fmt:formatDate value="${thinkerIndex.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${thinkerIndex.zanNum}
				</td>
				<td>
					${thinkerIndex.updateBy.id}
				</td>
				<td>
					${thinkerIndex.shareNum}
				</td>
				<td>
					<fmt:formatDate value="${thinkerIndex.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${thinkerIndex.remarks}
				</td>
				<shiro:hasPermission name="indexs:thinkerIndex:edit"><td>
    				<a href="${ctx}/indexs/thinkerIndex/form?id=${thinkerIndex.id}">修改</a>
					<a href="${ctx}/indexs/thinkerIndex/delete?id=${thinkerIndex.id}" onclick="return confirmx('确认要删除该指标管理吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>