package com.example.demo5;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
public class MainActivity extends Activity {

	NetUtils netUtils;
	TextView tv;
	ImageView iv;
	String content="";
	List<String> imag_urls;
	final String url="https://www.zhihu.com/question/41404094/answer/90834557";
	ListView listview;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout1);
		
		tv= (TextView) findViewById(R.id.tv);
		
		imag_urls=new ArrayList<String>();
		
		new Thread(runnable).start();
		
		
	}

	//新线程
	Runnable runnable = new Runnable(){
	    @Override
	    public void run() {
	    	// 执行完毕后给handler发送一个空消息
			try {
				Document doc = Jsoup.connect(url).get();
				Elements elements = doc.getElementsByTag("img");
				
				for (Element ele : elements) {
					imag_urls.add(ele.attr("src"));
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
	        handler1.sendEmptyMessage(0);
	    }
	};

	
	
	Handler handler1 = new Handler(){


		@Override
	    public void handleMessage(Message msg) {
	        super.handleMessage(msg);
//	        tv.setText(imag_urls.toString());
	        
	        listview =(ListView) findViewById(R.id.picListView);
	        
//	        listview.setAdapter(new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_expandable_list_item_1,imag_urls));
	        
	    	MyAdapter adapter= new MyAdapter(MainActivity.this, imag_urls);
	    	listview.setAdapter(adapter);
	    	
	        
	        
	        
//	        NetUtils.loadImageByUIL(MainActivity.this,imag_urls.get(2));


//	        iv=(ImageView) findViewById(R.id.image);

//	        NetUtils.loadImageByUIL(MainActivity.this, imag_urls.get(2));

			
	        
	        
	        
	    }

	    
	    
	    
	    
	    
	    
	    
	};

	
	
	
}
