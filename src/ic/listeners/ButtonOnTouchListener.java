package ic.listeners;

import ic.utils.Methods;
import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.os.Vibrator;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageButton;

public class ButtonOnTouchListener implements OnTouchListener {

	/*----------------------------
	 * Fields
		----------------------------*/
	//
	Activity actv;

	//
	Vibrator vib;
	
	public ButtonOnTouchListener(Activity actv) {
		//
		this.actv = actv;
		
		vib = (Vibrator) actv.getSystemService(actv.VIBRATOR_SERVICE);
	}

//	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO 自動生成されたメソッド・スタブ
		Methods.ButtonTags tag = (Methods.ButtonTags) v.getTag();
		
		switch (event.getActionMasked()) {
		case MotionEvent.ACTION_DOWN:
			
			switch (tag) {
			
			case actv_check_bt_add:
			case actv_check_bt_top:
			case actv_check_bt_bottom:
				
				v.setBackgroundColor(Color.GRAY);
				
				break;
			}//switch (tag)
			
			break;//case MotionEvent.ACTION_DOWN:
			
		case MotionEvent.ACTION_UP:
			switch (tag) {
			case actv_check_bt_add:
			case actv_check_bt_top:
			case actv_check_bt_bottom:
				
				v.setBackgroundColor(Color.WHITE);
				break;
			}//switch (tag)
			
			break;//case MotionEvent.ACTION_UP:
		}//switch (event.getActionMasked())
		return false;
	}

}
