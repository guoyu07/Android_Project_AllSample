package com.example.demo5;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.app.Activity;
import android.util.Log;
import android.widget.TextView;

public class JsoupUtils {


	
	public String result;
	
	public  String getResult() {
		return result;
	}
	public  void setResult(String result) {
		this.result = result;
	}
	
	public  void  getImageUrlByUrl( String content, final String url){

		new Thread(new Runnable() {
			public void run() {
				try {
					Document doc = Jsoup.connect(url).get();
					Elements elements = doc.getElementsByTag("img");
					
					for (Element ele : elements) {
//						content+=ele.attr("src");
//						content+="\n";
					}
					
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
		}).start();
		
		
		
	}
	
	
	
	
	
	
	
	

}























