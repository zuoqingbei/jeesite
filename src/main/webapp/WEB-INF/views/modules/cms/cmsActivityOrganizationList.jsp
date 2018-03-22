<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>活动组织结构管理</title>
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
		<li class="active"><a href="${ctx}/cms/cmsActivityOrganization/">活动组织结构列表</a></li>
		<shiro:hasPermission name="cms:cmsActivityOrganization:edit"><li><a href="${ctx}/cms/cmsActivityOrganization/form">活动组织结构添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="cmsActivityOrganization" action="${ctx}/cms/cmsActivityOrganization/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>企业单位：</label>
				<input name="name" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${cmsActivityOrganization.name}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>联系人姓名：</label>
				<form:input path="contact" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>联系人手机号：</label>
				<form:input path="contactMobile" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>创建时间：</label>
				<input name="createDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${cmsActivityOrganization.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
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
				<th>企业单位</th>
				<th>企业简介</th>
				<th>联系人姓名</th>
				<th>联系人手机号</th>
				<th>企业排序</th>
				<th>创建人</th>
				<th>创建时间</th>
				<th>remarks</th>
				<shiro:hasPermission name="cms:cmsActivityOrganization:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="cmsActivityOrganization">
			<tr>
				<td><a href="${ctx}/cms/cmsActivityOrganization/form?id=${cmsActivityOrganization.id}">
					<fmt:formatDate value="${cmsActivityOrganization.name}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</a></td>
				<td>
					${cmsActivityOrganization.descs}
				</td>
				<td>
					${cmsActivityOrganization.contact}
				</td>
				<td>
					${cmsActivityOrganization.contactMobile}
				</td>
				<td>
					${cmsActivityOrganization.orderNum}
				</td>
				<td>
					${cmsActivityOrganization.createBy.id}
				</td>
				<td>
					<fmt:formatDate value="${cmsActivityOrganization.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${cmsActivityOrganization.remarks}
				</td>
				<shiro:hasPermission name="cms:cmsActivityOrganization:edit"><td>
    				<a href="${ctx}/cms/cmsActivityOrganization/form?id=${cmsActivityOrganization.id}">修改</a>
					<a href="${ctx}/cms/cmsActivityOrganization/delete?id=${cmsActivityOrganization.id}" onclick="return confirmx('确认要删除该活动组织结构吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>