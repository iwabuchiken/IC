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

		/*********************************
		 * Columns
		 *********************************/
		public static String[] cols_check_lists
										= {"name",	"genre_id", "yomi"};
		
		public static String[] cols_check_lists_FULL
		= {
			android.provider.BaseColumns._ID,
			"created_at", "modified_at",
			"name",	"genre_id", "yomi"};
		
		/*********************************
		 * Tables
		 *********************************/
		public static final String tname_CheckLists = "check_lists";
		
		/*********************************
		 * SQLite
		 *********************************/
		public static enum SQLiteDataTypes {
			
			TEXT,
			
		}
		
		/*********************************
		 * Others
		 *********************************/
		public static final int GetYomi_ChunkNum	= 5;
		
	}//public static class DBAdmin

	public static class RetVal {
		/*********************************
		 * Successful
		 *********************************/
		public static final int DB_BACKUP_SUCCESSFUL	= 10;
		
		public static final int DB_UPDATE_SUCCESSFUL	= 11;
		
		
		/*********************************
		 * Errors: DB
		 *********************************/
		public static final int DB_DOESNT_EXIST	= -10;
		
		public static final int DB_FILE_COPY_EXCEPTION	= -11;
		
		public static final int DB_CANT_CREATE_FOLDER	= -12;
		
		public static final int GETYOMI_NO_ENTRY		= -13;
		
		public static final int EXCEPTION_SQL			= -14;
		
		public static final int GetWordList_Failed		= -15;
		
		/*********************************
		 * Others: > 0, <= -90
		 *********************************/
		public static final int OK				= 1;
		
		public static final int NOP				= -90;
		
		public static final int FAILED			= -91;
		
		public static final int MAGINITUDE_ONE	= 1000;
		
	}//public static class RetVal
	
}//public class CONS
