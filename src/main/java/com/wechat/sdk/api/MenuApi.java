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
 * 自定义菜单相关API
 * @author 帮杰
 *
 */
public class MenuApi {
	
	public static String URL_CREATE_MENU = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
	public static String URL_GET_MENU = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN";
	public static String URL_DELETE_MENU = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";
	public static String URL_GET_MENU_CONFIG = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";
	
	/**
	 * 创建菜单
	 * @param accessToken
	 * @param jsonMenu
	 * @return JSON
	 */
	public static String createMenu(String accessToken,String jsonMenu) {
		String url = URL_CREATE_MENU.replace("ACCESS_TOKEN", accessToken);
		return new ApiRequest(url,jsonMenu.getBytes()).doPost().optString();
	}

	/**
	 * 获取菜单
	 * @param accessToken
	 * @return JSON
	 */
	public static String getMenu(String accessToken) {
		String url = URL_GET_MENU.replace("ACCESS_TOKEN", accessToken);
		return new ApiRequest(url).doGet().optString();
	}
	
	/**
	 * 删除菜单
	 * @param accessToken
	 * @return JSON
	 */
	public static String deleteMenu(String accessToken) {
		String url = URL_DELETE_MENU.replace("ACCESS_TOKEN", accessToken);
		return new ApiRequest(url).doGet().optString();
	}
	
	/**
	 * 获取菜单配置
	 * @param accessToken
	 * @return JSON
	 */
	public static String getMenuConfig(String accessToken) {
		String url = URL_GET_MENU_CONFIG.replace("ACCESS_TOKEN", accessToken);
		return new ApiRequest(url).doGet().optString();
	}
}
