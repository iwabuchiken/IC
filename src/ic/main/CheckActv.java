package ic.main;

import java.util.List;

import ic.items.CL;
import ic.items.Item;
import ic.listeners.ButtonOnClickListener;
import ic.listeners.ButtonOnTouchListener;
import ic.utils.DBUtils;
import ic.utils.ItemListAdapter;
import ic.utils.Methods;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class CheckActv extends ListActivity {

	/*********************************
	 * List-related
	 *********************************/
	static ItemListAdapter ilAdp;
	
	static List<Item> iList;

	static CL clList;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		/********************************
		 * 1. set_up_1
		 * 2. Set listeners
		 ********************************/
		/*********************************
		 * 1. Set up
		 *********************************/
		super.onCreate(savedInstanceState);

		set_up_1();
		
		/*********************************
		 * 2. Set listeners
		 *********************************/
		set_listeners();
		
	}//public void onCreate(Bundle savedInstanceState)

	private void set_listeners() {
		/*********************************
		 * 
		 *********************************/
		Button bt_add = (Button) findViewById(R.id.actv_check_bt_add);
		
		bt_add.setTag(Methods.ButtonTags.actv_check_bt_add);
		
		bt_add.setOnTouchListener(new ButtonOnTouchListener(this));
		bt_add.setOnClickListener(new ButtonOnClickListener(this));

		
	}//private void set_listeners()

	private void set_up_1() {
		/********************************
		 * 1. Set up
		 * 	1. Basics
		 * 	2. Initialise vars
		 * 2. Get intent values
		 * 
		 * 3. Get list object
		 * 
		 * 4. Set title
		 * 
		 * 5. Get item list
		 * 
		 * 6. Close db
		 * 
		 ********************************/
		
		setContentView(R.layout.actv_check);

		this.setTitle(this.getClass().getName());
		
		/*********************************
		 * 1.2. Initialise vars
		 *********************************/
		
		
		
		/*********************************
		 * 2. Get intent values
		 *********************************/
		Intent i = this.getIntent();
		
		long list_id = i.getLongExtra(MainActv.intent_list_id, -1);
		
		if (list_id == -1) {
			
			// debug
			Toast.makeText(this, "No intent value", Toast.LENGTH_SHORT).show();
			
			return;
			
		}//if (list_id == -1)
		
		// debug
//		Toast.makeText(this, "list_id=" + list_id, Toast.LENGTH_SHORT).show();
		
		/*********************************
		 * 3. Get list object
		 *********************************/
		clList = Methods.get_clList_from_db_id(this, list_id);
		
		// Log
		Log.d("CheckActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "clList.getName(): " + clList.getName());
		
		/*********************************
		 * 4. Set title
		 *********************************/
		TextView tv_title = (TextView) findViewById(R.id.actv_check_tv);

		String title = clList.getName();
		
		if (title.equals("")) {
			
			tv_title.setText("No list name data");
			
		} else {
			
			tv_title.setText(title);
			
		}
		
		/*********************************
		 * 5. Get item list
		 * 	1. Query
		 * 	2. Build list
		 *********************************/
		/*********************************
		 * 5.1. Query
		 *********************************/
		DBUtils dbu = new DBUtils(this, MainActv.dbName);
		
		SQLiteDatabase rdb = dbu.getReadableDatabase();

		Cursor c = Methods.select_all_from_table(this, rdb, MainActv.tableName_items);
		
		if (c.getCount() < 1) {
			
			// debug
			Toast.makeText(this, "No data yet", Toast.LENGTH_SHORT).show();
			
			/********************************
			 * 3. Close db
			 ********************************/
			rdb.close();

			return;
		}//if (c.getCount() < 1)
		
		/*********************************
		 * 5.2. Build list
		 *********************************/
		iList = Methods.get_item_list_from_check_list(this, clList.getDb_id());
		
		// Log
		Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "iList.size(): " + iList.size());
		
		
		/*********************************
		 * 6. Close db
		 *********************************/
		rdb.close();
		
	}//private void set_up_1()
	

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
	
}//public class CheckActv extends ListActivity
