package com.qdch.portal.modules.index.web;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qdch.portal.common.jdbc.datasource.DynamicDataSource;
import com.qdch.portal.common.mapper.JsonMapper;
import com.qdch.portal.common.persistence.Page;
import com.qdch.portal.common.utils.JdbcUtils;
import com.qdch.portal.common.utils.JedisUtils;
import com.qdch.portal.common.utils.PostgreUtils;
import com.qdch.portal.common.web.BaseController;
import com.qdch.portal.modules.account.entity.AccountAttention;
import com.qdch.portal.modules.sys.entity.Role.RoleTypeEnum;
import com.qdch.portal.modules.sys.entity.User;
import com.qdch.portal.modules.sys.security.SecurityAuthorityAnnotation;
import com.qdch.portal.modules.sys.utils.UserUtils;

/**
 * 
 * @className PortalIndexController.java
 * @time   2018年3月2日 下午8:32:06
 * @author zuoqb
 * @todo   清算所门户PC首页模块
 */
@Controller
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
	//@SecurityAuthorityAnnotation(needLogin=true)
	@RequestMapping(value = {"${portalPath}","${portalPath}/index","${portalPath}/index/index"}, method = RequestMethod.GET)
	public String index(Model model,HttpServletRequest request, HttpServletResponse response){
		//return portalPage+"${pla}/index/index";
		
//		try {
//		    DynamicDataSource.setSlaveDataSource();
//		    //数据操作
//		   JdbcUtils postgreUtils  = JdbcUtils.getInstance("mysql");
//		   List<Object> list = postgreUtils.excuteQuery("select id from user limit 1,1", null);
//		   System.out.println("--------------"+list.get(0));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}finally{
//			DynamicDataSource.setDataSource();
//		}
		return render(request, "${pla}index/index");//添加${pla}表示需要自动识别PC or Mobile
	}
	@SecurityAuthorityAnnotation(needRoles={RoleTypeEnum.NOMAL_ROLE})
	@RequestMapping(value = {"${portalPath}/index2",""}, method = RequestMethod.GET)
	public String index2(Model model,HttpServletRequest request, HttpServletResponse response){
		//return portalPage+"${pla}/index/index";
		return render(request, "index/index");//不添加${pla}表示只有PC端 无需自动切换页面
	}
	
	@RequestMapping(value = {"${portalPath}/toJson",""}, method = RequestMethod.GET)
	@ResponseBody
	public String toJson(HttpServletRequest request,HttpServletResponse response){
		/*HashMap< String, Object> r=new HashMap<String, Object>();
		r.put("status", "value");*/
		/*Page<AccountAttention> page = accountAttentionService.findPage(new Page<AccountAttention>(pageNo, pageSize), accountAttention); 
		return this.resultSuccessData(request,response, "", mapJson(page,"success","获取数据成功"));*/
		User u=UserUtils.get("1");
		return this.resultSuccessData(request,response, "获取数据成功", u);
	};
}
