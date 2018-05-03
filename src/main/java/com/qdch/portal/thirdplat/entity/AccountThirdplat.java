/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.qdch.portal.thirdplat.entity;

import com.qdch.portal.modules.sys.entity.User;
import org.hibernate.validator.constraints.Length;

import com.qdch.portal.common.persistence.DataEntity;

/**
 * 第三方账号Entity
 * @author zuoqb
 * @version 2018-03-20
 */
public class AccountThirdplat extends DataEntity<AccountThirdplat> {

	private static final long serialVersionUID = 1L;
	private User user; // 用户id
	private String ptype; // 平台类型 QQ wechart weibo
	private String platkey; // 第三方平台标识码 比如openid unionid

	private String unionid; //unionid
	private String nickName; // 昵称
	private String image; // 头像
	private String gender; // 性别
	private String province; // 省
	private String city; // 城市
	private String mobile; // 手机号
	private String country; // 国家
	private String sessionkey;

	public String getSessionkey() {
		return sessionkey;
	}

	public void setSessionkey(String sessionkey) {
		this.sessionkey = sessionkey;
	}

	public AccountThirdplat() {
		super();
	}

	public AccountThirdplat(String id) {
		super(id);
	}

	public AccountThirdplat(String ptype, String platkey) {
		this.ptype = ptype;
		this.platkey = platkey;
	}

	public AccountThirdplat(WxUserInfo wxUserInfo) {
		this.ptype = "wechat";
		this.platkey = wxUserInfo.getOpenid();
		this.nickName = wxUserInfo.getNickname();
		this.image=wxUserInfo.getHeadimgurl();
		if("1".equals(wxUserInfo.getSex())){
			this.gender="男";
		}else if("0".equals(wxUserInfo.getSex())){
			this.gender="女";
		}else{
			this.gender="其他";
		}
		this.province=wxUserInfo.getProvince();
		this.city=wxUserInfo.getCity();
		this.country=wxUserInfo.getCountry();
	}
	public AccountThirdplat updateInfo(AccountThirdplat account,WxUserInfo wxUserInfo) {
		account.setNickName(wxUserInfo.getNickname());
		account.setImage(wxUserInfo.getHeadimgurl());
		if("1".equals(wxUserInfo.getSex())){
			account.setGender("男");
		}else if("0".equals(wxUserInfo.getSex())){
			account.setGender("女");
		}else{
			account.setGender("其他");
		}
		account.setProvince(wxUserInfo.getProvince());
		account.setCountry(wxUserInfo.getCountry());
		account.setCity(wxUserInfo.getCity());
		return account;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Length(min = 1, max = 64, message = "平台类型 QQ wechart weibo长度必须介于 1 和 64 之间")
	public String getPtype() {
		return ptype;
	}

	public void setPtype(String ptype) {
		this.ptype = ptype;
	}

	@Length(min = 0, max = 255, message = "第三方平台标识码 比如openid unionid长度必须介于 0 和 255 之间")
	public String getPlatkey() {
		return platkey;
	}

	public void setPlatkey(String platkey) {
		this.platkey = platkey;
	}

	@Length(min = 0, max = 255, message = "昵称长度必须介于 0 和 255 之间")
	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	@Length(min = 0, max = 255, message = "头像长度必须介于 0 和 255 之间")
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Length(min = 0, max = 64, message = "性别长度必须介于 0 和 64 之间")
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Length(min = 0, max = 64, message = "省长度必须介于 0 和 64 之间")
	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	@Length(min = 0, max = 64, message = "城市长度必须介于 0 和 64 之间")
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Length(min = 0, max = 64, message = "手机号长度必须介于 0 和 64 之间")
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Length(min = 0, max = 64, message = "国家长度必须介于 0 和 64 之间")
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getUnionid() {
		return unionid;
	}

	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}
}