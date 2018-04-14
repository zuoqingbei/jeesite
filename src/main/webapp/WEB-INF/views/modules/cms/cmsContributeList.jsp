<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用户投稿管理</title>
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
		<li class="active"><a href="${ctx}/cms/cmsContribute/list">用户投稿列表</a></li>
		<%--<shiro:hasPermission name="cms:cmsContribute:edit"><li><a href="${ctx}/cms/cmsContribute/form">用户投稿添加</a></li></shiro:hasPermission>--%>
	</ul>
	<form:form id="searchForm" modelAttribute="cmsContribute" action="${ctx}/cms/cmsContribute/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<%-- <li><label>编号：</label>
				<form:input path="id" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li> --%>
			<%--<li><label>源链接：</label>--%>
				<%--<form:input path="link" htmlEscape="false" maxlength="255" class="input-medium"/>--%>
			<%--</li>--%>
			<li><label>投稿类型 ：</label>
				<form:select path="dataType" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:option value="0" label="资讯"/>
					<form:option value="1" label="案例"/>
					<form:option value="2" label="投资教育"/>
					<form:option value="3" label="问答"/>
				</form:select>
			</li>
		<%-- 	<li><label>投稿用户id：</label>
				<sys:treeselect id="user" name="user.id" value="${cmsContribute.user.id}" labelName="user.name" labelValue="${cmsContribute.user.name}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
			</li> --%>
			<li><label>标题：</label>
				<form:input path="title" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<%-- <li><label>内容 包含HTML标签：</label>
				<form:input path="contentHtml" htmlEscape="false" class="input-medium"/>
			</li>
			<li><label>内容 不包含HTML标签：</label>
				<form:input path="content" htmlEscape="false" class="input-medium"/>
			</li> --%>
			<%-- <li><label>图片：</label>
				<form:input path="image" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li> --%>
			<%--<li><label>关键字：</label>--%>
				<%--<form:input path="keywords" htmlEscape="false" maxlength="255" class="input-medium"/>--%>
			<%--</li>--%>
			<%-- <li><label>标签，多个 用&ldquo;，&rdquo;分开并且开头结尾也是逗号,比如 ,1,2,3,：</label>
				<form:input path="tags" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>描述、摘要：</label>
				<form:input path="description" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li> --%>
			<%--<li><label>创建时间：</label>--%>
				<%--<input name="createDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"--%>
					<%--value="<fmt:formatDate value="${cmsContribute.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"--%>
					<%--onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>--%>
			<%--</li>--%>
			<%-- <li><label>更新者：</label>
				<form:input path="updateBy.id" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li> --%>
			<li><label>更新时间：</label>
				<input name="updateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${cmsContribute.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>状态：</label>
			<form:select path="status" class="input-xlarge " style="width:100">
					<form:option value="" label=""/>
					<form:option value="0" label="草稿"/>
					<form:option value="1" label="已投稿,审核中"/>
					<form:option value="2" label="审核通过，发布"/>
					<form:option value="3" label="驳回"/>
				</form:select>
			</li>
			<%-- <li><label>备注信息：</label>
				<form:input path="remarks" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li> --%>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<!-- <th>编号</th> -->
				<th>源链接</th>
				<th>投稿类型</th>
<!-- 				<th>投稿用户id</th>
 -->				<th>标题</th>
				<!-- <th>内容 包含HTML标签</th>
				<th>内容 不包含HTML标签</th> -->
				<th>图片</th>
				<!-- <th>关键字</th>
				<th>标签，多个 用&ldquo;，&rdquo;分开并且开头结尾也是逗号,比如 ,1,2,3,</th>
				<th>描述、摘要</th> -->
				<th>创建时间</th>
				<!-- <th>更新者</th> -->
				<th>更新时间</th>
				<th>状态</th>
<!-- 				<th>备注信息</th>
 -->				<shiro:hasPermission name="cms:cmsContribute:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="cmsContribute">
			<tr>
				<%-- <td><a href="${ctx}/cms/cmsContribute/form?id=${cmsContribute.id}">
					${cmsContribute.id}
				</a></td> --%>
				<td><a href="${cmsContribute.link}">${cmsContribute.link}</a>
					
				</td>
				<td>
				<c:if test="${cmsContribute.dataType eq '0'}">资讯</c:if>
				<c:if test="${cmsContribute.dataType eq '1'}">案例</c:if>
				<c:if test="${cmsContribute.dataType eq '2'}">投资教育</c:if>
				<c:if test="${cmsContribute.dataType eq '3'}">问答</c:if>
				</td>
				<%-- <td>
					${cmsContribute.user.name}
				</td> --%>
				<td><a href="${ctx}/cms/cmsContribute/form?id=${cmsContribute.id}">${cmsContribute.title}</a>
					
				</td>
				<%-- <td>
					${cmsContribute.contentHtml}
				</td>
				<td>
					${cmsContribute.content}
				</td> --%>
				<td>
					<c:if test="${ not empty cmsContribute.image}">
						<img src="${cmsContribute.image}" style="width:80px;height:80px;"/>
					</c:if>

				</td>
				<%-- <td>
					${cmsContribute.keywords}
				</td>
				<td>
					${cmsContribute.tags}
				</td>
				<td>
					${cmsContribute.description}
				</td> --%>
				<td>
					<fmt:formatDate value="${cmsContribute.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<%-- <td>
					${cmsContribute.updateBy.id}
				</td> --%>
				<td>
					<fmt:formatDate value="${cmsContribute.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
				<c:if test="${cmsContribute.status eq '0'}">草稿</c:if>
				<c:if test="${cmsContribute.status eq '1'}">已投稿,审核中</c:if>
				<c:if test="${cmsContribute.status eq '2'}">审核通过，发布</c:if>
				<c:if test="${cmsContribute.status eq '3'}">驳回</c:if>
				</td>
				<%-- <td>
					${cmsContribute.remarks}
				</td> --%>

					<c:choose>
						<c:when test="${cmsContribute.status eq '1'}">
							<shiro:hasPermission name="cms:cmsContribute:edit"><td>
								<a href="${ctx}/cms/cmsContribute/form?id=${cmsContribute.id}">审核</a>
								<a href="${ctx}/cms/cmsContribute/delete?id=${cmsContribute.id}" onclick="return confirmx('确认要删除该用户投稿吗？', this.href)">删除</a>
							</td></shiro:hasPermission>
						</c:when>
						<c:otherwise>
							<shiro:hasPermission name="cms:cmsContribute:edit"><td>
								<%--<a href="${ctx}/cms/cmsContribute/form?id=${cmsContribute.id}">审核</a>--%>
								<a href="${ctx}/cms/cmsContribute/delete?id=${cmsContribute.id}" onclick="return confirmx('确认要删除该用户投稿吗？', this.href)">删除</a>
							</td></shiro:hasPermission>
						</c:otherwise>
					</c:choose>
					<%--<c:if test="${cmsContribute.status ne '2'}>
					<shiro:hasPermission name="cms:cmsContribute:edit"><td>
    				<a href="${ctx}/cms/cmsContribute/form?id=${cmsContribute.id}">审核</a>
					<a href="${ctx}/cms/cmsContribute/delete?id=${cmsContribute.id}" onclick="return confirmx('确认要删除该用户投稿吗？', this.href)">删除</a>
					</td></shiro:hasPermission>
					</c:if>
				<shiro:hasPermission name="cms:cmsContribute:edit"><td>
    				<a href="${ctx}/cms/cmsContribute/form?id=${cmsContribute.id}">审核</a>
					<a href="${ctx}/cms/cmsContribute/delete?id=${cmsContribute.id}" onclick="return confirmx('确认要删除该用户投稿吗？', this.href)">删除</a>
				</td></shiro:hasPermission>--%>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
<script>
</script>
</body>
</html>