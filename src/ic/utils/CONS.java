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

		public static class AdminLog {
			
			public static String dName_ExternalStorage =
							"/mnt/sdcard-ext";
			
			public static String folName_Data =
					"IC_DATA";
			
			public static String folName_Logs =
					"IC_LOGS";
			
			public static String fname_Log_Trunk = "log";
			
			public static String ext_Log = ".log";
			
			public static String fname_Log_Full = "log.log";
			
			
			public static long logFile_Limit = 6000;
			
		}//static class Logs
		
		public static String dbName = "ic.db";
//		public static String dbName = "ic";
		
		public static String dbFileExt = ".db";
		
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
		
		public static enum tableNames {
			
			items,
			
		}
		
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
		 * Errors: General
		 *********************************/
		public static final int EXCEPTION_IO			= -50;
		public static final int EXCEPTION_FileNotFound	= -51;
		
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
		 * Errors: FTP
		 *********************************/
		public static final int FTP_Exception_Socket	= -40;
		
		public static final int FTP_LOGIN_FAILED		= -41;
		
		/*********************************
		 * Others: > 0, <= -90
		 *********************************/
		public static final int OK				= 1;
		
		public static final int NOP				= -90;
		
		public static final int FAILED			= -91;
		
		public static final int MAGINITUDE_ONE	= 1000;
		
	}//public static class RetVal
	
	public static class Admin {
		
		public static SortTypes sortType;
		
		public static enum SortTypes {
			SortBy_Yomi,
			SortBy_CreatedAt,
			
		}
		
		public static final int vib_Long	= 100;

		public static final String appName	= "ic";
		
		public static final String separatorColon	= ":";
		
		public static enum IntentKeys {
			
			LogFilePath,
			
		}
		
		public static class Miscs {
			
			public static final int DialogTitleLength = 10;
			
		}
	}//public static class Admin
	
	public static class FTPData {
		
		public static final String serverName
						= "ftp.benfranklin.chips.jp";
		
		public static final String userName
						= "chips.jp-benfranklin";
		
		public static final String passWord
						= "9x9jh4";
		
		public static final String dpath_Remote_Db
						= "android_app_data/IC/db";
		
		class RetVal {
			
			
		}
	}
}//public class CONS
