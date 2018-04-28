<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>数据超市</title>
    <link rel="stylesheet" href="${ctxStatic}/${portalPage}/thinker/asserts/css/bootstrap-4.0.0.css">
    <link rel="stylesheet" href="${ctxStatic}/${portalPage}/thinker/css/reset.css"/>
    <link rel="stylesheet" href="${ctxStatic}/${portalPage}/thinker/asserts/iconfont/iconfont.css"/>
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/${portalPage}/thinker/css/detailApi.css"/>
    <link rel="stylesheet" href="${ctxStatic}/${portalPage}/thinker/css/comm.css">
    <link rel="stylesheet" href="${ctxStatic}/${portalPage}/thinker/css/report.css">
</head>
<body>
<header class="navbar navbar-expand flex-column flex-md-row bd-navbar">
    <a class="navbar-brand mr-0 mr-md-2" href="/portal/thinker/home">
        <div class="logoBg"></div>
        <h1 class="icon-pre">数据超市</h1>
    </a>
    <ul class="navbar-nav flex-row ml-md-auto d-none d-md-flex">
        <li class="nav-item">
            <a class="nav-link py-0 px-2 my-2 mx-2" href="#" rel="noopener" aria-label="reg">注册</a>
        </li>
        <li class="split py-1 my-1">|</li>
        <li class="nav-item">
            <a class="nav-link py-0 px-2 my-2 mx-2" href="#" rel="noopener" aria-label="login">登录</a>
        </li>
    </ul>
</header>
<div id="bottom">
    <div class="bottomCon con">
        <div class="crumb">
            <span>当前位置：<a href="${portalPath}/thinker/search" class="bl">搜索页面</a> ><span
                    class="bl">${entity.name}</span></span>
        </div>

        <div class="apiInfo">
            <div class="handle">
                <ul>
                    <li class="favorite">
                        <i class="iconfont icon-shoucang"></i>
                        <span>收藏</span>
                    </li>
                    <li class="split">|</li>
                    <li class="good">
                        <i class="iconfont icon-dianzan"></i>
                        <span>点赞</span>
                    </li>
                    <li class="split">|</li>
                    <li class="share">
                        <i class="iconfont icon-fenxiang"></i>
                        <span>分享</span>
                    </li>
                </ul>

            </div>
            <div class="topTitle">
                <div class="header">
                    <h2>${entity.name}</h2>
                    <c:choose>
                        <c:when test="${item.https==是}">
                            <span class="support">支持HTTPS</span>
                        </c:when>
                        <c:otherwise>
                            <span class="support">不支持HTTPS</span>
                        </c:otherwise>
                    </c:choose>
                </div>


            </div>
            <div class="info">${entity.descs}</div>
<!--
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
-->
            <div class="zb_box">
                <li>
                    <span>数据ID：</span>
                    <span>${entity.dataId}</span>
                </li>
                <li>
                    <span>连接应用数：</span>
                    <span>${entity.appNum}</span>
                </li>
                <br/>
                <li class="mart">
                    <span>接口状态：</span>
                    <c:choose>
                        <c:when test="${entity.status==0}">
                            <span>正常</span>
                        </c:when>
                        <c:otherwise>
                            <span>故障</span>
                        </c:otherwise>
                    </c:choose>
                </li>
                <li class="mart">
                    <span>接入服务商：</span>
                    <span>1169数据超市</span>
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


                <div class="part-title ts">服务级错误码参照</div>
                <!-- (err_code) -->
                <table border="0" cellspacing="0" cellpadding="0" rules="rows" class="table table-bordered table-hover">
                    <tr class="th">
                        <td>错误码</td>
                        <td>说明</td>
                    </tr>
                    <c:forEach items="${entity.thinkerApiErrorcodeList}" var="item">
                        <tr>
                            <c:if test="${item.types==2}">
                                <td>${item.errorCode}</td>
                            </c:if>
                            <c:if test="${item.types==2}">
                                <td>${item.descs}</td>
                            </c:if>
                        </tr>
                    </c:forEach>
                </table>
                <div class="part-title">系统级错误码参照</div>
                <table border="0" cellspacing="0" cellpadding="0" rules="rows" class="table table-bordered table-hover">
                    <tr class="th">
                        <td>错误码</td>
                        <td>说明</td>
                        <td>旧版本</td>
                    </tr>
                    <c:forEach items="${entity.thinkerApiErrorcodeList}" var="item">
                        <tr>
                            <c:if test="${item.types==0}">
                                <td>${item.errorCode}</td>
                            </c:if>
                            <c:if test="${item.types==0}">
                                <td>${item.descs}</td>
                            </c:if>
                            <c:if test="${item.types==0}">
                                <td>${item.descs}</td>
                            </c:if>
                        </tr>
                    </c:forEach>
                </table>
                <div class="part-title">错误码格式说明</div>
                <table border="0" cellspacing="0" cellpadding="0" rules="rows" class="table table-bordered table-hover">
                    <tr class="th">
                        <td>错误码</td>
                        <td>说明</td>
                        <td>旧版本</td>
                    </tr>
                    <c:forEach items="${entity.thinkerApiErrorcodeList}" var="item">
                        <tr>
                            <c:if test="${item.types==1}">
                                <td>${item.errorCode}</td>
                            </c:if>
                            <c:if test="${item.types==1}">
                                <td>${item.descs}</td>
                            </c:if>
                            <c:if test="${item.types==1}">
                                <td>${item.descs}</td>
                            </c:if>
                        </tr>
                    </c:forEach>
                </table>
            </div>

            <div class="callUsCon">
                <div class="part-title ts">联系我们</div>
                <table border="0" cellspacing="0" cellpadding="0" rules="rows" class="table table-bordered table-hover">
                    <tr class="th">
                        <td>姓名</td>
                        <td>邮箱</td>
                        <td>联系电话</td>
                    </tr>
                    <tr>
                        <td>张三</td>
                        <td>1169@haier.com</td>
                        <td>1334455667788</td>
                    </tr>
                </table>

            </div>


            <div class="demoCodeCon">


                <div class="part-title ts">完整教学代码示例</div>
                <table border="0" cellspacing="0" cellpadding="0" rules="rows" class="table table-bordered table-hover">
                    <tr class="th">
                        <td>语言</td>
                        <td>标题</td>
                        <td>提供者</td>
                        <td>时间</td>
                    </tr>
                    <tr>
                        <td>JAVA</td>
                        <td>Dubbo示例代码</td>
                        <td>1169</td>
                        <td>2018年4月15日</td>
                    </tr>
                </table>
                <div class="part-title">常见问题</div>
                <table border="0" cellspacing="0" cellpadding="0" rules="rows" class="table table-bordered table-hover">
                    <tr class="th">
                        <td>内容</td>
                        <td>详细</td>
                    </tr>
                    <tr>
                        <td>注册中心</td>
                        <td>注册中心的配置问题</td>
                    </tr>
                </table>

            </div>


            <div class="docCon">
                <p>接口地址：<span>${entity.url}</span></p>
                <p>返回格式：<span>${entity.dataType}</span></p>
                <p>请求方式：<span>${entity.requestType}</span></p>
                <p>请求示例：<span>${entity.demoUrl}</span></p>
                <p>接口备注：<span>${entity.remarks}</span></p>
                <div class="blueBtn">API测试工具</div>

                <div class="part-title">请求参数说明</div>
                <table border="0" cellspacing="0" cellpadding="0" rules="rows" class="table table-bordered table-hover">
                    <tr class="th">
                        <td>名称</td>
                        <td>类型</td>
                        <td>必填</td>
                        <td>说明</td>
                    </tr>
                    <c:forEach items="${entity.thinkerApiParamList}" var="item">
                        <tr>
                            <td>${item.name}</td>
                            <td>${item.dataType}</td>
                            <c:if test="${item.required==0}">
                                <td>非必填</td>
                            </c:if>
                            <c:if test="${item.required==1}">
                                <td>必填</td>
                            </c:if>
                            <td>${item.remarks}</td>
                        </tr>
                    </c:forEach>
                </table>
                <div class="part-title">返回参数说明</div>
                <table border="0" cellspacing="0" cellpadding="0" rules="rows" class="table table-bordered table-hover">
                    <tr class="th">
                        <td>名称</td>
                        <td>类型</td>
                        <td>说明</td>
                    </tr>
                    <c:forEach items="${entity.thinkerApiSuccessList}" var="item">
                        <tr>
                            <td>${item.name}</td>
                            <td>${item.dataType}</td>
                            <td>${item.remarks}</td>
                        </tr>
                    </c:forEach>
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

<script src="${ctxStatic}/${portalPage}/thinker/asserts/js/jquery-2.2.4.js" type="text/javascript"
        charset="utf-8"></script>
<script src="${ctxStatic}/${portalPage}/thinker/js/detailApi.js" type="text/javascript" charset="utf-8"></script>
<script>
    //console.log('${entity.id}');
    console.log('${entity.thinkerApiParamList}');
    $.post("/portal/api/detail", {id: '${entity.id}'}, function (data, status) {
        console.log(data);
        console.log("Data: " + data + "\nStatus: " + status);
        //$('.jsontxt').html(data.jsonDemo);
        //$('.jsontxt').html('<h1>aaaaaa</h1>');
    });
    $(function () {
        $('.handle li').on('click', function () {
            $(this).toggleClass('active')
        })
    })
</script>
</body>
</html>
