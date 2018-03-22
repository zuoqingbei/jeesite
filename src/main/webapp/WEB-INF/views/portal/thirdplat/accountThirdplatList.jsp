<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>第三方账号管理</title>
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
		<li class="active"><a href="${ctx}/thirdplat/accountThirdplat/">第三方账号列表</a></li>
		<%-- <shiro:hasPermission name="thirdplat:accountThirdplat:edit"><li><a href="${ctx}/thirdplat/accountThirdplat/form">第三方账号添加</a></li></shiro:hasPermission> --%>
	</ul>
	<form:form id="searchForm" modelAttribute="accountThirdplat" action="${ctx}/thirdplat/accountThirdplat/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>平台</label>
				<form:select path="ptype" class="input-medium">
					<form:option value="" label=""/>
					<form:option value="wechat" label="微信"/>
					<form:option value="qq" label="QQ"/>
					<form:option value="weibo" label="微博"/>
				</form:select>
			</li>
			<li><label>平台标识码：</label>
				<form:input path="platkey" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>昵称：</label>
				<form:input path="nickName" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<%-- <li><label>省：</label>
				<form:input path="province" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>城市：</label>
				<form:input path="city" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li> --%>
			<li><label>手机号：</label>
				<form:input path="mobile" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<%-- <li><label>国家：</label>
				<form:input path="country" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li> --%>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>昵称</th>
				<th>手机号</th>
				<th>对应uid</th>
				<th>平台类型</th>
				<th>平台标识码</th>
				<th>头像</th>
				<th>性别</th>
				<th>登陆时间</th>
				<%-- <shiro:hasPermission name="thirdplat:accountThirdplat:edit"><th>操作</th></shiro:hasPermission> --%>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="accountThirdplat">
			<tr>
				<%-- <td><a href="${ctx}/thirdplat/accountThirdplat/form?id=${accountThirdplat.id}">
					${accountThirdplat.nickName}
				</a></td> --%>
				<td>
					${accountThirdplat.nickName}
				</td>
				<td>
					${accountThirdplat.mobile}
				</td>
				<td>
					${accountThirdplat.user.id}
				</td>
				<td>
					${accountThirdplat.ptype}
				</td>
				<td>s
					${accountThirdplat.platkey}
				</td>
				<td>
					<img src="${accountThirdplat.image}" style="width:80px;height:80px;"></img>
				</td>
				<td>
					${accountThirdplat.gender}
				</td>
				<td>
					<fmt:formatDate value="${accountThirdplat.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<%-- <shiro:hasPermission name="thirdplat:accountThirdplat:edit"><td>
    				<a href="${ctx}/thirdplat/accountThirdplat/form?id=${accountThirdplat.id}">修改</a>
					<a href="${ctx}/thirdplat/accountThirdplat/delete?id=${accountThirdplat.id}" onclick="return confirmx('确认要删除该第三方账号吗？', this.href)">删除</a>
				</td></shiro:hasPermission> --%>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>