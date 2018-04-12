<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>api管理管理</title>
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
		<li><a href="${ctx}/api/thinkerApi/">api管理列表</a></li>
		<li class="active"><a href="${ctx}/api/thinkerApi/form?id=${thinkerApi.id}">api管理<shiro:hasPermission name="api:thinkerApi:edit">${not empty thinkerApi.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="api:thinkerApi:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="thinkerApi" action="${ctx}/api/thinkerApi/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">名称：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="100" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否支持https  0-不支持 1-支持：</label>
			<div class="controls">
				<form:input path="https" htmlEscape="false" maxlength="1" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">描述：</label>
			<div class="controls">
				<form:input path="descs" htmlEscape="false" maxlength="1000" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">数据ID：</label>
			<div class="controls">
				<form:input path="dataId" htmlEscape="false" maxlength="64" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">应用数：</label>
			<div class="controls">
				<form:input path="appNum" htmlEscape="false" maxlength="10" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">接口状态 0-正常 1-故障：</label>
			<div class="controls">
				<form:input path="status" htmlEscape="false" maxlength="1" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">分类：</label>
			<div class="controls">
				<form:input path="category1" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">category2：</label>
			<div class="controls">
				<form:input path="category2" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">category3：</label>
			<div class="controls">
				<form:input path="category3" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">标签：</label>
			<div class="controls">
				<form:input path="tags" htmlEscape="false" maxlength="400" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">接口地址：</label>
			<div class="controls">
				<form:input path="url" htmlEscape="false" maxlength="400" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">请求类型：</label>
			<div class="controls">
				<form:input path="requestType" htmlEscape="false" maxlength="20" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">数据类型 json xml其实可以支持多种：</label>
			<div class="controls">
				<form:input path="dataType" htmlEscape="false" maxlength="20" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">样例地址：</label>
			<div class="controls">
				<form:input path="demoUrl" htmlEscape="false" maxlength="400" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">json返回样例 html格式：</label>
			<div class="controls">
				<form:input path="jsonDemo" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">xml返回样例 html格式：</label>
			<div class="controls">
				<form:input path="xmlDemo" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
			<div class="control-group">
				<label class="control-label">api请求错误码表：</label>
				<div class="controls">
					<table id="contentTable" class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th class="hide"></th>
								<th>错误分类 0-系统错误 1-参数错误 2-服务错误</th>
								<th>状态码</th>
								<th>错误描述</th>
								<th>解决办法 建议</th>
								<th>备注</th>
								<shiro:hasPermission name="api:thinkerApi:edit"><th width="10">&nbsp;</th></shiro:hasPermission>
							</tr>
						</thead>
						<tbody id="thinkerApiErrorcodeList">
						</tbody>
						<shiro:hasPermission name="api:thinkerApi:edit"><tfoot>
							<tr><td colspan="7"><a href="javascript:" onclick="addRow('#thinkerApiErrorcodeList', thinkerApiErrorcodeRowIdx, thinkerApiErrorcodeTpl);thinkerApiErrorcodeRowIdx = thinkerApiErrorcodeRowIdx + 1;" class="btn">新增</a></td></tr>
						</tfoot></shiro:hasPermission>
					</table>
					<script type="text/template" id="thinkerApiErrorcodeTpl">//<!--
						<tr id="thinkerApiErrorcodeList{{idx}}">
							<td class="hide">
								<input id="thinkerApiErrorcodeList{{idx}}_id" name="thinkerApiErrorcodeList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
								<input id="thinkerApiErrorcodeList{{idx}}_delFlag" name="thinkerApiErrorcodeList[{{idx}}].delFlag" type="hidden" value="0"/>
							</td>
							<td>
								<input id="thinkerApiErrorcodeList{{idx}}_types" name="thinkerApiErrorcodeList[{{idx}}].types" type="text" value="{{row.types}}" maxlength="64" class="input-small "/>
							</td>
							<td>
								<input id="thinkerApiErrorcodeList{{idx}}_errorCode" name="thinkerApiErrorcodeList[{{idx}}].errorCode" type="text" value="{{row.errorCode}}" maxlength="20" class="input-small required"/>
							</td>
							<td>
								<input id="thinkerApiErrorcodeList{{idx}}_descs" name="thinkerApiErrorcodeList[{{idx}}].descs" type="text" value="{{row.descs}}" maxlength="200" class="input-small "/>
							</td>
							<td>
								<input id="thinkerApiErrorcodeList{{idx}}_dealMethod" name="thinkerApiErrorcodeList[{{idx}}].dealMethod" type="text" value="{{row.dealMethod}}" maxlength="200" class="input-small "/>
							</td>
							<td>
								<textarea id="thinkerApiErrorcodeList{{idx}}_remarks" name="thinkerApiErrorcodeList[{{idx}}].remarks" rows="4" maxlength="255" class="input-small ">{{row.remarks}}</textarea>
							</td>
							<shiro:hasPermission name="api:thinkerApi:edit"><td class="text-center" width="10">
								{{#delBtn}}<span class="close" onclick="delRow(this, '#thinkerApiErrorcodeList{{idx}}')" title="删除">&times;</span>{{/delBtn}}
							</td></shiro:hasPermission>
						</tr>//-->
					</script>
					<script type="text/javascript">
						var thinkerApiErrorcodeRowIdx = 0, thinkerApiErrorcodeTpl = $("#thinkerApiErrorcodeTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
						$(document).ready(function() {
							var data = ${fns:toJson(thinkerApi.thinkerApiErrorcodeList)};
							for (var i=0; i<data.length; i++){
								addRow('#thinkerApiErrorcodeList', thinkerApiErrorcodeRowIdx, thinkerApiErrorcodeTpl, data[i]);
								thinkerApiErrorcodeRowIdx = thinkerApiErrorcodeRowIdx + 1;
							}
						});
					</script>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">api请求参数说明表：</label>
				<div class="controls">
					<table id="contentTable" class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th class="hide"></th>
								<th>参数名称</th>
								<th>参数类型</th>
								<th>是否必填 0-非必填 1-必填</th>
								<th>备注</th>
								<shiro:hasPermission name="api:thinkerApi:edit"><th width="10">&nbsp;</th></shiro:hasPermission>
							</tr>
						</thead>
						<tbody id="thinkerApiParamList">
						</tbody>
						<shiro:hasPermission name="api:thinkerApi:edit"><tfoot>
							<tr><td colspan="6"><a href="javascript:" onclick="addRow('#thinkerApiParamList', thinkerApiParamRowIdx, thinkerApiParamTpl);thinkerApiParamRowIdx = thinkerApiParamRowIdx + 1;" class="btn">新增</a></td></tr>
						</tfoot></shiro:hasPermission>
					</table>
					<script type="text/template" id="thinkerApiParamTpl">//<!--
						<tr id="thinkerApiParamList{{idx}}">
							<td class="hide">
								<input id="thinkerApiParamList{{idx}}_id" name="thinkerApiParamList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
								<input id="thinkerApiParamList{{idx}}_delFlag" name="thinkerApiParamList[{{idx}}].delFlag" type="hidden" value="0"/>
							</td>
							<td>
								<input id="thinkerApiParamList{{idx}}_name" name="thinkerApiParamList[{{idx}}].name" type="text" value="{{row.name}}" maxlength="100" class="input-small required"/>
							</td>
							<td>
								<input id="thinkerApiParamList{{idx}}_dataType" name="thinkerApiParamList[{{idx}}].dataType" type="text" value="{{row.dataType}}" maxlength="64" class="input-small required"/>
							</td>
							<td>
								<input id="thinkerApiParamList{{idx}}_required" name="thinkerApiParamList[{{idx}}].required" type="text" value="{{row.required}}" maxlength="1" class="input-small required"/>
							</td>
							<td>
								<textarea id="thinkerApiParamList{{idx}}_remarks" name="thinkerApiParamList[{{idx}}].remarks" rows="4" maxlength="255" class="input-small ">{{row.remarks}}</textarea>
							</td>
							<shiro:hasPermission name="api:thinkerApi:edit"><td class="text-center" width="10">
								{{#delBtn}}<span class="close" onclick="delRow(this, '#thinkerApiParamList{{idx}}')" title="删除">&times;</span>{{/delBtn}}
							</td></shiro:hasPermission>
						</tr>//-->
					</script>
					<script type="text/javascript">
						var thinkerApiParamRowIdx = 0, thinkerApiParamTpl = $("#thinkerApiParamTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
						$(document).ready(function() {
							var data = ${fns:toJson(thinkerApi.thinkerApiParamList)};
							for (var i=0; i<data.length; i++){
								addRow('#thinkerApiParamList', thinkerApiParamRowIdx, thinkerApiParamTpl, data[i]);
								thinkerApiParamRowIdx = thinkerApiParamRowIdx + 1;
							}
						});
					</script>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">api请求返回参数说明：</label>
				<div class="controls">
					<table id="contentTable" class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th class="hide"></th>
								<th>返回类型 json/xml</th>
								<th>参数名称</th>
								<th>结果类型 String int char</th>
								<th>错误描述</th>
								<th>备注</th>
								<shiro:hasPermission name="api:thinkerApi:edit"><th width="10">&nbsp;</th></shiro:hasPermission>
							</tr>
						</thead>
						<tbody id="thinkerApiSuccessList">
						</tbody>
						<shiro:hasPermission name="api:thinkerApi:edit"><tfoot>
							<tr><td colspan="7"><a href="javascript:" onclick="addRow('#thinkerApiSuccessList', thinkerApiSuccessRowIdx, thinkerApiSuccessTpl);thinkerApiSuccessRowIdx = thinkerApiSuccessRowIdx + 1;" class="btn">新增</a></td></tr>
						</tfoot></shiro:hasPermission>
					</table>
					<script type="text/template" id="thinkerApiSuccessTpl">//<!--
						<tr id="thinkerApiSuccessList{{idx}}">
							<td class="hide">
								<input id="thinkerApiSuccessList{{idx}}_id" name="thinkerApiSuccessList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
								<input id="thinkerApiSuccessList{{idx}}_delFlag" name="thinkerApiSuccessList[{{idx}}].delFlag" type="hidden" value="0"/>
							</td>
							<td>
								<input id="thinkerApiSuccessList{{idx}}_types" name="thinkerApiSuccessList[{{idx}}].types" type="text" value="{{row.types}}" maxlength="64" class="input-small "/>
							</td>
							<td>
								<input id="thinkerApiSuccessList{{idx}}_name" name="thinkerApiSuccessList[{{idx}}].name" type="text" value="{{row.name}}" maxlength="20" class="input-small required"/>
							</td>
							<td>
								<input id="thinkerApiSuccessList{{idx}}_dataType" name="thinkerApiSuccessList[{{idx}}].dataType" type="text" value="{{row.dataType}}" maxlength="20" class="input-small "/>
							</td>
							<td>
								<input id="thinkerApiSuccessList{{idx}}_descs" name="thinkerApiSuccessList[{{idx}}].descs" type="text" value="{{row.descs}}" maxlength="200" class="input-small "/>
							</td>
							<td>
								<textarea id="thinkerApiSuccessList{{idx}}_remarks" name="thinkerApiSuccessList[{{idx}}].remarks" rows="4" maxlength="255" class="input-small ">{{row.remarks}}</textarea>
							</td>
							<shiro:hasPermission name="api:thinkerApi:edit"><td class="text-center" width="10">
								{{#delBtn}}<span class="close" onclick="delRow(this, '#thinkerApiSuccessList{{idx}}')" title="删除">&times;</span>{{/delBtn}}
							</td></shiro:hasPermission>
						</tr>//-->
					</script>
					<script type="text/javascript">
						var thinkerApiSuccessRowIdx = 0, thinkerApiSuccessTpl = $("#thinkerApiSuccessTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
						$(document).ready(function() {
							var data = ${fns:toJson(thinkerApi.thinkerApiSuccessList)};
							for (var i=0; i<data.length; i++){
								addRow('#thinkerApiSuccessList', thinkerApiSuccessRowIdx, thinkerApiSuccessTpl, data[i]);
								thinkerApiSuccessRowIdx = thinkerApiSuccessRowIdx + 1;
							}
						});
					</script>
				</div>
			</div>
		<div class="form-actions">
			<shiro:hasPermission name="api:thinkerApi:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>