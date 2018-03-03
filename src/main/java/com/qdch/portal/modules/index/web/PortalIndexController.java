package com.qdch.portal.modules.index.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qdch.portal.common.web.BaseController;

/**
 * 
 * @className PortalIndexController.java
 * @time   2018年3月2日 下午8:32:06
 * @author zuoqb
 * @todo   清算所门户PC首页模块
 */
@Controller
@RequestMapping("${portalPath}/index")
public class PortalIndexController extends BaseController {
	/**
	 * @time   2018年3月2日 下午8:34:10
	 * @author zuoqb
	 * @todo   首页
	 * @param  @param model
	 * @param  @param request
	 * @param  @param response
	 * @param  @return
	 * @return_type   String
	 */

	@RequestMapping({"/index"})
	public String index(Model model,HttpServletRequest request, HttpServletResponse response){
		//return portalPage+"${pla}/index/index";
		return render(request, "${pla}index/index");//添加${pla}表示需要自动识别PC or Mobile
	}
	@RequestMapping({"/index2"})
	public String index2(Model model,HttpServletRequest request, HttpServletResponse response){
		//return portalPage+"${pla}/index/index";
		return render(request, "index/index");//不添加${pla}表示只有PC端 无需自动切换页面
	}
}