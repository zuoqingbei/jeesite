<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<meta charset="UTF-8"  name="viewport" content="maximum-scale=1.0,minimum-scale=1.0,user-scalable=0,width=device-width,initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>注册</title>
    <link rel="stylesheet" href="${ctxStatic}/${portalPage}/wx/asserts/css/bootstrap.min-4.0.0.css">
	<style>
        form {
            margin-top: 6rem;
        }

        label.error {
            display: block;
            margin-top: .3rem;
            margin-bottom: .6rem;
            text-indent: .75rem;
            font-size: .8125rem;
            color: #ff9261;
        }

        .form-control,
        .form-control:focus {
            border-radius: 0;
            border-top: none;
            border-left: none;
            border-right: none;
            box-shadow: none;
        }

        .validCodeBox {
            position: relative;
        }

        .validCodeBox .btn {
            position: absolute;
            right: 0;
            top: 0;
        }

        /*bootstrap4里面没有此类*/
        .btn-default {
            color: #333;
            background-color: #fff;
            border-color: #ccc;
        }
        #reg ::-webkit-input-placeholder {
            font-size: .9375rem;
            color: #aaa;
        }

    </style>
</head>
<body id="reg">
<div class="container">
    <form id="regForm" action="${portalPath}/wx/register" >
   		<input type="hidden" id="accountId" value="${accountId}" />
   		<input type="hidden" id="to" value="${to}" />
        <div class="form-group">
            <label class="sr-only" for="telephone">手机号码</label>
            <input class="form-control" id="telephone" name="telephone" value="${telephone }" placeholder="请输入手机号码">
        </div>
        <div class="form-group validCodeBox">
            <label class="sr-only" for="validCode">验证码</label>
            <input class="form-control" id="validCode" name="validCode" placeholder="请输入验证码">
            <button type="button" class="btn btn-default btn-sm pull-right" id="sendBtn">获取验证码</button>
        </div>
        <div class="form-group">
            <span class="form-control btn-success" id="regFormBtn" style="text-align: center;" type="text" value="注 册" >注 册</span>
        </div>
    </form>
</div>

<script src="${ctxStatic}/${portalPage}/wx/asserts/js/jquery-2.2.4.js"></script>
<script src="${ctxStatic}/${portalPage}/wx/asserts/js/jquery.validate-1.15.1.js"></script>
<script>
	var countdown=120; 
    var timer;
	//验证表单
    var validResult = false;
	var $regForm = $("#regForm");
    $(function () {
       initPage();
    });
	function initPage(){
		 $regForm.find(":input").keyup(function () {
            validateForm();
        });
        $("#regFormBtn").one("click",submitMethod);
	};
	function submitMethod(){
			 validateForm();
	            if ($regForm.valid()) {
	            	//先验证验证码正确性
	            	 $.post("${portalPath}/account/accountMobileCode/checkIndentifyCode",{"mobile":$("#telephone").val(),"codes":$("#validCode").val()},function(data){
						console.log(data);
						data=eval('('+data+')');
						if(data.status=="success"){
							//去注册
						 $.post("${portalPath}/wx/register", {
		                    name: $("#telephone").val(),
		                    telephone: $("#telephone").val(),
		                    validCode: $("#validCode").val(),
		                    accountId:$("#accountId").val(),
		                    to:$("#to").val()
		                }, function (data, textStatus) {
							data=eval('('+data+')');
		                	console.log(data)
		                	if(data.status=="success"){
		                		window.location.href=data.to+"?userId="+data.userId;
		                	}else{
		                		$("#regFormBtn").one("click",submitMethod);
		                	}
		                })  
						}else{
							$("#regFormBtn").one("click",submitMethod);
						}
					}); 
					
	              
	            }else{
	            	$("#regFormBtn").one("click",submitMethod);
	            }
		};
        function validateForm() {
            $regForm.validate({
                debug: true,
                rules: {
                    telephone: {
                        required: true,
                        number: true,
                        minlength: 8,
                        maxlength: 11
                    },
                    validCode: {
                        required: true
                    }
                },
                messages: {
                    telephone: {
                        required: "请输入手机号码",
                        // number: "",
                        minlength: "电话号码不能少于8位",
                        maxlength: "电话号码不能多于11位"
                    },
                    validCode: {
                        required: "请输入手机验证码"
                    }
                }
            })
        };
        //发送手机验证码
        
        $("#sendBtn").one("click",sendMobileCode);
    	function sendMobileCode(){
    		 if ($regForm.validate().element($("#telephone"))) {
				//发送验证码 uasge短信用途 0-注册 1-找回密码 2-重置密码 3-设定支付密码 4-提现 
				$.post("${portalPath}/account/accountMobileCode/sendCheckCode",{"mobile":$("#telephone").val(),"uasge":"0"},function(data){
					data=eval('('+data+')');
					//console.log(data.status)
					if(data.status=="success"){
					//alert(data.status=="success")
						//发送成功
						 timer=window.setInterval(settime,1000)
					}else{
						alert(data.msg);
						 $("#sendBtn").one("click",sendMobileCode);
					}
				});
    		 }else{
    			 $("#sendBtn").one("click",sendMobileCode);
    		 }
			 
    	};
    	
    	function settime() { 
    		var obj=$("#sendBtn").get(0);
    		console.log(countdown)
    	    if (countdown == 0) { 
    	        obj.removeAttribute("disabled");    
    	        obj.innerHTML="获取验证码"; 
    	        countdown = 120; 
    	        window.clearInterval(timer);
    	        $("#sendBtn").one("click",sendMobileCode);
    	        return;
    	    } else { 
    	        obj.setAttribute("disabled", true); 
    	        obj.innerHTML="重新发送(" + countdown + ")"; 
    	        countdown--; 
    	    } 
    	};
</script>
<script>
</script>
</body>
</html>