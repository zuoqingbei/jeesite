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
<div class="container-fluid">
	<img style="width:100%" src="/static/images/yingXiangFenXi.png" alt="">
</div>

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
</body>
</html>