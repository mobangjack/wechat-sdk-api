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

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.security.SecureRandom;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

/**
 * HttpsRequest
 * @author 帮杰
 *
 */
public class HttpsRequest {

	public static final String METHOD_GET = "GET";
	public static final String METHOD_POST = "POST";
	
	protected String method = METHOD_GET;
	protected String url;
	protected String encoding = "UTF-8";
	private byte[] outBytes;
	
	protected HttpsURLConnection connection = null;
	
	public HttpsRequest(String url) {
		this.url = url;
	}
	
	public HttpsRequest(String url,byte[] outBytes) {
		this.url = url;
		this.outBytes = outBytes;
	}
	
	public HttpsRequest(String url,String method) {
		this.url = url;
		this.method = method;
	}
	
	public HttpsRequest(String url,String method,byte[] outBytes) {
		this.url = url;
		this.method = method;
		this.outBytes = outBytes;
	}
	
	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public byte[] getOutBytes() {
		return outBytes;
	}

	public void setOutBytes(byte[] outBytes) {
		this.outBytes = outBytes;
	}

	public void doRequest() throws Exception {
		TrustManager[] trustManager = { new MyX509TrustManager() };
		SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
		sslContext.init(null, trustManager, new SecureRandom());
		SSLSocketFactory factory = sslContext.getSocketFactory();

		URL requestUrl = new URL(null,getUrl(),new sun.net.www.protocol.https.Handler());
		connection = (HttpsURLConnection) requestUrl.openConnection();
		connection.setHostnameVerifier(new MyHostnameVerifier());
		connection.setSSLSocketFactory(factory);
		connection.setDoInput(true);
		connection.setUseCaches(false);
		connection.setRequestMethod(method);
		connection.setRequestProperty("Connection", "Keep-Alive");  
		connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:39.0) Gecko/20100101 Firefox/39.0");
		if (outBytes!=null) {
			connection.setDoOutput(true);
			OutputStream out = connection.getOutputStream();
			out.write(outBytes);
			out.close();
		}
	}
	
	public InputStream getResonseAsInputStream() throws IOException {
		return connection.getInputStream();
	}
	
	public byte[] getResponseAsByteArray() throws Exception {
		InputStream in = connection.getInputStream();
		ByteArrayOutputStream output = new ByteArrayOutputStream();
	    byte[] buffer = new byte[4096];
	    int n = 0;
	    while (-1 != (n = in.read(buffer))) {
	        output.write(buffer, 0, n);
	    }
	    in.close();
	    in = null;
	    return output.toByteArray();
	}
	
	public String getResponseAsString() throws Exception {
		return new String(getResponseAsByteArray(),encoding);
	}
	
	public boolean downloadFile(File dest) throws Exception {
		boolean flag = true;
		FileOutputStream out = null;
		try {
			byte[] bytes = getResponseAsByteArray();
			if (!dest.isFile()) {
				dest.createNewFile();
			}
			out = new FileOutputStream(dest);
			out.write(bytes);
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		} finally {
			out.flush();
			if (out!=null) {
				out.close();
				out = null;
			}
			close();
		}
		return flag;
	}
	
	public void close() {
		if(connection!=null){
			connection.disconnect();
			connection = null;
		}
	}
	
	public boolean isClosed() {
		return connection==null;
	}
}
