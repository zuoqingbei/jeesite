<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ page isELIgnored="false"%>
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

<section class="met_section met_section_asidenone met_section_sehed">

    <section class="met_section_head">
      <!--  <a href="index.htm" tppabs="http://www.xgxmkj.com/" title="网站首页">网站首页</a> &gt; <a href=%22index-2.htm%22
                                                                                          tppabs="http://www.xgxmkj.com/product/">产品展示</a>
        > <a href=%22product.php-lang=cn&class2=6.htm%22
             tppabs="http://www.xgxmkj.com/product/product.php?lang=cn&class2=6">养牛设备</a>-->
    </section>


    <article>
        <div class="met_article">

            <div class="met_clear"></div>

            <div id="showproduct">
                <dl >
                    <dt data-product_x="400">
                    <div class="met_box" id="product_img">
                    <img id="image" src="${detail.image }" alt="" width="100%" height="100%">
                    </div>
                    </dt>
                    <dd>
                        <div class="met_box">
                            <h1 class='met_title' id="title">${detail.title }</h1>
                   
                            <p class="desc" id="description">${detail.description }</p>
                        </div>
                    </dd>
                </dl>
                <div class="met_clear"></div>
                <h1 class='met_title' style="margin-left: 1em">详细信息</h1>
                <!--<ol class="met_nav">

                    <li class="met_now"><a href="#">详细信息</a></li>

                </ol>-->
                <div class="met_nav_contbox">

                    <div class="met_editor " id="content">
               	    </div>
            	</div>
            </div>
        </div>
    </article>
    <div class="met_clear"></div>
</section>
<script>
  $(function () {
	  getCaseDetail();
    });
	function getCaseDetail(){
		 $.post("${portalPath}/cms/cmsEducation/detail", { id:'${detail.id}'}, function (data) {
			var htmls='';
			data=eval("("+data+")");
			console.log(data)
			if (data.status === "success") {
				$("#title").html(data.data.title);
				$("#description").html(data.data.description);
				$("#image").attr("src",data.data.image);
				$("#content").html(data.data.contentHtml);
			} 
		}) ;
	}
</script>
</body>
</html>