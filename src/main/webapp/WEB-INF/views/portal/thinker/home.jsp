<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
<head>
    <meta charset="UTF-8" name="viewport" content="width=device-width,initial-scale=1.0,user-scalable=no">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>1169数据立方体</title>
    <link rel="stylesheet" href="${ctxStatic}/${portalPage}/thinker/asserts/css/bootstrap-4.0.0.css">
		<link rel="stylesheet" href="${ctxStatic}/${portalPage}/thinker/asserts/css/swiper-4.2.2.min.css">
		<link rel="stylesheet" href="${ctxStatic}/${portalPage}/thinker/asserts/css/iconfont.css">
		<link rel="stylesheet" href="${ctxStatic}/${portalPage}/thinker/css/index.css">
</head>
<body>
  <div class="home-index">
		<div class="wrapper">
			
			<div class="banner">
				<div class="header">
					<div class="left"><img src="${ctxStatic}/${portalPage}/thinker/images/lifang.png" alt="icon" width="32" height="32"><h5>1169数据立方体</h5></div>
					<div class="right">
						<button class="register" type="button">注册</button>
						<button type="button" class="login">登录</button>
					</div>
				</div>
				<div class="title">
					<img src="${ctxStatic}/${portalPage}/thinker/images/lifang.png" alt=""><h2>1169数据立方体</h2>
				</div>
				<form id="searchForm" action="${portalPath}/thinker/search" type="post">
				<div class="search">
					<input type="text" id="keyword" name="keyWord" placeholder="请输入关键词...">
					<button type="button" id="searchBtn" onclick="search()">搜索</button>
				</div>
				</form>
			</div>
			
			<div class="container-as">
				<div class="hot-key">
					<p class="markt"><i class="iconfont icon-remen1"></i><span>热搜</span></p>
					<div class="lable">
						<span>收入</span><span>指标</span><span>财务运营</span><span>接口</span><span>代码</span>
					</div>
				</div>
				<div class="special">
					<p class="markt"><i class="iconfont icon-zhuantifuwu"></i><span>专题</span></p>
					<div class="slide-offset">
						<div class="swiper-container">
							<div class="swiper-wrapper">
								<div class="swiper-slide"><img src="${ctxStatic}/${portalPage}/thinker/images/1.png" alt="" class="slide-img"><span>财务运营</span></div>
								<div class="swiper-slide"><img src="${ctxStatic}/${portalPage}/thinker/images/2.png" alt="" class="slide-img"><span>智慧生活</span></div>
								<div class="swiper-slide"><img src="${ctxStatic}/${portalPage}/thinker/images/3.png" alt="" class="slide-img"><span>开放式创新</span></div>
								<div class="swiper-slide"><img src="${ctxStatic}/${portalPage}/thinker/images/4.png" alt="" class="slide-img"><span>职能创新</span></div>
								<div class="swiper-slide"><img src="${ctxStatic}/${portalPage}/thinker/images/1.png" alt="" class="slide-img"><span>财务运营</span></div>
								<div class="swiper-slide"><img src="${ctxStatic}/${portalPage}/thinker/images/2.png" alt="" class="slide-img"><span>智慧生活</span></div>
								<div class="swiper-slide"><img src="${ctxStatic}/${portalPage}/thinker/images/3.png" alt="" class="slide-img"><span>开放式创新</span></div>
								<div class="swiper-slide"><img src="${ctxStatic}/${portalPage}/thinker/images/4.png" alt="" class="slide-img"><span>职能创新</span></div>
							</div>
							<!-- Add Pagination -->
							<div class="swiper-pagination"></div>
							<!-- Add Arrows -->
						</div>
						<div class="swiper-button-next"></div>
						<div class="swiper-button-prev"></div>
					</div>
				
				</div>
			</div>
		</div>
	</div>
</body>

<script src="${ctxStatic}/${portalPage}/thinker/asserts/js/jquery-3.3.1.min.js"></script>
<script src="${ctxStatic}/${portalPage}/thinker/asserts/js/bootstrap-4.0.0.js"></script>
<script src="${ctxStatic}/${portalPage}/thinker/asserts/js/swiper-4.2.2.min.js"></script>
<script src="${ctxStatic}/${portalPage}/thinker/js/main.js"></script>
<script>

    $(function () {
			var swiper = new Swiper('.swiper-container', {
				slidesPerView: 4,
				spaceBetween: 30,
				slidesPerGroup: 4,
				loop: true,
				loopFillGroupWithBlank: true,
				navigation: {
					nextEl: '.swiper-button-next',
					prevEl: '.swiper-button-prev',
				},
			});	

			$('.lable span').click(function () {
				$('.lable span').css({'border':'1px solid #8e8e8e','color':'#8e8e8e'})
				$(this).css({'border':'1px solid #2774b6','color':'#2774b6'})
				var value = $(this)[0].innerHTML;
				console.log(value);
				window.location.href = 'http://'+location.hostname+':'+location.port+'/portal/thinker/search?content='+value;
				//alert(value);
			});
			$('#keyword').bind('keyup', function(event) {
					if (event.keyCode == "13") {
						$('#searchBtn').click();
					}
				});
			
    })
	function search() {
		$("#searchForm").submit();
		//var content = $('.search input').val();
		//alert(m);
		//window.location.href = '${portalPath}/thinker/search?keyWord='+content;
	};

</script>
</body>
</html>