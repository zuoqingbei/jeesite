<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用户投稿管理</title>
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
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/cms/cmsContribute/list">用户投稿列表</a></li>
		<li class="active"><a href="${ctx}/cms/cmsContribute/form?id=${cmsContribute.id}">用户投稿<shiro:hasPermission name="cms:cmsContribute:edit">${not empty cmsContribute.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="cms:cmsContribute:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="cmsContribute" action="${ctx}/cms/cmsContribute/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">源链接：</label>
			<div class="controls">
				<form:input disabled="true" path="link" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">投稿类型：</label>
			<div class="controls">
			<form:select disabled="true" path="dataType" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:option value="0" label="资讯"/>
					<form:option value="1" label="案例"/>
					<form:option value="2" label="投资教育"/>
					<form:option value="3" label="问答"/>
				</form:select>
			</div>
		</div>
		<%-- <div class="control-group">
			<label class="control-label">投稿用户id：</label>
			<div class="controls">
				<sys:treeselect id="user" name="user.id" value="${cmsContribute.user.id}" labelName="user.name" labelValue="${cmsContribute.user.name}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="" allowClear="true" notAllowSelectParent="true"/>
			</div>
		</div> --%>
		<div class="control-group">
			<label class="control-label">标题：</label>
			<div class="controls">
				<form:input disabled="true" path="title" htmlEscape="false" maxlength="255" class="input-xlarge required"/>
				<!-- <span class="help-inline"><font color="red">*</font> </span> -->
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">内容：</label>
			<div class="controls">
				<form:textarea    id="contentHtml" htmlEscape="false" path="contentHtml" rows="4" maxlength="200" class="input-xxlarge"/>
				<sys:ckeditor replace="contentHtml" uploadPath="/cms/cmsContribute" />
			</div>
			<%--<div class="controls">--%>
				<%--<form:input disabled="true" path="contentHtml" htmlEscape="false" class="input-xlarge "/>--%>
			<%--</div>--%>
		</div>
		<%--<div class="control-group">--%>
			<%--<label class="control-label">内容 不包含HTML标签：</label>--%>
			<%--<div class="controls">--%>
				<%--<form:textarea disabled="true" path="content" htmlEscape="false" rows="4" class="input-xxlarge "/>--%>
			<%--</div>--%>
		<%--</div>--%>
		<div class="control-group">
			<label class="control-label">图片：</label>
			<div class="controls">
				<form:hidden id="image" path="image" htmlEscape="false" maxlength="255" class="input-xlarge"/>
				<sys:ckfinder input="image" type="files" uploadPath="/cms/cmsContribute" selectMultiple="true"/>
			</div>
				<%--<form:input disabled="true"   path="image" htmlEscape="false" maxlength="255" class="input-xlarge "/>--%>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">关键字：</label>
			<div class="controls">
				<form:input disabled="true"   path="keywords" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">标签：</label>
			<div class="controls">
				<c:choose>
					<c:when test="${ cmsContribute.id eq null }">
						<form:checkboxes items="${fns:getDictList('tags_type')}" path="tags" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</c:when>
					<c:otherwise>
						<c:forEach var ="dicts" items="${fns:getDictList('tags_type')}" >
							<c:choose>
								<c:when test="${fn:contains(cmsContribute.tags,dicts.value)}">
									<input type="checkbox" checked="checked" name="tags" value="${dicts.value}">${dicts.label}
								</c:when>
								<c:otherwise>
									<input type="checkbox"  name="tags" value="${dicts.value}">${dicts.label}
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</c:otherwise>
				</c:choose>
				<%--<form:input disabled="true" path="tags" htmlEscape="false" maxlength="255" class="input-xlarge "/>--%>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">描述、摘要：</label>
			<div class="controls">
				<form:textarea path="description" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>

				<%--<form:input disabled="true"  path="description" htmlEscape="false" maxlength="255" class="input-xlarge "/>--%>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">状态：</label>
			<div class="controls">
			<form:select disabled="true"  path="status" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:option value="0" label="草稿"/>
					<form:option value="1" label="已投稿"/>
					<form:option value="2" label="审核中"/>
					<form:option value="3" label="审核通过，发布"/>
					<form:option value="4" label="驳回"/>
				</form:select>
			</div>
		</div>
		<%--<div class="control-group">--%>
			<%--<label class="control-label">备注信息：</label>--%>
			<%--<div class="controls">--%>
				<%--<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>--%>
			<%--</div>--%>
		<%--</div>--%>
		<div class="form-actions">
			<%--<shiro:hasPermission name="cms:cmsContribute:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保存"/>&nbsp;</shiro:hasPermission>--%>
<c:choose>
	<c:when test="${cmsContribute.status eq '1'}">
		<input id="btnAccept" class="btn" type="button" value="审核通过" onclick="accepts()"/>
		<input id="btnReject" class="btn" type="button" value="驳回" onclick="rejects()"/>
		<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
	</c:when>
	<c:otherwise>
		<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
	</c:otherwise>

</c:choose>

		</div>
	</form:form>
	<script type="text/javascript">
		function accepts(){
			  $.ajax({
		             type: "post",
		             url: "changeState",
		             data: {id:'${cmsContribute.id}',status:"2"},
		             dataType: "json",
		             success: function(data){
		            	 alert(data.msg);
                         location.replace(document.referrer); //返回上一个页面，并刷新
		                      }
		   		 });
			
		}
		function rejects(){
			  $.ajax({
		             type: "post",
		             url: "${ctx}/cms/cmsContribute/changeState",
		             data: {id:'${cmsContribute.id}',status:"3"},
		             dataType: "json",
		             success: function(data){
		            	alert(data.msg);
                         location.replace(document.referrer);
		                      }
		   		 });
			
		}
	</script>
</body>
</html>