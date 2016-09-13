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
 * 微信客服接口
 * @author 帮杰
 *
 */
public class CustomerServiceApi {

	public static final String URL_SEND_MSG_TO_USER = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESS_TOKEN";
	
	/**
	 * 向用户发送消息
	 * @param accessToken
	 * @param openid
	 * @param msg 如文本消息：{"touser":"$openid","msgtype":"text","text":{"content":"$(this is a msg)"}}";
	 * @return JSON
	 */
	public static String sendMsgToUser(String accessToken,String openid,String jsonMsg) {
		String url = URL_SEND_MSG_TO_USER.replace("ACCESS_TOKEN", accessToken);
		return new ApiRequest(url,jsonMsg.getBytes()).doPost().optString();
	}

}
