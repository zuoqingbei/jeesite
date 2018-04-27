package com.qdch.portal.thirdplat.utils;

import java.util.HashMap;
import java.util.Map;

import com.qdch.portal.common.config.Global;
import com.qdch.portal.common.mapper.JsonMapper;
import com.qdch.portal.common.utils.StringUtils;
import com.qdch.portal.thirdplat.entity.AccessToken;
import com.qdch.portal.thirdplat.entity.WxUserInfo;
/**
 * @todo   微信认证工具类
 * @time   2018年3月20日 下午2:33:29
 * @author zuoqb
 */
public class WxpubOAuth {
	private static final String APP_ID = Global.getAppId();
	private static final String APP_SECRET = Global.getAppSecret();
	/**
	 * 
	 * @todo   用户同意授权，获取微信code  组装重定向地址
	 * @time   2018年3月20日 下午12:58:37
	 * @author zuoqb
	 * @return_type   String
	 */
	public static String createOauthUrlForCode(String redirect_uri, boolean scope) {
		String url = "https://open.weixin.qq.com/connect/oauth2/authorize?";
		url+="appid="+APP_ID;
		url+="&redirect_uri="+redirect_uri;
		url+="&response_type=code";
		/**
		 * 应用授权作用域，snsapi_base （不弹出授权页面，直接跳转，只能获取用户openid），
		 * snsapi_userinfo （弹出授权页面，可通过openid拿到昵称、性别、所在地。并且，
		 *  即使在未关注的情况下，只要用户授权，也能获取其信息 ）
		 */
		if(scope){
			//需要用户手动授权
			url+="&scope=snsapi_userinfo";
		}else{
			url+="&scope=snsapi_base";
		}
		url+="#wechat_redirect";
		return url;
	}
	/**
	 * 根据code获取微信access_token
	 * @todo   首先请注意，这里通过code换取的是一个特殊的网页授权access_token,
	 * 与基础支持中的access_token（该access_token用于调用其他接口）不同。
	 * 公众号可通过下述接口来获取网页授权access_token。如果网页授权的作用域为snsapi_base，
	 * 则本步骤中获取到网页授权access_token的同时，
	 * 也获取到了openid，snsapi_base式的网页授权流程即到此为止。
	 * @time   2018年3月20日 下午1:32:06
	 * @author zuoqb
	 * @return_type   AccessToken
	 */
	public static AccessToken getAccessTokenByCode(String code) {
		String url = "https://api.weixin.qq.com/sns/oauth2/access_token";
		Map<String,String> createMap = new HashMap<String,String>();  
        createMap.put("appid",APP_ID);  
        createMap.put("secret",APP_SECRET);  
        createMap.put("code",code);  
        createMap.put("grant_type","authorization_code");  
		/**token
		 * { "access_token":"ACCESS_TOKEN",
			"expires_in":7200,
			"refresh_token":"REFRESH_TOKEN",
			"openid":"OPENID",
			"scope":"SCOPE" }
		 */
        String token=HttpClientUtil.sendPostSSLRequest(url, createMap);
		AccessToken accessToken=(AccessToken)JsonMapper.fromJsonString(token, AccessToken.class);
		System.out.println(token);
		return accessToken;
	}
	/**
	 * @todo   检验授权凭证（access_token）是否有效
	 * @time   2018年3月20日 下午3:14:47
	 * @author zuoqb
	 * @return_type   boolean
	 */
	public static boolean checkAccessToken(AccessToken accessToken) {
		if(accessToken==null){
			return false;
		}
		String url = "https://api.weixin.qq.com/sns/auth";
		Map<String,String> createMap = new HashMap<String,String>();  
        createMap.put("access_token",accessToken.getAccess_token());  
        createMap.put("openid",accessToken.getOpenid());  
        String token=HttpClientUtil.sendPostSSLRequest(url, createMap);
        /**
         * 正确的JSON返回结果{ "errcode":0,"errmsg":"ok"}
         * 错误时的JSON返回示例：{ "errcode":40003,"errmsg":"invalid openid"}
         */
        return (token.indexOf("ok")!=-1);
	}
	/**
	 * @todo   刷新access_token由于access_token拥有较短的有效期，当access_token超时后，
	 * 可以使用refresh_token进行刷新，refresh_token有效期为30天，当refresh_token失效之后，需要用户重新授权
	 * @time   2018年3月20日 下午2:42:05
	 * @author zuoqb
	 * @return_type   AccessToken
	 */
	public static AccessToken refreshAccessToken(AccessToken accessToken) {
		String url = "https://api.weixin.qq.com/sns/oauth2/refresh_token";
		Map<String,String> createMap = new HashMap<String,String>();  
        createMap.put("appid",APP_ID);  
        createMap.put("grant_type","refresh_token");  
        createMap.put("refresh_token",accessToken.getRefresh_token());  
		/**token
		 * { "access_token":"ACCESS_TOKEN",
			"expires_in":7200,
			"refresh_token":"REFRESH_TOKEN",
			"openid":"OPENID",
			"scope":"SCOPE" }
		 */
        String token=HttpClientUtil.sendPostSSLRequest(url, createMap);
		accessToken=(AccessToken)JsonMapper.fromJsonString(token, AccessToken.class);
		return accessToken;
	}
	/**
	 * @todo   拉取用户信息(需scope为 snsapi_userinfo)
	 * @time   2018年3月20日 下午2:38:33
	 * @author zuoqb
	 * @return_type   WxUserInfo
	 */
	public static WxUserInfo getUserInfo(AccessToken accessToken) {
		if(accessToken==null){
			return null;
		}
		String url = "https://api.weixin.qq.com/sns/userinfo";
		Map<String,String> createMap = new HashMap<String,String>();  
        createMap.put("access_token",accessToken.getAccess_token());  
        createMap.put("openid",accessToken.getOpenid());  
        createMap.put("lang","zh_CN");  
        String token=HttpClientUtil.sendPostSSLRequest(url, createMap);
        WxUserInfo wxUserInfo=(WxUserInfo)JsonMapper.fromJsonString(token, WxUserInfo.class);
		System.out.println(token);
		return wxUserInfo;
	}

    /**
     * 获取用户sessionkey
     * @param code
     * @return
     */
    public static WxUserInfo getSessionKey(String code) {
        if(StringUtils.isBlank(code)){
            return null;
        }
        String url = "https://api.weixin.qq.com/sns/jscode2session";
        Map<String,String> createMap = new HashMap<String,String>();
        createMap.put("appid",Global.getLittleProjectAppId());
        createMap.put("secret",Global.getLittleProjectAppSecret());
        createMap.put("js_code",code);
        createMap.put("grant_type","authorization_code");
        String token=HttpClientUtil.sendPostSSLRequest(url, createMap);
        WxUserInfo wxUserInfo=(WxUserInfo) JsonMapper.fromJsonString(token, WxUserInfo.class);
        System.out.println(token);
        return wxUserInfo;
    }
}
