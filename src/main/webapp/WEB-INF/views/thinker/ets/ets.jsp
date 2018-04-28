<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8" name="viewport" content="width=device-width,initial-scale=1.0,user-scalable=no">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>etsDemo</title>
    <%-- <link rel="stylesheet" href="${ctxStatic}/${portalPage}/thinker/asserts/css/bootstrap.min-4.0.0.css"> --%>
    <link href="https://cdn.bootcss.com/bootstrap/4.0.0/css/bootstrap.css" rel="stylesheet">
    <style>
        img{
            width: 100%;
            /*height: 100%;*/
        }
        .tab-pane{
            padding: 1rem;
        }
    </style>
</head>
<body>
<div class="container-fluid">
    <header class="row">
        <img src="/static/images/header.png" alt="">
    </header>
    <ul class="nav nav-tabs" id="myTab" role="tablist">
        <li class="nav-item">
            <a class="nav-link active" id="home-tab" data-toggle="tab" href="#home" role="tab" aria-controls="home" aria-selected="true">系统运行信息</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" id="profile-tab" data-toggle="tab" href="#profile" role="tab" aria-controls="profile" aria-selected="false">系统性能分析</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" id="contact-tab" data-toggle="tab" href="#contact" role="tab" aria-controls="contact" aria-selected="false">系统配置检测</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" id="yingXiang-tab" data-toggle="tab" href="#yingXiang" role="tab" aria-controls="yingXiang" aria-selected="false">影响分析</a>
        </li>
    </ul>
    <div class="tab-content" id="myTabContent">
        <div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
            <img src="/static/images/xiTongYunXing.png" alt="">
        </div>
        <div class="tab-pane fade" id="profile" role="tabpanel" aria-labelledby="profile-tab">
            <ul class="nav nav-tabs" id="myTab2" role="tablist">
                <li class="nav-item small">
                    <a class="nav-link active" id="home-tab2" data-toggle="tab" href="#home2" role="tab" aria-controls="home" aria-selected="true">耗时长分析</a>
                </li>
                <li class="nav-item small">
                    <a class="nav-link" id="profile-tab2" data-toggle="tab" href="#profile2" role="tab" aria-controls="profile" aria-selected="false">增长率分析</a>
                </li>
                <li class="nav-item small">
                    <a class="nav-link" id="contact-tab2" data-toggle="tab" href="#contact2" role="tab" aria-controls="contact" aria-selected="false">并发量分析</a>
                </li>
            </ul>
            <div class="tab-content" id="myTabContent2">
                <div class="tab-pane fade show active" id="home2" role="tabpanel" aria-labelledby="home-tab">
                    <img src="/static/images/haoShiChang.png" alt="">
                </div>
                <div class="tab-pane fade" id="profile2" role="tabpanel" aria-labelledby="profile-tab">
                    <img src="/static/images/zengZhangLv.png" alt="">
                </div>
                <div class="tab-pane fade" id="contact2" role="tabpanel" aria-labelledby="contact-tab">
                    <img src="/static/images/bingFaLiang.jpg" alt="">
                </div>
            </div>
        </div>
        <div class="tab-pane fade" id="contact" role="tabpanel" aria-labelledby="contact-tab">
            <img src="/static/images/xiTongPeiZhi.png" alt="">
        </div>
        <div class="tab-pane fade" id="yingXiang" role="tabpanel" aria-labelledby="yingXiang-tab">
            <img src="/static/images/yingXiangFenXi.png" alt="">
        </div>
    </div>
</div>

<%-- <script src="${ctxStatic}/${portalPage}/thinker/asserts/js/jquery-3.3.1.min.js"></script> --%>
<%-- <script src="${ctxStatic}/${portalPage}/thinker/asserts/js/bootstrap-4.0.0.js"></script> --%>
<script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.slim.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/4.0.0/js/bootstrap.js"></script>
</body>
</html>