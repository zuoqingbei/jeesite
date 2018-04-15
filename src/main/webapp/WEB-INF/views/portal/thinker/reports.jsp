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
    <link rel="stylesheet" href="${ctxStatic}/${portalPage}/thinker/css/report.css">

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
                    <p>
                    <c:choose>
                    	<c:when test="${entity.link!=''}">
                    	 <a href="${entity.link }" target="${entity.openType }">${entity.systemName }</a>
                    	</c:when>
                    	<c:otherwise>
                    	 <a href="#">${entity.systemName }</a>
                    	</c:otherwise>
                    </c:choose>
                    </p>
                </li>
                <li class="type">
                    <h4>报表类型</h4>
                    <p>${entity.types }</p>
                </li>
                <li class="dimension">
                    <h4>显示维度</h4>
                    <p>
	                    <c:forEach items="${entity.dimensionStr}" var="li">
		                     <span>${li}</span>
	                    </c:forEach>
                    </p>
                </li>
                <li class="departmentName">
                    <h4>使用部门名称</h4>
                    <p>
                    	<c:forEach items="${entity.deptStr}" var="li">
		                     <span>${li}</span>
	                    </c:forEach>
                    </p>
                </li>
                <li class="reportPath">
                    <h4>报表路径</h4>
                    <p>${entity.path }</p>
                </li>
                <li class="reportURL">
                    <h4>报表URL</h4>
                    <p>${entity.url }</p>
                </li>
            </ul>
        </div>
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