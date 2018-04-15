<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<link rel="stylesheet" href="${ctxStatic}/${portalPage}/thinker/css/reset.css" />
		<link rel="stylesheet" href="${ctxStatic}/${portalPage}/thinker/asserts/iconfont/iconfont.css" />
		<link rel="stylesheet" type="text/css" href="${ctxStatic}/${portalPage}/thinker/css/detailApi.css"/>
		
	</head>
	<body>
		<div id="bottom">
			<div class="bottomCon con">
				<div class="crumb">
					<span>当前位置：搜索页面 ><span class="bl">竞争力形式检测API</span></span>
				</div>
				
				<div class="apiInfo">
					<div class="topTitle">
						<h2>竞争力形式检测API</h2>
						<div class="interact">
							<span>
								<i class="iconfont icon-shoucang"></i>
								<span>收藏</span>
							</span>
							<span>
								<i class="iconfont icon-dianzan"></i>
								<span>点赞</span>
							</span>
							<span>
								<i class="iconfont icon-fenxiang"></i>
								<span>分享</span>
							</span>
						</div>
					</div>
					<div class="info">根据全国竞争力形式，做出精细化的服务分析</div>
					
					<div class="zb_box">
						<li>
							<span>数据ID：</span>
							<span>20</span>
						</li>
						<li>
							<span>连接应用数：</span>
							<span>20</span>
						</li>
						<li>
							<span>接口状态：</span>
							<span>正常</span>
						</li>
					</div>
				</div>
			
				<div class="tabBox">
					<ul>
						<li class="liActive" flag="1">API文档</li>
						<li flag="2">错误代码参考</li>
						<li flag="3">示例代码</li>
						<li flag="4">联系我们</li>
					</ul>
					
					<div class="defaultCodeCon">
						
						
						<div class="part-title ts">服务级错误码参照(err_code)</div>
						<table border="0" cellspacing="0" cellpadding="0" rules="rows">
							<tr class="th">
								<td>错误码</td>
								<td>说明</td>
							</tr>
							<tr>
								<td>是</td>
								<td>是否存在问题</td>
							</tr>
						</table>
						<div class="part-title">系统级错误码参照</div>
						<table border="0" cellspacing="0" cellpadding="0" rules="rows">
							<tr class="th">
								<td>错误码</td>
								<td>说明</td>
								<td>旧版本</td>
							</tr>
							<tr>
								<td>lkjfljl</td>
								<td>string</td>
								<td>是否存在问题</td>
							</tr>
						</table>
						<div class="part-title">错误码格式说明</div>
						<table border="0" cellspacing="0" cellpadding="0" rules="rows">
							<tr class="th">
								<td>2</td>
								<td>002</td>
								<td>01</td>
							</tr>
							<tr>
								<td>lkjfljl</td>
								<td>string</td>
								<td>是否存在问题</td>
							</tr>
						</table>
					</div>
					
					<div class="callUsCon">
						<div class="part-title ts">联系我们</div>
						<table border="0" cellspacing="0" cellpadding="0" rules="rows">
							<tr class="th">
								<td>内容</td>
								<td>详细</td>
							</tr>
							<tr>
								<td>是</td>
								<td>是否存在问题</td>
							</tr>
						</table>
						
					</div>
					
					
					<div class="demoCodeCon">
						
						
						<div class="part-title ts">完整教学代码示例</div>
						<table border="0" cellspacing="0" cellpadding="0" rules="rows">
							<tr class="th">
								<td>语言</td>
								<td>标题</td>
								<td>提供者</td>
								<td>时间</td>
							</tr>
							<tr>
								<td>是</td>
								<td>是否存在问题</td>
								<td>是否存在问题</td>
								<td>是否存在问题</td>
							</tr>
						</table>
						<div class="part-title">常见问题</div>
						<table border="0" cellspacing="0" cellpadding="0" rules="rows">
							<tr class="th">
								<td>内容</td>
								<td>详细</td>
							</tr>
							<tr>
								<td>lkjfljl</td>
								<td>是否存在问题</td>
							</tr>
						</table>
						
					</div>
					
					
					<div class="docCon">
						<p>接口地址：<span>http://v.juhe.cn/xiangji_weather/real_time_weather.php</span></p>
						<p>返回格式：<span>json</span></p>
						<p>请求方式：<span>get</span></p>
						<p>请求示例：<span>http://v.juhe.cn/xiangji_weather/real_time_weather.php?areaid=101010100&key=YOURKEY</span></p>
						<p>接口备注：<span>根据竞争力形式分析数据</span></p>
						<div class="blueBtn">API测试工具</div>
						
						<div class="part-title">请求参数说明</div>
						<table border="0" cellspacing="0" cellpadding="0" rules="rows">
							<tr class="th">
								<td>名称</td>
								<td>类型</td>
								<td>必填</td>
								<td>说明</td>
							</tr>
							<tr>
								<td>lkjfljl</td>
								<td>string</td>
								<td>是</td>
								<td>是否存在问题</td>
							</tr>
						</table>
						<div class="part-title">返回参数说明</div>
						<table border="0" cellspacing="0" cellpadding="0" rules="rows">
							<tr class="th">
								<td>名称</td>
								<td>类型</td>
								<td>说明</td>
							</tr>
							<tr>
								<td>lkjfljl</td>
								<td>string</td>
								<td>是否存在问题</td>
							</tr>
						</table>
						<div class="part-title">JSON返回示例：</div>
						<pre class="jsontxt">
							{
							    "reason": "成功",
							    "result": {
							        "jobid": "2015120913503797592",/*本次查询流水号*/
							        "realname": "商世界",/*姓名*/
							        "bankcard": "6259656360701234",/*银行卡卡号*/
							        "idcard": "310329198103050011",/*身份证号码*/
							        "mobile": "18912341234",/*预留手机号码*/
							        "res": "2",/*验证结果，1:匹配 2:不匹配*/
							        "message": "认证信息不匹配"/*描述*/
							    },
							    "error_code": 0
							}
						</pre>
					</div>
					
					
				</div>
			</div>
		</div>
		
		<script src="${ctxStatic}/${portalPage}/thinker/asserts/js/jquery-2.2.4.js" type="text/javascript" charset="utf-8"></script>
		<script src="${ctxStatic}/${portalPage}/thinker/js/detailApi.js" type="text/javascript" charset="utf-8"></script>
	</body>
</html>
