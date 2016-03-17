package com.example.demo5;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Environment;
import android.provider.Contacts.People;
import android.util.Log;

public class FileUtil {

	// 由路径获取所有文件及文件夹
	static List<File> getFilesByPath(String path) {

		List<File> fileList = new ArrayList<File>();
		File file = new File(path);
		if (file.isDirectory()) {
			File[] files = file.listFiles();

			for (File f : files) {
				fileList.add(f);
			}
			return fileList;
		}

		return null;
	}

	// object[] values = List.ToArray(typeof(object));

	// 由filelist获取其下所有文件夹
	static List<File> getDirector(List<File> filelist) {
		List<File> list = new ArrayList<File>();
		for (File file : filelist) {
			if (file.isDirectory()) {
				list.add(file);
			}
		}
		return list;
	}

	// 由filelist获取其下所有非文件夹文件
	static List<File> getIsNotDirector(List<File> filelist) {
		List<File> list = new ArrayList<File>();
		for (File file : filelist) {
			if (file.isFile()) {
				list.add(file);
			}
		}
		return list;
	}

	// 由filelist获取其下所有文件名称
	static List<String> getAllFilenameByFiles(List<File> fileList) {
		List<String> list = new ArrayList<String>();
		for (File file : fileList) {
			list.add(file.getName());
		}
		return list;
	}

	// 由filelist获取其下所有 歌曲 文件
	static List<File> getMp3FileByPath(String filePath) {

		List<File> fileList = new ArrayList<File>();
		List<File> result = new ArrayList<File>();
		fileList = FileUtil.getFilesByPath(filePath);

		fileList = FileUtil.getIsNotDirector(fileList);
		for (File file : fileList) {
			if (file.getName().endsWith(".mp3")) {
				result.add(file);
			}
		}
		return result;

	}

	// 由filelist获取其下所有 视频文件
	static List<File> getMP4FileByPath(String filePath) {

		List<File> fileList = new ArrayList<File>();
		List<File> result = new ArrayList<File>();
		fileList = FileUtil.getFilesByPath(filePath);

		fileList = FileUtil.getIsNotDirector(fileList);
		for (File file : fileList) {
			if (file.getName().endsWith(".mp4")
					|| file.getName().endsWith(".avi")
					|| file.getName().endsWith(".rmvb")
					|| file.getName().endsWith(".MP4")
					|| file.getName().endsWith(".AVI")
					|| file.getName().endsWith(".RMVB")) {
				result.add(file);
			}
		}
		return result;

	}

	// 由filelist获取其下所有 图片文件
	static List<File> getPictureByPath(String filePath) {

		List<File> fileList = new ArrayList<File>();
		List<File> result = new ArrayList<File>();
		fileList = FileUtil.getFilesByPath(filePath);

		fileList = FileUtil.getIsNotDirector(fileList);
		for (File file : fileList) {
			if (file.getName().endsWith(".jpg")
					|| file.getName().endsWith(".gif")
					|| file.getName().endsWith(".JPG")
					|| file.getName().endsWith(".JPGE")
					|| file.getName().endsWith(".jpge")) {
				result.add(file);
			}
		}
		return result;

	}

	static List<File> folderList = new ArrayList<File>();
	static List<File> result = new ArrayList<File>();

	// 递归方法
	static void traverseFolder2Picture(String path) {

		File file = new File(path);
		if (file.exists()) {
			File[] files = file.listFiles();

			// Log.i("hhhhhh",files.length+"");

			if (files.length == 0) {
				System.out.println("文件夹是空的!");
				return;
			} else {
				for (File file2 : files) {
					if (file2.isDirectory()) {
						folderList.add(file2);
						traverseFolder2Picture(file2.getAbsolutePath());

					} else if (file2.getName().endsWith(".JPG")
							|| file2.getName().endsWith(".jpg")
							|| file2.getName().endsWith(".JPGE")
							|| file2.getName().endsWith(".png")
							|| file2.getName().endsWith(".jpge")
							|| file2.getName().endsWith(".gif")) {
						result.add(file2);
						Log.i("hhhhhh", file2.getName() + "  is added \n");

					} else {

					}
				}
			}
		} else {
			System.out.println("文件不存在!");
		}

	}

	// 递归方法
	static void traverseFolder2Video(String path) {

		File file = new File(path);
		if (file.exists()) {
			File[] files = file.listFiles();

			// Log.i("hhhhhh",files.length+"");

			if (files.length == 0) {
				System.out.println("文件夹是空的!");
				return;
			} else {
				for (File file2 : files) {
					if (file2.isDirectory()) {
						folderList.add(file2);
						traverseFolder2Video(file2.getAbsolutePath());

					} else if (file2.getName().endsWith(".MP4")
							|| file2.getName().endsWith(".mp4")
							|| file2.getName().endsWith(".RMVB")
							|| file2.getName().endsWith(".avi")
							|| file2.getName().endsWith(".rmvb")
							|| file2.getName().endsWith(".AVI")) {
						result.add(file2);
						Log.i("hhhhhh", file2.getName() + "  is added \n");

					} else {

					}
				}
			}
		} else {
			System.out.println("文件不存在!");
		}

	}

	// 筛选目录下所有子目录特定文件 -视频
	static List<File> selectAllVedioByFilepath(String filepath) {
		traverseFolder2Video(filepath);
		return result;
	}

	// 筛选目录下所有子目录特定文件 -歌曲
	static List<File> selectAllPictureByFilepath(String filepath) {
		traverseFolder2Picture(filepath);
		return result;
	}

	static String str=""; 
	
	// 写入文件
	static void writeTXTFile(String fileName, String content) {
		File file = new File("/mnt/sdcard/aaa", fileName);
		
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			try {
				FileOutputStream fos = new FileOutputStream(file);
				fos.write(content.getBytes());
				fos.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

	static String readTXTFile(String fileName) {
		File file = new File("/mnt/sdcard/aaa", fileName);
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			try {
				FileInputStream inputStream = new FileInputStream(file);
				byte[] b = new byte[inputStream.available()];
				inputStream.read(b);
				return new String(b);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	
	
	
	
	
	
	
	
	//设置共享变量
	static void setSharedPreferences (Activity activity){
        Context ctx =activity.getApplicationContext();
        SharedPreferences sp =ctx.getSharedPreferences("CITY", Context.MODE_PRIVATE);
        Editor editor=sp.edit();
        editor.putString("CityName", "Beijing");
        editor.commit();
        Log.i("hechao","setCityName is finished");
    }
	
	
//	获取共享变量
	static String getSharedPreferences(Activity activity) {
		String cityName = "";
		Context ctx = activity.getApplicationContext();
		SharedPreferences sp = ctx.getSharedPreferences("CITY", Context.MODE_PRIVATE);
		cityName=sp.getString("CityName", "广州");
		return cityName;
	}
	

	
	
	
	
	
	
	//定义SQLite工具类
	static MySQLiteHelper myHelper;

	// 初始化sqlite数据库
	static void init_Sqlite(Activity activity) {
		myHelper = new MySQLiteHelper(activity, "my.db", null, 1);
	}

	// 向数据库中插入和更新数据
	static void insertAndUpdateData_SQLite() {
		// 获取数据库对象
		SQLiteDatabase db = myHelper.getWritableDatabase();
		db.execSQL("insert into hero_info(name,level) values('bb',0)");

		ContentValues values = new ContentValues();
		values.put("name", "xh");
		values.put("level", 5);
		db.insert("hero_info", "id", values);
		Log.i("hechao", "insert is finished !");
		values.clear();
		values.put("name", "xh");
		values.put("level", 10);

		db.update("hero_info", values, "level = 5", null);

		db.close();
	}

	// 查询数据库
	static String queryData_SQLite() {
		String result = "";
		SQLiteDatabase db = myHelper.getReadableDatabase();
		// 查询表中的数据
		Cursor cursor = db.query("hero_info", null, null, null, null, null,
				"id asc");
		// 获取name列的索引
		int nameIndex = cursor.getColumnIndex("name");
		// 获取level列的索引
		int levelIndex = cursor.getColumnIndex("level");
		for (cursor.moveToFirst(); !(cursor.isAfterLast()); cursor.moveToNext()) {
			result = result + cursor.getString(nameIndex) + "\t\t";
			result = result + cursor.getInt(levelIndex) + "       \n";
		}
		cursor.close();// 关闭结果集
		db.close();// 关闭数据库对象
		return result;
	}

	// 删除数据
	public static void deleteData_Sqlite() {
		SQLiteDatabase db = myHelper.getWritableDatabase();// 获取数据库对象
		String DELETE_DATA = "DELETE FROM hero_info WHERE id=1";
		db.execSQL(DELETE_DATA);
		db.close();
	}

	// 删除表
	public static void destroyTable_Sqlite() {
		SQLiteDatabase db = myHelper.getWritableDatabase();// 获取数据库对象
		db.delete("hero_info", "1", null);
		db.close();
	}
	

	
	
	
	//获取其他程序的数据
	static void getContentProviderData(Activity activity){
		
		Context ctx=activity.getApplicationContext();
		ContentResolver resolver =ctx.getContentResolver();
		Uri uri=Uri.parse("content://com.example.demo3.MyProvider");

		Log.i("hechao","uri is done");
		Cursor c = resolver.query(uri, null, null, null, null);
		Log.i("hechao","cursor is done");
		c.moveToFirst();
		while(!c.isAfterLast()){
		    for(int i=0,j=c.getColumnCount();i<j;i++){
		        Log.i("hechao",""+c.getString(i));
		    }
		    c.moveToNext();
		}
		
		
		
		
	}
	
	
	//获取手机联系人	
    static String getContactFromPhone(Activity activity) {
        
        String result = "";
        Uri uri = Uri.parse("content://contacts/people");        //联系人Content Provider的URI
        String[] columns = {People._ID, People.NAME};            //联系人的ID和Name
        
    	Context ctx=activity.getApplicationContext();
        ContentResolver contentResolver = ctx.getContentResolver();     //获取ContentResolver对象
        Cursor cursor = contentResolver.query(uri, columns, null, null, null);    //查询Content Provider
            int peopleId = cursor.getColumnIndex(People._ID);       //获得ID字段的列索引
            int peopleName = cursor.getColumnIndex(People.NAME);    //获得Name字段的列索引
//            Log.i("hechao",peopleId+"");
            
        //遍历Cursor对象，提取数据
        for(cursor.moveToFirst(); (!cursor.isAfterLast()); cursor.moveToNext()) {
            result = result + cursor.getString(peopleId) + "\t\t";
            result = result + cursor.getString(peopleName) + "\t\n";
        }
        
        cursor.close();
        return result;
    }
	
	
	
    
    
    
    
	
	
	
	
}




