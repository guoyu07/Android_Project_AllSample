package com.example.demo5;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;
import com.example.demo5.R;
import com.example.demo5.R.drawable;
import com.example.demo5.R.id;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

@SuppressLint("InflateParams")
public class NetUtils {

    static  String temp;

	private NetUtils() {
		super();
	}
	
	//提交get请求
	static void httpGet_Network(Context context,String url){

		StringRequest stringRequest = new StringRequest(Request.Method.GET,url,new Listener<String>(){
            public void onResponse(String s) {
                Log.i("hechao",s);
            }
        },new ErrorListener(){
            public void onErrorResponse(VolleyError volleyError) {
                Log.e("hechao","erro");
            }
        });
		VolleySingleton.getVolleySingleton(context.getApplicationContext()).addToRequestQueue(stringRequest);
		
		
	}
	
	
	
	//提交post请求
	static void  httpPost_Network(Context context,String url){
		
		StringRequest stringRequest = new StringRequest(Method.POST, url, new Listener<String>(){
					public void onResponse(String s) {
						Log.i("hechao", s);
					}
				}, new ErrorListener() {
					public void onErrorResponse(VolleyError volleyError) {
						Log.e("hechao", "erro");
					}
				}) {
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				Map<String, String> map = new HashMap<String, String>();
				map.put("params1", "value1");
				map.put("params2", "value2");
				return map;
			}
		};
		
		VolleySingleton.getVolleySingleton(context.getApplicationContext()).addToRequestQueue(stringRequest);

	}

	 
	
	//获取json数据
	static void jsonObjectRequest(Context context,String url) {
	
		JsonObjectRequest jr = new JsonObjectRequest(Request.Method.GET, url,
				   new Response.Listener<JSONObject>() {
					public void onResponse(JSONObject jsonObject) {
				
						
						temp=jsonObject.toString();
						Log.i("hechao",temp);
					}
				}, new ErrorListener() {
					public void onErrorResponse(VolleyError volleyError) {
						Log.i("hechao","ERROR");
					}
				});
		VolleySingleton.getVolleySingleton(context.getApplicationContext()).addToRequestQueue(jr);
		
		
		
		
		
		
	}	
	
	
	
	//使用imageRequest加载图片
	
	@SuppressLint("InflateParams")
	static void imagerRequest_Network(final Context context,String url,Activity activity){
		
		final ImageView mImageView;
		
		mImageView=(ImageView) activity.findViewById(R.id.image);
		@SuppressWarnings("deprecation")
		ImageRequest request = new ImageRequest(url,
				new Response.Listener<Bitmap>() {
					
					public void onResponse(Bitmap bitmap) {
						mImageView.setImageBitmap(bitmap);
					}
					
				}, 0, 0, android.graphics.Bitmap.Config.RGB_565, new Response.ErrorListener() {
					
					public void onErrorResponse(VolleyError error) {
						mImageView.setImageResource(R.drawable.ic_launcher);
					}
				});
		
		   
		VolleySingleton.getVolleySingleton(context.getApplicationContext()).addToRequestQueue(request);
		
	}
	
	
	//保存bitmap图片
	static void saveBitmap(Bitmap bm) {
		File f = new File("/mnt/sdcard/aaa", "pic.jpeg");
		if (f.exists()) {
			f.delete();
		}
		try {
			FileOutputStream out = new FileOutputStream(f);
			bm.compress(Bitmap.CompressFormat.JPEG, 90, out);
			out.flush();
			out.close();
			Log.i("hechao", "已经保存");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	//通过imageloader加载图片
	
	@SuppressLint("InflateParams")
	static void imageLoader_Network(Context context,String url,Activity activity){
		
		ImageLoader mImageLoader;
		ImageView mImageView;
		mImageView = (ImageView) activity.findViewById(R.id.image);
		mImageLoader = VolleySingleton.getVolleySingleton(context.getApplicationContext()).getImageLoader();
		
		mImageLoader.get(url, ImageLoader.getImageListener(mImageView,R.drawable.ic_launcher, R.drawable.ic_launcher) );
		Log.i("hechao","finished");
		
	}
	
	
	
	
	
	//通过networkimageloader加载图片
	static void  netWorkImageLoader(Context context,String url,Activity activity){
		
		ImageLoader mImageLoader;
		NetworkImageView mNetworkImageView;
		mNetworkImageView = (NetworkImageView) activity.findViewById(R.id.networkImageView);
		mImageLoader = VolleySingleton.getVolleySingleton(context.getApplicationContext()).getImageLoader();
		mNetworkImageView.setImageUrl(url, mImageLoader);
	
	}
	

	
	// uil 加载图片
	static void loadImageByUIL(Activity activity,String url) {
		
		com.nostra13.universalimageloader.core.ImageLoader loader;
		loader = com.nostra13.universalimageloader.core.ImageLoader.getInstance();
		ImageView iv_img = (ImageView) activity.findViewById(R.id.image);
		loader.displayImage(
			url,iv_img, new ImageLoadingListener() {
			public void onLoadingStarted(String arg0, View arg1) {}
			public void onLoadingComplete(String arg0, View arg1,Bitmap arg2) {}
			public void onLoadingCancelled(String arg0, View arg1) {}
			public void onLoadingFailed(String arg0, View arg1, FailReason arg2) {
			}
		 });
		
	
		
	}

	//单例模式下实例化方法
	public static NetUtils getInstance() {
		return new NetUtils();
	}
		
	
	
	
	
}
