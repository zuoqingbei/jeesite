<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8" name="viewport" content="width=device-width,initial-scale=1.0,user-scalable=no">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>数据立方体</title>
    <link rel="stylesheet" href="${ctxStatic}/${portalPage}/thinker/asserts/css/bootstrap-4.0.0.css">
    <link rel="stylesheet" href="${ctxStatic}/${portalPage}/thinker/asserts/css/swiper-4.2.2.min.css">
    <link rel="stylesheet" href="${ctxStatic}/${portalPage}/thinker/asserts/iconfont/iconfont.css">
    <link rel="stylesheet" href="${ctxStatic}/${portalPage}/thinker/css/comm.css">
    <!--<link rel="stylesheet" href="${ctxStatic}/${portalPage}/thinker/css/style.css">-->
    <link rel="stylesheet" href="${ctxStatic}/${portalPage}/thinker/css/report.css">
    <style>
        .main header {
            width: 100%;
        }

        .main header h2 {
            color: var(--textBlue);
            font-size: 1.25rem;
            /*font-weight: bold;*/
            margin: 1.25rem auto 1.25rem 2rem;
        }

        .main header table {
            font-size: .75rem;
            margin-bottom: 0;
        }

        .main header table td {
            color: #666;
            font-size: .9375rem;
            padding: .5rem 2rem;
        }

        .main header table tr td:nth-of-type(odd) {
            width: 8rem;
            color: #999;
        }

        .main header table td.screenUrl {
            word-break: break-all;
        }
    </style>
</head>
<body>
<header class="navbar navbar-expand flex-column flex-md-row bd-navbar">
    <a class="navbar-brand mr-0 mr-md-2" href="/portal/thinker/home">
        <div class="logoBg"></div>
        <h1 class="icon-pre">数据立方体</h1>
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
                    <li class="breadcrumb-item"><a href="#">搜索页面</a></li>
                    <li class="breadcrumb-item active" aria-current="page">指标</li>
                </ol>
            </nav>
        </div>
    </section>
    <section class="row main">
        <header>
            <h2>物流柜收入</h2>
            <table class="table table-bordered table-hover">
                <tbody>
                <tr>
                    <td>指标编号</td>
                    <td class="indicatorNumber">${entity.code }</td>
                    <td>指标名称</td>
                    <td class="indicatorName">${entity.name }<a href="javascript:void();"></a></td>
                </tr>
                <tr>
                    <td>屏幕编号</td>
                    <td class="screenNumber">${entity.screenNo }</td>
                    <td>屏幕名称</td>
                    <td class="screenName">${entity.screenName }</td>
                </tr>
<%--                 <tr>
                    <td>屏幕URL</td>
                    <td colspan="3" class="screenUrl">${entity.screenUrl }</td>
                </tr>
  --%>               </tbody>
            </table>
        </header>
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
    <section class="row main">
        <div class="col-12 list">
            <ul>
                <li class="system">
                    <h4>指标描述</h4>
                    <p>${entity.descs }<a href="#"></a></p>
                </li>
                <li class="type">
                    <h4>显示形式</h4>
                    <p>${entity.showType }</p>
                </li>
                <li class="dimension">
                    <h4>显示表</h4>
                    <p>${entity.showTable }</p>
                </li>
                <li class="departmentName">
                    <h4>使用字段</h4>
                    <p>${entity.useFiled }</p>
                </li>
                <li class="reportPath">
                    <h4>计算公式</h4>
                    <p>${entity.useFiled }</p>
                </li>
            </ul>
        </div>
    </section>
</div>

<script src="${ctxStatic}/${portalPage}/thinker/asserts/js/jquery-3.3.1.min.js"></script>
<script src="${ctxStatic}/${portalPage}/thinker/asserts/iconfont/iconfont.js"></script>
<script>
    $(function () {
        $('.handle li').on('click', function () {
            $(this).toggleClass('active')
        })
    })

</script>
</body>
</html>