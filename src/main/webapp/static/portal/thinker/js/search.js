/**
 * Created by Administrator on 2018/4/13.
 */

var Search = {
	init:function(){
		this.MenuliClick();
		this.labelClose();
		this.inputIsNull();
	},
	MenuliClick:function(){
		
		//点击一级菜单
		$(".menus_box li").click(function(){
			
			
			
			
			$(".label0").css("display","block");
			var txt = $(this).text();
			$(".txt").text(txt);
			
			if($(".label1").css("display") == "block"){
				aler(1)
				$(".label1").css("display","none");
			}
			if($(".label2").css("display") == "block"){
				$(".label2").css("display","none");
			}

			
		});
				//点击二级菜单
		$(".menus_two>li>span").click(function(){
			$(".label0").css("display","block");
			$(".label1").css("display","block");
			var txt = $(this).text();
			//$(this).clone().children().remove().end().text()
			console.log(txt);
			$(".txt1").text(txt);
			
			//获取点击的二级菜单的id
			var id = $(this).parents(".menus_two_box").attr("id").replace("menus_two_box_","");
			//console.log(id);
			//匹配一级菜单
			var lis = $(".menus_box li");
			for(var i=0;i<lis.length;i++){
				var data = $(lis[i]).attr("data");
				if(data == id) {
					var txt = $(lis[i]).text();
					$(".txt").text(txt);
				}
			}
		});
		if($(".label2").css("display") == "block"){
			$(".label2").css("display","none");
		}
		//点击三级菜单
		
		$(".menus_three li").click(function(){
			var txt = $(this).text();
			$(".label0").css("display","block");
			$(".label1").css("display","block");
			$(".label2").css("display","block");
			
			$(".txt2").text(txt);
			//匹配二级菜单
			var zi = $(this).parents("li").find("span").text();
			var lis = $(".menus_two").find("li");
			for(var i=0;i<lis.length;i++){
				var data = $(lis[i]).find("span").text();
				console.log(data);
				if(zi == data) {
					$(".txt1").text(data);
				}
			}
			//获取点击的二级菜单的id
			var id = $(this).parents(".menus_two_box").attr("id").replace("menus_two_box_","");
			//console.log(id);
			//匹配一级菜单
			var lis = $(".menus_box li");
			for(var i=0;i<lis.length;i++){
				var data = $(lis[i]).attr("data");
				if(data == id) {
					var txt = $(lis[i]).text();
					$(".txt").text(txt);
				}
			}
		})
	},
	labelClose:function(){
		$(".close_label").click(function(){
			$(this).parent().css("display","none");
		})
	},
	inputIsNull:function(){
		$("#searchBtn").click(function(){
			if($("#keyword").val() == ""){
				alert("搜索的内容不能为空");
			}
		})
		$("#keyword").keyDown(function(){
			alert("搜索的内容不能为空");
		})
	}
}


Search.init();