<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>轮播图管理管理</title>
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
		<li class="active"><a href="${ctx}/cms/cmsBanner/list">轮播图管理列表</a></li>
		<shiro:hasPermission name="cms:cmsBanner:edit"><li><a href="${ctx}/cms/cmsBanner/form">轮播图管理添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="cmsBanner" action="${ctx}/cms/cmsBanner/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>文字描述：</label>
				<form:input path="title" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>发布日期：</label>
				<input name="beginCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${cmsBanner.beginCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/> - 
				<input name="endCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${cmsBanner.endCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
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
				<th>标题</th>
				<th>介绍</th>
				<th>图片</th>
				<!-- <th>地址</th>
				<th>打开方式</th> -->
				<th>是否置顶</th>
				<th>排序</th>
				<th>发布日期</th>
				<th>创建人</th>
				<!-- <th>备注信息</th> -->
				<shiro:hasPermission name="cms:cmsBanner:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="cmsBanner">
			<tr>
				<td><a href="${ctx}/cms/cmsBanner/form?id=${cmsBanner.id}">
					${cmsBanner.title}
				</a></td>
				<td>
					${cmsBanner.content}
				</td>
				<td style="width:80px;height:80px;">
					<img src="${cmsBanner.image}"></img>
				</td>
				<%-- <td>
					<a href="${cmsBanner.linkUrl}" target="_blank">${cmsBanner.linkUrl}</a>
				</td>
				<td>
					${cmsBanner.target}
				</td> --%>
				<td>
					${fns:getDictLabel(cmsBanner.isTop, 'top_type', '')}
				</td>
				<td>
					${cmsBanner.orderNo}
				</td>
				<td>
					<fmt:formatDate value="${cmsBanner.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${cmsBanner.userName}
				</td>
				<%-- <td>
					${cmsBanner.remarks}
				</td> --%>
				<shiro:hasPermission name="cms:cmsBanner:edit"><td>
    				<a href="${ctx}/cms/cmsBanner/form?id=${cmsBanner.id}">修改</a>
					<a href="${ctx}/cms/cmsBanner/delete?id=${cmsBanner.id}" onclick="return confirmx('确认要删除该轮播图管理吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>