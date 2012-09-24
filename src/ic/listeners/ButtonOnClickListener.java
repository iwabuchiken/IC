package ic.listeners;
import ic.main.CheckActv;
import ic.utils.Methods;

import java.io.File;

import android.app.Activity;
import android.app.Dialog;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class ButtonOnClickListener implements OnClickListener {
	/*----------------------------
	 * Fields
		----------------------------*/
	//
	Activity actv;

	//
	Vibrator vib;
	
	//
	int position;
	
	//
	ListView lv;
	
	public ButtonOnClickListener(Activity actv) {
		//
		this.actv = actv;
		
		//
		vib = (Vibrator) actv.getSystemService(actv.VIBRATOR_SERVICE);
	}

	public ButtonOnClickListener(Activity actv, int position) {
		//
		this.actv = actv;
		this.position = position;
		
		//
		vib = (Vibrator) actv.getSystemService(actv.VIBRATOR_SERVICE);
		
		
		
	}//public ButtonOnClickListener(Activity actv, int position)

	public ButtonOnClickListener(Activity actv, ListView lv) {
		// 
		this.actv = actv;
		this.lv = lv;
		
		vib = (Vibrator) actv.getSystemService(actv.VIBRATOR_SERVICE);
	}

//	@Override
	public void onClick(View v) {
//		//
		Methods.ButtonTags tag = (Methods.ButtonTags) v.getTag();

		vib.vibrate(Methods.vibLength_click);
		
		//
		switch (tag) {
		case actv_check_bt_add://---------------------------------------------
		
			Methods.dlg_register_item(actv);
			
//			// debug
//			Toast.makeText(actv, "ADD", Toast.LENGTH_SHORT).show();
			
			break;// case actv_check_bt_add
			
		case actv_check_bt_top://---------------------------------------------
			
			if (CheckActv.checkactv_lv != null) {
				
				CheckActv.checkactv_lv.setSelection(0);
				
			} else {
				
				// debug
				Toast.makeText(actv, "CheckActv.checkactv_lv => null", Toast.LENGTH_SHORT).show();
				
				// Log
				Log.d("ButtonOnClickListener.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber() + "]", "CheckActv.checkactv_lv => null");
				
			}
			
			break;// case actv_check_bt_top
			
		case actv_check_bt_bottom://------------------------------------------

			if (CheckActv.checkactv_lv != null) {
				
				int child_count = CheckActv.checkactv_lv.getChildCount();
				
//				int new_position = CheckActv.checkactv_lv.getCount() - child_count;
				int new_position = CheckActv.checkactv_lv.getCount() - child_count + 1;
				
				CheckActv.checkactv_lv.setSelection(new_position);
				
			} else {
				
				// debug
				Toast.makeText(actv, "CheckActv.checkactv_lv => null", Toast.LENGTH_SHORT).show();
				
				// Log
				Log.d("ButtonOnClickListener.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber() + "]", "CheckActv.checkactv_lv => null");
				
			}
			
			break;// case actv_check_bt_bottom
			
		}//switch (tag)
		
	}//public void onClick(View v)

}
