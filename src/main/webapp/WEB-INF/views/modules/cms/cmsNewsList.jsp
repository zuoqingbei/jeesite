<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>资讯管理</title>
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
		<li class="active"><a href="${ctx}/cms/cmsNews/list">资讯列表</a></li>
		<shiro:hasPermission name="cms:cmsNews:edit"><li><a href="${ctx}/cms/cmsNews/form">资讯添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="cmsNews" action="${ctx}/cms/cmsNews/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>数据来源类型</label>
				<form:select path="dataType" class="input-medium">
					<form:option value="" label=""/>
					<form:option value="0" label="采集"/>
					<form:option value="1" label="用户投稿"/>
					<form:option value="2" label="管理人员发布"/>
				</form:select>
			</li>
			
			<li>
				<label>标签：</label>
				<form:select id="type" path="tags" class="input-small">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('tags_type')}" itemValue="value" itemLabel="label" htmlEscape="false"/>
				</form:select>
			</li>
			<%-- <li><label>创建者</label>
				<sys:treeselect id="user" name="user.id" value="${cmsNews.user.id}" labelName="user.name" labelValue="${cmsNews.user.name}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
			</li> --%>
			<li><label>标题</label>
				<form:input path="title" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<%-- <li><label>关键字</label>
				<form:input path="keywords" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li> --%>
			<%--<li><label>是否推荐</label>--%>
				<%--<form:select path="recommend" class="input-medium">--%>
					<%--<form:option value="" label=""/>--%>
					<%--<form:option value="0" label="否"/>--%>
					<%--<form:option value="1" label="是"/>--%>
				<%--</form:select>--%>
			<%--</li>--%>
			<%--<li><label>是否允许评论</label>--%>
				<%--<form:select path="allowComment" class="input-medium">--%>
					<%--<form:option value="" label=""/>--%>
					<%--<form:option value="0" label="是"/>--%>
					<%--<form:option value="1" label="否"/>--%>
				<%--</form:select>--%>
			<%--</li>--%>
			<%--<li><label>是否需要审核</label>--%>
				<%--<form:select path="commentAudit" class="input-medium">--%>
					<%--<form:option value="" label=""/>--%>
					<%--<form:option value="0" label="是"/>--%>
					<%--<form:option value="1" label="否"/>--%>
				<%--</form:select>--%>
			<%--</li>--%>
			<%--<li><label>是否允许举报</label>--%>
				<%--<form:select path="allowReport" class="input-medium">--%>
					<%--<form:option value="" label=""/>--%>
					<%--<form:option value="0" label="是"/>--%>
					<%--<form:option value="1" label="否"/>--%>
<%--&lt;%&ndash; 					<form:options items="${fns:getDictList('0,1')}" itemLabel="label" itemValue="value" htmlEscape="false"/>--%>
 <%--&ndash;%&gt;			</form:select>--%>
			<%--</li>--%>
			<%--<li><label>内容是否下架 </label>--%>
			<%--<form:select path="undercarriage" class="input-medium">--%>
					<%--<form:option value="" label=""/>--%>
					<%--<form:option value="0" label="否"/>--%>
					<%--<form:option value="1" label="是"/>--%>
				<%--</form:select>--%>
<%--&lt;%&ndash; 				<form:input path="undercarriage" htmlEscape="false" maxlength="1" class="input-medium"/>--%>
 <%--&ndash;%&gt;			</li>--%>
			<li><label>创建时间</label>
				<input name="createDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${cmsNews.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
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
				<%--<th>源链接</th>--%>
				<th>数据来源类型</th>
				<!-- <th>创建者 </th> -->
				<th>标题</th>
				<th>文章图片</th>
				<th>关键字</th>
				<th>标签</th>
<!-- 				<th>描述</th>
 <%---->				<th>权重</th>--%>
				<!-- <th>点击数</th>
				<th>转发数</th>
				<th>评论数</th>
				<th>赞数量</th>
				<th>踩数量</th>
				<th>收藏量</th>
				<th>举报数量</th>
				<th>评价数量</th>
				<th>打赏次数</th> 
				<th>曝光量</th>-->
				<%--<th>是否推荐</th>--%>
				<%--<th>是否允许评论</th>--%>
				<%--<th>评论是否需要审核</th>--%>
				<%--<th>是否允许举报</th>--%>
				<%--<th>内容是否下架</th>--%>
				<!-- <th>下架原因</th> -->
			<!-- 	<th>一级分类</th>
				<th>二级分类</th>
				<th>三级分类</th> -->
				<th>创	建	时	间</th>
				<!-- <th>更新者</th> -->
				<!-- <th>更	新	时	间</th> -->
<!-- 				<th>备注信息</th>
 --><!-- 				<th>删除标记</th>
 -->				<shiro:hasPermission name="cms:cmsNews:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="cmsNews">
			<tr>
				<%--<td><a href="${cmsNews.link}">
					${cmsNews.link}
				</a></td>--%>
				<td>
					<c:if test="${cmsNews.dataType eq '0' }">采集</c:if>
					<c:if test="${cmsNews.dataType eq '1' }">用户投稿</c:if>
					<c:if test="${cmsNews.dataType eq '2' }">管理人员发布</c:if>
				</td>
				<%-- <td>
					${cmsNews.user.name}
				</td> --%>
				<td>
				<a href="${ctx}/cms/cmsNews/form?id=${cmsNews.id}">${cmsNews.title}</a>
					
				</td>
				<td>
					<c:if test="${ not empty cmsNews.image}">
						<img src="${cmsNews.image}" style="width:80px;height:80px;"/>
					</c:if>
				</td>
				<td>
					${cmsNews.keywords}
				</td>
				<td>
					${fns:getDictLabel(cmsNews.tags, 'tags_type', '')}
					<c:forEach var ="dicts" items="${fns:getDictList('tags_type')}" >
							<c:choose>
								<c:when test="${fn:contains(cmsNews.tags,dicts.value)}">
									${dicts.label}
								</c:when>
							</c:choose>
							
						</c:forEach>
				</td>
			<%-- 	<td>
					${cmsNews.description}
				</td> --%>
				<%--<td>--%>
					<%--${cmsNews.weight}--%>
				<%--</td>--%>
				<%-- <td>
					${cmsNews.hits}
				</td>
				<td>
					${cmsNews.transmit}
				</td>
				<td>
					${cmsNews.discess}
				</td>
				<td>
					${cmsNews.praise}
				</td>
				<td>
					${cmsNews.tread}
				</td>
				<td>
					${cmsNews.collection}
				</td>
				<td>
					${cmsNews.report}
				</td>
				<td>
					${cmsNews.evaluate}
				</td>
				<td>
					${cmsNews.tip}
				</td>
				<td>
					${cmsNews.view}
				</td> --%>
				<%--<td>--%>
				<%--<c:if test="${cmsNews.recommend eq '0' }">否</c:if>--%>
				<%--<c:if test="${cmsNews.recommend eq '1' }">是</c:if>--%>
<%--&lt;%&ndash; 					${fns:getDictLabel(cmsNews.recommend, '0,1', '')}--%>
 <%--&ndash;%&gt;				</td>--%>
				<%--<td>--%>
				<%--<c:if test="${cmsNews.allowComment eq '0' }">是</c:if>--%>
				<%--<c:if test="${cmsNews.allowComment eq '1' }">否</c:if>--%>
<%--&lt;%&ndash; 					${fns:getDictLabel(cmsNews.allowComment, '0,1', '')}--%>
 <%--&ndash;%&gt;				</td>--%>
				<%--<td>--%>
				<%--<c:if test="${cmsNews.commentAudit eq '0' }">是</c:if>--%>
				<%--<c:if test="${cmsNews.commentAudit eq '1' }">否</c:if>--%>
<%--&lt;%&ndash; 					${fns:getDictLabel(cmsNews.commentAudit, '0,1', '')}--%>
 <%--&ndash;%&gt;				</td>--%>
				<%--<td>--%>
				<%--<c:if test="${cmsNews.allowReport eq '0' }">是</c:if>--%>
				<%--<c:if test="${cmsNews.allowReport eq '1' }">否</c:if>--%>
<%--&lt;%&ndash; 					${fns:getDictLabel(cmsNews.allowReport, '0,1', '')}--%>
 <%--&ndash;%&gt;				</td>--%>
				<%--<td>--%>
				<%--<c:if test="${cmsNews.undercarriage eq '0' }">否</c:if>--%>
				<%--<c:if test="${cmsNews.undercarriage eq '1' }">是</c:if>--%>
<%--&lt;%&ndash; 					${cmsNews.undercarriage}--%>
 <%--&ndash;%&gt;				</td>--%>
			<%-- 	<td>
					${cmsNews.reason}
				</td>
				<td>
					${cmsNews.category1}
				</td>
				<td>
					${cmsNews.category2}
				</td>
				<td>
					${cmsNews.category3}
				</td> --%>
				<td>
					<fmt:formatDate value="${cmsNews.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
			<%-- 	<td>
					${cmsNews.updateBy.id}
				</td> --%>
			<%-- 	<td>
					<fmt:formatDate value="${cmsNews.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td> --%>
			<%-- 	<td>
					${cmsNews.remarks}
				</td> --%>
			<%-- 	<td>
					${fns:getDictLabel(cmsNews.delFlag, 'del_flag', '')}
				</td> --%>
				<shiro:hasPermission name="cms:cmsNews:edit"><td>
    				<a href="${ctx}/cms/cmsNews/form?id=${cmsNews.id}">修改</a>
					<a href="${ctx}/cms/cmsNews/delete?id=${cmsNews.id}" onclick="return confirmx('确认要删除该资讯吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>