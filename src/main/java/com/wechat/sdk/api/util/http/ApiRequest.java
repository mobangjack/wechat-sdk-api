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
package com.wechat.sdk.api.util.http;

import java.io.File;


/**
 * ApiRequest
 * @author 帮杰
 *
 */
public class ApiRequest extends MultipartFormUploadRequest {

	public ApiRequest(String url) {
		super(url);
	}

	public ApiRequest(String url,byte[] outBytes) {
		super(url, outBytes);
	}
	
	public ApiRequest doGet() {
		try {
			setMethod(METHOD_GET);
			doRequest();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}
	
	public ApiRequest doPost() {
		try {
			setMethod(METHOD_POST);
			doRequest();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}
	
	public String optString() {
		try {
			return getResponseAsString();
		} catch (Exception e) {
			close();
			e.printStackTrace();
			return null;
		}
		
	}
	
	public byte[] optBytes() {
		try {
			return getResponseAsByteArray();
		} catch (Exception e) {
			close();
			e.printStackTrace();
			return null;
		}
		
	}
	
	public boolean optFile(File dest) {
		try {
			return downloadFile(dest);
		} catch (Exception e) {
			close();
			e.printStackTrace();
			return false;
		}
	}

}
