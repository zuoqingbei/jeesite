<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8"/>
    <meta name="decorator" content="portal"/>
    <title>大中恒橡胶</title>
    <!--<meta name="description" content="犊牛岛、牛颈枷、 牛卧栏、 饮水槽 、挤奶机、卷帘、风机、清粪设备等，大中恒橡胶是畜牧养牛行业专用设备行业知名企业，如果您对我公司的产品服务有兴趣，来电咨询010-69207267。" />
    <meta name="keywords" content="牧场设计|养牛场建设|养牛设备|牛颈枷|牛卧床|饮水槽|刮粪板" />-->
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport"/>
    <meta name="generator" content="MetInfo 5.3.19" data-variable="http://www.xgxmkj.com/|cn|10001||10001|metx5"/>
    <link rel="stylesheet" href="${ctxStatic}/${portalPage}/dzh/asserts/swiper-3.4.2.min.css">
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/${portalPage}/dzh/css/metinfo.css"/>
    <!--[if IE]>
    <script src="asserts/html5.js" tppabs="http://www.xgxmkj.com/public/js/html5.js" type="text/javascript"></script>
    <![endif]-->
    <meta name="baidu-site-verification" content="vJ2W57SDVb"/>
</head>
<body>

<section class="tem_index_about">
    <div class="tem_inner">
        <h3 class="tem_index_title">
			<span>
				关于大中恒橡胶
				<p></p>
			</span>
        </h3>
        <div class="tem_index_about_cont ">
            <div class="tem_index_about_txt ">
                <div class="met_editor">
                    <div style="color: rgb(51, 51, 51); line-height: 24px; padding-top: 10px; font-size: 12px;">
                        <span
                            style="font-size: 14px;"><strong>关于“<span
                            style="line-height: 24px; font-size: 14px;">大中恒橡胶</span>”</strong></span></div>
                    <div style="color: rgb(51, 51, 51); line-height: 24px; font-size: 12px;">
                        <p style='text-indent: 2em'>
                          青岛大中恒橡胶有限公司坐落在美丽的海滨城市青岛，青岛是中国的重要经济中心和沿海开放城市，青岛又被称为“中国名牌之都”，是重要的港口城市，以独特的魅力吸引着各地的精英们在这里投资发展。相信我们青岛大中恒橡胶有限公司在这特有的环境和浓厚的名牌产业文化气息下，通过我们全体员工的努力和团结，坚持“信誉为重、质量第一”的宗旨，必定会走向辉煌，走出中国走向世界，成为橡胶行业中一颗璀璨的明星。                        <p>
                            　　在专注牧场建设的基础上，修刚畜牧积极投身于和深入延伸于中国奶业产业链上下游，争取成为中国奶业做大做强的中坚和典范力量。修刚畜牧参股黑龙江省供销合作社，成立黑龙江惠丰农牧科技有限公司和黑龙江省惠丰金源电子商务有限公司，未来修刚畜牧将形成牧场建设、牧场咨询服务、农牧咨询服务三驾马车为主营业务的集团型公司。</p>
                        <p>
                            <span style="line-height: 24px; font-weight: 700;"></span>
                        </p>
                        <div style="line-height: 24px; padding-top: 10px; font-size: 12px; white-space: normal;"><span
                                style="font-size: 14px;"><span style="font-weight: 700;">公司主要经营项目</span></span></div>
                        <div style="line-height: 24px; font-size: 12px; white-space: normal;"><p>　
                            在畜牧行业中，多年来，公司自主研发的橡胶牛床垫产品遍布全国各地，并出口北美、南美、欧洲、中东等国家，在国内通过与众多知名畜牧企业合作，取得长期的信任和好评。相信未来的大中恒橡胶，在发展的道路上，能赢得广大新老客户更多的赞誉和支持。
                            <p><strong><span
                                    style="font-size: 14px;">联系电话：13708957511</span></strong></p>
                        </div>
                    </div>
                    <p><strong>15101013101 代经理</strong></p>
                    <div class="met_clear"></div>
                    <h4 class="tem_index_about_more"><a href="pages/about_us.htm"
                                                        title="了解更多" target='_self'>了解更多</a></h4>
                </div>
            </div>

            <div class="tem_index_about_video">
                <img src="${ctxStatic}/${portalPage}/dzh/images/index/logo.jpg" alt="" style="width: 100%;height: 100%">
            </div>

            <div class="met_clear"></div>
        </div>
    </div>
</section>
<!--<!--string(7) "product"
-->
<section class="tem_index_product tem_index_to">
    <div class="tem_inner">
        <h3 class="tem_index_title">
			<span>
				推荐产品
				<p></p>
			</span>
        </h3>
        <ul data-product_x="220">
			<c:forEach items="${products.list}" var="pro">
				<li class="">
					<a href="${portalPath}/company/productDetail?id=${pro.id}" title="${pro.title}" target='_blank'>
						<img src="${pro.image}"
							 title="${pro.title}" alt="${pro.title}" width="220" height="150"/>
						<h2>${pro.title}</h2>
						<h5>${pro.description}</h5>
					</a>
				</li>
			</c:forEach>
            

          
        </ul>
        <div class="met_clear"></div>
        <h4 class="tem_index_more"><a href="${portalPath}/company/productList"  title="浏览更多产品"
                                      >浏览更多产品</a></h4>
    </div>
</section>

</body>
</html>
<script src="${ctxStatic}/${portalPage}/dzh/asserts/sea.js" tppabs="http://www.xgxmkj.com/public/ui/v1/js/sea.js" type="text/javascript"></script>
<script src="${ctxStatic}/${portalPage}/dzh/asserts/swiper-3.4.2.min.js" type="text/javascript"></script>
<script>
    var mySwiper = new Swiper ('.swiper-container', {
        /*
         direction: 'vertical',
         */
        loop: true,
        autoplay :3000,

        // 如果需要分页器
        pagination: '.swiper-pagination',

        // 如果需要前进后退按钮
        nextButton: '.swiper-button-next',
        prevButton: '.swiper-button-prev',

        /*  // 如果需要滚动条
         scrollbar: '.swiper-scrollbar',*/
    })
</script>
