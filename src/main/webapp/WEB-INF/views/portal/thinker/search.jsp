<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8" name="viewport" content="width=device-width,initial-scale=1.0,user-scalable=no">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>1169数据立方体</title>
    <link rel="stylesheet" href="${ctxStatic}/${portalPage}/thinker/asserts/css/bootstrap-4.0.0.css">
    <link rel="stylesheet" href="${ctxStatic}/${portalPage}/thinker/asserts/css/page.css">
    <link rel="stylesheet" href="${ctxStatic}/${portalPage}/thinker/css/style.css">
    <link rel="stylesheet" href="${ctxStatic}/${portalPage}/thinker/css/reset.css">
    <link rel="stylesheet" href="${ctxStatic}/${portalPage}/thinker/asserts/iconfont/iconfont.css" />
    <link rel="stylesheet" href="${ctxStatic}/${portalPage}/thinker/css/Search.css">

</head>

<body>
<header></header>
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
                       <div class="serch_input"><input type="text" placeholder="请输入热搜词"></div>
                       <div class="serch_button"><button>搜索</button></div>
                   <div class="serch_head_checkbox">
                       <span class="fl">热搜：</span>
                       <!--<label class="demo&#45;&#45;label"><input class="demo&#45;&#45;radio" type="checkbox" name="demo-checkbox1">
                           <span class="demo&#45;&#45;checkbox demo&#45;&#45;radioInput"></span>指标
                       </label>
                       <label class="demo&#45;&#45;label">
                           <input class="demo&#45;&#45;radio" type="checkbox" name="demo-checkbox2">
                           <span class="demo&#45;&#45;checkbox demo&#45;&#45;radioInput"></span>财务运营
                       </label>
                       <label class="demo&#45;&#45;label">
                           <input class="demo&#45;&#45;radio" type="checkbox" name="demo-checkbox3">
                           <span class="demo&#45;&#45;checkbox demo&#45;&#45;radioInput"></span>接口
                       </label>-->
                       <div class="hot_words fl">
                           <span>指标</span><span>指标</span><span>指标</span>
                       </div>
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
                <span>当前位置：</span><span class="blue">搜索条件</span>
            </div>
            <!--内容部分-->
            <div class="search_content_box clearfix">
                <!--左侧的一级菜单-->
                <div class="search_content_box_left fl">
                    <ul class="menus_head">全部分类<i class="iconfont icon-plus-select-down"></i></ul>
                    <ul class="menus_box">
                        <li>报表1<span class="fr"><i class="iconfont icon-youzhankai"></i></span></li>
                        <li>报表2<span class="fr"><i class="iconfont icon-youzhankai"></i></span></li>
                        <li>报表3<span class="fr"><i class="iconfont icon-youzhankai"></i></span></li>
                        <li>报表4<span class="fr"><i class="iconfont icon-youzhankai"></i></span></li>
                        <li>报表5<span class="fr"><i class="iconfont icon-youzhankai"></i></span></li>
                        <li>报表6<span class="fr"><i class="iconfont icon-youzhankai"></i></span></li>
                    </ul>

                </div>
                <!--触发后的二三级菜单-->
                <div class="menus_two_box">
                    <ul class="menus_two">
                        <li><span class="fl">接口</span>
                            <ul class="menus_three fl">
                                <li>111</li>
                                <li>222</li>
                                <li>333</li>
                                <li>111</li>
                                <li>222</li>
                                <li>333</li>
                                <li>111</li>
                                <li>222</li>
                                <li>333</li>
                            </ul>
                        </li>
                        <li><span class="fl">接口</span>
                            <ul class="menus_three fl">
                                <li>111</li>
                                <li>222</li>
                                <li>333</li>
                                <li>111</li>
                                <li>222</li>
                                <li>333</li>
                                <li>111</li>
                                <li>222</li>
                                <li>333</li>
                            </ul>
                        </li>
                        <li><span class="fl">接口</span>
                            <ul class="menus_three fl">
                                <li>111</li>
                                <li>222</li>
                                <li>333</li>
                                <li>111</li>
                                <li>222</li>
                                <li>333</li>
                                <li>111</li>
                                <li>222</li>
                                <li>333</li>
                            </ul>
                        </li>
                        <li><span class="fl">接口</span>
                            <ul class="menus_three fl">
                                <li>111</li>
                                <li>222</li>
                                <li>333</li>
                                <li>111</li>
                                <li>222</li>
                                <li>333</li>
                                <li>111</li>
                                <li>222</li>
                                <li>333</li>
                            </ul>
                        </li>
                    </ul>
                </div>
                <!--信息列表-->
                <div class="search_content_box_right fl clearfix">
                    <div class="serch_content_main">
                        <div class="news_box">
                            <div class="box_left fl">
                                <div class="box_left_img"></div>
                            </div>
                            <div class="box_right fl">
                                <div class="news_title clearfix">
                                    <span class="fl news_mark">API</span>
                                    <b class="fl"><a href="">国内收入</a></b>
                                    <span>
                                        <span class="news_time fr">2018-04-14</span>
                                        <i class="iconfont icon-shijian fr"></i>
							        </span>
                                </div>
                                <div class="news_contents">
                                    如果我们对一个li的文章列表设置宽度，因文章标题过长文章标题而自动换行，
                                </div>
                                <div class="news_more">
                                    <ul>
                                        <li>
                                            <span>
                                                <i class="iconfont icon-find"></i>
                                                <span>13455</span>
							                </span>
                                        </li>
                                        <li>
                                          <span>
                                                <i class="iconfont icon-dianzan"></i>
                                                <span>13455</span>
							                </span>
                                        </li>
                                        <li>
                                           <span>
                                                <i class="iconfont icon-fenxiang2"></i>
                                                <span>13455</span>
							                </span>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                        <div class="news_box">
                            <div class="box_left fl">
                                <div class="box_left_img"></div>
                            </div>
                            <div class="box_right fl">
                                <div class="news_title clearfix">
                                    <span class="fl news_mark">API</span>
                                    <b class="fl"><a href="">国内收入</a></b>
                                    <span>
                                        <span class="news_time fr">2018-04-14</span>
                                        <i class="iconfont icon-shijian fr"></i>
							        </span>
                                </div>
                                <div class="news_contents">
                                    如果我们对一个li的文章列表设置宽度，因文章标题过长文章标题而自动换行，
                                </div>
                                <div class="news_more">
                                    <ul>
                                        <li>
                                            <span>
                                                <i class="iconfont icon-find"></i>
                                                <span>13455</span>
							                </span>
                                        </li>
                                        <li>
                                          <span>
                                                <i class="iconfont icon-dianzan"></i>
                                                <span>13455</span>
							                </span>
                                        </li>
                                        <li>
                                           <span>
                                                <i class="iconfont icon-fenxiang2"></i>
                                                <span>13455</span>
							                </span>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                        <div class="news_box">
                            <div class="box_left fl">
                                <div class="box_left_img"></div>
                            </div>
                            <div class="box_right fl">
                                <div class="news_title clearfix">
                                    <span class="fl news_mark">API</span>
                                    <b class="fl"><a href="">国内收入</a></b>
                                    <span>
                                        <span class="news_time fr">2018-04-14</span>
                                        <i class="iconfont icon-shijian fr"></i>
							        </span>
                                </div>
                                <div class="news_contents">
                                    如果我们对一个li的文章列表设置宽度，因文章标题过长文章标题而自动换行，
                                </div>
                                <div class="news_more">
                                    <ul>
                                        <li>
                                            <span>
                                                <i class="iconfont icon-find"></i>
                                                <span>13455</span>
							                </span>
                                        </li>
                                        <li>
                                          <span>
                                                <i class="iconfont icon-dianzan"></i>
                                                <span>13455</span>
							                </span>
                                        </li>
                                        <li>
                                           <span>
                                                <i class="iconfont icon-fenxiang2"></i>
                                                <span>13455</span>
							                </span>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                        <div class="news_box">
                            <div class="box_left fl">
                                <div class="box_left_img"></div>
                            </div>
                            <div class="box_right fl">
                                <div class="news_title clearfix">
                                    <span class="fl news_mark">API</span>
                                    <b class="fl"><a href="">国内收入</a></b>
                                    <span>
                                        <span class="news_time fr">2018-04-14</span>
                                        <i class="iconfont icon-shijian fr"></i>
							        </span>
                                </div>
                                <div class="news_contents">
                                    如果我们对一个li的文章列表设置宽度，因文章标题过长文章标题而自动换行，
                                </div>
                                <div class="news_more">
                                    <ul>
                                        <li>
                                            <span>
                                                <i class="iconfont icon-find"></i>
                                                <span>13455</span>
							                </span>
                                        </li>
                                        <li>
                                          <span>
                                                <i class="iconfont icon-dianzan"></i>
                                                <span>13455</span>
							                </span>
                                        </li>
                                        <li>
                                           <span>
                                                <i class="iconfont icon-fenxiang2"></i>
                                                <span>13455</span>
							                </span>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
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
    $(function () {
        var menus_lis=$('.menus_box li');
        menus_lis.on('mouseenter',function () {
            for(var i=0;i<=menus_lis.length;i++) {
                $(this).addClass('menus_li_active').siblings('li').removeClass('menus_li_active')
                $('.menus_two_box').css('display','block')
               $(this).find('i').css('color','white').parents('li').siblings('li').find('i').css('color','#333')
            }
        })
        menus_lis.on('mouseleave',function () {
                $('.menus_two_box').css('display','none')
        })
        $('.menus_two_box').on('mouseenter',function () {
            $(this).css('display','block')
        })
        $('.menus_two_box').on('mouseleave',function () {
            $(this).css('display','none')
        })
        $('.box_right .news_more ul li i').on('click',function () {
            $(this).toggleClass('blue')
        })
        //分页
        var GG = {
            "kk":function(mm){
                // alert(mm);
            }
        }

        $("#page").initPage(71,1,GG.kk);
    })
</script>
</html>
