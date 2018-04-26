<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8" name="viewport" content="width=device-width,initial-scale=1.0,user-scalable=no">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>数据立方体</title>
    <link rel="stylesheet" href="${ctxStatic}/${portalPage}/thinker/asserts/css/bootstrap-4.0.0.css">
    <link rel="stylesheet" href="${ctxStatic}/${portalPage}/thinker/asserts/css/page.css">
    <link rel="stylesheet" href="${ctxStatic}/${portalPage}/thinker/css/style.css">
    <link rel="stylesheet" href="${ctxStatic}/${portalPage}/thinker/css/reset.css">
    <link rel="stylesheet" href="${ctxStatic}/${portalPage}/thinker/css/comm.css">
    <link rel="stylesheet" href="${ctxStatic}/${portalPage}/thinker/asserts/iconfont/iconfont.css" />
    <link rel="stylesheet" href="${ctxStatic}/${portalPage}/thinker/css/Search.css">

</head>

<body>
<header class="navbar navbar-expand flex-column flex-md-row bd-navbar">
    <a class="navbar-brand mr-0 mr-md-2" href="/portal/thinker/home">
        <div class="logoBg"></div>
        <h1 class="icon-pre">数据立方体</h1>
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
<div class="search_box clearfix">
    <!--头部收缩框-->
    <div class="serch_head clearfix">
       <div class="content clearfix">
           <div class="serch_head_content">
               <div class="serch_head_content_left">
                   <div class="serch_icon"></div>
               </div>
               <div class="serch_head_content_right">
                   <div class="serch_head_input">
                       <div class="serch_input"><input id="keyword" type="text" placeholder="请输入热搜词" value="${keyWord}"></div>
                       <div class="serch_button"><button id="searchBtn" onclick="search()">搜 索</button></div>
                   <div class="serch_head_checkbox">
                     <!-- <span class="fl">热搜：</span>
                       <label class="demo&#45;&#45;label"><input class="demo&#45;&#45;radio" type="checkbox" name="demo-checkbox1">
                           <span class="demo&#45;&#45;checkbox demo&#45;&#45;radioInput"></span>指标
                       </label>
                       <label class="demo&#45;&#45;label">
                           <input class="demo&#45;&#45;radio" type="checkbox" name="demo-checkbox2">
                           <span class="demo&#45;&#45;checkbox demo&#45;&#45;radioInput"></span>财务运营
                       </label>
                       <label class="demo&#45;&#45;label">
                           <input class="demo&#45;&#45;radio" type="checkbox" name="demo-checkbox3">
                           <span class="demo&#45;&#45;checkbox demo&#45;&#45;radioInput"></span>接口
                       </label>
                       <div class="hot_words fl">
                          <ul>
                               <li class="active">指标</li>
                               <li>财务运营</li>
                               <li>接口</li>
                           </ul>
                       </div> -->
                   </div>
               </div>
           </div>
       </div>
    </div>

</div>
    <div class="content main clearfix">
        <!--内容部分-->
        <div class="search_content clearfix">
            <!--头部导航-->
            <div class="serch_content_header">
                <!-- <span>当前位置：</span><span class="blue">搜索条件</span> -->
                <span class="label label0"><span class="txt"></span><span class="close_label" onclick="closeLable(1)">×</span></span>
                 <span class="label label1"><span class="txt1"></span><span class="close_label" onclick="closeLable(2)">×</span></span>
                 <span class="label label2"><span class="txt2"></span><span class="close_label" onclick="closeLable(3)">×</span></span>
            </div>
            <!--内容部分-->
            <div class="search_content_box clearfix">
                <!--左侧的一级菜单-->
                <div class="search_content_box_left fl">
                    <ul class="menus_head">全部分类<i class="iconfont icon-plus-select-down"></i></ul>
                    <ul class="menus_box">
                    	 <c:forEach items="${category}" var="ca">
                    	 	<li onclick="changeCategory('${ca.name}','','')" data="${ca.id }" pid="${ca.parent.id }">${ca.name }<span class="fr"><i class="iconfont icon-youzhankai"></i></span></li>
	                    </c:forEach>
                    </ul>

                </div>
                <!--触发后的二三级菜单-->
               	<c:forEach items="${category}" var="first">
               		<div class="menus_two_box" id="menus_two_box_${first.id }">
                    	<c:if test="${fn:length(first.children) gt 0}">
		                    <ul class="menus_two">
			                    	<c:forEach items="${first.children}" var="second">
				                           <li ><span onclick="changeCategory('${first.name}','${second.name}','')" class="fl">${second.name }</span>
				                           <%-- <c:if test="${fn:length(second.children) gt 0}"> --%>
					                            <ul class="menus_three fl">
					                            	<c:forEach items="${second.children}" var="third">
						                                <li onclick="changeCategory('${first.name}','${second.name}','${third.name}')">${third.name }</li>
					                            	</c:forEach>
					                            </ul>
		                    				<%-- </c:if> --%>
				                        </li>
			                    	</c:forEach>
		                    </ul>
                    	</c:if>
                </div>
               	
               	</c:forEach>
                
                <!--信息列表-->
                <div class="search_content_box_right fl clearfix">
                    <div class="search_content_main">
                        <div class="search_result">
                           	 搜索结果：<span>共搜索到<span class="search_result_number"></span>条结果 </span>
                        </div>
                        
                        
                        <div id="content"></div>
                        
                        <ul class="page" maxshowpageitem="5" pagelistcount="10"  id="page"></ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script src="${ctxStatic}/${portalPage}/thinker/asserts/js/jquery-2.2.4.js"></script>
<script src="${ctxStatic}/${portalPage}/thinker/asserts/js/bootstrap-4.0.0.js"></script>
<script src="${ctxStatic}/${portalPage}/thinker/asserts/js/page.js"></script>
<script src="${ctxStatic}/${portalPage}/thinker/js/search.js"></script>
<script>
	var category1;//一级分类
	var category2;//二级分类
	var category3;//三级分类
	var tags;//标签
	var pageNo=1;//当前页面
	var pageSize=$(".page").attr("pagelistcount");//每页条数
	var keyword;
    $(function () {

        $(".serch_head_checkbox .hot_words ul").on("click","li",function () {
            $(this).addClass("active").siblings().removeClass()
        })

        var menus_lis=$('.menus_box li');
        menus_lis.on('mouseenter',function () {
            for(var i=0;i<=menus_lis.length;i++) {
                $(this).addClass('menus_li_active').siblings('li').removeClass('menus_li_active')
                $('.menus_two_box').css('display','none');
                $("#menus_two_box_"+$(this).attr("data")).css('display','block');
               $(this).find('i').css('color','white').parents('li').siblings('li').find('i').css('color','#333')
            }
        }).on('mouseleave',function () {
                $('.menus_two_box').css('display','none');
        })
        $('.menus_two_box').on('mouseenter',function () {
            $(this).css('display','block')
        }).on('mouseleave',function () {
            $(this).css('display','none')
        });
        $('.box_right .news_more ul li i').on('click',function () {
            $(this).toggleClass('blue')
        });
		$('#keyword').bind('keyup', function(event) {
					if (event.keyCode == "13") {
						/* if($("#keyword").val() == ""){
							alert("搜索的内容不能为空");
							return;
						} */
						$('#searchBtn').click();
						
					}
				});
        //分页
        var GG = {
            "kk":function(mm){
                 alert(mm);
            }
        }

       
		loadData();
		//hotSearch();
    });
	
	//页码切换
	function changeData(mm){
		//alert(mm)
		if(mm!=pageNo){
			pageNo=mm;
			loadData();
		}
	};
	//加载列表数据
	function loadData(){
		keyword=$("#keyword").val();
		$('.menus_two_box').css('display','none');
		$.post("${portalPath}/view/viewThinker/list",{"category1":category1,"category2":category2,"category3":category3,
		"tags":tags,"pageNo":pageNo,"pageSize":pageSize,"name":keyword},function(data){
			console.log(data);
			joinHtml(data.list);
			$(".search_result_number").html(data.count);
			$("#page").initPage(data.count,data.pageNo,changeData);	
		});
	};
	function closeLable(index){
			var c1 = $('.txt').text();
			var c2 = $('.txt1').text();
			var c3 = $('.txt2').text();
			if(index==1){
				console.log(c1);
				$('.txt').empty();
				c1='';
				//c1='';
				//c2='';
			}else if(index==2){
				$('.txt1').empty();
				c2='';
				//c1='';
				//c2='';
			}else{
				//c1='';
				//c3='';
				c3='';
				$('.txt2').empty();
				console.log($('.txt2').text());
			}
			$('.menus_two_box').css('display','none');
			$.post("${portalPath}/view/viewThinker/list",{"category1":c1,"category2":c2,"category3":c3,
			"tags":tags,"pageNo":pageNo,"pageSize":pageSize,"name":keyword},function(data){
				console.log(data);
				joinHtml(data.list);
				$(".search_result_number").html(data.count);
				$("#page").initPage(data.count,data.pageNo,changeData);	
			});
	}
	//搜索
	function search(){
		pageNo=1;
		loadData();
	};
	//切换分类
	function changeCategory(cate1,cate2,cate3){
		category1=cate1;
		category2=cate2;
		category3=cate3;
		pageNo=1;
		loadData();
	}
	//拼接内容
	function joinHtml(data){
		var htmls='';
		$.each(data,function(index,item){
			// if(item.type=="API"){
			// 	htmls+=" <div class='news_box' onclick='inetent(1,\""+item.id+"\")' >";
			// }else if(item.type=="指标"){
			// 	htmls+=" <div class='news_box' onclick='inetent(2,\""+item.id+"\")' >";
			// }else if(item.type=="报表"){
			// 	htmls+=" <div class='news_box' onclick='inetent(3,\""+item.id+"\")' >";
			// }
			htmls+='<div class="box_left fl"><div class="box_left_img" style="background:url('+item.screenUrl+') 50% 50% no-repeat;background-size: contain;"></div></div>';//图片
			htmls+='<div class="box_right fl"><div class="news_title clearfix"><span class="fl news_mark">'+item.type+'</span>';
				if(item.type=="API"){
				//htmls+=" <div class='news_box' onclick='inetent(1,\""+item.id+"\")' >";
				htmls+='<b class="fl" onclick="inetent(1,\''+item.id+'\')"><a href="">'+item.name+'</a></b>';
			}else if(item.type=="指标"){
				//htmls+=" <div class='news_box' onclick='inetent(2,\""+item.id+"\")' >";
				htmls+='<b class="fl" onclick="inetent(2,\''+item.id+'\')"><a href="">'+item.name+'</a></b>';
			}else if(item.type=="报表"){
				//htmls+=" <div class='news_box' onclick='inetent(3,\""+item.id+"\")' >";
				htmls+='<b class="fl" onclick="inetent(3,\''+item.id+'\')"><a href="">'+item.name+'</a></b>';
			}
			//htmls+='<b class="fl"><a href="">'+item.name+'</a></b>';
			htmls+='<span><span class="news_time fr">'+item.createDate+'</span><i class="iconfont icon-shijian fr"></i></span></div>';
			if(item.descs!=''&&item.descs.length>80){
				htmls+='<div class="news_contents">'+item.descs.substr(0,80)+'...</div>';
			}else{
				htmls+='<div class="news_contents">'+item.descs+'</div>';
			};
			htmls+='<div class="news_more">';
			htmls+='<ul><li><span><i class="iconfont icon-find"></i><span>'+(item.viewNum==undefined?0:item.viewNum)+'</span></span></li>';
			htmls+='<li><span><i class="iconfont icon-dianzan"></i><span>'+(item.zanNum==undefined?0:item.zanNum)+'</span></span></li>';
			htmls+='<li><span><i class="iconfont icon-fenxiang2"></i><span>'+(item.shareNum==undefined?0:item.shareNum)+'</span></span></li>';
			htmls+='</ul></div></div></div>';
		});
		$("#content").html(htmls);  
			
	};
	//跳转明细
	function inetent(type,id){
		var url="";
		if(type==1){
			url="${portalPath}/thinker/api?id="+id;
		}else if(type==2){
			url="${portalPath}/thinker/indexDetail?id="+id;
		}else if(type==3){
			url="${portalPath}/thinker/reports?id="+id;
		};
		window.open(url);
	}
	//热搜
	function hotSearch(){
		
		$.post("${portalPath}/hot/search/list",{"pageNo":1,"pageSize":7},function(data){
			var htmls='<ul>';
			  
			$.each(data.list,function(index,item){
				htmls+='<li onclick="clickHot(this)">'+item.keyword+'</li>';
			});
			htmls+='</ul>';
			$(".hot_words").html(htmls);
		});
	};
	function clickHot(obj){
		if($(obj).hasClass("active")){
			$("#keyword").val('');
		}else{
			$("#keyword").val($(obj).html());
		}
		
		$(obj).toggleClass("active").siblings().removeClass("active");
		search();
	}
	// $(".menus_three ul").find("li:gt(6)").hide();
	$(".menus_three").find("li:gt(3)").hide();
	//$(".menus_box").children("li")
	$(".menus_two").children().gt(3).hide();
</script>
</html>

