<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8" name="viewport" content="width=device-width,initial-scale=1.0,user-scalable=no">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>数据超市</title>
    <link rel="stylesheet" href="${ctxStatic}/${portalPage}/thinker/asserts/css/bootstrap-4.0.0.css">
    <link rel="stylesheet" href="${ctxStatic}/${portalPage}/thinker/asserts/css/swiper-4.2.2.min.css">
    <link rel="stylesheet" href="${ctxStatic}/${portalPage}/thinker/asserts/iconfont/iconfont.css">
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
<div class="container">
    <section class="row">
        <div class="col-">
            <nav aria-label="breadcrumb">
                <ol class="breadcrumb">
                    <li class="">当前位置：</li>
                    <li class="breadcrumb-item"><a href="${portalPath}/thinker/search">搜索页面</a></li>
                    <li class="breadcrumb-item active" aria-current="page">报表</li>
                </ol>
            </nav>
        </div>
    </section>
    <section class="row main">
        <div class="col-12 list">
            <ul>
                <li class="system">
                    <h4>显示系统</h4>
                    <p>1169物流大屏</p>
                </li>
                <li class="type">
                    <h4>报表类型</h4>
                    <p>大屏</p>
                </li>
                <li class="dimension">
                    <h4>包含指标</h4>
                    <p><a href="${portalPath}/thinker/indexDetail" target="_blank"> 物流平台收入</a>、年累计完成率、月累计收入及同期收入、各小微收入占比(月累)、收入月趋势、收入完成率及同比月趋势
                    </p>
                </li>
                <li class="departmentName">
                    <h4>使用部门名称</h4>
                    <p>1169战略部</p>
                </li>
                <li class="netType">
                    <h4>网络类型</h4>
                    <p>内网</p>
                </li>
                <li class="reportPath">
                    <h4>报表路径</h4>
                    <p>战略显示\物流\物流新大屏\物流平台收入</p>
                </li>
                <li class="reportURL">
                    <h4>报表URL</h4>
                    <p><a target="_blank" href="http://sso1169sjdptest.haier.net/web/dp_web/page/index.html?token=788076fefa666589a13eaced3e93fb19&pageId=rrswl_home
">http://sso1169sjdptest.haier.net/web/dp_web/page/index.html?token=788076fefa666589a13eaced3e93fb19&pageId=rrswl_home
                    </a></p>
                </li>
            </ul>
        </div>
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
    </section>

<script src="${ctxStatic}/${portalPage}/thinker/asserts/js/jquery-3.3.1.min.js"></script>
<script src="${ctxStatic}/${portalPage}/thinker/asserts/iconfont/iconfont.js"></script>
<script>
    $(function () {
        $('.handle li').on('click', function () {
            $(this).toggleClass('active')
        });
        $(".techMore>a").click(function () {
            if(!$(this).parent().hasClass("open")){
                $(this).children("span").text("▲").parents("p").addClass("open").next("ul").show();
            }else{
                $(this).children("span").text("▼").parents("p").removeClass("open").next("ul").hide();
            }

        })
    })

</script>
</body>
</html>