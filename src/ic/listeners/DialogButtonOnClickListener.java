package ic.listeners;

import ic.utils.Methods;
import ic.main.R;
import android.app.Activity;
import android.app.Dialog;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

public class DialogButtonOnClickListener implements OnClickListener {
	/*----------------------------
	 * Fields
		----------------------------*/
	//
	Activity actv;
	Dialog dlg;
	Dialog dlg2;		//=> Used in dlg_input_empty_btn_XXX

	//
	Vibrator vib;
	
	// Used in => Methods.dlg_addMemo(Activity actv, long file_id, String tableName)
	long file_id;
	String tableName;
	
	public DialogButtonOnClickListener(Activity actv, Dialog dlg) {
		//
		this.actv = actv;
		this.dlg = dlg;
		
		//
		vib = (Vibrator) actv.getSystemService(actv.VIBRATOR_SERVICE);
	}

	public DialogButtonOnClickListener(Activity actv, Dialog dlg1,
			Dialog dlg2) {
		//
		this.actv = actv;
		this.dlg = dlg1;
		this.dlg2 = dlg2;
		
		//
		vib = (Vibrator) actv.getSystemService(actv.VIBRATOR_SERVICE);
	}

	public DialogButtonOnClickListener(Activity actv, Dialog dlg, long file_id, String tableName) {
		// 
		this.actv = actv;
		this.dlg = dlg;
		
		this.tableName = tableName;
		
		this.file_id = file_id;
		
		vib = (Vibrator) actv.getSystemService(actv.VIBRATOR_SERVICE);
		
	}//public DialogButtonOnClickListener(Activity actv, Dialog dlg, long file_id, String tableName)

//	@Override
	public void onClick(View v) {
		//
		Methods.DialogButtonTags tag_name = (Methods.DialogButtonTags) v.getTag();

		//
		switch (tag_name) {
		
		case dlg_generic_dismiss://------------------------------------------------
			
			vib.vibrate(Methods.vibLength_click);
			
			dlg.dismiss();
			
			break;
			
		case dlg_generic_dismiss_second_dialog://------------------------------------------------
			
			vib.vibrate(Methods.vibLength_click);
			
			dlg2.dismiss();
			
			break;// case dlg_generic_dismiss_second_dialog

		case dlg_register_genre_bt_ok://------------------------------------------------
			
			register_genre_bt_ok();
			
			break;// case dlg_register_genre_bt_ok
			
		case dlg_register_list_bt_ok://------------------------------------------------
			
			register_list_bt_ok();
			
			break;// case dlg_register_list_bt_ok

		default: // ----------------------------------------------------
			break;
		}//switch (tag_name)
	}

	private void register_list_bt_ok() {
		/*********************************
		 * dlg	=> dlg_register.xml
		 * dlg2	=> dlg_register_genre.xml
		 * 
		 * 1. Vibrate
		 * 2. Input empty?
		 * 
		 * 3. Register
		 *********************************/
		vib.vibrate(Methods.vibLength_click);
		
		/*********************************
		 * 2. Input empty?
		 *********************************/
		EditText et = 
			(EditText) dlg2.findViewById(R.id.dlg_register_list_et);
		
		if (et.getText().toString().equals("")) {
			
			// debug
			Toast.makeText(actv, "No input!", 2000).show();
			
			return;
			
		}//if (!et.getText().toString().equals(""))
		
		/*********************************
		 * 3. Register
		 *********************************/
		Methods.register_list(actv, dlg, dlg2);
		
	}//private void register_list_bt_ok()

	private void register_genre_bt_ok() {
		/*********************************
		 * dlg	=> dlg_register.xml
		 * dlg2	=> dlg_register_genre.xml
		 * 
		 * 1. Vibrate
		 * 2. Input empty?
		 * 
		 * 3. Register
		 *********************************/
		vib.vibrate(Methods.vibLength_click);
		
		/*********************************
		 * 2. Input empty?
		 *********************************/
		EditText et = 
			(EditText) dlg2.findViewById(R.id.dlg_register_genre_et);
		
		if (et.getText().toString().equals("")) {
			
			// debug
			Toast.makeText(actv, "No input!", 2000).show();
			
			return;
			
		}//if (!et.getText().toString().equals(""))
		
//		// debug
//		Toast.makeText(actv, et.getText().toString(), 2000).show();
		
		/*********************************
		 * 3. Register
		 *********************************/
		Methods.register_genre(actv, dlg, dlg2);
		
		
	}//private void register_genre_bt_ok()

}//public class DialogButtonOnClickListener implements OnClickListener
