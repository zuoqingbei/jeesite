<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html lang="zn-CN">
<head>
    <meta charset="UTF-8" name="viewport" content="width=device-width,initial-scale=1.0,user-scalable=no">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>举报</title>
    <link rel="stylesheet" href="${ctxStatic}/${portalPage}/wx/asserts/css/bootstrap.min-4.0.0.css">
    <link rel="stylesheet" href="${ctxStatic}/${portalPage}/wx/asserts/css/animate.css">
	<link rel="stylesheet" href="${ctxStatic}/${portalPage}/wx/asserts/css/style.css">
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

        [type=submit] {
            width: 60%;
            margin-bottom: 2rem;
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
    <div class="container">
        <div class="form-group titleBox">
            <label class="" for="title">标题：</label>
            <input class="form-control" name="title" id="title" aria-describedby="titleHelp" placeholder="">
        </div>
        <div class="form-group">
            <label class="sr-only" for="description">举报内容：</label>
            <textarea class="form-control" name="description" id="description" placeholder="请填写描述内容，最少输入10个字，最多输入100个字" rows="5"></textarea>
        </div>
    </div>
    <div class="container">
        <div class="form-group titleBox">
             <div class="form-group titleBox album-old">
            	<div class="upload-btn btn-old" style="display:block;"><input type="file" name="files" id=""></div>
				<div class="upload-img " ></div>	
        	</div>
        </div>
    </div>
    <div class="container">
        <div class="form-group titleBox">
            <label for="target">举报对象：</label>
            <input class="form-control" name="target" id="target" placeholder="">
        </div>
    </div>
    <div class="container">
        <div class="form-group titleBox">
            <label for="address">发现地址：</label>
            <input class="form-control" name="address" id="address" placeholder="">
        </div>
    </div>
    <div class="container">
        <div class="form-group dateBox focus-within">
            <label for="date">发现时间：</label>
            <input class="form-control" name="date" id="date" type="date" value="2018-01-01" placeholder="">
        </div>
    </div>
    <div class="form-group text-center">
        <span  id="submitFormBtn" class="btn btn-success form-control center">提 交</span>
    </div>
</form>

<script src="${ctxStatic}/${portalPage}/wx/asserts/js/jquery-2.2.4.js"></script>
<script src="${ctxStatic}/${portalPage}/wx/asserts/js/jquery.validate-1.15.1.js"></script>

	<script type="text/javascript" src="${ctxStatic}/${portalPage}/wx/asserts/js/zepto.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/${portalPage}/wx/asserts/js/iscroll-zoom.js"></script>
	<script type="text/javascript" src="${ctxStatic}/${portalPage}/wx/asserts/js/script.js"></script>
<script>
    $(function () {

        $(":input:not(#date)").focus(function () {
            $(this).parents(".form-group").addClass("focus-within")
        }).blur(function () {
            if(!$(this).val()){
            $(this).parents(".form-group").removeClass("focus-within")
            }
        });
        //验证表单
        var $form = $("#toReport").find("form");
        $form.find(":input").keyup(function () {
            validateForm()
        });
        $form.find("#submitFormBtn").click(function () {
            validateForm();

            if ($form.valid()) {
            	$form.submit();
                 $.post("${portalPath}/wx/saveReport", {
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
                        alert("提交成功！")
                    } else {
                        alert(data.msg)
                    }
                }) 
            }
        });
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
        }

    })
</script>
</body>
</html>
