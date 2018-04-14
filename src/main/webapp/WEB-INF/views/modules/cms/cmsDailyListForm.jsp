<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>每日一览管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					console.log(form)
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
		<li><a href="${ctx}/cms/cmsDailyList/list">每日一览列表</a></li>
		<li class="active"><a href="${ctx}/cms/cmsDailyList/form?id=${cmsDailyList.id}">每日一览<shiro:hasPermission name="cms:cmsDailyList:edit">${not empty cmsDailyList.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="cms:cmsDailyList:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="cmsDailyList" action="${ctx}/cms/cmsDailyList/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">标题：</label>
			<div class="controls">
				<form:input path="title" htmlEscape="false" maxlength="255" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">图片：</label>
			<div class="controls">
				<form:hidden id="image" path="image" htmlEscape="false" maxlength="255" class="input-xlarge"/>
				<sys:ckfinder input="image" type="files" uploadPath="/cms/cmsDailyList" selectMultiple="true"/>
			</div>

		</div>

		<div class="control-group" >
			<label class="control-label">资讯：</label>
			<div class="controls">
				<table id="contentTable" class="table table-striped table-bordered table-condensed">
					<thead>
					<tr>
						<th class="hide"></th>
						<th>名称</th>
						<%--<th>备注信息</th>--%>
						<shiro:hasPermission name="cms:cmsDailyList:edit"><th width="10">&nbsp;</th></shiro:hasPermission>
					</tr>
					</thead>
					<tbody id="testDataChildList" >
					</tbody>
					<shiro:hasPermission name="cms:cmsDailyList:edit"><tfoot>
					<tr><td colspan="4"><a href="javascript:" onclick="addRow('#testDataChildList', testDataChildRowIdx, testDataChildTpl);testDataChildRowIdx = testDataChildRowIdx + 1;" class="btn">新增</a></td></tr>
					</tfoot></shiro:hasPermission>
				</table>
				<script type="text/template" id="testDataChildTpl">
					<%--<c:choose>--%>
						<%----%>
					<%--</c:choose>--%>

						<tr id="testDataChildList{{idx}}">
							<td class="hide">
								<input id="testDataChildList{{idx}}_id" name="testDataChildList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
								<input id="testDataChildList{{idx}}_delFlag" name="testDataChildList[{{idx}}].delFlag" type="hidden" value="0"/>
							</td>
							<td>

								<form:select  path="newids" value="{{row.id}}"  name="testDataChildList[{{idx}}].name" type="text"   class="input-small " style="width:400px">
									<form:option value="" label=""/>/
									<c:forEach items="${cmsNewsList}" var="a">
										<form:option value="${a.id}" label="${a.title}"/>
									</c:forEach>


								</form:select>

								<%--<input id="testDataChildList{{idx}}_name" name="testDataChildList[{{idx}}].name" type="text" value="{{row.name}}" maxlength="100" class="input-small "/>--%>
							</td>
							<%--<td>--%>
								<%--<input id="testDataChildList{{idx}}_remarks" name="testDataChildList[{{idx}}].remarks" type="text" value="{{row.remarks}}" maxlength="255" class="input-small "/>--%>
							<%--</td>--%>
							<shiro:hasPermission name="cms:cmsDailyList:edit"><td class="text-center" width="10">
								{{#delBtn}}<span class="close" onclick="delRow(this, '#testDataChildList{{idx}}')" title="删除">&times;</span>{{/delBtn}}
							</td></shiro:hasPermission>
						</tr>
				</script>
				<script type="text/javascript">
                    var testDataChildRowIdx = 0, testDataChildTpl = $("#testDataChildTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
                    $(document).ready(function() {
                        var data = ${fns:toJson(news)};
                        for (var i=0; i<data.length; i++){
                            addRow('#testDataChildList', testDataChildRowIdx, testDataChildTpl, data[i]);
                            testDataChildRowIdx = testDataChildRowIdx + 1;
                        }
                    });
				</script>
			</div>
		</div>


		<div class="control-group" >
			<label class="control-label">案例：</label>
			<div class="controls">
				<table id="contentTable1" class="table table-striped table-bordered table-condensed">
					<thead>
					<tr>
						<th class="hide"></th>
						<th>名称</th>
							<%--<th>备注信息</th>--%>
						<shiro:hasPermission name="cms:cmsDailyList:edit"><th width="10">&nbsp;</th></shiro:hasPermission>
					</tr>
					</thead>
					<tbody id="educationChildList">
					</tbody>
					<shiro:hasPermission name="cms:cmsDailyList:edit"><tfoot>
					<tr><td colspan="4">
						<a href="javascript:"onclick="addRow('#educationChildList', educationChildRowIdx, educationChildTpl);educationChildRowIdx = educationChildRowIdx + 1;" class="btn">新增</a></td></tr>
					</tfoot></shiro:hasPermission>
				</table>
				<script type="text/template" id="educationChildTpl">
					<tr id="educationChildList{{idx}}">
						<td class="hide">
							<input id="educationChildList{{idx}}_id" name="educationChildList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
							<input id="educationChildList{{idx}}_delFlag" name="educationChildList[{{idx}}].delFlag" type="hidden" value="0"/>
						</td>
						<td>
							<form:select  path="educationids" value="{{row.id}}"   name="educationChildList[{{idx}}].name" type="text"   class="input-small " style="width:400px">
								<form:option value="" label=""/>/
								<c:forEach items="${educationList}" var="b">
									<form:option value="${b.id}" label="${b.title}"/>
								</c:forEach>
							</form:select>
								<%--<input id="testDataChildList{{idx}}_name" name="testDataChildList[{{idx}}].name" type="text" value="{{row.name}}" maxlength="100" class="input-small "/>--%>
						</td>
							<%--<td>--%>
							<%--<input id="testDataChildList{{idx}}_remarks" name="testDataChildList[{{idx}}].remarks" type="text" value="{{row.remarks}}" maxlength="255" class="input-small "/>--%>
							<%--</td>--%>
						<shiro:hasPermission name="cms:cmsDailyList:edit">
							<td class="text-center" width="10">
							{{#delBtn}}<span class="close" onclick="delRow(this, '#educationChildList{{idx}}')" title="删除">&times;</span>
								{{/delBtn}}
						</td></shiro:hasPermission>
					</tr>
				</script>
				<script type="text/javascript">
                    var educationChildRowIdx = 0,
                        educationChildTpl = $("#educationChildTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
                    $(document).ready(function() {
                        var data = ${fns:toJson(educations)};
                        for (var i=0; i<data.length; i++){
                            addRow('#educationChildList', educationChildRowIdx, educationChildTpl, data[i]);
                            educationChildRowIdx = educationChildRowIdx + 1;
                        }
                    });
				</script>
			</div>
		</div>

		<div class="form-actions">
			<shiro:hasPermission name="cms:cmsDailyList:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>

<script>
    function addRow(list, idx, tpl, row){
            console.log(row)
            $(list).append(Mustache.render(tpl, {
                idx: idx, delBtn: true, row: row
            }));
            $(list+idx).find("select").each(function(){
                // $(this).val($(this).attr("data-value"));
                $(this).find("option").each(function(index,item){
                    if(row != null &&row !='' && row != 'null'){
                        if($(this).attr("value")==row.cmsId){
                            $(this).attr("selected","selected");
                        }
                    }

                });
                // if($(this).id==row.id){
                //    // console.log($(this).id)
                // }
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
        console.log(obj)
        var id = $(prefix+"_id");
        var delFlag = $(prefix+"_delFlag");
        // if (id.val() == ""){
        //     $(obj).parent().parent().remove();
        // }else if(delFlag.val() == "0"){
        //     delFlag.val("1");
        //     $(obj).html("&divide;").attr("title", "撤销删除");
        //     $(obj).parent().parent().addClass("error");
        // }else if(delFlag.val() == "1"){
        //     delFlag.val("0");
        //     $(obj).html("&times;").attr("title", "删除");
        //     $(obj).parent().parent().removeClass("error");
        // }
        $(obj).html("&times;").attr("title", "删除");

        $(obj).parent().parent().removeClass("error");
        $(obj).parent().parent().remove();

    }
</script>
</body>
</html>