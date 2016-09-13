/**
 * Copyright (c) 2011-2015, Mobangjack 莫帮杰 (mobangjack@foxmail.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.wechat.sdk.api;

import com.wechat.sdk.api.util.http.ApiRequest;

/**
 * 获取全局属性AccessToken的API
 * @author 帮杰
 *
 */
public class AccessTokenApi {

	public static final String URL_GET_ACCESSTOKEN = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	
	/**
	 * @param appId
	 * @param appSecret
	 * @return {"access_token":"ACCESS_TOKEN","expires_in":7200}
	 */
	public static String getAccessToken(String appId,String appSecret) {
		String url = URL_GET_ACCESSTOKEN.replace("APPID", appId).replace("APPSECRET", appSecret);
		return new ApiRequest(url).doGet().optString();
	}
	
}
