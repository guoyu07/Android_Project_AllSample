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

	// ��·����ȡ�����ļ����ļ���
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

	// ��filelist��ȡ���������ļ���
	static List<File> getDirector(List<File> filelist) {
		List<File> list = new ArrayList<File>();
		for (File file : filelist) {
			if (file.isDirectory()) {
				list.add(file);
			}
		}
		return list;
	}

	// ��filelist��ȡ�������з��ļ����ļ�
	static List<File> getIsNotDirector(List<File> filelist) {
		List<File> list = new ArrayList<File>();
		for (File file : filelist) {
			if (file.isFile()) {
				list.add(file);
			}
		}
		return list;
	}

	// ��filelist��ȡ���������ļ�����
	static List<String> getAllFilenameByFiles(List<File> fileList) {
		List<String> list = new ArrayList<String>();
		for (File file : fileList) {
			list.add(file.getName());
		}
		return list;
	}

	// ��filelist��ȡ�������� ���� �ļ�
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

	// ��filelist��ȡ�������� ��Ƶ�ļ�
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

	// ��filelist��ȡ�������� ͼƬ�ļ�
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

	// �ݹ鷽��
	static void traverseFolder2Picture(String path) {

		File file = new File(path);
		if (file.exists()) {
			File[] files = file.listFiles();

			// Log.i("hhhhhh",files.length+"");

			if (files.length == 0) {
				System.out.println("�ļ����ǿյ�!");
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
			System.out.println("�ļ�������!");
		}

	}

	// �ݹ鷽��
	static void traverseFolder2Video(String path) {

		File file = new File(path);
		if (file.exists()) {
			File[] files = file.listFiles();

			// Log.i("hhhhhh",files.length+"");

			if (files.length == 0) {
				System.out.println("�ļ����ǿյ�!");
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
			System.out.println("�ļ�������!");
		}

	}

	// ɸѡĿ¼��������Ŀ¼�ض��ļ� -��Ƶ
	static List<File> selectAllVedioByFilepath(String filepath) {
		traverseFolder2Video(filepath);
		return result;
	}

	// ɸѡĿ¼��������Ŀ¼�ض��ļ� -����
	static List<File> selectAllPictureByFilepath(String filepath) {
		traverseFolder2Picture(filepath);
		return result;
	}

	static String str=""; 
	
	// д���ļ�
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

	
	
	
	
	
	
	
	
	//���ù������
	static void setSharedPreferences (Activity activity){
        Context ctx =activity.getApplicationContext();
        SharedPreferences sp =ctx.getSharedPreferences("CITY", Context.MODE_PRIVATE);
        Editor editor=sp.edit();
        editor.putString("CityName", "Beijing");
        editor.commit();
        Log.i("hechao","setCityName is finished");
    }
	
	
//	��ȡ�������
	static String getSharedPreferences(Activity activity) {
		String cityName = "";
		Context ctx = activity.getApplicationContext();
		SharedPreferences sp = ctx.getSharedPreferences("CITY", Context.MODE_PRIVATE);
		cityName=sp.getString("CityName", "����");
		return cityName;
	}
	

	
	
	
	
	
	
	//����SQLite������
	static MySQLiteHelper myHelper;

	// ��ʼ��sqlite���ݿ�
	static void init_Sqlite(Activity activity) {
		myHelper = new MySQLiteHelper(activity, "my.db", null, 1);
	}

	// �����ݿ��в���͸�������
	static void insertAndUpdateData_SQLite() {
		// ��ȡ���ݿ����
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

	// ��ѯ���ݿ�
	static String queryData_SQLite() {
		String result = "";
		SQLiteDatabase db = myHelper.getReadableDatabase();
		// ��ѯ���е�����
		Cursor cursor = db.query("hero_info", null, null, null, null, null,
				"id asc");
		// ��ȡname�е�����
		int nameIndex = cursor.getColumnIndex("name");
		// ��ȡlevel�е�����
		int levelIndex = cursor.getColumnIndex("level");
		for (cursor.moveToFirst(); !(cursor.isAfterLast()); cursor.moveToNext()) {
			result = result + cursor.getString(nameIndex) + "\t\t";
			result = result + cursor.getInt(levelIndex) + "       \n";
		}
		cursor.close();// �رս����
		db.close();// �ر����ݿ����
		return result;
	}

	// ɾ������
	public static void deleteData_Sqlite() {
		SQLiteDatabase db = myHelper.getWritableDatabase();// ��ȡ���ݿ����
		String DELETE_DATA = "DELETE FROM hero_info WHERE id=1";
		db.execSQL(DELETE_DATA);
		db.close();
	}

	// ɾ����
	public static void destroyTable_Sqlite() {
		SQLiteDatabase db = myHelper.getWritableDatabase();// ��ȡ���ݿ����
		db.delete("hero_info", "1", null);
		db.close();
	}
	

	
	
	
	//��ȡ�������������
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
	
	
	//��ȡ�ֻ���ϵ��	
    static String getContactFromPhone(Activity activity) {
        
        String result = "";
        Uri uri = Uri.parse("content://contacts/people");        //��ϵ��Content Provider��URI
        String[] columns = {People._ID, People.NAME};            //��ϵ�˵�ID��Name
        
    	Context ctx=activity.getApplicationContext();
        ContentResolver contentResolver = ctx.getContentResolver();     //��ȡContentResolver����
        Cursor cursor = contentResolver.query(uri, columns, null, null, null);    //��ѯContent Provider
            int peopleId = cursor.getColumnIndex(People._ID);       //���ID�ֶε�������
            int peopleName = cursor.getColumnIndex(People.NAME);    //���Name�ֶε�������
//            Log.i("hechao",peopleId+"");
            
        //����Cursor������ȡ����
        for(cursor.moveToFirst(); (!cursor.isAfterLast()); cursor.moveToNext()) {
            result = result + cursor.getString(peopleId) + "\t\t";
            result = result + cursor.getString(peopleName) + "\t\n";
        }
        
        cursor.close();
        return result;
    }
	
	
	
    
    
    
    
	
	
	
	
}




