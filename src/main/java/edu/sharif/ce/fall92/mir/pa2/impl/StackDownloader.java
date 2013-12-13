package edu.sharif.ce.fall92.mir.pa2.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.google.gson.Gson;


public class StackDownloader {
	
	
	
		
	
	public static String readUrl(String urlString)throws Exception{
		BufferedReader reader = null;
		try{
			URL url = new URL(urlString);
			reader = new BufferedReader(new InputStreamReader(url.openStream(),"UTF-8"));
			StringBuffer buffer = new StringBuffer();
			int read;
			char[] chars = new char[1024];
			while((read = reader.read(chars)) != -1){
				//System.out.println("*****"+ chars[0]);
				buffer.append(chars,0,read);
			}
			return buffer.toString();
		}finally{
			if(reader !=null){
				reader.close();
			}
		}
	}
	
	public static StackExchangeClass Json2Doc(String json){

		Gson gson = new Gson();
		StackExchangeClass items = gson.fromJson(json, StackExchangeClass.class);
		return items;
	}
	public static void showResults() throws Exception{
		String url = "http://api.stackexchange.com/2.1/questions?order=desc&sort=activity&site=stackoverflow&filter=-HX._IZLp8hrwY1AEiCN8BGLaGsCkNgy9Kbk-";
		String result = StackDownloader.readUrl(url);
//		String result = StackDownloader.getSource(url);


		/* fapfap fap 
		InputStream input = new URL(url).openStream();
		Reader reader = new InputStreamReader(input, "UTF-8");
		StackExchangeClass items = new Gson().fromJson(reader, StackExchangeClass.class);/*
//		System.out.println(result);
//	    PrintStream out = new PrintStream(System.out, true, "UTF-8");
//	    out.println(result);
		
		
		Gson gson = new Gson();
		String jsonobj= "{\"items\":\"ffffffff\"}";
		//StackExchangeClass items = gson.fromJson(result, StackExchangeClass.class);
//		JsonElement je = new JsonParser().parse(result);
		
//		String value = je.getAsJsonObject().get("items").getAsString();
//		System.out.println(value);
		//StackExchangeClass items = gson.fromJson(result	, StackExchangeClass.class);
		/*for(Item item: items.items){
			System.out.println(item.title);
		}*/
	}
}
