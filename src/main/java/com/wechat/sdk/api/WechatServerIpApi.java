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
 * 获取微信服务器IP地址API
 * @author 帮杰
 *
 */
public class WechatServerIpApi {

	public static final String URL_GET_WECHAT_SERVER_IP = "https://api.weixin.qq.com/cgi-bin/getcallbackip?access_token=ACCESS_TOKEN";
	
	/**
	 * 获取微信服务器IP地址
	 * @param accessToken
	 * @return {
					"ip_list":["127.0.0.1","127.0.0.1"]
	 *			}
	 */
	public static String getWechatServerIpList(String accessToken) {
		String url = URL_GET_WECHAT_SERVER_IP.replace("ACCESS_TOKEN", accessToken);
		return new ApiRequest(url).doGet().optString();
	}
}
