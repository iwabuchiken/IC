package ic.utils;

import java.io.File;

import android.database.sqlite.SQLiteDatabase;
import android.provider.MediaStore;

public class CONS {

	public static class Prefs {
		
		public static String prefName = "pref_ic";
		
		public static String prefKey_genreId = "genre_id";
		
		public static int prefKey_genreId_intValue = -1;
		
	}
	
	public static class DBAdmin {

		public static String dbName = "ic.db";
//		public static String dbName = "ic";
		
		public static String dirName_ExternalStorage = "/mnt/sdcard-ext";

//		public static String dirPath_db = "/data/data/shoppinglist.main/databases";
		public static String dirPath_db = "/data/data/ic.main/databases";
		
		public static String fileName_db_backup_trunk = "ic_backup";

		public static String fileName_db_backup_ext = ".bk";

		public static String dirPath_db_backup = 
						dirName_ExternalStorage + "/IC_backup";

		
	}//public static class DBAdmin
	
}
