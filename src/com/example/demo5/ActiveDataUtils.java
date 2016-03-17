package com.example.demo5;

import android.util.Log;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.Model;
import com.activeandroid.query.Select;

public class ActiveDataUtils {

	 
	
	
	
	public static void deleteData(){
		
//		new Delete().from(Item.class).where("Id = ?", 1).execute();
		
		Item.delete(Item.class, 1);
		Log.i("hechao","deleted");
		
	}
	
	public static void getRandom() {
		Model model = new Select().from(Item.class).orderBy("RANDOM()").executeSingle();
		Log.i("hechao",model.toString());
	}	
	
	
	
	
	static void saveData() {

		ActiveAndroid.beginTransaction();
		try {
		    for (int i = 0; i < 100; i++) {
		        Item item = new Item();
		        item.name = "Example " + i;
		        item.save();
		    }
		    ActiveAndroid.setTransactionSuccessful();
		    
		    Log.i("hechao","saved");
		}
		finally {
		    ActiveAndroid.endTransaction();
		}	
		
	}
	
	 
	 
	 
	 
	 
	 
	 
	 
	
}
