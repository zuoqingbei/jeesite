<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>活动管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
		function addRow(list, idx, tpl, row){
			$(list).append(Mustache.render(tpl, {
				idx: idx, delBtn: true, row: row
			}));
			$(list+idx).find("select").each(function(){
				$(this).val($(this).attr("data-value"));
			});
			$(list+idx).find("input[type='checkbox'], input[type='radio']").each(function(){
				var ss = $(this).attr("data-value").split(',');
				for (var i=0; i<ss.length; i++){
					if($(this).val() == ss[i]){
						$(this).attr("checked","checked");
					}
				}
			});
		}
		function delRow(obj, prefix){
			var id = $(prefix+"_id");
			var delFlag = $(prefix+"_delFlag");
			if (id.val() == ""){
				$(obj).parent().parent().remove();
			}else if(delFlag.val() == "0"){
				delFlag.val("1");
				$(obj).html("&divide;").attr("title", "撤销删除");
				$(obj).parent().parent().addClass("error");
			}else if(delFlag.val() == "1"){
				delFlag.val("0");
				$(obj).html("&times;").attr("title", "删除");
				$(obj).parent().parent().removeClass("error");
			}
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<%-- <li><a href="${ctx}/cms/cmsActivity/">活动列表</a></li> --%>
		<%-- <li class="active"><a href="${ctx}/cms/cmsActivity/form?id=${cmsActivity.id}">活动<shiro:hasPermission name="cms:cmsActivity:edit">${not empty cmsActivity.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="cms:cmsActivity:edit">查看</shiro:lacksPermission></a></li> --%>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="cmsActivity" action="${ctx}/cms/cmsActivity/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">类型</label>
			<div class="controls">
				<form:select path="dataType" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('activity_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">活动创建人：</label>
			<div class="controls">
				<sys:treeselect id="user" name="user.id" value="${cmsActivity.user.id}" labelName="user.name" labelValue="${cmsActivity.user.name}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="" allowClear="true" notAllowSelectParent="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">标题：</label>
			<div class="controls">
				<form:input path="title" htmlEscape="false" maxlength="255" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">活动图片：</label>
			<div class="controls">
				<form:hidden id="image" path="image" htmlEscape="false" maxlength="255" class="input-xlarge"/>
				<sys:ckfinder input="image" type="files" uploadPath="/cms/cmsActivity" selectMultiple="false"/>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">关键字：</label>
			<div class="controls">
				<form:input path="keywords" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">标签：</label>
			<div class="controls">
				<c:choose>
					<c:when test="${ cmsNews.id eq null }">
						<form:checkboxes items="${fns:getDictList('tags_type')}" path="tags" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</c:when>
					<c:otherwise>
						<c:forEach var ="dicts" items="${fns:getDictList('tags_type')}" >
							<c:choose>
								<c:when test="${fn:contains(cmsNews.tags,dicts.value)}">
									<input type="checkbox" checked="checked" name="tags" value="${dicts.value}">${dicts.label}
								</c:when>
								<c:otherwise>
									<input type="checkbox"  name="tags" value="${dicts.value}">${dicts.label}
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
		
		
		<div class="control-group">
			<label class="control-label">描述、摘要：</label>
			<div class="controls">
				<form:input path="description" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">活动内容 不含HTML：</label>
			<div class="controls">
				<form:textarea path="content" htmlEscape="false" rows="4" class="input-xxlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">活动内容 含HTML：</label>
			<div class="controls">
				<form:input path="contentHtml" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">权重，越大越靠前：</label>
			<div class="controls">
				<form:input path="weight" htmlEscape="false" maxlength="11" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">点击数、阅读数：</label>
			<div class="controls">
				<form:input path="hits" htmlEscape="false" maxlength="11" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">转发数 分享数：</label>
			<div class="controls">
				<form:input path="transmit" htmlEscape="false" maxlength="11" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">评论数  回复数：</label>
			<div class="controls">
				<form:input path="discess" htmlEscape="false" maxlength="11" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">赞数量：</label>
			<div class="controls">
				<form:input path="praise" htmlEscape="false" maxlength="11" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">踩数量：</label>
			<div class="controls">
				<form:input path="tread" htmlEscape="false" maxlength="11" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">收藏量：</label>
			<div class="controls">
				<form:input path="collection" htmlEscape="false" maxlength="11" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">评价数量：</label>
			<div class="controls">
				<form:input path="evaluate" htmlEscape="false" maxlength="11" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">报名人数：</label>
			<div class="controls">
				<form:input path="enter" htmlEscape="false" maxlength="11" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">曝光量：</label>
			<div class="controls">
				<form:input path="view" htmlEscape="false" maxlength="11" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否推荐：</label>
			<div class="controls">
				<form:select path="recommend" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:option value="1" label="是"/>
					<form:option value="0" label="否"/>
				</form:select>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">是否允许评论 ：</label>
			<div class="controls">
				<form:select path="allowComment" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:option value="0" label="是"/>
					<form:option value="1" label="否"/>
				</form:select>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">活动主办方：</label>
			<div class="controls">
				<form:input path="leader" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">活动举办地点：</label>
			<div class="controls">
				<form:input path="address" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">举办开始时间：</label>
			<div class="controls">
				<input name="startDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${cmsActivity.startDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">活动结束时间：</label>
			<div class="controls">
				<input name="endDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${cmsActivity.endDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">报名开始时间：</label>
			<div class="controls">
				<input name="enterStartDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${cmsActivity.enterStartDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">报名结束时间：</label>
			<div class="controls">
				<input name="enterEndDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${cmsActivity.enterEndDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">一级分类：</label>
			<div class="controls">
				<form:input path="category1" htmlEscape="false" maxlength="64" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">二级分类：</label>
			<div class="controls">
				<form:input path="category2" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">三级分类：</label>
			<div class="controls">
				<form:input path="category3" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注信息 报名须知：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="500" class="input-xxlarge "/>
			</div>
		</div>
		
			<div class="control-group">
				<label class="control-label">活动日程：</label>
				<div class="controls">
					<table id="contentTable" class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th class="hide"></th>
								<th>标题</th>
								<th>图片</th>
								<th>当前流程开始时间</th>
								<th>流程结束时间</th>
								<th>流程排序</th>
								<th>地址</th>
								<th>当前流程内容介绍 支持HTML</th>
								<th>remarks</th>
								<shiro:hasPermission name="cms:cmsActivity:edit"><th width="10">&nbsp;</th></shiro:hasPermission>
							</tr>
						</thead>
						<tbody id="cmsActivityFlowList">
						</tbody>
						<shiro:hasPermission name="cms:cmsActivity:edit"><tfoot>
							<tr><td colspan="10"><a href="javascript:" onclick="addRow('#cmsActivityFlowList', cmsActivityFlowRowIdx, cmsActivityFlowTpl);cmsActivityFlowRowIdx = cmsActivityFlowRowIdx + 1;" class="btn">新增</a></td></tr>
						</tfoot></shiro:hasPermission>
					</table>
					<script type="text/template" id="cmsActivityFlowTpl">//<!--
						<tr id="cmsActivityFlowList{{idx}}">
							<td class="hide">
								<input id="cmsActivityFlowList{{idx}}_id" name="cmsActivityFlowList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
								<input id="cmsActivityFlowList{{idx}}_delFlag" name="cmsActivityFlowList[{{idx}}].delFlag" type="hidden" value="0"/>
							</td>
							<td>
								<input id="cmsActivityFlowList{{idx}}_title" name="cmsActivityFlowList[{{idx}}].title" type="text" value="{{row.title}}" maxlength="255" class="input-small "/>
							</td>
							<td>
								<input id="cmsActivityFlowList{{idx}}_images" name="cmsActivityFlowList[{{idx}}].images" type="text" value="{{row.images}}" maxlength="255" class="input-small "/>
							</td>
							<td>
								<input id="cmsActivityFlowList{{idx}}_flowStartTime" name="cmsActivityFlowList[{{idx}}].flowStartTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
									value="{{row.flowStartTime}}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
							</td>
							<td>
								<input id="cmsActivityFlowList{{idx}}_flowEndTime" name="cmsActivityFlowList[{{idx}}].flowEndTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
									value="{{row.flowEndTime}}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
							</td>
							<td>
								<input id="cmsActivityFlowList{{idx}}_orderNum" name="cmsActivityFlowList[{{idx}}].orderNum" type="text" value="{{row.orderNum}}" maxlength="11" class="input-small  digits"/>
							</td>
							<td>
								<input id="cmsActivityFlowList{{idx}}_address" name="cmsActivityFlowList[{{idx}}].address" type="text" value="{{row.address}}" maxlength="255" class="input-small "/>
							</td>
							<td>
								<input id="cmsActivityFlowList{{idx}}_descs" name="cmsActivityFlowList[{{idx}}].descs" type="text" value="{{row.descs}}" class="input-small required"/>
							</td>
							<td>
								<textarea id="cmsActivityFlowList{{idx}}_remarks" name="cmsActivityFlowList[{{idx}}].remarks" rows="4" maxlength="255" class="input-small ">{{row.remarks}}</textarea>
							</td>
							<shiro:hasPermission name="cms:cmsActivity:edit"><td class="text-center" width="10">
								{{#delBtn}}<span class="close" onclick="delRow(this, '#cmsActivityFlowList{{idx}}')" title="删除">&times;</span>{{/delBtn}}
							</td></shiro:hasPermission>
						</tr>//-->
					</script>
					<script type="text/javascript">
						var cmsActivityFlowRowIdx = 0, cmsActivityFlowTpl = $("#cmsActivityFlowTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
						$(document).ready(function() {
							var data = ${fns:toJson(cmsActivity.cmsActivityFlowList)};
							for (var i=0; i<data.length; i++){
								addRow('#cmsActivityFlowList', cmsActivityFlowRowIdx, cmsActivityFlowTpl, data[i]);
								cmsActivityFlowRowIdx = cmsActivityFlowRowIdx + 1;
							}
						});
					</script>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">活动组织结构：</label>
				<div class="controls">
					<table id="contentTable" class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th class="hide"></th>
								<th>类型 0-主办方 1-承办方 2-技术支持/讲师</th>
								<th>图标</th>
								<th>企业网站</th>
								<th>企业单位</th>
								<th>企业地址</th>
								<th>企业简介</th>
								<th>联系人姓名</th>
								<th>联系人手机号</th>
								<th>企业排序</th>
								<th>remarks</th>
								<shiro:hasPermission name="cms:cmsActivity:edit"><th width="10">&nbsp;</th></shiro:hasPermission>
							</tr>
						</thead>
						<tbody id="cmsActivityOrganizationList">
						</tbody>
						<shiro:hasPermission name="cms:cmsActivity:edit"><tfoot>
							<tr><td colspan="12"><a href="javascript:" onclick="addRow('#cmsActivityOrganizationList', cmsActivityOrganizationRowIdx, cmsActivityOrganizationTpl);cmsActivityOrganizationRowIdx = cmsActivityOrganizationRowIdx + 1;" class="btn">新增</a></td></tr>
						</tfoot></shiro:hasPermission>
					</table>
					<script type="text/template" id="cmsActivityOrganizationTpl">//<!--
						<tr id="cmsActivityOrganizationList{{idx}}">
							<td class="hide">
								<input id="cmsActivityOrganizationList{{idx}}_id" name="cmsActivityOrganizationList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
								<input id="cmsActivityOrganizationList{{idx}}_delFlag" name="cmsActivityOrganizationList[{{idx}}].delFlag" type="hidden" value="0"/>
							</td>
							<td>
								<input id="cmsActivityOrganizationList{{idx}}_type" name="cmsActivityOrganizationList[{{idx}}].type" type="text" value="{{row.type}}" maxlength="1" class="input-small "/>
							</td>
							<td>
								<input id="cmsActivityOrganizationList{{idx}}_logo" name="cmsActivityOrganizationList[{{idx}}].logo" type="text" value="{{row.logo}}" maxlength="255" class="input-small "/>
							</td>
							<td>
								<input id="cmsActivityOrganizationList{{idx}}_webUrl" name="cmsActivityOrganizationList[{{idx}}].webUrl" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
									value="{{row.webUrl}}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
							</td>
							<td>
								<input id="cmsActivityOrganizationList{{idx}}_name" name="cmsActivityOrganizationList[{{idx}}].name" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
									value="{{row.name}}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
							</td>
							<td>
								<input id="cmsActivityOrganizationList{{idx}}_address" name="cmsActivityOrganizationList[{{idx}}].address" type="text" value="{{row.address}}" maxlength="255" class="input-small "/>
							</td>
							<td>
								<input id="cmsActivityOrganizationList{{idx}}_descs" name="cmsActivityOrganizationList[{{idx}}].descs" type="text" value="{{row.descs}}" class="input-small required"/>
							</td>
							<td>
								<input id="cmsActivityOrganizationList{{idx}}_contact" name="cmsActivityOrganizationList[{{idx}}].contact" type="text" value="{{row.contact}}" maxlength="255" class="input-small "/>
							</td>
							<td>
								<input id="cmsActivityOrganizationList{{idx}}_contactMobile" name="cmsActivityOrganizationList[{{idx}}].contactMobile" type="text" value="{{row.contactMobile}}" maxlength="255" class="input-small "/>
							</td>
							<td>
								<input id="cmsActivityOrganizationList{{idx}}_orderNum" name="cmsActivityOrganizationList[{{idx}}].orderNum" type="text" value="{{row.orderNum}}" maxlength="11" class="input-small  digits"/>
							</td>
							<td>
								<textarea id="cmsActivityOrganizationList{{idx}}_remarks" name="cmsActivityOrganizationList[{{idx}}].remarks" rows="4" maxlength="255" class="input-small ">{{row.remarks}}</textarea>
							</td>
							<shiro:hasPermission name="cms:cmsActivity:edit"><td class="text-center" width="10">
								{{#delBtn}}<span class="close" onclick="delRow(this, '#cmsActivityOrganizationList{{idx}}')" title="删除">&times;</span>{{/delBtn}}
							</td></shiro:hasPermission>
						</tr>//-->
					</script>
					<script type="text/javascript">
						var cmsActivityOrganizationRowIdx = 0, cmsActivityOrganizationTpl = $("#cmsActivityOrganizationTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
						$(document).ready(function() {
							var data = ${fns:toJson(cmsActivity.cmsActivityOrganizationList)};
							for (var i=0; i<data.length; i++){
								addRow('#cmsActivityOrganizationList', cmsActivityOrganizationRowIdx, cmsActivityOrganizationTpl, data[i]);
								cmsActivityOrganizationRowIdx = cmsActivityOrganizationRowIdx + 1;
							}
						});
					</script>
				</div>
			</div>
		<div class="form-actions">
			<%-- <shiro:hasPermission name="cms:cmsActivity:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission> --%>
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>