<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>投资教育管理</title>
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
		<li class="active"><a href="${ctx}/cms/cmsEducation/list">投资教育列表</a></li>
		<li><a href="${ctx}/cms/cmsEducation/form">投资教育添加</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="cmsEducation" action="${ctx}/cms/cmsEducation/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>标题：</label>
				<form:input path="title" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>创建时间：</label>
				<input name="createDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${cmsEducation.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/>
			</li>
		<%-- 	<li><label>更新时间：</label>
				<input name="updateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${cmsEducation.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li> --%>
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
				<%--<th>内容 包含HTML</th>--%>
				<%--<th>讲师 对应用户表中id</th>--%>
				<%--<th>权重，越大越靠前</th>--%>
				<%--<th>点击数、阅读数</th>--%>
				<%--<th>转发数 分享数</th>--%>
				<%--<th>评论数  回复数</th>--%>
				<%--<th>赞数量</th>--%>
				<%--<th>踩数量</th>--%>
				<%--<th>收藏量</th>--%>
				<%--<th>举报数量</th>--%>
				<%--<th>评价数量</th>--%>
				<%--<th>打赏次数</th>--%>
				<%--<th>曝光量</th>--%>
				<%--<th>是否推荐 0-普通 1-推荐</th>--%>
				<%--<th>是否允许评论 0-允许 1-不允许</th>--%>
				<%--<th>评论是否需要审核 0-需要 1-不需要</th>--%>
				<%--<th>是否允许举报  0-允许 1-不允许</th>--%>
				<%--<th>内容是否下架 0-未下架 1-已下架</th>--%>
				<th>一级分类</th>
				<th>更新时间</th>
				<%--<th>备注信息</th>--%>
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
				<%--<td>--%>
					<%--${cmsEducation.contentHtml}--%>
				<%--</td>--%>
				<%--<td>--%>
					<%--${cmsEducation.teacherId}--%>
				<%--</td>--%>
				<%--<td>--%>
					<%--${cmsEducation.weight}--%>
				<%--</td>--%>
				<%--<td>--%>
					<%--${cmsEducation.hits}--%>
				<%--</td>--%>
				<%--<td>--%>
					<%--${cmsEducation.transmit}--%>
				<%--</td>--%>
				<%--<td>--%>
					<%--${cmsEducation.discess}--%>
				<%--</td>--%>
				<%--<td>--%>
					<%--${cmsEducation.praise}--%>
				<%--</td>--%>
				<%--<td>--%>
					<%--${cmsEducation.tread}--%>
				<%--</td>--%>
				<%--<td>--%>
					<%--${cmsEducation.collection}--%>
				<%--</td>--%>
				<%--<td>--%>
					<%--${cmsEducation.report}--%>
				<%--</td>--%>
				<%--<td>--%>
					<%--${cmsEducation.evaluate}--%>
				<%--</td>--%>
				<%--<td>--%>
					<%--${cmsEducation.tip}--%>
				<%--</td>--%>
				<%--<td>--%>
					<%--${cmsEducation.view}--%>
				<%--</td>--%>
				<%--<td>--%>
					<%--${cmsEducation.recommend}--%>
				<%--</td>--%>
				<%--<td>--%>
					<%--${cmsEducation.allowComment}--%>
				<%--</td>--%>
				<%--<td>--%>
					<%--${cmsEducation.commentAudit}--%>
				<%--</td>--%>
				<%--<td>--%>
					<%--${cmsEducation.allowReport}--%>
				<%--</td>--%>
				<%--<td>--%>
					<%--${cmsEducation.undercarriage}--%>
				<%--</td>--%>
				<td> <%--一级分类0-投资教育 1-案例 2-政策解读 3-攻略--%>
					<c:choose>
						<c:when test="${cmsEducation.category1 eq '0'}">
							投资教育
						</c:when>
						<c:when test="${cmsEducation.category1 eq '1'}">
							案例
						</c:when>
						<c:when test="${cmsEducation.category1 eq '2'}">
							政策解读
						</c:when>
						<c:when test="${cmsEducation.category1 eq '3'}">
							攻略
						</c:when>
					</c:choose>

				</td>
				<td>
					<fmt:formatDate value="${cmsEducation.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<%--<td>--%>
					<%--${cmsEducation.remarks}--%>
				<%--</td>--%>
				<shiro:hasPermission name="cms:cmsEducation:edit"><td>
    				<a href="${ctx}/cms/cmsEducation/form?id=${cmsEducation.id}">修改</a>
					<a href="${ctx}/cms/cmsEducation/delete?id=${cmsEducation.id}" onclick="return confirmx('确认要删除该投资教育吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>