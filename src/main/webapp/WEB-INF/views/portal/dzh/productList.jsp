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

<section class="met_section  ">


    <aside>

		<section class="met_aside">
			<h2>产品展示</h2>
			<div class="met_aside_list">
				<c:forEach items="${dicts}" var="dict" varStatus="stat">
				 <c:choose>
					<c:when test="${stat.index eq 0}">
					 <dl onclick="changeData(this)" class="list-none navnow"><dt class='on' data="${dict.value}"><a href="javascript:void();" title='${dict.label}' class="zm"><span>${dict.label}</span></a></dt></dl>
					</c:when>
					<c:otherwise>
					 <dl onclick="changeData(this)" class="list-none navnow"><dt class='' data="${dict.value}"><a href="javascript:void();" title='${dict.label}' class="zm"><span>${dict.label}</span></a></dt></dl>
					</c:otherwise>
					</c:choose>
				</c:forEach>
				
<!--
				<dl class="list-none navnow"><dt id='part2_106' ><a href="product.php-lang=cn&class2=106.htm" tppabs="http://www.xgxmkj.com/product/product.php?lang=cn&class2=106"  title='通风设备' class="zm"><span>通风设备</span></a></dt></dl>
-->
				<div class="met_clear"></div>
			</div>
		</section>


    </aside>

    <article>
		<div class="met_article">

		<section class="met_article_head">
			<h1 id="mine_tip">
			<c:forEach items="${dicts}" var="dict" varStatus="stat">
					<c:if test="${stat.index eq 0}">
					${dict.label}
					</c:if>
			</c:forEach>
			</h1>
			<div class="met_position">
				<a href="${portalPath}/company/home"  title="网站首页">网站首页</a> &gt;
				<a href="${portalPath}/company/productList" >产品展示</a> > 
				
				<c:forEach items="${dicts}" var="dict" varStatus="stat">
					<c:if test="${stat.index eq 0}">
					<a href="javascript:void(0);" id="menu_name">${dict.label}</a>
					</c:if>
				</c:forEach>
			</div>
		</section>

		<div class="met_clear"></div>

        <div class="met_module3_list">

			<ul class="list_1">
			</ul>

			<div class="met_clear"></div>
			
		    <div class='met_pager'>
		    </div>
		
		</div>

	</div>
    </article>
    <div class="met_clear"></div>
</section>
<script>
var tags;
var pageNo=1;
var pageSize=8;
  $(function () {
        initData(1);
    });
	function changeData(obj){
		$("#mine_tip").html($(obj).find("span").html());
		$("#menu_name").html($(obj).find("span").html());
		$(".on").removeClass("on");
		$(obj).find("dt").addClass("on");
		initData(1);
	}
	function initData(nums){
		pageNo=nums;
		tags=$(".on").attr("data");
		getProductDate();
	}
	function getProductDate(){
		 $.post("${portalPath}/cms/cmsProduct/getList", {
			 tags:tags,pageNo:pageNo,pageSize:pageSize
		}, function (data) {
			var htmls='';
			data=eval("("+data+")");
			if (data.status === "success") {
				createPager(parseInt(data.totalPage));
				$.each(data.data,function(index,item){
					htmls+=' <li><a href="${portalPath}/company/productDetail?id='+item.id+'" title="犊牛栏" target="_blank">';
					htmls+='<img src="'+item.image+'"';
					htmls+='title="'+item.title+'" alt="'+item.title+'" width="220" height="150"/>';
					htmls+='<h2>'+item.title+'</h2>';
					htmls+='<h5>'+item.description+'</h5>';
					htmls+='</a></li>';
					$(".list_1").html(htmls);
				});
			} 
		}) ;
	}
	function createPager(totalPage){
		//<span class='PreSpan'>上一页</span><a class='Ahover'>1</a><a>2</a><a class='NextA'>下一页</a>	
		var htmls='';
		if(pageNo==1||totalPage==1){
			htmls+='<span class="PreSpan">上一页</span>';
		}else{
			htmls+='<span class="PreSpan" onclick=initData('+(pageNo-1)+')>上一页</span>';
		}
		for(var x=1;x<totalPage+1;x++){
			if(x==pageNo){
				htmls+='<a class="Ahover" onclick=initData('+x+')>'+x+'</a>';
			}else{
				htmls+='<a  onclick=initData('+x+')>'+x+'</a>';
			}
		}
		if(pageNo==totalPage){
			htmls+='<a class="NextA">下一页</a>';
		}else{
			htmls+='<a class="NextA" onclick=initData('+(pageNo+1)+')>下一页</a>';
		}
		$(".met_pager").html(htmls);
			   
	}
</script>
</body>

</html>
<script src="${ctxStatic}/${portalPage}/dzh/asserts/sea.js" tppabs="http://www.xgxmkj.com/public/ui/v1/js/sea.js" type="text/javascript"></script>
