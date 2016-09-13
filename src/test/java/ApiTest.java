import com.wechat.sdk.api.MediaApi;

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

/**
 * @author 帮杰
 *
 */
public class ApiTest {

	public static void main(String[] args) {
		//String appId = "wxdd8e6007a42078a7";
		//String appSecret = "45521ab7a4dc5b3fdbd302eb74de810a";
		//String token = AccessTokenApi.getAccessToken(appId, appSecret);
		//System.out.println(token);
		String token = "8igN-CAVZ_iBtp0fsAKMHmMIoQtImv0P3pONxerZhJLvX-bH6zJxVAcE4fSheOz8lySPYYKi8Ou4FFsUgu53fa2T6dcOGndm14qYq51VPII";
		//File file = new File("C:\\Users\\帮杰\\Pictures\\Camera Roll\\WIN_20150214_181258.MP4");
		//System.out.println(uploadPermanentMedia(token, file, MEDIA_TYPE_VIDEO, "{\"title\":\"me\",\"introduction\":\"this is a short video\"}"));
		System.out.println(MediaApi.getMediaCount(token));
		System.out.println(MediaApi.getMediaBatch(token, MediaApi.MEDIA_TYPE_NEWS, 0, 1));
		//File dest = new File("C:\\Users\\帮杰\\desktop\\WIN_20150214_181258.mp4");
		//System.out.println(getPermanetMedia(token, "njcLcS3Fl9XJF7gmR__bPApZ5GBIH1_Luk0juehMqzfOFE4rl3cPGEKP5mwS5yRF", dest));
	}
}
