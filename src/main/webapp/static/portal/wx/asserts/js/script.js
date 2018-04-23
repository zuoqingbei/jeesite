$(function() {
	var _w = 456;
	var _h = 344;
	var _old = {};
	var _new = {};
	var _txt = $(".textarea textarea");

	$(".upload-btn input").on("change", function() {
		var _this = $(this);
		var fr = new FileReader();
		fr.readAsDataURL(this.files[0]);

		var img = new Image();
		var btn = _this.parent();
		//btn.hide();
		var upImg = btn.siblings(".upload-img");
		upImg.addClass("loading");
	
		fr.onload = function() {
			img.src = this.result;
			
			img.onload = function() {
				btn.siblings(".upload-img").html(img);
				var ratio = 1;
				if (img.width > img.height) {
					upImg.find("img").addClass("mh");
					ratio = _h / img.height;
				} else {
					upImg.find("img").addClass("mw");
					ratio = _w / img.width;
				}
				upImg.find("img").css({"width":"100%","height":"100%","z-index":"9999"});
				var scroll = new IScroll(upImg[0], {
					zoom : true,
					scrollX : true,
					scrollY : true,
					mouseWheel : true,
					bounce : false,
					wheelAction : 'zoom'
				});

				if (btn.hasClass("btn-old")) {
					//alert(111)
					//ajaxFileUpload("files", "#image1");
					_old.img = img;
					_old.scroll = scroll;
					_old.ratio = ratio;
					
				}
				if (btn.hasClass("btn-new")) {
					//ajaxFileUpload("image_btn2", "#image2");
					_new.img = img;
					_new.scroll = scroll;
					_new.ratio = ratio;
				}

				setTimeout(function() {
					upImg.removeClass("loading").find("img").css("opacity", 1);
					ajaxFileUpload();
					
				}, 1000);
			}
			
		};
		/**fr.onloadend =function(){
				alert(3)
					
			};**/
			
	});


	function imageData(obj) {
		obj.scroll.enabled = false;
		var canvas = document.createElement('canvas');

		canvas.width = _w;
		canvas.height = _h;
		var ctx = canvas.getContext('2d');

		var w = _w / obj.scroll.scale / obj.ratio;
		var h = _h / obj.scroll.scale / obj.ratio;
		var x = Math.abs(obj.scroll.x) / obj.scroll.scale / obj.ratio;
		var y = Math.abs(obj.scroll.y) / obj.scroll.scale / obj.ratio;

		ctx.drawImage(obj.img, x, y, w, h, 0, 0, _w, _h);
		return canvas.toDataURL();
	}
	
	function ajaxFileUpload(){
		
		/**$.ajax({
    		url:portalPath+"/wx/uploadImage",
    		type:"post",
    		data:{"images":$(".upload-img>img").attr("src")},
    		dataType:"json",
    		success:function(data){
    			var htmls="";
				//console.log(data)
    			if(data.status=="success"){
    				$("upload-img>img").attr("img",data.data.image);
    			}else{
    				console.log("error",data);
    			}
    		}
    	});**/
		/**$.ajaxFileUpload({
			url : portalPath+'/wx/uploadImage2',
			secureuri : false,
			fileElementId : 'files',
			async: false,
			dataType : 'json',
			success : function(data, status) {
				console.log(data);
				if(status == 'success'){
					var url = data.replace(/<[^>]+>/g,"");
					
				}

			}

		});**/
		
	}

	/**function ajaxFileUpload(image, image_) {
		$.ajaxFileUpload({
			url : '${portalPath}/wx/uploadImage',// servlet请求路径
			fileElementId : image,// 上传控件的id
			type: 'post',  
			dataType : 'json',
			data : {
				paramName : image
			}, // 参数名称
			success : function(data, status) {
				$(image_).val(data.msg);
			},
			error : function(data, status, e) {
				alert('上传出错');
			}
		});

		return false;

	}**/
});
