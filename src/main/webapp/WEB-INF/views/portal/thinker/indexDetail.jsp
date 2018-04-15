<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8" name="viewport" content="width=device-width,initial-scale=1.0,user-scalable=no">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>1169数据立方体</title>
    <link rel="stylesheet" href="${ctxStatic}/${portalPage}/thinker/asserts/css/bootstrap-4.0.0.css">
    <link rel="stylesheet" href="${ctxStatic}/${portalPage}/thinker/asserts/css/swiper-4.2.2.min.css">
    <link rel="stylesheet" href="${ctxStatic}/${portalPage}/thinker/asserts/iconfont/iconfont.css">
    <link rel="stylesheet" href="${ctxStatic}/${portalPage}/thinker/css/style.css">
    <link rel="stylesheet" href=".${ctxStatic}/${portalPage}/thinker/css/report.css">
    <style>
        .main header{
            width: 100%;
        }
         .main header h2{
            font-size: 1.25rem;
            font-weight: bold;
            margin: 1.25rem auto 1.25rem 2rem;
        }
         .main header table{
             font-size: .75rem;
             margin-bottom: 0;
         }
        .main header table tr td:nth-of-type(odd){
            color:var(--textBlue)
        }
        .main header table td{
            padding: .5rem 2rem;
        }
    </style>
</head>
<body>
<header class="navbar navbar-expand flex-column flex-md-row bd-navbar">
    <a class="navbar-brand mr-0 mr-md-2" href="#">
        <div class="logoBg"></div>
        <h1 class="icon-pre">1169数据立方体</h1>
    </a>
    <ul class="navbar-nav flex-row ml-md-auto d-none d-md-flex">
        <li class="nav-item">
            <a class="nav-link p-2" href="#" rel="noopener" aria-label="reg">注册</a>
        </li>
        <li class="nav-item">
            <a class="nav-link p-2" href="#" rel="noopener" aria-label="login">登录</a>
        </li>
    </ul>
</header>
<div class="container">
    <section class="row">
        <div class="col-">
            <nav aria-label="breadcrumb">
                <ol class="breadcrumb">
                    <li class="">当前位置：&emsp;</li>
                    <li class="breadcrumb-item"><a href="#">搜索页面</a></li>
                    <li class="breadcrumb-item active" aria-current="page">指标</li>
                </ol>
            </nav>
        </div>
    </section>
    <section class="row main">
        <header>
            <h2>物流柜<a href="">收入</a></h2>
            <table class="table table-bordered table-hover">
                <tbody>
                <tr><td>指标编号</td><td class="indicatorNumber">1169-DGTY-006</td><td>指标名称</td><td class="indicatorName">物流柜<a href="">收入</a></td></tr>
                <tr><td>屏幕编号</td><td class="screenNumber">1169Y11</td><td>屏幕名称</td><td class="screenName">交互营销</td></tr>
                <tr><td>屏幕URL</td><td colspan="3" class="screenNumber">http://code.z01.com/v4/content/tables.html</td></tr>
                </tbody>
            </table>
        </header>
        <div class="handle">
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
    </section>
    <section class="row main">
        <div class="col-12 list">
            <ul>
                <li class="system">
                    <h4>显示系统</h4>
                    <p><a href="#">1169系统平台</a></p>
                </li>
                <li class="type">
                    <h4>报表类型</h4>
                    <p>线下</p>
                </li>
                <li class="dimension">
                    <h4>显示维度</h4>
                    <p><span>小薇编码</span><span>客户门店</span><span>物料</span><span>管理客户</span></p>
                </li>
                <li class="departmentName">
                    <h4>使用部门名称</h4>
                    <p><span>小薇，</span><span>平台，</span><span>产业</span></p>
                </li>
                <li class="reportPath">
                    <h4>报表路径</h4>
                    <p>【零售报表分析】</p>
                </li>
                <li class="reportURL">
                    <h4>报表URL</h4>
                    <p>http://fanyi.youdao.com/</p>
                </li>
            </ul>
        </div>
    </section>
</div>

<script src="${ctxStatic}/${portalPage}/thinker/asserts/js/jquery-3.3.1.min.js"></script>
<script src="${ctxStatic}/${portalPage}/thinker/asserts/iconfont/iconfont.js"></script>
<script>

    $(function () {
        $('.handle span i').on('click',function () {
            $(this).toggleClass('blue')
        })
    })

</script>
</body>
</html>