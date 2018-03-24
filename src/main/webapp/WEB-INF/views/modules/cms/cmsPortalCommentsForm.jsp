<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>门户评论管理</title>
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
		<li><a href="${ctx}/cms/cmsPortalComments/list">门户评论列表</a></li>
		<li class="active"><a href="${ctx}/cms/cmsPortalComments/form?id=${cmsPortalComments.id}">门户评论<shiro:hasPermission name="cms:cmsPortalComments:edit">${not empty cmsPortalComments.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="cms:cmsPortalComments:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="cmsPortalComments" action="${ctx}/cms/cmsPortalComments/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<%--<div class="control-group">--%>
			<%--<label class="control-label">评论父级ID，如果直接回复作者为-1：</label>--%>
			<%--<div class="controls">--%>
			<%--</div>--%>
		<%--</div>--%>
		<%--<div class="control-group">--%>
			<%--<label class="control-label">所有父级id用逗号拼接，并且以逗号开头结尾 比如 ,1,2,：</label>--%>
			<%--<div class="controls">--%>
				<%--<form:input path="parentIds" htmlEscape="false" maxlength="255" class="input-xlarge "/>--%>
			<%--</div>--%>
		<%--</div>--%>
		<%--<div class="control-group">--%>
			<%--<label class="control-label">评论内容源id：</label>--%>
			<%--<div class="controls">--%>
				<%--<form:input path="sourceId" htmlEscape="false" maxlength="64" class="input-xlarge required"/>--%>
				<%--<span class="help-inline"><font color="red">*</font> </span>--%>
			<%--</div>--%>
		<%--</div>--%>
		<div class="control-group">
			<label class="control-label">内容来源表 ：</label>
			<div class="controls">
				<form:input path="sourceTable" htmlEscape="false" maxlength="64" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">评论内容：</label>
			<div class="controls">
				<form:textarea path="content" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">评论姓名：</label>
			<div class="controls">
				<sys:treeselect id="user" name="user.id" value="${cmsPortalComments.user.id}" labelName="user.name" labelValue="${cmsPortalComments.user.name}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="" allowClear="true" notAllowSelectParent="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">评论IP：</label>
			<div class="controls">
				<form:input path="ip" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">审核人：</label>
			<div class="controls">
				<form:input path="auditUserId" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">审核时间：</label>
			<div class="controls">
				<input name="auditDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${cmsPortalComments.auditDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<%--<div class="form-actions">--%>
			<%--<shiro:hasPermission name="cms:cmsPortalComments:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>--%>
			<%--<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>--%>
		<%--</div>--%>
		<div class="form-actions">
				<%-- 			<shiro:hasPermission name="cms:cmsContribute:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="审核通过"/>&nbsp;</shiro:hasPermission>
                 --%>
			<input id="btnAccept" class="btn" type="button" value="审核通过" onclick="accepts()"/>
			<input id="btnReject" class="btn" type="button" value="驳回" onclick="rejects()"/>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
	<script type="text/javascript">
        function accepts(){
            $.ajax({
                type: "post",
                url: "${ctx}/cms/cmsPortalComments/changeState",
                data: {id:'${cmsPortalComments.id}',status:"1"},
                dataType: "json",
                success: function(data){
                    console.log(data)
                    alert(data.msg);
                    location.replace(document.referrer); //返回上一个页面，并刷新
                }
            });

        }
        function rejects(){
            $.ajax({
                type: "post",
                url: "${ctx}/cms/cmsPortalComments/changeState",
                data: {id:'${cmsPortalComments.id}',status:"2"},
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