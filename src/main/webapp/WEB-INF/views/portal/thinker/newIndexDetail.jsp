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
<header class="navbar navbar-expand flex-column flex-md-row bd-navbar">
    <a class="navbar-brand mr-0 mr-md-2" href="/portal/thinker/home">
        <div class="logoBg"></div>
        <h1 class="icon-pre">数据超市</h1>
    </a>
    <ul class="navbar-nav flex-row ml-md-auto d-none d-md-flex">
        <li class="nav-item">
            <a class="nav-link py-0 px-2 my-2 mx-2" href="#" rel="noopener" aria-label="reg">注册</a>
        </li>
        <li class="split py-1 my-1">|</li>
        <li class="nav-item">
            <a class="nav-link py-0 px-2 my-2 mx-2" href="#" rel="noopener" aria-label="login">登录</a>
        </li>
    </ul>
</header>

<div class="container">
    <section class="row">
        <div class="col-">
            <nav aria-label="breadcrumb">
                <ol class="breadcrumb">
                    <li class="">当前位置：</li>
                    <li class="breadcrumb-item"><a href="#">搜索页面</a></li>
                    <li class="breadcrumb-item active" aria-current="page">指标</li>
                </ol>
            </nav>
        </div>
    </section>
    <section class="row main">
        <div class="handle">
            <ul>
                <li class="favorite">
                    <i class="iconfont icon-shoucang"></i>
                    <span>收藏</span>
                </li>
                <li class="split">|</li>
                <li class="good">
                    <i class="iconfont icon-dianzan"></i>
                    <span>点赞</span>
                </li>
                <li class="split">|</li>
                <li class="share">
                    <i class="iconfont icon-fenxiang"></i>
                    <span>分享</span>
                </li>
            </ul>

        </div>

        <div class="col-12 list">
            <ul>
                <li class="">
                    <h4>平台(乐家，物流等)</h4>
                    <p>物流</p>
                </li>
                <li class="">
                    <h4>指标名称</h4>
                    <p>物流平台收入</p>
                </li>
                <li class="">
                    <h4>定义</h4>
                    <p>物流总体收入</p>
                </li>
            
                <li class="">
                    <h4>指标编号</h4>
                    <p>wl_0010001_0000_b1
                    </p>
                </li>
                <li class="">
                    <h4>指标类型</h4>
                    <p>累计值
                    </p>
                </li>
                <li class="">
                    <h4>指标描述</h4>
                    <p>开发1169物流大屏需要的指标</p>
                </li>
                <li class="">
                    <h4>指标维度</h4>
                    <p>时间维度：日、月、年；
                        组织维度：小微
                    </p>
                </li>
              	 <li class="">
                    <h4>分类</h4>
                    <p>大屏</p>
                </li>
                
                <li class="">
                    <h4>实际单位</h4>
                    <p>元</p>
                </li>
                
                <li class="">
                    <h4>计算方式</h4>
                    <p>来自风控系统，合计各日日顺物流下的各小微收入
                    </p>
                </li>
            </ul>
        </div>
    </section>
    <section class="row main">
        <div class="col-12 list">
            <p class="techMore"><a class="">技术详情 <span>▼</span></a></p>
            <ul style="display: none">
                <li class="">
                    <h4>显示表</h4>
                    <p class="displayTable">
                    	<!--<a target="_blank" href="javascript:void(0)" >dw_wl.w_wls_kpi_01_d_v</a>-->
                    	<a href="${portalPath}/thinker/yingXiangFenXi" target="_blank">dw_wl.w_wls_kpi_01_d_v</a></p>
	                    <div class="yingXiangFenXi" style="display: none">
	                    	<img style="width:100%;" src="/static/images/yingXiangFenXi.png" alt="">
	                    </div>

                </li>
                <li class="">
                    <h4>使用字段</h4>
                    <p> xv_code
                        <br>data_dt
                        <br>xv_name
                        <br>sum_month
                        <br>month_target
                        <br>total_month_target
                        <br>month_complete
                        <br>sum_year
                        <br>year_target
                        <br>year_complete
                        <br>sum_month_last_year_period_value
                        <br>sum_year_last_year_period_value
                        <br>month_basis
                        <br>year_basis
                    </p>
                </li>
                <li class="">
                    <h4>取数源</h4>
                    <p>RPT_XW_SHOURU<br>SYS_DEPARTMENT</p>
                </li>
                <li class="">
                    <h4>加工涉及表</h4>
                    <p> stg_wl.s_fin_rpt_xw_shouru
                        <br>stg_wl.s_fin_sys_department
                        <br>dl_wl.l_fin_rpt_xw_shouru
                        
                    </p>
                </li>
                <li class="">
                    <h4>workflow工作流</h4>
                    <p>FULL_RPT_XW_SHOURU&SYS_DEPARTMENT
                    </p>
                </li>
                <li class="">
                    <h4>coordinator调度
                    </h4>
                    <p>FULL_RPT_XW_SHOURU&SYS_DEPARTMENT
                    </p>
                </li>
                <li class="">
                    <h4>取数逻辑</h4>
                    <p>取自风控</p>
                </li>
                <li class="">
                    <h4>取数时间</h4>
                    <p>每天10点</p>
                </li>
                <li class="">
                    <h4>取数频次</h4>
                    <p>一次/天</p>
                </li>
                <li class="">
                    <h4>取数所需时长</h4>
                    <p>20分钟左右</p>
                </li>
                <li class="">
                    <h4>取数方式
                        （增量/全量)</h4>
                    <p>全量</p>
                </li>
                <li class="">
                    <h4>业务接口人</h4>
                    <p>谢雨婷</p>
                </li>
                <li class="">
                    <h4>技术接口人</h4>
                    <p>林宗晓</p>
                </li>
                <li class="">
                    <h4>数据状态
                        （系统/手工）</h4>
                    <p>系统</p>
                </li>
            </ul>
        </div>
    </section>
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
        $(".displayTable>a").click(function(){
           /*  console.log($(this).next()[0])
        	$(this).parent().next().toggle(); */
        })
    })

</script>
</body>
</html>