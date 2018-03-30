<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html lang="zn-CN">
<head>
    <meta charset="UTF-8"  name="viewport" content="maximum-scale=1.0,minimum-scale=1.0,user-scalable=0,width=device-width,initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>举报</title>
    <link rel="stylesheet" href="${ctxStatic}/${portalPage}/wx/asserts/css/bootstrap.min-4.0.0.css">
    <link rel="stylesheet" href="${ctxStatic}/${portalPage}/wx/asserts/css/animate.css">
	<link rel="stylesheet" href="${ctxStatic}/${portalPage}/wx/asserts/css/style.css">
	<link rel="stylesheet" type="text/css" href="https://res.wx.qq.com/open/libs/weui/0.4.1/weui.css">
    <style>
        body {
            background-color: #eee;
        }

        .container {
            margin-bottom: .6rem;
            padding-top: 1px;
            padding-bottom: 1px;
            background-color: #fff;
            /*box-shadow: 0.1rem 0.1rem 0.1rem #0002;*/
        }

        .form-group {
            position: relative;
            margin-bottom: auto;
            padding-top: 1px;
        }
        .form-group.titleBox {
            margin-bottom: .1rem;
        }

        .form-group > label:not(.error) {
            position: absolute;
            bottom: 0;
            margin-top: .5rem;
            margin-bottom: .475rem;
            width: 5em;
            /*#开头的4位带透明度的颜色值在手机上不好用*/
            color: rgba(0,0,0,.375);
            transition: .4s ease-in-out all;
        }
        .form-group.titleBox > label:not(.error),
        .form-group.noteBox > label:not(.error) {
            top: .5rem;
            width: 100%;
            color: #000;
        }
        .noteBox>label:not(.error){
            margin-top: 0;
        }
        label.error{
            display: block;
            width: 100%;
            position: absolute;
            font-size: .75rem;
            text-align: right;
            color: #ff4d4f;
            top: 1.25rem;
        }
        .form-group.titleBox > label.error,
        .form-group.noteBox > label.error {
            top: 1rem;
        }
        #description-error{
            top: auto;
            bottom: 0;
        }
        .form-group > label[for=image] {
            position: relative;
            margin-top: 1rem;
            margin-bottom: 1.875rem;
            height: 4rem;

        }
        .iconBox{
            display: block;
            position: relative;
            width: 5rem;
            height: 5rem;
            padding: 1rem;
            font-size: .875rem;
            background: url(img/photo.png) 48% 30% no-repeat;
            background-size: 60% 50%;
            border: .1rem dashed rgba(0,0,0,.2);
        }
        .iconBox>.photoNumber{
            position: absolute;
            bottom: .125rem;
            left: 1.75rem;
        }
        /*新css伪类 “:focus-within”，当本身或后代获得焦点时触发，因支持不够宽泛而弃用*/
        .form-group.focus-within > label:not(.error):not([for=title]):not([for=note]) {
            top: 0;
            color: #000;
        }
        .form-group.focus-within > label.error {
            top: .625rem;
        }
        .form-group.focus-within > input:not(#description):not(#title){
            margin-top: 1.5rem;
            margin-bottom: 0;
        }
        #description{
            margin-top: 0;
        }
        #note{
            margin-top: 1.875rem;
        }

        #title{
            margin-top: .625rem;
            margin-bottom: 0;
            text-indent: 4em;
        }
        .form-group.titleBox > input {
            margin-top: .5rem;
            border-bottom: .1rem solid rgba(0,0,0,.125);
        }
        .titleBox > input {
        }

        .form-control,
        .form-control:focus {
            margin-top: 1rem;
            margin-bottom: .625rem;
            padding-left: 0;
            font-size: .875rem;
            border-radius: 0;
            border: none;
            box-shadow: none;
        }

        input#image {
            width: 6rem;
        }

        #toReport ::-webkit-input-placeholder {
            font-size: .875rem;
            color: #aaa;
        }
		[type=button]{
			width: 33%;
			margin: 1rem .5rem 2rem;
			font-size: 1rem;
		}

    </style>
    	<!-- <script type="text/javascript">
	if(/Android (\d+\.\d+)/.test(navigator.userAgent)){
		var version = parseFloat(RegExp.$1);
		if(version>2.3){
			var phoneScale = parseInt(window.screen.width)/640;
			document.write('<meta name="viewport" content="width=640, minimum-scale = '+ phoneScale +', maximum-scale = '+ phoneScale +', target-densitydpi=device-dpi">');
		}else{
			document.write('<meta name="viewport" content="width=640, target-densitydpi=device-dpi">');
		}
	}else{
		document.write('<meta name="viewport" content="width=640, user-scalable=no, target-densitydpi=device-dpi">');
	}
	</script> -->
</head>
<body id="toReport">
<form class="toReport" enctype="multipart/form-data" action="${portalPath}/wx/saveReport">
	<input type="hidden" id="userId" value="${userId}" />
	<input type="hidden" id="cmsId" value="${cmsComplaint.id}" />
    <div class="container">
        <div class="form-group titleBox">
            <label class="" for="title">标题：</label>
            <input class="form-control" name="title" id="title" value="${cmsComplaint.title }" aria-describedby="titleHelp" placeholder="">
        </div>
        <div class="form-group">
            <label class="sr-only" for="description">举报内容：</label>
            <textarea class="form-control" name="description"  value="${cmsComplaint.content }" id="description" placeholder="请填写描述内容，最少输入10个字，最多输入100个字" rows="5"></textarea>
        </div>
    </div>
	 <div class="container" id="statusdiv" style="display:none">
        <div class="form-group titleBox">
            <label class="" for="status">状态：</label>
            <input class="form-control" name="status" id="status"  aria-describedby="titleHelp" placeholder="">
        </div>
    </div>
    <div class="container">
        <div class="form-group imageBox">
             <div class="form-group titleBox album-old">
            	<div class="upload-btn btn-old" style="display:block;"><input type="file" name="files" id="files"></div>
				<div class="upload-img " ></div>	
        	</div>
        </div>
    </div>
    <div class="container">
        <div class="form-group imageBox">
            <label for="target">举报对象：</label>
            <input class="form-control" name="target" value="${cmsComplaint.companyName }" id="target" placeholder="">
        </div>
    </div>
    <div class="container">
        <div class="form-group imageBox">
            <label for="address">发现地址：</label>
            <input class="form-control" name="address" value="${cmsComplaint.companyAddress }"  id="address" placeholder="">
        </div>
    </div>
    <div class="container">
        <div class="form-group dateBox focus-within">
            <label for="date">发现时间：</label>
            <input class="form-control" name="date" id="date"  type="date" value="${cmsComplaint.findDateStr }" placeholder="">
        </div>
    </div>
    <div class="form-group text-center" id="btn1" style="display:none">
		<button  id="submitFormBtn" type="button" class="btn btn-success form-control center">提 交</button>
    </div>
	<div class="form-group text-center" id="btn2" style="display:none">
		<button type="button"  id="submitFormBtn"  class="btn btn-success center">提 交</button>
		<button type="button"  id="cancleFormBtn"  class="btn btn-danger center">撤 销</button>
	</div>
</form>

<script src="${ctxStatic}/${portalPage}/wx/asserts/js/jquery-2.2.4.js"></script>
<script src="${ctxStatic}/${portalPage}/wx/asserts/js/jquery.validate-1.15.1.js"></script>

	<script type="text/javascript" src="${ctxStatic}/${portalPage}/wx/asserts/js/zepto.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/${portalPage}/wx/asserts/js/iscroll-zoom.js"></script>
	<script type="text/javascript" src="${ctxStatic}/${portalPage}/wx/asserts/js/script.js"></script>
<script>
	
    $(function () {		
        judgeForBidPage();
    });
	function judgeForBidPage(){
		var useragent = navigator.userAgent;
		if (useragent.match(/MicroMessenger/i) != 'MicroMessenger') {
			// 这里警告框会阻塞当前页面继续加载
			// 以下代码是用javascript强行关闭当前页面
			//var opened = window.open('about:blank', '_self');
			/*opened.opener = null;
			opened.close();*/
			var htmls='<div class="weui_msg"><div class="weui_icon_area"><i class="weui_icon_info weui_icon_msg"></i></div><div class="weui_text_area"><h4 class="weui_msg_title">请在微信客户端打开链接</h4></div></div>';			
			$("body").html(htmls);
		} else{
			window.alert = function(name){
				var iframe = document.createElement("IFRAME");
				iframe.style.display="none";
				iframe.setAttribute("src", 'data:text/plain,');
				document.documentElement.appendChild(iframe);
				window.frames[0].window.alert(name);
				iframe.parentNode.removeChild(iframe);
			};
			initPage();
		};
	};
	function initPage(){
		$(":input:not(#date)").focus(function () {
            $(this).parents(".form-group").addClass("focus-within");
        }).blur(function () {
            if(!$(this).val()){
            $(this).parents(".form-group").removeClass("focus-within");
            }
        });
		initForm();
		$("#cancleFormBtn").one("click",cancleReport);
        //验证表单
        var $form = $("#toReport").find("form");
        $form.find(":input").keyup(function () {
            validateForm()
        });
        $form.find("#submitFormBtn").click(function () {
            validateForm();
            if ($form.valid()) {
                 $.post("${portalPath}/wx/saveReport", {
					 id: $("#cmsId").val(),
                	userId:$("#userId").val(),
                    title: $("#title").val(),
                    description: $("#description").val(),
                    images: $(".upload-img img").attr("src"),
                    target: $("#target").val(),
                    address: $("#address").val(),
                    date: $("#date").val(),
                    source:"wxpub"
                }, function (data, textStatus) {
                    console.log(data);
                    if (data.status === "success") {
                       window.location.href="${portalPath}/cms/cmsComplaint/list?userId="+$("#userId").val();
                    } else {
                        console.log(data.msg)
                    }
                }) 
            }
        });
	};
	function cancleReport(){
			if(confirm("确定撤销举报吗？")){
				 $.post("${portalPath}/wx/cancleReport", {
					 id: $("#cmsId").val()
                }, function (data, textStatus) {
                    if (data.status === "success") {
                       window.location.href="${portalPath}/cms/cmsComplaint/list?userId="+$("#userId").val();
                    } else {
						$("#cancleFormBtn").one("click",cancleReport);
                        console.log(data.msg)
                    }
                }) ;
			}else{
				$("#cancleFormBtn").one("click",cancleReport);
			}
			
		}
		function initForm(){
			if($("#target").val()!=""){
				$("#target").parents(".form-group").addClass("focus-within");
			};
			if($("#address").val()!=""){
				$("#address").parents(".form-group").addClass("focus-within");
			};
			if("${cmsComplaint.image}"!=""){
				//生成图片
				var img='<img src="${cmsComplaint.image}" class="mw" style="width: 100%; height: 100%; transform-origin: 0px 0px 0px; transition-timing-function: cubic-bezier(0.1, 0.57, 0.1, 1); transition-duration: 0ms; transform: translate(0px, 0px) scale(1) translateZ(0px); opacity: 1;">';
				$(".upload-img").html(img);
			};
			$("#description").val("${cmsComplaint.content}");
			if("${cmsComplaint.id}"==null){
				//第一次填报
				$("#btn1").css("display","block");
				$("#btn2").remove();
				$("#statusdiv").remove();
			}else{
				$("#status").parents(".form-group").addClass("focus-within");
				$("#statusdiv").css("display","block");
				$("#status").attr('disabled','disabled');
				if("${cmsComplaint.status}"=="0"){
					//可修改 可撤销
					$("#btn1").remove();
					$("#btn2").css("display","block");
					$("#status").val("未受理");
				}else{
					//不可编辑
					$("#btn1").remove();
					$("#btn2").remove();
					$("#title").attr('disabled','disabled');
					$("#files").attr('disabled','disabled');
					$("#description").attr('disabled','disabled');
					$("#target").attr('disabled','disabled');
					$("#address").attr('disabled','disabled');
					$("#date").attr('disabled','disabled');
					if("${cmsComplaint.status}"=="1"){
						$("#status").val("已受理");
					}else if("${cmsComplaint.status}"=="2"){
						$("#status").val("驳回");
					}else if("${cmsComplaint.status}"=="3"){
						$("#status").val("结束");
					}else if("${cmsComplaint.status}"=="4"){
						$("#status").val("已撤销");
					}
				}
				
			};
		}
        function validateForm() {
            $form.validate({
                debug: true,
                rules: {
                    title: {
                        required: true,
                        minlength: 2
                    },
                    description: {
                        required: true,
                        minlength: 10,
                        maxlength: 100
                    },
                    target: {
                        required: true
                    },
                    address: {
                        required: true
                    },
                    date: {
                        required: true
                    },
                },
                messages: {
                    title: {
                        required: "请输入标题",
                        minlength: "最少输入2个字"
                    },
                    description: {
                        required: "请填写描述的内容",
                        minlength: "最少输入10个字",
                        maxlength: "最多输入100个字"
                    },
                    target: {
                        required: "举报对象不能为空"
                    },
                    address: {
                        required: "举报地址不能为空"
                    },
                    date: {
                        required: "举报时间不能为空"
                    },
                }
            })
        };
</script>
</body>
</html>
