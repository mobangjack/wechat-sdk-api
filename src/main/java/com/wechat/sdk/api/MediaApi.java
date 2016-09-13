/**
 * Copyright (c) 2011-2015, Mobangjack 莫帮�? (mobangjack@foxmail.com).
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

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.wechat.sdk.api.util.http.ApiRequest;

/**
 * 素材管理API
 * @author 帮杰
 */
public class MediaApi {

	public static final String URL_UPLOAD_TEMP_MEDIA = "http://file.api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";
	public static final String URL_DOWNLOAD_TEMP_MEDIA = "http://file.api.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID";
	public static final String URL_UPLOAD_PERMANENT_NEWS = "https://api.weixin.qq.com/cgi-bin/material/add_news?access_token=ACCESS_TOKEN";
	public static final String URL_UPLOAD_PERMANENT_IMAGE = "https://api.weixin.qq.com/cgi-bin/media/uploadimg?access_token=ACCESS_TOKEN";
	public static final String URL_UPLOAD_PERMANENT_MEDIA = "https://api.weixin.qq.com/cgi-bin/material/add_material?access_token=ACCESS_TOKEN";
	public static final String URL_GET_PERMANENT_MEDIA = "https://api.weixin.qq.com/cgi-bin/material/get_material?access_token=ACCESS_TOKEN";
	public static final String URL_DELETE_PERMANENT_MEDIA = "https://api.weixin.qq.com/cgi-bin/material/del_material?access_token=ACCESS_TOKEN";
	public static final String URL_UPDATE_NEWS = "https://api.weixin.qq.com/cgi-bin/material/update_news?access_token=ACCESS_TOKEN";
	public static final String URL_GET_MEDIA_COUNT = "https://api.weixin.qq.com/cgi-bin/material/get_materialcount?access_token=ACCESS_TOKEN";
	public static final String URL_GET_MEDIA_BATCH = "https://api.weixin.qq.com/cgi-bin/material/batchget_material?access_token=ACCESS_TOKEN";
	
	public static final String MEDIA_TYPE_IMAGE = "image";
	public static final String MEDIA_TYPE_VOICE = "voice";
	public static final String MEDIA_TYPE_VIDEO = "video";
	public static final String MEDIA_TYPE_THUMB = "thumb";
	public static final String MEDIA_TYPE_NEWS = "news";
	
	/**
	 *<P>新增临时素材
	 *<p> 公众号经常有需要用到一些临时性的多媒体素材的场景，例如在使用接口特别是发送消息时，对多媒体文件、多媒体消息的获取和调用等操作，是通过media_id来进行的。素材管理接口对所有认证的订阅号和服务号开放。通过本接口，公众号可以新增临时素材（即上传临时多媒体文件）。
	 *<p>请注意：
	 *<p>1、对于临时素材，每个素材（media_id）会在开发者上传或粉丝发送到微信服务器3天后自动删除（所以用户发送给开发者的素材，若开发者需要，应尽快下载到本地），以节省服务器资源。
	 *<p>2、media_id是可复用的。
	 *<p>3、素材的格式大小等要求与公众平台官网一致。具体是，图片大小不超过2M，支持bmp/png/jpeg/jpg/gif格式，语音大小不超过5M，长度不超过60秒，支持mp3/wma/wav/amr格式
	 * @param accessToken 公众号access_token
	 * @param file 待上传的素材文件
	 * @param mediaType 媒体文件类型（image/voice/video/thumb/news）
	 * @return JSON
	 */
	public static String uploadTempMedia(String accessToken,File file,String mediaType) {
		String url = URL_UPLOAD_TEMP_MEDIA.replace("ACCESS_TOKEN", accessToken).replace("TYPE", mediaType);
		Map<String, Object> formDataMap = new HashMap<String, Object>();
		formDataMap.put("type", mediaType);
		formDataMap.put("media", file);
		ApiRequest request =  new ApiRequest(url);
		request.setFormData(formDataMap);
		return request.doPost().optString();
	}
	
	/**
	 * 下载临时素材（视频文件不支持下载）
	 * @param accessToken
	 * @param mediaId
	 * @param dest 目标文件，不存在会自动新建
	 * @return true if success,false otherwise.
	 */
	public static boolean downloadTempMedia(String accessToken,String mediaId,File dest) {
		String url = URL_DOWNLOAD_TEMP_MEDIA.replace("ACCESS_TOKEN", accessToken).replace("MEDIA_ID", mediaId);
		return new ApiRequest(url).doPost().optFile(dest);
	}
	
	/**
	 * 上传永久图文素材
	 * @param accessToken
	 * @param jsonNews 
	 *<br>{
  	 *<br>"articles": [{
     *<br>"title": TITLE,
     *<br>"thumb_media_id": THUMB_MEDIA_ID,
     *<br>"author": AUTHOR,
     *<br>"digest": DIGEST,
     *<br>"show_cover_pic": SHOW_COVER_PIC(0 / 1),
     *<br>"content": CONTENT,
     *<br>"content_source_url": CONTENT_SOURCE_URL
     *<br>},
     *<br>//若新增的是多图文素材，则此处应还有几段articles结构
     *<br>]}
     *<p>参数说明
	 *<p>|--参数--|--是否必须--|--说明--|
     *<p>title 	 是                标题
     *<p>thumb_media_id 	是 	图文消息的封面图片素材id（必须是永久mediaID）
     *<p>author 	是 	作者
     *<p>digest 	是 	图文消息的摘要，仅有单图文消息才有摘要，多图文此处为空
     *<p>show_cover_pic 	是 	是否显示封面，0为false，即不显示，1为true，即显示
     *<p>content 	是 	图文消息的具体内容，支持HTML标签，必须少于2万字符，小于1M，且此处会去除JS
     *<p>content_source_url 	是 	图文消息的原文地址，即点击“阅读原文”后的URL
     *<br>请注意，在图文消息的具体内容中，将过滤外部的图片链接，开发者可以通过上传永久图片素材接口上传图片得到URL，放到图文内容中使用。
	 * @return 
	 *{
     *<br>"media_id":MEDIA_ID
     *<br>}
     *<br>返回的即为新增的图文消息素材的media_id
	 */
	public static String uploadPermanentNews(String accessToken,String jsonNews) {
		String url = URL_UPLOAD_PERMANENT_NEWS.replace("ACCESS_TOKEN", accessToken);
		return new ApiRequest(url,jsonNews.getBytes()).doPost().optString();
	}
	
	/**
	 * 上传永久图片素材，获得的url可用于上传永久图文素材。请注意，本接口所上传的图片不占用公众号的素材库中图片数量的5000个的限制。图片仅支持jpg/png格式，大小必须在1MB以下。 
	 * @param accessToken
	 * @param imageFile 待上传的素材文件
	 * @return 返回说明 正常情况下的返回结果为：
	 *<br>{
	 *<br>"url":  "http://mmbiz.qpic.cn/mmbiz/gLO17UPS6FS2xsypf378iaNhWacZ1G1UplZYWEYfwvuU6Ont96b1roYs CNFwaRrSaKTPCUdBK9DgEHicsKwWCBRQ/0"
	 *<br>}
	 *<br>其中url就是上传图片的URL，可用于后续群发中，放置到图文消息中。 
	 */
	public static String uploadPermanentImage(String accessToken,File imageFile) {
		String url = URL_UPLOAD_PERMANENT_IMAGE.replace("ACCESS_TOKEN", accessToken);
		Map<String, Object> formDataMap = new HashMap<String, Object>();
		formDataMap.put("media", imageFile);
		ApiRequest request =  new ApiRequest(url);
		request.setFormData(formDataMap);
		return request.doPost().optString();
	}
	
	/**
	 * 上传图片、语音、视频和缩略图等永久素材
	 * @param accessToken
	 * @param file 待上传的素材文件
	 * @param mediaType 媒体文件类型，分别有图片（image）、语音（voice）、视频（video）和缩略图（thumb）
	 * @param description 如果上传的是视频，则需提供该参数
	 * <br>{
     *<br>"title":VIDEO_TITLE,
  	 *<br>"introduction":INTRODUCTION
	 *<br>}
	 * @return
	 * {
  	 *<br>"media_id":MEDIA_ID,
     *<br>"url":URL
	 *<br>}
	 *<br>media_id:新增的永久素材的media_id
	 *<br>url:新增的图片素材的图片URL（仅新增图片素材时会返回该字段） 
	 */
	public static String uploadPermanentMedia(String accessToken,File file,String mediaType,String description) {
		String url = URL_UPLOAD_TEMP_MEDIA.replace("ACCESS_TOKEN", accessToken).replace("TYPE", mediaType);
		Map<String, Object> formDataMap = new HashMap<String, Object>();
		formDataMap.put("type", mediaType);
		formDataMap.put("media", file);
		if (description!=null) {
			formDataMap.put("description", description);
		}
		ApiRequest request =  new ApiRequest(url);
		request.setFormData(formDataMap);
		return request.doPost().optString();
	}
	
	/**
	 * 获取永久素材
	 * @param accessToken
	 * @param mediaId
	 * @param dest 目标转储文件（可选）。如果请求的是图片、语音和视频等素材，则必须提供该参数。
	 * @return
	 * 如果请求的素材为图文消息，则响应如下：
	 * {
 	 *<br>"news_item":
 	 *<br>[
     *<br>{
     *<br>"title":TITLE,
     *<br>"thumb_media_id"::THUMB_MEDIA_ID,
     *<br>"show_cover_pic":SHOW_COVER_PIC(0/1),
     *<br>"author":AUTHOR,
     *<br>"digest":DIGEST,
     *<br>"content":CONTENT,
     *<br>"url":URL,
     *<br>"content_source_url":CONTENT_SOURCE_URL
     *<br>},
     *<br>//多图文消息有多篇文章
  	 *<br>]
     *<br>}
     *<br>如果返回的是视频消息素材，则内容如下：
     *<br>{
  	 *<br>"title":TITLE,
  	 *<br>"description":DESCRIPTION,
     *<br>"down_url":DOWN_URL,
	 *<br>}
	 *<br>其他类型的素材消息将直接保存到目标文件，并返回"true"（成功）或者"false"（失败）
	 */
	public static String getPermanetMedia(String accessToken,String mediaId,File dest) {
		String url = URL_GET_PERMANENT_MEDIA.replace("ACCESS_TOKEN", accessToken);
		String out = "{\"media_id\":\""+mediaId+"\"}";
		if (dest==null) {
			return new ApiRequest(url,out.getBytes()).doPost().optString();
		}else {
			return ""+new ApiRequest(url,out.getBytes()).doPost().optFile(dest);
		}
		
	}
	
	/**
	 * 删除永久素材
	 * @param accessToken
	 * @param mediaId
	 * @return
	 *{
     *<br>"errcode":ERRCODE,
     *<br>"errmsg":ERRMSG
   	 *<br>}
     *<br>正常情况下调用成功时，errcode将为0。 
	 */
	public static String deletePermanentMedia(String accessToken,String mediaId) {
		String url = URL_DELETE_PERMANENT_MEDIA.replace("ACCESS_TOKEN", accessToken);
		String out = "{\"media_id\":"+"\""+mediaId+"\"}";
		return new ApiRequest(url,out.getBytes()).doPost().optString();
	}
	
	/**
	 * 修改永久图文素材
	 * @param accessToken
	 * @param jsonNews 
	 *<br>{
  	 *<br>"media_id":MEDIA_ID,
  	 *<br>"index":INDEX,
  	 *<br>"articles": {
     *<br>  "title": TITLE,
     *<br>  "thumb_media_id": THUMB_MEDIA_ID,
     *<br>  "author": AUTHOR,
     *<br>  "digest": DIGEST,
     *<br>  "show_cover_pic": SHOW_COVER_PIC(0 / 1),
     *<br>  "content": CONTENT,
     *<br>  "content_source_url": CONTENT_SOURCE_URL
     *<br>}
     *<br>}
     *<br>|--参数--|--是否必须--|--说明--|
	 *<br>media_id 	是 	要修改的图文消息的id
	 *<br>index 	是 	要更新的文章在图文消息中的位置（多图文消息时，此字段才有意义），第一篇为0
	 *<br>title 	是 	标题
	 *<br>thumb_media_id 	是 	图文消息的封面图片素材id（必须是永久mediaID）
	 *<br>author 	是 	作者
	 *<br>digest 	是 	图文消息的摘要，仅有单图文消息才有摘要，多图文此处为空
	 *<br>show_cover_pic 	是 	是否显示封面，0为false，即不显示，1为true，即显示
	 *<br>content 	是 	图文消息的具体内容，支持HTML标签，必须少于2万字符，小于1M，且此处会去除JS
	 *<br>content_source_url 	是 	图文消息的原文地址，即点击“阅读原文”后的URL
	 * @return
	 * {
     *<br>"errcode": ERRCODE,
     *<br>"errmsg": ERRMSG
	 *<br>}
	 *<br>正确时errcode的值应为0 
	 */
	public static String updateNews(String accessToken,String jsonNews) {
		String url = URL_UPDATE_NEWS.replace("ACCESS_TOKEN", accessToken);
		return new ApiRequest(url,jsonNews.getBytes()).doPost().optString();
	}
	
	/**
	 * 获取素材总数
	 *<br>请注意：
	 *<br>1.永久素材的总数，也会计算公众平台官网素材管理中的素材
	 *<br>2.图片和图文消息素材（包括单图文和多图文）的总数上限为5000，其他素材的总数上限为1000
	 * @param accessToken
	 * @return
	 * {
  	 *<br>"voice_count":COUNT,
  	 *<br>"video_count":COUNT,
  	 *<br>"image_count":COUNT,
  	 *<br>"news_count":COUNT
	 *<br>}
	 *<br>返回参数说明：
	 *<br>voice_count: 	语音总数量
	 *<br>video_count: 	视频总数量
	 *<br>image_count: 	图片总数量
	 *<br>news_count: 	图文总数量 
	 */
	public static String getMediaCount(String accessToken) {
		String url = URL_GET_MEDIA_COUNT.replace("ACCESS_TOKEN", accessToken);
		return new ApiRequest(url).doGet().optString();
	}
	
	/**
	 * 批量获取永久素材
	 * @param accessToken
	 * @param type 素材的类型，图片（image）、视频（video）、语音 （voice）、图文（news）
	 * @param offset 从全部素材的该偏移位置开始返回，0表示从第一个素材 返回 
	 * @param count 返回素材的数量，取值在1到20之间 
	 * @return
	 * 永久图文消息素材列表的响应如下：
	 *<br>{
     *<br>"total_count": TOTAL_COUNT,
     *<br>"item_count": ITEM_COUNT,
     *<br>"item": [{
     *<br>  "media_id": MEDIA_ID,
     *<br>  "content": {
     *<br>      "news_item": [{
     *<br>          "title": TITLE,
     *<br>          "thumb_media_id": THUMB_MEDIA_ID,
     *<br>          "show_cover_pic": SHOW_COVER_PIC(0 / 1),
     *<br>          "author": AUTHOR,
     *<br>          "digest": DIGEST,
     *<br>          "content": CONTENT,
     *<br>          "url": URL,
     *<br>          "content_source_url": CONTETN_SOURCE_URL
     *<br>      },
     *<br>      //多图文消息会在此处有多篇文章
     *<br>      ]
     *<br>   },
     *<br>   "update_time": UPDATE_TIME
     *<br>},
     *<br>//可能有多个图文消息item结构
     *<br>其他类型（图片、语音、视频）的返回如下：
	 *<br>{
     *<br>"total_count": TOTAL_COUNT,
     *<br>"item_count": ITEM_COUNT,
     *<br>"item": [{
     *<br>  "media_id": MEDIA_ID,
     *<br>  "name": NAME,
     *<br>  "update_time": UPDATE_TIME,
     *<br>  "url":URL
     *<br>},
     *<br>//可能会有多个素材
     *<br>]}
     *<br>]}
	 */
	public static String getMediaBatch(String accessToken,String type,int offset,int count) {
		offset = offset<0?0:offset;
		count = count<1?1:(count>20?20:count);
		String url = URL_GET_MEDIA_BATCH.replace("ACCESS_TOKEN", accessToken);
		String out = "{\"type\":\""+type+"\",\"offset\":"+offset+",\"count\":"+count+"}";
		return new ApiRequest(url,out.getBytes()).doPost().optString();
	}
	
}
