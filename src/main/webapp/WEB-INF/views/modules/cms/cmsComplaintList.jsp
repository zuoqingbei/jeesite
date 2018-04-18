<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>投诉管理</title>
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
		<li class="active"><a href="${ctx}/cms/cmsComplaint/list">举报列表</a></li>
		<shiro:hasPermission name="cms:cmsComplaint:edit"><li><a href="${ctx}/cms/cmsComplaint/form">举报添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="cmsComplaint" action="${ctx}/cms/cmsComplaint/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>标题/内容：</label>
				<form:input path="title" htmlEscape="false" maxlength="500" class="input-medium"/>
			</li>
			<li><label>举报对象：</label>
				<form:input path="companyName" htmlEscape="false" maxlength="64" class="input-medium"/>
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
				<th>举报人</th>
			    <th>联系方式</th>
				<th>图片</th>
				<th>数据来源</th>
				<th>举报对象</th>
				<th>发现地点</th>
				<th>发现时间</th>
				<th>状态</th>
				<th>内容</th>
				<!-- <th>备注</th> -->
				<shiro:hasPermission name="cms:cmsComplaint:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="cmsComplaint">
			<tr>
				<td><a href="${ctx}/cms/cmsComplaint/form?id=${cmsComplaint.id}">
					${cmsComplaint.title}
				</a></td>
				<td>
					${cmsComplaint.user.name}
				</td>
				<td>
					${cmsComplaint.remarks}
				</td>
				<td>
					<%-- <c:forEach items="${cmsComplaint.images}" var="img">
					${img}
					</c:forEach> --%>
					    <c:forTokens var="name" items="${cmsComplaint.image}" delims=",">
				               <img src="${name}" style="width:80px;height:80px;"/>      
				        </c:forTokens> 
				</td>
				<td>
					${cmsComplaint.source}
				</td>
				<td>
					${cmsComplaint.companyName}
				</td>
				<td>
					${cmsComplaint.companyAddress}
				</td>
				<td>
					<fmt:formatDate value="${cmsComplaint.findDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${fns:getDictLabel(cmsComplaint.status, 'act_process', '')}
				</td>
				<td>
					${cmsComplaint.content}
				</td>
			
				<%-- <td>
					${cmsComplaint.remarks}
				</td> --%>
				<shiro:hasPermission name="cms:cmsComplaint:edit"><td>
    				<a href="${ctx}/cms/cmsComplaint/form?id=${cmsComplaint.id}">修改</a>
					<a href="${ctx}/cms/cmsComplaint/delete?id=${cmsComplaint.id}" onclick="return confirmx('确认要删除该投诉吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>