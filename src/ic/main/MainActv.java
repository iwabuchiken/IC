/*********************************
 * IC=ItemChecker
 * Date=20120914_205758
 *********************************/
package ic.main;

import java.util.ArrayList;
import java.util.List;

import ic.items.CL;
import ic.listeners.CustomOnItemLongClickListener;
import ic.utils.CONS;
import ic.utils.DBUtils;
import ic.utils.MainListAdapter;
import ic.utils.Methods;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

// Apache


public class MainActv extends ListActivity {

	/********************************
	 * Lists
	 ********************************/
	public static List<CL> CLList;
	public static MainListAdapter mlAdp;
	
	/********************************
	 * DB
	 ********************************/
	public static String dbName = "ic.db";
	
	// check_lists
	public static String tableName_check_lists = "check_lists";

	public static String[] cols_check_lists =			{"name",	"genre_id"};
	
	public static String[] col_types_check_lists = 	{"TEXT", 	"INTEGER"};

	// items
	public static String tableName_items = "items";
	
//	public static String[] cols_items =			{"text", "serial_num",	"list_id"};
	public static String[] cols_items =			{"text", "serial_num",	"list_id", "status"};
	
	public static String[] col_types_items = {"TEXT", 	 "INTEGER",		"INTEGER"};

	// genres
	public static String tableName_genres = "genres";
	
	public static String[] cols_genres =		{"name"};
	
	public static String[] col_types_genres = 	{"TEXT"};

	// Backup
	public static String dirPath_db = "/data/data/ic.main/databases";
	
	public static String dirName_ExternalStorage = "/mnt/sdcard-ext";
	
	public static String dirPath_db_backup = dirName_ExternalStorage + "/IC_backup";
	
	public static String fileName_db_backup_trunk = "ic_backup";
	
	public static String fileName_db_backup_ext = ".bk";
	
	/*********************************
	 * Intents
	 *********************************/
	public static String intent_list_id = "list_id";
	
	
	/*********************************
	 * Others
	 *********************************/
	public static Activity mainActv;
	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	/********************************
		 * 1. Set up
		 * 2. Create tables
		 * 
		 * 3. Show list
		 * 4. Initialise vars
		 * 
		 * 5. Set listeners
		 ********************************/
    	
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.main);
        
        this.setTitle(this.getClass().getName());
    
        //debug
        create_tables();
        
        /*********************************
		 * 4. Initialise vars
		 *********************************/
        mainActv = this;
        
        /*********************************
		 * 5. Set listeners
		 *********************************/
		ListView lv = this.getListView();
		
//		lv.setTag(Methods.ItemTags.dir_list);
		lv.setTag(Methods.ListTags.actv_main_lv);
		
		lv.setOnItemLongClickListener(new CustomOnItemLongClickListener(this));
        
		/*********************************
		 * Re-install the app
		 *********************************/
//		_debug_D_20_reinstall_app();
		
    }//public void onCreate(Bundle savedInstanceState)

    private void _debug_D_20_reinstall_app() {
		
    	String src = "/mnt/sdcard-ext/IC_backup/ic_backup_20130730_092046.bk";
    	String dst = dirPath_db + "/" + dbName;
    	
    	Methods.restore_db(this, dbName, src, dst);
    	
    	// Log
		Log.d("[" + "MainActv.java : "
				+ +Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "Restore db => Done");;
		
	}//private void _debug_D_20_reinstall_app()

	private void drop_table(String tableName) {
    	// Setup db
		DBUtils dbu = new DBUtils(this, MainActv.dbName);
		
		SQLiteDatabase wdb = dbu.getWritableDatabase();
		
		boolean res = 
				dbu.dropTable(this, wdb, tableName);
		
		if (res == true) {
			// Log
			Log.d("MainActv.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Table dropped: " + tableName);
		} else {//if (res == true)

			// Log
			Log.d("MainActv.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Drop table => Failed: " + tableName);
			
		}//if (res == true)
		
		
		wdb.close();
		
		
	}//private void drop_table(String tableName)

	private void show_list() {
		/********************************
		 * 1. Set up db
		 * 2. Query
		 * 
		 * 3. Close db
		 * 
		 * 4. Build list
		 * 4-2. Sort list
		 * 
		 * 5. Set list to adapter
		 * 
		 * 6. Set adapter to view
		 ********************************/
		DBUtils dbu = new DBUtils(this, MainActv.dbName);
		
		SQLiteDatabase rdb = dbu.getReadableDatabase();
		
		/********************************
		 * 2. Query
		 ********************************/
		String sql = "SELECT * FROM " + MainActv.tableName_check_lists;
		
		Cursor c = null;
		
		try {
			
			c = rdb.rawQuery(sql, null);
			
			startManagingCursor(c);
			
		} catch (Exception e) {
			// Log
			Log.d("MainActv.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Exception => " + e.toString());
			
			rdb.close();
			
			return;
		}
		
		// Log
		Log.d("MainActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "c.getCount() => " + c.getCount());

		
		/********************************
		 * 4. Build list
		 ********************************/
		if (c.getCount() < 1) {
			
			// debug
			Toast.makeText(this, "No data yet", Toast.LENGTH_SHORT).show();
			
			/********************************
			 * 3. Close db
			 ********************************/
			rdb.close();

			return;
		}//if (c.getCount() < 1)

		c.moveToNext();
		
		CLList = new ArrayList<CL>();

		for (int i = 0; i < c.getCount(); i++) {
			
			CLList.add(new CL(
					c.getString(3),
					c.getInt(4),
					
					c.getLong(0),
					c.getLong(1),
					c.getLong(2)
					));
			
			c.moveToNext();
			
		}//for (int i = 0; i < c.getCount(); i++)

		// Log
		Log.d("MainActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "CLList.size(): " + CLList.size());
		
		rdb.close();
		
		/*********************************
		 * 4-2. Sort list
		 *********************************/
		boolean res = Methods.sort_list_CLList(this, CLList);
		
		// Log
		Log.d("MainActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "res: " + res);
		
		/********************************
		 * 5. Set list to adapter
		 ********************************/
		mlAdp = new MainListAdapter(
				this,
				R.layout.list_row_main,
				CLList
				);
		
		/********************************
		 * 6. Set adapter to view
		 ********************************/
		setListAdapter(mlAdp);
		
	}//private void show_list()

	@Override
	protected void onListItemClick(
					ListView l, View v, int position, long id) {
		/*********************************
		 * 1. Get item
		 * 2. Set up for intent
		 * 
		 * 3. Start
		 *********************************/
		CL clList = (CL) l.getItemAtPosition(position);
		
		/*********************************
		 * Register: Genre id
		 *********************************/
		SharedPreferences prefs = this
						.getSharedPreferences(
							CONS.Prefs.prefName,
							Context.MODE_PRIVATE);
		
		SharedPreferences.Editor editor = prefs.edit();
		
		editor.putInt(CONS.Prefs.prefKey_genreId, clList.getGenre_id());
		editor.commit();

		// Log
		Log.d("[" + "MainActv.java : "
				+ +Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "Prefs saved => Genre id = " + clList.getGenre_id());
		
		/*********************************
		 * 2. Set up for intent
		 *********************************/
		Intent i = new Intent();
		
		i.setClass(this, CheckActv.class);
		
		i.putExtra(MainActv.intent_list_id, clList.getDb_id());
		
		/*********************************
		 * 3. Start
		 *********************************/
		startActivity(i);
		
		// debug
//		Toast.makeText(this, clList.getName(), Toast.LENGTH_SHORT).show();
//		Toast.makeText(this, "list_id=" + clList.getDb_id(), Toast.LENGTH_SHORT).show();
		
		super.onListItemClick(l, v, position, id);
		
	}//protected void onListItemClick(ListView l, View v, int position, long id)

	private void create_tables() {
		/********************************
		 * 1. Set up db
		 * 2. check_lists
		 * 3. items
		 * 4. genres
		 * 
		 * 5. Close db
		 ********************************/
		DBUtils dbu = new DBUtils(this, MainActv.dbName);
		
		SQLiteDatabase wdb = dbu.getWritableDatabase();

		/*********************************
		 * 2. check_lists
		 *********************************/
		boolean res = dbu.createTable(
											wdb, 
											MainActv.tableName_check_lists, 
											MainActv.cols_check_lists, 
											MainActv.col_types_check_lists);
		
		// Log
		Log.d("MainActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "res(" + MainActv.tableName_check_lists + ": " + res);
		
		/*********************************
		 * 3. items
		 *********************************/
		res = dbu.createTable(
				wdb, 
				MainActv.tableName_items, 
				MainActv.cols_items, 
				MainActv.col_types_items);
		
		/*********************************
		 * 4. genres
		 *********************************/
		// Log
		Log.d("MainActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "res(" + MainActv.tableName_genres + ": " + res);

		res = dbu.createTable(
				wdb, 
				MainActv.tableName_genres, 
				MainActv.cols_genres, 
				MainActv.col_types_genres);
		
		// Log
		Log.d("MainActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "res(" + MainActv.tableName_genres + ": " + res);
		
		
		/*********************************
		 * 5. Close db
		 *********************************/
		wdb.close();
		
	}//private void create_tables()

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		MenuInflater mi = getMenuInflater();
		mi.inflate(R.menu.main_menu, menu);

		return super.onCreateOptionsMenu(menu);
	}//public boolean onCreateOptionsMenu(Menu menu)

	@Override
	protected void onDestroy() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		super.onDestroy();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		
		case R.id.main_opt_menu_register://---------------
			
			Methods.dlg_register(this);
			
			break;// case R.id.opt_menu_main_actv_register
			
		case R.id.main_opt_menu_backup_db://---------------
			
			Methods.db_backup(this);
			
			break;// case R.id.main_opt_menu_backup_db

		case R.id.main_opt_menu_filter_by_genre://---------------
			
			Methods.dlg_filter_by_genre(this);
			
			break;// case R.id.main_opt_menu_filter_by_genre

		}//switch (item.getItemId())

		return super.onOptionsItemSelected(item);
	}//public boolean onOptionsItemSelected(MenuItem item)

	@Override
	protected void onPause() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		super.onPause();
	}

	@Override
	protected void onResume() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		super.onResume();
	}

	@Override
	protected void onStart() {
		/*********************************
		 * 1. Show list
		 * 
//		 * 1. Refresh list view;
		 *********************************/
//		/*********************************
//		 * 1. Show list
//		 *********************************/
		show_list();
		
		super.onStart();
		
	}//protected void onStart()

	@Override
	protected void onStop() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		super.onStop();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		
		Methods.confirm_quit(this, keyCode);
		
		return super.onKeyDown(keyCode, event);
		
	}//public boolean onKeyDown(int keyCode, KeyEvent event)
    
}//public class MainActv extends Activity
