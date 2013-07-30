package ic.listeners;

import ic.utils.CONS;
import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.DialogInterface.OnClickListener;
import android.util.Log;

public class DialogListener implements OnClickListener {

	Activity actv;
//	Dialog dlg;
	Builder dialog;
	int type;
	
	public DialogListener(Activity actv, Builder dialog, int type) {
		
		this.actv = actv;
		this.dialog = dialog;
		this.type = type;
	}
	
//	@Override
	public void onClick(DialogInterface dialog, int which) {
		/*----------------------------
		 * Steps
			----------------------------*/
		switch (type) {
		
		case 0:	// OK
			
			/*********************************
			 * Reset prefs: Genre id
			 *********************************/
			SharedPreferences prefs = actv
								.getSharedPreferences(
									CONS.Prefs.prefName,
									Context.MODE_PRIVATE);

			SharedPreferences.Editor editor = prefs.edit();
			
			editor.putInt(CONS.Prefs.prefKey_genreId, -1);
			
			editor.commit();
			
			// Log
			Log.d("[" + "MainActv.java : "
					+ +Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Prefs: Genre id => Reset to -1");
			
			/*********************************
			 * Dismiss dialog
			 * Finish activity
			 *********************************/
			
			dialog.dismiss();
			actv.finish();
			
			actv.overridePendingTransition(0, 0);
			
			break;
			
		case 1: // Cancel
			
			dialog.dismiss();
			
			break;
		
		}//switch (type)
		
	}//public void onClick(DialogInterface dialog, int which)
}
