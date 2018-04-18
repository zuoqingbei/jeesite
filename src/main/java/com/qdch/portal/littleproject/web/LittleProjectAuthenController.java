package com.qdch.portal.littleproject.web;

import com.qdch.portal.common.utils.PostgreHubUtils;
import com.qdch.portal.common.utils.PostgreUtils;
import com.qdch.portal.common.utils.StringUtils;
import com.qdch.portal.common.web.BaseController;
import com.qdch.portal.modules.account.dao.AccountAttentionDao;
import com.qdch.portal.modules.sys.dao.UserDao;
import com.qdch.portal.modules.sys.entity.User;
import com.qdch.portal.modules.sys.service.SystemService;
import com.qdch.portal.thirdplat.dao.AccountThirdplatDao;
import com.qdch.portal.thirdplat.entity.AccessToken;
import com.qdch.portal.thirdplat.entity.AccountThirdplat;
import com.qdch.portal.thirdplat.service.AccountThirdplatService;
import com.qdch.portal.thirdplat.utils.WxpubOAuth;
import com.qdch.portal.thirdplat.web.WxAuthController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * 登录小程序
 */

@Controller
public class LittleProjectAuthenController  extends BaseController {

    @Autowired
    private SystemService systemService;

    @Autowired
    private UserDao userDao;
    @Autowired
    private AccountThirdplatService accountThirdplatService;

    @Autowired
    private AccountThirdplatDao accountThirdplatDao;







//    /**
//     * 进入小程序
//     * @param request
//     * @param response
//     * @return
//     */
//    @RequestMapping(value = "${portalPath}/littleproject/auth/enter")
//    @ResponseBody
//    public  String enter(AccountThirdplat accountThirdplat,HttpServletRequest request, HttpServletResponse response){
////        if(systemService.getUser())
//
//        if(StringUtils.isBlank(accountThirdplat.getUserId())){ //用户第一次进来
//            accountThirdplatService.save(accountThirdplat);
//            return this.resultSuccessData(request,response,"未登录，跳到绑定页面",null);
//        }else { //已经登录过
//
//        }
//
//        new WxAuthController().dealAuthorize(request,response);
//        return this.resultSuccessData(request,response,"",null);
//    }

    /**
     * 点击授权按钮
     * @param request
     * @param response
     * @return
     */

    @RequestMapping(value = "${portalPath}/littleproject/auth/authoriUser")
    @ResponseBody
    public String authoriUser(AccountThirdplat accountThirdplat,HttpServletRequest request,HttpServletResponse response ){
        //先去数据库查看是否是企业用户，如果是的话，写入account_thirdplat表和sys_user表
        try {
            if(StringUtils.isBlank(request.getParameter("platkey"))){
                return this.resultFaliureData(request,response,"请先输入用户的key",null);
            }
            if(StringUtils.isBlank(request.getParameter("mobile"))){
                return this.resultFaliureData(request,response,"请先输入手机号码",null);
            }

            try {
                accountThirdplat = accountThirdplatService.getByPlatKey(accountThirdplat);

            } catch (Exception e) {
                e.printStackTrace();
                return this.resultFaliureData(request,response,"该用户的key不存在",null);
            }
            User user  = new User(accountThirdplat.getNickName(),accountThirdplat);
            user.setMobile(request.getParameter("mobile"));
            systemService.saveUser(user);
            List<Object> tellist = PostgreHubUtils.getInstance().excuteQuery("select ftel from hub_testapp_info",null);

            boolean flag = false;
            for(int i=0;i<tellist.size();i++){
                Map m = (Map) tellist.get(i);
                if(request.getParameter("mobile").equals(m.get("ftel")+"")){
                    flag = true;
                    break;
                }
            }
//            tellist.
            if(flag){ //这个true先表示是企业用户
//                accountThirdplat=accountThirdplatService.getByPlatKey(accountThirdplat);
                accountThirdplat.setUserId(user.getId());
                accountThirdplatService.save(accountThirdplat);
                return this.resultSuccessData(request,response,"绑定成功",null);
            }else{
                return this.resultData(request,response,"refuse","无权限","","");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return this.resultFaliureData(request,response,"",null);
        }


    }

    /**
     * 点击小程序进入时校验
     * @param accountThirdplat
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "${portalPath}/littleproject/auth/enterLittleProject")
    @ResponseBody
    public String enterLittleProject(AccountThirdplat accountThirdplat,HttpServletRequest request, HttpServletResponse response){
        try {
            if(StringUtils.isBlank(accountThirdplat.getPlatkey())){
                return this.resultFaliureData(request,response,"请先输入key",null);
            }
            AccountThirdplat thirdplat = accountThirdplatService.getByPlatKey(accountThirdplat);
            if(thirdplat == null){
                accountThirdplatService.save(accountThirdplat); //在第三方平台表保存基本信息
            }else{
                if(StringUtils.isNotBlank(thirdplat.getUserId())){ //认证通过会写入userid字段，若有，表示已经认证通过
                    return this.resultData(request,response,"already","用户已通过验证","",null);
                }
                accountThirdplat.setId(thirdplat.getId());
                accountThirdplatDao.update(accountThirdplat);
            }

            return this.resultSuccessData(request,response,"",null);
        } catch (Exception e) {
            e.printStackTrace();
            return this.resultFaliureData(request,response,"",null);
        }
    }
}
