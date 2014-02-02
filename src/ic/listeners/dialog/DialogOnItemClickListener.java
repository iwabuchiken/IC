package ic.listeners.dialog;

import java.util.ArrayList;

import tasks.TaskFTP;
import tasks.Task_GetYomi;

import ic.items.CL;
import ic.items.Item;
import ic.main.MainActv;
import ic.main.R;
import ic.utils.CONS;
import ic.utils.DBUtils;
import ic.utils.Methods;
import ic.utils.Methods_ic;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.Toast;

public class DialogOnItemClickListener implements OnItemClickListener {

	//
	Activity actv;
	Dialog dlg1;
	Dialog dlg2;
	
	int item_position;
	
	long check_list_id;
	
	CL check_list;
	
	Item item;
	
	//
	Vibrator vib;
	
	//
//	Methods.DialogTags dlgTag = null;

	public DialogOnItemClickListener(Activity actv, Dialog dlg) {
		// 
		this.actv = actv;
		this.dlg1 = dlg;
		
		vib = (Vibrator) actv.getSystemService(Context.VIBRATOR_SERVICE);
		
	}//public DialogOnItemClickListener(Activity actv, Dialog dlg)

	public DialogOnItemClickListener(Activity actv, Dialog dlg, int item_position) {
		// 
		this.actv = actv;
		this.dlg1 = dlg;
		
		this.item_position = item_position;
		
		vib = (Vibrator) actv.getSystemService(Context.VIBRATOR_SERVICE);
		
	}//public DialogOnItemClickListener(Activity actv, Dialog dlg)

//	@Override
	public DialogOnItemClickListener(Activity actv, Dialog dlg,
			int item_position, long check_list_id) {
		/*********************************
		 * memo
		 *********************************/
		vib = (Vibrator) actv.getSystemService(Context.VIBRATOR_SERVICE);

		this.actv = actv;
		this.dlg1 = dlg;
		
		this.item_position = item_position;
		
		this.check_list_id = check_list_id;
		
	}

	public DialogOnItemClickListener(Activity actv, Dialog dlg,
			int item_position, long check_list_id, CL check_list) {

		/*********************************
		 * memo
		 *********************************/
		vib = (Vibrator) actv.getSystemService(Context.VIBRATOR_SERVICE);
		
		this.actv = actv;
		this.dlg1 = dlg;
		
		this.item_position = item_position;
		
		this.check_list_id = check_list_id;
		
		this.check_list = check_list;

	}

	public DialogOnItemClickListener
	(Activity actv,
			Dialog dlg1,
			int item_position, Item item) {
		// TODO Auto-generated constructor stub
		this.actv	= actv;
		this.dlg1	= dlg1;
		
		this.item_position = item_position;
		
		this.item	= item;

	}

	public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
		/*----------------------------
		 * Steps
		 * 1. Get tag
		 * 2. Vibrate
		 * 3. Switching
			----------------------------*/
		
		Methods.DialogItemTags tag = (Methods.DialogItemTags) parent.getTag();
//		
		vib.vibrate(Methods.vibLength_click);
		
		/*----------------------------
		 * 3. Switching
			----------------------------*/
		switch (tag) {
		
		case dlg_register_lv://------------------------------
			/*********************************
			 * 1. Get item
			 * 2. Switching
			 *********************************/
			String item = (String) parent.getItemAtPosition(position);

			/*********************************
			 * 2. Switching
			 *********************************/
			register_switching(item);
			
			break;// case dlg_register_lv

		case dlg_checkactv_long_click_lv://------------------------------
			/*********************************
			 * 1. Get item
			 * 2. Switching
			 *********************************/
			item = (String) parent.getItemAtPosition(position);
			
			// Log
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "item_position: " + item_position);

			/*********************************
			 * 2. Switching
			 *********************************/
			if (item.equals(actv.getString(
					R.string.dlg_checkactv_long_click_lv_edit))) {

				Methods.dlg_checkactv_long_click_lv_edit_item_text(
								actv, dlg1, item_position);
				
			} else if (item.equals(actv.getString(
					R.string.dlg_checkactv_long_click_lv_change_serial_num))) {
			
				Methods.dlg_checkactv_long_click_lv_change_serial_num(
								actv, dlg1, item_position);
				
			} else if (item.equals(actv.getString(
					R.string.dlg_checkactv_long_click_lv_delete_item))) {
				
				Item item_CheckActv = (Item) parent.getItemAtPosition(position);
				
				Methods.dlg_checkactv_long_click_lv_delete_item(
						actv, dlg1, item_position, item_CheckActv);
				
			}//if (item.equals(actv.getString(R.string.dlg_checkactv_long_click_lv_edit)))
			
			break;// case dlg_checkactv_long_click_lv
			
		case dlg_filter_by_genre_lv://-------------------------------
			
			item = (String) parent.getItemAtPosition(position);
			
			dlg_filter_by_genre_lv(item);
			
			break;// case dlg_filter_by_genre_lv

		case dlg_main_actv_long_click_lv://-------------------------------
			
			case_dlg_main_actv_long_click_lv(parent, position);
			
			break;// case dlg_main_actv_long_click_lv
		
		case dlg_db_admin_lv://-------------------------------
			
			String choice = (String) parent.getItemAtPosition(position);
			
			case_dlg_db_admin_lv(choice);
			
			break;// case dlg_main_actv_long_click_lv
		
		case dlg_sort_list_lv://-------------------------------
			
			choice = (String) parent.getItemAtPosition(position);
			
			case_Dlg_sort_list_lv(choice);
			
			break;// case dlg_main_actv_long_click_lv
			
		}//switch (tag)
		
	}//public void onItemClick(AdapterView<?> parent, View v, int position, long id)

	private void
	case_Dlg_sort_list_lv(String choice) {
		
		// Log
		Log.d("[" + "DialogOnItemClickListener.java : "
				+ +Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ " : "
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "choice=" + choice);
		
		CONS.Admin.SortTypes type = null;
		
		if (choice.equals(actv.getString(
				R.string.dlg_sort_list_item_name))) {
			
//			case_dlg_sort_list_ItemName();
			
			type = CONS.Admin.SortTypes.SortBy_Yomi;
			
//			Methods_ic.sort_CheckList(actv, CONS.Admin.SortTypes.SortBy_Yomi);
//			Methods_ic.sort_CheckList_ItemName(actv);
			
//			MainActv.mlAdp.notifyDataSetChanged();
//			
//			dlg.dismiss();
			
		} else if (choice.equals(actv.getString(
				R.string.dlg_sort_list_created_at))) {

			type = CONS.Admin.SortTypes.SortBy_CreatedAt;
			
//			Methods_ic.sort_CheckList(
//							actv,
//							CONS.Admin.SortTypes.SortBy_CreatedAt);
//			
//			MainActv.mlAdp.notifyDataSetChanged();
//			
//			dlg.dismiss();

		}//if (choice.equals(actv.getString(

		Methods_ic.sort_CheckList(actv, type);
//		Methods_ic.sort_CheckList_ItemName(actv);
		
		MainActv.mlAdp.notifyDataSetChanged();
		
		dlg1.dismiss();

	}//case_Dlg_sort_list_lv(String choice)
	

	private void
	case_dlg_db_admin_lv(String choice) {
		
		if (choice.equals(actv.getString(
				R.string.dlg_db_admin_item_backup_db))) {
			
			dlg_db_admin_lv_backupDb();
	
			return;
			
//		} else if (choice.equals(actv.getString(
//				R.string.dlg_db_admin_item_refatcor_db))) {
//		
//			return;
			
		} else if (choice.equals(actv.getString(
				R.string.dlg_db_admin_item_restore_db))) {
			
			Methods.restore_db(actv);
		
		} else if (choice.equals(actv.getString(
					R.string.dlg_db_admin_item_get_yomi))) {
			
			Task_GetYomi task = new Task_GetYomi(actv);
			
			task.execute("Start");
				
		} else if (choice.equals(actv.getString(
				R.string.dlg_db_admin_item_post_data))) {
			
		} else if (choice.equals(actv.getString(
				R.string.dlg_db_admin_item_upload_db))) {
			
			dlg_db_admin_item_upload_db();
			
		}//if

		
		
	}//case_dlg_db_admin_lv(AdapterView<?> parent, int position)

	private void dlg_db_admin_item_upload_db() {

		TaskFTP task = new TaskFTP(actv);

		task.execute(actv.getString(R.string.task_ftp_upload_db));
		
		dlg1.dismiss();
		
//		// debug
//		Toast.makeText(actv, "dlg_db_admin_item_upload_db", Toast.LENGTH_LONG).show();
		
	}//private void dlg_db_admin_item_upload_db()

	private void
	case_dlg_main_actv_long_click_lv
	(AdapterView<?> parent, int position) {
		// TODO Auto-generated method stub
		String item = (String) parent.getItemAtPosition(position);
		
		if (item.equals(actv.getString(
				R.string.dlg_main_actv_long_click_lv_clear_item_status))) {
			
			Methods.clear_items_all_to_zero(actv, check_list_id, dlg1);
			
		} else if (item.equals(actv.getString(
				R.string.dlg_main_actv_long_click_lv_delete_list))) {

			Methods.delete_list(actv, check_list_id, dlg1, check_list);
			
		} else {//if (item == condition)
			
			// Log
			Log.d("DialogOnItemClickListener.java"
					+ "["
					+ Thread.currentThread().getStackTrace()[2]
							.getLineNumber() + "]", "item=" + item);
			
		}//if (item == condition)

	}//case_dlg_main_actv_long_click_lv

	private void dlg_filter_by_genre_lv(String item) {
		/*********************************
		 * 1. Set up db
		 * 2. Query
		 * 
		 * 3. Build list
		 * 4. Notify adapter
		 * 
		 * 5. Dismiss dlg
		 *********************************/
		DBUtils dbu = new DBUtils(actv, MainActv.dbName);
		
		SQLiteDatabase rdb = dbu.getReadableDatabase();

		/*********************************
		 * 2. Query
		 *********************************/
		String q;
		
		if (item.equals(actv.getString(R.string.generic_label_all))) {
			
			q = "SELECT * FROM " + MainActv.tableName_check_lists;
			
		} else {//if (item.equals(actv.getString(R.string.generic_label_all))
			
			q = "SELECT * FROM " + MainActv.tableName_check_lists
					+ " WHERE " + MainActv.cols_check_lists[1] + "="
					+ Methods.get_genre_id_from_genre_name(actv, item);
			
		}//if (item.equals(actv.getString(R.string.generic_label_all))
		
		
//		String q = "SELECT * FROM " + MainActv.tableName_check_lists+
//				" WHERE " + MainActv.cols_check_lists[1] + "=" + Methods.get_genre_id_from_genre_name(actv, item);

		Cursor c = null;
		
		try {
			c = rdb.rawQuery(q, null);
			
			// Log
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "c.getCount(): " + c.getCount());
		
		} catch (Exception e) {
			// Log
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Exception => " + e.toString());
			
			// debug
			Toast.makeText(actv, "Can't get check lists", Toast.LENGTH_SHORT).show();
			
			/*********************************
			 * Close db
			 *********************************/
			rdb.close();
			
			return;
		}

		/*********************************
		 * Validation: Any records?
		 *********************************/
		if (c.getCount() < 1) {
			
			// debug
			String msg = "No records: " + item;
			
			Toast.makeText(actv,
					msg,
					Toast.LENGTH_LONG).show();
			
			// Log
			Log.d("["
					+ "DialogOnItemClickListener.java : "
					+ +Thread.currentThread().getStackTrace()[2]
							.getLineNumber() + " : "
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", msg);
			
			return;
			
		}//if (c.getCount() < 1)
		
		/*********************************
		 * 3. Build list
		 *********************************/
		c.moveToFirst();
		
		MainActv.CLList.clear();

		MainActv.CLList.addAll(Methods_ic.build_CL(actv, c));
		
		// Log
		Log.d("MainActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "CLList.size(): " + MainActv.CLList.size());

		/*********************************
		 * Close db
		 *********************************/
		rdb.close();

		/*********************************
		 * 4-2. Sort list
		 *********************************/
//		boolean res = Methods.sort_list_CLList(actv, MainActv.CLList);
//		boolean res = Methods_ic.sort_CheckList_ItemName(actv);
		boolean res = Methods_ic.sort_CheckList(
						actv,
						CONS.Admin.SortTypes.SortBy_Yomi);
		
		// Log
		Log.d("MainActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "res: " + res);
		
		MainActv.mlAdp.notifyDataSetChanged();

		/*********************************
		 * Set: Preference
		 *********************************/
		int genreId;
		
		if (item.equals(actv.getString(R.string.generic_label_all))) {
			
			genreId = 0;
			
		} else {//if (item.equals(actv.getString(R.string.generic_label_all))
			
			genreId = Methods.get_genre_id_from_genre_name(actv, item);
			
		}//if (item.equals(actv.getString(R.string.generic_label_all))
		
		
		SharedPreferences prefs = actv
						.getSharedPreferences(
							CONS.Prefs.prefName,
							Context.MODE_PRIVATE);
		
		SharedPreferences.Editor editor = prefs.edit();
		
		editor.putInt(CONS.Prefs.prefKey_genreId, genreId);
		editor.commit();

		// Log
		Log.d("[" + "MainActv.java : "
				+ +Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "Prefs saved => Genre id = " + genreId);
		
		/*********************************
		 * 5. Dismiss dlg
		 *********************************/
		dlg1.dismiss();
		
	}//private void dlg_filter_by_genre_lv()

	private void register_switching(String item) {
		if (item.equals(actv.getString(R.string.main_menu_register_list))) {
			
//			// debug
//			Toast.makeText(actv, item + "=> Under construction", 2000).show();
			
			Methods.dlg_register_list(actv, dlg1);
			
		} else if (item.equals(actv.getString(R.string.main_menu_register_genre))) {
			
			Methods.dlg_register_genre(actv, dlg1);
				
		} else {//if (item.equals(actv.getString(R.string.main_menu_register_list)))
	
			// debug
			Toast.makeText(actv, "Unknown item: " + item, 2000).show();
			
		}//if (item.equals(actv.getString(R.string.main_menu_register_list)))
		
	}//private void register_switching(String item)

	private void dlg_db_admin_lv_backupDb() {
		// TODO Auto-generated method stub
		int res = Methods.backupDb(
				actv, CONS.DBAdmin.dbName, CONS.DBAdmin.dirPath_db_backup);

		if (res == CONS.RetVal.DB_DOESNT_EXIST) {
			
			// Log
			Log.d("DialogOnItemClickListener.java"
					+ "["
					+ Thread.currentThread().getStackTrace()[2]
							.getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2]
							.getMethodName() + "]", "DB file doesn't exist: " + res);
			
		} else if (res == CONS.RetVal.DB_FILE_COPY_EXCEPTION) {//if (res == CONS.DB_DOESNT_EXIST)
		
			// Log
			Log.d("DialogOnItemClickListener.java"
					+ "["
					+ Thread.currentThread().getStackTrace()[2]
							.getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2]
							.getMethodName() + "]",
					"Copying file => Failed: " + res);
		
		} else if (res == CONS.RetVal.DB_CANT_CREATE_FOLDER) {//if (res == CONS.DB_DOESNT_EXIST)
		
			// Log
			Log.d("DialogOnItemClickListener.java"
					+ "["
					+ Thread.currentThread().getStackTrace()[2]
							.getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2]
							.getMethodName() + "]",
					"Can't create a backup folder: " + res);
		
		} else if (res == CONS.RetVal.DB_BACKUP_SUCCESSFUL) {//if (res == CONS.DB_DOESNT_EXIST)
		
			// Log
			Log.d("DialogOnItemClickListener.java"
					+ "["
					+ Thread.currentThread().getStackTrace()[2]
							.getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2]
							.getMethodName() + "]",
					"Backup successful: " + res);
		
			// debug
			Toast.makeText(actv,
					"DB backup => Done",
					Toast.LENGTH_LONG).show();
			
			/*********************************
			 * If successful, dismiss the dialog
			 *********************************/
			dlg1.dismiss();
		
		}//if (res == CONS.DB_DOESNT_EXIST)
		
	}//private void dlg_db_admin_lv_backupDb()

}//DialogOnItemClickListener
