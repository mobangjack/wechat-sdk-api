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
 * 微信用户管理API
 * @author 帮杰
 *
 */
public class UserApi {

	public static final String URL_CREATE_USER_GROUP = "https://api.weixin.qq.com/cgi-bin/groups/create?access_token=ACCESS_TOKEN";
	public static final String URL_QUERY_ALL_USER_GROUP = "https://api.weixin.qq.com/cgi-bin/groups/get?access_token=ACCESS_TOKEN";
	public static final String URL_QUERY_USER_GROUP = "https://api.weixin.qq.com/cgi-bin/groups/getid?access_token=ACCESS_TOKEN";
	public static final String URL_UPDATE_GROUP_NAME = "https://api.weixin.qq.com/cgi-bin/groups/update?access_token=ACCESS_TOKEN";
	public static final String URL_UPDATE_USER_GROUP = "https://api.weixin.qq.com/cgi-bin/groups/members/update?access_token=ACCESS_TOKEN";
	public static final String URL_UPDATE_USER_GROUP_BATCH = "https://api.weixin.qq.com/cgi-bin/groups/members/batchupdate?access_token=ACCESS_TOKEN";
	public static final String URL_DELETE_USER_GROUP = "https://api.weixin.qq.com/cgi-bin/groups/delete?access_token=ACCESS_TOKEN";
	public static final String URL_SET_USER_REMARK = "https://api.weixin.qq.com/cgi-bin/user/info/updateremark?access_token=ACCESS_TOKEN";
	public static final String URL_GET_USER_INFO = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
	public static final String URL_GET_USER_INFO_BATCH = "https://api.weixin.qq.com/cgi-bin/user/info/batchget?access_token=ACCESS_TOKEN";
	public static final String URL_GET_USER_LIST = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN&next_openid=NEXT_OPENID";
	
	/**
	 * 创建用户分组
	 * @param accessToken
	 * @param groupName 分组名
	 * @return
	 * 正常时的返回JSON数据包示例：
	 *<br>{
     *<br>"group": {
     *<br>   "id": 107, 
     *<br>   "name": "test"
     *<br>}}
     *<br>返回参数说明：
     *<br>id: 	分组id，由微信分配
	 *<br>name: 	分组名字，UTF8编码 
	 */
	public static String createUserGroup(String accessToken,String groupName) {
		String url = URL_CREATE_USER_GROUP.replace("ACCESS_TOKEN", accessToken);
		String out = "{\"group\":{\"name\":\""+groupName+"\"}}";
		return new ApiRequest(url,out.getBytes()).doPost().optString();
	}
	
	/**
	 * 查询所有用户分组
	 * @param accessToken
	 * @return
	 * 返回说明 正常时的返回JSON数据包示例：
		{
		    "groups": [
		        {
		            "id": 0, 
		            "name": "未分组", 
		            "count": 72596
		        }, 
		        {
		            "id": 1, 
		            "name": "黑名单", 
		            "count": 36
		        }, 
		        {
		            "id": 2, 
		            "name": "星标组", 
		            "count": 8
		        }, 
		        {
		            "id": 104, 
		            "name": "华东媒", 
		            "count": 4
		        }, 
		        {
		            "id": 106, 
		            "name": "★不测试组★", 
		            "count": 1
		        }
		    ]
		}

	 */
	public static String queryAllUserGroup(String accessToken) {
		String url = URL_QUERY_ALL_USER_GROUP.replace("ACCESS_TOKEN", accessToken);
		return new ApiRequest(url).doGet().optString();
	}
	
	/**
	 *  查询用户所在分组（通过用户的OpenID查询其所在的GroupID）
	 * @param accessToken
	 * @param openId
	 * @return
	 * 返回说明 正常时的返回JSON数据包示例：

		{
		    "groupid": 102
		}

	 */
	public static String queryUserGroup(String accessToken,String openId) {
		String url = URL_QUERY_USER_GROUP.replace("ACCESS_TOKEN", accessToken);
		String out = "{\"openid\":\""+openId+"\"}";
		return new ApiRequest(url,out.getBytes()).doPost().optString();
	}
	
	/**
	 * 
	 * @param accessToken 调用接口凭证 
	 * @param id 分组id，由微信分配 
	 * @param name 分组名字（30个字符以内） 
	 * @return
	 * 返回说明 正常时的返回JSON数据包示例：

		{"errcode": 0, "errmsg": "ok"}

	 */
	public static String updateGroupName(String accessToken,String id,String name) {
		String url = URL_UPDATE_GROUP_NAME.replace("ACCESS_TOKEN", accessToken);
		String out = "{\"group\":{\"id\":"+id+",\"name\":\""+name+"\"}}";
		return new ApiRequest(url,out.getBytes()).doPost().optString();
	}
	
	/**
	 * 移动用户分组
	 * @param accessToken 调用接口凭证 
	 * @param openId 用户唯一标识符 
	 * @param toGroupId 分组id 
	 * @return
	 * 返回说明 正常时的返回JSON数据包示例：

		{"errcode": 0, "errmsg": "ok"}

	 */
	public static String updateUserGroup(String accessToken,String openId,String toGroupId) {
		String url = URL_UPDATE_USER_GROUP.replace("ACCESS_TOKEN", accessToken);
		String out = "{\"openid\":\""+openId+"\",\"to_groupid\":"+toGroupId+"}";
		return new ApiRequest(url,out.getBytes()).doPost().optString();
	}
	
	/**
	 * 批量移动用户分组
	 * @param accessToken 调用接口凭证 
	 * @param toGroupId 分组id 
	 * @param openIds 用户唯一标识符openid的列表（size不能超过50） 
	 * @return
	 * 正常时的返回JSON数据包示例：

		{"errcode": 0, "errmsg": "ok"}
		
	 */
	public static String updateUserGroupBatch(String accessToken,String toGroupId,String... openIds) {
		String url = URL_UPDATE_USER_GROUP_BATCH.replace("ACCESS_TOKEN", accessToken);
		StringBuilder out = new StringBuilder("{\"openid_list\":[");
		for (String openId:openIds) {
			out.append("\""+openId+"\",");
		}
		out = out.replace(out.length()-1, out.length(), "\"],\"to_groupid\":"+toGroupId+"}");
		return new ApiRequest(url,out.toString().getBytes()).doPost().optString();
	}
	
	/**
	 * 
	 * @param accessToken 调用接口凭证 
	 * @param group 分组 
	 * @param id 分组的id
	 * @return
	 * 返回说明 正常时的返回JSON数据包示例：

		{"errcode": 0, "errmsg": "ok"}

	 */
	public static String deleteUserGroup(String accessToken,String group,String id) {
		String url = URL_DELETE_USER_GROUP.replace("ACCESS_TOKEN", accessToken);
		String out = "{\""+group+"\":{\"id\":"+id+"}}";
		return new ApiRequest(url,out.getBytes()).doPost().optString();
	}
	
	/**
	 * 设置用户备注
	 * @param accessToken
	 * @param openid
	 * @param jsonRemark 如"{"openid":"$openid","remark":"$remark"}";
	 * @return JSON
	 */
	public String setUserRemark(String accessToken,String openid,String jsonRemark) {
		String url = URL_SET_USER_REMARK.replace("ACCESS_TOKEN", accessToken);
		return new ApiRequest(url,jsonRemark.getBytes()).doPost().optString();
	}
	
	/**
	 * 获取用户信息
	 * @param accessToken
	 * @param openid
	 * @return 正常情况下，微信会返回下述JSON数据包给公众号：

		{
		    "subscribe": 1, 
		    "openid": "o6_bmjrPTlm6_2sgVt7hMZOPfL2M", 
		    "nickname": "Band", 
		    "sex": 1, 
		    "language": "zh_CN", 
		    "city": "广州", 
		    "province": "广东", 
		    "country": "中国", 
		    "headimgurl":    "http://wx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ4eMsv84eavHiaiceqxibJxCfHe/0", 
		   "subscribe_time": 1382694957,
		   "unionid": " o6_bmasdasdsad6_2sgVt7hMZOPfL"
		   "remark": "",
		   "groupid": 0
		}

	 */
	public static String getUserInfo(String accessToken,String openid) {
		String url = URL_GET_USER_INFO.replace("ACCESS_TOKEN", accessToken).replace("OPENID", openid);
		return new ApiRequest(url).doGet().optString();
	}

	/**
	 * 批量获取用户信息
	 * @param accessToken
	 * @param openids
	 * @return
	 * 正常情况下，微信会返回下述JSON数据包给公众号（示例中为一次性拉取了2个openid的用户基本信息，第一个是已关注的，第二个是未关注的）：

		{
		   "user_info_list": [
		       {
		           "subscribe": 1, 
		           "openid": "otvxTs4dckWG7imySrJd6jSi0CWE", 
		           "nickname": "iWithery", 
		           "sex": 1, 
		           "language": "zh_CN", 
		           "city": "Jieyang", 
		           "province": "Guangdong", 
		           "country": "China", 
		           "headimgurl": "http://wx.qlogo.cn/mmopen/xbIQx1GRqdvyqkMMhEaGOX802l1CyqMJNgUzKP8MeAeHFicRDSnZH7FY4XB7p8XHXIf6uJA2SCunTPicGKezDC4saKISzRj3nz/0", 
		           "subscribe_time": 1434093047, 
		           "unionid": "oR5GjjgEhCMJFyzaVZdrxZ2zRRF4", 
		           "remark": "", 
		           "groupid": 0
		       }, 
		       {
		           "subscribe": 0, 
		           "openid": "otvxTs_JZ6SEiP0imdhpi50fuSZg", 
		           "unionid": "oR5GjjjrbqBZbrnPwwmSxFukE41U", 
		       }
		   ]
		}

	 */
	public static String getUserInfoBatch(String accessToken,String... openids) {
		String url = URL_GET_USER_INFO_BATCH.replace("ACCESS_TOKEN", accessToken);
		StringBuilder out = new StringBuilder("{\"user_list\":[");
		for (String openid:openids) {
			out.append("{\""+openid+"\",\"lang\":\"zh-CN\"},");
		}
		out = out.replace(out.length()-1, out.length(), "\"]}");
		return new ApiRequest(url).doPost().optString();
	}
	
	/**
	 * 获取用户列表
	 * @param accessToken
	 * @param nextOpenid 从nextOpenid开始获取
	 * @return 正确时返回JSON数据包：

		{"total":2,"count":2,"data":{"openid":["","OPENID1","OPENID2"]},"next_openid":"NEXT_OPENID"}

	 */
	public static String getUserList(String accessToken,String nextOpenid){
		String url = URL_GET_USER_LIST.replace("ACCESS_TOKEN", accessToken).replace("NEXT_OPENID", nextOpenid==null?"":nextOpenid);
		return new ApiRequest(url).doGet().optString();
	}
	
}
