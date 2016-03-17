package com.example.demo5;

import java.io.File;

import android.graphics.Bitmap;
import android.util.Log;

import com.activeandroid.ActiveAndroid;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.utils.StorageUtils;

public class MyApplication extends com.activeandroid.app.Application{


	String result;
	public void setResult(String result) {
		this.result = result;
	}
	public String getResult() {
		return result;
	}
	
	
	public void onCreate() {
		
		
//		Log.i("hechao","application is starting ");
		
		File cacheDir = StorageUtils.getOwnCacheDirectory(getApplicationContext(), "/mnt/sdcard/aaa/hhh"); 
		
		ImageLoaderConfiguration config = new ImageLoaderConfiguration  
			    .Builder(this)  
			    .memoryCacheExtraOptions(480, 800) // max width, max height���������ÿ�������ļ�����󳤿�  
			    .threadPoolSize(3)//�̳߳��ڼ��ص�����  
			    .threadPriority(Thread.NORM_PRIORITY - 2)  
			    .denyCacheImageMultipleSizesInMemory()  
			    .memoryCache(new UsingFreqLimitedMemoryCache(2 * 1024 * 1024)) // You can pass your own memory cache implementation/�����ͨ���Լ����ڴ滺��ʵ��  
			    .memoryCacheSize(2 * 1024 * 1024)    
			    .discCacheSize(50 * 1024 * 1024)    
			    .discCacheFileNameGenerator(new Md5FileNameGenerator())//�������ʱ���URI������MD5 ����  
			    .tasksProcessingOrder(QueueProcessingType.LIFO)  
			    .discCacheFileCount(100) //������ļ�����  
			    .discCache(new UnlimitedDiscCache(cacheDir))//�Զ��建��·��  
			    .defaultDisplayImageOptions(DisplayImageOptions.createSimple())  
			    .imageDownloader(new BaseImageDownloader(this, 5 * 1000, 30 * 1000)) // connectTimeout (5 s), readTimeout (30 s)��ʱʱ��  
			    .writeDebugLogs() // Remove for release app  
			    .build();//��ʼ����  		ImageLoader.getInstance().init(config);

		ImageLoader.getInstance().init(config);//ȫ�ֳ�ʼ��������  
		

		
		
		DisplayImageOptions options;  
		options = new DisplayImageOptions.Builder()  
		 .showImageOnLoading(R.drawable.ic_launcher) //����ͼƬ�������ڼ���ʾ��ͼƬ  
		 .showImageForEmptyUri(R.drawable.ic_launcher)//����ͼƬUriΪ�ջ��Ǵ����ʱ����ʾ��ͼƬ  
		.showImageOnFail(R.drawable.ic_launcher)  //����ͼƬ����/��������д���ʱ����ʾ��ͼƬ
		.cacheInMemory(true)//�������ص�ͼƬ�Ƿ񻺴����ڴ���  
		.cacheOnDisc(true)//�������ص�ͼƬ�Ƿ񻺴���SD����  
		.considerExifParams(true)  //�Ƿ���JPEGͼ��EXIF��������ת����ת��
		.imageScaleType(ImageScaleType.EXACTLY_STRETCHED)//����ͼƬ����εı��뷽ʽ��ʾ  
		.bitmapConfig(Bitmap.Config.RGB_565)//����ͼƬ�Ľ�������//  
		.resetViewBeforeLoading(true)//����ͼƬ������ǰ�Ƿ����ã���λ  
		.displayer(new RoundedBitmapDisplayer(20))//�Ƿ�����ΪԲ�ǣ�����Ϊ����  
		.displayer(new FadeInBitmapDisplayer(100))//�Ƿ�ͼƬ���غú���Ķ���ʱ��  
		.build();//�������
		
		
		
		
		
		
		
		super.onCreate();
	
		
		setResult("hello");
		
	}

	
	
	
}
