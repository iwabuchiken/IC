package ic.main;

import java.util.ArrayList;
import java.util.List;

import ic.items.CL;
import ic.utils.DBUtils;
import ic.utils.MainListAdapter;
import ic.utils.Methods;
import android.app.Activity;
import android.app.ListActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActv extends ListActivity {

	/********************************
	 * Lists
	 ********************************/
	public static List<CL> CLList;
	
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
	
	public static String[] cols_items =			{"text",	"serial_num",	"list_id"};
	
	public static String[] col_types_items = {"TEXT", "INTEGER",		"INTEGER"};


    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	/********************************
		 * 
		 ********************************/
    	
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.main);
        
        this.setTitle(this.getClass().getName());
    
        //debug
        create_tables();
        
        show_list();
        
    }//public void onCreate(Bundle savedInstanceState)

	private void show_list() {
		/********************************
		 * 1. Set up db
		 * 2. Query
		 * 
		 * 3. Close db
		 * 
		 * 4. Build list
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
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Exception => " + e.toString());
			
			rdb.close();
			
			return;
		}
		
		// Log
		Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "c.getCount() => " + c.getCount());

		
		/********************************
		 * 4. Build list
		 ********************************/
		if (c.getCount() < 1) {
			
			// debug
			Toast.makeText(this, "No data yet", 2000).show();
			
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
					c.getInt(4)
					));
			
		}//for (int i = 0; i < c.getCount(); i++)

		// Log
		Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "CLList.size(): " + CLList.size());
		
		/********************************
		 * 5. Set list to adapter
		 ********************************/
		MainListAdapter mlAdp = new MainListAdapter(
				this,
				R.layout.list_row_main,
				CLList
				);
		
		/********************************
		 * 6. Set adapter to view
		 ********************************/
		setListAdapter(mlAdp);
		
	}//private void show_list()

	private void create_tables() {
		/********************************
		 * 1. Set up db
		 ********************************/
		DBUtils dbu = new DBUtils(this, MainActv.dbName);
		
		SQLiteDatabase wdb = dbu.getWritableDatabase();

		
		boolean res = dbu.createTable(
											wdb, 
											MainActv.tableName_check_lists, 
											MainActv.cols_check_lists, 
											MainActv.col_types_check_lists);
		
		// Log
		Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "res(" + MainActv.tableName_check_lists + ": " + res);
		
		res = dbu.createTable(
				wdb, 
				MainActv.tableName_items, 
				MainActv.cols_items, 
				MainActv.col_types_items);
		
		// Log
		Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "res(" + MainActv.tableName_items + ": " + res);

		
	}//private void create_tables()

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO 自動生成されたメソッド・スタブ
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	protected void onDestroy() {
		// TODO 自動生成されたメソッド・スタブ
		super.onDestroy();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO 自動生成されたメソッド・スタブ
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onPause() {
		// TODO 自動生成されたメソッド・スタブ
		super.onPause();
	}

	@Override
	protected void onResume() {
		// TODO 自動生成されたメソッド・スタブ
		super.onResume();
	}

	@Override
	protected void onStart() {
		// TODO 自動生成されたメソッド・スタブ
		super.onStart();
	}

	@Override
	protected void onStop() {
		// TODO 自動生成されたメソッド・スタブ
		super.onStop();
	}

    
}//public class MainActv extends Activity
