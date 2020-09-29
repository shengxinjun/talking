package com.controller;

import java.net.URI;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.util.Result;


@Controller
@RequestMapping("/*")
public class IndexController {
	
	@RequestMapping("/test")
	@ResponseBody
	String test(){
		return "test";
		
	}
	@RequestMapping("/trTalking")
	@ResponseBody
	public Result index(HttpServletRequest request,HttpServletResponse response1,String tkl) {
		response1.setHeader("Access-Control-Allow-Origin","*");
		Result result = new Result();
		tkl=formtTKL(tkl);
		try {
			CloseableHttpClient client = null;
			CloseableHttpResponse response = null;
			try {
				String strUrl=String.format("http://api.web.21ds.cn/taoke/doTpwdCovert?apkey=2b85b448-3a8c-9d1c-a959-bdb06c4a0292&pid=mm_113075822_176200097_49910150319&content=%s&tbname=%s", tkl, "睥睨天下少年郎");
				//增加代码 begin
				URL url = new URL(strUrl);			
				URI uri = new URI(url.getProtocol(), url.getHost(), url.getPath(), url.getQuery(), null);

				HttpGet httpGet = new HttpGet(strUrl);

				client = HttpClients.createDefault();
				response = client.execute(httpGet);
				HttpEntity entity = response.getEntity();
				String json = EntityUtils.toString(entity);
				result.setMessage(json);
			} finally {
				if (response != null) {
					response.close();
				}
				if (client != null) {
					client.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	@RequestMapping("/tkl")
	@ResponseBody
	Result indexMy(HttpServletRequest request,HttpServletResponse response1,String tkl) {
		response1.setHeader("Access-Control-Allow-Origin","*");
		Result result = new Result();
		tkl=formtTKL(tkl);
		try {
			CloseableHttpClient client = null;
			CloseableHttpResponse response = null;
			try {
				String strUrl=String.format("http://api.web.21ds.cn/taoke/doTpwdCovert?apkey=66b7a88f-c79f-5359-0cb3-8ca87193c960&pid=mm_1121060066_1723950053_110428500435&content=%s&tbname=%s", tkl, "叫me军哥");
				//增加代码 begin
				URL url = new URL(strUrl);			
				URI uri = new URI(url.getProtocol(), url.getHost(), url.getPath(), url.getQuery(), null);

				HttpGet httpGet = new HttpGet(strUrl);

				client = HttpClients.createDefault();
				response = client.execute(httpGet);
				HttpEntity entity = response.getEntity();
				String json = EntityUtils.toString(entity);
				result.setMessage(json);
			} finally {
				if (response != null) {
					response.close();
				}
				if (client != null) {
					client.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	String formtTKL(String tkl){
		String result="";
		if(tkl.indexOf("$")>0&&tkl.indexOf("$")!=tkl.lastIndexOf("$")){
			result=tkl.substring(tkl.indexOf("$")+1, tkl.lastIndexOf("$"));
			return result;
		}else if(tkl.indexOf("₤")>0&&tkl.indexOf("₤")!=tkl.lastIndexOf("₤")){
			result=tkl.substring(tkl.indexOf("₤")+1, tkl.lastIndexOf("₤"));
			return result;
		}else if(tkl.indexOf("￥")>0&&tkl.indexOf("￥")!=tkl.lastIndexOf("￥")){
			result=tkl.substring(tkl.indexOf("￥")+1, tkl.lastIndexOf("￥"));
			return result;
		}else if(tkl.indexOf("₳")>0&&tkl.indexOf("₳")!=tkl.lastIndexOf("₳")){
			result=tkl.substring(tkl.indexOf("₳")+1, tkl.lastIndexOf("₳"));
			return result;
		}else if(tkl.indexOf("₴")>0&&tkl.indexOf("₴")!=tkl.lastIndexOf("₴")){
			result=tkl.substring(tkl.indexOf("₴")+1, tkl.lastIndexOf("₴"));
			return result;
		}else if(tkl.indexOf("¢")>0&&tkl.indexOf("¢")!=tkl.lastIndexOf("¢")){
			result=tkl.substring(tkl.indexOf("¢")+1, tkl.lastIndexOf("¢"));
			return result;
		}else if(tkl.indexOf("€")>0&&tkl.indexOf("€")!=tkl.lastIndexOf("€")){
			result=tkl.substring(tkl.indexOf("€")+1, tkl.lastIndexOf("€"));
			return result;
		}else if(tkl.indexOf("(")>0&&tkl.indexOf("(")!=tkl.lastIndexOf("(")){
			result=tkl.substring(tkl.indexOf("(")+1, tkl.lastIndexOf("("));
			return result;
		}else if(tkl.indexOf("(")>0&&tkl.indexOf(")")>0){
			result=tkl.substring(tkl.indexOf("(")+1, tkl.lastIndexOf(")"));
			return result;
		}else
			return tkl;
	}
}
