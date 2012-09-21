package ic.listeners;

import ic.main.R;
import ic.utils.Methods;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
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
	Dialog dlg;
	Dialog dlg2;
	//
	Vibrator vib;
	
	//
//	Methods.DialogTags dlgTag = null;

	public DialogOnItemClickListener(Activity actv, Dialog dlg) {
		// 
		this.actv = actv;
		this.dlg = dlg;
		
		vib = (Vibrator) actv.getSystemService(Context.VIBRATOR_SERVICE);
		
	}//public DialogOnItemClickListener(Activity actv, Dialog dlg)

//	@Override
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
			
			/*********************************
			 * 2. Switching
			 *********************************/
			if (item.equals(actv.getString(R.string.dlg_checkactv_long_click_lv_edit))) {

				
				
			} else if (item.equals(actv.getString(
								R.string.dlg_checkactv_long_click_lv_change_serial_num))) {
			
				Methods.dlg_checkactv_long_click_lv_change_serial_num(actv, dlg);
				
			}//if (item.equals(actv.getString(R.string.dlg_checkactv_long_click_lv_edit)))
			
			
			
			break;// case dlg_checkactv_long_click_lv
			
		}//switch (tag)
		
	}//public void onItemClick(AdapterView<?> parent, View v, int position, long id)

	private void register_switching(String item) {
		if (item.equals(actv.getString(R.string.main_menu_register_list))) {
			
//			// debug
//			Toast.makeText(actv, item + "=> Under construction", 2000).show();
			
			Methods.dlg_register_list(actv, dlg);
			
		} else if (item.equals(actv.getString(R.string.main_menu_register_genre))) {
			
			Methods.dlg_register_genre(actv, dlg);
				
		} else {//if (item.equals(actv.getString(R.string.main_menu_register_list)))
	
			// debug
			Toast.makeText(actv, "Unknown item: " + item, 2000).show();
			
		}//if (item.equals(actv.getString(R.string.main_menu_register_list)))
		
	}//private void register_switching(String item)
	
}//DialogOnItemClickListener
