

var DetailApi = {
	init:function(){
		this.tabChanges();
		this.tabFixed();
		this.iconBlue();
	},
	tabChanges:function(){
		$("ul li").click(function(){
			$(this).addClass("liActive").siblings().removeClass("liActive");
			
			if($(this).attr("flag") == 1){
				$(".docCon").css("display","block");
				$(".defaultCodeCon").css("display","none");
				$(".demoCodeCon").css("display","none");
				$(".callUsCon").css("display","none");
			}else if($(this).attr("flag") == 2) {
				$(".docCon").css("display","none");
				$(".demoCodeCon").css("display","none");
				$(".callUsCon").css("display","none");
				$(".defaultCodeCon").css("display","block");
			}else if($(this).attr("flag") == 3) {
				$(".docCon").css("display","none");
				$(".defaultCodeCon").css("display","none");
				$(".callUsCon").css("display","none");
				$(".demoCodeCon").css("display","block");
			}else {
				$(".docCon").css("display","none");
				$(".defaultCodeCon").css("display","none");
				$(".callUsCon").css("display","block");
				$(".demoCodeCon").css("display","none");
			}
		})
	},
	iconBlue:function(){
		$(".interact span").click(function(){
			$(this).find("i").toggleClass("iconblue");
		})
	},
	tabFixed:function(){
			console.log($("ul").scrollTop());
		
		$(window).scroll(function(){
			console.log($(document).scrollTop());
			if($(document).scrollTop() > 286) {
				$("ul").addClass("ulfixed");
			}else if($("ul").hasClass("ulfixed")){
				$("ul").removeClass("ulfixed");
				
			}else{
				return;
			}
		})
	}
}


DetailApi.init();
