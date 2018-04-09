<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>大中恒橡胶</title>
    <!--<meta name="description" content="犊牛岛、牛颈枷、 牛卧栏、 饮水槽 、挤奶机、卷帘、风机、清粪设备等，大中恒橡胶是畜牧养牛行业专用设备行业知名企业，如果您对我公司的产品服务有兴趣，来电咨询010-69207267。" />
    <meta name="keywords" content="牧场设计|养牛场建设|养牛设备|牛颈枷|牛卧床|饮水槽|刮粪板" />-->
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport"/>
    <meta name="generator" content="MetInfo 5.3.19" data-variable="http://www.xgxmkj.com/|cn|10001||10001|metx5"/>
    <link href="${ctxStatic}/${portalPage}/dzh/images/index/logo.jpg"/>
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/${portalPage}/dzh/css/metinfo.css"/>
    <!--[if IE]>
    <script src="../asserts/html5.js"  type="text/javascript"></script>
    <![endif]-->
    <meta name="baidu-site-verification" content="vJ2W57SDVb"/>
    
    <style>
        body,html{
            overflow: hidden;
        }
    </style>
</head>
<body>
<header data-waypointsok="0">
    <section>
        <div class="tem_inner tem_top">
            <h2>
                <p>
                   <span style="color: rgb(34, 34, 34); font-family: Consolas, &#39;Lucida Console&#39;, monospace; font-size: 12px; line-height: normal; white-space: pre-wrap;">大中恒橡胶</span>
                </p>
            </h2>
            <div class="tem_top_nav">

                <dl>
                    <dt><a href="${portalPath}/company/home" tppabs="http://www.xgxmkj.com/" title="简体中文">简体中文<i class="fa fa-caret-down"></i></a></dt>
                </dl>

            </div>
        </div>
    </section>
    <div class="tem_inner tem_head">

        <h1>
            <a href="${portalPath}/company/home"  title="大中恒橡胶">
                <img src="${ctxStatic}/${portalPage}/dzh/images/index/logo.jpg" alt="大中恒橡胶" style="margin:10px 0px 0px 0px;width: 220px;height: 110px" title="大中恒橡胶"/>
            </a>
        </h1>

        <nav>
            <ul><!-- ${ctxStatic}/${portalPage}/dzh/ -->
                <li><a href="${portalPath}/company/home"  title="网站首页"  class="navdown">网站首页</a></li>

                <li>
                    <a href="${portalPath}/company/productList"  title="产品展示">产品展示</a>
                </li>

                <li>
                    <a href="${portalPath}/company/caseList"  title="客户案例">客户案例</a>
                </li>

                <li>
                    <a href="${portalPath}/company/newsList"  title="新闻资讯">新闻资讯</a>
                </li>

                <li>
                    <a href="${portalPath}/company/about"  title="关于我们">关于我们</a>

            </ul>
        </nav>
    </div>
<div class="tem_banner">
        <!--轮播图-->
        <div class="swiper-container" style="height: 400px;width: 100%;">
            <div class="swiper-wrapper">
            	<c:forEach items="${cmsBanner}" var="banner">
                  <div class="swiper-slide"><img src="${banner.image }" alt=""></div>
            	</c:forEach>
            </div>
            <!-- 如果需要分页器 -->
            <div class="swiper-pagination"></div>

            <!-- 如果需要导航按钮 -->
            <!-- <div class="swiper-button-prev"></div>
             <div class="swiper-button-next"></div>-->

            <!-- 如果需要滚动条 -->
            <!--  <div class="swiper-scrollbar"></div>-->
        </div>
    </div>
</header>
</body>
<script>
var index='${index}';
$("a").removeClass("navdown");
$("ul>li").eq(index).find("a").addClass("navdown");
</script>
</html>