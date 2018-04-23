package com.qdch.portal.littleproject.web;

import com.qdch.portal.common.config.Global;
import com.qdch.portal.common.mapper.JsonMapper;
import com.qdch.portal.common.security.Cryptos;
import com.qdch.portal.common.utils.AESUtil;
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
import com.qdch.portal.thirdplat.entity.WxUserInfo;
import com.qdch.portal.thirdplat.service.AccountThirdplatService;
import com.qdch.portal.thirdplat.utils.HttpClientUtil;
import com.qdch.portal.thirdplat.utils.WxpubOAuth;
import com.qdch.portal.thirdplat.web.WxAuthController;



import org.activiti.engine.impl.util.json.JSONObject;
import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.misc.Cache;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.Security;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;




import static org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers.encryptedData;

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
                return this.resultFaliureData(request,response,"请先输入用户的openid",null);
            }
            if(StringUtils.isBlank(request.getParameter("mobile"))){
                return this.resultFaliureData(request,response,"请先输入手机号码",null);
            }

            try {
                accountThirdplat = accountThirdplatService.getByPlatKey(accountThirdplat);

            } catch (Exception e) {
                e.printStackTrace();
                return this.resultFaliureData(request,response,"该用户的openid不存在",null);
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

//    public void sendCode(HttpServletRequest request,HttpServletResponse response){
//
//        return this.resultSuccessData(request,response,"",null);
//
//    }



    @RequestMapping(value = "${portalPath}/littleproject/auth/inserttest")
    @ResponseBody
    public String  inserttest(HttpServletRequest request,HttpServletResponse response){
        String sql = "insert into hub_testapp_info(ftel) values ('111')";
        PostgreUtils.getInstance().excuteQuery(sql,null);
        return  this.resultData(request,response,"","","","");
    }


    /**
     * 完善用户基础信息
     * @param request
     * @param response
     * @return
     */

    @RequestMapping(value = "${portalPath}/littleproject/auth/getUserInfo")
    @ResponseBody
    public String getUserInfo(HttpServletRequest request,HttpServletResponse response){
        String encryptedData = request.getParameter("encryptedData");
        String iv = request.getParameter("iv");
        String session_key = request.getSession().getAttribute("sessionKey"+request.getParameter("openid"))+"";
        try {
            byte[] resultByte = AESUtil.instance.decrypt(Base64.decodeBase64(encryptedData), Base64.decodeBase64(session_key), Base64.decodeBase64(iv));
            if(null != resultByte && resultByte.length > 0){
                String userInfo = new String(resultByte, "UTF-8");
                System.out.println(userInfo);
                WxUserInfo wxUserInfo=(WxUserInfo) JsonMapper.fromJsonString(userInfo, WxUserInfo.class);
//                JSONObject json = JSONObject.fromObject(userInfo); //将字符串{“id”：1}
                AccountThirdplat accountThirdplat  = new AccountThirdplat();
                accountThirdplat.setPtype("wechat");
                accountThirdplat.setPlatkey(request.getParameter("openid"));
                AccountThirdplat thirdplat = accountThirdplatService.getByPlatKey(accountThirdplat);
                if(thirdplat == null){
                    return this.resultFaliureData(request,response,"","");
                }else{
                    thirdplat.setGender(wxUserInfo.getSex());
                    thirdplat.setCity(wxUserInfo.getCity());
                    thirdplat.setCountry(wxUserInfo.getCountry());
                    thirdplat.setProvince(wxUserInfo.getProvince());
                    thirdplat.setNickName(wxUserInfo.getNickname());
                    accountThirdplatDao.update(thirdplat);
                }
            }
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
            return this.resultFaliureData(request,response,"","");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return this.resultFaliureData(request,response,"","");
        }


        return this.resultSuccessData(request,response,"","");
    }

    /**
     * 得到用户的sessionid
     * @param request
     * @param response
     * @return
     */

    @RequestMapping(value = "${portalPath}/littleproject/auth/getSessionkey")
    @ResponseBody
    public String getSessionkey(HttpServletRequest request,HttpServletResponse response){
        try {
            String code  = request.getParameter("code");
            if(StringUtils.isBlank(code)){
                return this.resultFaliureData(request,response,"请输入用户的code",null);
            }
            WxUserInfo info = WxpubOAuth.getSessionKey(code);
            if(StringUtils.isBlank(info.getOpenid())){
                return this.resultFaliureData(request,response,"发生错误",null);
            }
            request.getSession().setAttribute("sessionKey"+info.getOpenid(),info.getSessionKey());

            //下面是往第三方平台表里插
            AccountThirdplat accountThirdplat = new AccountThirdplat();
            accountThirdplat.setPtype("wechat");
            accountThirdplat.setPlatkey(info.getOpenid());
            accountThirdplat.setUnionid(info.getUnionid());
            return this.resultSuccessData(request,response,"",info.getOpenid());
        } catch (Exception e) {
            e.printStackTrace();
            return this.resultFaliureData(request,response,"发生错误",null);
        }


    }
}
