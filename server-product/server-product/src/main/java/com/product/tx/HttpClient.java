package com.product.tx;

import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HttpClient {

	public static String get(String url) {
		String reslut="";
		try {
			CloseableHttpClient httpClient=HttpClients.createDefault(); 
			HttpGet httpGet=new HttpGet(url);
			httpGet.addHeader("Content-Type","application/json");
			httpGet.addHeader("groupId",LubanTransactionManager.getThreadLocalGroupId());
			System.out.println("LubanTransactionManager.getThreadLocalGroupId()"+LubanTransactionManager.getThreadLocalGroupId());
			httpGet.addHeader("transactionCount",String.valueOf(LubanTransactionManager.getThreadLocaltransactionCount()));
			CloseableHttpResponse closeableHttpResponse=httpClient.execute(httpGet);
			if(closeableHttpResponse.getStatusLine().getStatusCode()==HttpStatus.SC_OK) {
				reslut=EntityUtils.toString(closeableHttpResponse.getEntity());
			}
			closeableHttpResponse.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reslut;
	}
}
