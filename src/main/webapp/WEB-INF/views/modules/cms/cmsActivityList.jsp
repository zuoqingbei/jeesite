<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>活动管理</title>
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
		<li class="active"><a href="${ctx}/cms/cmsActivity/">活动列表</a></li>
		<shiro:hasPermission name="cms:cmsActivity:edit"><li><a href="${ctx}/cms/cmsActivity/form">活动添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="cmsActivity" action="${ctx}/cms/cmsActivity/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>标题：</label>
				<form:input path="title" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>举办开始时间：</label>
				<input name="startDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${cmsActivity.startDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>活动结束时间：</label>
				<input name="endDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${cmsActivity.endDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>报名开始时间：</label>
				<input name="enterStartDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${cmsActivity.enterStartDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>报名结束时间：</label>
				<input name="enterEndDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${cmsActivity.enterEndDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>创建时间：</label>
				<input name="createDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${cmsActivity.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>更新时间：</label>
				<input name="updateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${cmsActivity.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
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
				<th>标题</th>
				<th>文章图片</th>
				<th>关键字</th>
				<th>点击数、阅读数</th>
				<th>转发数 分享数</th>
				<th>评论数  回复数</th>
				<th>赞数量</th>
				<th>踩数量</th>
				<th>收藏量</th>
				<th>评价数量</th>
				<th>报名人数</th>
				<th>曝光量</th>
				<th>是否推荐 0-普通 1-推荐</th>
				<th>是否允许评论 0-允许 1-不允许</th>
				<th>活动主办方</th>
				<th>活动举办地点</th>
				<th>举办开始时间</th>
				<th>活动结束时间</th>
				<th>报名开始时间</th>
				<th>报名结束时间</th>
				<th>一级分类</th>
				<th>二级分类</th>
				<th>三级分类</th>
				<th>创建时间</th>
				<th>更新者</th>
				<th>更新时间</th>
				<th>备注信息 报名须知</th>
				<shiro:hasPermission name="cms:cmsActivity:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="cmsActivity">
			<tr>
				<td><a href="${ctx}/cms/cmsActivity/form?id=${cmsActivity.id}">
					${cmsActivity.title}
				</a></td>
				<td>
					${cmsActivity.image}
				</td>
				<td>
					${cmsActivity.keywords}
				</td>
				<td>
					${cmsActivity.hits}
				</td>
				<td>
					${cmsActivity.transmit}
				</td>
				<td>
					${cmsActivity.discess}
				</td>
				<td>
					${cmsActivity.praise}
				</td>
				<td>
					${cmsActivity.tread}
				</td>
				<td>
					${cmsActivity.collection}
				</td>
				<td>
					${cmsActivity.evaluate}
				</td>
				<td>
					${cmsActivity.enter}
				</td>
				<td>
					${cmsActivity.view}
				</td>
				<td>
					${cmsActivity.recommend}
				</td>
				<td>
					${cmsActivity.allowComment}
				</td>
				<td>
					${cmsActivity.leader}
				</td>
				<td>
					${cmsActivity.address}
				</td>
				<td>
					<fmt:formatDate value="${cmsActivity.startDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${cmsActivity.endDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${cmsActivity.enterStartDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${cmsActivity.enterEndDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${cmsActivity.category1}
				</td>
				<td>
					${cmsActivity.category2}
				</td>
				<td>
					${cmsActivity.category3}
				</td>
				<td>
					<fmt:formatDate value="${cmsActivity.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${cmsActivity.updateBy.id}
				</td>
				<td>
					<fmt:formatDate value="${cmsActivity.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${cmsActivity.remarks}
				</td>
				<shiro:hasPermission name="cms:cmsActivity:edit"><td>
    				<a href="${ctx}/cms/cmsActivity/form?id=${cmsActivity.id}">修改</a>
					<a href="${ctx}/cms/cmsActivity/delete?id=${cmsActivity.id}" onclick="return confirmx('确认要删除该活动吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>